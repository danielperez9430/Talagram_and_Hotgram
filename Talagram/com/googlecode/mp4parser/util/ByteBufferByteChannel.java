package com.googlecode.mp4parser.util;

import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

public class ByteBufferByteChannel implements ByteChannel {
    ByteBuffer byteBuffer;

    public ByteBufferByteChannel(ByteBuffer arg1) {
        super();
        this.byteBuffer = arg1;
    }

    public void close() {
    }

    public boolean isOpen() {
        return 1;
    }

    public int read(ByteBuffer arg5) {
        int v0 = arg5.remaining();
        if(this.byteBuffer.remaining() <= 0) {
            return -1;
        }

        arg5.put(this.byteBuffer.duplicate().limit(this.byteBuffer.position() + arg5.remaining()));
        this.byteBuffer.position(this.byteBuffer.position() + v0);
        return v0;
    }

    public int write(ByteBuffer arg3) {
        int v0 = arg3.remaining();
        this.byteBuffer.put(arg3);
        return v0;
    }
}

