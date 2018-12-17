package com.google.android.exoplayer2;

import android.util.Pair;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource$MediaPeriodId;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Assertions;

final class MediaPeriodQueue {
    private static final int MAXIMUM_BUFFER_AHEAD_PERIODS = 100;
    private int length;
    private MediaPeriodHolder loading;
    private long nextWindowSequenceNumber;
    private Object oldFrontPeriodUid;
    private long oldFrontPeriodWindowSequenceNumber;
    private final Period period;
    private MediaPeriodHolder playing;
    private MediaPeriodHolder reading;
    private int repeatMode;
    private boolean shuffleModeEnabled;
    private Timeline timeline;
    private final Window window;

    public MediaPeriodQueue() {
        super();
        this.period = new Period();
        this.window = new Window();
        this.timeline = Timeline.EMPTY;
    }

    public MediaPeriodHolder advancePlayingPeriod() {
        if(this.playing != null) {
            if(this.playing == this.reading) {
                this.reading = this.playing.next;
            }

            this.playing.release();
            --this.length;
            if(this.length == 0) {
                this.loading = null;
                this.oldFrontPeriodUid = this.playing.uid;
                this.oldFrontPeriodWindowSequenceNumber = this.playing.info.id.windowSequenceNumber;
            }

            this.playing = this.playing.next;
        }
        else {
            this.playing = this.loading;
            this.reading = this.loading;
        }

        return this.playing;
    }

    public MediaPeriodHolder advanceReadingPeriod() {
        boolean v0 = this.reading == null || this.reading.next == null ? false : true;
        Assertions.checkState(v0);
        this.reading = this.reading.next;
        return this.reading;
    }

    private boolean canKeepMediaPeriodHolder(MediaPeriodHolder arg6, MediaPeriodInfo arg7) {
        MediaPeriodInfo v6 = arg6.info;
        boolean v6_1 = v6.startPositionUs != arg7.startPositionUs || !v6.id.equals(arg7.id) ? false : true;
        return v6_1;
    }

    public void clear(boolean arg5) {
        MediaPeriodHolder v0 = this.getFrontPeriod();
        Object v1 = null;
        if(v0 != null) {
            Object v5 = arg5 ? v0.uid : v1;
            this.oldFrontPeriodUid = v5;
            this.oldFrontPeriodWindowSequenceNumber = v0.info.id.windowSequenceNumber;
            v0.release();
            this.removeAfter(v0);
        }
        else {
            if(arg5) {
                goto label_17;
            }

            this.oldFrontPeriodUid = v1;
        }

    label_17:
        this.playing = ((MediaPeriodHolder)v1);
        this.loading = ((MediaPeriodHolder)v1);
        this.reading = ((MediaPeriodHolder)v1);
        this.length = 0;
    }

    public MediaPeriod enqueueNextMediaPeriod(RendererCapabilities[] arg13, TrackSelector arg14, Allocator arg15, MediaSource arg16, Object arg17, MediaPeriodInfo arg18) {
        MediaPeriodQueue v0 = this;
        long v2 = v0.loading == null ? arg18.startPositionUs : v0.loading.getRendererOffset() + v0.loading.info.durationUs;
        long v4 = v2;
        MediaPeriodHolder v11 = new MediaPeriodHolder(arg13, v4, arg14, arg15, arg16, arg17, arg18);
        if(v0.loading != null) {
            Assertions.checkState(this.hasPlayingPeriod());
            v0.loading.next = v11;
        }

        v0.oldFrontPeriodUid = null;
        v0.loading = v11;
        ++v0.length;
        return v11.mediaPeriod;
    }

    private MediaPeriodInfo getFirstMediaPeriodInfo(PlaybackInfo arg7) {
        return this.getMediaPeriodInfo(arg7.periodId, arg7.contentPositionUs, arg7.startPositionUs);
    }

    private MediaPeriodInfo getFollowingMediaPeriodInfo(MediaPeriodHolder arg18, long arg19) {
        int v3_1;
        int v1_2;
        long v0_1;
        long v4_1;
        long v9;
        int v2;
        MediaPeriodQueue v8 = this;
        MediaPeriodHolder v0 = arg18;
        MediaPeriodInfo v1 = v0.info;
        int v4 = -1;
        MediaPeriodInfo v5 = null;
        if(v1.isLastInTimelinePeriod) {
            v2 = v8.timeline.getNextPeriodIndex(v1.id.periodIndex, v8.period, v8.window, v8.repeatMode, v8.shuffleModeEnabled);
            if(v2 == v4) {
                return v5;
            }

            int v12 = v8.timeline.getPeriod(v2, v8.period, true).windowIndex;
            Object v3 = v8.period.uid;
            long v6 = v1.id.windowSequenceNumber;
            v9 = 0;
            if(v8.timeline.getWindow(v12, v8.window).firstPeriodIndex == v2) {
                Pair v1_1 = v8.timeline.getPeriodPosition(v8.window, v8.period, v12, -9223372036854775807L, Math.max(v9, arg18.getRendererOffset() + v1.durationUs - arg19));
                if(v1_1 == null) {
                    return v5;
                }
                else {
                    v2 = v1_1.first.intValue();
                    v4_1 = v1_1.second.longValue();
                    if(v0.next == null || !v0.next.uid.equals(v3)) {
                        v0_1 = v8.nextWindowSequenceNumber;
                        v8.nextWindowSequenceNumber = 1 + v0_1;
                    }
                    else {
                        v0_1 = v0.next.info.id.windowSequenceNumber;
                    }

                    v9 = v4_1;
                    v4_1 = v0_1;
                    v1_2 = v2;
                }
            }
            else {
                v1_2 = v2;
                v4_1 = v6;
            }

            return this.getMediaPeriodInfo(this.resolveMediaPeriodIdForAds(v1_2, v9, v4_1), v9, v9);
        }

        MediaPeriodId v0_2 = v1.id;
        v8.timeline.getPeriod(v0_2.periodIndex, v8.period);
        if(v0_2.isAd()) {
            v2 = v0_2.adGroupIndex;
            v3_1 = v8.period.getAdCountInAdGroup(v2);
            if(v3_1 == v4) {
                return v5;
            }

            v4 = v8.period.getNextAdIndexToPlay(v2, v0_2.adIndexInAdGroup);
            if(v4 < v3_1) {
                if(!v8.period.isAdAvailable(v2, v4)) {
                }
                else {
                    v5 = this.getMediaPeriodInfoForAd(v0_2.periodIndex, v2, v4, v1.contentPositionUs, v0_2.windowSequenceNumber);
                }

                return v5;
            }

            return this.getMediaPeriodInfoForContent(v0_2.periodIndex, v1.contentPositionUs, v0_2.windowSequenceNumber);
        }

        v9 = -9223372036854775808L;
        if(v1.id.endPositionUs != v9) {
            v2 = v8.period.getAdGroupIndexForPositionUs(v1.id.endPositionUs);
            if(v2 == v4) {
                return this.getMediaPeriodInfoForContent(v0_2.periodIndex, v1.id.endPositionUs, v0_2.windowSequenceNumber);
            }

            v3_1 = v8.period.getFirstAdIndexToPlay(v2);
            if(!v8.period.isAdAvailable(v2, v3_1)) {
            }
            else {
                v5 = this.getMediaPeriodInfoForAd(v0_2.periodIndex, v2, v3_1, v1.id.endPositionUs, v0_2.windowSequenceNumber);
            }

            return v5;
        }

        v1_2 = v8.period.getAdGroupCount();
        if(v1_2 == 0) {
            return v5;
        }

        v2 = v1_2 - 1;
        if(v8.period.getAdGroupTimeUs(v2) == v9) {
            if(v8.period.hasPlayedAdGroup(v2)) {
            }
            else {
                v3_1 = v8.period.getFirstAdIndexToPlay(v2);
                if(!v8.period.isAdAvailable(v2, v3_1)) {
                    return v5;
                }
                else {
                    return this.getMediaPeriodInfoForAd(v0_2.periodIndex, v2, v3_1, v8.period.getDurationUs(), v0_2.windowSequenceNumber);
                }
            }
        }

        return v5;
    }

    public MediaPeriodHolder getFrontPeriod() {
        MediaPeriodHolder v0 = this.hasPlayingPeriod() ? this.playing : this.loading;
        return v0;
    }

    public MediaPeriodHolder getLoadingPeriod() {
        return this.loading;
    }

    private MediaPeriodInfo getMediaPeriodInfo(MediaPeriodId arg9, long arg10, long arg12) {
        this.timeline.getPeriod(arg9.periodIndex, this.period);
        if(arg9.isAd()) {
            if(!this.period.isAdAvailable(arg9.adGroupIndex, arg9.adIndexInAdGroup)) {
                return null;
            }

            return this.getMediaPeriodInfoForAd(arg9.periodIndex, arg9.adGroupIndex, arg9.adIndexInAdGroup, arg10, arg9.windowSequenceNumber);
        }

        return this.getMediaPeriodInfoForContent(arg9.periodIndex, arg12, arg9.windowSequenceNumber);
    }

    private MediaPeriodInfo getMediaPeriodInfoForAd(int arg15, int arg16, int arg17, long arg18, long arg20) {
        MediaPeriodQueue v0 = this;
        MediaPeriodId v7 = new MediaPeriodId(arg15, arg16, arg17, arg20);
        boolean v9 = this.isLastInPeriod(v7);
        boolean v10 = this.isLastInTimeline(v7, v9);
        long v11 = v0.timeline.getPeriod(v7.periodIndex, v0.period).getAdDurationUs(v7.adGroupIndex, v7.adIndexInAdGroup);
        long v1 = arg17 == v0.period.getFirstAdIndexToPlay(arg16) ? v0.period.getAdResumePositionUs() : 0;
        long v3 = v1;
        return new MediaPeriodInfo(v7, v3, arg18, v11, v9, v10);
    }

    private MediaPeriodInfo getMediaPeriodInfoForContent(int arg15, long arg16, long arg18) {
        MediaPeriodQueue v0 = this;
        int v1 = v0.period.getAdGroupIndexAfterPositionUs(arg16);
        long v2 = -9223372036854775808L;
        long v6 = v1 == -1 ? v2 : v0.period.getAdGroupTimeUs(v1);
        MediaPeriodId v1_1 = new MediaPeriodId(arg15, arg18, v6);
        v0.timeline.getPeriod(v1_1.periodIndex, v0.period);
        boolean v10 = this.isLastInPeriod(v1_1);
        boolean v11 = this.isLastInTimeline(v1_1, v10);
        long v8 = v6 == v2 ? v0.period.getDurationUs() : v6;
        return new MediaPeriodInfo(v1_1, arg16, -9223372036854775807L, v8, v10, v11);
    }

    public MediaPeriodInfo getNextMediaPeriodInfo(long arg2, PlaybackInfo arg4) {
        MediaPeriodInfo v2 = this.loading == null ? this.getFirstMediaPeriodInfo(arg4) : this.getFollowingMediaPeriodInfo(this.loading, arg2);
        return v2;
    }

    public MediaPeriodHolder getPlayingPeriod() {
        return this.playing;
    }

    public MediaPeriodHolder getReadingPeriod() {
        return this.reading;
    }

    private MediaPeriodInfo getUpdatedMediaPeriodInfo(MediaPeriodInfo arg12, MediaPeriodId arg13) {
        long v0;
        long v2 = arg12.startPositionUs;
        boolean v8 = this.isLastInPeriod(arg13);
        boolean v9 = this.isLastInTimeline(arg13, v8);
        this.timeline.getPeriod(arg13.periodIndex, this.period);
        if(arg13.isAd()) {
            v0 = this.period.getAdDurationUs(arg13.adGroupIndex, arg13.adIndexInAdGroup);
        }
        else if(arg13.endPositionUs == -9223372036854775808L) {
            v0 = this.period.getDurationUs();
        }
        else {
            v0 = arg13.endPositionUs;
        }

        long v6 = v0;
        return new MediaPeriodInfo(arg13, v2, arg12.contentPositionUs, v6, v8, v9);
    }

    public MediaPeriodInfo getUpdatedMediaPeriodInfo(MediaPeriodInfo arg2, int arg3) {
        return this.getUpdatedMediaPeriodInfo(arg2, arg2.id.copyWithPeriodIndex(arg3));
    }

    public boolean hasPlayingPeriod() {
        boolean v0 = this.playing != null ? true : false;
        return v0;
    }

    private boolean isLastInPeriod(MediaPeriodId arg10) {
        // Method was not decompiled
    }

    private boolean isLastInTimeline(MediaPeriodId arg8, boolean arg9) {
        boolean v8 = (this.timeline.getWindow(this.timeline.getPeriod(arg8.periodIndex, this.period).windowIndex, this.window).isDynamic) || !this.timeline.isLastPeriod(arg8.periodIndex, this.period, this.window, this.repeatMode, this.shuffleModeEnabled) || !arg9 ? false : true;
        return v8;
    }

    public boolean isLoading(MediaPeriod arg2) {
        boolean v2 = this.loading == null || this.loading.mediaPeriod != arg2 ? false : true;
        return v2;
    }

    public void reevaluateBuffer(long arg2) {
        if(this.loading != null) {
            this.loading.reevaluateBuffer(arg2);
        }
    }

    public boolean removeAfter(MediaPeriodHolder arg4) {
        boolean v0 = false;
        boolean v2 = arg4 != null ? true : false;
        Assertions.checkState(v2);
        this.loading = arg4;
        while(arg4.next != null) {
            arg4 = arg4.next;
            if(arg4 == this.reading) {
                this.reading = this.playing;
                v0 = true;
            }

            arg4.release();
            --this.length;
        }

        this.loading.next = null;
        return v0;
    }

    private MediaPeriodId resolveMediaPeriodIdForAds(int arg8, long arg9, long arg11) {
        this.timeline.getPeriod(arg8, this.period);
        int v3 = this.period.getAdGroupIndexForPositionUs(arg9);
        int v0 = -1;
        if(v3 == v0) {
            int v9 = this.period.getAdGroupIndexAfterPositionUs(arg9);
            arg9 = v9 == v0 ? -9223372036854775808L : this.period.getAdGroupTimeUs(v9);
            long v4 = arg9;
            return new MediaPeriodId(arg8, arg11, v4);
        }

        return new MediaPeriodId(arg8, v3, this.period.getFirstAdIndexToPlay(v3), arg11);
    }

    public MediaPeriodId resolveMediaPeriodIdForAds(int arg7, long arg8) {
        return this.resolveMediaPeriodIdForAds(arg7, arg8, this.resolvePeriodIndexToWindowSequenceNumber(arg7));
    }

    private long resolvePeriodIndexToWindowSequenceNumber(int arg6) {
        int v1;
        Object v6 = this.timeline.getPeriod(arg6, this.period, true).uid;
        int v0 = this.period.windowIndex;
        int v2 = -1;
        if(this.oldFrontPeriodUid != null) {
            v1 = this.timeline.getIndexOfPeriod(this.oldFrontPeriodUid);
            if(v1 != v2 && this.timeline.getPeriod(v1, this.period).windowIndex == v0) {
                return this.oldFrontPeriodWindowSequenceNumber;
            }
        }

        MediaPeriodHolder v1_1;
        for(v1_1 = this.getFrontPeriod(); v1_1 != null; v1_1 = v1_1.next) {
            if(v1_1.uid.equals(v6)) {
                return v1_1.info.id.windowSequenceNumber;
            }
        }

        MediaPeriodHolder v6_1;
        for(v6_1 = this.getFrontPeriod(); v6_1 != null; v6_1 = v6_1.next) {
            v1 = this.timeline.getIndexOfPeriod(v6_1.uid);
            if(v1 != v2 && this.timeline.getPeriod(v1, this.period).windowIndex == v0) {
                return v6_1.info.id.windowSequenceNumber;
            }
        }

        long v0_1 = this.nextWindowSequenceNumber;
        this.nextWindowSequenceNumber = 1 + v0_1;
        return v0_1;
    }

    public void setTimeline(Timeline arg1) {
        this.timeline = arg1;
    }

    public boolean shouldLoadNextMediaPeriod() {
        boolean v0;
        if(this.loading != null) {
            if(!this.loading.info.isFinal && (this.loading.isFullyBuffered()) && this.loading.info.durationUs != -9223372036854775807L && this.length < 100) {
                goto label_20;
            }

            v0 = false;
        }
        else {
        label_20:
            v0 = true;
        }

        return v0;
    }

    private boolean updateForPlaybackModeChange() {
        MediaPeriodHolder v0 = this.getFrontPeriod();
        boolean v1 = true;
        if(v0 == null) {
            return 1;
        }

        while(true) {
            int v2 = this.timeline.getNextPeriodIndex(v0.info.id.periodIndex, this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
            while(v0.next != null) {
                if(v0.info.isLastInTimelinePeriod) {
                    break;
                }

                v0 = v0.next;
            }

            if(v2 != -1 && v0.next != null) {
                if(v0.next.info.id.periodIndex != v2) {
                }
                else {
                    v0 = v0.next;
                    continue;
                }
            }

            break;
        }

        boolean v2_1 = this.removeAfter(v0);
        v0.info = this.getUpdatedMediaPeriodInfo(v0.info, v0.info.id);
        if(v2_1) {
            if(!this.hasPlayingPeriod()) {
            }
            else {
                v1 = false;
            }
        }

        return v1;
    }

    public boolean updateQueuedPeriods(MediaPeriodId arg10, long arg11) {
        int v3 = arg10.periodIndex;
        MediaPeriodHolder v10 = null;
        MediaPeriodHolder v1 = this.getFrontPeriod();
        while(true) {
            if(v1 == null) {
                return 1;
            }

            if(v10 == null) {
                v1.info = this.getUpdatedMediaPeriodInfo(v1.info, v3);
            }
            else {
                if(v3 != -1) {
                    if(!v1.uid.equals(this.timeline.getUidOfPeriod(v3))) {
                    }
                    else {
                        MediaPeriodInfo v2 = this.getFollowingMediaPeriodInfo(v10, arg11);
                        if(v2 == null) {
                            return this.removeAfter(v10) ^ 1;
                        }
                        else {
                            v1.info = this.getUpdatedMediaPeriodInfo(v1.info, v3);
                            if(!this.canKeepMediaPeriodHolder(v1, v2)) {
                                return this.removeAfter(v10) ^ 1;
                            }
                            else {
                                goto label_34;
                            }
                        }
                    }
                }

                break;
            }

        label_34:
            if(v1.info.isLastInTimelinePeriod) {
                v3 = this.timeline.getNextPeriodIndex(v3, this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
            }

            MediaPeriodHolder v8 = v1;
            v1 = v1.next;
            v10 = v8;
        }

        return this.removeAfter(v10) ^ 1;
    }

    public boolean updateRepeatMode(int arg1) {
        this.repeatMode = arg1;
        return this.updateForPlaybackModeChange();
    }

    public boolean updateShuffleModeEnabled(boolean arg1) {
        this.shuffleModeEnabled = arg1;
        return this.updateForPlaybackModeChange();
    }
}

