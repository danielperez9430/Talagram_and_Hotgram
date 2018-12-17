package com.coremedia.iso;

import java.io.ByteArrayOutputStream;

public class Hex {
    private static final char[] DIGITS;

    static {
        Hex.DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    public Hex() {
        super();
    }

    public static byte[] decodeHex(String arg4) {
        ByteArrayOutputStream v0 = new ByteArrayOutputStream();
        int v1;
        for(v1 = 0; v1 < arg4.length(); v1 = v2) {
            int v2 = v1 + 2;
            v0.write(Integer.parseInt(arg4.substring(v1, v2), 16));
        }

        return v0.toByteArray();
    }

    public static String encodeHex(byte[] arg1) {
        return Hex.encodeHex(arg1, 0);
    }

    public static String encodeHex(byte[] arg7, int arg8) {
        int v0 = arg7.length;
        int v1 = v0 << 1;
        int v2 = 0;
        int v3 = arg8 > 0 ? v0 / arg8 : 0;
        char[] v1_1 = new char[v1 + v3];
        v3 = 0;
        while(v2 < v0) {
            if(arg8 > 0 && v2 % arg8 == 0 && v3 > 0) {
                v1_1[v3] = '-';
                ++v3;
            }

            int v4 = v3 + 1;
            v1_1[v3] = Hex.DIGITS[(arg7[v2] & 240) >>> 4];
            v3 = v4 + 1;
            v1_1[v4] = Hex.DIGITS[arg7[v2] & 15];
            ++v2;
        }

        return new String(v1_1);
    }
}

