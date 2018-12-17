package com.googlecode.mp4parser;

import java.io.Closeable;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

public interface DataSource extends Closeable {
    void close();

    ByteBuffer map(long arg1, long arg2);

    long position();

    void position(long arg1);

    int read(ByteBuffer arg1);

    long size();

    long transferTo(long arg1, long arg2, WritableByteChannel arg3);
}

