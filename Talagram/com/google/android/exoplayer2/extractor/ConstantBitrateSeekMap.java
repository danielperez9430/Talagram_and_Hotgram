package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.util.Util;

public class ConstantBitrateSeekMap implements SeekMap {
    private final int bitrate;
    private final long dataSize;
    private final long durationUs;
    private final long firstFrameBytePosition;
    private final int frameSize;
    private final long inputLength;

    public ConstantBitrateSeekMap(long arg3, long arg5, int arg7, int arg8) {
        super();
        this.inputLength = arg3;
        this.firstFrameBytePosition = arg5;
        this.frameSize = 1;
        this.bitrate = arg7;
        long v0 = -1;
        if(arg3 == v0) {
            this.dataSize = v0;
            arg3 = -9223372036854775807L;
        }
        else {
            this.dataSize = arg3 - arg5;
            arg3 = ConstantBitrateSeekMap.getTimeUsAtPosition(arg3, arg5, arg7);
        }

        this.durationUs = arg3;
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    private long getFramePositionForTimeUs(long arg9) {
        return this.firstFrameBytePosition + Util.constrainValue(arg9 * (((long)this.bitrate)) / 8000000 / (((long)this.frameSize)) * (((long)this.frameSize)), 0, this.dataSize - (((long)this.frameSize)));
    }

    public SeekPoints getSeekPoints(long arg7) {
        if(this.dataSize == -1) {
            return new SeekPoints(new SeekPoint(0, this.firstFrameBytePosition));
        }

        long v0 = this.getFramePositionForTimeUs(arg7);
        long v2 = this.getTimeUsAtPosition(v0);
        SeekPoint v4 = new SeekPoint(v2, v0);
        if(v2 < arg7) {
            if((((long)this.frameSize)) + v0 >= this.inputLength) {
            }
            else {
                v0 += ((long)this.frameSize);
                return new SeekPoints(v4, new SeekPoint(this.getTimeUsAtPosition(v0), v0));
            }
        }

        return new SeekPoints(v4);
    }

    private static long getTimeUsAtPosition(long arg0, long arg2, int arg4) {
        return Math.max(0, arg0 - arg2) * 8 * 1000000 / (((long)arg4));
    }

    public long getTimeUsAtPosition(long arg4) {
        return ConstantBitrateSeekMap.getTimeUsAtPosition(arg4, this.firstFrameBytePosition, this.bitrate);
    }

    public boolean isSeekable() {
        boolean v0 = this.dataSize != -1 ? true : false;
        return v0;
    }
}

