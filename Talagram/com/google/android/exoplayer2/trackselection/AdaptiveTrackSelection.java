package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.Util;
import java.util.List;

public class AdaptiveTrackSelection extends BaseTrackSelection {
    public final class Factory implements com.google.android.exoplayer2.trackselection.TrackSelection$Factory {
        private final float bandwidthFraction;
        private final BandwidthMeter bandwidthMeter;
        private final float bufferedFractionToLiveEdgeForQualityIncrease;
        private final Clock clock;
        private final int maxDurationForQualityDecreaseMs;
        private final int minDurationForQualityIncreaseMs;
        private final int minDurationToRetainAfterDiscardMs;
        private final long minTimeBetweenBufferReevaluationMs;

        public Factory() {
            this(10000, 25000, 25000, 0.75f, 0.75f, 2000, Clock.DEFAULT);
        }

        public Factory(int arg11, int arg12, int arg13, float arg14, float arg15, long arg16, Clock arg18) {
            this(null, arg11, arg12, arg13, arg14, arg15, arg16, arg18);
        }

        public Factory(int arg10, int arg11, int arg12, float arg13) {
            this(arg10, arg11, arg12, arg13, 0.75f, 2000, Clock.DEFAULT);
        }

        @Deprecated public Factory(BandwidthMeter arg1, int arg2, int arg3, int arg4, float arg5, float arg6, long arg7, Clock arg9) {
            super();
            this.bandwidthMeter = arg1;
            this.minDurationForQualityIncreaseMs = arg2;
            this.maxDurationForQualityDecreaseMs = arg3;
            this.minDurationToRetainAfterDiscardMs = arg4;
            this.bandwidthFraction = arg5;
            this.bufferedFractionToLiveEdgeForQualityIncrease = arg6;
            this.minTimeBetweenBufferReevaluationMs = arg7;
            this.clock = arg9;
        }

        @Deprecated public Factory(BandwidthMeter arg11) {
            this(arg11, 10000, 25000, 25000, 0.75f, 0.75f, 2000, Clock.DEFAULT);
        }

        @Deprecated public Factory(BandwidthMeter arg11, int arg12, int arg13, int arg14, float arg15) {
            this(arg11, arg12, arg13, arg14, arg15, 0.75f, 2000, Clock.DEFAULT);
        }

        public AdaptiveTrackSelection createTrackSelection(TrackGroup arg18, BandwidthMeter arg19, int[] arg20) {
            Factory v0 = this;
            BandwidthMeter v5 = v0.bandwidthMeter != null ? v0.bandwidthMeter : arg19;
            return new AdaptiveTrackSelection(arg18, arg20, v5, ((long)v0.minDurationForQualityIncreaseMs), ((long)v0.maxDurationForQualityDecreaseMs), ((long)v0.minDurationToRetainAfterDiscardMs), v0.bandwidthFraction, v0.bufferedFractionToLiveEdgeForQualityIncrease, v0.minTimeBetweenBufferReevaluationMs, v0.clock);
        }

        public TrackSelection createTrackSelection(TrackGroup arg1, BandwidthMeter arg2, int[] arg3) {
            return this.createTrackSelection(arg1, arg2, arg3);
        }
    }

    public static final float DEFAULT_BANDWIDTH_FRACTION = 0f;
    public static final float DEFAULT_BUFFERED_FRACTION_TO_LIVE_EDGE_FOR_QUALITY_INCREASE = 0f;
    public static final int DEFAULT_MAX_DURATION_FOR_QUALITY_DECREASE_MS = 25000;
    public static final int DEFAULT_MIN_DURATION_FOR_QUALITY_INCREASE_MS = 10000;
    public static final int DEFAULT_MIN_DURATION_TO_RETAIN_AFTER_DISCARD_MS = 25000;
    public static final long DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS = 2000;
    private final float bandwidthFraction;
    private final BandwidthMeter bandwidthMeter;
    private final float bufferedFractionToLiveEdgeForQualityIncrease;
    private final Clock clock;
    private long lastBufferEvaluationMs;
    private final long maxDurationForQualityDecreaseUs;
    private final long minDurationForQualityIncreaseUs;
    private final long minDurationToRetainAfterDiscardUs;
    private final long minTimeBetweenBufferReevaluationMs;
    private float playbackSpeed;
    private int reason;
    private int selectedIndex;

    public AdaptiveTrackSelection(TrackGroup arg16, int[] arg17, BandwidthMeter arg18) {
        this(arg16, arg17, arg18, 10000, 25000, 25000, 0.75f, 0.75f, 2000, Clock.DEFAULT);
    }

    public AdaptiveTrackSelection(TrackGroup arg1, int[] arg2, BandwidthMeter arg3, long arg4, long arg6, long arg8, float arg10, float arg11, long arg12, Clock arg14) {
        super(arg1, arg2);
        this.bandwidthMeter = arg3;
        this.minDurationForQualityIncreaseUs = arg4 * 1000;
        this.maxDurationForQualityDecreaseUs = arg6 * 1000;
        this.minDurationToRetainAfterDiscardUs = arg8 * 1000;
        this.bandwidthFraction = arg10;
        this.bufferedFractionToLiveEdgeForQualityIncrease = arg11;
        this.minTimeBetweenBufferReevaluationMs = arg12;
        this.clock = arg14;
        this.playbackSpeed = 1f;
        this.reason = 1;
        this.lastBufferEvaluationMs = -9223372036854775807L;
        this.selectedIndex = this.determineIdealSelectedIndex(-9223372036854775808L);
    }

    private int determineIdealSelectedIndex(long arg8) {
        long v0 = ((long)((((float)this.bandwidthMeter.getBitrateEstimate())) * this.bandwidthFraction));
        int v2 = 0;
        int v3 = 0;
        while(v2 < this.length) {
            if(arg8 == -9223372036854775808L || !this.isBlacklisted(v2, arg8)) {
                if((((long)Math.round((((float)this.getFormat(v2).bitrate)) * this.playbackSpeed))) <= v0) {
                    return v2;
                }
                else {
                    v3 = v2;
                }
            }

            ++v2;
        }

        return v3;
    }

    public void enable() {
        this.lastBufferEvaluationMs = -9223372036854775807L;
    }

    public int evaluateQueueSize(long arg10, List arg12) {
        long v0 = this.clock.elapsedRealtime();
        if(this.lastBufferEvaluationMs != -9223372036854775807L && v0 - this.lastBufferEvaluationMs < this.minTimeBetweenBufferReevaluationMs) {
            return arg12.size();
        }

        this.lastBufferEvaluationMs = v0;
        int v3 = 0;
        if(arg12.isEmpty()) {
            return 0;
        }

        int v2 = arg12.size();
        if(Util.getPlayoutDurationForMediaDuration(arg12.get(v2 - 1).startTimeUs - arg10, this.playbackSpeed) < this.minDurationToRetainAfterDiscardUs) {
            return v2;
        }

        Format v0_1 = this.getFormat(this.determineIdealSelectedIndex(v0));
        while(v3 < v2) {
            Object v1 = arg12.get(v3);
            Format v4 = ((MediaChunk)v1).trackFormat;
            if(Util.getPlayoutDurationForMediaDuration(((MediaChunk)v1).startTimeUs - arg10, this.playbackSpeed) >= this.minDurationToRetainAfterDiscardUs && v4.bitrate < v0_1.bitrate) {
                int v5 = -1;
                if(v4.height != v5 && v4.height < 720 && v4.width != v5 && v4.width < 1280 && v4.height < v0_1.height) {
                    return v3;
                }
            }

            ++v3;
        }

        return v2;
    }

    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    public Object getSelectionData() {
        return null;
    }

    public int getSelectionReason() {
        return this.reason;
    }

    private long minDurationForQualityIncreaseUs(long arg4) {
        int v0 = arg4 == -9223372036854775807L || arg4 > this.minDurationForQualityIncreaseUs ? 0 : 1;
        return v0 != 0 ? ((long)((((float)arg4)) * this.bufferedFractionToLiveEdgeForQualityIncrease)) : this.minDurationForQualityIncreaseUs;
    }

    public void onPlaybackSpeed(float arg1) {
        this.playbackSpeed = arg1;
    }

    public void updateSelectedTrack(long arg4, long arg6, long arg8) {
        arg4 = this.clock.elapsedRealtime();
        int v0 = this.selectedIndex;
        this.selectedIndex = this.determineIdealSelectedIndex(arg4);
        if(this.selectedIndex == v0) {
            return;
        }

        if(!this.isBlacklisted(v0, arg4)) {
            Format v4 = this.getFormat(v0);
            Format v5 = this.getFormat(this.selectedIndex);
            if(v5.bitrate <= v4.bitrate || arg6 >= this.minDurationForQualityIncreaseUs(arg8)) {
                if(v5.bitrate >= v4.bitrate) {
                    goto label_26;
                }
                else if(arg6 >= this.maxDurationForQualityDecreaseUs) {
                }
                else {
                    goto label_26;
                }
            }

            this.selectedIndex = v0;
        }

    label_26:
        if(this.selectedIndex != v0) {
            this.reason = 3;
        }
    }
}

