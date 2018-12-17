package org.telegram.customization.compression.lz4;

import java.nio.ByteBuffer;
import org.telegram.customization.compression.a.b;

enum LZ4JNI {
    static {
        LZ4JNI.$VALUES = new LZ4JNI[0];
        b.b();
        LZ4JNI.init();
    }

    private LZ4JNI(String arg1, int arg2) {
        super(arg1, arg2);
    }

    static native int LZ4_compressBound(int arg0) {
    }

    static native int LZ4_compressHC(byte[] arg0, ByteBuffer arg1, int arg2, int arg3, byte[] arg4, ByteBuffer arg5, int arg6, int arg7, int arg8) {
    }

    static native int LZ4_compress_limitedOutput(byte[] arg0, ByteBuffer arg1, int arg2, int arg3, byte[] arg4, ByteBuffer arg5, int arg6, int arg7) {
    }

    static native int LZ4_decompress_fast(byte[] arg0, ByteBuffer arg1, int arg2, byte[] arg3, ByteBuffer arg4, int arg5, int arg6) {
    }

    static native int LZ4_decompress_safe(byte[] arg0, ByteBuffer arg1, int arg2, int arg3, byte[] arg4, ByteBuffer arg5, int arg6, int arg7) {
    }

    static native void init() {
    }

    public static LZ4JNI valueOf(String arg1) {
        return Enum.valueOf(LZ4JNI.class, arg1);
    }

    public static LZ4JNI[] values() {
        // Method was not decompiled
    }
}

