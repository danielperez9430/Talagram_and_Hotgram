package com.google.ads.interactivemedia.v3.internal;

public final class cm implements cc, cj {
    public int a;
    public int b;
    public long c;
    private static final int d;
    private final fp e;
    private final fp g;
    private final fp h;
    private final fp i;
    private ce j;
    private int k;
    private int l;
    private cl m;
    private cp n;
    private cn o;

    static {
        cm.d = ft.c("FLV");
    }

    public cm() {
        super();
        this.e = new fp(4);
        this.g = new fp(9);
        this.h = new fp(11);
        this.i = new fp();
        this.k = 1;
    }

    public int a(cd arg2, ch arg3) {
        int v0;
        do {
        label_0:
            v0 = -1;
            switch(this.k) {
                case 1: {
                    goto label_13;
                }
                case 2: {
                    goto label_11;
                }
                case 3: {
                    goto label_8;
                }
                case 4: {
                    goto label_4;
                }
            }

            goto label_0;
        label_4:
            if(!this.e(arg2)) {
                goto label_0;
            }

            return 0;
        label_8:
            if(this.d(arg2)) {
                goto label_0;
            }

            return v0;
        label_11:
            this.c(arg2);
            goto label_0;
        label_13:
        }
        while(this.b(arg2));

        return v0;
    }

    public void a(ce arg1) {
        this.j = arg1;
    }

    public boolean a() {
        return 0;
    }

    public boolean a(cd arg4) {
        boolean v1 = false;
        arg4.c(this.e.a, 0, 3);
        this.e.c(0);
        if(this.e.j() != cm.d) {
            return 0;
        }

        arg4.c(this.e.a, 0, 2);
        this.e.c(0);
        if((this.e.g() & 250) != 0) {
            return 0;
        }

        arg4.c(this.e.a, 0, 4);
        this.e.c(0);
        int v0 = this.e.m();
        arg4.a();
        arg4.c(v0);
        arg4.c(this.e.a, 0, 4);
        this.e.c(0);
        if(this.e.m() == 0) {
            v1 = true;
        }

        return v1;
    }

    private boolean b(cd arg7) {
        int v1 = 9;
        int v3 = 0;
        if(!arg7.a(this.g.a, 0, v1, true)) {
            return 0;
        }

        this.g.c(0);
        int v0 = 4;
        this.g.d(v0);
        int v7 = this.g.f();
        int v4 = (v7 & 4) != 0 ? 1 : 0;
        if((v7 & 1) != 0) {
            v3 = 1;
        }

        if(v4 != 0 && this.m == null) {
            this.m = new cl(this.j.d(8));
        }

        if(v3 != 0 && this.n == null) {
            this.n = new cp(this.j.d(v1));
        }

        if(this.o == null) {
            this.o = new cn(null);
        }

        this.j.f();
        this.j.a(((cj)this));
        this.l = this.g.m() - v1 + v0;
        this.k = 2;
        return 1;
    }

    public long b(long arg1) {
        return 0;
    }

    public void b() {
        this.k = 1;
        this.l = 0;
    }

    private void c(cd arg2) {
        arg2.b(this.l);
        this.l = 0;
        this.k = 3;
    }

    public void c() {
    }

    private boolean d(cd arg7) {
        if(!arg7.a(this.h.a, 0, 11, true)) {
            return 0;
        }

        this.h.c(0);
        this.a = this.h.f();
        this.b = this.h.j();
        this.c = ((long)this.h.j());
        this.c = ((((long)(this.h.f() << 24))) | this.c) * 1000;
        this.h.d(3);
        this.k = 4;
        return 1;
    }

    private boolean e(cd arg5) {
        boolean v5;
        if(this.a != 8 || this.m == null) {
            if(this.a == 9 && this.n != null) {
                this.n.b(this.f(arg5), this.c);
                goto label_45;
            }

            if(this.a == 18 && this.o != null) {
                this.o.b(this.f(arg5), this.c);
                if(this.o.a() != -1) {
                    if(this.m != null) {
                        this.m.a(this.o.a());
                    }

                    if(this.n == null) {
                        goto label_45;
                    }

                    this.n.a(this.o.a());
                    goto label_45;
                }
                else {
                label_45:
                    v5 = true;
                    goto label_50;
                }
            }

            arg5.b(this.b);
            v5 = false;
        }
        else {
            this.m.b(this.f(arg5), this.c);
            goto label_45;
        }

    label_50:
        this.l = 4;
        this.k = 2;
        return v5;
    }

    private fp f(cd arg5) {
        if(this.b > this.i.e()) {
            this.i.a(new byte[Math.max(this.i.e() * 2, this.b)], 0);
        }
        else {
            this.i.c(0);
        }

        this.i.b(this.b);
        arg5.b(this.i.a, 0, this.b);
        return this.i;
    }
}

