package com.google.ads.interactivemedia.v3.internal;

import java.nio.ByteBuffer;

public final class fd {
    private static final int[] a;
    private static final int[] b;
    private static final int[] c;
    private static final int[] d;
    private static final int[] e;
    private static final int[] f;

    static {
        fd.a = new int[]{1, 2, 3, 6};
        fd.b = new int[]{48000, 44100, 32000};
        fd.c = new int[]{24000, 22050, 16000};
        fd.d = new int[]{2, 1, 2, 3, 3, 4, 4, 5};
        fd.e = new int[]{32, 40, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 256, 320, 384, 448, 512, 576, 640};
        fd.f = new int[]{69, 87, 104, 121, 139, 174, 208, 243, 278, 348, 417, 487, 557, 696, 835, 975, 1114, 1253, 1393};
    }

    public static int a() {
        return 1536;
    }

    public static int a(ByteBuffer arg3) {
        int v1 = 6;
        if((arg3.get(arg3.position() + 4) & 192) >> v1 == 3) {
        }
        else {
            v1 = fd.a[(arg3.get(arg3.position() + 4) & 48) >> 4];
        }

        return v1 * 256;
    }

    public static bj a(fp arg12, String arg13, long arg14, String arg16) {
        int v9 = fd.b[(arg12.f() & 192) >> 6];
        int v0 = arg12.f();
        int v1 = fd.d[(v0 & 56) >> 3];
        if((v0 & 4) != 0) {
            ++v1;
        }

        return bj.a(arg13, "audio/ac3", -1, -1, arg14, v1, v9, null, arg16);
    }

    private static int a(int arg1, int arg2) {
        arg1 = fd.b[arg1];
        if(arg1 == 44100) {
            return (fd.f[arg2 / 2] + arg2 % 2) * 2;
        }

        arg2 = fd.e[arg2 / 2];
        if(arg1 == 32000) {
            return arg2 * 6;
        }

        return arg2 * 4;
    }

    public static int a(byte[] arg2) {
        return fd.a((arg2[4] & 192) >> 6, arg2[4] & 63);
    }

    public static bj a(fo arg14, String arg15, long arg16, String arg18) {
        arg14.b(32);
        int v1 = 2;
        int v2 = arg14.c(v1);
        arg14.b(14);
        int v3 = arg14.c(3);
        if((v3 & 1) != 0 && v3 != 1) {
            arg14.b(v1);
        }

        if((v3 & 4) != 0) {
            arg14.b(v1);
        }

        if(v3 == v1) {
            arg14.b(v1);
        }

        return bj.a(arg15, "audio/ac3", -1, -1, arg16, fd.d[v3] + arg14.b(), fd.b[v2], null, arg18);
    }

    public static bj b(fp arg13, String arg14, long arg15, String arg17) {
        arg13.d(2);
        int v10 = fd.b[(arg13.f() & 192) >> 6];
        int v0 = arg13.f();
        int v1 = fd.d[(v0 & 14) >> 1];
        if((v0 & 1) != 0) {
            ++v1;
        }

        return bj.a(arg14, "audio/eac3", -1, -1, arg15, v1, v10, null, arg17);
    }

    public static int b(byte[] arg3) {
        return (((arg3[2] & 7) << 8) + (arg3[3] & 255) + 1) * 2;
    }

    public static bj b(fo arg14, String arg15, long arg16, String arg18) {
        arg14.b(32);
        int v1 = 2;
        int v2 = arg14.c(v1);
        int v3 = 3;
        if(v2 == v3) {
            v1 = fd.c[arg14.c(v1)];
        }
        else {
            arg14.b(v1);
            v1 = fd.b[v2];
        }

        int v11 = v1;
        return bj.a(arg15, "audio/eac3", -1, -1, arg16, fd.d[arg14.c(v3)] + arg14.b(), v11, null, arg18);
    }

    public static int c(byte[] arg4) {
        int v0 = 4;
        int v2 = 6;
        if((arg4[v0] & 192) >> v2 == 3) {
        }
        else {
            v2 = fd.a[(arg4[v0] & 48) >> v0];
        }

        return v2 * 256;
    }
}

