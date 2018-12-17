package com.google.android.exoplayer2.upstream;

public interface DataSink {
    public interface Factory {
        DataSink createDataSink();
    }

    void close();

    void open(DataSpec arg1);

    void write(byte[] arg1, int arg2, int arg3);
}

