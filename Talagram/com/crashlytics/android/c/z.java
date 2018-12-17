package com.crashlytics.android.c;

import java.util.Map$Entry;
import java.util.Map;
import java.util.TreeMap;

class z {
    final class a extends j {
        public a(f arg3, k arg4) {
            super(3, new j[]{arg3, arg4});
        }
    }

    final class b extends j {
        private final long a;
        private final long b;
        private final String c;
        private final String d;

        public b(com.crashlytics.android.c.a.a.a arg3) {
            super(4, new j[0]);
            this.a = arg3.a;
            this.b = arg3.b;
            this.c = arg3.c;
            this.d = arg3.d;
        }

        public int a() {
            return e.b(3, com.crashlytics.android.c.b.a(this.c)) + e.b(1, this.a) + e.b(2, this.b) + e.b(4, com.crashlytics.android.c.b.a(this.d));
        }

        public void a(e arg4) {
            arg4.a(1, this.a);
            arg4.a(2, this.b);
            arg4.a(3, com.crashlytics.android.c.b.a(this.c));
            arg4.a(4, com.crashlytics.android.c.b.a(this.d));
        }
    }

    final class c extends j {
        private final String a;
        private final String b;

        public c(com.crashlytics.android.c.a.a.b arg3) {
            super(2, new j[0]);
            this.a = arg3.a;
            this.b = arg3.b;
        }

        public int a() {
            int v0 = e.b(1, com.crashlytics.android.c.b.a(this.a));
            String v1 = this.b == null ? "" : this.b;
            return v0 + e.b(2, com.crashlytics.android.c.b.a(v1));
        }

        public void a(e arg3) {
            arg3.a(1, com.crashlytics.android.c.b.a(this.a));
            String v0 = this.b == null ? "" : this.b;
            arg3.a(2, com.crashlytics.android.c.b.a(v0));
        }
    }

    final class d extends j {
        private final float a;
        private final int b;
        private final boolean c;
        private final int d;
        private final long e;
        private final long f;

        public d(float arg3, int arg4, boolean arg5, int arg6, long arg7, long arg9) {
            super(5, new j[0]);
            this.a = arg3;
            this.b = arg4;
            this.c = arg5;
            this.d = arg6;
            this.e = arg7;
            this.f = arg9;
        }

        public int a() {
            return e.b(1, this.a) + e.f(2, this.b) + e.b(3, this.c) + e.d(4, this.d) + e.b(5, this.e) + e.b(6, this.f);
        }

        public void a(e arg4) {
            arg4.a(1, this.a);
            arg4.c(2, this.b);
            arg4.a(3, this.c);
            arg4.a(4, this.d);
            arg4.a(5, this.e);
            arg4.a(6, this.f);
        }
    }

    final class com.crashlytics.android.c.z$e extends j {
        private final long a;
        private final String b;

        public com.crashlytics.android.c.z$e(long arg2, String arg4, j[] arg5) {
            super(10, arg5);
            this.a = arg2;
            this.b = arg4;
        }

        public int a() {
            return e.b(1, this.a) + e.b(2, com.crashlytics.android.c.b.a(this.b));
        }

        public void a(e arg4) {
            arg4.a(1, this.a);
            arg4.a(2, com.crashlytics.android.c.b.a(this.b));
        }
    }

    final class f extends j {
        public f(l arg3, k arg4, k arg5) {
            super(1, new j[]{arg4, arg3, arg5});
        }
    }

    final class g extends j {
        private final long a;
        private final String b;
        private final String c;
        private final long d;
        private final int e;

        public g(com.crashlytics.android.c.a.a.f$a arg3) {
            super(3, new j[0]);
            this.a = arg3.a;
            this.b = arg3.b;
            this.c = arg3.c;
            this.d = arg3.d;
            this.e = arg3.e;
        }

        public int a() {
            return e.b(1, this.a) + e.b(2, com.crashlytics.android.c.b.a(this.b)) + e.b(3, com.crashlytics.android.c.b.a(this.c)) + e.b(4, this.d) + e.d(5, this.e);
        }

        public void a(e arg4) {
            arg4.a(1, this.a);
            arg4.a(2, com.crashlytics.android.c.b.a(this.b));
            arg4.a(3, com.crashlytics.android.c.b.a(this.c));
            arg4.a(4, this.d);
            arg4.a(5, this.e);
        }
    }

    final class h extends j {
        com.crashlytics.android.c.b a;

        public h(com.crashlytics.android.c.b arg3) {
            super(6, new j[0]);
            this.a = arg3;
        }

        public int a() {
            return e.b(1, this.a);
        }

        public void a(e arg3) {
            arg3.a(1, this.a);
        }
    }

    final class i extends j {
        public i() {
            super(0, new j[0]);
        }

        public int b() {
            return 0;
        }

        public void b(e arg1) {
        }
    }

    abstract class j {
        private final int a;
        private final j[] b;

        public j(int arg1, j[] arg2) {
            super();
            this.a = arg1;
            if(arg2 != null) {
            }
            else {
                arg2 = z.a();
            }

            this.b = arg2;
        }

        public int a() {
            return 0;
        }

        public void a(e arg1) {
        }

        public int b() {
            int v0 = this.c();
            return v0 + e.l(v0) + e.j(this.a);
        }

        public void b(e arg5) {
            arg5.g(this.a, 2);
            arg5.k(this.c());
            this.a(arg5);
            j[] v0 = this.b;
            int v1 = v0.length;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                v0[v2].b(arg5);
            }
        }

        public int c() {
            int v0 = this.a();
            j[] v1 = this.b;
            int v2 = v1.length;
            int v3;
            for(v3 = 0; v3 < v2; ++v3) {
                v0 += v1[v3].b();
            }

            return v0;
        }
    }

    final class k extends j {
        private final j[] a;

        public k(j[] arg3) {
            super(0, new j[0]);
            this.a = arg3;
        }

        public int b() {
            j[] v0 = this.a;
            int v1 = v0.length;
            int v2 = 0;
            int v3 = 0;
            while(v2 < v1) {
                v3 += v0[v2].b();
                ++v2;
            }

            return v3;
        }

        public void b(e arg5) {
            j[] v0 = this.a;
            int v1 = v0.length;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                v0[v2].b(arg5);
            }
        }
    }

    final class l extends j {
        private final String a;
        private final String b;
        private final long c;

        public l(com.crashlytics.android.c.a.a.e arg3) {
            super(3, new j[0]);
            this.a = arg3.a;
            this.b = arg3.b;
            this.c = arg3.c;
        }

        public int a() {
            return e.b(1, com.crashlytics.android.c.b.a(this.a)) + e.b(2, com.crashlytics.android.c.b.a(this.b)) + e.b(3, this.c);
        }

        public void a(e arg4) {
            arg4.a(1, com.crashlytics.android.c.b.a(this.a));
            arg4.a(2, com.crashlytics.android.c.b.a(this.b));
            arg4.a(3, this.c);
        }
    }

    final class m extends j {
        private final String a;
        private final int b;

        public m(com.crashlytics.android.c.a.a.f arg4, k arg5) {
            super(1, new j[]{arg5});
            this.a = arg4.a;
            this.b = arg4.b;
        }

        public int a() {
            int v0 = this.d() ? e.b(1, com.crashlytics.android.c.b.a(this.a)) : 0;
            return e.d(2, this.b) + v0;
        }

        public void a(e arg3) {
            if(this.d()) {
                arg3.a(1, com.crashlytics.android.c.b.a(this.a));
            }

            arg3.a(2, this.b);
        }

        private boolean d() {
            boolean v0 = this.a == null || this.a.length() <= 0 ? false : true;
            return v0;
        }
    }

    private static final com.crashlytics.android.c.a.a.e a;
    private static final j[] b;
    private static final m[] c;
    private static final g[] d;
    private static final b[] e;
    private static final c[] f;

    static {
        z.a = new com.crashlytics.android.c.a.a.e("", "", 0);
        z.b = new j[0];
        z.c = new m[0];
        z.d = new g[0];
        z.e = new b[0];
        z.f = new c[0];
    }

    public static void a(com.crashlytics.android.c.a.a.d arg0, u arg1, Map arg2, e arg3) {
        z.a(arg0, arg1, arg2).b(arg3);
    }

    private static com.crashlytics.android.c.z$e a(com.crashlytics.android.c.a.a.d arg6, u arg7, Map arg8) {
        i v7_1;
        com.crashlytics.android.c.a.a.e v0 = arg6.b != null ? arg6.b : z.a;
        a v0_1 = new a(new f(new l(v0), z.a(arg6.c), z.a(arg6.d)), z.a(z.a(arg6.e, arg8)));
        j v8 = z.a(arg6.f);
        com.crashlytics.android.c.b v1 = arg7.a();
        if(v1 == null) {
            c.a.a.a.c.h().a("CrashlyticsCore", "No log data to include with this event.");
        }

        arg7.b();
        if(v1 != null) {
            h v7 = new h(v1);
        }
        else {
            v7_1 = new i();
        }

        return new com.crashlytics.android.c.z$e(arg6.a, "ndk-crash", new j[]{v0_1, v8, ((h)v7_1)});
    }

    private static k a(com.crashlytics.android.c.a.a.f[] arg5) {
        m[] v0 = arg5 != null ? new m[arg5.length] : z.c;
        int v1;
        for(v1 = 0; v1 < v0.length; ++v1) {
            v0[v1] = new m(arg5[v1], z.a(arg5[v1].c));
        }

        return new k(((j[])v0));
    }

    private static k a(com.crashlytics.android.c.a.a.a[] arg4) {
        b[] v0 = arg4 != null ? new b[arg4.length] : z.e;
        int v1;
        for(v1 = 0; v1 < v0.length; ++v1) {
            v0[v1] = new b(arg4[v1]);
        }

        return new k(((j[])v0));
    }

    private static k a(com.crashlytics.android.c.a.a.b[] arg4) {
        c[] v0 = arg4 != null ? new c[arg4.length] : z.f;
        int v1;
        for(v1 = 0; v1 < v0.length; ++v1) {
            v0[v1] = new c(arg4[v1]);
        }

        return new k(((j[])v0));
    }

    private static com.crashlytics.android.c.a.a.b[] a(com.crashlytics.android.c.a.a.b[] arg5, Map arg6) {
        TreeMap v0 = new TreeMap(arg6);
        int v6 = 0;
        if(arg5 != null) {
            int v1 = arg5.length;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                ((Map)v0).put(arg5[v2].a, arg5[v2].b);
            }
        }

        Object[] v5 = ((Map)v0).entrySet().toArray(new Map$Entry[((Map)v0).size()]);
        com.crashlytics.android.c.a.a.b[] v0_1 = new com.crashlytics.android.c.a.a.b[v5.length];
        while(v6 < v0_1.length) {
            v0_1[v6] = new com.crashlytics.android.c.a.a.b(v5[v6].getKey(), v5[v6].getValue());
            ++v6;
        }

        return v0_1;
    }

    private static j a(com.crashlytics.android.c.a.a.c arg12) {
        if(arg12 == null) {
            return new i();
        }

        return new d((((float)arg12.f)) / 100f, arg12.g, arg12.h, arg12.a, arg12.b - arg12.d, arg12.c - arg12.e);
    }

    private static k a(com.crashlytics.android.c.a.a.f$a[] arg4) {
        g[] v0 = arg4 != null ? new g[arg4.length] : z.d;
        int v1;
        for(v1 = 0; v1 < v0.length; ++v1) {
            v0[v1] = new g(arg4[v1]);
        }

        return new k(((j[])v0));
    }

    static j[] a() {
        return z.b;
    }
}

