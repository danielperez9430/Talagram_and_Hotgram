package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;
import java.util.Collections;

final class dx extends du {
    final class a {
        private final ck a;
        private long b;
        private boolean c;
        private int d;
        private long e;
        private boolean f;
        private boolean g;
        private boolean h;
        private boolean i;
        private boolean j;
        private long k;
        private long l;
        private boolean m;

        public a(ck arg1) {
            super();
            this.a = arg1;
        }

        public void a(long arg2, int arg4, int arg5, long arg6) {
            this.g = false;
            this.h = false;
            this.e = arg6;
            this.d = 0;
            this.b = arg2;
            boolean v2 = true;
            if(arg5 >= 32) {
                if(!this.j && (this.i)) {
                    this.a(arg4);
                    this.i = false;
                }

                if(arg5 > 34) {
                    goto label_21;
                }

                this.h = this.j ^ 1;
                this.j = true;
            }

        label_21:
            boolean v3 = arg5 < 16 || arg5 > 21 ? false : true;
            this.c = v3;
            if(!this.c) {
                if(arg5 <= 9) {
                }
                else {
                    v2 = false;
                }
            }

            this.f = v2;
        }

        public void a(byte[] arg3, int arg4, int arg5) {
            if(this.f) {
                int v0 = arg4 + 2 - this.d;
                if(v0 < arg5) {
                    boolean v3 = (arg3[v0] & 128) != 0 ? true : false;
                    this.g = v3;
                    this.f = false;
                }
                else {
                    this.d += arg5 - arg4;
                }
            }
        }

        public void a(long arg3, int arg5) {
            if(!this.j || !this.g) {
                if(!this.h && !this.g) {
                    return;
                }

                if(this.i) {
                    this.a(arg5 + (((int)(arg3 - this.b))));
                }

                this.k = this.b;
                this.l = this.e;
                this.i = true;
                this.m = this.c;
            }
            else {
                this.m = this.c;
                this.j = false;
            }
        }

        public void a() {
            this.f = false;
            this.g = false;
            this.h = false;
            this.i = false;
            this.j = false;
        }

        private void a(int arg8) {
            this.a.a(this.l, this.m, ((int)(this.b - this.k)), arg8, null);
        }
    }

    private boolean b;
    private final ed c;
    private final boolean[] d;
    private final ea e;
    private final ea f;
    private final ea g;
    private final ea h;
    private final ea i;
    private final a j;
    private long k;
    private long l;
    private final fp m;

    public dx(ck arg3, ed arg4) {
        super(arg3);
        this.c = arg4;
        this.d = new boolean[3];
        this.e = new ea(32, 128);
        this.f = new ea(33, 128);
        this.g = new ea(34, 128);
        this.h = new ea(39, 128);
        this.i = new ea(40, 128);
        this.j = new a(arg3);
        this.m = new fp();
    }

    private static bj a(ea arg21, ea arg22, ea arg23) {
        float v20;
        byte[] v3 = new byte[arg21.b + arg22.b + arg23.b];
        int v6 = 0;
        System.arraycopy(arg21.a, 0, v3, 0, arg21.b);
        System.arraycopy(arg22.a, 0, v3, arg21.b, arg22.b);
        System.arraycopy(arg23.a, 0, v3, arg21.b + arg22.b, arg23.b);
        fn.a(arg22.a, arg22.b);
        fo v0 = new fo(arg22.a);
        v0.b(44);
        int v1 = 3;
        int v2 = v0.c(v1);
        v0.b(1);
        v0.b(88);
        int v5 = 8;
        v0.b(v5);
        int v7 = 0;
        int v8 = 0;
        while(v7 < v2) {
            if(v0.b()) {
                v8 += 89;
            }

            if(v0.b()) {
                v8 += 8;
            }

            ++v7;
        }

        v0.b(v8);
        v7 = 2;
        if(v2 > 0) {
            v0.b((8 - v2) * 2);
        }

        v0.d();
        v8 = v0.d();
        if(v8 == v1) {
            v0.b(1);
        }

        v1 = v0.d();
        int v9 = v0.d();
        if(v0.b()) {
            int v10 = v0.d();
            int v11 = v0.d();
            int v12 = v0.d();
            int v13 = v0.d();
            int v14 = v8 == 1 || v8 == v7 ? 2 : 1;
            v8 = v8 == 1 ? 2 : 1;
            v1 -= v14 * (v10 + v11);
            v9 -= v8 * (v12 + v13);
        }

        int v16 = v1;
        int v17 = v9;
        v0.d();
        v0.d();
        v1 = v0.d();
        for(v8 = v0.b() ? 0 : v2; v8 <= v2; ++v8) {
            v0.d();
            v0.d();
            v0.d();
        }

        v0.d();
        v0.d();
        v0.d();
        v0.d();
        v0.d();
        v0.d();
        if((v0.b()) && (v0.b())) {
            dx.a(v0);
        }

        v0.b(v7);
        if(v0.b()) {
            v0.b(v5);
            v0.d();
            v0.d();
            v0.b(1);
        }

        dx.b(v0);
        if(v0.b()) {
            while(v6 < v0.d()) {
                v0.b(v1 + 5);
                ++v6;
            }
        }

        v0.b(v7);
        float v1_1 = 1f;
        if(!v0.b() || !v0.b()) {
        label_163:
            v20 = 1f;
        }
        else {
            v2 = v0.c(v5);
            if(v2 == 255) {
                int v4 = v0.c(16);
                int v0_1 = v0.c(16);
                if(v4 != 0 && v0_1 != 0) {
                    v1_1 = (((float)v4)) / (((float)v0_1));
                }

                v20 = v1_1;
            }
            else {
                if(v2 < fn.b.length) {
                    v20 = fn.b[v2];
                    goto label_164;
                }

                StringBuilder v5_1 = new StringBuilder(46);
                v5_1.append("Unexpected aspect_ratio_idc value: ");
                v5_1.append(v2);
                Log.w("H265Reader", v5_1.toString());
                goto label_163;
            }
        }

    label_164:
        return bj.a(null, "video/hevc", -1, -1, -1, v16, v17, Collections.singletonList(v3), -1, v20);
    }

    private static void a(fo arg7) {
        int v4;
        int v1;
        for(v1 = 0; true; ++v1) {
            int v2 = 4;
            if(v1 >= v2) {
                return;
            }

            int v3;
            for(v3 = 0; v3 < 6; v3 += v4) {
                if(!arg7.b()) {
                    arg7.d();
                }
                else {
                    v4 = Math.min(64, 1 << (v1 << 1) + v2);
                    if(v1 > 1) {
                        arg7.e();
                    }

                    int v6;
                    for(v6 = 0; v6 < v4; ++v6) {
                        arg7.e();
                    }
                }

                v4 = 3;
                if(v1 == v4) {
                }
                else {
                    v4 = 1;
                }
            }
        }
    }

    private void a(long arg9, int arg11, int arg12, long arg13) {
        if(this.b) {
            this.j.a(arg9, arg11, arg12, arg13);
        }
        else {
            this.e.a(arg12);
            this.f.a(arg12);
            this.g.a(arg12);
        }

        this.h.a(arg12);
        this.i.a(arg12);
    }

    private void a(byte[] arg2, int arg3, int arg4) {
        if(this.b) {
            this.j.a(arg2, arg3, arg4);
        }
        else {
            this.e.a(arg2, arg3, arg4);
            this.f.a(arg2, arg3, arg4);
            this.g.a(arg2, arg3, arg4);
        }

        this.h.a(arg2, arg3, arg4);
        this.i.a(arg2, arg3, arg4);
    }

    public void a() {
        fn.a(this.d);
        this.e.a();
        this.f.a();
        this.g.a();
        this.h.a();
        this.i.a();
        this.j.a();
        this.k = 0;
    }

    public void a(long arg1, boolean arg3) {
        this.l = arg1;
    }

    public void a(fp arg17) {
        dx v7 = this;
        fp v8 = arg17;
    label_2:
        if(arg17.b() > 0) {
            int v0 = arg17.d();
            int v9 = arg17.c();
            byte[] v10 = v8.a;
            v7.k += ((long)arg17.b());
            v7.a.a(v8, arg17.b());
            while(true) {
                if(v0 >= v9) {
                    goto label_2;
                }

                int v11 = fn.a(v10, v0, v9, v7.d);
                if(v11 == v9) {
                    v7.a(v10, v0, v9);
                    return;
                }

                int v12 = fn.c(v10, v11);
                int v1 = v11 - v0;
                if(v1 > 0) {
                    v7.a(v10, v0, v11);
                }

                int v13 = v9 - v11;
                long v14 = v7.k - (((long)v13));
                int v4 = v1 < 0 ? -v1 : 0;
                this.b(v14, v13, v4, v7.l);
                this.a(v14, v13, v12, v7.l);
                v0 = v11 + 3;
            }
        }
    }

    private static void b(fo arg9) {
        int v0 = arg9.d();
        int v2 = 0;
        boolean v3 = false;
        int v4 = 0;
        while(v2 < v0) {
            if(v2 != 0) {
                v3 = arg9.b();
            }

            if(v3) {
                arg9.b(1);
                arg9.d();
                int v6;
                for(v6 = 0; v6 <= v4; ++v6) {
                    if(arg9.b()) {
                        arg9.b(1);
                    }
                }
            }
            else {
                v4 = arg9.d();
                v6 = arg9.d();
                int v7 = v4 + v6;
                int v8;
                for(v8 = 0; v8 < v4; ++v8) {
                    arg9.d();
                    arg9.b(1);
                }

                for(v4 = 0; v4 < v6; ++v4) {
                    arg9.d();
                    arg9.b(1);
                }

                v4 = v7;
            }

            ++v2;
        }
    }

    private void b(long arg2, int arg4, int arg5, long arg6) {
        if(this.b) {
            this.j.a(arg2, arg4);
        }
        else {
            this.e.b(arg5);
            this.f.b(arg5);
            this.g.b(arg5);
            if((this.e.b()) && (this.f.b()) && (this.g.b())) {
                this.a.a(dx.a(this.e, this.f, this.g));
                this.b = true;
            }
        }

        int v3 = 5;
        if(this.h.b(arg5)) {
            this.m.a(this.h.a, fn.a(this.h.a, this.h.b));
            this.m.d(v3);
            this.c.a(arg6, this.m);
        }

        if(this.i.b(arg5)) {
            this.m.a(this.i.a, fn.a(this.i.a, this.i.b));
            this.m.d(v3);
            this.c.a(arg6, this.m);
        }
    }

    public void b() {
    }
}

