package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public abstract class DataChunk extends Chunk {
    private static final int READ_GRANULARITY = 16384;
    private byte[] data;
    private volatile boolean loadCanceled;

    public DataChunk(DataSource arg12, DataSpec arg13, int arg14, Format arg15, int arg16, Object arg17, byte[] arg18) {
        super(arg12, arg13, arg14, arg15, arg16, arg17, -9223372036854775807L, -9223372036854775807L);
        this.data = arg18;
    }

    public final void cancelLoad() {
        this.loadCanceled = true;
    }

    protected abstract void consume(byte[] arg1, int arg2);

    public byte[] getDataHolder() {
        return this.data;
    }

    public final void load() {
        try {
            this.dataSource.open(this.dataSpec);
            int v0_1 = 0;
            int v1 = 0;
            while(true) {
                int v2 = -1;
                if(v0_1 != v2 && !this.loadCanceled) {
                    this.maybeExpandData(v1);
                    v0_1 = this.dataSource.read(this.data, v1, 16384);
                    if(v0_1 == v2) {
                        continue;
                    }

                    v1 += v0_1;
                    continue;
                }

                break;
            }

            if(!this.loadCanceled) {
                this.consume(this.data, v1);
            }
        }
        catch(Throwable v0) {
            goto label_26;
        }

        Util.closeQuietly(this.dataSource);
        return;
    label_26:
        Util.closeQuietly(this.dataSource);
        throw v0;
    }

    private void maybeExpandData(int arg3) {
        byte[] v3;
        int v1 = 16384;
        if(this.data == null) {
            v3 = new byte[v1];
            goto label_4;
        }
        else if(this.data.length < arg3 + v1) {
            v3 = Arrays.copyOf(this.data, this.data.length + v1);
        label_4:
            this.data = v3;
        }
    }
}

