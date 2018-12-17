package com.google.android.exoplayer2.upstream;

import com.google.android.exoplayer2.util.Assertions;
import java.io.InputStream;

public final class DataSourceInputStream extends InputStream {
    private boolean closed;
    private final DataSource dataSource;
    private final DataSpec dataSpec;
    private boolean opened;
    private final byte[] singleByteArray;
    private long totalBytesRead;

    public DataSourceInputStream(DataSource arg2, DataSpec arg3) {
        super();
        this.opened = false;
        this.closed = false;
        this.dataSource = arg2;
        this.dataSpec = arg3;
        this.singleByteArray = new byte[1];
    }

    public long bytesRead() {
        return this.totalBytesRead;
    }

    private void checkOpened() {
        if(!this.opened) {
            this.dataSource.open(this.dataSpec);
            this.opened = true;
        }
    }

    public void close() {
        if(!this.closed) {
            this.dataSource.close();
            this.closed = true;
        }
    }

    public void open() {
        this.checkOpened();
    }

    public int read() {
        int v1 = -1;
        if(this.read(this.singleByteArray) == v1) {
        }
        else {
            v1 = this.singleByteArray[0] & 255;
        }

        return v1;
    }

    public int read(byte[] arg3) {
        return this.read(arg3, 0, arg3.length);
    }

    public int read(byte[] arg3, int arg4, int arg5) {
        Assertions.checkState(this.closed ^ 1);
        this.checkOpened();
        int v3 = this.dataSource.read(arg3, arg4, arg5);
        arg4 = -1;
        if(v3 == arg4) {
            return arg4;
        }

        this.totalBytesRead += ((long)v3);
        return v3;
    }
}

