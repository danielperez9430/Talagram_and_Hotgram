package com.google.android.exoplayer2.decoder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;

public class DecoderInputBuffer extends Buffer {
    @Retention(value=RetentionPolicy.SOURCE) @public interface BufferReplacementMode {
    }

    public static final int BUFFER_REPLACEMENT_MODE_DIRECT = 2;
    public static final int BUFFER_REPLACEMENT_MODE_DISABLED = 0;
    public static final int BUFFER_REPLACEMENT_MODE_NORMAL = 1;
    private final int bufferReplacementMode;
    public final CryptoInfo cryptoInfo;
    public ByteBuffer data;
    public long timeUs;

    public DecoderInputBuffer(int arg2) {
        super();
        this.cryptoInfo = new CryptoInfo();
        this.bufferReplacementMode = arg2;
    }

    public void clear() {
        super.clear();
        if(this.data != null) {
            this.data.clear();
        }
    }

    private ByteBuffer createReplacementByteBuffer(int arg5) {
        if(this.bufferReplacementMode == 1) {
            return ByteBuffer.allocate(arg5);
        }

        if(this.bufferReplacementMode == 2) {
            return ByteBuffer.allocateDirect(arg5);
        }

        int v0 = this.data == null ? 0 : this.data.capacity();
        StringBuilder v2 = new StringBuilder();
        v2.append("Buffer too small (");
        v2.append(v0);
        v2.append(" < ");
        v2.append(arg5);
        v2.append(")");
        throw new IllegalStateException(v2.toString());
    }

    public void ensureSpaceForWrite(int arg4) {
        if(this.data == null) {
            this.data = this.createReplacementByteBuffer(arg4);
            return;
        }

        int v0 = this.data.capacity();
        int v1 = this.data.position();
        arg4 += v1;
        if(v0 >= arg4) {
            return;
        }

        ByteBuffer v4 = this.createReplacementByteBuffer(arg4);
        if(v1 > 0) {
            this.data.position(0);
            this.data.limit(v1);
            v4.put(this.data);
        }

        this.data = v4;
    }

    public final void flip() {
        this.data.flip();
    }

    public final boolean isEncrypted() {
        return this.getFlag(1073741824);
    }

    public final boolean isFlagsOnly() {
        boolean v0 = this.data != null || this.bufferReplacementMode != 0 ? false : true;
        return v0;
    }

    public static DecoderInputBuffer newFlagsOnlyInstance() {
        return new DecoderInputBuffer(0);
    }
}

