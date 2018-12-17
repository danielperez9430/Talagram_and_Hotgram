package com.google.android.exoplayer2.ext.flac;

import android.support.annotation.Keep;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.FlacStreamInfo;
import java.io.IOException;
import java.nio.ByteBuffer;

@Keep final class FlacDecoderJni {
    public final class FlacFrameDecodeException extends Exception {
        public final int errorCode;

        public FlacFrameDecodeException(String arg1, int arg2) {
            super(arg1);
            this.errorCode = arg2;
        }
    }

    private static final int TEMP_BUFFER_SIZE = 8192;
    private ByteBuffer byteBufferData;
    private boolean endOfExtractorInput;
    private ExtractorInput extractorInput;
    private final long nativeDecoderContext;
    private byte[] tempBuffer;

    public FlacDecoderJni() {
        super();
        this.nativeDecoderContext = this.flacInit();
        if(this.nativeDecoderContext != 0) {
            return;
        }

        throw new FlacDecoderException("Failed to initialize decoder");
    }

    public FlacStreamInfo decodeMetadata() {
        return this.flacDecodeMetadata(this.nativeDecoderContext);
    }

    public void decodeSample(ByteBuffer arg4) {
        arg4.clear();
        int v0 = arg4.isDirect() ? this.flacDecodeToBuffer(this.nativeDecoderContext, arg4) : this.flacDecodeToArray(this.nativeDecoderContext, arg4.array());
        if(v0 < 0) {
            if(this.isDecoderAtEndOfInput()) {
                v0 = 0;
            }
            else {
                throw new FlacFrameDecodeException("Cannot decode FLAC frame", v0);
            }
        }

        arg4.limit(v0);
    }

    public void decodeSampleWithBacktrackPosition(ByteBuffer arg4, long arg5) {
        try {
            this.decodeSample(arg4);
            return;
        }
        catch(IOException v4) {
            if(arg5 >= 0) {
                this.reset(arg5);
                if(this.extractorInput != null) {
                    this.extractorInput.setRetryPosition(arg5, ((Throwable)v4));
                }
            }

            throw v4;
        }
    }

    private native FlacStreamInfo flacDecodeMetadata(long arg1) {
    }

    private native int flacDecodeToArray(long arg1, byte[] arg2) {
    }

    private native int flacDecodeToBuffer(long arg1, ByteBuffer arg2) {
    }

    private native void flacFlush(long arg1) {
    }

    private native long flacGetDecodePosition(long arg1) {
    }

    private native long flacGetLastFrameFirstSampleIndex(long arg1) {
    }

    private native long flacGetLastFrameTimestamp(long arg1) {
    }

    private native long flacGetNextFrameFirstSampleIndex(long arg1) {
    }

    private native long flacGetSeekPosition(long arg1, long arg2) {
    }

    private native String flacGetStateString(long arg1) {
    }

    private native long flacInit() {
    }

    private native boolean flacIsDecoderAtEndOfStream(long arg1) {
    }

    private native void flacRelease(long arg1) {
    }

    private native void flacReset(long arg1, long arg2) {
    }

    public void flush() {
        this.flacFlush(this.nativeDecoderContext);
    }

    public long getDecodePosition() {
        return this.flacGetDecodePosition(this.nativeDecoderContext);
    }

    public long getLastFrameFirstSampleIndex() {
        return this.flacGetLastFrameFirstSampleIndex(this.nativeDecoderContext);
    }

    public long getLastFrameTimestamp() {
        return this.flacGetLastFrameTimestamp(this.nativeDecoderContext);
    }

    public long getNextFrameFirstSampleIndex() {
        return this.flacGetNextFrameFirstSampleIndex(this.nativeDecoderContext);
    }

    public long getSeekPosition(long arg3) {
        return this.flacGetSeekPosition(this.nativeDecoderContext, arg3);
    }

    public String getStateString() {
        return this.flacGetStateString(this.nativeDecoderContext);
    }

    public boolean isDecoderAtEndOfInput() {
        return this.flacIsDecoderAtEndOfStream(this.nativeDecoderContext);
    }

    public boolean isEndOfData() {
        boolean v1 = true;
        if(this.byteBufferData != null) {
            if(this.byteBufferData.remaining() == 0) {
            }
            else {
                v1 = false;
            }

            return v1;
        }

        if(this.extractorInput != null) {
            return this.endOfExtractorInput;
        }

        return 1;
    }

    @Keep public int read(ByteBuffer arg5) {
        int v0 = arg5.remaining();
        if(this.byteBufferData != null) {
            v0 = Math.min(v0, this.byteBufferData.remaining());
            int v1 = this.byteBufferData.limit();
            this.byteBufferData.limit(this.byteBufferData.position() + v0);
            arg5.put(this.byteBufferData);
            this.byteBufferData.limit(v1);
        }
        else if(this.extractorInput != null) {
            v0 = Math.min(v0, 8192);
            int v2 = this.readFromExtractorInput(0, v0);
            if(v2 < 4) {
                v2 += this.readFromExtractorInput(v2, v0 - v2);
            }

            v0 = v2;
            arg5.put(this.tempBuffer, 0, v0);
        }
        else {
            return -1;
        }

        return v0;
    }

    private int readFromExtractorInput(int arg3, int arg4) {
        arg3 = this.extractorInput.read(this.tempBuffer, arg3, arg4);
        if(arg3 == -1) {
            this.endOfExtractorInput = true;
            arg3 = 0;
        }

        return arg3;
    }

    public void release() {
        this.flacRelease(this.nativeDecoderContext);
    }

    public void reset(long arg3) {
        this.flacReset(this.nativeDecoderContext, arg3);
    }

    public void setData(ExtractorInput arg2) {
        this.byteBufferData = null;
        this.extractorInput = arg2;
        if(this.tempBuffer == null) {
            this.tempBuffer = new byte[8192];
        }

        this.endOfExtractorInput = false;
    }

    public void setData(ByteBuffer arg1) {
        this.byteBufferData = arg1;
        this.extractorInput = null;
        this.tempBuffer = null;
    }
}

