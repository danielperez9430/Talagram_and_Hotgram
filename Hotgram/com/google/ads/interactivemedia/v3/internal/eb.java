package com.google.ads.interactivemedia.v3.internal;

import android.util.SparseArray;

public final class eb implements cc {
    final class a {
        private final du a;
        private final ec b;
        private final fo c;
        private boolean d;
        private boolean e;
        private boolean f;
        private int g;
        private long h;

        public a(du arg1, ec arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = new fo(new byte[64]);
        }

        public void a(fp arg4, ce arg5) {
            arg4.a(this.c.a, 0, 3);
            this.c.a(0);
            this.b();
            arg4.a(this.c.a, 0, this.g);
            this.c.a(0);
            this.c();
            this.a.a(this.h, true);
            this.a.a(arg4);
            this.a.b();
        }

        public void a() {
            this.f = false;
            this.a.a();
        }

        private void b() {
            this.c.b(8);
            this.d = this.c.b();
            this.e = this.c.b();
            this.c.b(6);
            this.g = this.c.c(8);
        }

        private void c() {
            this.h = 0;
            if(this.d) {
                int v1 = 4;
                this.c.b(v1);
                int v2 = 3;
                int v0 = 30;
                long v3 = (((long)this.c.c(v2))) << v0;
                this.c.b(1);
                int v7 = 15;
                v3 |= ((long)(this.c.c(v7) << v7));
                this.c.b(1);
                v3 |= ((long)this.c.c(v7));
                this.c.b(1);
                if(!this.f && (this.e)) {
                    this.c.b(v1);
                    long v0_1 = (((long)this.c.c(v2))) << v0;
                    this.c.b(1);
                    v0_1 |= ((long)(this.c.c(v7) << v7));
                    this.c.b(1);
                    v0_1 |= ((long)this.c.c(v7));
                    this.c.b(1);
                    this.b.a(v0_1);
                    this.f = true;
                }

                this.h = this.b.a(v3);
            }
        }
    }

    private final ec a;
    private final SparseArray b;
    private final fp c;
    private boolean d;
    private boolean e;
    private boolean f;
    private ce g;

    public eb() {
        this(new ec(0));
    }

    public eb(ec arg2) {
        super();
        this.a = arg2;
        this.c = new fp(4096);
        this.b = new SparseArray();
    }

    public int a(cd arg10, ch arg11) {
        dv v5_2;
        int v4;
        int v3;
        int v2 = -1;
        if(!arg10.b(this.c.a, 0, 4, true)) {
            return v2;
        }

        this.c.c(0);
        int v11 = this.c.m();
        if(v11 == 441) {
            return v2;
        }

        if(v11 == 442) {
            arg10.c(this.c.a, 0, 10);
            this.c.c(0);
            this.c.d(9);
            v11 = (this.c.f() & 7) + 14;
        }
        else {
            v3 = 2;
            v4 = 6;
            if(v11 == 443) {
                arg10.c(this.c.a, 0, v3);
                this.c.c(0);
                v11 = this.c.g() + v4;
            }
            else {
                goto label_46;
            }
        }

        arg10.b(v11);
        return 0;
    label_46:
        if((v11 & -256) >> 8 != 1) {
            arg10.b(1);
            return 0;
        }

        v11 &= 255;
        Object v2_1 = this.b.get(v11);
        if(!this.d) {
            if(v2_1 == null) {
                dq v5 = null;
                if((this.e) || v11 != 189) {
                    if(!this.e && (v11 & 224) == 192) {
                        dz v5_1 = new dz(this.g.d(v11));
                    label_66:
                        this.e = true;
                        goto label_88;
                    }

                    if(this.f) {
                        goto label_88;
                    }

                    if((v11 & 240) != 224) {
                        goto label_88;
                    }

                    v5_2 = new dv(this.g.d(v11));
                    this.f = true;
                }
                else {
                    v5 = new dq(this.g.d(v11), false);
                    goto label_66;
                }

            label_88:
                if((((dq)v5_2)) == null) {
                    goto label_94;
                }

                a v2_2 = new a(((du)v5_2), this.a);
                this.b.put(v11, v2_2);
            }

        label_94:
            if((!this.e || !this.f) && arg10.c() <= 1048576) {
                goto label_104;
            }

            this.d = true;
            this.g.f();
        }

    label_104:
        arg10.c(this.c.a, 0, v3);
        this.c.c(0);
        v11 = this.c.g() + v4;
        if(v2_1 == null) {
            arg10.b(v11);
        }
        else {
            if(this.c.e() < v11) {
                this.c.a(new byte[v11], v11);
            }

            arg10.b(this.c.a, 0, v11);
            this.c.c(v4);
            this.c.b(v11);
            ((a)v2_1).a(this.c, this.g);
            this.c.b(this.c.e());
        }

        return 0;
    }

    public void a(ce arg2) {
        this.g = arg2;
        arg2.a(cj.f);
    }

    public boolean a(cd arg10) {
        byte[] v1 = new byte[14];
        boolean v2 = false;
        arg10.c(v1, 0, 14);
        int v4 = 2;
        int v6 = 8;
        int v5 = 3;
        if(442 != ((v1[0] & 255) << 24 | (v1[1] & 255) << 16 | (v1[v4] & 255) << v6 | v1[v5] & 255)) {
            return 0;
        }

        int v0 = 4;
        if((v1[v0] & 196) != 68) {
            return 0;
        }

        if((v1[6] & v0) != v0) {
            return 0;
        }

        if((v1[v6] & v0) != v0) {
            return 0;
        }

        if((v1[9] & 1) != 1) {
            return 0;
        }

        if((v1[12] & v5) != v5) {
            return 0;
        }

        arg10.c(v1[13] & 7);
        arg10.c(v1, 0, v5);
        if(1 == ((v1[0] & 255) << 16 | (v1[1] & 255) << v6 | v1[v4] & 255)) {
            v2 = true;
        }

        return v2;
    }

    public void b() {
        this.a.a();
        int v0;
        for(v0 = 0; v0 < this.b.size(); ++v0) {
            this.b.valueAt(v0).a();
        }
    }

    public void c() {
    }
}

