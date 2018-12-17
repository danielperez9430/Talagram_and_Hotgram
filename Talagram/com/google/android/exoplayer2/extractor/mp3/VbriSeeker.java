package com.google.android.exoplayer2.extractor.mp3;

import android.util.Log;
import com.google.android.exoplayer2.extractor.MpegAudioHeader;
import com.google.android.exoplayer2.extractor.SeekMap$SeekPoints;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

final class VbriSeeker implements Seeker {
    private static final String TAG = "VbriSeeker";
    private final long durationUs;
    private final long[] positions;
    private final long[] timesUs;

    private VbriSeeker(long[] arg1, long[] arg2, long arg3) {
        super();
        this.timesUs = arg1;
        this.positions = arg2;
        this.durationUs = arg3;
    }

    public static VbriSeeker create(long arg18, long arg20, MpegAudioHeader arg22, ParsableByteArray arg23) {
        long v16;
        MpegAudioHeader v2 = arg22;
        ParsableByteArray v3 = arg23;
        v3.skipBytes(10);
        int v4 = arg23.readInt();
        VbriSeeker v5 = null;
        if(v4 <= 0) {
            return v5;
        }

        int v6 = v2.sampleRate;
        long v7 = ((long)v4);
        long v9 = 1000000;
        v4 = v6 >= 32000 ? 1152 : 576;
        long v6_1 = Util.scaleLargeTimestamp(v7, v9 * (((long)v4)), ((long)v6));
        v4 = arg23.readUnsignedShort();
        int v8 = arg23.readUnsignedShort();
        int v9_1 = arg23.readUnsignedShort();
        v3.skipBytes(2);
        long v10 = arg20 + (((long)v2.frameSize));
        long[] v2_1 = new long[v4];
        long[] v14 = new long[v4];
        int v15 = 0;
        long v12 = arg20;
        while(v15 < v4) {
            v16 = v6_1;
            v2_1[v15] = (((long)v15)) * v6_1 / (((long)v4));
            v14[v15] = Math.max(v12, v10);
            switch(v9_1) {
                case 1: {
                    goto label_51;
                }
                case 2: {
                    goto label_49;
                }
                case 3: {
                    goto label_47;
                }
                case 4: {
                    goto label_45;
                }
            }

            return null;
        label_49:
            int v0 = arg23.readUnsignedShort();
            goto label_52;
        label_51:
            v0 = arg23.readUnsignedByte();
            goto label_52;
        label_45:
            v0 = arg23.readUnsignedIntToInt();
            goto label_52;
        label_47:
            v0 = arg23.readUnsignedInt24();
        label_52:
            v12 += ((long)(v0 * v8));
            ++v15;
            v6_1 = v16;
        }

        v16 = v6_1;
        long v3_1 = arg18;
        if(v3_1 != -1 && v3_1 != v12) {
            Log.w("VbriSeeker", "VBRI data size mismatch: " + v3_1 + ", " + v12);
        }

        return new VbriSeeker(v2_1, v14, v16);
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public SeekPoints getSeekPoints(long arg9) {
        int v0 = Util.binarySearchFloor(this.timesUs, arg9, true, true);
        SeekPoint v2 = new SeekPoint(this.timesUs[v0], this.positions[v0]);
        if(v2.timeUs < arg9) {
            if(v0 == this.timesUs.length - 1) {
            }
            else {
                ++v0;
                return new SeekPoints(v2, new SeekPoint(this.timesUs[v0], this.positions[v0]));
            }
        }

        return new SeekPoints(v2);
    }

    public long getTimeUs(long arg4) {
        return this.timesUs[Util.binarySearchFloor(this.positions, arg4, true, true)];
    }

    public boolean isSeekable() {
        return 1;
    }
}

