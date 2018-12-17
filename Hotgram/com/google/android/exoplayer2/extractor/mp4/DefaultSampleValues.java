package com.google.android.exoplayer2.extractor.mp4;

final class DefaultSampleValues {
    public final int duration;
    public final int flags;
    public final int sampleDescriptionIndex;
    public final int size;

    public DefaultSampleValues(int arg1, int arg2, int arg3, int arg4) {
        super();
        this.sampleDescriptionIndex = arg1;
        this.duration = arg2;
        this.size = arg3;
        this.flags = arg4;
    }
}

