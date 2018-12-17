package com.googlecode.mp4parser.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;

public class UUIDConverter {
    public UUIDConverter() {
        super();
    }

    public static UUID convert(byte[] arg5) {
        ByteBuffer v5 = ByteBuffer.wrap(arg5);
        v5.order(ByteOrder.BIG_ENDIAN);
        return new UUID(v5.getLong(), v5.getLong());
    }

    public static byte[] convert(UUID arg9) {
        long v0 = arg9.getMostSignificantBits();
        long v2 = arg9.getLeastSignificantBits();
        int v9 = 16;
        byte[] v4 = new byte[v9];
        int v5;
        for(v5 = 0; v5 < 8; ++v5) {
            v4[v5] = ((byte)(((int)(v0 >>> (7 - v5) * 8))));
        }

        int v0_1;
        for(v0_1 = 8; v0_1 < v9; ++v0_1) {
            v4[v0_1] = ((byte)(((int)(v2 >>> (7 - v0_1) * 8))));
        }

        return v4;
    }
}

