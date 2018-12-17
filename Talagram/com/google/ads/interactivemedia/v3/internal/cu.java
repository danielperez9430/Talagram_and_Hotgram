package com.google.ads.interactivemedia.v3.internal;

final class cu implements a {
    private final long a;
    private final long b;
    private final long c;
    private final long[] d;
    private final long e;
    private final int g;

    private cu(long arg12, long arg14, long arg16) {
        this(arg12, arg14, arg16, null, 0, 0);
    }

    private cu(long arg1, long arg3, long arg5, long[] arg7, long arg8, int arg10) {
        super();
        this.a = arg1;
        this.b = arg3;
        this.c = arg5;
        this.d = arg7;
        this.e = arg8;
        this.g = arg10;
    }

    public static cu a(fm arg16, fp arg17, long arg18, long arg20) {
        fm v0 = arg16;
        int v1 = v0.g;
        int v2 = v0.d;
        long v3 = arg18 + (((long)v0.c));
        int v5 = arg17.m();
        if((v5 & 1) == 1) {
            int v6 = arg17.s();
            if(v6 == 0) {
            }
            else {
                long v8 = ft.a(((long)v6), (((long)v1)) * 1000000, ((long)v2));
                if((v5 & 6) != 6) {
                    return new cu(v3, v8, arg20);
                }
                else {
                    long v13 = ((long)arg17.s());
                    arg17.d(1);
                    v2 = 99;
                    long[] v12 = new long[v2];
                    for(v5 = 0; v5 < v2; ++v5) {
                        v12[v5] = ((long)arg17.f());
                    }

                    return new cu(v3, v8, arg20, v12, v13, v0.c);
                }
            }
        }

        return null;
    }

    private long a(int arg5) {
        return this.b * (((long)arg5)) / 100;
    }

    public long a(long arg12) {
        long v1 = 0;
        if(this.a()) {
            if(arg12 < this.a) {
            }
            else {
                double v12 = ((double)(arg12 - this.a));
                Double.isNaN(v12);
                double v3 = ((double)this.e);
                Double.isNaN(v3);
                v12 = v12 * 256 / v3;
                int v0 = ft.a(this.d, ((long)v12), true, false) + 1;
                long v3_1 = this.a(v0);
                long v7 = v0 == 0 ? v1 : this.d[v0 - 1];
                long v9 = v0 == 99 ? 256 : this.d[v0];
                long v5 = this.a(v0 + 1);
                if(v9 == v7) {
                }
                else {
                    double v0_1 = ((double)(v5 - v3_1));
                    double v5_1 = ((double)v7);
                    Double.isNaN(v5_1);
                    Double.isNaN(v0_1);
                    v0_1 *= v12 - v5_1;
                    v12 = ((double)(v9 - v7));
                    Double.isNaN(v12);
                    v1 = ((long)(v0_1 / v12));
                }

                return v3_1 + v1;
            }
        }

        return v1;
    }

    public boolean a() {
        boolean v0 = this.d != null ? true : false;
        return v0;
    }

    public long b() {
        return this.b;
    }

    public long b(long arg7) {
        // Method was not decompiled
    }
}

