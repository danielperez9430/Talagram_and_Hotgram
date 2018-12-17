package com.google.ads.interactivemedia.v3.internal;

public final class by implements cj {
    public final int a;
    public final int[] b;
    public final long[] c;
    public final long[] d;
    public final long[] e;

    public by(int[] arg2, long[] arg3, long[] arg4, long[] arg5) {
        super();
        this.a = arg2.length;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
        this.e = arg5;
    }

    public int a(long arg3) {
        return ft.a(this.e, arg3, true, true);
    }

    public boolean a() {
        return 1;
    }

    public long b(long arg2) {
        return this.c[this.a(arg2)];
    }
}

