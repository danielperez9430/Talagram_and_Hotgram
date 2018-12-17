package com.google.ads.interactivemedia.v3.internal;

public final class fi {
    public final int a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public final int g;
    public final long h;

    public fi(byte[] arg2, int arg3) {
        super();
        fo v0 = new fo(arg2);
        v0.a(arg3 * 8);
        this.a = v0.c(16);
        this.b = v0.c(16);
        this.c = v0.c(24);
        this.d = v0.c(24);
        this.e = v0.c(20);
        this.f = v0.c(3) + 1;
        this.g = v0.c(5) + 1;
        this.h = ((long)v0.c(36));
    }

    public int a() {
        return this.g * this.e;
    }

    public long b() {
        return this.h * 1000000 / (((long)this.e));
    }
}

