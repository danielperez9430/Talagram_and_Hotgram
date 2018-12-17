package com.google.ads.interactivemedia.v3.internal;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class dw extends du {
    class com.google.ads.interactivemedia.v3.internal.dw$1 {
    }

    final class a {
        final class com.google.ads.interactivemedia.v3.internal.dw$a$a {
            private boolean a;
            private boolean b;
            private b c;
            private int d;
            private int e;
            private int f;
            private int g;
            private boolean h;
            private boolean i;
            private boolean j;
            private boolean k;
            private int l;
            private int m;
            private int n;
            private int o;
            private int p;

            com.google.ads.interactivemedia.v3.internal.dw$a$a(com.google.ads.interactivemedia.v3.internal.dw$1 arg1) {
                this();
            }

            private com.google.ads.interactivemedia.v3.internal.dw$a$a() {
                super();
            }

            static boolean a(com.google.ads.interactivemedia.v3.internal.dw$a$a arg0, com.google.ads.interactivemedia.v3.internal.dw$a$a arg1) {
                return arg0.a(arg1);
            }

            public void a() {
                this.b = false;
                this.a = false;
            }

            public void a(int arg1) {
                this.e = arg1;
                this.b = true;
            }

            public void a(b arg1, int arg2, int arg3, int arg4, int arg5, boolean arg6, boolean arg7, boolean arg8, boolean arg9, int arg10, int arg11, int arg12, int arg13, int arg14) {
                this.c = arg1;
                this.d = arg2;
                this.e = arg3;
                this.f = arg4;
                this.g = arg5;
                this.h = arg6;
                this.i = arg7;
                this.j = arg8;
                this.k = arg9;
                this.l = arg10;
                this.m = arg11;
                this.n = arg12;
                this.o = arg13;
                this.p = arg14;
                this.a = true;
                this.b = true;
            }

            private boolean a(com.google.ads.interactivemedia.v3.internal.dw$a$a arg4) {
                boolean v1 = true;
                if(!this.a) {
                label_63:
                    v1 = false;
                }
                else if((arg4.a) && this.f == arg4.f && this.g == arg4.g && this.h == arg4.h) {
                    if((this.i) && (arg4.i) && this.j != arg4.j) {
                        return v1;
                    }

                    if(this.d != arg4.d) {
                        if(this.d == 0) {
                        }
                        else if(arg4.d != 0) {
                            goto label_28;
                        }

                        return v1;
                    }

                label_28:
                    if(this.c.h == 0 && arg4.c.h == 0) {
                        if(this.m != arg4.m) {
                        }
                        else if(this.n == arg4.n) {
                            goto label_40;
                        }

                        return v1;
                    }

                label_40:
                    if(this.c.h == 1 && arg4.c.h == 1) {
                        if(this.o != arg4.o) {
                        }
                        else if(this.p == arg4.p) {
                            goto label_52;
                        }

                        return v1;
                    }

                label_52:
                    if(this.k != arg4.k) {
                        return v1;
                    }

                    if(!this.k) {
                        goto label_63;
                    }

                    if(!arg4.k) {
                        goto label_63;
                    }

                    if(this.l == arg4.l) {
                        goto label_63;
                    }
                }

                return v1;
            }

            public boolean b() {
                boolean v0;
                if(this.b) {
                    if(this.e != 7 && this.e != 2) {
                        goto label_10;
                    }

                    v0 = true;
                }
                else {
                label_10:
                    v0 = false;
                }

                return v0;
            }
        }

        private final ck a;
        private final boolean b;
        private final boolean c;
        private final fo d;
        private final SparseArray e;
        private final SparseArray f;
        private byte[] g;
        private int h;
        private int i;
        private long j;
        private boolean k;
        private long l;
        private com.google.ads.interactivemedia.v3.internal.dw$a$a m;
        private com.google.ads.interactivemedia.v3.internal.dw$a$a n;
        private boolean o;
        private long p;
        private long q;
        private boolean r;

        public a(ck arg1, boolean arg2, boolean arg3) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
            this.e = new SparseArray();
            this.f = new SparseArray();
            this.m = new com.google.ads.interactivemedia.v3.internal.dw$a$a(null);
            this.n = new com.google.ads.interactivemedia.v3.internal.dw$a$a(null);
            this.d = new fo();
            this.g = new byte[128];
            this.b();
        }

        public boolean a() {
            return this.c;
        }

        public void a(b arg3) {
            this.e.append(arg3.a, arg3);
        }

        public void a(com.google.ads.interactivemedia.v3.internal.fn$a arg3) {
            this.f.append(arg3.a, arg3);
        }

        public void a(long arg6, int arg8) {
            int v1 = 0;
            if(this.i == 9 || (this.c) && (com.google.ads.interactivemedia.v3.internal.dw$a$a.a(this.n, this.m))) {
                if(this.o) {
                    this.a(arg8 + (((int)(arg6 - this.j))));
                }

                this.p = this.j;
                this.q = this.l;
                this.r = false;
                this.o = true;
            }

            boolean v6 = this.r;
            if(this.i == 5 || (this.b) && this.i == 1 && (this.n.b())) {
                v1 = 1;
            }

            this.r = (((int)v6)) | v1;
        }

        public void a(long arg1, int arg3, long arg4) {
            this.i = arg3;
            this.l = arg4;
            this.j = arg1;
            if((this.b) && this.i == 1 || (this.c) && (this.i == 5 || this.i == 1 || this.i == 2)) {
                com.google.ads.interactivemedia.v3.internal.dw$a$a v1 = this.m;
                this.m = this.n;
                this.n = v1;
                this.n.a();
                this.h = 0;
                this.k = true;
            }
        }

        public void a(byte[] arg22, int arg23, int arg24) {
            int v17;
            int v18;
            int v19;
            int v20;
            int v16;
            boolean v13;
            boolean v14;
            boolean v12;
            a v0 = this;
            int v1 = arg23;
            if(!v0.k) {
                return;
            }

            int v2 = arg24 - v1;
            int v5 = 2;
            if(v0.g.length < v0.h + v2) {
                v0.g = Arrays.copyOf(v0.g, (v0.h + v2) * 2);
            }

            System.arraycopy(arg22, v1, v0.g, v0.h, v2);
            v0.h += v2;
            v0.d.a(v0.g, v0.h);
            if(v0.d.a() < 8) {
                return;
            }

            v0.d.b(1);
            int v8 = v0.d.c(v5);
            int v3 = 5;
            v0.d.b(v3);
            if(!v0.d.c()) {
                return;
            }

            v0.d.d();
            if(!v0.d.c()) {
                return;
            }

            int v9 = v0.d.d();
            if(!v0.c) {
                v0.k = false;
                v0.n.a(v9);
                return;
            }

            if(!v0.d.c()) {
                return;
            }

            int v11 = v0.d.d();
            if(v0.f.indexOfKey(v11) < 0) {
                v0.k = false;
                return;
            }

            Object v1_1 = v0.f.get(v11);
            Object v7 = v0.e.get(((com.google.ads.interactivemedia.v3.internal.fn$a)v1_1).b);
            if(((b)v7).e) {
                if(v0.d.a() < v5) {
                    return;
                }
                else {
                    v0.d.b(v5);
                }
            }

            if(v0.d.a() < ((b)v7).g) {
                return;
            }

            int v10 = v0.d.c(((b)v7).g);
            if(((b)v7).f) {
                v12 = false;
            label_116:
                v13 = false;
                v14 = false;
            }
            else if(v0.d.a() < 1) {
                return;
            }
            else {
                boolean v5_1 = v0.d.b();
                if(!v5_1) {
                    v12 = v5_1;
                    goto label_116;
                }
                else if(v0.d.a() < 1) {
                    return;
                }
                else {
                    v12 = v5_1;
                    v14 = v0.d.b();
                    v13 = true;
                }
            }

            boolean v15 = v0.i == v3 ? true : false;
            if(!v15) {
                v16 = 0;
            }
            else if(!v0.d.c()) {
                return;
            }
            else {
                v16 = v0.d.d();
            }

            if(((b)v7).h != 0) {
                if(((b)v7).h == 1 && !((b)v7).j) {
                    if(!v0.d.c()) {
                        return;
                    }
                    else {
                        v2 = v0.d.e();
                        if((((com.google.ads.interactivemedia.v3.internal.fn$a)v1_1).c) && !v12) {
                            if(!v0.d.c()) {
                                return;
                            }
                            else {
                                v20 = v0.d.e();
                                v19 = v2;
                                v17 = 0;
                                v18 = 0;
                                goto label_189;
                            }
                        }

                        v19 = v2;
                        v17 = 0;
                        v18 = 0;
                        goto label_188;
                    }
                }

                v17 = 0;
            label_186:
                v18 = 0;
            label_187:
                v19 = 0;
            label_188:
                v20 = 0;
            }
            else if(v0.d.a() < ((b)v7).i) {
                return;
            }
            else {
                v2 = v0.d.c(((b)v7).i);
                if((((com.google.ads.interactivemedia.v3.internal.fn$a)v1_1).c) && !v12) {
                    if(!v0.d.c()) {
                        return;
                    }
                    else {
                        v18 = v0.d.e();
                        v17 = v2;
                        goto label_187;
                    }
                }

                v17 = v2;
                goto label_186;
            }

        label_189:
            v0.n.a(((b)v7), v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20);
            v0.k = false;
        }

        private void a(int arg8) {
            this.a.a(this.q, this.r, ((int)(this.j - this.p)), arg8, null);
        }

        public void b() {
            this.k = false;
            this.o = false;
            this.n.a();
        }
    }

    private boolean b;
    private final ed c;
    private final boolean[] d;
    private final a e;
    private final ea f;
    private final ea g;
    private final ea h;
    private long i;
    private long j;
    private final fp k;

    public dw(ck arg1, ed arg2, boolean arg3, boolean arg4) {
        super(arg1);
        this.c = arg2;
        this.d = new boolean[3];
        this.e = new a(arg1, arg3, arg4);
        this.f = new ea(7, 128);
        this.g = new ea(8, 128);
        this.h = new ea(6, 128);
        this.k = new fp();
    }

    private static fo a(ea arg2) {
        fo v1 = new fo(arg2.a, fn.a(arg2.a, arg2.b));
        v1.b(32);
        return v1;
    }

    private void a(long arg17, int arg19, int arg20, long arg21) {
        ea v2_1;
        dw v0 = this;
        int v1 = arg20;
        if(!v0.b || (v0.e.a())) {
            v0.f.b(v1);
            v0.g.b(v1);
            if(!v0.b) {
                if(!v0.f.b()) {
                    goto label_81;
                }
                else if(v0.g.b()) {
                    ArrayList v11 = new ArrayList();
                    ((List)v11).add(Arrays.copyOf(v0.f.a, v0.f.b));
                    ((List)v11).add(Arrays.copyOf(v0.g.a, v0.g.b));
                    b v2 = fn.a(dw.a(v0.f));
                    com.google.ads.interactivemedia.v3.internal.fn$a v14 = fn.b(dw.a(v0.g));
                    v0.a.a(bj.a(null, "video/avc", -1, -1, -1, v2.b, v2.c, ((List)v11), -1, v2.d));
                    v0.b = true;
                    v0.e.a(v2);
                    v0.e.a(v14);
                    v0.f.a();
                    goto label_59;
                }
                else {
                    goto label_81;
                }
            }
            else if(v0.f.b()) {
                v0.e.a(fn.a(dw.a(v0.f)));
                v2_1 = v0.f;
            }
            else if(v0.g.b()) {
                v0.e.a(fn.b(dw.a(v0.g)));
            label_59:
                v2_1 = v0.g;
            }
            else {
                goto label_81;
            }

            v2_1.a();
        }

    label_81:
        if(v0.h.b(v1)) {
            v0.k.a(v0.h.a, fn.a(v0.h.a, v0.h.b));
            v0.k.c(4);
            v0.c.a(arg21, v0.k);
        }

        v0.e.a(arg17, arg19);
    }

    private void a(long arg8, int arg10, long arg11) {
        if(!this.b || (this.e.a())) {
            this.f.a(arg10);
            this.g.a(arg10);
        }

        this.h.a(arg10);
        this.e.a(arg8, arg10, arg11);
    }

    private void a(byte[] arg2, int arg3, int arg4) {
        if(!this.b || (this.e.a())) {
            this.f.a(arg2, arg3, arg4);
            this.g.a(arg2, arg3, arg4);
        }

        this.h.a(arg2, arg3, arg4);
        this.e.a(arg2, arg3, arg4);
    }

    public void a() {
        fn.a(this.d);
        this.f.a();
        this.g.a();
        this.h.a();
        this.e.b();
        this.i = 0;
    }

    public void a(long arg1, boolean arg3) {
        this.j = arg1;
    }

    public void a(fp arg15) {
        if(arg15.b() > 0) {
            int v0 = arg15.d();
            int v1 = arg15.c();
            byte[] v2 = arg15.a;
            this.i += ((long)arg15.b());
            this.a.a(arg15, arg15.b());
            while(true) {
                int v15 = fn.a(v2, v0, v1, this.d);
                if(v15 == v1) {
                    break;
                }

                int v6 = fn.b(v2, v15);
                int v3 = v15 - v0;
                if(v3 > 0) {
                    this.a(v2, v0, v15);
                }

                int v10 = v1 - v15;
                long v4 = this.i - (((long)v10));
                int v11 = v3 < 0 ? -v3 : 0;
                this.a(v4, v10, v11, this.j);
                this.a(v4, v6, this.j);
                v0 = v15 + 3;
            }

            this.a(v2, v0, v1);
            return;
        }
    }

    public void b() {
    }
}

