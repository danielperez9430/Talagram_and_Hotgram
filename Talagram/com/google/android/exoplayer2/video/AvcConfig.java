package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.NalUnitUtil$SpsData;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.List;

public final class AvcConfig {
    public final int height;
    public final List initializationData;
    public final int nalUnitLengthFieldLength;
    public final float pixelWidthAspectRatio;
    public final int width;

    private AvcConfig(List arg1, int arg2, int arg3, int arg4, float arg5) {
        super();
        this.initializationData = arg1;
        this.nalUnitLengthFieldLength = arg2;
        this.width = arg3;
        this.height = arg4;
        this.pixelWidthAspectRatio = arg5;
    }

    private static byte[] buildNalUnitForChild(ParsableByteArray arg2) {
        int v0 = arg2.readUnsignedShort();
        int v1 = arg2.getPosition();
        arg2.skipBytes(v0);
        return CodecSpecificDataUtil.buildNalUnit(arg2.data, v1, v0);
    }

    public static AvcConfig parse(ParsableByteArray arg8) {
        int v6;
        float v7;
        int v0 = 4;
        try {
            arg8.skipBytes(v0);
            int v4 = (arg8.readUnsignedByte() & 3) + 1;
            if(v4 != 3) {
                ArrayList v3 = new ArrayList();
                v0 = arg8.readUnsignedByte() & 31;
                int v2;
                for(v2 = 0; v2 < v0; ++v2) {
                    ((List)v3).add(AvcConfig.buildNalUnitForChild(arg8));
                }

                v2 = arg8.readUnsignedByte();
                int v5;
                for(v5 = 0; v5 < v2; ++v5) {
                    ((List)v3).add(AvcConfig.buildNalUnitForChild(arg8));
                }

                if(v0 > 0) {
                    SpsData v8_1 = NalUnitUtil.parseSpsNalUnit(((List)v3).get(0), v4, ((List)v3).get(0).length);
                    v0 = v8_1.width;
                    int v1 = v8_1.height;
                    v7 = v8_1.pixelWidthAspectRatio;
                    v5 = v0;
                    v6 = v1;
                }
                else {
                    v5 = -1;
                    v6 = -1;
                    v7 = 1f;
                }

                return new AvcConfig(((List)v3), v4, v5, v6, v7);
            }

            throw new IllegalStateException();
        }
        catch(ArrayIndexOutOfBoundsException v8) {
            throw new ParserException("Error parsing AVC config", ((Throwable)v8));
        }
    }
}

