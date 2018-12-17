package org.telegram.customization.compression.lz4;

import java.io.InputStream;

public class LZ4InputStream extends InputStream {
    private byte[] compressedBuffer;
    private byte[] decompressedBuffer;
    private int decompressedBufferLength;
    private int decompressedBufferPosition;
    private final LZ4Decompressor decompressor;
    private static LZ4Factory factory;
    private final InputStream inputStream;

    static {
        LZ4InputStream.factory = LZ4Factory.fastestInstance();
    }

    public LZ4InputStream(InputStream arg2) {
        this(arg2, 1048576);
    }

    public LZ4InputStream(InputStream arg2, int arg3) {
        super();
        this.decompressedBufferPosition = 0;
        this.decompressedBufferLength = 0;
        this.decompressor = LZ4InputStream.factory.decompressor();
        this.inputStream = arg2;
        this.compressedBuffer = new byte[arg3];
        this.decompressedBuffer = new byte[arg3];
    }

    private boolean blockHeadersIndicateNoMoreData(int arg1, int arg2) {
        boolean v1 = arg1 < 0 || arg2 < 0 ? true : false;
        return v1;
    }

    public void close() {
        this.inputStream.close();
    }

    private void ensureBufferCapacity(int arg2, int arg3) {
        if(arg2 > this.compressedBuffer.length) {
            this.compressedBuffer = new byte[arg2];
        }

        if(arg3 > this.decompressedBuffer.length) {
            this.decompressedBuffer = new byte[arg3];
        }
    }

    private boolean ensureBytesAvailableInDecompressedBuffer() {
        do {
            if(this.decompressedBufferPosition < this.decompressedBufferLength) {
                return 1;
            }
        }
        while(this.fillBuffer());

        return 0;
    }

    private boolean fillBuffer() {
        this.decompressedBufferLength = LZ4StreamHelper.readLength(this.inputStream);
        int v0 = LZ4StreamHelper.readLength(this.inputStream);
        if(this.blockHeadersIndicateNoMoreData(v0, this.decompressedBufferLength)) {
            return 0;
        }

        this.ensureBufferCapacity(v0, this.decompressedBufferLength);
        if(this.fillCompressedBuffer(v0)) {
            this.decompressor.decompress(this.compressedBuffer, 0, this.decompressedBuffer, 0, this.decompressedBufferLength);
            this.decompressedBufferPosition = 0;
            return 1;
        }

        return 0;
    }

    private boolean fillCompressedBuffer(int arg6) {
        int v1;
        for(v1 = 0; v1 < arg6; v1 += v2) {
            int v2 = this.inputStream.read(this.compressedBuffer, v1, arg6 - v1);
            if(v2 < 0) {
                return 0;
            }
        }

        return 1;
    }

    public boolean hasBytesAvailableInDecompressedBuffer(int arg2) {
        boolean v2 = this.decompressedBufferPosition + arg2 <= this.decompressedBufferLength ? true : false;
        return v2;
    }

    public int read() {
        if(this.ensureBytesAvailableInDecompressedBuffer()) {
            byte[] v0 = this.decompressedBuffer;
            int v1 = this.decompressedBufferPosition;
            this.decompressedBufferPosition = v1 + 1;
            return v0[v1] & 255;
        }

        return -1;
    }

    public int read(byte[] arg5, int arg6, int arg7) {
        if(!this.ensureBytesAvailableInDecompressedBuffer()) {
            return -1;
        }

        int v0;
        for(v0 = arg7 - arg6; v0 > 0; v0 -= v1) {
            if(!this.ensureBytesAvailableInDecompressedBuffer()) {
                break;
            }

            int v1 = this.decompressedBufferLength - this.decompressedBufferPosition;
            if(v0 > v1) {
            }
            else {
                v1 = v0;
            }

            System.arraycopy(this.decompressedBuffer, this.decompressedBufferPosition, arg5, arg6, v1);
            this.decompressedBufferPosition += v1;
            arg6 += v1;
        }

        return arg7 - v0;
    }

    public long skip(long arg7) {
        long v0 = arg7;
        while(v0 > 0) {
            if(!this.ensureBytesAvailableInDecompressedBuffer()) {
                break;
            }

            long v2 = ((long)(this.decompressedBufferLength - this.decompressedBufferPosition));
            if(v0 > v2) {
            }
            else {
                v2 = v0;
            }

            v0 -= v2;
            this.decompressedBufferPosition = ((int)((((long)this.decompressedBufferPosition)) + v2));
        }

        return arg7 - v0;
    }
}

