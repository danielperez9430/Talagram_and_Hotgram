package com.google.ads.interactivemedia.v3.internal;

final class dq extends du {
    private final boolean b;
    private final fo c;
    private final fp d;
    private int e;
    private int f;
    private boolean g;
    private long h;
    private bj i;
    private int j;
    private long k;

    public dq(ck arg1, boolean arg2) {
        super(arg1);
        this.b = arg2;
        this.c = new fo(new byte[8]);
        this.d = new fp(this.c.a);
        this.e = 0;
    }

    private boolean a(fp arg3, byte[] arg4, int arg5) {
        int v0 = Math.min(arg3.b(), arg5 - this.f);
        arg3.a(arg4, this.f, v0);
        this.f += v0;
        boolean v3 = this.f == arg5 ? true : false;
        return v3;
    }

    public void a() {
        this.e = 0;
        this.f = 0;
        this.g = false;
    }

    public void a(long arg1, boolean arg3) {
        this.k = arg1;
    }

    public void a(fp arg11) {
        while(arg11.b() > 0) {
            int v1 = 2;
            switch(this.e) {
                case 0: {
                    goto label_46;
                }
                case 1: {
                    goto label_33;
                }
                case 2: {
                    goto label_7;
                }
            }

            continue;
        label_33:
            int v3 = 8;
            if(!this.a(arg11, this.d.a, v3)) {
                continue;
            }

            this.c();
            this.d.c(0);
            this.a.a(this.d, v3);
            this.e = v1;
            continue;
        label_7:
            int v0 = Math.min(arg11.b(), this.j - this.f);
            this.a.a(arg11, v0);
            this.f += v0;
            if(this.f != this.j) {
                continue;
            }

            this.a.a(this.k, 1, this.j, 0, null);
            this.k += this.h;
            this.e = 0;
            continue;
        label_46:
            if(!this.b(arg11)) {
                continue;
            }

            this.e = 1;
            this.d.a[0] = 11;
            this.d.a[1] = 119;
            this.f = v1;
        }
    }

    private boolean b(fp arg6) {
        while(true) {
            boolean v1 = false;
            if(arg6.b() <= 0) {
                return 0;
            }

            int v2 = 11;
            if(!this.g) {
                if(arg6.f() != v2) {
                    goto label_10;
                }

                goto label_9;
            }
            else {
                int v0 = arg6.f();
                if(v0 == 119) {
                    this.g = false;
                    return 1;
                }
                else if(v0 == v2) {
                label_9:
                    v1 = true;
                }
            }

        label_10:
            this.g = v1;
        }

        return 0;
    }

    public void b() {
    }

    private void c() {
        if(this.i == null) {
            long v1 = -1;
            String v3 = null;
            bj v0 = this.b ? fd.b(this.c, v3, v1, v3) : fd.a(this.c, v3, v1, v3);
            this.i = v0;
            this.a.a(this.i);
        }

        int v0_1 = this.b ? fd.b(this.c.a) : fd.a(this.c.a);
        this.j = v0_1;
        v0_1 = this.b ? fd.c(this.c.a) : fd.a();
        this.h = ((long)(((int)((((long)v0_1)) * 1000000 / (((long)this.i.r))))));
    }
}

