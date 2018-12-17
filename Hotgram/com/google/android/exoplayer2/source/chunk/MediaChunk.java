package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Assertions;

public abstract class MediaChunk extends Chunk {
    public final long chunkIndex;

    public MediaChunk(DataSource arg12, DataSpec arg13, Format arg14, int arg15, Object arg16, long arg17, long arg19, long arg21) {
        super(arg12, arg13, 1, arg14, arg15, arg16, arg17, arg19);
        Assertions.checkNotNull(arg14);
        this.chunkIndex = arg21;
    }

    public long getNextChunkIndex() {
        long v2 = -1;
        if(this.chunkIndex != v2) {
            v2 = 1 + this.chunkIndex;
        }

        return v2;
    }

    public abstract boolean isLoadCompleted();
}

