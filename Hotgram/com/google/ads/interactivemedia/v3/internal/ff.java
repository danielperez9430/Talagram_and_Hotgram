package com.google.ads.interactivemedia.v3.internal;

import android.util.Pair;

public final class ff {
    private static final byte[] a;
    private static final int[] b;
    private static final int[] c;

    static {
        ff.a = new byte[]{0, 0, 0, 1};
        ff.b = new int[]{96000, 88200, 64000, 48000, 44100, 32000, 24000, 22050, 16000, 12000, 11025, 8000, 7350};
        ff.c = new int[]{0, 1, 2, 3, 4, 5, 6, 8, -1, -1, -1, 7, 8, -1, 8, -1};
    }

    public static Pair a(byte[] arg11) {
        fo v0 = new fo(arg11);
        int v11 = 5;
        int v1 = v0.c(v11);
        int v2 = 4;
        int v3 = v0.c(v2);
        int v4 = 13;
        int v5 = 24;
        int v6 = 15;
        boolean v7 = false;
        if(v3 == v6) {
            v3 = v0.c(v5);
        }
        else {
            boolean v9 = v3 < v4 ? true : false;
            fe.a(v9);
            v3 = ff.b[v3];
        }

        int v9_1 = v0.c(v2);
        if(v1 == v11 || v1 == 29) {
            v1 = v0.c(v2);
            if(v1 == v6) {
                v1 = v0.c(v5);
            }
            else {
                boolean v3_1 = v1 < v4 ? true : false;
                fe.a(v3_1);
                v1 = ff.b[v1];
            }

            v3 = v1;
            if(v0.c(v11) != 22) {
                goto label_42;
            }

            v9_1 = v0.c(v2);
        }

    label_42:
        v11 = ff.c[v9_1];
        if(v11 != -1) {
            v7 = true;
        }

        fe.a(v7);
        return Pair.create(Integer.valueOf(v3), Integer.valueOf(v11));
    }

    public static byte[] a(int arg2, int arg3, int arg4) {
        return new byte[]{((byte)(arg2 << 3 & 248 | arg3 >> 1 & 7)), ((byte)(arg3 << 7 & 128 | arg4 << 3 & 120))};
    }

    public static byte[] a(byte[] arg4, int arg5, int arg6) {
        byte[] v0 = new byte[ff.a.length + arg6];
        System.arraycopy(ff.a, 0, v0, 0, ff.a.length);
        System.arraycopy(arg4, arg5, v0, ff.a.length, arg6);
        return v0;
    }
}

