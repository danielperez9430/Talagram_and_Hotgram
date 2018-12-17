package com.google.android.exoplayer2.extractor.wav;

import com.google.android.exoplayer2.extractor.SeekMap$SeekPoints;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.util.Util;

final class WavHeader implements SeekMap {
    private final int averageBytesPerSecond;
    private final int bitsPerSample;
    private final int blockAlignment;
    private long dataSize;
    private long dataStartPosition;
    private final int encoding;
    private final int numChannels;
    private final int sampleRateHz;

    public WavHeader(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
        super();
        this.numChannels = arg1;
        this.sampleRateHz = arg2;
        this.averageBytesPerSecond = arg3;
        this.blockAlignment = arg4;
        this.bitsPerSample = arg5;
        this.encoding = arg6;
    }

    public int getBitrate() {
        return this.sampleRateHz * this.bitsPerSample * this.numChannels;
    }

    public int getBytesPerFrame() {
        return this.blockAlignment;
    }

    public long getDurationUs() {
        return this.dataSize / (((long)this.blockAlignment)) * 1000000 / (((long)this.sampleRateHz));
    }

    public int getEncoding() {
        return this.encoding;
    }

    public int getNumChannels() {
        return this.numChannels;
    }

    public int getSampleRateHz() {
        return this.sampleRateHz;
    }

    public SeekPoints getSeekPoints(long arg11) {
        long v0 = Util.constrainValue((((long)this.averageBytesPerSecond)) * arg11 / 1000000 / (((long)this.blockAlignment)) * (((long)this.blockAlignment)), 0, this.dataSize - (((long)this.blockAlignment)));
        long v2 = this.dataStartPosition + v0;
        long v4 = this.getTimeUs(v2);
        SeekPoint v6 = new SeekPoint(v4, v2);
        if(v4 < arg11) {
            if(v0 == this.dataSize - (((long)this.blockAlignment))) {
            }
            else {
                v2 += ((long)this.blockAlignment);
                return new SeekPoints(v6, new SeekPoint(this.getTimeUs(v2), v2));
            }
        }

        return new SeekPoints(v6);
    }

    public long getTimeUs(long arg3) {
        return Math.max(0, arg3 - this.dataStartPosition) * 1000000 / (((long)this.averageBytesPerSecond));
    }

    public boolean hasDataBounds() {
        long v2 = 0;
        boolean v0 = this.dataStartPosition == v2 || this.dataSize == v2 ? false : true;
        return v0;
    }

    public boolean isSeekable() {
        return 1;
    }

    public void setDataBounds(long arg1, long arg3) {
        this.dataStartPosition = arg1;
        this.dataSize = arg3;
    }
}

