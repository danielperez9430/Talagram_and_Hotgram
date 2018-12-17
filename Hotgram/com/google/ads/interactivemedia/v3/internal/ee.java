package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;

public final class ee implements cc {
    class com.google.ads.interactivemedia.v3.internal.ee$1 {
    }

    class a extends d {
        private final fp b;
        private final fo c;
        private int d;
        private int e;
        private int f;

        public a(ee arg2) {
            this.a = arg2;
            super(null);
            this.b = new fp();
            this.c = new fo(new byte[4]);
        }

        public void a() {
        }

        public void a(fp arg6, boolean arg7, ce arg8) {
            int v8 = 3;
            int v0 = 0;
            if(arg7) {
                arg6.d(arg6.f());
                arg6.a(this.c, v8);
                this.c.b(12);
                this.d = this.c.c(12);
                this.e = 0;
                this.f = ft.a(this.c.a, 0, v8, -1);
                this.b.a(this.d);
            }

            int v7 = Math.min(arg6.b(), this.d - this.e);
            arg6.a(this.b.a, this.e, v7);
            this.e += v7;
            if(this.e < this.d) {
                return;
            }

            if(ft.a(this.b.a, 0, this.d, this.f) != 0) {
                return;
            }

            this.b.d(5);
            v7 = 4;
            int v6 = (this.d - 9) / v7;
            while(v0 < v6) {
                this.b.a(this.c, v7);
                int v1 = this.c.c(16);
                this.c.b(v8);
                int v2 = 13;
                if(v1 == 0) {
                    this.c.b(v2);
                }
                else {
                    v1 = this.c.c(v2);
                    this.a.a.put(v1, new c(this.a, v1));
                }

                ++v0;
            }
        }
    }

    final class b extends d {
        private final du a;
        private final ec b;
        private final fo c;
        private int d;
        private int e;
        private boolean f;
        private boolean g;
        private boolean h;
        private int i;
        private int j;
        private boolean k;
        private long l;

        public b(du arg2, ec arg3) {
            super(null);
            this.a = arg2;
            this.b = arg3;
            this.c = new fo(new byte[10]);
            this.d = 0;
        }

        private void a(int arg1) {
            this.d = arg1;
            this.e = 0;
        }

        private boolean a(fp arg4, byte[] arg5, int arg6) {
            int v0 = Math.min(arg4.b(), arg6 - this.e);
            boolean v1 = true;
            if(v0 <= 0) {
                return 1;
            }

            if(arg5 == null) {
                arg4.d(v0);
            }
            else {
                arg4.a(arg5, this.e, v0);
            }

            this.e += v0;
            if(this.e == arg6) {
            }
            else {
                v1 = false;
            }

            return v1;
        }

        public void a() {
            this.d = 0;
            this.e = 0;
            this.h = false;
            this.a.a();
        }

        public void a(fp arg5, boolean arg6, ce arg7) {
            int v1;
            int v0 = -1;
            if(arg6) {
                switch(this.d) {
                    case 2: {
                        goto label_21;
                    }
                    case 3: {
                        goto label_6;
                    }
                }

                goto label_24;
            label_6:
                if(this.j != v0) {
                    v1 = this.j;
                    StringBuilder v3 = new StringBuilder(59);
                    v3.append("Unexpected start indicator: expected ");
                    v3.append(v1);
                    v3.append(" more bytes");
                    Log.w("TsExtractor", v3.toString());
                    goto label_51;
                label_21:
                    Log.w("TsExtractor", "Unexpected start indicator reading extended header");
                label_24:
                    this.a(1);
                    goto label_25;
                }
            }
            else {
                do {
                label_25:
                    if(arg5.b() > 0) {
                        v1 = 0;
                        switch(this.d) {
                            case 0: {
                                goto label_83;
                            }
                            case 1: {
                                goto label_73;
                            }
                            case 2: {
                                goto label_54;
                            }
                            case 3: {
                                goto label_31;
                            }
                        }

                        continue;
                    label_83:
                        arg5.d(arg5.b());
                        continue;
                    label_54:
                        if(!this.a(arg5, this.c.a, Math.min(10, this.i))) {
                            continue;
                        }

                        if(!this.a(arg5, null, this.i)) {
                            continue;
                        }

                        this.c();
                        this.a.a(this.l, this.k);
                        this.a(3);
                        continue;
                    label_73:
                        if(!this.a(arg5, this.c.a, 9)) {
                            continue;
                        }

                        if(this.b()) {
                            v1 = 2;
                        }

                        this.a(v1);
                        continue;
                    label_31:
                        int v6 = arg5.b();
                        if(this.j == v0) {
                        }
                        else {
                            v1 = v6 - this.j;
                        }

                        if(v1 > 0) {
                            v6 -= v1;
                            arg5.b(arg5.d() + v6);
                        }

                        this.a.a(arg5);
                        if(this.j == v0) {
                            continue;
                        }

                        this.j -= v6;
                        if(this.j != 0) {
                            continue;
                        }
                    }
                    else {
                        return;
                    }

                    goto label_51;
                }
                while(true);

                return;
            }

        label_51:
            this.a.b();
            goto label_24;
        }

        private boolean b() {
            this.c.a(0);
            int v0 = this.c.c(24);
            int v2 = -1;
            if(v0 != 1) {
                StringBuilder v5 = new StringBuilder(41);
                v5.append("Unexpected start code prefix: ");
                v5.append(v0);
                Log.w("TsExtractor", v5.toString());
                this.j = v2;
                return 0;
            }

            this.c.b(8);
            v0 = this.c.c(16);
            this.c.b(5);
            this.k = this.c.b();
            this.c.b(2);
            this.f = this.c.b();
            this.g = this.c.b();
            int v5_1 = 6;
            this.c.b(v5_1);
            this.i = this.c.c(8);
            this.j = v0 == 0 ? v2 : v0 + v5_1 - 9 - this.i;
            return 1;
        }

        private void c() {
            this.c.a(0);
            this.l = -1;
            if(this.f) {
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
                if(!this.h && (this.g)) {
                    this.c.b(v1);
                    long v0_1 = (((long)this.c.c(v2))) << v0;
                    this.c.b(1);
                    v0_1 |= ((long)(this.c.c(v7) << v7));
                    this.c.b(1);
                    v0_1 |= ((long)this.c.c(v7));
                    this.c.b(1);
                    this.b.a(v0_1);
                    this.h = true;
                }

                this.l = this.b.a(v3);
            }
        }
    }

    class c extends d {
        private final fo b;
        private final fp c;
        private final int d;
        private int e;
        private int f;
        private int g;

        public c(ee arg2, int arg3) {
            this.a = arg2;
            super(null);
            this.b = new fo(new byte[5]);
            this.c = new fp();
            this.d = arg3;
        }

        private int a(fp arg9, int arg10) {
            int v0 = arg9.d() + arg10;
            arg10 = -1;
            while(arg9.d() < v0) {
                int v1 = arg9.f();
                int v4 = arg9.f();
                if(v1 == 5) {
                    long v4_1 = arg9.k();
                    if(v4_1 == ee.a()) {
                        arg10 = 129;
                    }
                    else if(v4_1 == ee.d()) {
                        arg10 = 135;
                    }
                    else if(v4_1 == ee.e()) {
                        arg10 = 36;
                    }
                }
                else {
                    if(v1 == 106) {
                        arg10 = 129;
                    }
                    else if(v1 == 122) {
                        arg10 = 135;
                    }
                    else if(v1 == 123) {
                        arg10 = 138;
                    }

                    arg9.d(v4);
                    continue;
                }

                break;
            }

            arg9.c(v0);
            return arg10;
        }

        public void a() {
        }

        public void a(fp arg17, boolean arg18, ce arg19) {
            dw v15_6;
            dy v15_7;
            c v0 = this;
            fp v1 = arg17;
            ce v2 = arg19;
            int v3 = 3;
            int v4 = 12;
            if(arg18) {
                v1.d(arg17.f());
                v1.a(v0.b, v3);
                v0.b.b(v4);
                v0.e = v0.b.c(v4);
                v0.f = 0;
                v0.g = ft.a(v0.b.a, 0, v3, -1);
                v0.c.a(v0.e);
            }

            int v6 = Math.min(arg17.b(), v0.e - v0.f);
            v1.a(v0.c.a, v0.f, v6);
            v0.f += v6;
            if(v0.f < v0.e) {
                return;
            }

            if(ft.a(v0.c.a, 0, v0.e, v0.g) != 0) {
                return;
            }

            v0.c.d(7);
            int v7 = 2;
            v0.c.a(v0.b, v7);
            v6 = 4;
            v0.b.b(v6);
            int v1_1 = v0.b.c(v4);
            v0.c.d(v1_1);
            int v9 = 21;
            if((ee.a(v0.a) & 16) != 0 && v0.a.c == null) {
                v0.a.c = new dy(v2.d(v9));
            }

            int v8 = v0.e - 9 - v1_1 - v6;
            while(v8 > 0) {
                v0.c.a(v0.b, 5);
                int v11 = 8;
                int v10 = v0.b.c(v11);
                v0.b.b(v3);
                int v12 = v0.b.c(13);
                v0.b.b(v6);
                int v13 = v0.b.c(v4);
                if(v10 == 6) {
                    v10 = v0.a(v0.c, v13);
                }
                else {
                    v0.c.d(v13);
                }

                v8 -= v13 + 5;
                v13 = (ee.a(v0.a) & 16) != 0 ? v10 : v12;
                if(v0.a.b.get(v13)) {
                }
                else {
                    dq v15 = null;
                    if(v10 == 15) {
                        if((ee.a(v0.a) & v7) != 0) {
                            goto label_219;
                        }

                        ds v15_8 = new ds(v2.d(v13), new cb());
                    }
                    else if(v10 == v9) {
                        if((ee.a(v0.a) & 16) != 0) {
                            v15_7 = v0.a.c;
                            goto label_219;
                        }

                        v15_7 = new dy(v2.d(ee.b(v0.a)));
                    }
                    else if(v10 == 27) {
                        if((ee.a(v0.a) & v6) != 0) {
                            goto label_219;
                        }

                        ck v14 = v2.d(v13);
                        ed v15_5 = new ed(v2.d(ee.b(v0.a)));
                        boolean v3_1 = (ee.a(v0.a) & 1) != 0 ? true : false;
                        boolean v4_1 = (ee.a(v0.a) & v11) != 0 ? true : false;
                        v15_6 = new dw(v14, v15_5, v3_1, v4_1);
                    }
                    else if(v10 == 36) {
                        dx v15_4 = new dx(v2.d(v13), new ed(v2.d(ee.b(v0.a))));
                    }
                    else if(v10 != 135) {
                        if(v10 != 138) {
                            switch(v10) {
                                case 2: {
                                    goto label_145;
                                }
                                case 3: {
                                    goto label_141;
                                }
                                case 4: {
                                    goto label_137;
                                }
                            }

                            switch(v10) {
                                case 129: {
                                    goto label_133;
                                }
                                case 130: {
                                    goto label_149;
                                }
                            }

                            goto label_219;
                        label_133:
                            v15 = new dq(v2.d(v13), false);
                            goto label_219;
                        label_145:
                            dv v15_1 = new dv(v2.d(v13));
                            goto label_219;
                        label_137:
                            dz v15_2 = new dz(v2.d(v13));
                            goto label_219;
                        label_141:
                            v15_2 = new dz(v2.d(v13));
                            goto label_219;
                        }

                    label_149:
                        dt v15_3 = new dt(v2.d(v13));
                    }
                    else {
                        v15 = new dq(v2.d(v13), true);
                    }

                label_219:
                    if((((dq)v15_6)) == null) {
                        goto label_230;
                    }

                    v0.a.b.put(v13, true);
                    v0.a.a.put(v12, new b(((du)v15_6), ee.c(v0.a)));
                }

            label_230:
                v3 = 3;
                v4 = 12;
            }

            if((ee.a(v0.a) & 16) == 0) {
                v0.a.a.remove(0);
                v0.a.a.remove(v0.d);
            label_248:
                arg19.f();
            }
            else if(!ee.d(v0.a)) {
                goto label_248;
            }

            ee.a(v0.a, true);
        }
    }

    abstract class d {
        private d() {
            super();
        }

        d(com.google.ads.interactivemedia.v3.internal.ee$1 arg1) {
            this();
        }

        public abstract void a();

        public abstract void a(fp arg1, boolean arg2, ce arg3);
    }

    final SparseArray a;
    final SparseBooleanArray b;
    dy c;
    private static final long d;
    private static final long e;
    private static final long f;
    private final ec g;
    private final int h;
    private final fp i;
    private final fo j;
    private final SparseIntArray k;
    private ce l;
    private boolean m;
    private int n;

    static {
        ee.d = ((long)ft.c("AC-3"));
        ee.e = ((long)ft.c("EAC3"));
        ee.f = ((long)ft.c("HEVC"));
    }

    public ee() {
        this(new ec(0));
    }

    public ee(ec arg2) {
        this(arg2, 0);
    }

    public ee(ec arg1, int arg2) {
        super();
        this.g = arg1;
        this.h = arg2;
        this.i = new fp(940);
        this.j = new fo(new byte[3]);
        this.a = new SparseArray();
        this.b = new SparseBooleanArray();
        this.k = new SparseIntArray();
        this.f();
    }

    static int a(ee arg0) {
        return arg0.h;
    }

    static long a() {
        return ee.d;
    }

    static boolean a(ee arg0, boolean arg1) {
        arg0.m = arg1;
        return arg1;
    }

    public int a(cd arg10, ch arg11) {
        int v3;
        int v0;
        byte[] v11 = this.i.a;
        int v1 = 188;
        if(940 - this.i.d() < v1) {
            v0 = this.i.b();
            if(v0 > 0) {
                System.arraycopy(v11, this.i.d(), v11, 0, v0);
            }

            this.i.a(v11, v0);
        }

        while(this.i.b() < v1) {
            v0 = this.i.c();
            v3 = arg10.a(v11, v0, 940 - v0);
            int v4 = -1;
            if(v3 == v4) {
                return v4;
            }

            this.i.b(v0 + v3);
        }

        int v10 = this.i.c();
        for(v0 = this.i.d(); v0 < v10; ++v0) {
            if(v11[v0] == 71) {
                break;
            }
        }

        this.i.c(v0);
        v0 += v1;
        if(v0 > v10) {
            return 0;
        }

        boolean v1_1 = true;
        this.i.d(1);
        this.i.a(this.j, 3);
        if(!this.j.b()) {
            boolean v11_1 = this.j.b();
            this.j.b(1);
            v3 = this.j.c(13);
            this.j.b(2);
            boolean v4_1 = this.j.b();
            boolean v5 = this.j.b();
            int v6 = this.j.c(4);
            if((this.h & 16) == 0) {
                int v7 = this.k.get(v3, v6 - 1);
                this.k.put(v3, v6);
                if(v7 == v6) {
                    if(v5) {
                        goto label_55;
                    }
                    else {
                        goto label_91;
                    }
                }
                else if(v6 != (v7 + 1) % 16) {
                    v6 = 1;
                }
                else {
                    goto label_91;
                }
            }
            else {
            label_91:
                v6 = 0;
            }

            if(v4_1) {
                this.i.d(this.i.f());
            }

            if(!v5) {
                goto label_55;
            }

            Object v3_1 = this.a.get(v3);
            if(v3_1 == null) {
                goto label_55;
            }

            if(v6 != 0) {
                ((d)v3_1).a();
            }

            this.i.b(v0);
            ((d)v3_1).a(this.i, v11_1, this.l);
            if(this.i.d() <= v0) {
            }
            else {
                v1_1 = false;
            }

            fe.b(v1_1);
            this.i.b(v10);
        }

    label_55:
        this.i.c(v0);
        return 0;
    }

    public void a(ce arg2) {
        this.l = arg2;
        arg2.a(cj.f);
    }

    public boolean a(cd arg7) {
        int v3;
        byte[] v0 = this.i.a;
        arg7.c(v0, 0, 940);
        int v2 = 0;
        while(true) {
        label_6:
            if(v2 >= 188) {
                return 0;
            }

            v3 = 0;
            while(true) {
            label_9:
                if(v3 == 5) {
                    goto label_11;
                }

                if(v0[v3 * 188 + v2] == 71) {
                    break;
                }

                ++v2;
                goto label_6;
            }
        }

        ++v3;
        goto label_9;
    label_11:
        arg7.b(v2);
        return 1;
    }

    static int b(ee arg2) {
        int v0 = arg2.n;
        arg2.n = v0 + 1;
        return v0;
    }

    public void b() {
        this.g.a();
        this.i.a();
        this.k.clear();
        this.f();
    }

    static ec c(ee arg0) {
        return arg0.g;
    }

    public void c() {
    }

    static long d() {
        return ee.e;
    }

    static boolean d(ee arg0) {
        return arg0.m;
    }

    static long e() {
        return ee.f;
    }

    private void f() {
        this.b.clear();
        this.a.clear();
        this.a.put(0, new a(this));
        this.c = null;
        this.n = 8192;
    }
}

