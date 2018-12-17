package com.google.ads.interactivemedia.v3.internal;

import java.io.EOFException;

public final class cs implements cc {
    interface a extends cj {
        long a(long arg1);

        long b();
    }

    private static final int a;
    private static final int b;
    private static final int c;
    private final long d;
    private final fp e;
    private final fm f;
    private ce g;
    private ck h;
    private int i;
    private cg j;
    private a k;
    private long l;
    private long m;
    private int n;

    static {
        cs.a = ft.c("Xing");
        cs.b = ft.c("Info");
        cs.c = ft.c("VBRI");
    }

    public cs() {
        this(-1);
    }

    public cs(long arg1) {
        super();
        this.d = arg1;
        this.e = new fp(4);
        this.f = new fm();
        this.l = -1;
    }

    private boolean a(cd arg11, boolean arg12) {
        // Method was not decompiled
    }

    public int a(cd arg12, ch arg13) {
        if(this.i == 0 && !this.d(arg12)) {
            return -1;
        }

        if(this.k == null) {
            this.e(arg12);
            this.g.a(this.k);
            bj v13 = bj.a(null, this.f.b, -1, 4096, this.k.b(), this.f.e, this.f.d, null, null);
            if(this.j != null) {
                v13 = v13.a(this.j.a, this.j.b);
            }

            this.h.a(v13);
        }

        return this.b(arg12);
    }

    public void a(ce arg2) {
        this.g = arg2;
        this.h = arg2.d(0);
        arg2.f();
    }

    public boolean a(cd arg2) {
        return this.a(arg2, true);
    }

    private int b(cd arg13) {
        int v1 = -1;
        if(this.n == 0) {
            if(!this.c(arg13)) {
                return v1;
            }
            else {
                long v4 = -1;
                if(this.l == v4) {
                    this.l = this.k.a(arg13.c());
                    if(this.d != v4) {
                        this.l += this.d - this.k.a(0);
                    }
                }

                this.n = this.f.c;
            }
        }

        int v13 = this.h.a(arg13, this.n, true);
        if(v13 == v1) {
            return v1;
        }

        this.n -= v13;
        if(this.n > 0) {
            return 0;
        }

        this.h.a(this.l + this.m * 1000000 / (((long)this.f.d)), 1, this.f.c, 0, null);
        this.m += ((long)this.f.g);
        this.n = 0;
        return 0;
    }

    public void b() {
        this.i = 0;
        this.m = 0;
        this.l = -1;
        this.n = 0;
    }

    private boolean c(cd arg7) {
        arg7.a();
        if(!arg7.b(this.e.a, 0, 4, true)) {
            return 0;
        }

        this.e.c(0);
        int v0 = this.e.m();
        if((v0 & -128000) == (-128000 & this.i) && fm.a(v0) != -1) {
            fm.a(v0, this.f);
            return 1;
        }

        this.i = 0;
        arg7.b(1);
        return this.d(arg7);
    }

    public void c() {
    }

    private boolean d(cd arg2) {
        try {
            return this.a(arg2, false);
        }
        catch(EOFException ) {
            return 0;
        }
    }

    private void e(cd arg14) {
        int v0;
        int v8;
        fp v1 = new fp(this.f.c);
        arg14.c(v1.a, 0, this.f.c);
        long v2 = arg14.c();
        long v11 = arg14.d();
        int v7 = 36;
        if((this.f.a & 1) != 0) {
            if(this.f.e != 1) {
                v8 = 36;
            }
            else {
                goto label_23;
            }
        }
        else if(this.f.e != 1) {
        label_23:
            v8 = 21;
        }
        else {
            v8 = 13;
        }

        if(v1.c() >= v8 + 4) {
            v1.c(v8);
            v0 = v1.m();
        }
        else {
            v0 = 0;
        }

        if(v0 == cs.a || v0 == cs.b) {
            this.k = cu.a(this.f, v1, v2, v11);
            if(this.k != null) {
                if(this.j == null) {
                    arg14.a();
                    arg14.c(v8 + 141);
                    arg14.c(this.e.a, 0, 3);
                    this.e.c(0);
                    this.j = cg.a(this.e.j());
                }
                else {
                }

                goto label_76;
            }
            else {
            label_76:
                arg14.b(this.f.c);
            }
        }
        else if(v1.c() >= 40) {
            v1.c(v7);
            if(v1.m() == cs.c) {
                this.k = ct.a(this.f, v1, v2, v11);
                goto label_76;
            }
        }

        if(this.k == null) {
            arg14.a();
            arg14.c(this.e.a, 0, 4);
            this.e.c(0);
            fm.a(this.e.m(), this.f);
            this.k = new cq(arg14.c(), this.f.f, v11);
        }
    }
}

