package com.googlecode.mp4parser;

import com.googlecode.mp4parser.util.CastUtils;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

public class MemoryDataSourceImpl implements DataSource {
    ByteBuffer data;

    public MemoryDataSourceImpl(ByteBuffer arg1) {
        super();
        this.data = arg1;
    }

    public MemoryDataSourceImpl(byte[] arg1) {
        super();
        this.data = ByteBuffer.wrap(arg1);
    }

    public void close() {
    }

    public ByteBuffer map(long arg3, long arg5) {
        int v0 = this.data.position();
        this.data.position(CastUtils.l2i(arg3));
        ByteBuffer v3 = this.data.slice();
        v3.limit(CastUtils.l2i(arg5));
        this.data.position(v0);
        return v3;
    }

    public long position() {
        return ((long)this.data.position());
    }

    public void position(long arg2) {
        this.data.position(CastUtils.l2i(arg2));
    }

    public int read(ByteBuffer arg4) {
        if(this.data.remaining() == 0 && arg4.remaining() != 0) {
            return -1;
        }

        int v0 = Math.min(arg4.remaining(), this.data.remaining());
        if(arg4.hasArray()) {
            arg4.put(this.data.array(), this.data.position(), v0);
            this.data.position(this.data.position() + v0);
        }
        else {
            byte[] v1 = new byte[v0];
            this.data.get(v1);
            arg4.put(v1);
        }

        return v0;
    }

    public long size() {
        return ((long)this.data.capacity());
    }

    public long transferTo(long arg2, long arg4, WritableByteChannel arg6) {
        return ((long)arg6.write(this.data.position(CastUtils.l2i(arg2)).slice().limit(CastUtils.l2i(arg4))));
    }
}

