package com.google.ads.interactivemedia.v3.internal;

import java.nio.ByteBuffer;

public final class fg {
    private static final int[] a;
    private static final int[] b;
    private static final int[] c;
    private static final fo d;

    static {
        fg.a = new int[]{1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 6, 6, 6, 7, 8, 8};
        fg.b = new int[]{-1, 8000, 16000, 32000, -1, -1, 11025, 22050, 44100, -1, -1, 12000, 24000, 48000, -1, -1};
        fg.c = new int[]{64, 112, 128, 192, 224, 256, 384, 448, 512, 640, 768, 896, 1024, 1152, 1280, 1536, 1920, 2048, 2304, 2560, 2688, 2816, 2823, 2944, 3072, 3840, 4096, 6144, 7680};
        fg.d = new fo();
    }

    public static int a(ByteBuffer arg2) {
        int v0 = arg2.position();
        return (((arg2.get(v0 + 5) & 252) >> 2 | (arg2.get(v0 + 4) & 1) << 6) + 1) * 32;
    }

    public static int a(byte[] arg2) {
        return (((arg2[5] & 252) >> 2 | (arg2[4] & 1) << 6) + 1) * 32;
    }

    public static bj a(byte[] arg14, String arg15, long arg16, String arg18) {
        fo v0 = fg.d;
        v0.a(arg14);
        v0.b(60);
        int v1 = fg.a[v0.c(6)];
        int v11 = fg.b[v0.c(4)];
        int v2 = v0.c(5);
        int v4 = 2;
        int v6 = v2 >= fg.c.length ? -1 : fg.c[v2] * 1000 / v4;
        v0.b(10);
        int v0_1 = v0.c(v4) > 0 ? 1 : 0;
        return bj.a(arg15, "audio/vnd.dts", v6, -1, arg16, v1 + v0_1, v11, null, arg18);
    }

    public static int b(byte[] arg2) {
        return ((arg2[7] & 240) >> 4 | ((arg2[5] & 2) << 12 | (arg2[6] & 255) << 4)) + 1;
    }
}

