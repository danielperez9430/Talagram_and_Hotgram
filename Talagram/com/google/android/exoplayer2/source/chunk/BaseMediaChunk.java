package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;

public abstract class BaseMediaChunk extends MediaChunk {
    private int[] firstSampleIndices;
    private BaseMediaChunkOutput output;
    public final long seekTimeUs;

    public BaseMediaChunk(DataSource arg13, DataSpec arg14, Format arg15, int arg16, Object arg17, long arg18, long arg20, long arg22, long arg24) {
        super(arg13, arg14, arg15, arg16, arg17, arg18, arg20, arg24);
        this.seekTimeUs = arg22;
    }

    public final int getFirstSampleIndex(int arg2) {
        return this.firstSampleIndices[arg2];
    }

    protected final BaseMediaChunkOutput getOutput() {
        return this.output;
    }

    public void init(BaseMediaChunkOutput arg1) {
        this.output = arg1;
        this.firstSampleIndices = arg1.getWriteIndices();
    }
}

