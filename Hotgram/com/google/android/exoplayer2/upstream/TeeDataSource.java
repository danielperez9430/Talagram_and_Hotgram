package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import com.google.android.exoplayer2.util.Assertions;
import java.util.Map;

public final class TeeDataSource implements DataSource {
    private long bytesRemaining;
    private final DataSink dataSink;
    private boolean dataSinkNeedsClosing;
    private final DataSource upstream;

    public TeeDataSource(DataSource arg1, DataSink arg2) {
        super();
        this.upstream = Assertions.checkNotNull(arg1);
        this.dataSink = Assertions.checkNotNull(arg2);
    }

    public void addTransferListener(TransferListener arg2) {
        this.upstream.addTransferListener(arg2);
    }

    public void close() {
        try {
            this.upstream.close();
        }
        catch(Throwable v1) {
            if(this.dataSinkNeedsClosing) {
                this.dataSinkNeedsClosing = false;
                this.dataSink.close();
            }

            throw v1;
        }

        if(this.dataSinkNeedsClosing) {
            this.dataSinkNeedsClosing = false;
            this.dataSink.close();
        }
    }

    public Map getResponseHeaders() {
        return this.upstream.getResponseHeaders();
    }

    public Uri getUri() {
        return this.upstream.getUri();
    }

    public long open(DataSpec arg8) {
        this.bytesRemaining = this.upstream.open(arg8);
        long v2 = 0;
        if(this.bytesRemaining == v2) {
            return v2;
        }

        long v4 = -1;
        if(arg8.length == v4 && this.bytesRemaining != v4) {
            arg8 = arg8.subrange(v2, this.bytesRemaining);
        }

        this.dataSinkNeedsClosing = true;
        this.dataSink.open(arg8);
        return this.bytesRemaining;
    }

    public int read(byte[] arg6, int arg7, int arg8) {
        if(this.bytesRemaining == 0) {
            return -1;
        }

        arg8 = this.upstream.read(arg6, arg7, arg8);
        if(arg8 > 0) {
            this.dataSink.write(arg6, arg7, arg8);
            if(this.bytesRemaining != -1) {
                this.bytesRemaining -= ((long)arg8);
            }
        }

        return arg8;
    }
}

