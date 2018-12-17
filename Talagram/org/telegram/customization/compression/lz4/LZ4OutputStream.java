package org.telegram.customization.compression.lz4;

import java.io.OutputStream;

public class LZ4OutputStream extends OutputStream {
    private static final int ONE_MEGABYTE = 1048576;
    private int bytesRemainingInCompressionInputBuffer;
    private final byte[] compressionInputBuffer;
    private final byte[] compressionOutputBuffer;
    private final LZ4Compressor compressor;
    private int currentCompressionInputBufferPosition;
    private static final LZ4Factory lz4Factory;
    private final OutputStream underlyingOutputStream;

    static {
        LZ4OutputStream.lz4Factory = LZ4Factory.fastestInstance();
    }

    public LZ4OutputStream(OutputStream arg3) {
        this(arg3, 1048576, LZ4OutputStream.lz4Factory.fastCompressor());
    }

    public LZ4OutputStream(OutputStream arg3, int arg4, LZ4Compressor arg5) {
        super();
        this.bytesRemainingInCompressionInputBuffer = 0;
        this.currentCompressionInputBufferPosition = 0;
        this.compressionInputBuffer = new byte[arg4];
        this.compressor = arg5;
        this.underlyingOutputStream = arg3;
        this.bytesRemainingInCompressionInputBuffer = arg4;
        this.currentCompressionInputBufferPosition = 0;
        this.compressionOutputBuffer = new byte[arg5.maxCompressedLength(arg4)];
    }

    public LZ4OutputStream(OutputStream arg2, int arg3) {
        this(arg2, arg3, LZ4OutputStream.lz4Factory.fastCompressor());
    }

    public void close() {
        this.flush();
        this.underlyingOutputStream.close();
    }

    public void flush() {
        if(this.currentCompressionInputBufferPosition > 0) {
            LZ4StreamHelper.writeLength(this.currentCompressionInputBufferPosition, this.underlyingOutputStream);
            int v0 = this.compressor.compress(this.compressionInputBuffer, 0, this.currentCompressionInputBufferPosition, this.compressionOutputBuffer, 0, this.compressionOutputBuffer.length);
            LZ4StreamHelper.writeLength(v0, this.underlyingOutputStream);
            this.underlyingOutputStream.write(this.compressionOutputBuffer, 0, v0);
            this.bytesRemainingInCompressionInputBuffer = this.compressionInputBuffer.length;
            this.currentCompressionInputBufferPosition = 0;
        }
    }

    public void write(int arg3) {
        byte v3 = ((byte)arg3);
        if(this.bytesRemainingInCompressionInputBuffer == 0) {
            this.flush();
        }

        this.compressionInputBuffer[this.currentCompressionInputBufferPosition] = v3;
        --this.bytesRemainingInCompressionInputBuffer;
        ++this.currentCompressionInputBufferPosition;
    }

    public void write(byte[] arg4, int arg5, int arg6) {
        if(arg6 <= this.bytesRemainingInCompressionInputBuffer) {
            System.arraycopy(arg4, arg5, this.compressionInputBuffer, this.currentCompressionInputBufferPosition, arg6);
            this.currentCompressionInputBufferPosition += arg6;
            this.bytesRemainingInCompressionInputBuffer -= arg6;
        }
        else {
            while(arg6 > 0) {
                int v0 = Math.min(this.bytesRemainingInCompressionInputBuffer, arg6);
                System.arraycopy(arg4, arg5, this.compressionInputBuffer, this.currentCompressionInputBufferPosition, v0);
                this.currentCompressionInputBufferPosition += v0;
                this.bytesRemainingInCompressionInputBuffer -= v0;
                this.flush();
                arg6 -= v0;
                arg5 += v0;
            }
        }
    }
}

