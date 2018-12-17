package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Util;

public final class SingleSampleMediaChunk extends BaseMediaChunk {
    private boolean loadCompleted;
    private long nextLoadPosition;
    private final Format sampleFormat;
    private final int trackType;

    public SingleSampleMediaChunk(DataSource arg16, DataSpec arg17, Format arg18, int arg19, Object arg20, long arg21, long arg23, long arg25, int arg27, Format arg28) {
        super(arg16, arg17, arg18, arg19, arg20, arg21, arg23, -9223372036854775807L, arg25);
        this.trackType = arg27;
        this.sampleFormat = arg28;
    }

    public void cancelLoad() {
    }

    public boolean isLoadCompleted() {
        return this.loadCompleted;
    }

    public void load() {
        DataSpec v0 = this.dataSpec.subrange(this.nextLoadPosition);
        try {
            long v0_2 = this.dataSource.open(v0);
            if(v0_2 != -1) {
                v0_2 += this.nextLoadPosition;
            }

            DefaultExtractorInput v0_3 = new DefaultExtractorInput(this.dataSource, this.nextLoadPosition, v0_2);
            BaseMediaChunkOutput v1 = this.getOutput();
            v1.setSampleOffsetUs(0);
            int v3 = 0;
            TrackOutput v4 = v1.track(0, this.trackType);
            v4.format(this.sampleFormat);
            while(v3 != -1) {
                this.nextLoadPosition += ((long)v3);
                v3 = v4.sampleData(((ExtractorInput)v0_3), 2147483647, true);
            }

            v4.sampleMetadata(this.startTimeUs, 1, ((int)this.nextLoadPosition), 0, null);
        }
        catch(Throwable v0_1) {
            goto label_48;
        }

        Util.closeQuietly(this.dataSource);
        this.loadCompleted = true;
        return;
    label_48:
        Util.closeQuietly(this.dataSource);
        throw v0_1;
    }
}

