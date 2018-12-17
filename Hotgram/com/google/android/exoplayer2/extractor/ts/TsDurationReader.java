package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;

final class TsDurationReader {
    private static final int DURATION_READ_BYTES = 37600;
    private static final int DURATION_READ_PACKETS = 200;
    private long durationUs;
    private long firstPcrValue;
    private boolean isDurationRead;
    private boolean isFirstPcrValueRead;
    private boolean isLastPcrValueRead;
    private long lastPcrValue;
    private final ParsableByteArray packetBuffer;
    private final TimestampAdjuster pcrTimestampAdjuster;

    TsDurationReader() {
        super();
        this.pcrTimestampAdjuster = new TimestampAdjuster(0);
        this.firstPcrValue = -9223372036854775807L;
        this.lastPcrValue = -9223372036854775807L;
        this.durationUs = -9223372036854775807L;
        this.packetBuffer = new ParsableByteArray(37600);
    }

    private int finishReadDuration(ExtractorInput arg2) {
        this.isDurationRead = true;
        arg2.resetPeekPosition();
        return 0;
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public boolean isDurationReadFinished() {
        return this.isDurationRead;
    }

    public int readDuration(ExtractorInput arg6, PositionHolder arg7, int arg8) {
        if(arg8 <= 0) {
            return this.finishReadDuration(arg6);
        }

        if(!this.isLastPcrValueRead) {
            return this.readLastPcrValue(arg6, arg7, arg8);
        }

        long v2 = -9223372036854775807L;
        if(this.lastPcrValue == v2) {
            return this.finishReadDuration(arg6);
        }

        if(!this.isFirstPcrValueRead) {
            return this.readFirstPcrValue(arg6, arg7, arg8);
        }

        if(this.firstPcrValue == v2) {
            return this.finishReadDuration(arg6);
        }

        this.durationUs = this.pcrTimestampAdjuster.adjustTsTimestamp(this.lastPcrValue) - this.pcrTimestampAdjuster.adjustTsTimestamp(this.firstPcrValue);
        return this.finishReadDuration(arg6);
    }

    private int readFirstPcrValue(ExtractorInput arg6, PositionHolder arg7, int arg8) {
        // Method was not decompiled
    }

    private long readFirstPcrValueFromBuffer(ParsableByteArray arg8, int arg9) {
        long v2;
        int v0 = arg8.getPosition();
        int v1 = arg8.limit();
        while(true) {
            v2 = -9223372036854775807L;
            if(v0 >= v1) {
                return v2;
            }

            if(arg8.data[v0] != 71) {
            }
            else {
                long v4 = TsDurationReader.readPcrFromPacket(arg8, v0, arg9);
                if(v4 != v2) {
                    return v4;
                }
            }

            ++v0;
        }

        return v2;
    }

    private int readLastPcrValue(ExtractorInput arg7, PositionHolder arg8, int arg9) {
        // Method was not decompiled
    }

    private long readLastPcrValueFromBuffer(ParsableByteArray arg8, int arg9) {
        long v2;
        int v0 = arg8.getPosition();
        int v1;
        for(v1 = arg8.limit() - 1; true; --v1) {
            v2 = -9223372036854775807L;
            if(v1 < v0) {
                return v2;
            }

            if(arg8.data[v1] != 71) {
            }
            else {
                long v4 = TsDurationReader.readPcrFromPacket(arg8, v1, arg9);
                if(v4 != v2) {
                    return v4;
                }
            }
        }

        return v2;
    }

    private static long readPcrFromPacket(ParsableByteArray arg4, int arg5, int arg6) {
        arg4.setPosition(arg5);
        long v0 = -9223372036854775807L;
        if(arg4.bytesLeft() < 5) {
            return v0;
        }

        arg5 = arg4.readInt();
        if((8388608 & arg5) != 0) {
            return v0;
        }

        if((2096896 & arg5) >> 8 != arg6) {
            return v0;
        }

        arg6 = 1;
        arg5 = (arg5 & 32) != 0 ? 1 : 0;
        if(arg5 == 0) {
            return v0;
        }

        int v3 = 7;
        if(arg4.readUnsignedByte() >= v3 && arg4.bytesLeft() >= v3) {
            if((arg4.readUnsignedByte() & 16) == 16) {
            }
            else {
                arg6 = 0;
            }

            if(arg6 == 0) {
                return v0;
            }

            byte[] v5 = new byte[6];
            arg4.readBytes(v5, 0, v5.length);
            return TsDurationReader.readPcrValueFromPcrBytes(v5);
        }

        return v0;
    }

    private static long readPcrValueFromPcrBytes(byte[] arg8) {
        return ((((long)arg8[0])) & 255) << 25 | ((((long)arg8[1])) & 255) << 17 | ((((long)arg8[2])) & 255) << 9 | ((((long)arg8[3])) & 255) << 1 | (255 & (((long)arg8[4]))) >> 7;
    }
}

