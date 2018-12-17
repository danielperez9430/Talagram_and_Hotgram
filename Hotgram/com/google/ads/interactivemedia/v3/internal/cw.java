package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;
import android.util.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class cw {
    final class a {
        public final List a;
        public final int b;
        public final float c;

        public a(List arg1, int arg2, float arg3) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
        }
    }

    final class b {
        public final int a;
        public int b;
        public int c;
        public long d;
        private final boolean e;
        private final fp f;
        private final fp g;
        private int h;
        private int i;

        public b(fp arg1, fp arg2, boolean arg3) {
            super();
            this.g = arg1;
            this.f = arg2;
            this.e = arg3;
            arg2.c(12);
            this.a = arg2.s();
            arg1.c(12);
            this.i = arg1.s();
            boolean v2 = true;
            if(arg1.m() == 1) {
            }
            else {
                v2 = false;
            }

            fe.b(v2, "first_chunk must be 1");
            this.b = -1;
        }

        public boolean a() {
            int v0 = this.b + 1;
            this.b = v0;
            if(v0 == this.a) {
                return 0;
            }

            long v2 = this.e ? this.f.u() : this.f.k();
            this.d = v2;
            if(this.b == this.h) {
                this.c = this.g.s();
                this.g.d(4);
                v0 = this.i - 1;
                this.i = v0;
                v0 = v0 > 0 ? this.g.s() - 1 : -1;
                this.h = v0;
            }

            return 1;
        }
    }

    interface c {
        int a();

        int b();

        boolean c();
    }

    final class d {
        public final de[] a;
        public bj b;
        public int c;

        public d(int arg1) {
            super();
            this.a = new de[arg1];
            this.c = -1;
        }
    }

    final class e implements c {
        private final int a;
        private final int b;
        private final fp c;

        public e(com.google.ads.interactivemedia.v3.internal.cv$b arg2) {
            super();
            this.c = arg2.aP;
            this.c.c(12);
            this.a = this.c.s();
            this.b = this.c.s();
        }

        public int a() {
            return this.b;
        }

        public int b() {
            int v0 = this.a == 0 ? this.c.s() : this.a;
            return v0;
        }

        public boolean c() {
            boolean v0 = this.a != 0 ? true : false;
            return v0;
        }
    }

    final class f implements c {
        private final fp a;
        private final int b;
        private final int c;
        private int d;
        private int e;

        public f(com.google.ads.interactivemedia.v3.internal.cv$b arg2) {
            super();
            this.a = arg2.aP;
            this.a.c(12);
            this.c = this.a.s() & 255;
            this.b = this.a.s();
        }

        public int a() {
            return this.b;
        }

        public int b() {
            if(this.c == 8) {
                return this.a.f();
            }

            if(this.c == 16) {
                return this.a.g();
            }

            int v0 = this.d;
            this.d = v0 + 1;
            if(v0 % 2 == 0) {
                this.e = this.a.f();
                return (this.e & 240) >> 4;
            }

            return this.e & 15;
        }

        public boolean c() {
            return 0;
        }
    }

    final class g {
        private final int a;
        private final long b;
        private final int c;

        public g(int arg1, long arg2, int arg4) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg4;
        }

        static long a(g arg2) {
            return arg2.b;
        }

        static int b(g arg0) {
            return arg0.a;
        }

        static int c(g arg0) {
            return arg0.c;
        }
    }

    private static final int a;

    static {
        cw.a = ft.c("cenc");
    }

    private static int a(fp arg4, int arg5, int arg6) {
        int v0;
        for(v0 = arg4.d(); v0 - arg5 < arg6; v0 += v1) {
            arg4.c(v0);
            int v1 = arg4.m();
            boolean v2 = v1 > 0 ? true : false;
            fe.a(v2, "childAtomSize should be positive");
            if(arg4.m() == cv.J) {
                return v0;
            }
        }

        return -1;
    }

    private static int a(fp arg4, int arg5, int arg6, d arg7, int arg8) {
        int v0;
        for(v0 = arg4.d(); true; v0 += v1) {
            boolean v2 = false;
            if(v0 - arg5 >= arg6) {
                return 0;
            }

            arg4.c(v0);
            int v1 = arg4.m();
            if(v1 > 0) {
                v2 = true;
            }

            fe.a(v2, "childAtomSize should be positive");
            if(arg4.m() == cv.V) {
                Pair v2_1 = cw.b(arg4, v0, v1);
                if(v2_1 != null) {
                    arg7.a[arg8] = v2_1.second;
                    return v2_1.first.intValue();
                }
            }
        }

        return 0;
    }

    private static Pair a(com.google.ads.interactivemedia.v3.internal.cv$a arg8) {
        if(arg8 != null) {
            com.google.ads.interactivemedia.v3.internal.cv$b v8 = arg8.d(cv.Q);
            if(v8 == null) {
            }
            else {
                fp v8_1 = v8.aP;
                v8_1.c(8);
                int v0 = cv.a(v8_1.m());
                int v1 = v8_1.s();
                long[] v2 = new long[v1];
                long[] v3 = new long[v1];
                int v4 = 0;
                while(true) {
                    if(v4 < v1) {
                        long v6 = v0 == 1 ? v8_1.u() : v8_1.k();
                        v2[v4] = v6;
                        v6 = v0 == 1 ? v8_1.o() : ((long)v8_1.m());
                        v3[v4] = v6;
                        if(v8_1.i() != 1) {
                            break;
                        }

                        v8_1.d(2);
                        ++v4;
                        continue;
                    }
                    else {
                        goto label_37;
                    }
                }

                throw new IllegalArgumentException("Unsupported media rate.");
            label_37:
                return Pair.create(v2, v3);
            }
        }

        return Pair.create(null, null);
    }

    public static cg a(com.google.ads.interactivemedia.v3.internal.cv$b arg4, boolean arg5) {
        cg v0 = null;
        if(arg5) {
            return v0;
        }

        fp v4 = arg4.aP;
        int v5 = 8;
        v4.c(v5);
        while(v4.b() >= v5) {
            int v1 = v4.m();
            if(v4.m() == cv.aA) {
                v4.c(v4.d() - v5);
                v4.b(v4.d() + v1);
                return cw.a(v4);
            }

            v4.d(v1 - 8);
        }

        return v0;
    }

    private static cg a(fp arg4) {
        arg4.d(12);
        fp v0 = new fp();
        while(true) {
            int v2 = 8;
            if(arg4.b() < v2) {
                return null;
            }

            int v1 = arg4.m() - v2;
            if(arg4.m() == cv.aB) {
                v0.a(arg4.a, arg4.d() + v1);
                v0.c(arg4.d());
                cg v2_1 = cw.b(v0);
                if(v2_1 != null) {
                    return v2_1;
                }
            }

            arg4.d(v1);
        }

        return null;
    }

    private static a a(fp arg7, int arg8) {
        arg7.c(arg8 + 12);
        arg8 = (arg7.f() & 3) + 1;
        if(arg8 != 3) {
            ArrayList v0 = new ArrayList();
            float v1 = 1f;
            int v2 = arg7.f() & 31;
            int v4;
            for(v4 = 0; v4 < v2; ++v4) {
                ((List)v0).add(fn.a(arg7));
            }

            v4 = arg7.f();
            int v5;
            for(v5 = 0; v5 < v4; ++v5) {
                ((List)v0).add(fn.a(arg7));
            }

            if(v2 > 0) {
                fo v7 = new fo(((List)v0).get(0));
                v7.a((arg8 + 1) * 8);
                v1 = fn.a(v7).d;
            }

            return new a(((List)v0), arg8, v1);
        }

        throw new IllegalStateException();
    }

    private static d a(fp arg18, int arg19, long arg20, int arg22, String arg23, boolean arg24) {
        bj v0_1;
        String v3;
        String v2;
        fp v11 = arg18;
        v11.c(12);
        int v12 = arg18.m();
        d v13 = new d(v12);
        int v15;
        for(v15 = 0; v15 < v12; ++v15) {
            int v16 = arg18.d();
            int v17 = arg18.m();
            boolean v0 = v17 > 0 ? true : false;
            fe.a(v0, "childAtomSize should be positive");
            int v1 = arg18.m();
            if(v1 == cv.b || v1 == cv.c || v1 == cv.Z || v1 == cv.al || v1 == cv.d || v1 == cv.e || v1 == cv.f || v1 == cv.aJ || v1 == cv.aK) {
                cw.a(arg18, v1, v16, v17, arg19, arg20, arg22, v13, v15);
            }
            else {
                if(v1 != cv.i && v1 != cv.aa && v1 != cv.n && v1 != cv.p && v1 != cv.r && v1 != cv.u && v1 != cv.s && v1 != cv.t && v1 != cv.ax && v1 != cv.ay && v1 != cv.l && v1 != cv.m) {
                    if(v1 == cv.j) {
                    }
                    else {
                        if(v1 == cv.aj) {
                            v2 = Integer.toString(arg19);
                            v3 = "application/ttml+xml";
                            goto label_68;
                        }
                        else if(v1 == cv.au) {
                            v2 = Integer.toString(arg19);
                            v3 = "application/x-quicktime-tx3g";
                            goto label_68;
                        }
                        else if(v1 == cv.av) {
                            v2 = Integer.toString(arg19);
                            v3 = "application/x-mp4vtt";
                        label_68:
                            v0_1 = bj.a(v2, v3, -1, arg20, arg23);
                        }
                        else if(v1 == cv.aw) {
                            v0_1 = bj.a(Integer.toString(arg19), "application/ttml+xml", -1, arg20, arg23, 0);
                        }
                        else if(v1 == cv.aM) {
                            v0_1 = bj.a(Integer.toString(arg19), "application/x-camera-motion", -1, arg20);
                        }
                        else {
                            goto label_125;
                        }

                        v13.b = v0_1;
                        goto label_125;
                    }
                }

                cw.a(arg18, v1, v16, v17, arg19, arg20, arg23, arg24, v13, v15);
            }

        label_125:
            v11.c(v16 + v17);
        }

        return v13;
    }

    private static void a(fp arg27, int arg28, int arg29, int arg30, int arg31, long arg32, String arg34, boolean arg35, d arg36, int arg37) {
        Object v7_2;
        d v2_1;
        int v24;
        int v22;
        String v23;
        bj v3_2;
        String v5_1;
        int v6;
        int v5;
        fp v0 = arg27;
        int v1 = arg29;
        int v2 = arg30;
        long v13 = arg32;
        String v15 = arg34;
        d v12 = arg36;
        v0.c(v1 + 8);
        int v3 = 6;
        int v4 = 16;
        if(arg35) {
            v0.d(8);
            v5 = arg27.g();
            v0.d(v3);
        }
        else {
            v0.d(v4);
            v5 = 0;
        }

        int v11 = 2;
        if(v5 == 0 || v5 == 1) {
            v6 = arg27.g();
            v0.d(v3);
            v3 = arg27.q();
            if(v5 == 1) {
                v0.d(v4);
            }
        }
        else if(v5 == v11) {
            v0.d(v4);
            v3 = ((int)Math.round(arg27.v()));
            v4 = arg27.s();
            v0.d(20);
            v6 = v4;
        }
        else {
            return;
        }

        v4 = arg27.d();
        int v7 = arg28;
        if(v7 == cv.aa) {
            v5 = cw.a(v0, v1, v2, v12, arg37);
            v0.c(v4);
        }
        else {
            v5 = v7;
        }

        String v17 = null;
        if(v5 == cv.n) {
            v5_1 = "audio/ac3";
        }
        else if(v5 == cv.p) {
            v5_1 = "audio/eac3";
        }
        else if(v5 == cv.r) {
            v5_1 = "audio/vnd.dts";
        }
        else {
            if(v5 != cv.s) {
                if(v5 == cv.t) {
                }
                else if(v5 == cv.u) {
                    v5_1 = "audio/vnd.dts.hd;profile=lbr";
                    goto label_93;
                }
                else {
                    if(v5 == cv.ax) {
                        v5_1 = "audio/3gpp";
                    }
                    else if(v5 == cv.ay) {
                        v5_1 = "audio/amr-wb";
                    }
                    else {
                        if(v5 != cv.l) {
                            if(v5 == cv.m) {
                            }
                            else {
                                v5_1 = v5 == cv.j ? "audio/mpeg" : v17;
                                goto label_93;
                            }
                        }

                        v5_1 = "audio/raw";
                    }

                    goto label_93;
                }
            }

            v5_1 = "audio/vnd.dts.hd";
        }

    label_93:
        int v19 = v3;
        int v9 = v4;
        String v7_1 = v5_1;
        int v18 = v6;
        Object v20 = v17;
        while(true) {
            v4 = -1;
            if(v9 - v1 >= v2) {
                break;
            }

            v0.c(v9);
            int v8 = arg27.m();
            boolean v3_1 = v8 > 0 ? true : false;
            fe.a(v3_1, "childAtomSize should be positive");
            v3 = arg27.m();
            if(v3 != cv.J) {
                if((arg35) && v3 == cv.k) {
                    goto label_161;
                }

                if(v3 == cv.o) {
                    v0.c(v9 + 8);
                    v3_2 = fd.a(v0, Integer.toString(arg31), v13, v15);
                    goto label_122;
                }
                else if(v3 == cv.q) {
                    v0.c(v9 + 8);
                    v3_2 = fd.b(v0, Integer.toString(arg31), v13, v15);
                label_122:
                    v12.b = v3_2;
                    goto label_123;
                }
                else if(v3 == cv.v) {
                    v23 = v7_1;
                    v22 = v8;
                    v24 = v9;
                    v2_1 = v12;
                    v2_1.b = bj.a(Integer.toString(arg31), v7_1, -1, -1, arg32, v18, v19, null, arg34);
                }
                else {
                label_123:
                    v23 = v7_1;
                    v22 = v8;
                    v24 = v9;
                    v2_1 = v12;
                }

                v5 = v22;
                v7_1 = v23;
                v3 = v24;
            }
            else {
            label_161:
                v23 = v7_1;
                v22 = v8;
                v24 = v9;
                v2_1 = v12;
                if(v3 == cv.J) {
                    v5 = v22;
                    v3 = v24;
                    v9 = v3;
                }
                else {
                    v5 = v22;
                    v3 = v24;
                    v9 = cw.a(v0, v3, v5);
                }

                if(v9 != v4) {
                    Pair v4_1 = cw.d(v0, v9);
                    Object v6_1 = v4_1.first;
                    v20 = v4_1.second;
                    if("audio/mp4a-latm".equals(v6_1)) {
                        v4_1 = ff.a(((byte[])v20));
                        v7 = v4_1.first.intValue();
                        v18 = v4_1.second.intValue();
                        v19 = v7;
                    }

                    v7_2 = v6_1;
                    goto label_194;
                }

                v7_1 = v23;
            }

        label_194:
            v9 = v3 + v5;
            v12 = v2_1;
            v2 = arg30;
        }

        v23 = ((String)v7_2);
        v2_1 = v12;
        if(v2_1.b == null) {
            v5_1 = v23;
            if(v5_1 != null) {
                int v10 = "audio/raw".equals(v5_1) ? 2 : -1;
                String v0_1 = Integer.toString(arg31);
                v3 = -1;
                v4 = -1;
                List v8_1 = v20 == null ? ((List)v17) : Collections.singletonList(v20);
                v2_1.b = bj.a(v0_1, v5_1, v3, v4, arg32, v18, v19, v8_1, arg34, v10);
            }
        }
    }

    private static void a(fp arg21, int arg22, int arg23, int arg24, int arg25, long arg26, int arg28, d arg29, int arg30) {
        Object v7_2;
        Object v14_1;
        boolean v20;
        fp v0 = arg21;
        int v1 = arg22;
        int v2 = arg23;
        int v3 = arg24;
        d v4 = arg29;
        v0.c(v2 + 8);
        v0.d(24);
        int v12 = arg21.g();
        int v13 = arg21.g();
        v0.d(50);
        int v5 = arg21.d();
        if(v1 == cv.Z) {
            cw.a(v0, v2, v3, v4, arg30);
            v0.c(v5);
        }

        String v7 = null;
        List v14 = ((List)v7);
        byte[] v17 = ((byte[])v14);
        int v6 = 0;
        float v16 = 1f;
        int v18 = -1;
        while(v5 - v2 < v3) {
            v0.c(v5);
            int v9 = arg21.d();
            int v10 = arg21.m();
            if(v10 == 0 && arg21.d() - v2 == v3) {
                break;
            }

            boolean v15 = v10 > 0 ? true : false;
            fe.a(v15, "childAtomSize should be positive");
            int v8 = arg21.m();
            int v11 = 3;
            if(v8 == cv.H) {
                v20 = v7 == null ? true : false;
                fe.b(v20);
                v7 = "video/avc";
                a v8_1 = cw.a(v0, v9);
                v14 = v8_1.a;
                v4.c = v8_1.b;
                if(v6 != 0) {
                    goto label_136;
                }

                v16 = v8_1.c;
            }
            else {
                if(v8 == cv.I) {
                    v20 = v7 == null ? true : false;
                    fe.b(v20);
                    v7 = "video/hevc";
                    Pair v8_2 = cw.b(v0, v9);
                    v14_1 = v8_2.first;
                    v4.c = v8_2.second.intValue();
                    goto label_136;
                }

                if(v8 == cv.g) {
                    v20 = v7 == null ? true : false;
                    fe.b(v20);
                    v7 = "video/3gpp";
                    goto label_136;
                }

                if(v8 == cv.J) {
                    v20 = v7 == null ? true : false;
                    fe.b(v20);
                    Pair v7_1 = cw.d(v0, v9);
                    Object v8_3 = v7_1.first;
                    v14 = Collections.singletonList(v7_1.second);
                    v7_2 = v8_3;
                    goto label_136;
                }

                if(v8 == cv.ai) {
                    v16 = cw.c(v0, v9);
                    v6 = 1;
                    goto label_136;
                }

                if(v8 == cv.aL) {
                    v20 = (((String)v7_2)) == null ? true : false;
                    fe.b(v20);
                    if(v1 == cv.aJ) {
                        v7 = "video/x-vnd.on2.vp8";
                        goto label_136;
                    }

                    v7 = "video/x-vnd.on2.vp9";
                    goto label_136;
                }

                if(v8 == cv.aH) {
                    v17 = cw.d(v0, v9, v10);
                    goto label_136;
                }

                if(v8 != cv.aG) {
                    goto label_136;
                }

                v8 = arg21.f();
                v0.d(v11);
                if(v8 != 0) {
                    goto label_136;
                }

                switch(arg21.f()) {
                    case 0: {
                        goto label_135;
                    }
                    case 1: {
                        goto label_133;
                    }
                    case 2: {
                        goto label_130;
                    }
                    case 3: {
                        goto label_128;
                    }
                }

                goto label_136;
            label_130:
                v18 = 2;
                goto label_136;
            label_133:
                v18 = 1;
                goto label_136;
            label_135:
                v18 = 0;
                goto label_136;
            label_128:
                v18 = 3;
            }

        label_136:
            v5 += v10;
        }

        if((((String)v7_2)) == null) {
            return;
        }

        v4.b = bj.a(Integer.toString(arg25), ((String)v7_2), -1, -1, arg26, v12, v13, ((List)v14_1), arg28, v16, v17, v18, null);
    }

    public static dd a(com.google.ads.interactivemedia.v3.internal.cv$a arg23, com.google.ads.interactivemedia.v3.internal.cv$b arg24, long arg25, boolean arg27) {
        com.google.ads.interactivemedia.v3.internal.cv$b v4;
        long v8;
        com.google.ads.interactivemedia.v3.internal.cv$a v0 = arg23;
        com.google.ads.interactivemedia.v3.internal.cv$a v1 = v0.e(cv.E);
        int v5 = cw.e(v1.d(cv.S).aP);
        dd v3 = null;
        if(v5 != dd.b && v5 != dd.a && v5 != dd.c && v5 != dd.d && v5 != dd.e && v5 != dd.f) {
            return v3;
        }

        g v2 = cw.d(v0.d(cv.O).aP);
        long v6 = -1;
        if(arg25 == v6) {
            v8 = g.a(v2);
            v4 = arg24;
        }
        else {
            v4 = arg24;
            v8 = arg25;
        }

        long v14 = cw.c(v4.aP);
        if(v8 != v6) {
            v6 = ft.a(v8, 1000000, v14);
        }

        long v10 = v6;
        com.google.ads.interactivemedia.v3.internal.cv$a v4_1 = v1.e(cv.F).e(cv.G);
        Pair v1_1 = cw.f(v1.d(cv.R).aP);
        d v4_2 = cw.a(v4_1.d(cv.T).aP, g.b(v2), v10, g.c(v2), v1_1.second, arg27);
        Pair v0_1 = cw.a(v0.e(cv.P));
        dd v17 = v4_2.b == null ? v3 : new dd(g.b(v2), v5, v1_1.first.longValue(), v14, v10, v4_2.b, v4_2.a, v4_2.c, v0_1.first, v0_1.second);
        return v17;
    }

    public static dg a(dd arg44, com.google.ads.interactivemedia.v3.internal.cv$a arg45) {
        long[] v41;
        int[] v40;
        long[] v39;
        int[] v12_3;
        int[] v14_1;
        int v38;
        long v9_2;
        int v1_5;
        int v23;
        int v3_2;
        int v35;
        e v33;
        int v34;
        int v30;
        int v9_1;
        int v27;
        int v0_1;
        int[] v5_3;
        long[] v6;
        int[] v11_1;
        long[] v4_1;
        int v14;
        boolean v7;
        e v3;
        dd v0 = arg44;
        com.google.ads.interactivemedia.v3.internal.cv$a v1 = arg45;
        com.google.ads.interactivemedia.v3.internal.cv$b v2 = v1.d(cv.aq);
        if(v2 != null) {
            v3 = new e(v2);
        }
        else {
            v2 = v1.d(cv.ar);
            if(v2 != null) {
                f v3_1 = new f(v2);
            }
            else {
                goto label_488;
            }
        }

        int v2_1 = ((c)v3).a();
        if(v2_1 == 0) {
            return new dg(new long[0], new int[0], 0, new long[0], new int[0]);
        }

        com.google.ads.interactivemedia.v3.internal.cv$b v5 = v1.d(cv.as);
        if(v5 == null) {
            v5 = v1.d(cv.at);
            v7 = true;
        }
        else {
            v7 = false;
        }

        fp v5_1 = v5.aP;
        fp v8 = v1.d(cv.ap).aP;
        fp v9 = v1.d(cv.am).aP;
        com.google.ads.interactivemedia.v3.internal.cv$b v10 = v1.d(cv.an);
        fp v11 = null;
        fp v10_1 = v10 != null ? v10.aP : v11;
        com.google.ads.interactivemedia.v3.internal.cv$b v1_1 = v1.d(cv.ao);
        fp v1_2 = v1_1 != null ? v1_1.aP : v11;
        b v12 = new b(v8, v5_1, v7);
        int v5_2 = 12;
        v9.c(v5_2);
        int v7_1 = v9.s() - 1;
        int v8_1 = v9.s();
        int v13 = v9.s();
        if(v1_2 != null) {
            v1_2.c(v5_2);
            v14 = v1_2.s();
        }
        else {
            v14 = 0;
        }

        int v15 = -1;
        if(v10_1 != null) {
            v10_1.c(v5_2);
            v5_2 = v10_1.s();
            if(v5_2 > 0) {
                v15 = v10_1.s() - 1;
            }
            else {
                v10_1 = v11;
            }
        }
        else {
            v5_2 = 0;
        }

        int v4 = !((c)v3).c() || !"audio/raw".equals(v0.l.b) || v7_1 != 0 || v14 != 0 || v5_2 != 0 ? 0 : 1;
        long v17 = 0;
        if(v4 == 0) {
            v4_1 = new long[v2_1];
            v11_1 = new int[v2_1];
            v6 = new long[v2_1];
            int v20 = v5_2;
            v5_3 = new int[v2_1];
            fp v28 = v9;
            int v24 = v14;
            long v22 = v17;
            v0_1 = v20;
            v14 = 0;
            int v25 = 0;
            int v26 = 0;
            v27 = 0;
            v9_1 = v7_1;
            long v20_1 = v22;
            v7_1 = 0;
            int v43 = v13;
            v13 = v8_1;
            v8_1 = v43;
            while(v7_1 < v2_1) {
                while(v26 == 0) {
                    fe.b(v12.a());
                    long v31 = v12.d;
                    v26 = v12.c;
                    v8_1 = v8_1;
                    v9_1 = v9_1;
                    v20_1 = v31;
                }

                int v29 = v8_1;
                v30 = v9_1;
                if(v1_2 != null) {
                    while(v27 == 0) {
                        if(v24 <= 0) {
                            break;
                        }

                        v27 = v1_2.s();
                        v25 = v1_2.m();
                        --v24;
                    }

                    --v27;
                }

                v8_1 = v25;
                v4_1[v7_1] = v20_1;
                v11_1[v7_1] = ((c)v3).b();
                if(v11_1[v7_1] > v14) {
                    v34 = v2_1;
                    v33 = v3;
                    v14 = v11_1[v7_1];
                }
                else {
                    v34 = v2_1;
                    v33 = v3;
                }

                v6[v7_1] = v22 + (((long)v8_1));
                v2_1 = v10_1 == null ? 1 : 0;
                v5_3[v7_1] = v2_1;
                if(v7_1 == v15) {
                    v5_3[v7_1] = 1;
                    --v0_1;
                    if(v0_1 > 0) {
                        v35 = v0_1;
                        v15 = v10_1.s() - 1;
                    }
                    else {
                        goto label_168;
                    }
                }
                else {
                label_168:
                    v35 = v0_1;
                }

                v0_1 = v29;
                v22 += ((long)v0_1);
                --v13;
                if(v13 != 0 || v30 <= 0) {
                    v9_1 = v30;
                }
                else {
                    v9_1 = v30 - 1;
                    v13 = v28.s();
                    v0_1 = v28.s();
                }

                v20_1 += ((long)v11_1[v7_1]);
                --v26;
                ++v7_1;
                v25 = v8_1;
                v3 = v33;
                v2_1 = v34;
                v8_1 = v0_1;
                v0_1 = v35;
            }

            v34 = v2_1;
            v30 = v9_1;
            boolean v2_2 = v27 == 0 ? true : false;
            fe.a(v2_2);
            while(v24 > 0) {
                v2_2 = v1_2.s() == 0 ? true : false;
                fe.a(v2_2);
                v1_2.m();
                --v24;
            }

            if(v0_1 != 0 || v13 != 0 || v26 != 0 || v30 != 0) {
                v2_1 = v0_1;
                v0 = arg44;
                v3_2 = v0.g;
                StringBuilder v8_2 = new StringBuilder(215);
                v8_2.append("Inconsistent stbl box for track ");
                v8_2.append(v3_2);
                v8_2.append(": remainingSynchronizationSamples ");
                v8_2.append(v2_1);
                v8_2.append(", remainingSamplesAtTimestampDelta ");
                v8_2.append(v13);
                v8_2.append(", remainingSamplesInChunk ");
                v8_2.append(v26);
                v8_2.append(", remainingTimestampDeltaChanges ");
                v8_2.append(v30);
                Log.w("AtomParsers", v8_2.toString());
            }
            else {
                v0 = arg44;
            }

            v23 = v14;
        }
        else {
            v34 = v2_1;
            v33 = v3;
            long[] v1_3 = new long[v12.a];
            int[] v2_3 = new int[v12.a];
            while(v12.a()) {
                v1_3[v12.b] = v12.d;
                v2_3[v12.b] = v12.c;
            }

            com.google.ads.interactivemedia.v3.internal.cy$a v1_4 = cy.a(((c)v33).b(), v1_3, v2_3, ((long)v13));
            v4_1 = v1_4.a;
            v11_1 = v1_4.b;
            v2_1 = v1_4.c;
            v6 = v1_4.d;
            v5_3 = v1_4.e;
            v23 = v2_1;
        }

        if(v0.n == null) {
            ft.a(v6, 1000000, v0.i);
            return new dg(v4_1, v11_1, v23, v6, v5_3);
        }

        if(v0.n.length == 1) {
            int v16 = 0;
            if(v0.n[0] == v17) {
                v1_5 = 0;
                while(v1_5 < v6.length) {
                    v6[v1_5] = ft.a(v6[v1_5] - v0.o[v16], 1000000, v0.i);
                    ++v1_5;
                    v16 = 0;
                }

                return new dg(v4_1, v11_1, v23, v6, v5_3);
            }
        }

        v1_5 = 0;
        v2_1 = 0;
        v3_2 = 0;
        v7_1 = 0;
        while(true) {
            v9_2 = -1;
            if(v1_5 >= v0.n.length) {
                break;
            }

            long v12_1 = v0.o[v1_5];
            if(v12_1 != v9_2) {
                long v8_3 = ft.a(v0.n[v1_5], v0.i, v0.j);
                v14 = ft.b(v6, v12_1, true, true);
                v9_1 = ft.b(v6, v12_1 + v8_3, true, false);
                v3_2 += v9_1 - v14;
                v7_1 = v7_1 != v14 ? 1 : 0;
                v2_1 |= v7_1;
                v7_1 = v9_1;
            }

            ++v1_5;
        }

        v1_5 = v3_2 != v34 ? 1 : 0;
        v1_5 |= v2_1;
        long[] v2_4 = v1_5 != 0 ? new long[v3_2] : v4_1;
        int[] v7_2 = v1_5 != 0 ? new int[v3_2] : v11_1;
        if(v1_5 != 0) {
            v23 = 0;
        }

        int[] v8_4 = v1_5 != 0 ? new int[v3_2] : v5_3;
        long[] v3_3 = new long[v3_2];
        v27 = v23;
        int v12_2 = 0;
        v13 = 0;
        while(v12_2 < v0.n.length) {
            v9_2 = v0.o[v12_2];
            long v28_1 = v0.n[v12_2];
            if(v9_2 != -1) {
                int[] v36 = v7_2;
                int[] v37 = v8_4;
                long v7_3 = ft.a(v28_1, v0.i, v0.j) + v9_2;
                v15 = ft.b(v6, v9_2, true, true);
                v38 = v12_2;
                v7_1 = ft.b(v6, v7_3, true, false);
                if(v1_5 != 0) {
                    v8_1 = v7_1 - v15;
                    System.arraycopy(v4_1, v15, v2_4, v13, v8_1);
                    v14_1 = v36;
                    System.arraycopy(v11_1, v15, v14_1, v13, v8_1);
                    v12_3 = v37;
                    System.arraycopy(v5_3, v15, v12_3, v13, v8_1);
                }
                else {
                    v14_1 = v36;
                    v12_3 = v37;
                }

                v8_1 = v27;
                while(v15 < v7_1) {
                    v39 = v4_1;
                    v40 = v5_3;
                    v41 = v6;
                    int v42 = v7_1;
                    v3_3[v13] = ft.a(v17, 1000000, v0.j) + ft.a(v6[v15] - v9_2, 1000000, v0.i);
                    if(v1_5 != 0 && v14_1[v13] > v8_1) {
                        v8_1 = v11_1[v15];
                    }

                    ++v13;
                    ++v15;
                    v4_1 = v39;
                    v5_3 = v40;
                    v6 = v41;
                    v7_1 = v42;
                }

                v39 = v4_1;
                v40 = v5_3;
                v41 = v6;
                v27 = v8_1;
            }
            else {
                v39 = v4_1;
                v40 = v5_3;
                v41 = v6;
                v14_1 = v7_2;
                v38 = v12_2;
                v12_3 = v8_4;
            }

            v17 += v28_1;
            v8_4 = v12_3;
            v7_2 = v14_1;
            v5_3 = v40;
            v6 = v41;
            v12_2 = v38 + 1;
            v4_1 = v39;
        }

        v14_1 = v7_2;
        v12_3 = v8_4;
        v0_1 = 0;
        v1_5 = 0;
        while(v0_1 < v12_3.length) {
            if(v1_5 != 0) {
                break;
            }

            v4 = (v12_3[v0_1] & 1) != 0 ? 1 : 0;
            v1_5 |= v4;
            ++v0_1;
        }

        if(v1_5 != 0) {
            return new dg(v2_4, v14_1, v27, v3_3, v12_3);
        }

        throw new bl("The edited sample sequence does not contain a sync sample.");
    label_488:
        throw new bl("Track has no sample table size information");
    }

    private static Pair b(fp arg10, int arg11, int arg12) {
        int v0 = arg11 + 8;
        Pair v1 = null;
        boolean v2 = false;
        Integer v4 = ((Integer)v1);
        de v5 = ((de)v4);
        int v3 = 0;
        while(true) {
            int v7 = 1;
            if(v0 - arg11 >= arg12) {
                break;
            }

            arg10.c(v0);
            int v6 = arg10.m();
            int v8 = arg10.m();
            if(v8 == cv.ab) {
                v4 = Integer.valueOf(arg10.m());
            }
            else if(v8 == cv.W) {
                arg10.d(4);
                if(arg10.m() == cw.a) {
                }
                else {
                    v7 = 0;
                }

                v3 = v7;
            }
            else {
                if(v8 != cv.X) {
                    goto label_31;
                }

                v5 = cw.c(arg10, v0, v6);
            }

        label_31:
            v0 += v6;
        }

        if(v3 != 0) {
            boolean v10 = v4 != null ? true : false;
            fe.a(v10, "frma atom is mandatory");
            if(v5 != null) {
                v2 = true;
            }

            fe.a(v2, "schi->tenc atom is mandatory");
            return Pair.create(v4, v5);
        }

        return v1;
    }

    private static cg b(fp arg8) {
        String v2;
        String v1;
        while(true) {
        label_0:
            v1 = null;
            if(arg8.b() <= 0) {
                break;
            }

            int v0 = arg8.d() + arg8.m();
            if(arg8.m() == cv.aN) {
                v2 = v1;
                Object v3 = v2;
                goto label_11;
            }

            arg8.c(v0);
        }

        return ((cg)v1);
    label_11:
        while(arg8.d() < v0) {
            int v4 = arg8.m() - 12;
            int v5 = arg8.m();
            int v6 = 4;
            arg8.d(v6);
            if(v5 == cv.aC) {
                String v3_1 = arg8.e(v4);
                continue;
            }

            if(v5 == cv.aD) {
                v1 = arg8.e(v4);
                continue;
            }

            if(v5 == cv.aE) {
                arg8.d(v6);
                v2 = arg8.e(v4 - 4);
                continue;
            }

            arg8.d(v4);
        }

        if(v1 == null) {
            goto label_0;
        }

        if(v2 == null) {
            goto label_0;
        }

        if(!"com.apple.iTunes".equals(v3_1)) {
            goto label_0;
        }

        return cg.a(v1, v2);
    }

    private static Pair b(fp arg12, int arg13) {
        List v12_1;
        int v7;
        int v6;
        arg12.c(arg13 + 29);
        arg13 = arg12.f() & 3;
        int v0 = arg12.f();
        int v1 = arg12.d();
        int v3 = 0;
        int v4;
        for(v4 = 0; v3 < v0; v4 = v6) {
            arg12.d(1);
            int v5 = arg12.g();
            v6 = v4;
            for(v4 = 0; v4 < v5; ++v4) {
                v7 = arg12.g();
                v6 += v7 + 4;
                arg12.d(v7);
            }

            ++v3;
        }

        arg12.c(v1);
        byte[] v1_1 = new byte[v4];
        v3 = 0;
        for(v6 = 0; v3 < v0; v6 = v8) {
            arg12.d(1);
            v7 = arg12.g();
            int v8 = v6;
            for(v6 = 0; v6 < v7; ++v6) {
                int v9 = arg12.g();
                System.arraycopy(fn.a, 0, v1_1, v8, fn.a.length);
                v8 += fn.a.length;
                System.arraycopy(arg12.a, arg12.d(), v1_1, v8, v9);
                v8 += v9;
                arg12.d(v9);
            }

            ++v3;
        }

        if(v4 == 0) {
            Object v12 = null;
        }
        else {
            v12_1 = Collections.singletonList(v1_1);
        }

        return Pair.create(v12_1, Integer.valueOf(arg13 + 1));
    }

    private static long c(fp arg2) {
        int v0 = 8;
        arg2.c(v0);
        if(cv.a(arg2.m()) == 0) {
        }
        else {
            v0 = 16;
        }

        arg2.d(v0);
        return arg2.k();
    }

    private static float c(fp arg0, int arg1) {
        arg0.c(arg1 + 8);
        return (((float)arg0.s())) / (((float)arg0.s()));
    }

    private static de c(fp arg4, int arg5, int arg6) {
        int v0;
        for(v0 = arg5 + 8; v0 - arg5 < arg6; v0 += v1) {
            arg4.c(v0);
            int v1 = arg4.m();
            if(arg4.m() == cv.Y) {
                arg4.d(6);
                boolean v0_1 = true;
                if(arg4.f() == 1) {
                }
                else {
                    v0_1 = false;
                }

                arg5 = arg4.f();
                byte[] v1_1 = new byte[16];
                arg4.a(v1_1, 0, v1_1.length);
                return new de(v0_1, arg5, v1_1);
            }
        }

        return null;
    }

    private static g d(fp arg11) {
        int v0 = 8;
        arg11.c(v0);
        int v1 = cv.a(arg11.m());
        int v2 = 16;
        int v3 = v1 == 0 ? 8 : 16;
        arg11.d(v3);
        v3 = arg11.m();
        int v4 = 4;
        arg11.d(v4);
        int v5 = arg11.d();
        if(v1 == 0) {
            v0 = 4;
        }

        int v6 = 0;
        int v7 = 0;
        while(true) {
            if(v7 >= v0) {
                break;
            }
            else if(arg11.a[v5 + v7] != -1) {
                v5 = 0;
            }
            else {
                ++v7;
                continue;
            }

            goto label_29;
        }

        v5 = 1;
    label_29:
        long v7_1 = -1;
        if(v5 != 0) {
            arg11.d(v0);
        }
        else {
            long v0_1 = v1 == 0 ? arg11.k() : arg11.u();
            if(v0_1 == 0) {
                goto label_41;
            }

            v7_1 = v0_1;
        }

    label_41:
        arg11.d(v2);
        v0 = arg11.m();
        v1 = arg11.m();
        arg11.d(v4);
        v2 = arg11.m();
        int v11 = arg11.m();
        v4 = 65536;
        v5 = -65536;
        if(v0 != 0 || v1 != v4 || v2 != v5 || v11 != 0) {
            if(v0 == 0 && v1 == v5 && v2 == v4 && v11 == 0) {
                v6 = 270;
                goto label_66;
            }

            if(v0 != v5) {
                goto label_66;
            }

            if(v1 != 0) {
                goto label_66;
            }

            if(v2 != 0) {
                goto label_66;
            }

            if(v11 != v5) {
                goto label_66;
            }

            v6 = 180;
        }
        else {
            v6 = 90;
        }

    label_66:
        return new g(v3, v7_1, v6);
    }

    private static Pair d(fp arg3, int arg4) {
        arg3.c(arg4 + 12);
        arg3.d(1);
        cw.g(arg3);
        int v0 = 2;
        arg3.d(v0);
        int v1 = arg3.f();
        if((v1 & 128) != 0) {
            arg3.d(v0);
        }

        if((v1 & 64) != 0) {
            arg3.d(arg3.g());
        }

        if((v1 & 32) != 0) {
            arg3.d(v0);
        }

        arg3.d(1);
        cw.g(arg3);
        Object v1_1 = null;
        switch(arg3.f()) {
            case 32: {
                goto label_44;
            }
            case 33: {
                goto label_42;
            }
            case 35: {
                goto label_40;
            }
            case 64: 
            case 102: 
            case 103: 
            case 104: {
                goto label_38;
            }
            case 107: {
                goto label_35;
            }
            case 165: {
                goto label_33;
            }
            case 166: {
                goto label_31;
            }
            case 170: 
            case 171: {
                goto label_25;
            }
            case 169: 
            case 172: {
                goto label_28;
            }
        }

        goto label_45;
    label_33:
        String v1_2 = "audio/ac3";
        goto label_45;
    label_35:
        return Pair.create("audio/mpeg", v1_1);
    label_38:
        v1_2 = "audio/mp4a-latm";
        goto label_45;
    label_40:
        v1_2 = "video/hevc";
        goto label_45;
    label_42:
        v1_2 = "video/avc";
        goto label_45;
    label_44:
        v1_2 = "video/mp4v-es";
        goto label_45;
    label_25:
        return Pair.create("audio/vnd.dts.hd", v1_1);
    label_28:
        return Pair.create("audio/vnd.dts", v1_1);
    label_31:
        v1_2 = "audio/eac3";
    label_45:
        arg3.d(12);
        arg3.d(1);
        arg4 = cw.g(arg3);
        byte[] v0_1 = new byte[arg4];
        arg3.a(v0_1, 0, arg4);
        return Pair.create(v1_1, v0_1);
    }

    private static byte[] d(fp arg4, int arg5, int arg6) {
        int v0;
        for(v0 = arg5 + 8; v0 - arg5 < arg6; v0 += v1) {
            arg4.c(v0);
            int v1 = arg4.m();
            if(arg4.m() == cv.aI) {
                return Arrays.copyOfRange(arg4.a, v0, v1 + v0);
            }
        }

        return null;
    }

    private static int e(fp arg1) {
        arg1.c(16);
        return arg1.m();
    }

    private static Pair f(fp arg6) {
        int v0 = 8;
        arg6.c(v0);
        int v1 = cv.a(arg6.m());
        int v2 = v1 == 0 ? 8 : 16;
        arg6.d(v2);
        long v2_1 = arg6.k();
        if(v1 == 0) {
            v0 = 4;
        }

        arg6.d(v0);
        int v6 = arg6.g();
        StringBuilder v5 = new StringBuilder(3);
        v5.append(((char)((v6 >> 10 & 31) + 96)));
        v5.append(((char)((v6 >> 5 & 31) + 96)));
        v5.append(((char)((v6 & 31) + 96)));
        return Pair.create(Long.valueOf(v2_1), v5.toString());
    }

    private static int g(fp arg3) {
        int v0 = arg3.f();
        int v1;
        for(v1 = v0 & 127; (v0 & 128) == 128; v1 = v1 << 7 | v0 & 127) {
            v0 = arg3.f();
        }

        return v1;
    }
}

