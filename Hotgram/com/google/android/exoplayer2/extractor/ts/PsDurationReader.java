package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;

final class PsDurationReader {
    private static final int DURATION_READ_BYTES = 20000;
    private long durationUs;
    private long firstScrValue;
    private boolean isDurationRead;
    private boolean isFirstScrValueRead;
    private boolean isLastScrValueRead;
    private long lastScrValue;
    private final ParsableByteArray packetBuffer;
    private final TimestampAdjuster scrTimestampAdjuster;

    PsDurationReader() {
        super();
        this.scrTimestampAdjuster = new TimestampAdjuster(0);
        this.firstScrValue = -9223372036854775807L;
        this.lastScrValue = -9223372036854775807L;
        this.durationUs = -9223372036854775807L;
        this.packetBuffer = new ParsableByteArray(20000);
    }

    private boolean checkMarkerBits(byte[] arg4) {
        boolean v0 = false;
        if((arg4[0] & 196) != 68) {
            return 0;
        }

        int v2 = 4;
        if((arg4[2] & v2) != v2) {
            return 0;
        }

        if((arg4[v2] & v2) != v2) {
            return 0;
        }

        if((arg4[5] & 1) != 1) {
            return 0;
        }

        if((arg4[8] & 3) == 3) {
            v0 = true;
        }

        return v0;
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

    private int peakIntAtPosition(byte[] arg3, int arg4) {
        return arg3[arg4 + 3] & 255 | ((arg3[arg4] & 255) << 24 | (arg3[arg4 + 1] & 255) << 16 | (arg3[arg4 + 2] & 255) << 8);
    }

    public int readDuration(ExtractorInput arg6, PositionHolder arg7) {
        if(!this.isLastScrValueRead) {
            return this.readLastScrValue(arg6, arg7);
        }

        long v2 = -9223372036854775807L;
        if(this.lastScrValue == v2) {
            return this.finishReadDuration(arg6);
        }

        if(!this.isFirstScrValueRead) {
            return this.readFirstScrValue(arg6, arg7);
        }

        if(this.firstScrValue == v2) {
            return this.finishReadDuration(arg6);
        }

        this.durationUs = this.scrTimestampAdjuster.adjustTsTimestamp(this.lastScrValue) - this.scrTimestampAdjuster.adjustTsTimestamp(this.firstScrValue);
        return this.finishReadDuration(arg6);
    }

    private int readFirstScrValue(ExtractorInput arg6, PositionHolder arg7) {
        // Method was not decompiled
    }

    private long readFirstScrValueFromBuffer(ParsableByteArray arg8) {
        long v3;
        int v0 = arg8.getPosition();
        int v1 = arg8.limit();
        while(true) {
            v3 = -9223372036854775807L;
            if(v0 >= v1 - 3) {
                return v3;
            }

            if(this.peakIntAtPosition(arg8.data, v0) == 442) {
                long v5 = this.readScrValueFromPack(arg8, v0 + 4);
                if(v5 != v3) {
                    return v5;
                }
            }

            ++v0;
        }

        return v3;
    }

    private int readLastScrValue(ExtractorInput arg7, PositionHolder arg8) {
        // Method was not decompiled
    }

    private long readLastScrValueFromBuffer(ParsableByteArray arg8) {
        long v2;
        int v0 = arg8.getPosition();
        int v1;
        for(v1 = arg8.limit() - 4; true; --v1) {
            v2 = -9223372036854775807L;
            if(v1 < v0) {
                return v2;
            }

            if(this.peakIntAtPosition(arg8.data, v1) == 442) {
                long v4 = this.readScrValueFromPack(arg8, v1 + 4);
                if(v4 != v2) {
                    return v4;
                }
            }
        }

        return v2;
    }

    private long readScrValueFromPack(ParsableByteArray arg5, int arg6) {
        arg5.setPosition(arg6);
        long v0 = -9223372036854775807L;
        int v2 = 9;
        if(arg5.bytesLeft() < v2) {
            return v0;
        }

        byte[] v6 = new byte[v2];
        arg5.readBytes(v6, 0, v6.length);
        if(!this.checkMarkerBits(v6)) {
            return v0;
        }

        return PsDurationReader.readScrValueFromPackHeader(v6);
    }

    private static long readScrValueFromPackHeader(byte[] arg13) {
        return ((((long)arg13[0])) & 56) >> 3 << 30 | ((((long)arg13[0])) & 3) << 28 | ((((long)arg13[1])) & 255) << 20 | ((((long)arg13[2])) & 248) >> 3 << 15 | ((((long)arg13[2])) & 3) << 13 | ((((long)arg13[3])) & 255) << 5 | ((((long)arg13[4])) & 248) >> 3;
    }
}

