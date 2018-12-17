package com.google.android.exoplayer2.analytics;

import android.view.Surface;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.MediaSource$MediaPeriodId;
import com.google.android.exoplayer2.source.MediaSourceEventListener$LoadEventInfo;
import com.google.android.exoplayer2.source.MediaSourceEventListener$MediaLoadData;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import java.io.IOException;

public interface AnalyticsListener {
    public final class EventTime {
        public final long currentPlaybackPositionMs;
        public final long eventPlaybackPositionMs;
        public final MediaPeriodId mediaPeriodId;
        public final long realtimeMs;
        public final Timeline timeline;
        public final long totalBufferedDurationMs;
        public final int windowIndex;

        public EventTime(long arg1, Timeline arg3, int arg4, MediaPeriodId arg5, long arg6, long arg8, long arg10) {
            super();
            this.realtimeMs = arg1;
            this.timeline = arg3;
            this.windowIndex = arg4;
            this.mediaPeriodId = arg5;
            this.eventPlaybackPositionMs = arg6;
            this.currentPlaybackPositionMs = arg8;
            this.totalBufferedDurationMs = arg10;
        }
    }

    void onAudioAttributesChanged(EventTime arg1, AudioAttributes arg2);

    void onAudioSessionId(EventTime arg1, int arg2);

    void onAudioUnderrun(EventTime arg1, int arg2, long arg3, long arg4);

    void onBandwidthEstimate(EventTime arg1, int arg2, long arg3, long arg4);

    void onDecoderDisabled(EventTime arg1, int arg2, DecoderCounters arg3);

    void onDecoderEnabled(EventTime arg1, int arg2, DecoderCounters arg3);

    void onDecoderInitialized(EventTime arg1, int arg2, String arg3, long arg4);

    void onDecoderInputFormatChanged(EventTime arg1, int arg2, Format arg3);

    void onDownstreamFormatChanged(EventTime arg1, MediaLoadData arg2);

    void onDrmKeysLoaded(EventTime arg1);

    void onDrmKeysRemoved(EventTime arg1);

    void onDrmKeysRestored(EventTime arg1);

    void onDrmSessionManagerError(EventTime arg1, Exception arg2);

    void onDroppedVideoFrames(EventTime arg1, int arg2, long arg3);

    void onLoadCanceled(EventTime arg1, LoadEventInfo arg2, MediaLoadData arg3);

    void onLoadCompleted(EventTime arg1, LoadEventInfo arg2, MediaLoadData arg3);

    void onLoadError(EventTime arg1, LoadEventInfo arg2, MediaLoadData arg3, IOException arg4, boolean arg5);

    void onLoadStarted(EventTime arg1, LoadEventInfo arg2, MediaLoadData arg3);

    void onLoadingChanged(EventTime arg1, boolean arg2);

    void onMediaPeriodCreated(EventTime arg1);

    void onMediaPeriodReleased(EventTime arg1);

    void onMetadata(EventTime arg1, Metadata arg2);

    void onPlaybackParametersChanged(EventTime arg1, PlaybackParameters arg2);

    void onPlayerError(EventTime arg1, ExoPlaybackException arg2);

    void onPlayerStateChanged(EventTime arg1, boolean arg2, int arg3);

    void onPositionDiscontinuity(EventTime arg1, int arg2);

    void onReadingStarted(EventTime arg1);

    void onRenderedFirstFrame(EventTime arg1, Surface arg2);

    void onRepeatModeChanged(EventTime arg1, int arg2);

    void onSeekProcessed(EventTime arg1);

    void onSeekStarted(EventTime arg1);

    void onShuffleModeChanged(EventTime arg1, boolean arg2);

    void onSurfaceSizeChanged(EventTime arg1, int arg2, int arg3);

    void onTimelineChanged(EventTime arg1, int arg2);

    void onTracksChanged(EventTime arg1, TrackGroupArray arg2, TrackSelectionArray arg3);

    void onUpstreamDiscarded(EventTime arg1, MediaLoadData arg2);

    void onVideoSizeChanged(EventTime arg1, int arg2, int arg3, int arg4, float arg5);

    void onVolumeChanged(EventTime arg1, float arg2);
}

