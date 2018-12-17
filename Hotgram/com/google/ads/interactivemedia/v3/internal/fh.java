package com.google.ads.interactivemedia.v3.internal;

public final class fh {
    private final long[] a;
    private final long[] b;

    private fh(long[] arg1, long[] arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public static fh a(fp arg6) {
        arg6.d(1);
        int v0 = arg6.j() / 18;
        long[] v1 = new long[v0];
        long[] v2 = new long[v0];
        int v3;
        for(v3 = 0; v3 < v0; ++v3) {
            v1[v3] = arg6.o();
            v2[v3] = arg6.o();
            arg6.d(2);
        }

        return new fh(v1, v2);
    }

    static long[] a(fh arg0) {
        return arg0.a;
    }

    public cj a(long arg8, long arg10) {
        return new cj(arg10, arg8) {
            public boolean a() {
                return 1;
            }

            public long b(long arg3) {
                return this.b + fh.b(this.c)[ft.a(fh.a(this.c), arg3 * this.a / 1000000, true, true)];
            }
        };
    }

    static long[] b(fh arg0) {
        return arg0.b;
    }
}

