package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class ChunkIndex implements SeekMap {
    private final long durationUs;
    public final long[] durationsUs;
    public final int length;
    public final long[] offsets;
    public final int[] sizes;
    public final long[] timesUs;

    public ChunkIndex(int[] arg1, long[] arg2, long[] arg3, long[] arg4) {
        super();
        this.sizes = arg1;
        this.offsets = arg2;
        this.durationsUs = arg3;
        this.timesUs = arg4;
        this.length = arg1.length;
        long v1 = this.length > 0 ? arg3[this.length - 1] + arg4[this.length - 1] : 0;
        this.durationUs = v1;
    }

    public int getChunkIndex(long arg3) {
        return Util.binarySearchFloor(this.timesUs, arg3, true, true);
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public SeekPoints getSeekPoints(long arg8) {
        int v0 = this.getChunkIndex(arg8);
        SeekPoint v1 = new SeekPoint(this.timesUs[v0], this.offsets[v0]);
        if(v1.timeUs < arg8) {
            if(v0 == this.length - 1) {
            }
            else {
                ++v0;
                return new SeekPoints(v1, new SeekPoint(this.timesUs[v0], this.offsets[v0]));
            }
        }

        return new SeekPoints(v1);
    }

    public boolean isSeekable() {
        return 1;
    }

    public String toString() {
        return "ChunkIndex(length=" + this.length + ", sizes=" + Arrays.toString(this.sizes) + ", offsets=" + Arrays.toString(this.offsets) + ", timeUs=" + Arrays.toString(this.timesUs) + ", durationsUs=" + Arrays.toString(this.durationsUs) + ")";
    }
}

