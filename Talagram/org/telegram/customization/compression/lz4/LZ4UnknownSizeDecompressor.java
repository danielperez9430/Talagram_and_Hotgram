package org.telegram.customization.compression.lz4;

@Deprecated public interface LZ4UnknownSizeDecompressor {
    int decompress(byte[] arg1, int arg2, int arg3, byte[] arg4, int arg5);

    int decompress(byte[] arg1, int arg2, int arg3, byte[] arg4, int arg5, int arg6);
}

