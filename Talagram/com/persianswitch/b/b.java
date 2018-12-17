package com.persianswitch.b;

import java.io.UnsupportedEncodingException;

final class b {
    private static final byte[] a;
    private static final byte[] b;

    static {
        b.a = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        b.b = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    }

    public static String a(byte[] arg1) {
        return b.a(arg1, b.a);
    }

    private static String a(byte[] arg9, byte[] arg10) {
        int v6;
        byte[] v0 = new byte[(arg9.length + 2) * 4 / 3];
        int v1 = arg9.length - arg9.length % 3;
        int v3 = 0;
        int v4 = 0;
        while(v3 < v1) {
            int v5 = v4 + 1;
            v0[v4] = arg10[(arg9[v3] & 255) >> 2];
            v4 = v5 + 1;
            int v7 = v3 + 1;
            v0[v5] = arg10[(arg9[v3] & 3) << 4 | (arg9[v7] & 255) >> 4];
            v5 = v4 + 1;
            v6 = (arg9[v7] & 15) << 2;
            v7 = v3 + 2;
            v0[v4] = arg10[v6 | (arg9[v7] & 255) >> 6];
            v4 = v5 + 1;
            v0[v5] = arg10[arg9[v7] & 63];
            v3 += 3;
        }

        byte v5_1 = 61;
        switch(arg9.length % 3) {
            case 1: {
                v3 = v4 + 1;
                v0[v4] = arg10[(arg9[v1] & 255) >> 2];
                v4 = v3 + 1;
                v0[v3] = arg10[(arg9[v1] & 3) << 4];
                int v9 = v4 + 1;
                v0[v4] = v5_1;
                v4 = v9 + 1;
                v0[v9] = v5_1;
                break;
            }
            case 2: {
                v3 = v4 + 1;
                v0[v4] = arg10[(arg9[v1] & 255) >> 2];
                v4 = v3 + 1;
                v6 = (arg9[v1] & 3) << 4;
                ++v1;
                v0[v3] = arg10[v6 | (arg9[v1] & 255) >> 4];
                v3 = v4 + 1;
                v0[v4] = arg10[(arg9[v1] & 15) << 2];
                v4 = v3 + 1;
                v0[v3] = v5_1;
                break;
            }
            default: {
                break;
            }
        }

        try {
            return new String(v0, 0, v4, "US-ASCII");
        }
        catch(UnsupportedEncodingException v9_1) {
            throw new AssertionError(v9_1);
        }
    }
}

