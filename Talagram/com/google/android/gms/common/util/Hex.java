package com.google.android.gms.common.util;

public class Hex {
    private static final char[] zzaaa;
    private static final char[] zzzz;

    static {
        Hex.zzzz = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        Hex.zzaaa = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    }

    public Hex() {
        super();
    }

    public static String bytesToColonDelimitedStringLowercase(byte[] arg7) {
        if(arg7.length == 0) {
            return new String();
        }

        char[] v0 = new char[arg7.length * 3 - 1];
        int v1 = 0;
        int v2;
        for(v2 = 0; v1 < arg7.length - 1; ++v2) {
            int v3 = arg7[v1] & 255;
            int v4 = v2 + 1;
            v0[v2] = Hex.zzaaa[v3 >>> 4];
            v2 = v4 + 1;
            v0[v4] = Hex.zzaaa[v3 & 15];
            v0[v2] = ':';
            ++v1;
        }

        int v7 = arg7[arg7.length - 1] & 255;
        v0[v2] = Hex.zzaaa[v7 >>> 4];
        v0[v2 + 1] = Hex.zzaaa[v7 & 15];
        return new String(v0);
    }

    public static String bytesToColonDelimitedStringUppercase(byte[] arg7) {
        if(arg7.length == 0) {
            return new String();
        }

        char[] v0 = new char[arg7.length * 3 - 1];
        int v1 = 0;
        int v2;
        for(v2 = 0; v1 < arg7.length - 1; ++v2) {
            int v3 = arg7[v1] & 255;
            int v4 = v2 + 1;
            v0[v2] = Hex.zzzz[v3 >>> 4];
            v2 = v4 + 1;
            v0[v4] = Hex.zzzz[v3 & 15];
            v0[v2] = ':';
            ++v1;
        }

        int v7 = arg7[arg7.length - 1] & 255;
        v0[v2] = Hex.zzzz[v7 >>> 4];
        v0[v2 + 1] = Hex.zzzz[v7 & 15];
        return new String(v0);
    }

    public static String bytesToStringLowercase(byte[] arg7) {
        char[] v0 = new char[arg7.length << 1];
        int v1 = 0;
        int v2 = 0;
        while(v1 < arg7.length) {
            int v3 = arg7[v1] & 255;
            int v4 = v2 + 1;
            v0[v2] = Hex.zzaaa[v3 >>> 4];
            v2 = v4 + 1;
            v0[v4] = Hex.zzaaa[v3 & 15];
            ++v1;
        }

        return new String(v0);
    }

    public static String bytesToStringUppercase(byte[] arg1) {
        return Hex.bytesToStringUppercase(arg1, false);
    }

    public static String bytesToStringUppercase(byte[] arg5, boolean arg6) {
        int v0 = arg5.length;
        StringBuilder v1 = new StringBuilder(v0 << 1);
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            if((arg6) && v2 == v0 - 1 && (arg5[v2] & 255) == 0) {
                break;
            }

            v1.append(Hex.zzzz[(arg5[v2] & 240) >>> 4]);
            v1.append(Hex.zzzz[arg5[v2] & 15]);
        }

        return v1.toString();
    }

    public static byte[] colonDelimitedStringToBytes(String arg2) {
        return Hex.stringToBytes(arg2.replace(":", ""));
    }

    public static byte[] stringToBytes(String arg6) {
        int v0 = arg6.length();
        if(v0 % 2 == 0) {
            byte[] v1 = new byte[v0 / 2];
            int v2;
            for(v2 = 0; v2 < v0; v2 = v4) {
                int v4 = v2 + 2;
                v1[v2 / 2] = ((byte)Integer.parseInt(arg6.substring(v2, v4), 16));
            }

            return v1;
        }

        throw new IllegalArgumentException("Hex string has odd number of characters");
    }
}

