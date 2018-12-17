package com.google.ads.interactivemedia.v3.internal;

final class dt extends du {
    private final fp b;
    private int c;
    private int d;
    private int e;
    private long f;
    private bj g;
    private int h;
    private long i;

    public dt(ck arg5) {
        super(arg5);
        this.b = new fp(new byte[15]);
        this.b.a[0] = 127;
        this.b.a[1] = -2;
        this.b.a[2] = -128;
        this.b.a[3] = 1;
        this.c = 0;
    }

    private boolean a(fp arg3, byte[] arg4, int arg5) {
        int v0 = Math.min(arg3.b(), arg5 - this.d);
        arg3.a(arg4, this.d, v0);
        this.d += v0;
        boolean v3 = this.d == arg5 ? true : false;
        return v3;
    }

    public void a() {
        this.c = 0;
        this.d = 0;
        this.e = 0;
    }

    public void a(long arg1, boolean arg3) {
        this.i = arg1;
    }

    public void a(fp arg11) {
        while(arg11.b() > 0) {
            switch(this.c) {
                case 0: {
                    goto label_45;
                }
                case 1: {
                    goto label_32;
                }
                case 2: {
                    goto label_6;
                }
            }

            continue;
        label_6:
            int v0 = Math.min(arg11.b(), this.h - this.d);
            this.a.a(arg11, v0);
            this.d += v0;
            if(this.d != this.h) {
                continue;
            }

            this.a.a(this.i, 1, this.h, 0, null);
            this.i += this.f;
            this.c = 0;
            continue;
        label_45:
            if(!this.b(arg11)) {
                continue;
            }

            this.d = 4;
            v0 = 1;
            goto label_50;
        label_32:
            int v2 = 15;
            if(!this.a(arg11, this.b.a, v2)) {
                continue;
            }

            this.c();
            this.b.c(0);
            this.a.a(this.b, v2);
            v0 = 2;
        label_50:
            this.c = v0;
        }
    }

    private boolean b(fp arg4) {
        do {
            if(arg4.b() <= 0) {
                return 0;
            }

            this.e <<= 8;
            this.e |= arg4.f();
        }
        while(this.e != 2147385345);

        this.e = 0;
        return 1;
    }

    public void b() {
    }

    private void c() {
        byte[] v0 = this.b.a;
        if(this.g == null) {
            this.g = fg.a(v0, null, -1, null);
            this.a.a(this.g);
        }

        this.h = fg.b(v0);
        this.f = ((long)(((int)((((long)fg.a(v0))) * 1000000 / (((long)this.g.r))))));
    }
}

