package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Collections;
import java.util.List;

public final class HevcConfig {
    public final List initializationData;
    public final int nalUnitLengthFieldLength;

    private HevcConfig(List arg1, int arg2) {
        super();
        this.initializationData = arg1;
        this.nalUnitLengthFieldLength = arg2;
    }

    public static HevcConfig parse(ParsableByteArray arg13) {
        int v8;
        int v7;
        int v0 = 21;
        try {
            arg13.skipBytes(v0);
            v0 = arg13.readUnsignedByte() & 3;
            int v1 = arg13.readUnsignedByte();
            int v2 = arg13.getPosition();
            int v4 = 0;
            int v5;
            for(v5 = 0; v4 < v1; v5 = v7) {
                arg13.skipBytes(1);
                int v6 = arg13.readUnsignedShort();
                v7 = v5;
                for(v5 = 0; v5 < v6; ++v5) {
                    v8 = arg13.readUnsignedShort();
                    v7 += v8 + 4;
                    arg13.skipBytes(v8);
                }

                ++v4;
            }

            arg13.setPosition(v2);
            byte[] v2_1 = new byte[v5];
            v4 = 0;
            for(v7 = 0; v4 < v1; v7 = v9) {
                arg13.skipBytes(1);
                v8 = arg13.readUnsignedShort();
                int v9 = v7;
                for(v7 = 0; v7 < v8; ++v7) {
                    int v10 = arg13.readUnsignedShort();
                    System.arraycopy(NalUnitUtil.NAL_START_CODE, 0, v2_1, v9, NalUnitUtil.NAL_START_CODE.length);
                    v9 += NalUnitUtil.NAL_START_CODE.length;
                    System.arraycopy(arg13.data, arg13.getPosition(), v2_1, v9, v10);
                    v9 += v10;
                    arg13.skipBytes(v10);
                }

                ++v4;
            }

            List v13_1 = v5 == 0 ? null : Collections.singletonList(v2_1);
            return new HevcConfig(v13_1, v0 + 1);
        }
        catch(ArrayIndexOutOfBoundsException v13) {
            throw new ParserException("Error parsing HEVC config", ((Throwable)v13));
        }
    }
}

