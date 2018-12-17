package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.util.SparseArray;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class cf implements a, bn, ce, com.google.ads.interactivemedia.v3.internal.fa$a {
    public interface com.google.ads.interactivemedia.v3.internal.cf$a {
        void a(int arg1, IOException arg2);
    }

    class b implements c {
        private final Uri a;
        private final et b;
        private final com.google.ads.interactivemedia.v3.internal.cf$c c;
        private final eq d;
        private final int e;
        private final ch f;
        private volatile boolean g;
        private boolean h;

        public b(Uri arg1, et arg2, com.google.ads.interactivemedia.v3.internal.cf$c arg3, eq arg4, int arg5, long arg6) {
            super();
            this.a = fe.a(arg1);
            this.b = fe.a(arg2);
            this.c = fe.a(arg3);
            this.d = fe.a(arg4);
            this.e = arg5;
            this.f = new ch();
            this.f.a = arg6;
            this.h = true;
        }

        public void a() {
            this.g = true;
        }

        public boolean b() {
            return this.g;
        }

        public void c() {
            bz v4_1;
            bz v5;
            int v1 = 0;
            while(true) {
                if(v1 == 0 && !this.g) {
                    bz v2 = null;
                    try {
                        long v12 = this.f.a;
                        long v4 = this.b.a(new eu(this.a, v12, -1, null));
                        if(v4 != -1) {
                            v4 += v12;
                        }

                        v5 = new bz(this.b, v12, v4);
                    }
                    catch(Throwable v0) {
                        v4_1 = v2;
                        break;
                    }

                    v4_1 = v5;
                    try {
                        cc v2_1 = this.c.a(((cd)v4_1));
                        if(this.h) {
                            v2_1.b();
                            this.h = false;
                        }

                        while(v1 == 0) {
                            if(this.g) {
                                break;
                            }

                            this.d.b(this.e);
                            v1 = v2_1.a(((cd)v4_1), this.f);
                        }
                    }
                    catch(Throwable v0) {
                        break;
                    }

                    if(v1 == 1) {
                        v1 = 0;
                    }
                    else {
                        this.f.a = ((cd)v4_1).c();
                    }

                    ft.a(this.b);
                    continue;
                }

                return;
            }

            if(v1 != 1 && v4_1 != null) {
                this.f.a = ((cd)v4_1).c();
            }

            ft.a(this.b);
            throw v0;
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.cf$c {
        private final cc[] a;
        private final ce b;
        private cc c;

        public com.google.ads.interactivemedia.v3.internal.cf$c(cc[] arg1, ce arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        public void a() {
            if(this.c != null) {
                this.c.c();
                this.c = null;
            }
        }

        public cc a(cd arg6) {
            if(this.c != null) {
                return this.c;
            }

            cc[] v0 = this.a;
            int v1 = v0.length;
            int v2 = 0;
            while(v2 < v1) {
                cc v3 = v0[v2];
                try {
                    if(v3.a(arg6)) {
                        this.c = v3;
                        goto label_12;
                    }
                    else {
                        goto label_17;
                    }
                }
                catch(EOFException ) {
                label_17:
                    arg6.a();
                    ++v2;
                    continue;
                }
                catch(Throwable v0_1) {
                    arg6.a();
                    throw v0_1;
                label_12:
                    arg6.a();
                    break;
                }

                break;
            }

            if(this.c != null) {
                this.c.a(this.b);
                return this.c;
            }

            throw new e(this.a);
        }
    }

    class d extends ca {
        public d(cf arg1, eq arg2) {
            this.a = arg1;
            super(arg2);
        }

        public void a(long arg1, int arg3, int arg4, int arg5, byte[] arg6) {
            super.a(arg1, arg3, arg4, arg5, arg6);
            cf.d(this.a);
        }
    }

    public final class e extends bl {
        public e(cc[] arg3) {
            String v3 = ft.a(((Object[])arg3));
            StringBuilder v1 = new StringBuilder(String.valueOf(v3).length() + 58);
            v1.append("None of the available extractors (");
            v1.append(v3);
            v1.append(") could read the stream.");
            super(v1.toString());
        }
    }

    private long A;
    private long B;
    private fa C;
    private b D;
    private IOException E;
    private int F;
    private long G;
    private boolean H;
    private int I;
    private int J;
    private static final List a;
    private final com.google.ads.interactivemedia.v3.internal.cf$c b;
    private final eq c;
    private final int d;
    private final SparseArray e;
    private final int f;
    private final Uri g;
    private final et h;
    private final Handler i;
    private final com.google.ads.interactivemedia.v3.internal.cf$a j;
    private final int k;
    private volatile boolean l;
    private volatile cj m;
    private volatile bu n;
    private boolean o;
    private int p;
    private bj[] q;
    private long r;
    private boolean[] s;
    private boolean[] t;
    private boolean[] u;
    private int v;
    private long w;
    private long x;
    private long y;
    private boolean z;

    static {
        cf.a = new ArrayList();
        try {
            cf.a.add(Class.forName("com.google.ads.interactivemedia.v3.internal.en").asSubclass(cc.class));
            goto label_9;
        }
        catch(ClassNotFoundException ) {
            try {
            label_9:
                cf.a.add(Class.forName("com.google.ads.interactivemedia.v3.internal.cz").asSubclass(cc.class));
                goto label_15;
            }
            catch(ClassNotFoundException ) {
                try {
                label_15:
                    cf.a.add(Class.forName("com.google.ads.interactivemedia.v3.internal.da").asSubclass(cc.class));
                    goto label_21;
                }
                catch(ClassNotFoundException ) {
                    try {
                    label_21:
                        cf.a.add(Class.forName("com.google.ads.interactivemedia.v3.internal.cs").asSubclass(cc.class));
                        goto label_27;
                    }
                    catch(ClassNotFoundException ) {
                        try {
                        label_27:
                            cf.a.add(Class.forName("com.google.ads.interactivemedia.v3.internal.dr").asSubclass(cc.class));
                            goto label_33;
                        }
                        catch(ClassNotFoundException ) {
                            try {
                            label_33:
                                cf.a.add(Class.forName("com.google.ads.interactivemedia.v3.internal.ee").asSubclass(cc.class));
                                goto label_39;
                            }
                            catch(ClassNotFoundException ) {
                                try {
                                label_39:
                                    cf.a.add(Class.forName("com.google.ads.interactivemedia.v3.internal.cm").asSubclass(cc.class));
                                    goto label_45;
                                }
                                catch(ClassNotFoundException ) {
                                    try {
                                    label_45:
                                        cf.a.add(Class.forName("com.google.ads.interactivemedia.v3.internal.di").asSubclass(cc.class));
                                        goto label_51;
                                    }
                                    catch(ClassNotFoundException ) {
                                        try {
                                        label_51:
                                            cf.a.add(Class.forName("com.google.ads.interactivemedia.v3.internal.eb").asSubclass(cc.class));
                                            goto label_57;
                                        }
                                        catch(ClassNotFoundException ) {
                                            try {
                                            label_57:
                                                cf.a.add(Class.forName("com.google.ads.interactivemedia.v3.internal.ef").asSubclass(cc.class));
                                                goto label_63;
                                            }
                                            catch(ClassNotFoundException ) {
                                                try {
                                                label_63:
                                                    cf.a.add(Class.forName("com.google.ads.interactivemedia.v3.exoplayer.ext.flac.FlacExtractor").asSubclass(cc.class));
                                                    return;
                                                }
                                                catch(ClassNotFoundException ) {
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public cf(Uri arg11, et arg12, eq arg13, int arg14, Handler arg15, com.google.ads.interactivemedia.v3.internal.cf$a arg16, int arg17, cc[] arg18) {
        this(arg11, arg12, arg13, arg14, -1, arg15, arg16, arg17, arg18);
    }

    public cf(Uri arg1, et arg2, eq arg3, int arg4, int arg5, Handler arg6, com.google.ads.interactivemedia.v3.internal.cf$a arg7, int arg8, cc[] arg9) {
        super();
        this.g = arg1;
        this.h = arg2;
        this.j = arg7;
        this.i = arg6;
        this.k = arg8;
        this.c = arg3;
        this.d = arg4;
        this.f = arg5;
        if(arg9 == null || arg9.length == 0) {
            arg9 = new cc[cf.a.size()];
            int v1 = 0;
            while(v1 < arg9.length) {
                try {
                    arg9[v1] = cf.a.get(v1).newInstance();
                    ++v1;
                    continue;
                }
                catch(IllegalAccessException v1_1) {
                    throw new IllegalStateException("Unexpected error creating default extractor", ((Throwable)v1_1));
                }
                catch(InstantiationException v1_2) {
                    throw new IllegalStateException("Unexpected error creating default extractor", ((Throwable)v1_2));
                }

                break;
            }
        }

        this.b = new com.google.ads.interactivemedia.v3.internal.cf$c(arg9, ((ce)this));
        this.e = new SparseArray();
        this.y = -9223372036854775808L;
    }

    static com.google.ads.interactivemedia.v3.internal.cf$c a(cf arg0) {
        return arg0.b;
    }

    private void a(IOException arg3) {
        if(this.i != null && this.j != null) {
            this.i.post(new Runnable(arg3) {
                public void run() {
                    cf.c(this.b).a(cf.b(this.b), this.a);
                }
            });
        }
    }

    public int a(int arg3, long arg4, bk arg6, bm arg7) {
        this.w = arg4;
        int v5 = -2;
        if(!this.t[arg3]) {
            if(this.k()) {
            }
            else {
                Object v4 = this.e.valueAt(arg3);
                if(this.s[arg3]) {
                    arg6.a = ((d)v4).c();
                    arg6.b = this.n;
                    this.s[arg3] = false;
                    return -4;
                }
                else if(((d)v4).a(arg7)) {
                    arg3 = arg7.e < this.x ? 1 : 0;
                    int v4_1 = arg7.d;
                    arg3 = arg3 != 0 ? 134217728 : 0;
                    arg7.d = arg3 | v4_1;
                    if(this.z) {
                        this.B = this.A - arg7.e;
                        this.z = false;
                    }

                    arg7.e += this.B;
                    return -3;
                }
                else {
                    if(!this.H) {
                        return v5;
                    }

                    return -1;
                }
            }
        }

        return v5;
    }

    public bj a(int arg2) {
        fe.b(this.o);
        return this.q[arg2];
    }

    public a a() {
        ++this.v;
        return this;
    }

    public void a(int arg4, long arg5) {
        fe.b(this.o);
        fe.b(this.u[arg4] ^ 1);
        ++this.p;
        this.u[arg4] = true;
        this.s[arg4] = true;
        this.t[arg4] = false;
        if(this.p == 1) {
            if(!this.m.a()) {
                arg5 = 0;
            }

            this.w = arg5;
            this.x = arg5;
            this.c(arg5);
        }
    }

    public void a(bu arg1) {
        this.n = arg1;
    }

    public void a(cj arg1) {
        this.m = arg1;
    }

    public void a(c arg1) {
        this.H = true;
    }

    public void a(c arg3, IOException arg4) {
        this.E = arg4;
        int v1 = 1;
        if(this.I > this.J) {
        }
        else {
            v1 = 1 + this.F;
        }

        this.F = v1;
        this.G = SystemClock.elapsedRealtime();
        this.a(arg4);
        this.g();
    }

    public boolean a(long arg10) {
        if(this.o) {
            return 1;
        }

        if(this.C == null) {
            this.C = new fa("Loader:ExtractorSampleSource");
        }

        this.g();
        int v0 = 0;
        if(this.m != null && (this.l) && (this.i())) {
            int v10 = this.e.size();
            this.u = new boolean[v10];
            this.t = new boolean[v10];
            this.s = new boolean[v10];
            this.q = new bj[v10];
            long v1 = -1;
            this.r = v1;
            while(v0 < v10) {
                bj v3 = this.e.valueAt(v0).c();
                this.q[v0] = v3;
                if(v3.e != v1 && v3.e > this.r) {
                    this.r = v3.e;
                }

                ++v0;
            }

            this.o = true;
            return 1;
        }

        return 0;
    }

    static int b(cf arg0) {
        return arg0.k;
    }

    public long b(int arg3) {
        if(this.t[arg3]) {
            this.t[arg3] = false;
            return this.x;
        }

        return -9223372036854775808L;
    }

    public void b() {
        int v0;
        if(this.E == null) {
            return;
        }

        if(!this.l()) {
            if(this.f != -1) {
                v0 = this.f;
            }
            else {
                if(this.m != null && !this.m.a()) {
                    v0 = 6;
                    goto label_18;
                }

                v0 = 3;
            }

        label_18:
            if(this.F <= v0) {
                return;
            }

            throw this.E;
        }

        throw this.E;
    }

    public void b(long arg6) {
        fe.b(this.o);
        int v1 = 0;
        boolean v0 = this.p > 0 ? true : false;
        fe.b(v0);
        if(!this.m.a()) {
            arg6 = 0;
        }

        long v3 = this.k() ? this.y : this.w;
        this.w = arg6;
        this.x = arg6;
        if(v3 == arg6) {
            return;
        }

        int v0_1 = this.k() ^ 1;
        int v3_1;
        for(v3_1 = 0; v0_1 != 0; ++v3_1) {
            if(v3_1 >= this.e.size()) {
                break;
            }

            v0_1 &= this.e.valueAt(v3_1).b(arg6);
        }

        if(v0_1 == 0) {
            this.c(arg6);
        }

        while(v1 < this.t.length) {
            this.t[v1] = true;
            ++v1;
        }
    }

    public void b(c arg3) {
        if(this.p > 0) {
            this.c(this.y);
        }
        else {
            this.j();
            this.c.a(0);
        }
    }

    public boolean b(int arg2, long arg3) {
        fe.b(this.o);
        fe.b(this.u[arg2]);
        this.w = arg3;
        this.e(this.w);
        if(this.H) {
            return 1;
        }

        this.g();
        if(this.k()) {
            return 0;
        }

        return this.e.valueAt(arg2).e() ^ 1;
    }

    static com.google.ads.interactivemedia.v3.internal.cf$a c(cf arg0) {
        return arg0.j;
    }

    private void c(long arg1) {
        this.y = arg1;
        this.H = false;
        if(this.C.a()) {
            this.C.b();
        }
        else {
            this.j();
            this.g();
        }
    }

    public int c() {
        return this.e.size();
    }

    public void c(int arg5) {
        fe.b(this.o);
        fe.b(this.u[arg5]);
        --this.p;
        this.u[arg5] = false;
        if(this.p == 0) {
            this.w = -9223372036854775808L;
            if(this.C.a()) {
                this.C.b();
            }
            else {
                this.j();
                this.c.a(0);
            }
        }
    }

    static int d(cf arg2) {
        int v0 = arg2.I;
        arg2.I = v0 + 1;
        return v0;
    }

    private b d(long arg10) {
        return new b(this.g, this.h, this.b, this.c, this.d, this.m.b(arg10));
    }

    public long d() {
        if(this.H) {
            return -3;
        }

        if(this.k()) {
            return this.y;
        }

        int v0 = 0;
        long v1 = -9223372036854775808L;
        long v3 = v1;
        while(v0 < this.e.size()) {
            v3 = Math.max(v3, this.e.valueAt(v0).d());
            ++v0;
        }

        if(v3 == v1) {
            v3 = this.w;
        }

        return v3;
    }

    public ck d(int arg3) {
        Object v0 = this.e.get(arg3);
        if(v0 == null) {
            d v0_1 = new d(this, this.c);
            this.e.put(arg3, v0_1);
        }

        return ((ck)v0);
    }

    private void e(long arg3) {
        int v0;
        for(v0 = 0; v0 < this.u.length; ++v0) {
            if(!this.u[v0]) {
                this.e.valueAt(v0).a(arg3);
            }
        }
    }

    public void e() {
        boolean v0 = this.v > 0 ? true : false;
        fe.b(v0);
        int v0_1 = this.v - 1;
        this.v = v0_1;
        if(v0_1 == 0 && this.C != null) {
            this.C.a(new Runnable() {
                public void run() {
                    cf.a(this.a).a();
                }
            });
            this.C = null;
        }
    }

    private long f(long arg3) {
        return Math.min((arg3 - 1) * 1000, 5000);
    }

    public void f() {
        this.l = true;
    }

    private void g() {
        // Method was not decompiled
    }

    private b h() {
        return new b(this.g, this.h, this.b, this.c, this.d, 0);
    }

    private boolean i() {
        int v1;
        for(v1 = 0; v1 < this.e.size(); ++v1) {
            if(!this.e.valueAt(v1).b()) {
                return 0;
            }
        }

        return 1;
    }

    private void j() {
        int v1;
        for(v1 = 0; v1 < this.e.size(); ++v1) {
            this.e.valueAt(v1).a();
        }

        this.D = null;
        this.E = null;
        this.F = 0;
    }

    private boolean k() {
        boolean v0 = this.y != -9223372036854775808L ? true : false;
        return v0;
    }

    private boolean l() {
        return this.E instanceof e;
    }
}

