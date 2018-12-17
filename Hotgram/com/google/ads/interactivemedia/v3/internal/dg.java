package com.google.ads.interactivemedia.v3.internal;

final class dg {
    public final int a;
    public final long[] b;
    public final int[] c;
    public final int d;
    public final long[] e;
    public final int[] f;

    dg(long[] arg5, int[] arg6, int arg7, long[] arg8, int[] arg9) {
        super();
        boolean v2 = false;
        boolean v0 = arg6.length == arg8.length ? true : false;
        fe.a(v0);
        v0 = arg5.length == arg8.length ? true : false;
        fe.a(v0);
        if(arg9.length == arg8.length) {
            v2 = true;
        }

        fe.a(v2);
        this.b = arg5;
        this.c = arg6;
        this.d = arg7;
        this.e = arg8;
        this.f = arg9;
        this.a = arg5.length;
    }

    public int a(long arg4) {
        int v4;
        for(v4 = ft.a(this.e, arg4, true, false); v4 >= 0; --v4) {
            if((this.f[v4] & 1) != 0) {
                return v4;
            }
        }

        return -1;
    }

    public int b(long arg4) {
        int v4;
        for(v4 = ft.b(this.e, arg4, true, false); v4 < this.e.length; ++v4) {
            if((this.f[v4] & 1) != 0) {
                return v4;
            }
        }

        return -1;
    }
}

