package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;

public final class ByteArrayDataSource extends BaseDataSource {
    private int bytesRemaining;
    private final byte[] data;
    private boolean opened;
    private int readPosition;
    private Uri uri;

    public ByteArrayDataSource(byte[] arg3) {
        boolean v0 = false;
        super(false);
        Assertions.checkNotNull(arg3);
        if(arg3.length > 0) {
            v0 = true;
        }

        Assertions.checkArgument(v0);
        this.data = arg3;
    }

    public void close() {
        if(this.opened) {
            this.opened = false;
            this.transferEnded();
        }

        this.uri = null;
    }

    public Uri getUri() {
        return this.uri;
    }

    public long open(DataSpec arg6) {
        this.uri = arg6.uri;
        this.transferInitializing(arg6);
        this.readPosition = ((int)arg6.position);
        long v0 = arg6.length == -1 ? (((long)this.data.length)) - arg6.position : arg6.length;
        this.bytesRemaining = ((int)v0);
        if(this.bytesRemaining > 0 && this.readPosition + this.bytesRemaining <= this.data.length) {
            this.opened = true;
            this.transferStarted(arg6);
            return ((long)this.bytesRemaining);
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Unsatisfiable range: [");
        v1.append(this.readPosition);
        v1.append(", ");
        v1.append(arg6.length);
        v1.append("], length: ");
        v1.append(this.data.length);
        throw new IOException(v1.toString());
    }

    public int read(byte[] arg3, int arg4, int arg5) {
        if(arg5 == 0) {
            return 0;
        }

        if(this.bytesRemaining == 0) {
            return -1;
        }

        arg5 = Math.min(arg5, this.bytesRemaining);
        System.arraycopy(this.data, this.readPosition, arg3, arg4, arg5);
        this.readPosition += arg5;
        this.bytesRemaining -= arg5;
        this.bytesTransferred(arg5);
        return arg5;
    }
}

