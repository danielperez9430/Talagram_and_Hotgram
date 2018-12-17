package com.googlecode.mp4parser.authoring;

import com.coremedia.iso.boxes.Container;
import com.googlecode.mp4parser.util.CastUtils;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

public class SampleImpl implements Sample {
    private ByteBuffer[] data;
    private final long offset;
    private final Container parent;
    private final long size;

    public SampleImpl(long arg1, long arg3, Container arg5) {
        super();
        this.offset = arg1;
        this.size = arg3;
        this.data = null;
        this.parent = arg5;
    }

    public SampleImpl(long arg1, long arg3, ByteBuffer arg5) {
        super();
        this.offset = arg1;
        this.size = arg3;
        this.data = new ByteBuffer[]{arg5};
        this.parent = null;
    }

    public SampleImpl(ByteBuffer arg3) {
        super();
        this.offset = -1;
        this.size = ((long)arg3.limit());
        this.data = new ByteBuffer[]{arg3};
        this.parent = null;
    }

    public SampleImpl(ByteBuffer[] arg5) {
        super();
        this.offset = -1;
        int v0 = arg5.length;
        int v1 = 0;
        int v2 = 0;
        while(v1 < v0) {
            v2 += arg5[v1].remaining();
            ++v1;
        }

        this.size = ((long)v2);
        this.data = arg5;
        this.parent = null;
    }

    public ByteBuffer asByteBuffer() {
        this.ensureData();
        ByteBuffer v0 = ByteBuffer.wrap(new byte[CastUtils.l2i(this.size)]);
        ByteBuffer[] v1 = this.data;
        int v2 = v1.length;
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            v0.put(v1[v3].duplicate());
        }

        v0.rewind();
        return v0;
    }

    protected void ensureData() {
        if(this.data != null) {
            return;
        }

        if(this.parent != null) {
            try {
                this.data = new ByteBuffer[]{this.parent.getByteBuffer(this.offset, this.size)};
                return;
            }
            catch(IOException v0) {
                StringBuilder v2 = new StringBuilder("couldn\'t read sample ");
                v2.append(this);
                throw new RuntimeException(v2.toString(), ((Throwable)v0));
            }
        }

        StringBuilder v1 = new StringBuilder("Missing parent container, can\'t read sample ");
        v1.append(this);
        throw new RuntimeException(v1.toString());
    }

    public long getSize() {
        return this.size;
    }

    public String toString() {
        return "SampleImpl" + "{offset=" + this.offset + "{size=" + this.size + '}';
    }

    public void writeTo(WritableByteChannel arg5) {
        this.ensureData();
        ByteBuffer[] v0 = this.data;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            arg5.write(v0[v2].duplicate());
        }
    }
}

