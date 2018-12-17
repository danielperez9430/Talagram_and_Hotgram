package com.google.ads.interactivemedia.v3.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public final class da implements cc, cj {
    final class a {
        public final dd a;
        public final dg b;
        public final ck c;
        public int d;

        public a(dd arg1, dg arg2, ck arg3) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
        }
    }

    private static final int a;
    private final fp b;
    private final fp c;
    private final fp d;
    private final Stack e;
    private int g;
    private int h;
    private long i;
    private int j;
    private fp k;
    private int l;
    private int m;
    private int n;
    private ce o;
    private a[] p;
    private boolean q;

    static {
        da.a = ft.c("qt  ");
    }

    public da() {
        super();
        this.d = new fp(16);
        this.e = new Stack();
        this.b = new fp(fn.a);
        this.c = new fp(4);
        this.d();
    }

    private void a(long arg5) {
        while(true) {
            int v1 = 3;
            if(!this.e.isEmpty() && this.e.peek().aP == arg5) {
                Object v0 = this.e.pop();
                if(((com.google.ads.interactivemedia.v3.internal.cv$a)v0).aO == cv.B) {
                    this.a(((com.google.ads.interactivemedia.v3.internal.cv$a)v0));
                    this.e.clear();
                    this.g = v1;
                }
                else {
                    if(this.e.isEmpty()) {
                        continue;
                    }

                    this.e.peek().a(((com.google.ads.interactivemedia.v3.internal.cv$a)v0));
                }

                continue;
            }

            break;
        }

        if(this.g != v1) {
            this.d();
        }
    }

    private void a(com.google.ads.interactivemedia.v3.internal.cv$a arg12) {
        ArrayList v0 = new ArrayList();
        b v1 = arg12.d(cv.az);
        cg v1_1 = v1 != null ? cw.a(v1, this.q) : null;
        long v4 = 9223372036854775807L;
        int v3;
        for(v3 = 0; v3 < arg12.aR.size(); ++v3) {
            Object v6 = arg12.aR.get(v3);
            if(((com.google.ads.interactivemedia.v3.internal.cv$a)v6).aO != cv.D) {
            }
            else {
                dd v7 = cw.a(((com.google.ads.interactivemedia.v3.internal.cv$a)v6), arg12.d(cv.C), -1, this.q);
                if(v7 == null) {
                }
                else {
                    dg v6_1 = cw.a(v7, ((com.google.ads.interactivemedia.v3.internal.cv$a)v6).e(cv.E).e(cv.F).e(cv.G));
                    if(v6_1.a == 0) {
                    }
                    else {
                        a v8 = new a(v7, v6_1, this.o.d(v3));
                        bj v7_1 = v7.l.a(v6_1.d + 30);
                        if(v1_1 != null) {
                            v7_1 = v7_1.a(v1_1.a, v1_1.b);
                        }

                        v8.c.a(v7_1);
                        ((List)v0).add(v8);
                        long v7_2 = v6_1.b[0];
                        if(v7_2 >= v4) {
                            goto label_58;
                        }

                        v4 = v7_2;
                    }
                }
            }

        label_58:
        }

        this.p = ((List)v0).toArray(new a[0]);
        this.o.f();
        this.o.a(((cj)this));
    }

    private static boolean a(int arg1) {
        boolean v1 = arg1 == cv.R || arg1 == cv.C || arg1 == cv.S || arg1 == cv.T || arg1 == cv.am || arg1 == cv.an || arg1 == cv.ao || arg1 == cv.Q || arg1 == cv.ap || arg1 == cv.aq || arg1 == cv.ar || arg1 == cv.as || arg1 == cv.at || arg1 == cv.O || arg1 == cv.a || arg1 == cv.az ? true : false;
        return v1;
    }

    private static boolean a(fp arg3) {
        arg3.c(8);
        if(arg3.m() == da.a) {
            return 1;
        }

        arg3.d(4);
        do {
            if(arg3.b() <= 0) {
                return 0;
            }
        }
        while(arg3.m() != da.a);

        return 1;
    }

    public int a(cd arg6, ch arg7) {
        while(true) {
            switch(this.g) {
                case 0: {
                    goto label_12;
                }
                case 1: {
                    goto label_8;
                }
                case 2: {
                    goto label_4;
                }
            }

            return this.c(arg6, arg7);
        label_4:
            if(!this.b(arg6, arg7)) {
                continue;
            }

            return 1;
        label_8:
            if(this.b(arg6)) {
                continue;
            }

            return -1;
        label_12:
            if(arg6.c() == 0) {
                this.d();
                continue;
            }

            this.g = 3;
        }
    }

    public void a(ce arg1) {
        this.o = arg1;
    }

    public boolean a() {
        return 1;
    }

    public boolean a(cd arg1) {
        return dc.b(arg1);
    }

    private static boolean b(int arg1) {
        boolean v1 = arg1 == cv.B || arg1 == cv.D || arg1 == cv.E || arg1 == cv.F || arg1 == cv.G || arg1 == cv.P ? true : false;
        return v1;
    }

    private boolean b(cd arg9) {
        int v2 = 8;
        if(this.j == 0) {
            if(!arg9.a(this.d.a, 0, v2, true)) {
                return 0;
            }
            else {
                this.j = v2;
                this.d.c(0);
                this.i = this.d.k();
                this.h = this.d.m();
            }
        }

        if(this.i == 1) {
            arg9.b(this.d.a, v2, v2);
            this.j += v2;
            this.i = this.d.u();
        }

        if(da.b(this.h)) {
            long v2_1 = arg9.c() + this.i - (((long)this.j));
            this.e.add(new com.google.ads.interactivemedia.v3.internal.cv$a(this.h, v2_1));
            if(this.i == (((long)this.j))) {
                this.a(v2_1);
            }
            else {
                this.d();
            }
        }
        else {
            int v0 = 2;
            if(da.a(this.h)) {
                boolean v9 = this.j == v2 ? true : false;
                fe.b(v9);
                v9 = this.i <= 2147483647 ? true : false;
                fe.b(v9);
                this.k = new fp(((int)this.i));
                System.arraycopy(this.d.a, 0, this.k.a, 0, v2);
            }
            else {
                this.k = null;
            }

            this.g = v0;
        }

        return 1;
    }

    private boolean b(cd arg10, ch arg11) {
        int v10;
        long v0 = this.i - (((long)this.j));
        long v2 = arg10.c() + v0;
        boolean v5 = true;
        if(this.k != null) {
            arg10.b(this.k.a, this.j, ((int)v0));
            if(this.h == cv.a) {
                this.q = da.a(this.k);
            }
            else if(!this.e.isEmpty()) {
                this.e.peek().a(new b(this.h, this.k));
            }
            else {
            }

            goto label_37;
        }
        else if(v0 < 262144) {
            arg10.b(((int)v0));
        label_37:
            v10 = 0;
        }
        else {
            arg11.a = arg10.c() + v0;
            v10 = 1;
        }

        this.a(v2);
        if(v10 == 0 || this.g == 3) {
            v5 = false;
        }
        else {
        }

        return v5;
    }

    public long b(long arg7) {
        long v0 = 9223372036854775807L;
        int v2;
        for(v2 = 0; v2 < this.p.length; ++v2) {
            dg v3 = this.p[v2].b;
            int v4 = v3.a(arg7);
            if(v4 == -1) {
                v4 = v3.b(arg7);
            }

            this.p[v2].d = v4;
            long v4_1 = v3.b[v4];
            if(v4_1 < v0) {
                v0 = v4_1;
            }
        }

        return v0;
    }

    public void b() {
        this.e.clear();
        this.j = 0;
        this.m = 0;
        this.n = 0;
        this.g = 0;
    }

    private int c(cd arg13, ch arg14) {
        // Method was not decompiled
    }

    public void c() {
    }

    private void d() {
        this.g = 1;
        this.j = 0;
    }

    private int e() {
        int v0 = -1;
        long v1 = 9223372036854775807L;
        int v3;
        for(v3 = 0; v3 < this.p.length; ++v3) {
            a v4 = this.p[v3];
            int v5 = v4.d;
            if(v5 == v4.b.a) {
            }
            else {
                long v5_1 = v4.b.b[v5];
                if(v5_1 < v1) {
                    v0 = v3;
                    v1 = v5_1;
                }
            }
        }

        return v0;
    }
}

