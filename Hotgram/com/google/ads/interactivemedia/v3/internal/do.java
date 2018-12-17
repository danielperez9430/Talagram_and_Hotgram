package com.google.ads.interactivemedia.v3.internal;

final class do extends dm implements cj {
    final class a {
        public final d a;
        public final b b;
        public final byte[] c;
        public final c[] d;
        public final int e;

        public a(d arg1, b arg2, byte[] arg3, c[] arg4, int arg5) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
            this.d = arg4;
            this.e = arg5;
        }
    }

    private a e;
    private int g;
    private long h;
    private boolean i;
    private final dk j;
    private long k;
    private d l;
    private b m;
    private long n;
    private long o;
    private long p;
    private long q;

    do() {
        super();
        this.j = new dk();
        this.k = -1;
    }

    static boolean a(fp arg1) {
        try {
            return dp.a(1, arg1, true);
        }
        catch(bl ) {
            return 0;
        }
    }

    private static int a(byte arg2, a arg3) {
        int v2 = !arg3.d[dl.a(arg2, arg3.e, 1)].a ? arg3.a.g : arg3.a.h;
        return v2;
    }

    static void a(fp arg6, long arg7) {
        arg6.b(arg6.c() + 4);
        arg6.a[arg6.c() - 4] = ((byte)(((int)(arg7 & 255))));
        arg6.a[arg6.c() - 3] = ((byte)(((int)(arg7 >>> 8 & 255))));
        arg6.a[arg6.c() - 2] = ((byte)(((int)(arg7 >>> 16 & 255))));
        arg6.a[arg6.c() - 1] = ((byte)(((int)(arg7 >>> 24 & 255))));
    }

    public int a(cd arg22, ch arg23) {
        // Method was not decompiled
    }

    a a(cd arg7, fp arg8) {
        if(this.l == null) {
            this.b.a(arg7, arg8);
            this.l = dp.a(arg8);
            arg8.a();
        }

        if(this.m == null) {
            this.b.a(arg7, arg8);
            this.m = dp.b(arg8);
            arg8.a();
        }

        this.b.a(arg7, arg8);
        byte[] v3 = new byte[arg8.c()];
        System.arraycopy(arg8.a, 0, v3, 0, arg8.c());
        c[] v4 = dp.a(arg8, this.l.b);
        int v5 = dp.a(v4.length - 1);
        arg8.a();
        return new a(this.l, this.m, v3, v4, v5);
    }

    public boolean a() {
        boolean v0 = this.e == null || this.n == -1 ? false : true;
        return v0;
    }

    public long b(long arg7) {
        if(arg7 == 0) {
            this.k = -1;
            return this.o;
        }

        this.k = this.e.a.c * arg7 / 1000000;
        return Math.max(this.o, (this.n - this.o) * arg7 / this.q - 4000);
    }

    public void b() {
        super.b();
        this.g = 0;
        this.h = 0;
        this.i = false;
    }
}

