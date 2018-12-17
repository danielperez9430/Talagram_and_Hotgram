package com.google.android.exoplayer2.util;

public final class TimestampAdjuster {
    public static final long DO_NOT_OFFSET = 9223372036854775807L;
    private static final long MAX_PTS_PLUS_ONE = 8589934592L;
    private long firstSampleTimestampUs;
    private volatile long lastSampleTimestamp;
    private long timestampOffsetUs;

    public TimestampAdjuster(long arg3) {
        super();
        this.lastSampleTimestamp = -9223372036854775807L;
        this.setFirstSampleTimestampUs(arg3);
    }

    public long adjustSampleTimestamp(long arg6) {
        long v0 = -9223372036854775807L;
        if(arg6 == v0) {
            return v0;
        }

        if(this.lastSampleTimestamp != v0) {
            this.lastSampleTimestamp = arg6;
        }
        else {
            if(this.firstSampleTimestampUs != 9223372036854775807L) {
                this.timestampOffsetUs = this.firstSampleTimestampUs - arg6;
            }

            __monitor_enter(this);
            try {
                this.lastSampleTimestamp = arg6;
                this.notifyAll();
                __monitor_exit(this);
            }
            catch(Throwable v6) {
                try {
                label_21:
                    __monitor_exit(this);
                }
                catch(Throwable v6) {
                    goto label_21;
                }

                throw v6;
            }
        }

        return arg6 + this.timestampOffsetUs;
    }

    public long adjustTsTimestamp(long arg9) {
        long v0 = -9223372036854775807L;
        if(arg9 == v0) {
            return v0;
        }

        if(this.lastSampleTimestamp != v0) {
            v0 = TimestampAdjuster.usToPts(this.lastSampleTimestamp);
            long v2 = (4294967296L + v0) / 8589934592L;
            long v6 = (v2 - 1) * 8589934592L + arg9;
            arg9 += v2 * 8589934592L;
            if(Math.abs(v6 - v0) < Math.abs(arg9 - v0)) {
                arg9 = v6;
            }
        }

        return this.adjustSampleTimestamp(TimestampAdjuster.ptsToUs(arg9));
    }

    public long getFirstSampleTimestampUs() {
        return this.firstSampleTimestampUs;
    }

    public long getLastAdjustedTimestampUs() {
        long v2 = -9223372036854775807L;
        if(this.lastSampleTimestamp != v2) {
            v2 = this.lastSampleTimestamp;
        }
        else if(this.firstSampleTimestampUs != 9223372036854775807L) {
            v2 = this.firstSampleTimestampUs;
        }

        return v2;
    }

    public long getTimestampOffsetUs() {
        // Method was not decompiled
    }

    public static long ptsToUs(long arg2) {
        return arg2 * 1000000 / 90000;
    }

    public void reset() {
        this.lastSampleTimestamp = -9223372036854775807L;
    }

    public void setFirstSampleTimestampUs(long arg6) {
        __monitor_enter(this);
        try {
            boolean v0 = this.lastSampleTimestamp == -9223372036854775807L ? true : false;
            Assertions.checkState(v0);
            this.firstSampleTimestampUs = arg6;
        }
        catch(Throwable v6) {
            __monitor_exit(this);
            throw v6;
        }

        __monitor_exit(this);
    }

    public static long usToPts(long arg2) {
        return arg2 * 90000 / 1000000;
    }

    public void waitUntilInitialized() {
        __monitor_enter(this);
        try {
            while(this.lastSampleTimestamp == -9223372036854775807L) {
                this.wait();
            }
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }
}

