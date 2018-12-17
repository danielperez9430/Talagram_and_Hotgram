package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.MediaSource$MediaPeriodId;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;

final class PlaybackInfo {
    public static final MediaPeriodId DUMMY_MEDIA_PERIOD_ID;
    public volatile long bufferedPositionUs;
    public final long contentPositionUs;
    public final boolean isLoading;
    public final MediaPeriodId loadingMediaPeriodId;
    public final Object manifest;
    public final MediaPeriodId periodId;
    public final int playbackState;
    public volatile long positionUs;
    public final long startPositionUs;
    public final Timeline timeline;
    public volatile long totalBufferedDurationUs;
    public final TrackGroupArray trackGroups;
    public final TrackSelectorResult trackSelectorResult;

    static {
        PlaybackInfo.DUMMY_MEDIA_PERIOD_ID = new MediaPeriodId(0);
    }

    public PlaybackInfo(Timeline arg4, Object arg5, MediaPeriodId arg6, long arg7, long arg9, int arg11, boolean arg12, TrackGroupArray arg13, TrackSelectorResult arg14, MediaPeriodId arg15, long arg16, long arg18, long arg20) {
        super();
        this.timeline = arg4;
        this.manifest = arg5;
        this.periodId = arg6;
        this.startPositionUs = arg7;
        this.contentPositionUs = arg9;
        this.playbackState = arg11;
        this.isLoading = arg12;
        this.trackGroups = arg13;
        this.trackSelectorResult = arg14;
        this.loadingMediaPeriodId = arg15;
        this.bufferedPositionUs = arg16;
        this.totalBufferedDurationUs = arg18;
        this.positionUs = arg20;
    }

    public PlaybackInfo copyWithIsLoading(boolean arg24) {
        return new PlaybackInfo(this.timeline, this.manifest, this.periodId, this.startPositionUs, this.contentPositionUs, this.playbackState, arg24, this.trackGroups, this.trackSelectorResult, this.loadingMediaPeriodId, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs);
    }

    public PlaybackInfo copyWithLoadingMediaPeriodId(MediaPeriodId arg24) {
        return new PlaybackInfo(this.timeline, this.manifest, this.periodId, this.startPositionUs, this.contentPositionUs, this.playbackState, this.isLoading, this.trackGroups, this.trackSelectorResult, arg24, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs);
    }

    public PlaybackInfo copyWithPeriodIndex(int arg24) {
        return new PlaybackInfo(this.timeline, this.manifest, this.periodId.copyWithPeriodIndex(arg24), this.startPositionUs, this.contentPositionUs, this.playbackState, this.isLoading, this.trackGroups, this.trackSelectorResult, this.loadingMediaPeriodId, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs);
    }

    public PlaybackInfo copyWithPlaybackState(int arg24) {
        return new PlaybackInfo(this.timeline, this.manifest, this.periodId, this.startPositionUs, this.contentPositionUs, arg24, this.isLoading, this.trackGroups, this.trackSelectorResult, this.loadingMediaPeriodId, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs);
    }

    public PlaybackInfo copyWithTimeline(Timeline arg24, Object arg25) {
        return new PlaybackInfo(arg24, arg25, this.periodId, this.startPositionUs, this.contentPositionUs, this.playbackState, this.isLoading, this.trackGroups, this.trackSelectorResult, this.loadingMediaPeriodId, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs);
    }

    public PlaybackInfo copyWithTrackInfo(TrackGroupArray arg24, TrackSelectorResult arg25) {
        return new PlaybackInfo(this.timeline, this.manifest, this.periodId, this.startPositionUs, this.contentPositionUs, this.playbackState, this.isLoading, arg24, arg25, this.loadingMediaPeriodId, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs);
    }

    public static PlaybackInfo createDummy(long arg20, TrackSelectorResult arg22) {
        return new PlaybackInfo(Timeline.EMPTY, null, PlaybackInfo.DUMMY_MEDIA_PERIOD_ID, arg20, -9223372036854775807L, 1, false, TrackGroupArray.EMPTY, arg22, PlaybackInfo.DUMMY_MEDIA_PERIOD_ID, arg20, 0, arg20);
    }

    public PlaybackInfo fromNewPosition(MediaPeriodId arg22, long arg23, long arg25) {
        PlaybackInfo v0 = this;
        PlaybackInfo v20 = null;
        Timeline v2 = v0.timeline;
        Object v3 = v0.manifest;
        long v7 = arg22.isAd() ? arg25 : -9223372036854775807L;
        this(v2, v3, arg22, arg23, v7, v0.playbackState, v0.isLoading, v0.trackGroups, v0.trackSelectorResult, arg22, arg23, 0, arg23);
        return v20;
    }
}

