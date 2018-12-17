package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import java.io.IOException;
import java.util.Map;

public final class DummyDataSource implements DataSource {
    final class com.google.android.exoplayer2.upstream.DummyDataSource$1 implements Factory {
        com.google.android.exoplayer2.upstream.DummyDataSource$1() {
            super();
        }

        public DataSource createDataSource() {
            return new DummyDataSource(null);
        }
    }

    public static final Factory FACTORY;
    public static final DummyDataSource INSTANCE;

    static {
        DummyDataSource.INSTANCE = new DummyDataSource();
        DummyDataSource.FACTORY = new com.google.android.exoplayer2.upstream.DummyDataSource$1();
    }

    private DummyDataSource() {
        super();
    }

    DummyDataSource(com.google.android.exoplayer2.upstream.DummyDataSource$1 arg1) {
        this();
    }

    public void addTransferListener(TransferListener arg1) {
        DataSource$-CC.$default$addTransferListener(((DataSource)this), arg1);
    }

    public void close() {
    }

    public Map getResponseHeaders() {
        return DataSource$-CC.$default$getResponseHeaders(((DataSource)this));
    }

    public Uri getUri() {
        return null;
    }

    public long open(DataSpec arg2) {
        throw new IOException("Dummy source");
    }

    public int read(byte[] arg1, int arg2, int arg3) {
        throw new UnsupportedOperationException();
    }
}

