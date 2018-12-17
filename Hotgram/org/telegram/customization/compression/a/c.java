package org.telegram.customization.compression.a;

import java.nio.ByteOrder;

public enum c {
    static {
        c.a = new c[0];
    }

    public static void a(int arg1) {
        if(arg1 >= 0) {
            return;
        }

        throw new IllegalArgumentException("lengths must be >= 0");
    }

    public static int a(int[] arg0, int arg1) {
        return arg0[arg1];
    }

    public static int a(short[] arg0, int arg1) {
        return arg0[arg1] & 65535;
    }

    public static void a(byte[] arg0, int arg1) {
        if(arg1 >= 0 && arg1 < arg0.length) {
            return;
        }

        throw new ArrayIndexOutOfBoundsException(arg1);
    }

    public static void a(byte[] arg0, int arg1, int arg2) {
        c.a(arg2);
        if(arg2 > 0) {
            c.a(arg0, arg1);
            c.a(arg0, arg1 + arg2 - 1);
        }
    }

    public static void a(int[] arg0, int arg1, int arg2) {
        arg0[arg1] = arg2;
    }

    public static void a(short[] arg0, int arg1, int arg2) {
        arg0[arg1] = ((short)arg2);
    }

    public static byte b(byte[] arg0, int arg1) {
        return arg0[arg1];
    }

    public static void b(byte[] arg2, int arg3, int arg4) {
        arg2[arg3] = ((byte)arg4);
        arg2[arg3 + 1] = ((byte)(arg4 >>> 8));
    }

    public static int c(byte[] arg2, int arg3) {
        return arg2[arg3 + 3] & 255 | ((arg2[arg3] & 255) << 24 | (arg2[arg3 + 1] & 255) << 16 | (arg2[arg3 + 2] & 255) << 8);
    }

    public static void c(byte[] arg0, int arg1, int arg2) {
        arg0[arg1] = ((byte)arg2);
    }

    public static int d(byte[] arg2, int arg3) {
        return (arg2[arg3 + 3] & 255) << 24 | (arg2[arg3] & 255 | (arg2[arg3 + 1] & 255) << 8 | (arg2[arg3 + 2] & 255) << 16);
    }

    public static int e(byte[] arg2, int arg3) {
        if(d.a == ByteOrder.BIG_ENDIAN) {
            return c.c(arg2, arg3);
        }

        return c.d(arg2, arg3);
    }

    public static int f(byte[] arg1, int arg2) {
        return (arg1[arg2 + 1] & 255) << 8 | arg1[arg2] & 255;
    }

    public static c valueOf(String arg1) {
        return Enum.valueOf(c.class, arg1);
    }

    public static c[] values() {
        // Method was not decompiled
    }
}

