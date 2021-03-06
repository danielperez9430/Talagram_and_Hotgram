package com.googlecode.mp4parser.authoring;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

public interface Sample {
    ByteBuffer asByteBuffer();

    long getSize();

    void writeTo(WritableByteChannel arg1);
}

