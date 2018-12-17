package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

final class TrimmingAudioProcessor implements AudioProcessor {
    private ByteBuffer buffer;
    private int channelCount;
    private byte[] endBuffer;
    private int endBufferSize;
    private boolean inputEnded;
    private boolean isActive;
    private ByteBuffer outputBuffer;
    private int pendingTrimStartBytes;
    private int sampleRateHz;
    private int trimEndFrames;
    private int trimStartFrames;

    public TrimmingAudioProcessor() {
        super();
        this.buffer = TrimmingAudioProcessor.EMPTY_BUFFER;
        this.outputBuffer = TrimmingAudioProcessor.EMPTY_BUFFER;
        this.channelCount = -1;
        this.sampleRateHz = -1;
        this.endBuffer = new byte[0];
    }

    public boolean configure(int arg2, int arg3, int arg4) {
        if(arg4 == 2) {
            this.channelCount = arg3;
            this.sampleRateHz = arg2;
            this.endBuffer = new byte[this.trimEndFrames * arg3 * 2];
            boolean v2 = false;
            this.endBufferSize = 0;
            this.pendingTrimStartBytes = this.trimStartFrames * arg3 * 2;
            boolean v3 = this.isActive;
            boolean v4 = this.trimStartFrames != 0 || this.trimEndFrames != 0 ? true : false;
            this.isActive = v4;
            if(v3 != this.isActive) {
                v2 = true;
            }

            return v2;
        }

        throw new UnhandledFormatException(arg2, arg3, arg4);
    }

    public void flush() {
        this.outputBuffer = TrimmingAudioProcessor.EMPTY_BUFFER;
        this.inputEnded = false;
        this.pendingTrimStartBytes = 0;
        this.endBufferSize = 0;
    }

    public ByteBuffer getOutput() {
        ByteBuffer v0 = this.outputBuffer;
        this.outputBuffer = TrimmingAudioProcessor.EMPTY_BUFFER;
        return v0;
    }

    public int getOutputChannelCount() {
        return this.channelCount;
    }

    public int getOutputEncoding() {
        return 2;
    }

    public int getOutputSampleRateHz() {
        return this.sampleRateHz;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public boolean isEnded() {
        boolean v0 = !this.inputEnded || this.outputBuffer != TrimmingAudioProcessor.EMPTY_BUFFER ? false : true;
        return v0;
    }

    public void queueEndOfStream() {
        this.inputEnded = true;
    }

    public void queueInput(ByteBuffer arg8) {
        int v0 = arg8.position();
        int v1 = arg8.limit();
        int v2 = v1 - v0;
        int v3 = Math.min(v2, this.pendingTrimStartBytes);
        this.pendingTrimStartBytes -= v3;
        arg8.position(v0 + v3);
        if(this.pendingTrimStartBytes > 0) {
            return;
        }

        v2 -= v3;
        v0 = this.endBufferSize + v2 - this.endBuffer.length;
        if(this.buffer.capacity() < v0) {
            this.buffer = ByteBuffer.allocateDirect(v0).order(ByteOrder.nativeOrder());
        }
        else {
            this.buffer.clear();
        }

        v3 = Util.constrainValue(v0, 0, this.endBufferSize);
        this.buffer.put(this.endBuffer, 0, v3);
        v0 = Util.constrainValue(v0 - v3, 0, v2);
        arg8.limit(arg8.position() + v0);
        this.buffer.put(arg8);
        arg8.limit(v1);
        v2 -= v0;
        this.endBufferSize -= v3;
        System.arraycopy(this.endBuffer, v3, this.endBuffer, 0, this.endBufferSize);
        arg8.get(this.endBuffer, this.endBufferSize, v2);
        this.endBufferSize += v2;
        this.buffer.flip();
        this.outputBuffer = this.buffer;
    }

    public void reset() {
        this.flush();
        this.buffer = TrimmingAudioProcessor.EMPTY_BUFFER;
        this.channelCount = -1;
        this.sampleRateHz = -1;
        this.endBuffer = new byte[0];
    }

    public void setTrimFrameCount(int arg1, int arg2) {
        this.trimStartFrames = arg1;
        this.trimEndFrames = arg2;
    }
}

