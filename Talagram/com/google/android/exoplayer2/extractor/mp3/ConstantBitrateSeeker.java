package com.google.android.exoplayer2.extractor.mp3;

import com.google.android.exoplayer2.extractor.ConstantBitrateSeekMap;
import com.google.android.exoplayer2.extractor.MpegAudioHeader;

final class ConstantBitrateSeeker extends ConstantBitrateSeekMap implements Seeker {
    public ConstantBitrateSeeker(long arg8, long arg10, MpegAudioHeader arg12) {
        super(arg8, arg10, arg12.bitrate, arg12.frameSize);
    }

    public long getTimeUs(long arg1) {
        return this.getTimeUsAtPosition(arg1);
    }
}

