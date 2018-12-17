package com.google.android.exoplayer2.extractor.mp3;

import android.util.Log;
import com.google.android.exoplayer2.extractor.MpegAudioHeader;
import com.google.android.exoplayer2.extractor.SeekMap$SeekPoints;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

final class XingSeeker implements Seeker {
    private static final String TAG = "XingSeeker";
    private final long dataSize;
    private final long dataStartPosition;
    private final long durationUs;
    private final long[] tableOfContents;
    private final int xingFrameSize;

    private XingSeeker(long arg10, int arg12, long arg13) {
        this(arg10, arg12, arg13, -1, null);
    }

    private XingSeeker(long arg1, int arg3, long arg4, long arg6, long[] arg8) {
        super();
        this.dataStartPosition = arg1;
        this.xingFrameSize = arg3;
        this.durationUs = arg4;
        this.dataSize = arg6;
        this.tableOfContents = arg8;
    }

    public static XingSeeker create(long arg22, long arg24, MpegAudioHeader arg26, ParsableByteArray arg27) {
        long v0 = arg22;
        MpegAudioHeader v2 = arg26;
        int v3 = v2.samplesPerFrame;
        int v4 = v2.sampleRate;
        int v5 = arg27.readInt();
        if((v5 & 1) == 1) {
            int v6 = arg27.readUnsignedIntToInt();
            if(v6 == 0) {
            }
            else {
                long v17 = Util.scaleLargeTimestamp(((long)v6), (((long)v3)) * 1000000, ((long)v4));
                if((v5 & 6) != 6) {
                    return new XingSeeker(arg24, v2.frameSize, v17);
                }
                else {
                    long v3_1 = ((long)arg27.readUnsignedIntToInt());
                    v5 = 100;
                    long[] v6_1 = new long[v5];
                    int v7;
                    for(v7 = 0; v7 < v5; ++v7) {
                        v6_1[v7] = ((long)arg27.readUnsignedByte());
                    }

                    if(v0 != -1) {
                        long v9 = arg24 + v3_1;
                        if(v0 != v9) {
                            Log.w("XingSeeker", "XING data size mismatch: " + v0 + ", " + v9);
                        }
                    }

                    return new XingSeeker(arg24, v2.frameSize, v17, v3_1, v6_1);
                }
            }
        }

        return null;
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public SeekPoints getSeekPoints(long arg13) {
        if(!this.isSeekable()) {
            return new SeekPoints(new SeekPoint(0, this.dataStartPosition + (((long)this.xingFrameSize))));
        }

        arg13 = Util.constrainValue(arg13, 0, this.durationUs);
        double v0 = ((double)arg13);
        double v2 = 100;
        Double.isNaN(v0);
        double v4 = ((double)this.durationUs);
        Double.isNaN(v4);
        v0 = v0 * v2 / v4;
        v4 = 0;
        double v7 = 256;
        if(Double.compare(v0, v4) <= 0) {
        }
        else if(v0 >= v2) {
            v4 = v7;
        }
        else {
            int v2_1 = ((int)v0);
            double v3 = ((double)this.tableOfContents[v2_1]);
            double v5 = v2_1 == 99 ? v7 : ((double)this.tableOfContents[v2_1 + 1]);
            double v9 = ((double)v2_1);
            Double.isNaN(v9);
            Double.isNaN(v3);
            Double.isNaN(v3);
            v4 = v3 + (v0 - v9) * (v5 - v3);
        }

        v0 = ((double)this.dataSize);
        Double.isNaN(v0);
        return new SeekPoints(new SeekPoint(arg13, this.dataStartPosition + Util.constrainValue(Math.round(v4 / v7 * v0), ((long)this.xingFrameSize), this.dataSize - 1)));
    }

    public long getTimeUs(long arg13) {
        double v3_1;
        arg13 -= this.dataStartPosition;
        if(this.isSeekable()) {
            if(arg13 <= (((long)this.xingFrameSize))) {
            }
            else {
                double v13 = ((double)arg13);
                Double.isNaN(v13);
                double v0 = ((double)this.dataSize);
                Double.isNaN(v0);
                v13 = v13 * 256 / v0;
                int v0_1 = Util.binarySearchFloor(this.tableOfContents, ((long)v13), true, true);
                long v1 = this.getTimeUsForTableIndex(v0_1);
                long v4 = this.tableOfContents[v0_1];
                int v3 = v0_1 + 1;
                long v6 = this.getTimeUsForTableIndex(v3);
                long v8 = v0_1 == 99 ? 256 : this.tableOfContents[v3];
                if(v4 == v8) {
                    v13 = 0;
                }
                else {
                    double v10 = ((double)v4);
                    Double.isNaN(v10);
                    v3_1 = ((double)(v8 - v4));
                    Double.isNaN(v3_1);
                    v13 = (v13 - v10) / v3_1;
                }

                v3_1 = ((double)(v6 - v1));
                Double.isNaN(v3_1);
                return v1 + Math.round(v13 * v3_1);
            }
        }

        return 0;
    }

    private long getTimeUsForTableIndex(int arg5) {
        return this.durationUs * (((long)arg5)) / 100;
    }

    public boolean isSeekable() {
        boolean v0 = this.tableOfContents != null ? true : false;
        return v0;
    }
}

