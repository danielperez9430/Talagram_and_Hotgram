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

@Deprecated public abstract class DefaultAnalyticsListener implements AnalyticsListener {
    public DefaultAnalyticsListener() {
        super();
    }

    public void onAudioAttributesChanged(EventTime arg1, AudioAttributes arg2) {
        AnalyticsListener$-CC.$default$onAudioAttributesChanged(((AnalyticsListener)this), arg1, arg2);
    }

    public void onAudioSessionId(EventTime arg1, int arg2) {
        AnalyticsListener$-CC.$default$onAudioSessionId(((AnalyticsListener)this), arg1, arg2);
    }

    public void onAudioUnderrun(EventTime arg1, int arg2, long arg3, long arg5) {
        AnalyticsListener$-CC.$default$onAudioUnderrun(((AnalyticsListener)this), arg1, arg2, arg3, arg5);
    }

    public void onBandwidthEstimate(EventTime arg1, int arg2, long arg3, long arg5) {
        AnalyticsListener$-CC.$default$onBandwidthEstimate(((AnalyticsListener)this), arg1, arg2, arg3, arg5);
    }

    public void onDecoderDisabled(EventTime arg1, int arg2, DecoderCounters arg3) {
        AnalyticsListener$-CC.$default$onDecoderDisabled(((AnalyticsListener)this), arg1, arg2, arg3);
    }

    public void onDecoderEnabled(EventTime arg1, int arg2, DecoderCounters arg3) {
        AnalyticsListener$-CC.$default$onDecoderEnabled(((AnalyticsListener)this), arg1, arg2, arg3);
    }

    public void onDecoderInitialized(EventTime arg1, int arg2, String arg3, long arg4) {
        AnalyticsListener$-CC.$default$onDecoderInitialized(((AnalyticsListener)this), arg1, arg2, arg3, arg4);
    }

    public void onDecoderInputFormatChanged(EventTime arg1, int arg2, Format arg3) {
        AnalyticsListener$-CC.$default$onDecoderInputFormatChanged(((AnalyticsListener)this), arg1, arg2, arg3);
    }

    public void onDownstreamFormatChanged(EventTime arg1, MediaLoadData arg2) {
        AnalyticsListener$-CC.$default$onDownstreamFormatChanged(((AnalyticsListener)this), arg1, arg2);
    }

    public void onDrmKeysLoaded(EventTime arg1) {
        AnalyticsListener$-CC.$default$onDrmKeysLoaded(((AnalyticsListener)this), arg1);
    }

    public void onDrmKeysRemoved(EventTime arg1) {
        AnalyticsListener$-CC.$default$onDrmKeysRemoved(((AnalyticsListener)this), arg1);
    }

    public void onDrmKeysRestored(EventTime arg1) {
        AnalyticsListener$-CC.$default$onDrmKeysRestored(((AnalyticsListener)this), arg1);
    }

    public void onDrmSessionManagerError(EventTime arg1, Exception arg2) {
        AnalyticsListener$-CC.$default$onDrmSessionManagerError(((AnalyticsListener)this), arg1, arg2);
    }

    public void onDroppedVideoFrames(EventTime arg1, int arg2, long arg3) {
        AnalyticsListener$-CC.$default$onDroppedVideoFrames(((AnalyticsListener)this), arg1, arg2, arg3);
    }

    public void onLoadCanceled(EventTime arg1, LoadEventInfo arg2, MediaLoadData arg3) {
        AnalyticsListener$-CC.$default$onLoadCanceled(((AnalyticsListener)this), arg1, arg2, arg3);
    }

    public void onLoadCompleted(EventTime arg1, LoadEventInfo arg2, MediaLoadData arg3) {
        AnalyticsListener$-CC.$default$onLoadCompleted(((AnalyticsListener)this), arg1, arg2, arg3);
    }

    public void onLoadError(EventTime arg1, LoadEventInfo arg2, MediaLoadData arg3, IOException arg4, boolean arg5) {
        AnalyticsListener$-CC.$default$onLoadError(((AnalyticsListener)this), arg1, arg2, arg3, arg4, arg5);
    }

    public void onLoadStarted(EventTime arg1, LoadEventInfo arg2, MediaLoadData arg3) {
        AnalyticsListener$-CC.$default$onLoadStarted(((AnalyticsListener)this), arg1, arg2, arg3);
    }

    public void onLoadingChanged(EventTime arg1, boolean arg2) {
        AnalyticsListener$-CC.$default$onLoadingChanged(((AnalyticsListener)this), arg1, arg2);
    }

    public void onMediaPeriodCreated(EventTime arg1) {
        AnalyticsListener$-CC.$default$onMediaPeriodCreated(((AnalyticsListener)this), arg1);
    }

    public void onMediaPeriodReleased(EventTime arg1) {
        AnalyticsListener$-CC.$default$onMediaPeriodReleased(((AnalyticsListener)this), arg1);
    }

    public void onMetadata(EventTime arg1, Metadata arg2) {
        AnalyticsListener$-CC.$default$onMetadata(((AnalyticsListener)this), arg1, arg2);
    }

    public void onPlaybackParametersChanged(EventTime arg1, PlaybackParameters arg2) {
        AnalyticsListener$-CC.$default$onPlaybackParametersChanged(((AnalyticsListener)this), arg1, arg2);
    }

    public void onPlayerError(EventTime arg1, ExoPlaybackException arg2) {
        AnalyticsListener$-CC.$default$onPlayerError(((AnalyticsListener)this), arg1, arg2);
    }

    public void onPlayerStateChanged(EventTime arg1, boolean arg2, int arg3) {
        AnalyticsListener$-CC.$default$onPlayerStateChanged(((AnalyticsListener)this), arg1, arg2, arg3);
    }

    public void onPositionDiscontinuity(EventTime arg1, int arg2) {
        AnalyticsListener$-CC.$default$onPositionDiscontinuity(((AnalyticsListener)this), arg1, arg2);
    }

    public void onReadingStarted(EventTime arg1) {
        AnalyticsListener$-CC.$default$onReadingStarted(((AnalyticsListener)this), arg1);
    }

    public void onRenderedFirstFrame(EventTime arg1, Surface arg2) {
        AnalyticsListener$-CC.$default$onRenderedFirstFrame(((AnalyticsListener)this), arg1, arg2);
    }

    public void onRepeatModeChanged(EventTime arg1, int arg2) {
        AnalyticsListener$-CC.$default$onRepeatModeChanged(((AnalyticsListener)this), arg1, arg2);
    }

    public void onSeekProcessed(EventTime arg1) {
        AnalyticsListener$-CC.$default$onSeekProcessed(((AnalyticsListener)this), arg1);
    }

    public void onSeekStarted(EventTime arg1) {
        AnalyticsListener$-CC.$default$onSeekStarted(((AnalyticsListener)this), arg1);
    }

    public void onShuffleModeChanged(EventTime arg1, boolean arg2) {
        AnalyticsListener$-CC.$default$onShuffleModeChanged(((AnalyticsListener)this), arg1, arg2);
    }

    public void onSurfaceSizeChanged(EventTime arg1, int arg2, int arg3) {
        AnalyticsListener$-CC.$default$onSurfaceSizeChanged(((AnalyticsListener)this), arg1, arg2, arg3);
    }

    public void onTimelineChanged(EventTime arg1, int arg2) {
        AnalyticsListener$-CC.$default$onTimelineChanged(((AnalyticsListener)this), arg1, arg2);
    }

    public void onTracksChanged(EventTime arg1, TrackGroupArray arg2, TrackSelectionArray arg3) {
        AnalyticsListener$-CC.$default$onTracksChanged(((AnalyticsListener)this), arg1, arg2, arg3);
    }

    public void onUpstreamDiscarded(EventTime arg1, MediaLoadData arg2) {
        AnalyticsListener$-CC.$default$onUpstreamDiscarded(((AnalyticsListener)this), arg1, arg2);
    }

    public void onVideoSizeChanged(EventTime arg1, int arg2, int arg3, int arg4, float arg5) {
        AnalyticsListener$-CC.$default$onVideoSizeChanged(((AnalyticsListener)this), arg1, arg2, arg3, arg4, arg5);
    }

    public void onVolumeChanged(EventTime arg1, float arg2) {
        AnalyticsListener$-CC.$default$onVolumeChanged(((AnalyticsListener)this), arg1, arg2);
    }
}

