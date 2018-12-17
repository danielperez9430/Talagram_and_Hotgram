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

public class ContainerMediaChunk extends BaseMediaChunk {
    private static final PositionHolder DUMMY_POSITION_HOLDER;
    private final int chunkCount;
    private final ChunkExtractorWrapper extractorWrapper;
    private volatile boolean loadCanceled;
    private boolean loadCompleted;
    private long nextLoadPosition;
    private final long sampleOffsetUs;

    static {
        ContainerMediaChunk.DUMMY_POSITION_HOLDER = new PositionHolder();
    }

    public ContainerMediaChunk(DataSource arg4, DataSpec arg5, Format arg6, int arg7, Object arg8, long arg9, long arg11, long arg13, long arg15, int arg17, long arg18, ChunkExtractorWrapper arg20) {
        super(arg4, arg5, arg6, arg7, arg8, arg9, arg11, arg13, arg15);
        this.chunkCount = arg17;
        this.sampleOffsetUs = arg18;
        this.extractorWrapper = arg20;
    }

    public final void cancelLoad() {
        this.loadCanceled = true;
    }

    public long getNextChunkIndex() {
        return this.chunkIndex + (((long)this.chunkCount));
    }

    public boolean isLoadCompleted() {
        return this.loadCompleted;
    }

    public final void load() {
        DefaultExtractorInput v7;
        DataSpec v0 = this.dataSpec.subrange(this.nextLoadPosition);
        try {
            v7 = new DefaultExtractorInput(this.dataSource, v0.absoluteStreamPosition, this.dataSource.open(v0));
            long v2 = 0;
            if(this.nextLoadPosition == v2) {
                BaseMediaChunkOutput v0_2 = this.getOutput();
                v0_2.setSampleOffsetUs(this.sampleOffsetUs);
                ChunkExtractorWrapper v1 = this.extractorWrapper;
                if(this.seekTimeUs == -9223372036854775807L) {
                }
                else {
                    v2 = this.seekTimeUs - this.sampleOffsetUs;
                }

                v1.init(((TrackOutputProvider)v0_2), v2);
            }
        }
        catch(Throwable v0_1) {
            goto label_61;
        }

        try {
            Extractor v0_3 = this.extractorWrapper.extractor;
            boolean v1_1 = false;
            int v2_1;
            for(v2_1 = 0; v2_1 == 0; v2_1 = v0_3.read(((ExtractorInput)v7), ContainerMediaChunk.DUMMY_POSITION_HOLDER)) {
                if(this.loadCanceled) {
                    break;
                }
            }

            if(v2_1 != 1) {
                v1_1 = true;
            }

            Assertions.checkState(v1_1);
        }
        catch(Throwable v0_1) {
            goto label_52;
        }

        try {
            this.nextLoadPosition = ((ExtractorInput)v7).getPosition() - this.dataSpec.absoluteStreamPosition;
        }
        catch(Throwable v0_1) {
            goto label_61;
        }

        Util.closeQuietly(this.dataSource);
        this.loadCompleted = true;
        return;
        try {
        label_52:
            this.nextLoadPosition = ((ExtractorInput)v7).getPosition() - this.dataSpec.absoluteStreamPosition;
            throw v0_1;
        }
        catch(Throwable v0_1) {
        label_61:
            Util.closeQuietly(this.dataSource);
            throw v0_1;
        }
    }
}

