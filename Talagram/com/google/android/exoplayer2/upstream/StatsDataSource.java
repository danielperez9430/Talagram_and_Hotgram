package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import com.google.android.exoplayer2.util.Assertions;
import java.util.Collections;
import java.util.Map;

public final class StatsDataSource implements DataSource {
    private long bytesRead;
    private final DataSource dataSource;
    private Uri lastOpenedUri;
    private Map lastResponseHeaders;

    public StatsDataSource(DataSource arg1) {
        super();
        this.dataSource = Assertions.checkNotNull(arg1);
        this.lastOpenedUri = Uri.EMPTY;
        this.lastResponseHeaders = Collections.emptyMap();
    }

    public void addTransferListener(TransferListener arg2) {
        this.dataSource.addTransferListener(arg2);
    }

    public void close() {
        this.dataSource.close();
    }

    public long getBytesRead() {
        return this.bytesRead;
    }

    public Uri getLastOpenedUri() {
        return this.lastOpenedUri;
    }

    public Map getLastResponseHeaders() {
        return this.lastResponseHeaders;
    }

    public Map getResponseHeaders() {
        return this.dataSource.getResponseHeaders();
    }

    public Uri getUri() {
        return this.dataSource.getUri();
    }

    public long open(DataSpec arg3) {
        this.lastOpenedUri = arg3.uri;
        this.lastResponseHeaders = Collections.emptyMap();
        long v0 = this.dataSource.open(arg3);
        this.lastOpenedUri = Assertions.checkNotNull(this.getUri());
        this.lastResponseHeaders = this.getResponseHeaders();
        return v0;
    }

    public int read(byte[] arg3, int arg4, int arg5) {
        int v3 = this.dataSource.read(arg3, arg4, arg5);
        if(v3 != -1) {
            this.bytesRead += ((long)v3);
        }

        return v3;
    }

    public void resetBytesRead() {
        this.bytesRead = 0;
    }
}

