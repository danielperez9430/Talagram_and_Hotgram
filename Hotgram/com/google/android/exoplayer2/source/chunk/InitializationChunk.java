package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

public final class InitializationChunk extends Chunk {
    private static final PositionHolder DUMMY_POSITION_HOLDER;
    private final ChunkExtractorWrapper extractorWrapper;
    private volatile boolean loadCanceled;
    private long nextLoadPosition;

    static {
        InitializationChunk.DUMMY_POSITION_HOLDER = new PositionHolder();
    }

    public InitializationChunk(DataSource arg12, DataSpec arg13, Format arg14, int arg15, Object arg16, ChunkExtractorWrapper arg17) {
        super(arg12, arg13, 2, arg14, arg15, arg16, -9223372036854775807L, -9223372036854775807L);
        this.extractorWrapper = arg17;
    }

    public void cancelLoad() {
        this.loadCanceled = true;
    }

    public void load() {
        DefaultExtractorInput v7;
        DataSpec v0 = this.dataSpec.subrange(this.nextLoadPosition);
        try {
            v7 = new DefaultExtractorInput(this.dataSource, v0.absoluteStreamPosition, this.dataSource.open(v0));
            if(this.nextLoadPosition == 0) {
                this.extractorWrapper.init(null, -9223372036854775807L);
            }
        }
        catch(Throwable v0_1) {
            goto label_52;
        }

        try {
            Extractor v0_2 = this.extractorWrapper.extractor;
            int v2;
            for(v2 = 0; v2 == 0; v2 = v0_2.read(((ExtractorInput)v7), InitializationChunk.DUMMY_POSITION_HOLDER)) {
                if(this.loadCanceled) {
                    break;
                }
            }

            boolean v0_3 = true;
            if(v2 != 1) {
            }
            else {
                v0_3 = false;
            }

            Assertions.checkState(v0_3);
        }
        catch(Throwable v0_1) {
            goto label_43;
        }

        try {
            this.nextLoadPosition = ((ExtractorInput)v7).getPosition() - this.dataSpec.absoluteStreamPosition;
        }
        catch(Throwable v0_1) {
            goto label_52;
        }

        Util.closeQuietly(this.dataSource);
        return;
        try {
        label_43:
            this.nextLoadPosition = ((ExtractorInput)v7).getPosition() - this.dataSpec.absoluteStreamPosition;
            throw v0_1;
        }
        catch(Throwable v0_1) {
        label_52:
            Util.closeQuietly(this.dataSource);
            throw v0_1;
        }
    }
}

