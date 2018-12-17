package com.google.android.exoplayer2.decoder;

public interface Decoder {
    Object dequeueInputBuffer();

    Object dequeueOutputBuffer();

    void flush();

    String getName();

    void queueInputBuffer(Object arg1);

    void release();
}

