package com.google.ads.interactivemedia.v3.internal;

final class df {
    public cx a;
    public long b;
    public long c;
    public int d;
    public int[] e;
    public int[] f;
    public long[] g;
    public boolean[] h;
    public boolean i;
    public boolean[] j;
    public int k;
    public fp l;
    public boolean m;
    public de n;
    public long o;

    df() {
        super();
    }

    public void a() {
        this.d = 0;
        this.o = 0;
        this.i = false;
        this.m = false;
        this.n = null;
    }

    public void a(int arg3) {
        this.d = arg3;
        if(this.e == null || this.e.length < this.d) {
            arg3 = arg3 * 125 / 100;
            this.e = new int[arg3];
            this.f = new int[arg3];
            this.g = new long[arg3];
            this.h = new boolean[arg3];
            this.j = new boolean[arg3];
        }
    }

    public void a(cd arg4) {
        arg4.b(this.l.a, 0, this.k);
        this.l.c(0);
        this.m = false;
    }

    public void a(fp arg4) {
        arg4.a(this.l.a, 0, this.k);
        this.l.c(0);
        this.m = false;
    }

    public void b(int arg2) {
        if(this.l == null || this.l.c() < arg2) {
            this.l = new fp(arg2);
        }

        this.k = arg2;
        this.i = true;
        this.m = true;
    }

    public long c(int arg6) {
        return this.g[arg6] + (((long)this.f[arg6]));
    }
}

