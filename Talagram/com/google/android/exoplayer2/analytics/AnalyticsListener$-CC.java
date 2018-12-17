package com.google.android.exoplayer2.analytics;

import android.view.Surface;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.MediaSourceEventListener$LoadEventInfo;
import com.google.android.exoplayer2.source.MediaSourceEventListener$MediaLoadData;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import java.io.IOException;

public final class AnalyticsListener$-CC {
    public static void $default$onAudioAttributesChanged(AnalyticsListener arg0, EventTime arg1, AudioAttributes arg2) {
    }

    public static void $default$onAudioSessionId(AnalyticsListener arg0, EventTime arg1, int arg2) {
    }

    public static void $default$onAudioUnderrun(AnalyticsListener arg0, EventTime arg1, int arg2, long arg3, long arg5) {
    }

    public static void $default$onBandwidthEstimate(AnalyticsListener arg0, EventTime arg1, int arg2, long arg3, long arg5) {
    }

    public static void $default$onDecoderDisabled(AnalyticsListener arg0, EventTime arg1, int arg2, DecoderCounters arg3) {
    }

    public static void $default$onDecoderEnabled(AnalyticsListener arg0, EventTime arg1, int arg2, DecoderCounters arg3) {
    }

    public static void $default$onDecoderInitialized(AnalyticsListener arg0, EventTime arg1, int arg2, String arg3, long arg4) {
    }

    public static void $default$onDecoderInputFormatChanged(AnalyticsListener arg0, EventTime arg1, int arg2, Format arg3) {
    }

    public static void $default$onDownstreamFormatChanged(AnalyticsListener arg0, EventTime arg1, MediaLoadData arg2) {
    }

    public static void $default$onDrmKeysLoaded(AnalyticsListener arg0, EventTime arg1) {
    }

    public static void $default$onDrmKeysRemoved(AnalyticsListener arg0, EventTime arg1) {
    }

    public static void $default$onDrmKeysRestored(AnalyticsListener arg0, EventTime arg1) {
    }

    public static void $default$onDrmSessionManagerError(AnalyticsListener arg0, EventTime arg1, Exception arg2) {
    }

    public static void $default$onDroppedVideoFrames(AnalyticsListener arg0, EventTime arg1, int arg2, long arg3) {
    }

    public static void $default$onLoadCanceled(AnalyticsListener arg0, EventTime arg1, LoadEventInfo arg2, MediaLoadData arg3) {
    }

    public static void $default$onLoadCompleted(AnalyticsListener arg0, EventTime arg1, LoadEventInfo arg2, MediaLoadData arg3) {
    }

    public static void $default$onLoadError(AnalyticsListener arg0, EventTime arg1, LoadEventInfo arg2, MediaLoadData arg3, IOException arg4, boolean arg5) {
    }

    public static void $default$onLoadStarted(AnalyticsListener arg0, EventTime arg1, LoadEventInfo arg2, MediaLoadData arg3) {
    }

    public static void $default$onLoadingChanged(AnalyticsListener arg0, EventTime arg1, boolean arg2) {
    }

    public static void $default$onMediaPeriodCreated(AnalyticsListener arg0, EventTime arg1) {
    }

    public static void $default$onMediaPeriodReleased(AnalyticsListener arg0, EventTime arg1) {
    }

    public static void $default$onMetadata(AnalyticsListener arg0, EventTime arg1, Metadata arg2) {
    }

    public static void $default$onPlaybackParametersChanged(AnalyticsListener arg0, EventTime arg1, PlaybackParameters arg2) {
    }

    public static void $default$onPlayerError(AnalyticsListener arg0, EventTime arg1, ExoPlaybackException arg2) {
    }

    public static void $default$onPlayerStateChanged(AnalyticsListener arg0, EventTime arg1, boolean arg2, int arg3) {
    }

    public static void $default$onPositionDiscontinuity(AnalyticsListener arg0, EventTime arg1, int arg2) {
    }

    public static void $default$onReadingStarted(AnalyticsListener arg0, EventTime arg1) {
    }

    public static void $default$onRenderedFirstFrame(AnalyticsListener arg0, EventTime arg1, Surface arg2) {
    }

    public static void $default$onRepeatModeChanged(AnalyticsListener arg0, EventTime arg1, int arg2) {
    }

    public static void $default$onSeekProcessed(AnalyticsListener arg0, EventTime arg1) {
    }

    public static void $default$onSeekStarted(AnalyticsListener arg0, EventTime arg1) {
    }

    public static void $default$onShuffleModeChanged(AnalyticsListener arg0, EventTime arg1, boolean arg2) {
    }

    public static void $default$onSurfaceSizeChanged(AnalyticsListener arg0, EventTime arg1, int arg2, int arg3) {
    }

    public static void $default$onTimelineChanged(AnalyticsListener arg0, EventTime arg1, int arg2) {
    }

    public static void $default$onTracksChanged(AnalyticsListener arg0, EventTime arg1, TrackGroupArray arg2, TrackSelectionArray arg3) {
    }

    public static void $default$onUpstreamDiscarded(AnalyticsListener arg0, EventTime arg1, MediaLoadData arg2) {
    }

    public static void $default$onVideoSizeChanged(AnalyticsListener arg0, EventTime arg1, int arg2, int arg3, int arg4, float arg5) {
    }

    public static void $default$onVolumeChanged(AnalyticsListener arg0, EventTime arg1, float arg2) {
    }
}

