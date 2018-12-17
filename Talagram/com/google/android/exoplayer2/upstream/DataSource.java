package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import java.util.Map;

public interface DataSource {
    public interface Factory {
        DataSource createDataSource();
    }

    void addTransferListener(TransferListener arg1);

    void close();

    Map getResponseHeaders();

    Uri getUri();

    long open(DataSpec arg1);

    int read(byte[] arg1, int arg2, int arg3);
}

