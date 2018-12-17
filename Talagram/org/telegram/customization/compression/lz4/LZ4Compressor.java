package org.telegram.customization.compression.lz4;

import java.nio.ByteBuffer;
import java.util.Arrays;

public abstract class LZ4Compressor {
    public LZ4Compressor() {
        super();
    }

    public final byte[] compress(byte[] arg3) {
        return this.compress(arg3, 0, arg3.length);
    }

    public abstract int compress(ByteBuffer arg1, int arg2, int arg3, ByteBuffer arg4, int arg5, int arg6);

    public final int compress(byte[] arg9, int arg10, int arg11, byte[] arg12, int arg13) {
        return this.compress(arg9, arg10, arg11, arg12, arg13, arg12.length - arg13);
    }

    public abstract int compress(byte[] arg1, int arg2, int arg3, byte[] arg4, int arg5, int arg6);

    public final int compress(byte[] arg7, byte[] arg8) {
        return this.compress(arg7, 0, arg7.length, arg8, 0);
    }

    public final void compress(ByteBuffer arg8, ByteBuffer arg9) {
        int v0 = this.compress(arg8, arg8.position(), arg8.remaining(), arg9, arg9.position(), arg9.remaining());
        arg8.position(arg8.limit());
        arg9.position(arg9.position() + v0);
    }

    public final byte[] compress(byte[] arg8, int arg9, int arg10) {
        byte[] v0 = new byte[this.maxCompressedLength(arg10)];
        return Arrays.copyOf(v0, this.compress(arg8, arg9, arg10, v0, 0));
    }

    public final int maxCompressedLength(int arg1) {
        return LZ4Utils.maxCompressedLength(arg1);
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }
}

