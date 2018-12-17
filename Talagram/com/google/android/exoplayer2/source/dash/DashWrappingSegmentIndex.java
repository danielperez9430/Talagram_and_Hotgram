package com.google.android.exoplayer2.source.dash;

import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.source.dash.manifest.RangedUri;

public final class DashWrappingSegmentIndex implements DashSegmentIndex {
    private final ChunkIndex chunkIndex;
    private final long timeOffsetUs;

    public DashWrappingSegmentIndex(ChunkIndex arg1, long arg2) {
        super();
        this.chunkIndex = arg1;
        this.timeOffsetUs = arg2;
    }

    public long getDurationUs(long arg1, long arg3) {
        return this.chunkIndex.durationsUs[((int)arg1)];
    }

    public long getFirstSegmentNum() {
        return 0;
    }

    public int getSegmentCount(long arg1) {
        return this.chunkIndex.length;
    }

    public long getSegmentNum(long arg3, long arg5) {
        return ((long)this.chunkIndex.getChunkIndex(arg3 + this.timeOffsetUs));
    }

    public RangedUri getSegmentUrl(long arg8) {
        int v8 = ((int)arg8);
        return new RangedUri(null, this.chunkIndex.offsets[v8], ((long)this.chunkIndex.sizes[v8]));
    }

    public long getTimeUs(long arg3) {
        return this.chunkIndex.timesUs[((int)arg3)] - this.timeOffsetUs;
    }

    public boolean isExplicit() {
        return 1;
    }
}

