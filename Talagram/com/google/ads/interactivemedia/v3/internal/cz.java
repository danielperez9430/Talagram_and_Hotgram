package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class cz implements cc {
    final class a {
        public final df a;
        public final ck b;
        public dd c;
        public cx d;
        public int e;

        public a(ck arg2) {
            super();
            this.a = new df();
            this.b = arg2;
        }

        public void a(dd arg2, cx arg3) {
            this.c = fe.a(arg2);
            this.d = fe.a(arg3);
            this.b.a(arg2.l);
            this.a();
        }

        public void a() {
            this.a.a();
            this.e = 0;
        }
    }

    private static final int a;
    private static final byte[] b;
    private final int c;
    private final dd d;
    private final SparseArray e;
    private final fp f;
    private final fp g;
    private final fp h;
    private final fp i;
    private final byte[] j;
    private final Stack k;
    private int l;
    private int m;
    private long n;
    private int o;
    private fp p;
    private long q;
    private a r;
    private int s;
    private int t;
    private int u;
    private ce v;
    private boolean w;

    static {
        cz.a = ft.c("seig");
        cz.b = new byte[]{-94, 57, 79, 82, 90, -101, 79, 20, -94, 68, 108, 66, 124, 100, -115, -12};
    }

    public cz() {
        this(0);
    }

    public cz(int arg2) {
        this(arg2, null);
    }

    public cz(int arg3, dd arg4) {
        super();
        this.d = arg4;
        int v0 = 4;
        int v4 = arg4 != null ? 4 : 0;
        this.c = arg3 | v4;
        this.i = new fp(16);
        this.f = new fp(fn.a);
        this.g = new fp(v0);
        this.h = new fp(1);
        this.j = new byte[16];
        this.k = new Stack();
        this.e = new SparseArray();
        this.a();
    }

    private void a() {
        this.l = 0;
        this.o = 0;
    }

    private int a(a arg7) {
        df v0 = arg7.a;
        fp v1 = v0.l;
        int v2 = v0.a.a;
        de v2_1 = v0.n != null ? v0.n : arg7.c.m[v2];
        v2 = v2_1.b;
        boolean v0_1 = v0.j[arg7.e];
        byte[] v3 = this.h.a;
        int v5 = v0_1 ? 128 : 0;
        v3[0] = ((byte)(v5 | v2));
        this.h.c(0);
        ck v7 = arg7.b;
        v7.a(this.h, 1);
        v7.a(v1, v2);
        if(!v0_1) {
            return v2 + 1;
        }

        int v0_2 = v1.g();
        v1.d(-2);
        v0_2 = v0_2 * 6 + 2;
        v7.a(v1, v0_2);
        return v2 + 1 + v0_2;
    }

    private static Pair a(fp arg5) {
        arg5.c(12);
        return Pair.create(Integer.valueOf(arg5.m()), new cx(arg5.s() - 1, arg5.s(), arg5.s(), arg5.m()));
    }

    private static com.google.ads.interactivemedia.v3.internal.bu$a a(List arg7) {
        int v0 = arg7.size();
        com.google.ads.interactivemedia.v3.internal.bu$a v1 = null;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            Object v3 = arg7.get(v2);
            if(((b)v3).aO == cv.U) {
                if(v1 == null) {
                    v1 = new com.google.ads.interactivemedia.v3.internal.bu$a();
                }

                byte[] v3_1 = ((b)v3).aP.a;
                if(db.a(v3_1) == null) {
                    Log.w("FragmentedMp4Extractor", "Skipped pssh atom (failed to extract uuid)");
                    goto label_24;
                }

                v1.a(db.a(v3_1), new com.google.ads.interactivemedia.v3.internal.bu$b("video/mp4", v3_1));
            }

        label_24:
        }

        return v1;
    }

    private static a a(SparseArray arg9) {
        int v0 = arg9.size();
        a v1 = null;
        long v2 = 9223372036854775807L;
        int v4;
        for(v4 = 0; v4 < v0; ++v4) {
            Object v5 = arg9.valueAt(v4);
            if(((a)v5).e == ((a)v5).a.d) {
            }
            else {
                long v6 = ((a)v5).a.b;
                if(v6 < v2) {
                    Object v1_1 = v5;
                    v2 = v6;
                }
            }
        }

        return v1;
    }

    private static a a(fp arg4, SparseArray arg5, int arg6) {
        arg4.c(8);
        int v0 = cv.b(arg4.m());
        int v1 = arg4.m();
        if((arg6 & 4) == 0) {
        }
        else {
            v1 = 0;
        }

        Object v5 = arg5.get(v1);
        if(v5 == null) {
            return null;
        }

        if((v0 & 1) != 0) {
            long v1_1 = arg4.u();
            ((a)v5).a.b = v1_1;
            ((a)v5).a.c = v1_1;
        }

        cx v6 = ((a)v5).d;
        v1 = (v0 & 2) != 0 ? arg4.s() - 1 : v6.a;
        int v2 = (v0 & 8) != 0 ? arg4.s() : v6.b;
        int v3 = (v0 & 16) != 0 ? arg4.s() : v6.c;
        int v4 = (v0 & 32) != 0 ? arg4.s() : v6.d;
        ((a)v5).a.a = new cx(v1, v2, v3, v4);
        return ((a)v5);
    }

    private void a(long arg4) {
        while(!this.k.isEmpty()) {
            if(this.k.peek().aP != arg4) {
                break;
            }

            this.a(this.k.pop());
        }

        this.a();
    }

    private void a(com.google.ads.interactivemedia.v3.internal.cv$a arg3) {
        if(arg3.aO == cv.B) {
            this.b(arg3);
        }
        else if(arg3.aO == cv.K) {
            this.c(arg3);
        }
        else if(!this.k.isEmpty()) {
            this.k.peek().a(arg3);
        }
    }

    private static void a(com.google.ads.interactivemedia.v3.internal.cv$a arg5, SparseArray arg6, int arg7, byte[] arg8) {
        int v0 = arg5.aR.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = arg5.aR.get(v1);
            if(((com.google.ads.interactivemedia.v3.internal.cv$a)v2).aO == cv.L) {
                cz.b(((com.google.ads.interactivemedia.v3.internal.cv$a)v2), arg6, arg7, arg8);
            }
        }
    }

    private void a(b arg3, long arg4) {
        if(!this.k.isEmpty()) {
            this.k.peek().a(arg3);
        }
        else if(arg3.aO == cv.A) {
            this.v.a(cz.b(arg3.aP, arg4));
            this.w = true;
        }
        else if(arg3.aO == cv.aF) {
            this.a(arg3.aP, arg4);
        }
    }

    protected void a(fp arg1, long arg2) {
    }

    private static void a(a arg37, long arg38, int arg40, fp arg41) {
        boolean v9_1;
        int v35;
        int v34;
        int v33;
        int v32;
        int v16;
        int v31;
        arg41.c(8);
        int v1 = cv.b(arg41.m());
        dd v3 = arg37.c;
        df v0 = arg37.a;
        cx v4 = v0.a;
        int v5 = arg41.s();
        if((v1 & 1) != 0) {
            v0.b += ((long)arg41.m());
        }

        int v6 = (v1 & 4) != 0 ? 1 : 0;
        int v9 = v4.d;
        if(v6 != 0) {
            v9 = arg41.s();
        }

        int v10 = (v1 & 256) != 0 ? 1 : 0;
        int v11 = (v1 & 512) != 0 ? 1 : 0;
        int v12 = (v1 & 1024) != 0 ? 1 : 0;
        v1 = (v1 & 2048) != 0 ? 1 : 0;
        long v14 = 0;
        if(v3.n != null && v3.n.length == 1 && v3.n[0] == v14) {
            v14 = ft.a(v3.o[0], 1000, v3.i);
        }

        v0.a(v5);
        int[] v7 = v0.e;
        int[] v8 = v0.f;
        long[] v13 = v0.g;
        boolean[] v2 = v0.h;
        long[] v28 = v13;
        long v26 = v14;
        long v13_1 = v3.i;
        int v3_1 = v3.h != dd.a || (arg40 & 1) == 0 ? 0 : 1;
        long v29 = arg38;
        int v15 = 0;
        while(v15 < v5) {
            if(v10 != 0) {
                v31 = v5;
                v5 = arg41.s();
            }
            else {
                v31 = v5;
                v5 = v4.b;
            }

            if(v11 != 0) {
                v16 = arg41.s();
                v32 = v9;
            }
            else {
                v32 = v9;
                v16 = v4.c;
            }

            if(v15 == 0 && v6 != 0) {
                v9 = v32;
            }
            else if(v12 != 0) {
                v9 = arg41.m();
            }
            else {
                v9 = v4.d;
            }

            if(v1 != 0) {
                v33 = v1;
                v34 = v10;
                v35 = v11;
                v8[v15] = ((int)((((long)(arg41.m() * 1000))) / v13_1));
            }
            else {
                v33 = v1;
                v34 = v10;
                v35 = v11;
                v8[v15] = 0;
            }

            v28[v15] = ft.a(v29, 1000, v13_1) - v26;
            v7[v15] = v16;
            if((v9 >> 16 & 1) == 0) {
                if(v3_1 != 0 && v15 != 0) {
                    goto label_136;
                }

                v9_1 = true;
            }
            else {
            label_136:
                v9_1 = false;
            }

            v2[v15] = v9_1;
            v29 += ((long)v5);
            ++v15;
            v5 = v31;
            v9 = v32;
            v1 = v33;
            v10 = v34;
            v11 = v35;
            v2 = v2;
        }

        v0.o = v29;
    }

    private static void a(de arg7, fp arg8, df arg9) {
        int v5;
        int v7 = arg7.b;
        int v0 = 8;
        arg8.c(v0);
        boolean v2 = true;
        if((cv.b(arg8.m()) & 1) == 1) {
            arg8.d(v0);
        }

        v0 = arg8.f();
        int v1 = arg8.s();
        if(v1 == arg9.d) {
            if(v0 == 0) {
                boolean[] v0_1 = arg9.j;
                int v4 = 0;
                v5 = 0;
                while(v4 < v1) {
                    int v6 = arg8.f();
                    v5 += v6;
                    boolean v6_1 = v6 > v7 ? true : false;
                    v0_1[v4] = v6_1;
                    ++v4;
                }
            }
            else {
                if(v0 > v7) {
                }
                else {
                    v2 = false;
                }

                v5 = v0 * v1;
                Arrays.fill(arg9.j, 0, v1, v2);
            }

            arg9.b(v5);
            return;
        }

        int v8 = arg9.d;
        StringBuilder v0_2 = new StringBuilder(41);
        v0_2.append("Length mismatch: ");
        v0_2.append(v1);
        v0_2.append(", ");
        v0_2.append(v8);
        throw new bl(v0_2.toString());
    }

    private static void a(fp arg3, int arg4, df arg5) {
        arg3.c(arg4 + 8);
        arg4 = cv.b(arg3.m());
        if((arg4 & 1) == 0) {
            boolean v4 = (arg4 & 2) != 0 ? true : false;
            int v1 = arg3.s();
            if(v1 == arg5.d) {
                Arrays.fill(arg5.j, 0, v1, v4);
                arg5.b(arg3.b());
                arg5.a(arg3);
                return;
            }

            arg4 = arg5.d;
            StringBuilder v0 = new StringBuilder(41);
            v0.append("Length mismatch: ");
            v0.append(v1);
            v0.append(", ");
            v0.append(arg4);
            throw new bl(v0.toString());
        }

        throw new bl("Overriding TrackEncryptionBox parameters is unsupported.");
    }

    private static void a(fp arg5, df arg6) {
        int v0 = 8;
        arg5.c(v0);
        int v1 = arg5.m();
        if((cv.b(v1) & 1) == 1) {
            arg5.d(v0);
        }

        v0 = arg5.s();
        if(v0 == 1) {
            v0 = cv.a(v1);
            long v1_1 = arg6.c;
            long v3 = v0 == 0 ? arg5.k() : arg5.u();
            arg6.c = v1_1 + v3;
            return;
        }

        StringBuilder v1_2 = new StringBuilder(40);
        v1_2.append("Unexpected saio entry count: ");
        v1_2.append(v0);
        throw new bl(v1_2.toString());
    }

    private static void a(fp arg2, df arg3, byte[] arg4) {
        arg2.c(8);
        int v0 = 16;
        arg2.a(arg4, 0, v0);
        if(!Arrays.equals(arg4, cz.b)) {
            return;
        }

        cz.a(arg2, v0, arg3);
    }

    private static void a(fp arg6, fp arg7, df arg8) {
        int v0 = 8;
        arg6.c(v0);
        int v1 = arg6.m();
        if(arg6.m() != cz.a) {
            return;
        }

        int v2 = 4;
        if(cv.a(v1) == 1) {
            arg6.d(v2);
        }

        if(arg6.m() == 1) {
            arg7.c(v0);
            int v6 = arg7.m();
            if(arg7.m() != cz.a) {
                return;
            }

            v6 = cv.a(v6);
            v0 = 2;
            if(v6 == 1) {
                if(arg7.k() != 0) {
                }
                else {
                    throw new bl("Variable length decription in sgpd found (unsupported)");
                }
            }
            else if(v6 >= v0) {
                arg7.d(v2);
            }

            if(arg7.k() == 1) {
                arg7.d(v0);
                boolean v6_1 = arg7.f() == 1 ? true : false;
                if(!v6_1) {
                    return;
                }

                v1 = arg7.f();
                byte[] v2_1 = new byte[16];
                arg7.a(v2_1, 0, v2_1.length);
                arg8.i = true;
                arg8.n = new de(v6_1, v1, v2_1);
                return;
            }

            throw new bl("Entry count in sgpd != 1 (unsupported).");
        }

        throw new bl("Entry count in sbgp != 1 (unsupported).");
    }

    private static boolean a(int arg1) {
        boolean v1 = arg1 == cv.S || arg1 == cv.R || arg1 == cv.C || arg1 == cv.A || arg1 == cv.T || arg1 == cv.w || arg1 == cv.x || arg1 == cv.O || arg1 == cv.y || arg1 == cv.z || arg1 == cv.U || arg1 == cv.ac || arg1 == cv.ad || arg1 == cv.ah || arg1 == cv.ae || arg1 == cv.af || arg1 == cv.ag || arg1 == cv.Q || arg1 == cv.N || arg1 == cv.aF ? true : false;
        return v1;
    }

    public final int a(cd arg1, ch arg2) {
        do {
        label_0:
            switch(this.l) {
                case 0: {
                    goto label_10;
                }
                case 1: {
                    goto label_8;
                }
                case 2: {
                    goto label_6;
                }
            }

            if(!this.e(arg1)) {
                goto label_0;
            }

            return 0;
        label_6:
            this.d(arg1);
            goto label_0;
        label_8:
            this.c(arg1);
            goto label_0;
        label_10:
        }
        while(this.b(arg1));

        return -1;
    }

    public final void a(ce arg4) {
        this.v = arg4;
        if(this.d != null) {
            a v0 = new a(arg4.d(0));
            v0.a(this.d, new cx(0, 0, 0, 0));
            this.e.put(0, v0);
            this.v.f();
        }
    }

    public final boolean a(cd arg1) {
        return dc.a(arg1);
    }

    private void b(com.google.ads.interactivemedia.v3.internal.cv$a arg12) {
        int v1_1;
        boolean v1 = true;
        int v2 = 0;
        boolean v0 = this.d == null ? true : false;
        fe.b(v0, "Unexpected moov box.");
        com.google.ads.interactivemedia.v3.internal.bu$a v0_1 = cz.a(arg12.aQ);
        if(v0_1 != null) {
            this.v.a(((bu)v0_1));
        }

        com.google.ads.interactivemedia.v3.internal.cv$a v0_2 = arg12.e(cv.M);
        SparseArray v3 = new SparseArray();
        int v6 = v0_2.aQ.size();
        long v7 = -1;
        int v4;
        for(v4 = 0; v4 < v6; ++v4) {
            Object v5 = v0_2.aQ.get(v4);
            if(((b)v5).aO == cv.y) {
                Pair v5_1 = cz.a(((b)v5).aP);
                v3.put(v5_1.first.intValue(), v5_1.second);
            }
            else if(((b)v5).aO == cv.N) {
                v7 = cz.b(((b)v5).aP);
            }
        }

        SparseArray v0_3 = new SparseArray();
        v4 = arg12.aR.size();
        int v5_2;
        for(v5_2 = 0; v5_2 < v4; ++v5_2) {
            Object v6_1 = arg12.aR.get(v5_2);
            if(((com.google.ads.interactivemedia.v3.internal.cv$a)v6_1).aO == cv.D) {
                dd v6_2 = cw.a(((com.google.ads.interactivemedia.v3.internal.cv$a)v6_1), arg12.d(cv.C), v7, false);
                if(v6_2 != null) {
                    v0_3.put(v6_2.g, v6_2);
                }
            }
        }

        int v12 = v0_3.size();
        if(this.e.size() == 0) {
            v1_1 = 0;
            goto label_67;
        }
        else {
            if(this.e.size() == v12) {
            }
            else {
                v1 = false;
            }

            fe.b(v1);
            goto label_87;
        label_67:
            while(v1_1 < v12) {
                this.e.put(v0_3.valueAt(v1_1).g, new a(this.v.d(v1_1)));
                ++v1_1;
            }

            this.v.f();
        }

    label_87:
        while(v2 < v12) {
            Object v1_2 = v0_3.valueAt(v2);
            this.e.get(((dd)v1_2).g).a(((dd)v1_2), v3.get(((dd)v1_2).g));
            ++v2;
        }
    }

    private static void b(com.google.ads.interactivemedia.v3.internal.cv$a arg4, SparseArray arg5, int arg6, byte[] arg7) {
        if(arg4.f(cv.z) == 1) {
            a v5 = cz.a(arg4.d(cv.x).aP, arg5, arg6);
            if(v5 == null) {
                return;
            }

            df v0 = v5.a;
            long v1 = v0.o;
            v5.a();
            if(arg4.d(cv.w) != null && (arg6 & 2) == 0) {
                v1 = cz.c(arg4.d(cv.w).aP);
            }

            cz.a(v5, v1, arg6, arg4.d(cv.z).aP);
            b v6 = arg4.d(cv.ac);
            if(v6 != null) {
                cz.a(v5.c.m[v0.a.a], v6.aP, v0);
            }

            b v5_1 = arg4.d(cv.ad);
            if(v5_1 != null) {
                cz.a(v5_1.aP, v0);
            }

            v5_1 = arg4.d(cv.ah);
            if(v5_1 != null) {
                cz.b(v5_1.aP, v0);
            }

            v5_1 = arg4.d(cv.ae);
            v6 = arg4.d(cv.af);
            if(v5_1 != null && v6 != null) {
                cz.a(v5_1.aP, v6.aP, v0);
            }

            int v5_2 = arg4.aQ.size();
            for(arg6 = 0; arg6 < v5_2; ++arg6) {
                Object v1_1 = arg4.aQ.get(arg6);
                if(((b)v1_1).aO == cv.ag) {
                    cz.a(((b)v1_1).aP, v0, arg7);
                }
            }

            return;
        }

        throw new bl("Trun count in traf != 1 (unsupported).");
    }

    private static by b(fp arg20, long arg21) {
        long v7;
        long v5;
        fp v0 = arg20;
        v0.c(8);
        int v3 = cv.a(arg20.m());
        v0.d(4);
        long v11 = arg20.k();
        if(v3 == 0) {
            v5 = arg20.k();
            v7 = arg20.k();
        }
        else {
            v5 = arg20.u();
            v7 = arg20.u();
        }

        long v13 = arg21 + v7;
        long v1 = v5;
        v0.d(2);
        v3 = arg20.g();
        int[] v15 = new int[v3];
        long[] v9 = new long[v3];
        long[] v10 = new long[v3];
        v5 = v1;
        long[] v4 = new long[v3];
        long v18 = v1;
        long[] v1_1 = v9;
        long[] v2 = v10;
        v5 = ft.a(v5, 1000000, v11);
        long v16 = v13;
        int v13_1;
        for(v13_1 = 0; true; ++v13_1) {
            if(v13_1 >= v3) {
                goto label_67;
            }

            int v7_1 = arg20.m();
            if((-2147483648 & v7_1) != 0) {
                break;
            }

            long v8 = arg20.k();
            v15[v13_1] = v7_1 & 2147483647;
            v1_1[v13_1] = v16;
            v4[v13_1] = v5;
            v18 += v8;
            v5 = ft.a(v18, 1000000, v11);
            v2[v13_1] = v5 - v4[v13_1];
            v0.d(4);
            v16 += ((long)v15[v13_1]);
        }

        throw new bl("Unhandled indirect reference");
    label_67:
        return new by(v15, v1_1, v2, v4);
    }

    private static long b(fp arg2) {
        arg2.c(8);
        long v0 = cv.a(arg2.m()) == 0 ? arg2.k() : arg2.u();
        return v0;
    }

    private static void b(fp arg1, df arg2) {
        cz.a(arg1, 0, arg2);
    }

    private static boolean b(int arg1) {
        boolean v1 = arg1 == cv.B || arg1 == cv.D || arg1 == cv.E || arg1 == cv.F || arg1 == cv.G || arg1 == cv.K || arg1 == cv.L || arg1 == cv.M || arg1 == cv.P ? true : false;
        return v1;
    }

    private boolean b(cd arg9) {
        int v1 = 8;
        if(this.o == 0) {
            if(!arg9.a(this.i.a, 0, v1, true)) {
                return 0;
            }
            else {
                this.o = v1;
                this.i.c(0);
                this.n = this.i.k();
                this.m = this.i.m();
            }
        }

        if(this.n == 1) {
            arg9.b(this.i.a, v1, v1);
            this.o += v1;
            this.n = this.i.u();
        }

        if(this.n < (((long)this.o))) {
            goto label_127;
        }

        long v4 = arg9.c() - (((long)this.o));
        if(this.m == cv.K) {
            int v0 = this.e.size();
            int v6;
            for(v6 = 0; v6 < v0; ++v6) {
                df v7 = this.e.valueAt(v6).a;
                v7.c = v4;
                v7.b = v4;
            }
        }

        a v7_1 = null;
        if(this.m == cv.h) {
            this.r = v7_1;
            this.q = v4 + this.n;
            if(!this.w) {
                this.v.a(cj.f);
                this.w = true;
            }

            this.l = 2;
            return 1;
        }

        if(cz.b(this.m)) {
            long v0_1 = arg9.c() + this.n - 8;
            this.k.add(new com.google.ads.interactivemedia.v3.internal.cv$a(this.m, v0_1));
            if(this.n == (((long)this.o))) {
                this.a(v0_1);
            }
            else {
                this.a();
            }
        }
        else {
            v4 = 2147483647;
            if(cz.a(this.m)) {
                if(this.o != v1) {
                    throw new bl("Leaf atom defines extended atom size (unsupported).");
                }
                else if(this.n <= v4) {
                    this.p = new fp(((int)this.n));
                    System.arraycopy(this.i.a, 0, this.p.a, 0, v1);
                }
                else {
                    throw new bl("Leaf atom with length > 2147483647 (unsupported).");
                }
            }
            else if(this.n <= v4) {
                this.p = ((fp)v7_1);
            }
            else {
                goto label_123;
            }

            this.l = 1;
        }

        return 1;
    label_123:
        throw new bl("Skipping atom with length > 2147483647 (unsupported).");
    label_127:
        throw new bl("Atom size less than header length (unsupported).");
    }

    public final void b() {
        int v0 = this.e.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.e.valueAt(v1).a();
        }

        this.k.clear();
        this.a();
    }

    private void c(com.google.ads.interactivemedia.v3.internal.cv$a arg4) {
        cz.a(arg4, this.e, this.c, this.j);
        com.google.ads.interactivemedia.v3.internal.bu$a v4 = cz.a(arg4.aQ);
        if(v4 != null) {
            this.v.a(((bu)v4));
        }
    }

    private static long c(fp arg2) {
        arg2.c(8);
        long v0 = cv.a(arg2.m()) == 1 ? arg2.u() : arg2.k();
        return v0;
    }

    private void c(cd arg4) {
        int v0 = (((int)this.n)) - this.o;
        if(this.p != null) {
            arg4.b(this.p.a, 8, v0);
            this.a(new b(this.m, this.p), arg4.c());
        }
        else {
            arg4.b(v0);
        }

        this.a(arg4.c());
    }

    public final void c() {
    }

    private void d(cd arg12) {
        int v0 = this.e.size();
        Object v1 = null;
        long v2 = 9223372036854775807L;
        int v4;
        for(v4 = 0; v4 < v0; ++v4) {
            df v5 = this.e.valueAt(v4).a;
            if((v5.m) && v5.c < v2) {
                long v9 = v5.c;
                v1 = this.e.valueAt(v4);
                v2 = v9;
            }
        }

        if(v1 == null) {
            this.l = 3;
            return;
        }

        v0 = ((int)(v2 - arg12.c()));
        if(v0 >= 0) {
            arg12.b(v0);
            ((a)v1).a.a(arg12);
            return;
        }

        throw new bl("Offset to encryption data was negative.");
    }

    private boolean e(cd arg15) {
        byte[] v12;
        int v0;
        int v1 = 3;
        int v2 = 4;
        int v3 = 0;
        if(this.l == v1) {
            if(this.r == null) {
                this.r = cz.a(this.e);
                if(this.r == null) {
                    v0 = ((int)(this.q - arg15.c()));
                    if(v0 >= 0) {
                        arg15.b(v0);
                        this.a();
                        return 0;
                    }
                    else {
                        throw new bl("Offset to end of mdat was negative.");
                    }
                }
                else {
                    v0 = ((int)(this.r.a.b - arg15.c()));
                    if(v0 < 0) {
                        Log.w("FragmentedMp4Extractor", "Ignoring negative offset to sample data.");
                        v0 = 0;
                    }

                    arg15.b(v0);
                }
            }

            this.s = this.r.a.e[this.r.e];
            if(this.r.a.i) {
                this.t = this.a(this.r);
                this.s += this.t;
            }
            else {
                this.t = 0;
            }

            this.l = v2;
            this.u = 0;
        }

        df v0_1 = this.r.a;
        dd v4 = this.r.c;
        ck v6 = this.r.b;
        int v5 = this.r.e;
        int v9 = 2;
        if(v4.p != -1) {
            byte[] v7 = this.g.a;
            v7[0] = 0;
            v7[1] = 0;
            v7[v9] = 0;
            int v7_1 = v4.p;
            int v8 = 4 - v4.p;
            while(this.t < this.s) {
                if(this.u == 0) {
                    arg15.b(this.g.a, v8, v7_1);
                    this.g.c(0);
                    this.u = this.g.s();
                    this.f.c(0);
                    v6.a(this.f, v2);
                    this.t += v2;
                    this.s += v8;
                }
                else {
                    int v10 = v6.a(arg15, this.u, false);
                    this.t += v10;
                    this.u -= v10;
                }
            }
        }
        else {
            while(this.t < this.s) {
                this.t += v6.a(arg15, this.s - this.t, false);
            }
        }

        long v7_2 = v0_1.c(v5) * 1000;
        if(v0_1.i) {
            v3 = 2;
        }

        v9 = v3 | v0_1.h[v5];
        int v15 = v0_1.a.a;
        a v3_1 = null;
        if(v0_1.i) {
            de v15_1 = v0_1.n != null ? v0_1.n : v4.m[v15];
            byte[] v15_2 = v15_1.c;
            v12 = v15_2;
        }
        else {
            v12 = ((byte[])v3_1);
        }

        v6.a(v7_2, v9, this.s, 0, v12);
        ++this.r.e;
        if(this.r.e == v0_1.d) {
            this.r = v3_1;
        }

        this.l = v1;
        return 1;
    }
}

