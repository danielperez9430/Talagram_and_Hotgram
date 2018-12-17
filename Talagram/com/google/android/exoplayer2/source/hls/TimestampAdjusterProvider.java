package com.google.android.exoplayer2.source.hls;

import android.util.SparseArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;

public final class TimestampAdjusterProvider {
    private final SparseArray timestampAdjusters;

    public TimestampAdjusterProvider() {
        super();
        this.timestampAdjusters = new SparseArray();
    }

    public TimestampAdjuster getAdjuster(int arg4) {
        Object v0 = this.timestampAdjusters.get(arg4);
        if(v0 == null) {
            TimestampAdjuster v0_1 = new TimestampAdjuster(9223372036854775807L);
            this.timestampAdjusters.put(arg4, v0_1);
        }

        return ((TimestampAdjuster)v0);
    }

    public void reset() {
        this.timestampAdjusters.clear();
    }
}

