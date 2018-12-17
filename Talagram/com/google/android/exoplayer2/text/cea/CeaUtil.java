package com.google.android.exoplayer2.text.cea;

import android.util.Log;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

public final class CeaUtil {
    private static final int COUNTRY_CODE = 181;
    private static final int PAYLOAD_TYPE_CC = 4;
    private static final int PROVIDER_CODE_ATSC = 49;
    private static final int PROVIDER_CODE_DIRECTV = 47;
    private static final String TAG = "CeaUtil";
    public static final int USER_DATA_IDENTIFIER_GA94 = 0;
    public static final int USER_DATA_TYPE_CODE_MPEG_CC = 3;

    static {
        CeaUtil.USER_DATA_IDENTIFIER_GA94 = Util.getIntegerCodeForString("GA94");
    }

    private CeaUtil() {
        super();
    }

    public static void consume(long arg10, ParsableByteArray arg12, TrackOutput[] arg13) {
        while(true) {
            int v1 = 1;
            if(arg12.bytesLeft() <= 1) {
                return;
            }

            int v0 = CeaUtil.readNon255TerminatedValue(arg12);
            int v2 = CeaUtil.readNon255TerminatedValue(arg12);
            int v3 = arg12.getPosition() + v2;
            if(v2 == -1 || v2 > arg12.bytesLeft()) {
                Log.w("CeaUtil", "Skipping remainder of malformed SEI NAL unit.");
                v3 = arg12.limit();
            }
            else if(v0 == 4 && v2 >= 8) {
                v0 = arg12.readUnsignedByte();
                v2 = arg12.readUnsignedShort();
                int v4 = 49;
                int v6 = v2 == v4 ? arg12.readInt() : 0;
                int v7 = arg12.readUnsignedByte();
                int v8 = 47;
                if(v2 == v8) {
                    arg12.skipBytes(1);
                }

                if(v0 == 181) {
                    if(v2 != v4 && v2 != v8) {
                        goto label_36;
                    }

                    if(v7 != 3) {
                        goto label_36;
                    }

                    v0 = 1;
                }
                else {
                label_36:
                    v0 = 0;
                }

                if(v2 == v4) {
                    if(v6 == CeaUtil.USER_DATA_IDENTIFIER_GA94) {
                    }
                    else {
                        v1 = 0;
                    }

                    v0 &= v1;
                }

                if(v0 == 0) {
                    goto label_50;
                }

                CeaUtil.consumeCcData(arg10, arg12, arg13);
            }

        label_50:
            arg12.setPosition(v3);
        }
    }

    public static void consumeCcData(long arg11, ParsableByteArray arg13, TrackOutput[] arg14) {
        int v0 = arg13.readUnsignedByte();
        int v2 = 0;
        int v1 = (v0 & 64) != 0 ? 1 : 0;
        if(v1 == 0) {
            return;
        }

        arg13.skipBytes(1);
        v0 = (v0 & 31) * 3;
        v1 = arg13.getPosition();
        int v3 = arg14.length;
        while(v2 < v3) {
            TrackOutput v4 = arg14[v2];
            arg13.setPosition(v1);
            v4.sampleData(arg13, v0);
            v4.sampleMetadata(arg11, 1, v0, 0, null);
            ++v2;
        }
    }

    private static int readNon255TerminatedValue(ParsableByteArray arg3) {
        int v0 = 0;
        do {
            if(arg3.bytesLeft() == 0) {
                return -1;
            }

            int v1 = arg3.readUnsignedByte();
            v0 += v1;
        }
        while(v1 == 255);

        return v0;
    }
}

