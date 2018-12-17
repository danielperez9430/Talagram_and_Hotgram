package com.google.android.exoplayer2.util;

import android.os.SystemClock;
import android.util.Log;
import android.view.Surface;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Timeline$Period;
import com.google.android.exoplayer2.Timeline$Window;
import com.google.android.exoplayer2.analytics.AnalyticsListener$-CC;
import com.google.android.exoplayer2.analytics.AnalyticsListener$EventTime;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.MediaSourceEventListener$LoadEventInfo;
import com.google.android.exoplayer2.source.MediaSourceEventListener$MediaLoadData;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector$MappedTrackInfo;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class EventLogger implements AnalyticsListener {
    private static final int MAX_TIMELINE_ITEM_LINES = 3;
    private static final String TAG = "EventLogger";
    private static final NumberFormat TIME_FORMAT;
    private final Period period;
    private final long startTimeMs;
    private final MappingTrackSelector trackSelector;
    private final Window window;

    static {
        EventLogger.TIME_FORMAT = NumberFormat.getInstance(Locale.US);
        EventLogger.TIME_FORMAT.setMinimumFractionDigits(2);
        EventLogger.TIME_FORMAT.setMaximumFractionDigits(2);
        EventLogger.TIME_FORMAT.setGroupingUsed(false);
    }

    public EventLogger(MappingTrackSelector arg3) {
        super();
        this.trackSelector = arg3;
        this.window = new Window();
        this.period = new Period();
        this.startTimeMs = SystemClock.elapsedRealtime();
    }

    private static String getAdaptiveSupportString(int arg1, int arg2) {
        if(arg1 < 2) {
            return "N/A";
        }

        if(arg2 != 0) {
            if(arg2 != 8) {
                if(arg2 != 16) {
                    return "?";
                }

                return "YES";
            }

            return "YES_NOT_SEAMLESS";
        }

        return "NO";
    }

    private static String getDiscontinuityReasonString(int arg0) {
        switch(arg0) {
            case 0: {
                return "PERIOD_TRANSITION";
            }
            case 1: {
                return "SEEK";
            }
            case 2: {
                return "SEEK_ADJUSTMENT";
            }
            case 3: {
                return "AD_INSERTION";
            }
            case 4: {
                return "INTERNAL";
            }
        }

        return "?";
    }

    private String getEventString(EventTime arg2, String arg3) {
        return arg3 + " [" + this.getEventTimeString(arg2) + "]";
    }

    private String getEventString(EventTime arg2, String arg3, String arg4) {
        return arg3 + " [" + this.getEventTimeString(arg2) + ", " + arg4 + "]";
    }

    private String getEventTimeString(EventTime arg7) {
        String v0_1 = "window=" + arg7.windowIndex;
        if(arg7.mediaPeriodId != null) {
            v0_1 = v0_1 + ", period=" + arg7.mediaPeriodId.periodIndex;
            if(arg7.mediaPeriodId.isAd()) {
                v0_1 = v0_1 + ", adGroup=" + arg7.mediaPeriodId.adGroupIndex;
                v0_1 = v0_1 + ", ad=" + arg7.mediaPeriodId.adIndexInAdGroup;
            }
        }

        return EventLogger.getTimeString(arg7.realtimeMs - this.startTimeMs) + ", " + EventLogger.getTimeString(arg7.currentPlaybackPositionMs) + ", " + v0_1;
    }

    private static String getFormatSupportString(int arg0) {
        switch(arg0) {
            case 0: {
                return "NO";
            }
            case 1: {
                return "NO_UNSUPPORTED_TYPE";
            }
            case 2: {
                return "NO_UNSUPPORTED_DRM";
            }
            case 3: {
                return "NO_EXCEEDS_CAPABILITIES";
            }
            case 4: {
                return "YES";
            }
        }

        return "?";
    }

    private static String getRepeatModeString(int arg0) {
        switch(arg0) {
            case 0: {
                return "OFF";
            }
            case 1: {
                return "ONE";
            }
            case 2: {
                return "ALL";
            }
        }

        return "?";
    }

    private static String getStateString(int arg0) {
        switch(arg0) {
            case 1: {
                return "IDLE";
            }
            case 2: {
                return "BUFFERING";
            }
            case 3: {
                return "READY";
            }
            case 4: {
                return "ENDED";
            }
        }

        return "?";
    }

    private static String getTimeString(long arg3) {
        String v3 = arg3 == -9223372036854775807L ? "?" : EventLogger.TIME_FORMAT.format(((double)((((float)arg3)) / 1000f)));
        return v3;
    }

    private static String getTimelineChangeReasonString(int arg0) {
        switch(arg0) {
            case 0: {
                return "PREPARED";
            }
            case 1: {
                return "RESET";
            }
            case 2: {
                return "DYNAMIC";
            }
        }

        return "?";
    }

    private static String getTrackStatusString(TrackSelection arg1, TrackGroup arg2, int arg3) {
        boolean v1 = arg1 == null || arg1.getTrackGroup() != arg2 || arg1.indexOf(arg3) == -1 ? false : true;
        return EventLogger.getTrackStatusString(v1);
    }

    private static String getTrackStatusString(boolean arg0) {
        String v0 = arg0 ? "[X]" : "[ ]";
        return v0;
    }

    private static String getTrackTypeString(int arg2) {
        switch(arg2) {
            case 0: {
                return "default";
            }
            case 1: {
                return "audio";
            }
            case 2: {
                return "video";
            }
            case 3: {
                return "text";
            }
            case 4: {
                return "metadata";
            }
            case 5: {
                return "none";
            }
        }

        String v2 = arg2 >= 10000 ? "custom (" + arg2 + ")" : "?";
        return v2;
    }

    private void logd(EventTime arg1, String arg2) {
        this.logd(this.getEventString(arg1, arg2));
    }

    protected void logd(String arg2) {
        Log.d("EventLogger", arg2);
    }

    private void logd(EventTime arg1, String arg2, String arg3) {
        this.logd(this.getEventString(arg1, arg2, arg3));
    }

    private void loge(EventTime arg1, String arg2, String arg3, Throwable arg4) {
        this.loge(this.getEventString(arg1, arg2, arg3), arg4);
    }

    protected void loge(String arg2, Throwable arg3) {
        Log.e("EventLogger", arg2, arg3);
    }

    private void loge(EventTime arg1, String arg2, Throwable arg3) {
        this.loge(this.getEventString(arg1, arg2), arg3);
    }

    public void onAudioAttributesChanged(EventTime arg1, AudioAttributes arg2) {
        AnalyticsListener$-CC.$default$onAudioAttributesChanged(((AnalyticsListener)this), arg1, arg2);
    }

    public void onAudioSessionId(EventTime arg2, int arg3) {
        this.logd(arg2, "audioSessionId", Integer.toString(arg3));
    }

    public void onAudioUnderrun(EventTime arg3, int arg4, long arg5, long arg7) {
        this.loge(arg3, "audioTrackUnderrun", arg4 + ", " + arg5 + ", " + arg7 + "]", null);
    }

    public void onBandwidthEstimate(EventTime arg1, int arg2, long arg3, long arg5) {
    }

    public void onDecoderDisabled(EventTime arg1, int arg2, DecoderCounters arg3) {
        this.logd(arg1, "decoderDisabled", EventLogger.getTrackTypeString(arg2));
    }

    public void onDecoderEnabled(EventTime arg1, int arg2, DecoderCounters arg3) {
        this.logd(arg1, "decoderEnabled", EventLogger.getTrackTypeString(arg2));
    }

    public void onDecoderInitialized(EventTime arg1, int arg2, String arg3, long arg4) {
        this.logd(arg1, "decoderInitialized", EventLogger.getTrackTypeString(arg2) + ", " + arg3);
    }

    public void onDecoderInputFormatChanged(EventTime arg3, int arg4, Format arg5) {
        this.logd(arg3, "decoderInputFormatChanged", EventLogger.getTrackTypeString(arg4) + ", " + Format.toLogString(arg5));
    }

    public void onDownstreamFormatChanged(EventTime arg2, MediaLoadData arg3) {
        this.logd(arg2, "downstreamFormatChanged", Format.toLogString(arg3.trackFormat));
    }

    public void onDrmKeysLoaded(EventTime arg2) {
        this.logd(arg2, "drmKeysLoaded");
    }

    public void onDrmKeysRemoved(EventTime arg2) {
        this.logd(arg2, "drmKeysRemoved");
    }

    public void onDrmKeysRestored(EventTime arg2) {
        this.logd(arg2, "drmKeysRestored");
    }

    public void onDrmSessionManagerError(EventTime arg2, Exception arg3) {
        this.printInternalError(arg2, "drmSessionManagerError", arg3);
    }

    public void onDroppedVideoFrames(EventTime arg1, int arg2, long arg3) {
        this.logd(arg1, "droppedFrames", Integer.toString(arg2));
    }

    public void onLoadCanceled(EventTime arg1, LoadEventInfo arg2, MediaLoadData arg3) {
    }

    public void onLoadCompleted(EventTime arg1, LoadEventInfo arg2, MediaLoadData arg3) {
    }

    public void onLoadError(EventTime arg1, LoadEventInfo arg2, MediaLoadData arg3, IOException arg4, boolean arg5) {
        this.printInternalError(arg1, "loadError", ((Exception)arg4));
    }

    public void onLoadStarted(EventTime arg1, LoadEventInfo arg2, MediaLoadData arg3) {
    }

    public void onLoadingChanged(EventTime arg2, boolean arg3) {
        this.logd(arg2, "loading", Boolean.toString(arg3));
    }

    public void onMediaPeriodCreated(EventTime arg2) {
        this.logd(arg2, "mediaPeriodCreated");
    }

    public void onMediaPeriodReleased(EventTime arg2) {
        this.logd(arg2, "mediaPeriodReleased");
    }

    public void onMetadata(EventTime arg3, Metadata arg4) {
        this.logd("metadata [" + this.getEventTimeString(arg3) + ", ");
        this.printMetadata(arg4, "  ");
        this.logd("]");
    }

    public void onPlaybackParametersChanged(EventTime arg6, PlaybackParameters arg7) {
        this.logd(arg6, "playbackParameters", Util.formatInvariant("speed=%.2f, pitch=%.2f, skipSilence=%s", new Object[]{Float.valueOf(arg7.speed), Float.valueOf(arg7.pitch), Boolean.valueOf(arg7.skipSilence)}));
    }

    public void onPlayerError(EventTime arg2, ExoPlaybackException arg3) {
        this.loge(arg2, "playerFailed", ((Throwable)arg3));
    }

    public void onPlayerStateChanged(EventTime arg3, boolean arg4, int arg5) {
        this.logd(arg3, "state", arg4 + ", " + EventLogger.getStateString(arg5));
    }

    public void onPositionDiscontinuity(EventTime arg2, int arg3) {
        this.logd(arg2, "positionDiscontinuity", EventLogger.getDiscontinuityReasonString(arg3));
    }

    public void onReadingStarted(EventTime arg2) {
        this.logd(arg2, "mediaPeriodReadingStarted");
    }

    public void onRenderedFirstFrame(EventTime arg2, Surface arg3) {
        this.logd(arg2, "renderedFirstFrame", arg3.toString());
    }

    public void onRepeatModeChanged(EventTime arg2, int arg3) {
        this.logd(arg2, "repeatMode", EventLogger.getRepeatModeString(arg3));
    }

    public void onSeekProcessed(EventTime arg2) {
        this.logd(arg2, "seekProcessed");
    }

    public void onSeekStarted(EventTime arg2) {
        this.logd(arg2, "seekStarted");
    }

    public void onShuffleModeChanged(EventTime arg2, boolean arg3) {
        this.logd(arg2, "shuffleModeEnabled", Boolean.toString(arg3));
    }

    public void onSurfaceSizeChanged(EventTime arg3, int arg4, int arg5) {
        this.logd(arg3, "surfaceSizeChanged", arg4 + ", " + arg5);
    }

    public void onTimelineChanged(EventTime arg7, int arg8) {
        int v0 = arg7.timeline.getPeriodCount();
        int v1 = arg7.timeline.getWindowCount();
        this.logd("timelineChanged [" + this.getEventTimeString(arg7) + ", periodCount=" + v0 + ", windowCount=" + v1 + ", reason=" + EventLogger.getTimelineChangeReasonString(arg8));
        arg8 = 0;
        int v2_1;
        for(v2_1 = 0; true; ++v2_1) {
            int v3 = 3;
            if(v2_1 >= Math.min(v0, v3)) {
                break;
            }

            arg7.timeline.getPeriod(v2_1, this.period);
            this.logd("  period [" + EventLogger.getTimeString(this.period.getDurationMs()) + "]");
        }

        if(v0 > v3) {
            this.logd("  ...");
        }

        while(arg8 < Math.min(v1, v3)) {
            arg7.timeline.getWindow(arg8, this.window);
            this.logd("  window [" + EventLogger.getTimeString(this.window.getDurationMs()) + ", " + this.window.isSeekable + ", " + this.window.isDynamic + "]");
            ++arg8;
        }

        if(v1 > v3) {
            this.logd("  ...");
        }

        this.logd("]");
    }

    public void onTracksChanged(EventTime arg12, TrackGroupArray arg13, TrackSelectionArray arg14) {
        MappedTrackInfo v13 = this.trackSelector != null ? this.trackSelector.getCurrentMappedTrackInfo() : null;
        if(v13 == null) {
            this.logd(arg12, "tracksChanged", "[]");
            return;
        }

        this.logd("tracksChanged [" + this.getEventTimeString(arg12) + ", ");
        int v12 = v13.getRendererCount();
        int v1;
        for(v1 = 0; v1 < v12; ++v1) {
            TrackGroupArray v2 = v13.getTrackGroups(v1);
            TrackSelection v3 = arg14.get(v1);
            if(v2.length > 0) {
                this.logd("  Renderer:" + v1 + " [");
                int v4_1;
                for(v4_1 = 0; v4_1 < v2.length; ++v4_1) {
                    TrackGroup v5 = v2.get(v4_1);
                    String v6 = EventLogger.getAdaptiveSupportString(v5.length, v13.getAdaptiveSupport(v1, v4_1, false));
                    this.logd("    Group:" + v4_1 + ", adaptive_supported=" + v6 + " [");
                    int v6_1;
                    for(v6_1 = 0; v6_1 < v5.length; ++v6_1) {
                        String v7_1 = EventLogger.getTrackStatusString(v3, v5, v6_1);
                        String v8 = EventLogger.getFormatSupportString(v13.getTrackSupport(v1, v4_1, v6_1));
                        this.logd("      " + v7_1 + " Track:" + v6_1 + ", " + Format.toLogString(v5.getFormat(v6_1)) + ", supported=" + v8);
                    }

                    this.logd("    ]");
                }

                if(v3 != null) {
                    int v2_1 = 0;
                    while(v2_1 < v3.length()) {
                        Metadata v4_2 = v3.getFormat(v2_1).metadata;
                        if(v4_2 != null) {
                            this.logd("    Metadata [");
                            this.printMetadata(v4_2, "      ");
                            this.logd("    ]");
                        }
                        else {
                            ++v2_1;
                            continue;
                        }

                        break;
                    }
                }

                this.logd("  ]");
            }
        }

        TrackGroupArray v12_1 = v13.getUnmappedTrackGroups();
        if(v12_1.length > 0) {
            this.logd("  Renderer:None [");
            int v13_1;
            for(v13_1 = 0; v13_1 < v12_1.length; ++v13_1) {
                this.logd("    Group:" + v13_1 + " [");
                TrackGroup v14_1 = v12_1.get(v13_1);
                for(v1 = 0; v1 < v14_1.length; ++v1) {
                    String v2_2 = EventLogger.getTrackStatusString(false);
                    String v3_1 = EventLogger.getFormatSupportString(0);
                    this.logd("      " + v2_2 + " Track:" + v1 + ", " + Format.toLogString(v14_1.getFormat(v1)) + ", supported=" + v3_1);
                }

                this.logd("    ]");
            }

            this.logd("  ]");
        }

        this.logd("]");
    }

    public void onUpstreamDiscarded(EventTime arg2, MediaLoadData arg3) {
        this.logd(arg2, "upstreamDiscarded", Format.toLogString(arg3.trackFormat));
    }

    public void onVideoSizeChanged(EventTime arg1, int arg2, int arg3, int arg4, float arg5) {
        this.logd(arg1, "videoSizeChanged", arg2 + ", " + arg3);
    }

    public void onVolumeChanged(EventTime arg1, float arg2) {
        AnalyticsListener$-CC.$default$onVolumeChanged(((AnalyticsListener)this), arg1, arg2);
    }

    private void printInternalError(EventTime arg2, String arg3, Exception arg4) {
        this.loge(arg2, "internalError", arg3, ((Throwable)arg4));
    }

    private void printMetadata(Metadata arg4, String arg5) {
        int v0;
        for(v0 = 0; v0 < arg4.length(); ++v0) {
            this.logd(arg5 + arg4.get(v0));
        }
    }
}

