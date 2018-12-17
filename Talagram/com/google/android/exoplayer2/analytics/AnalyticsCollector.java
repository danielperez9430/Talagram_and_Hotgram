package com.google.android.exoplayer2.analytics;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player$EventListener;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline$Period;
import com.google.android.exoplayer2.Timeline$Window;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.audio.AudioListener;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.drm.DefaultDrmSessionEventListener;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataOutput;
import com.google.android.exoplayer2.source.MediaSource$MediaPeriodId;
import com.google.android.exoplayer2.source.MediaSourceEventListener$LoadEventInfo;
import com.google.android.exoplayer2.source.MediaSourceEventListener$MediaLoadData;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.video.VideoListener;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class AnalyticsCollector implements EventListener, AudioListener, AudioRendererEventListener, DefaultDrmSessionEventListener, MetadataOutput, MediaSourceEventListener, com.google.android.exoplayer2.upstream.BandwidthMeter$EventListener, VideoListener, VideoRendererEventListener {
    public class Factory {
        public Factory() {
            super();
        }

        public AnalyticsCollector createAnalyticsCollector(Player arg2, Clock arg3) {
            return new AnalyticsCollector(arg2, arg3);
        }
    }

    final class MediaPeriodQueueTracker {
        private final ArrayList activeMediaPeriods;
        private boolean isSeeking;
        private WindowAndMediaPeriodId lastReportedPlayingMediaPeriod;
        private final Period period;
        private WindowAndMediaPeriodId readingMediaPeriod;
        private Timeline timeline;

        public MediaPeriodQueueTracker() {
            super();
            this.activeMediaPeriods = new ArrayList();
            this.period = new Period();
            this.timeline = Timeline.EMPTY;
        }

        static ArrayList access$000(MediaPeriodQueueTracker arg0) {
            return arg0.activeMediaPeriods;
        }

        public WindowAndMediaPeriodId getLastReportedPlayingMediaPeriod() {
            return this.lastReportedPlayingMediaPeriod;
        }

        public WindowAndMediaPeriodId getLoadingMediaPeriod() {
            Object v0_1;
            if(this.activeMediaPeriods.isEmpty()) {
                WindowAndMediaPeriodId v0 = null;
            }
            else {
                v0_1 = this.activeMediaPeriods.get(this.activeMediaPeriods.size() - 1);
            }

            return ((WindowAndMediaPeriodId)v0_1);
        }

        public WindowAndMediaPeriodId getPlayingMediaPeriod() {
            Object v0;
            if((this.activeMediaPeriods.isEmpty()) || (this.timeline.isEmpty()) || (this.isSeeking)) {
                WindowAndMediaPeriodId v0_1 = null;
            }
            else {
                v0 = this.activeMediaPeriods.get(0);
            }

            return ((WindowAndMediaPeriodId)v0);
        }

        public WindowAndMediaPeriodId getReadingMediaPeriod() {
            return this.readingMediaPeriod;
        }

        public boolean isSeeking() {
            return this.isSeeking;
        }

        public void onMediaPeriodCreated(int arg3, MediaPeriodId arg4) {
            this.activeMediaPeriods.add(new WindowAndMediaPeriodId(arg3, arg4));
            if(this.activeMediaPeriods.size() == 1 && !this.timeline.isEmpty()) {
                this.updateLastReportedPlayingMediaPeriod();
            }
        }

        public void onMediaPeriodReleased(int arg2, MediaPeriodId arg3) {
            Object v2_1;
            WindowAndMediaPeriodId v0 = new WindowAndMediaPeriodId(arg2, arg3);
            this.activeMediaPeriods.remove(v0);
            if(v0.equals(this.readingMediaPeriod)) {
                if(this.activeMediaPeriods.isEmpty()) {
                    WindowAndMediaPeriodId v2 = null;
                }
                else {
                    v2_1 = this.activeMediaPeriods.get(0);
                }

                this.readingMediaPeriod = ((WindowAndMediaPeriodId)v2_1);
            }
        }

        public void onPositionDiscontinuity(int arg1) {
            this.updateLastReportedPlayingMediaPeriod();
        }

        public void onReadingStarted(int arg2, MediaPeriodId arg3) {
            this.readingMediaPeriod = new WindowAndMediaPeriodId(arg2, arg3);
        }

        public void onSeekProcessed() {
            this.isSeeking = false;
            this.updateLastReportedPlayingMediaPeriod();
        }

        public void onSeekStarted() {
            this.isSeeking = true;
        }

        public void onTimelineChanged(Timeline arg4) {
            int v0;
            for(v0 = 0; v0 < this.activeMediaPeriods.size(); ++v0) {
                this.activeMediaPeriods.set(v0, this.updateMediaPeriodToNewTimeline(this.activeMediaPeriods.get(v0), arg4));
            }

            if(this.readingMediaPeriod != null) {
                this.readingMediaPeriod = this.updateMediaPeriodToNewTimeline(this.readingMediaPeriod, arg4);
            }

            this.timeline = arg4;
            this.updateLastReportedPlayingMediaPeriod();
        }

        public MediaPeriodId tryResolveWindowIndex(int arg9) {
            MediaPeriodId v1 = null;
            if(this.timeline != null) {
                int v0 = this.timeline.getPeriodCount();
                int v2 = 0;
                MediaPeriodId v3 = v1;
                while(v2 < this.activeMediaPeriods.size()) {
                    Object v4 = this.activeMediaPeriods.get(v2);
                    int v5 = ((WindowAndMediaPeriodId)v4).mediaPeriodId.periodIndex;
                    if(v5 < v0 && this.timeline.getPeriod(v5, this.period).windowIndex == arg9) {
                        if(v3 != null) {
                            return v1;
                        }
                        else {
                            v3 = ((WindowAndMediaPeriodId)v4).mediaPeriodId;
                        }
                    }

                    ++v2;
                }

                v1 = v3;
            }

            return v1;
        }

        private void updateLastReportedPlayingMediaPeriod() {
            if(!this.activeMediaPeriods.isEmpty()) {
                this.lastReportedPlayingMediaPeriod = this.activeMediaPeriods.get(0);
            }
        }

        private WindowAndMediaPeriodId updateMediaPeriodToNewTimeline(WindowAndMediaPeriodId arg3, Timeline arg4) {
            if(!arg4.isEmpty()) {
                if(this.timeline.isEmpty()) {
                }
                else {
                    int v0 = arg4.getIndexOfPeriod(this.timeline.getUidOfPeriod(arg3.mediaPeriodId.periodIndex));
                    if(v0 == -1) {
                        return arg3;
                    }
                    else {
                        return new WindowAndMediaPeriodId(arg4.getPeriod(v0, this.period).windowIndex, arg3.mediaPeriodId.copyWithPeriodIndex(v0));
                    }
                }
            }

            return arg3;
        }
    }

    final class WindowAndMediaPeriodId {
        public final MediaPeriodId mediaPeriodId;
        public final int windowIndex;

        public WindowAndMediaPeriodId(int arg1, MediaPeriodId arg2) {
            super();
            this.windowIndex = arg1;
            this.mediaPeriodId = arg2;
        }

        public boolean equals(Object arg5) {
            boolean v0 = true;
            if(this == (((WindowAndMediaPeriodId)arg5))) {
                return 1;
            }

            if(arg5 != null) {
                if(this.getClass() != arg5.getClass()) {
                }
                else {
                    if(this.windowIndex != ((WindowAndMediaPeriodId)arg5).windowIndex || !this.mediaPeriodId.equals(((WindowAndMediaPeriodId)arg5).mediaPeriodId)) {
                        v0 = false;
                    }
                    else {
                    }

                    return v0;
                }
            }

            return 0;
        }

        public int hashCode() {
            return this.windowIndex * 31 + this.mediaPeriodId.hashCode();
        }
    }

    private final Clock clock;
    private final CopyOnWriteArraySet listeners;
    private final MediaPeriodQueueTracker mediaPeriodQueueTracker;
    private Player player;
    private final Window window;

    protected AnalyticsCollector(Player arg1, Clock arg2) {
        super();
        this.player = arg1;
        this.clock = Assertions.checkNotNull(arg2);
        this.listeners = new CopyOnWriteArraySet();
        this.mediaPeriodQueueTracker = new MediaPeriodQueueTracker();
        this.window = new Window();
    }

    public void addListener(AnalyticsListener arg2) {
        this.listeners.add(arg2);
    }

    private EventTime generateEventTime(WindowAndMediaPeriodId arg2) {
        if(arg2 == null) {
            int v2 = Assertions.checkNotNull(this.player).getCurrentWindowIndex();
            return this.generateEventTime(v2, this.mediaPeriodQueueTracker.tryResolveWindowIndex(v2));
        }

        return this.generateEventTime(arg2.windowIndex, arg2.mediaPeriodId);
    }

    protected EventTime generateEventTime(int arg14, MediaPeriodId arg15) {
        long v7;
        long v0;
        Assertions.checkNotNull(this.player);
        long v2 = this.clock.elapsedRealtime();
        Timeline v4 = this.player.getCurrentTimeline();
        long v5 = 0;
        if(arg14 == this.player.getCurrentWindowIndex()) {
            if(arg15 != null && (arg15.isAd())) {
                if(this.player.getCurrentAdGroupIndex() != arg15.adGroupIndex) {
                }
                else if(this.player.getCurrentAdIndexInAdGroup() == arg15.adIndexInAdGroup) {
                    v5 = this.player.getCurrentPosition();
                }
                else {
                }

                goto label_38;
            }

            v0 = this.player.getContentPosition();
            goto label_26;
        }
        else {
            if(arg14 < v4.getWindowCount() && (arg15 == null || !arg15.isAd())) {
                v0 = v4.getWindow(arg14, this.window).getDefaultPositionMs();
            label_26:
                v7 = v0;
                goto label_39;
            }

        label_38:
            v7 = v5;
        }

    label_39:
        return new EventTime(v2, v4, arg14, arg15, v7, this.player.getCurrentPosition(), this.player.getTotalBufferedDuration());
    }

    private EventTime generateLastReportedPlayingMediaPeriodEventTime() {
        return this.generateEventTime(this.mediaPeriodQueueTracker.getLastReportedPlayingMediaPeriod());
    }

    private EventTime generateLoadingMediaPeriodEventTime() {
        return this.generateEventTime(this.mediaPeriodQueueTracker.getLoadingMediaPeriod());
    }

    private EventTime generatePlayingMediaPeriodEventTime() {
        return this.generateEventTime(this.mediaPeriodQueueTracker.getPlayingMediaPeriod());
    }

    private EventTime generateReadingMediaPeriodEventTime() {
        return this.generateEventTime(this.mediaPeriodQueueTracker.getReadingMediaPeriod());
    }

    protected Set getListeners() {
        return Collections.unmodifiableSet(this.listeners);
    }

    public final void notifySeekStarted() {
        if(!this.mediaPeriodQueueTracker.isSeeking()) {
            EventTime v0 = this.generatePlayingMediaPeriodEventTime();
            this.mediaPeriodQueueTracker.onSeekStarted();
            Iterator v1 = this.listeners.iterator();
            while(v1.hasNext()) {
                v1.next().onSeekStarted(v0);
            }
        }
    }

    public void onAudioAttributesChanged(AudioAttributes arg4) {
        EventTime v0 = this.generateReadingMediaPeriodEventTime();
        Iterator v1 = this.listeners.iterator();
        while(v1.hasNext()) {
            v1.next().onAudioAttributesChanged(v0, arg4);
        }
    }

    public final void onAudioDecoderInitialized(String arg7, long arg8, long arg10) {
        EventTime v8 = this.generateReadingMediaPeriodEventTime();
        Iterator v9 = this.listeners.iterator();
        while(v9.hasNext()) {
            v9.next().onDecoderInitialized(v8, 1, arg7, arg10);
        }
    }

    public final void onAudioDisabled(DecoderCounters arg5) {
        EventTime v0 = this.generateLastReportedPlayingMediaPeriodEventTime();
        Iterator v1 = this.listeners.iterator();
        while(v1.hasNext()) {
            v1.next().onDecoderDisabled(v0, 1, arg5);
        }
    }

    public final void onAudioEnabled(DecoderCounters arg5) {
        EventTime v0 = this.generatePlayingMediaPeriodEventTime();
        Iterator v1 = this.listeners.iterator();
        while(v1.hasNext()) {
            v1.next().onDecoderEnabled(v0, 1, arg5);
        }
    }

    public final void onAudioInputFormatChanged(Format arg5) {
        EventTime v0 = this.generateReadingMediaPeriodEventTime();
        Iterator v1 = this.listeners.iterator();
        while(v1.hasNext()) {
            v1.next().onDecoderInputFormatChanged(v0, 1, arg5);
        }
    }

    public final void onAudioSessionId(int arg4) {
        EventTime v0 = this.generateReadingMediaPeriodEventTime();
        Iterator v1 = this.listeners.iterator();
        while(v1.hasNext()) {
            v1.next().onAudioSessionId(v0, arg4);
        }
    }

    public final void onAudioSinkUnderrun(int arg10, long arg11, long arg13) {
        EventTime v7 = this.generateReadingMediaPeriodEventTime();
        Iterator v8 = this.listeners.iterator();
        while(v8.hasNext()) {
            v8.next().onAudioUnderrun(v7, arg10, arg11, arg13);
        }
    }

    public final void onBandwidthSample(int arg10, long arg11, long arg13) {
        EventTime v7 = this.generateLoadingMediaPeriodEventTime();
        Iterator v8 = this.listeners.iterator();
        while(v8.hasNext()) {
            v8.next().onBandwidthEstimate(v7, arg10, arg11, arg13);
        }
    }

    public final void onDownstreamFormatChanged(int arg2, MediaPeriodId arg3, MediaLoadData arg4) {
        EventTime v2 = this.generateEventTime(arg2, arg3);
        Iterator v3 = this.listeners.iterator();
        while(v3.hasNext()) {
            v3.next().onDownstreamFormatChanged(v2, arg4);
        }
    }

    public final void onDrmKeysLoaded() {
        EventTime v0 = this.generateReadingMediaPeriodEventTime();
        Iterator v1 = this.listeners.iterator();
        while(v1.hasNext()) {
            v1.next().onDrmKeysLoaded(v0);
        }
    }

    public final void onDrmKeysRemoved() {
        EventTime v0 = this.generateReadingMediaPeriodEventTime();
        Iterator v1 = this.listeners.iterator();
        while(v1.hasNext()) {
            v1.next().onDrmKeysRemoved(v0);
        }
    }

    public final void onDrmKeysRestored() {
        EventTime v0 = this.generateReadingMediaPeriodEventTime();
        Iterator v1 = this.listeners.iterator();
        while(v1.hasNext()) {
            v1.next().onDrmKeysRestored(v0);
        }
    }

    public final void onDrmSessionManagerError(Exception arg4) {
        EventTime v0 = this.generateReadingMediaPeriodEventTime();
        Iterator v1 = this.listeners.iterator();
        while(v1.hasNext()) {
            v1.next().onDrmSessionManagerError(v0, arg4);
        }
    }

    public final void onDroppedFrames(int arg4, long arg5) {
        EventTime v0 = this.generateLastReportedPlayingMediaPeriodEventTime();
        Iterator v1 = this.listeners.iterator();
        while(v1.hasNext()) {
            v1.next().onDroppedVideoFrames(v0, arg4, arg5);
        }
    }

    public final void onLoadCanceled(int arg2, MediaPeriodId arg3, LoadEventInfo arg4, MediaLoadData arg5) {
        EventTime v2 = this.generateEventTime(arg2, arg3);
        Iterator v3 = this.listeners.iterator();
        while(v3.hasNext()) {
            v3.next().onLoadCanceled(v2, arg4, arg5);
        }
    }

    public final void onLoadCompleted(int arg2, MediaPeriodId arg3, LoadEventInfo arg4, MediaLoadData arg5) {
        EventTime v2 = this.generateEventTime(arg2, arg3);
        Iterator v3 = this.listeners.iterator();
        while(v3.hasNext()) {
            v3.next().onLoadCompleted(v2, arg4, arg5);
        }
    }

    public final void onLoadError(int arg7, MediaPeriodId arg8, LoadEventInfo arg9, MediaLoadData arg10, IOException arg11, boolean arg12) {
        EventTime v7 = this.generateEventTime(arg7, arg8);
        Iterator v8 = this.listeners.iterator();
        while(v8.hasNext()) {
            v8.next().onLoadError(v7, arg9, arg10, arg11, arg12);
        }
    }

    public final void onLoadStarted(int arg2, MediaPeriodId arg3, LoadEventInfo arg4, MediaLoadData arg5) {
        EventTime v2 = this.generateEventTime(arg2, arg3);
        Iterator v3 = this.listeners.iterator();
        while(v3.hasNext()) {
            v3.next().onLoadStarted(v2, arg4, arg5);
        }
    }

    public final void onLoadingChanged(boolean arg4) {
        EventTime v0 = this.generatePlayingMediaPeriodEventTime();
        Iterator v1 = this.listeners.iterator();
        while(v1.hasNext()) {
            v1.next().onLoadingChanged(v0, arg4);
        }
    }

    public final void onMediaPeriodCreated(int arg2, MediaPeriodId arg3) {
        this.mediaPeriodQueueTracker.onMediaPeriodCreated(arg2, arg3);
        EventTime v2 = this.generateEventTime(arg2, arg3);
        Iterator v3 = this.listeners.iterator();
        while(v3.hasNext()) {
            v3.next().onMediaPeriodCreated(v2);
        }
    }

    public final void onMediaPeriodReleased(int arg2, MediaPeriodId arg3) {
        this.mediaPeriodQueueTracker.onMediaPeriodReleased(arg2, arg3);
        EventTime v2 = this.generateEventTime(arg2, arg3);
        Iterator v3 = this.listeners.iterator();
        while(v3.hasNext()) {
            v3.next().onMediaPeriodReleased(v2);
        }
    }

    public final void onMetadata(Metadata arg4) {
        EventTime v0 = this.generatePlayingMediaPeriodEventTime();
        Iterator v1 = this.listeners.iterator();
        while(v1.hasNext()) {
            v1.next().onMetadata(v0, arg4);
        }
    }

    public final void onPlaybackParametersChanged(PlaybackParameters arg4) {
        EventTime v0 = this.generatePlayingMediaPeriodEventTime();
        Iterator v1 = this.listeners.iterator();
        while(v1.hasNext()) {
            v1.next().onPlaybackParametersChanged(v0, arg4);
        }
    }

    public final void onPlayerError(ExoPlaybackException arg4) {
        EventTime v0 = this.generatePlayingMediaPeriodEventTime();
        Iterator v1 = this.listeners.iterator();
        while(v1.hasNext()) {
            v1.next().onPlayerError(v0, arg4);
        }
    }

    public final void onPlayerStateChanged(boolean arg4, int arg5) {
        EventTime v0 = this.generatePlayingMediaPeriodEventTime();
        Iterator v1 = this.listeners.iterator();
        while(v1.hasNext()) {
            v1.next().onPlayerStateChanged(v0, arg4, arg5);
        }
    }

    public final void onPositionDiscontinuity(int arg4) {
        this.mediaPeriodQueueTracker.onPositionDiscontinuity(arg4);
        EventTime v0 = this.generatePlayingMediaPeriodEventTime();
        Iterator v1 = this.listeners.iterator();
        while(v1.hasNext()) {
            v1.next().onPositionDiscontinuity(v0, arg4);
        }
    }

    public final void onReadingStarted(int arg2, MediaPeriodId arg3) {
        this.mediaPeriodQueueTracker.onReadingStarted(arg2, arg3);
        EventTime v2 = this.generateEventTime(arg2, arg3);
        Iterator v3 = this.listeners.iterator();
        while(v3.hasNext()) {
            v3.next().onReadingStarted(v2);
        }
    }

    public final void onRenderedFirstFrame() {
    }

    public final void onRenderedFirstFrame(Surface arg4) {
        EventTime v0 = this.generateReadingMediaPeriodEventTime();
        Iterator v1 = this.listeners.iterator();
        while(v1.hasNext()) {
            v1.next().onRenderedFirstFrame(v0, arg4);
        }
    }

    public final void onRepeatModeChanged(int arg4) {
        EventTime v0 = this.generatePlayingMediaPeriodEventTime();
        Iterator v1 = this.listeners.iterator();
        while(v1.hasNext()) {
            v1.next().onRepeatModeChanged(v0, arg4);
        }
    }

    public final void onSeekProcessed() {
        if(this.mediaPeriodQueueTracker.isSeeking()) {
            this.mediaPeriodQueueTracker.onSeekProcessed();
            EventTime v0 = this.generatePlayingMediaPeriodEventTime();
            Iterator v1 = this.listeners.iterator();
            while(v1.hasNext()) {
                v1.next().onSeekProcessed(v0);
            }
        }
    }

    public final void onShuffleModeEnabledChanged(boolean arg4) {
        EventTime v0 = this.generatePlayingMediaPeriodEventTime();
        Iterator v1 = this.listeners.iterator();
        while(v1.hasNext()) {
            v1.next().onShuffleModeChanged(v0, arg4);
        }
    }

    public boolean onSurfaceDestroyed(SurfaceTexture arg1) {
        return 0;
    }

    public void onSurfaceSizeChanged(int arg4, int arg5) {
        EventTime v0 = this.generateReadingMediaPeriodEventTime();
        Iterator v1 = this.listeners.iterator();
        while(v1.hasNext()) {
            v1.next().onSurfaceSizeChanged(v0, arg4, arg5);
        }
    }

    public void onSurfaceTextureUpdated(SurfaceTexture arg1) {
    }

    public final void onTimelineChanged(Timeline arg2, Object arg3, int arg4) {
        this.mediaPeriodQueueTracker.onTimelineChanged(arg2);
        EventTime v2 = this.generatePlayingMediaPeriodEventTime();
        Iterator v3 = this.listeners.iterator();
        while(v3.hasNext()) {
            v3.next().onTimelineChanged(v2, arg4);
        }
    }

    public final void onTracksChanged(TrackGroupArray arg4, TrackSelectionArray arg5) {
        EventTime v0 = this.generatePlayingMediaPeriodEventTime();
        Iterator v1 = this.listeners.iterator();
        while(v1.hasNext()) {
            v1.next().onTracksChanged(v0, arg4, arg5);
        }
    }

    public final void onUpstreamDiscarded(int arg2, MediaPeriodId arg3, MediaLoadData arg4) {
        EventTime v2 = this.generateEventTime(arg2, arg3);
        Iterator v3 = this.listeners.iterator();
        while(v3.hasNext()) {
            v3.next().onUpstreamDiscarded(v2, arg4);
        }
    }

    public final void onVideoDecoderInitialized(String arg7, long arg8, long arg10) {
        EventTime v8 = this.generateReadingMediaPeriodEventTime();
        Iterator v9 = this.listeners.iterator();
        while(v9.hasNext()) {
            v9.next().onDecoderInitialized(v8, 2, arg7, arg10);
        }
    }

    public final void onVideoDisabled(DecoderCounters arg5) {
        EventTime v0 = this.generateLastReportedPlayingMediaPeriodEventTime();
        Iterator v1 = this.listeners.iterator();
        while(v1.hasNext()) {
            v1.next().onDecoderDisabled(v0, 2, arg5);
        }
    }

    public final void onVideoEnabled(DecoderCounters arg5) {
        EventTime v0 = this.generatePlayingMediaPeriodEventTime();
        Iterator v1 = this.listeners.iterator();
        while(v1.hasNext()) {
            v1.next().onDecoderEnabled(v0, 2, arg5);
        }
    }

    public final void onVideoInputFormatChanged(Format arg5) {
        EventTime v0 = this.generateReadingMediaPeriodEventTime();
        Iterator v1 = this.listeners.iterator();
        while(v1.hasNext()) {
            v1.next().onDecoderInputFormatChanged(v0, 2, arg5);
        }
    }

    public final void onVideoSizeChanged(int arg9, int arg10, int arg11, float arg12) {
        EventTime v6 = this.generateReadingMediaPeriodEventTime();
        Iterator v7 = this.listeners.iterator();
        while(v7.hasNext()) {
            v7.next().onVideoSizeChanged(v6, arg9, arg10, arg11, arg12);
        }
    }

    public void onVolumeChanged(float arg4) {
        EventTime v0 = this.generateReadingMediaPeriodEventTime();
        Iterator v1 = this.listeners.iterator();
        while(v1.hasNext()) {
            v1.next().onVolumeChanged(v0, arg4);
        }
    }

    public void removeListener(AnalyticsListener arg2) {
        this.listeners.remove(arg2);
    }

    public final void resetForNewMediaSource() {
        Iterator v0 = new ArrayList(MediaPeriodQueueTracker.access$000(this.mediaPeriodQueueTracker)).iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            this.onMediaPeriodReleased(((WindowAndMediaPeriodId)v1).windowIndex, ((WindowAndMediaPeriodId)v1).mediaPeriodId);
        }
    }

    public void setPlayer(Player arg2) {
        boolean v0 = this.player == null ? true : false;
        Assertions.checkState(v0);
        this.player = Assertions.checkNotNull(arg2);
    }
}

