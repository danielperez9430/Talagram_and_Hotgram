package e;

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

    private static String a(byte[] arg8, byte[] arg9) {
        int v5;
        byte[] v0 = new byte[(arg8.length + 2) / 3 * 4];
        int v1 = arg8.length - arg8.length % 3;
        int v2 = 0;
        int v3 = 0;
        while(v2 < v1) {
            int v4 = v3 + 1;
            v0[v3] = arg9[(arg8[v2] & 255) >> 2];
            v3 = v4 + 1;
            int v6 = v2 + 1;
            v0[v4] = arg9[(arg8[v2] & 3) << 4 | (arg8[v6] & 255) >> 4];
            v4 = v3 + 1;
            v5 = (arg8[v6] & 15) << 2;
            v6 = v2 + 2;
            v0[v3] = arg9[v5 | (arg8[v6] & 255) >> 6];
            v3 = v4 + 1;
            v0[v4] = arg9[arg8[v6] & 63];
            v2 += 3;
        }

        byte v4_1 = 61;
        switch(arg8.length % 3) {
            case 1: {
                v2 = v3 + 1;
                v0[v3] = arg9[(arg8[v1] & 255) >> 2];
                v3 = v2 + 1;
                v0[v2] = arg9[(arg8[v1] & 3) << 4];
                v0[v3] = v4_1;
                v0[v3 + 1] = v4_1;
                break;
            }
            case 2: {
                v2 = v3 + 1;
                v0[v3] = arg9[(arg8[v1] & 255) >> 2];
                v3 = v2 + 1;
                v5 = (arg8[v1] & 3) << 4;
                ++v1;
                v0[v2] = arg9[v5 | (arg8[v1] & 255) >> 4];
                v0[v3] = arg9[(arg8[v1] & 15) << 2];
                v0[v3 + 1] = v4_1;
                break;
            }
            default: {
                break;
            }
        }

        try {
            return new String(v0, "US-ASCII");
        }
        catch(UnsupportedEncodingException v8) {
            throw new AssertionError(v8);
        }
    }
}

