package com.google.ads.interactivemedia.v3.internal;

import java.util.ArrayList;
import java.util.List;

final class cp extends co {
    final class a {
        public final List a;
        public final int b;
        public final float c;
        public final int d;
        public final int e;

        public a(List arg1, int arg2, int arg3, int arg4, float arg5) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg5;
            this.d = arg3;
            this.e = arg4;
        }
    }

    private final fp b;
    private final fp c;
    private int d;
    private boolean e;
    private int f;

    public cp(ck arg2) {
        super(arg2);
        this.b = new fp(fn.a);
        this.c = new fp(4);
    }

    protected void a(fp arg12, long arg13) {
        int v0 = arg12.f();
        long v4 = arg13 + (((long)arg12.j())) * 1000;
        if(v0 == 0 && !this.e) {
            fp v0_1 = new fp(new byte[arg12.b()]);
            arg12.a(v0_1.a, 0, arg12.b());
            a v12 = this.b(v0_1);
            this.d = v12.b;
            this.a.a(bj.a(null, "video/avc", -1, -1, this.a(), v12.d, v12.e, v12.a, -1, v12.c));
            this.e = true;
        }
        else if(v0 == 1) {
            byte[] v0_2 = this.c.a;
            v0_2[0] = 0;
            v0_2[1] = 0;
            v0_2[2] = 0;
            int v1 = 4;
            v0 = 4 - this.d;
            int v7;
            for(v7 = 0; arg12.b() > 0; v7 = v7 + 4 + v2) {
                arg12.a(this.c.a, v0, this.d);
                this.c.c(0);
                int v2 = this.c.s();
                this.b.c(0);
                this.a.a(this.b, v1);
                this.a.a(arg12, v2);
            }

            ck v3 = this.a;
            int v6 = this.f == 1 ? 1 : 0;
            v3.a(v4, v6, v7, 0, null);
        }
    }

    protected boolean a(fp arg4) {
        int v4 = arg4.f();
        int v0 = v4 >> 4 & 15;
        v4 &= 15;
        if(v4 == 7) {
            this.f = v0;
            boolean v4_1 = v0 != 5 ? true : false;
            return v4_1;
        }

        StringBuilder v2 = new StringBuilder(39);
        v2.append("Video format not supported: ");
        v2.append(v4);
        throw new com.google.ads.interactivemedia.v3.internal.co$a(v2.toString());
    }

    private a b(fp arg10) {
        int v7;
        int v6;
        float v8;
        arg10.c(4);
        boolean v2 = true;
        int v5 = (arg10.f() & 3) + 1;
        if(v5 != 3) {
        }
        else {
            v2 = false;
        }

        fe.b(v2);
        ArrayList v4 = new ArrayList();
        int v1 = arg10.f() & 31;
        int v2_1;
        for(v2_1 = 0; v2_1 < v1; ++v2_1) {
            ((List)v4).add(fn.a(arg10));
        }

        v2_1 = arg10.f();
        int v3;
        for(v3 = 0; v3 < v2_1; ++v3) {
            ((List)v4).add(fn.a(arg10));
        }

        if(v1 > 0) {
            fo v10 = new fo(((List)v4).get(0));
            v10.a((v5 + 1) * 8);
            b v10_1 = fn.a(v10);
            int v0 = v10_1.b;
            v1 = v10_1.c;
            v8 = v10_1.d;
            v6 = v0;
            v7 = v1;
        }
        else {
            v6 = -1;
            v7 = -1;
            v8 = 1f;
        }

        return new a(((List)v4), v5, v6, v7, v8);
    }
}

