package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;
import android.util.Pair;
import java.util.Arrays;
import java.util.Collections;

final class ds extends du {
    private static final byte[] b;
    private final fo c;
    private final fp d;
    private final ck e;
    private int f;
    private int g;
    private int h;
    private boolean i;
    private boolean j;
    private long k;
    private int l;
    private long m;
    private ck n;
    private long o;

    static {
        ds.b = new byte[]{73, 68, 51};
    }

    public ds(ck arg2, ck arg3) {
        super(arg2);
        this.e = arg3;
        arg3.a(bj.a());
        this.c = new fo(new byte[7]);
        this.d = new fp(Arrays.copyOf(ds.b, 10));
        this.c();
    }

    public void a(long arg1, boolean arg3) {
        this.m = arg1;
    }

    public void a(fp arg3) {
        while(arg3.b() > 0) {
            switch(this.f) {
                case 0: {
                    goto label_25;
                }
                case 1: {
                    goto label_18;
                }
                case 2: {
                    goto label_7;
                }
                case 3: {
                    goto label_5;
                }
            }

            continue;
        label_18:
            if(!this.a(arg3, this.d.a, 10)) {
                continue;
            }

            this.f();
            continue;
        label_5:
            this.c(arg3);
            continue;
        label_7:
            int v0 = this.i ? 7 : 5;
            if(!this.a(arg3, this.c.a, v0)) {
                continue;
            }

            this.g();
            continue;
        label_25:
            this.b(arg3);
        }
    }

    public void a() {
        this.c();
    }

    private void a(ck arg2, long arg3, int arg5, int arg6) {
        this.f = 3;
        this.g = arg5;
        this.n = arg2;
        this.o = arg3;
        this.l = arg6;
    }

    private boolean a(fp arg3, byte[] arg4, int arg5) {
        int v0 = Math.min(arg3.b(), arg5 - this.g);
        arg3.a(arg4, this.g, v0);
        this.g += v0;
        boolean v3 = this.g == arg5 ? true : false;
        return v3;
    }

    private void b(fp arg8) {
        byte[] v0 = arg8.a;
        int v1 = arg8.d();
        int v2 = arg8.c();
        while(v1 < v2) {
            int v3 = v1 + 1;
            int v4 = 255;
            v1 = v0[v1] & v4;
            int v6 = 512;
            if(this.h != v6 || v1 < 240 || v1 == v4) {
                v1 |= this.h;
                if(v1 == 329) {
                    goto label_45;
                }
                else if(v1 == 511) {
                    goto label_43;
                }
                else if(v1 == 836) {
                    goto label_41;
                }
                else if(v1 != 1075) {
                    v4 = 256;
                    if(this.h != v4) {
                        this.h = v4;
                        --v3;
                    }
                }
                else {
                    this.d();
                    goto label_21;
                }

                goto label_47;
            }
            else {
                boolean v0_1 = true;
                if((v1 & 1) == 0) {
                }
                else {
                    v0_1 = false;
                }

                this.i = v0_1;
                this.e();
            }

        label_21:
            arg8.c(v3);
            return;
        label_41:
            v1 = 1024;
            goto label_46;
        label_43:
            this.h = v6;
            goto label_47;
        label_45:
            v1 = 768;
        label_46:
            this.h = v1;
        label_47:
            v1 = v3;
        }

        arg8.c(v1);
    }

    public void b() {
    }

    private void c() {
        this.f = 0;
        this.g = 0;
        this.h = 256;
    }

    private void c(fp arg9) {
        int v0 = Math.min(arg9.b(), this.l - this.g);
        this.n.a(arg9, v0);
        this.g += v0;
        if(this.g == this.l) {
            this.n.a(this.m, 1, this.l, 0, null);
            this.m += this.o;
            this.c();
        }
    }

    private void d() {
        this.f = 1;
        this.g = ds.b.length;
        this.l = 0;
        this.d.c(0);
    }

    private void e() {
        this.f = 2;
        this.g = 0;
    }

    private void f() {
        this.e.a(this.d, 10);
        this.d.c(6);
        this.a(this.e, 0, 10, this.d.r() + 10);
    }

    private void g() {
        int v0;
        this.c.a(0);
        int v1 = 4;
        int v2 = 2;
        if(!this.j) {
            v0 = this.c.c(v2) + 1;
            if(v0 != v2) {
                StringBuilder v6 = new StringBuilder(61);
                v6.append("Detected audio object type: ");
                v6.append(v0);
                v6.append(", but assuming AAC LC.");
                Log.w("AdtsReader", v6.toString());
                v0 = 2;
            }

            int v4 = this.c.c(v1);
            this.c.b(1);
            byte[] v0_1 = ff.a(v0, v4, this.c.c(3));
            Pair v4_1 = ff.a(v0_1);
            bj v0_2 = bj.a(null, "audio/mp4a-latm", -1, -1, -1, v4_1.second.intValue(), v4_1.first.intValue(), Collections.singletonList(v0_1), null);
            this.k = 1024000000 / (((long)v0_2.r));
            this.a.a(v0_2);
            this.j = true;
        }
        else {
            this.c.b(10);
        }

        this.c.b(v1);
        v0 = this.c.c(13) - v2 - 5;
        if(this.i) {
            v0 += -2;
        }

        this.a(this.a, this.k, 0, v0);
    }
}

