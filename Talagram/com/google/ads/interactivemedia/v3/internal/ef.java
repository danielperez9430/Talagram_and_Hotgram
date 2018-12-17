package com.google.ads.interactivemedia.v3.internal;

public final class ef implements cc, cj {
    private ce a;
    private ck b;
    private eg c;
    private int d;
    private int e;

    public ef() {
        super();
    }

    public int a(cd arg12, ch arg13) {
        if(this.c == null) {
            this.c = eh.a(arg12);
            if(this.c != null) {
                this.d = this.c.b();
            }
            else {
                throw new bl("Error initializing WavHeader. Did you sniff first?");
            }
        }

        if(!this.c.f()) {
            eh.a(arg12, this.c);
            this.b.a(bj.a(null, "audio/raw", this.c.c(), 32768, this.c.a(), this.c.e(), this.c.d(), null, null, this.c.g()));
            this.a.a(((cj)this));
        }

        int v13 = this.b.a(arg12, 32768 - this.e, true);
        int v0 = -1;
        if(v13 != v0) {
            this.e += v13;
        }

        int v7 = this.e / this.d * this.d;
        if(v7 > 0) {
            long v1 = arg12.c() - (((long)this.e));
            this.e -= v7;
            this.b.a(this.c.b(v1), 1, v7, this.e, null);
        }

        if(v13 == v0) {
            return v0;
        }

        return 0;
    }

    public void a(ce arg2) {
        this.a = arg2;
        this.b = arg2.d(0);
        this.c = null;
        arg2.f();
    }

    public boolean a() {
        return 1;
    }

    public boolean a(cd arg1) {
        boolean v1 = eh.a(arg1) != null ? true : false;
        return v1;
    }

    public long b(long arg2) {
        return this.c.a(arg2);
    }

    public void b() {
        this.e = 0;
    }

    public void c() {
    }
}

