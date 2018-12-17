package com.google.android.exoplayer2.util;

public final class FlacStreamInfo {
    public final int bitsPerSample;
    public final int channels;
    public final int maxBlockSize;
    public final int maxFrameSize;
    public final int minBlockSize;
    public final int minFrameSize;
    public final int sampleRate;
    public final long totalSamples;

    public FlacStreamInfo(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, long arg8) {
        super();
        this.minBlockSize = arg1;
        this.maxBlockSize = arg2;
        this.minFrameSize = arg3;
        this.maxFrameSize = arg4;
        this.sampleRate = arg5;
        this.channels = arg6;
        this.bitsPerSample = arg7;
        this.totalSamples = arg8;
    }

    public FlacStreamInfo(byte[] arg5, int arg6) {
        super();
        ParsableBitArray v0 = new ParsableBitArray(arg5);
        v0.setPosition(arg6 * 8);
        this.minBlockSize = v0.readBits(16);
        this.maxBlockSize = v0.readBits(16);
        this.minFrameSize = v0.readBits(24);
        this.maxFrameSize = v0.readBits(24);
        this.sampleRate = v0.readBits(20);
        this.channels = v0.readBits(3) + 1;
        this.bitsPerSample = v0.readBits(5) + 1;
        this.totalSamples = ((((long)v0.readBits(4))) & 15) << 32 | (((long)v0.readBits(32))) & 4294967295L;
    }

    public int bitRate() {
        return this.bitsPerSample * this.sampleRate;
    }

    public long durationUs() {
        return this.totalSamples * 1000000 / (((long)this.sampleRate));
    }

    public long getApproxBytesPerFrame() {
        long v2;
        long v0;
        if(this.maxFrameSize > 0) {
            v0 = ((((long)this.maxFrameSize)) + (((long)this.minFrameSize))) / 2;
            v2 = 1;
        }
        else {
            v0 = this.minBlockSize != this.maxBlockSize || this.minBlockSize <= 0 ? 4096 : ((long)this.minBlockSize);
            v0 = v0 * (((long)this.channels)) * (((long)this.bitsPerSample)) / 8;
            v2 = 64;
        }

        return v2;
    }

    public long getSampleIndex(long arg9) {
        return Util.constrainValue(arg9 * (((long)this.sampleRate)) / 1000000, 0, this.totalSamples - 1);
    }

    public int maxDecodedFrameSize() {
        return this.maxBlockSize * this.channels * (this.bitsPerSample / 8);
    }
}

