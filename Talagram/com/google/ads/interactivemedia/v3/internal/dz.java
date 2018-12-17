package com.google.ads.interactivemedia.v3.internal;

final class dz extends du {
    private final fp b;
    private final fm c;
    private int d;
    private int e;
    private boolean f;
    private boolean g;
    private long h;
    private int i;
    private long j;

    public dz(ck arg3) {
        super(arg3);
        this.d = 0;
        this.b = new fp(4);
        this.b.a[0] = -1;
        this.c = new fm();
    }

    public void a() {
        this.d = 0;
        this.e = 0;
        this.g = false;
    }

    public void a(long arg1, boolean arg3) {
        this.j = arg1;
    }

    public void a(fp arg2) {
        while(arg2.b() > 0) {
            switch(this.d) {
                case 0: {
                    goto label_9;
                }
                case 1: {
                    goto label_7;
                }
                case 2: {
                    goto label_5;
                }
            }

            continue;
        label_5:
            this.d(arg2);
            continue;
        label_7:
            this.c(arg2);
            continue;
        label_9:
            this.b(arg2);
        }
    }

    private void b(fp arg9) {
        byte[] v0 = arg9.a;
        int v1 = arg9.d();
        int v2 = arg9.c();
        while(v1 < v2) {
            boolean v3 = (v0[v1] & 255) == 255 ? true : false;
            int v4 = !this.g || (v0[v1] & 224) != 224 ? 0 : 1;
            this.g = v3;
            if(v4 != 0) {
                arg9.c(v1 + 1);
                this.g = false;
                this.b.a[1] = v0[v1];
                this.e = 2;
                this.d = 1;
                return;
            }

            ++v1;
        }

        arg9.c(v2);
    }

    public void b() {
    }

    private void c(fp arg20) {
        dz v0 = this;
        int v3 = 4;
        int v1 = Math.min(arg20.b(), 4 - v0.e);
        arg20.a(v0.b.a, v0.e, v1);
        v0.e += v1;
        if(v0.e < v3) {
            return;
        }

        v0.b.c(0);
        if(!fm.a(v0.b.m(), v0.c)) {
            v0.e = 0;
            v0.d = 1;
            return;
        }

        v0.i = v0.c.c;
        if(!v0.f) {
            v0.h = (((long)v0.c.g)) * 1000000 / (((long)v0.c.d));
            v0.a.a(bj.a(null, v0.c.b, -1, 4096, -1, v0.c.e, v0.c.d, null, null));
            v0.f = true;
        }

        v0.b.c(0);
        v0.a.a(v0.b, v3);
        v0.d = 2;
    }

    private void d(fp arg9) {
        int v0 = Math.min(arg9.b(), this.i - this.e);
        this.a.a(arg9, v0);
        this.e += v0;
        if(this.e < this.i) {
            return;
        }

        this.a.a(this.j, 1, this.i, 0, null);
        this.j += this.h;
        this.e = 0;
        this.d = 0;
    }
}

