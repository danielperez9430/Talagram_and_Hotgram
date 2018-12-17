package com.google.android.exoplayer2.decoder;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class SimpleOutputBuffer extends OutputBuffer {
    public ByteBuffer data;
    private final SimpleDecoder owner;

    public SimpleOutputBuffer(SimpleDecoder arg1) {
        super();
        this.owner = arg1;
    }

    public void clear() {
        super.clear();
        if(this.data != null) {
            this.data.clear();
        }
    }

    public ByteBuffer init(long arg1, int arg3) {
        this.timeUs = arg1;
        if(this.data == null || this.data.capacity() < arg3) {
            this.data = ByteBuffer.allocateDirect(arg3).order(ByteOrder.nativeOrder());
        }

        this.data.position(0);
        this.data.limit(arg3);
        return this.data;
    }

    public void release() {
        this.owner.releaseOutputBuffer(((OutputBuffer)this));
    }
}

