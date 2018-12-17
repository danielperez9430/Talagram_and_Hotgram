package com.google.ads.interactivemedia.v3.internal;

public class lt {
    public static final Object[] a;
    public static final Class[] b;
    public static final String[] c;
    public static final long[] d;
    public static final Long[] e;
    public static final int[] f;
    public static final Integer[] g;
    public static final short[] h;
    public static final Short[] i;
    public static final byte[] j;
    public static final Byte[] k;
    public static final double[] l;
    public static final Double[] m;
    public static final float[] n;
    public static final Float[] o;
    public static final boolean[] p;
    public static final Boolean[] q;
    public static final char[] r;
    public static final Character[] s;

    static {
        lt.a = new Object[0];
        lt.b = new Class[0];
        lt.c = new String[0];
        lt.d = new long[0];
        lt.e = new Long[0];
        lt.f = new int[0];
        lt.g = new Integer[0];
        lt.h = new short[0];
        lt.i = new Short[0];
        lt.j = new byte[0];
        lt.k = new Byte[0];
        lt.l = new double[0];
        lt.m = new Double[0];
        lt.n = new float[0];
        lt.o = new Float[0];
        lt.p = new boolean[0];
        lt.q = new Boolean[0];
        lt.r = new char[0];
        lt.s = new Character[0];
    }

    public static int a(Object[] arg1, Object arg2) {
        return lt.a(arg1, arg2, 0);
    }

    public static int a(Object[] arg2, Object arg3, int arg4) {
        int v0 = -1;
        if(arg2 == null) {
            return v0;
        }

        if(arg4 < 0) {
            arg4 = 0;
        }

        if(arg3 == null) {
            while(arg4 < arg2.length) {
                if(arg2[arg4] == null) {
                    return arg4;
                }
                else {
                    ++arg4;
                    continue;
                }

                return v0;
            }
        }
        else {
            while(arg4 < arg2.length) {
                if(arg3.equals(arg2[arg4])) {
                    return arg4;
                }
                else {
                    ++arg4;
                    continue;
                }

                return v0;
            }
        }

        return v0;
    }

    public static boolean b(Object[] arg0, Object arg1) {
        boolean v0 = lt.a(arg0, arg1) != -1 ? true : false;
        return v0;
    }
}

