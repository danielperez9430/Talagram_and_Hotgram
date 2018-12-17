package com.google.android.exoplayer2.upstream;

import android.os.Handler;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.EventDispatcher;
import com.google.android.exoplayer2.util.SlidingPercentile;

public final class DefaultBandwidthMeter implements BandwidthMeter, TransferListener {
    class com.google.android.exoplayer2.upstream.DefaultBandwidthMeter$1 {
    }

    public final class Builder {
        private Clock clock;
        private Handler eventHandler;
        private EventListener eventListener;
        private long initialBitrateEstimate;
        private int slidingWindowMaxWeight;

        public Builder() {
            super();
            this.initialBitrateEstimate = 1000000;
            this.slidingWindowMaxWeight = 2000;
            this.clock = Clock.DEFAULT;
        }

        public DefaultBandwidthMeter build() {
            DefaultBandwidthMeter v6 = new DefaultBandwidthMeter(this.initialBitrateEstimate, this.slidingWindowMaxWeight, this.clock, null);
            if(this.eventHandler != null && this.eventListener != null) {
                v6.addEventListener(this.eventHandler, this.eventListener);
            }

            return v6;
        }

        public Builder setClock(Clock arg1) {
            this.clock = arg1;
            return this;
        }

        public Builder setEventListener(Handler arg2, EventListener arg3) {
            boolean v0 = arg2 == null || arg3 == null ? false : true;
            Assertions.checkArgument(v0);
            this.eventHandler = arg2;
            this.eventListener = arg3;
            return this;
        }

        public Builder setInitialBitrateEstimate(long arg1) {
            this.initialBitrateEstimate = arg1;
            return this;
        }

        public Builder setSlidingWindowMaxWeight(int arg1) {
            this.slidingWindowMaxWeight = arg1;
            return this;
        }
    }

    private static final int BYTES_TRANSFERRED_FOR_ESTIMATE = 524288;
    public static final long DEFAULT_INITIAL_BITRATE_ESTIMATE = 1000000;
    public static final int DEFAULT_SLIDING_WINDOW_MAX_WEIGHT = 2000;
    private static final int ELAPSED_MILLIS_FOR_ESTIMATE = 2000;
    private long bitrateEstimate;
    private final Clock clock;
    private final EventDispatcher eventDispatcher;
    private long sampleBytesTransferred;
    private long sampleStartTimeMs;
    private final SlidingPercentile slidingPercentile;
    private int streamCount;
    private long totalBytesTransferred;
    private long totalElapsedTimeMs;

    public DefaultBandwidthMeter() {
        this(1000000, 2000, Clock.DEFAULT);
    }

    private DefaultBandwidthMeter(long arg2, int arg4, Clock arg5) {
        super();
        this.eventDispatcher = new EventDispatcher();
        this.slidingPercentile = new SlidingPercentile(arg4);
        this.clock = arg5;
        this.bitrateEstimate = arg2;
    }

    DefaultBandwidthMeter(long arg1, int arg3, Clock arg4, com.google.android.exoplayer2.upstream.DefaultBandwidthMeter$1 arg5) {
        this(arg1, arg3, arg4);
    }

    @Deprecated public DefaultBandwidthMeter(Handler arg5, EventListener arg6) {
        this(1000000, 2000, Clock.DEFAULT);
        if(arg5 != null && arg6 != null) {
            this.addEventListener(arg5, arg6);
        }
    }

    @Deprecated public DefaultBandwidthMeter(Handler arg4, EventListener arg5, int arg6) {
        this(1000000, arg6, Clock.DEFAULT);
        if(arg4 != null && arg5 != null) {
            this.addEventListener(arg4, arg5);
        }
    }

    public void addEventListener(Handler arg2, EventListener arg3) {
        this.eventDispatcher.addListener(arg2, arg3);
    }

    public long getBitrateEstimate() {
        long v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.bitrateEstimate;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    public TransferListener getTransferListener() {
        return this;
    }

    static void lambda$notifyBandwidthSample$0(int arg6, long arg7, long arg9, EventListener arg11) {
        arg11.onBandwidthSample(arg6, arg7, arg9);
    }

    private void notifyBandwidthSample(int arg9, long arg10, long arg12) {
        this.eventDispatcher.dispatch(new -$$Lambda$DefaultBandwidthMeter$0dWpVoCfeEm8PONlag-OKGMu96M(arg9, arg10, arg12));
    }

    public void onBytesTransferred(DataSource arg1, DataSpec arg2, boolean arg3, int arg4) {
        __monitor_enter(this);
        if(!arg3) {
            __monitor_exit(this);
            return;
        }

        try {
            this.sampleBytesTransferred += ((long)arg4);
        }
        catch(Throwable v1) {
            __monitor_exit(this);
            throw v1;
        }

        __monitor_exit(this);
    }

    public void onTransferEnd(DataSource arg11, DataSpec arg12, boolean arg13) {
        __monitor_enter(this);
        if(!arg13) {
            __monitor_exit(this);
            return;
        }

        try {
            boolean v11_1 = this.streamCount > 0 ? true : false;
            Assertions.checkState(v11_1);
            long v0 = this.clock.elapsedRealtime();
            int v5 = ((int)(v0 - this.sampleStartTimeMs));
            long v6 = ((long)v5);
            this.totalElapsedTimeMs += v6;
            this.totalBytesTransferred += this.sampleBytesTransferred;
            if(v5 > 0) {
                this.slidingPercentile.addSample(((int)Math.sqrt(((double)this.sampleBytesTransferred))), ((float)(this.sampleBytesTransferred * 8000 / v6)));
                if(this.totalElapsedTimeMs < 2000 && this.totalBytesTransferred < 524288) {
                    goto label_49;
                }

                this.bitrateEstimate = ((long)this.slidingPercentile.getPercentile(0.5f));
            }

        label_49:
            this.notifyBandwidthSample(v5, this.sampleBytesTransferred, this.bitrateEstimate);
            int v11_2 = this.streamCount - 1;
            this.streamCount = v11_2;
            if(v11_2 > 0) {
                this.sampleStartTimeMs = v0;
            }

            this.sampleBytesTransferred = 0;
        }
        catch(Throwable v11) {
            __monitor_exit(this);
            throw v11;
        }

        __monitor_exit(this);
    }

    public void onTransferInitializing(DataSource arg1, DataSpec arg2, boolean arg3) {
    }

    public void onTransferStart(DataSource arg1, DataSpec arg2, boolean arg3) {
        __monitor_enter(this);
        if(!arg3) {
            __monitor_exit(this);
            return;
        }

        try {
            if(this.streamCount == 0) {
                this.sampleStartTimeMs = this.clock.elapsedRealtime();
            }

            ++this.streamCount;
        }
        catch(Throwable v1) {
            __monitor_exit(this);
            throw v1;
        }

        __monitor_exit(this);
    }

    public void removeEventListener(EventListener arg2) {
        this.eventDispatcher.removeListener(arg2);
    }
}

