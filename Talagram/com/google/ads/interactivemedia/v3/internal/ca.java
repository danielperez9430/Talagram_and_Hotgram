package com.google.ads.interactivemedia.v3.internal;

public class ca implements ck {
    private final ci a;
    private final bm b;
    private boolean c;
    private long d;
    private long e;
    private volatile long f;
    private volatile bj g;

    public ca(eq arg3) {
        super();
        this.a = new ci(arg3);
        this.b = new bm(0);
        this.c = true;
        this.d = -9223372036854775808L;
        this.e = -9223372036854775808L;
        this.f = -9223372036854775808L;
    }

    public int a(cd arg2, int arg3, boolean arg4) {
        return this.a.a(arg2, arg3, arg4);
    }

    public void a() {
        this.a.a();
        this.c = true;
        this.d = -9223372036854775808L;
        this.e = -9223372036854775808L;
        this.f = -9223372036854775808L;
    }

    public void a(long arg4) {
        while(this.a.a(this.b)) {
            if(this.b.e >= arg4) {
                break;
            }

            this.a.b();
            this.c = true;
        }

        this.d = -9223372036854775808L;
    }

    public void a(long arg12, int arg14, int arg15, int arg16, byte[] arg17) {
        this.f = Math.max(this.f, arg12);
        this.a.a(arg12, arg14, this.a.c() - (((long)arg15)) - (((long)arg16)), arg15, arg17);
    }

    public void a(bj arg1) {
        this.g = arg1;
    }

    public void a(fp arg2, int arg3) {
        this.a.a(arg2, arg3);
    }

    public boolean a(bm arg3) {
        if(!this.f()) {
            return 0;
        }

        this.a.b(arg3);
        this.c = false;
        this.d = arg3.e;
        return 1;
    }

    public boolean b() {
        boolean v0 = this.g != null ? true : false;
        return v0;
    }

    public boolean b(long arg2) {
        return this.a.a(arg2);
    }

    public bj c() {
        return this.g;
    }

    public long d() {
        return this.f;
    }

    public boolean e() {
        return this.f() ^ 1;
    }

    private boolean f() {
        boolean v0 = this.a.a(this.b);
        if(this.c) {
            while(v0) {
                if(this.b.c()) {
                    break;
                }

                this.a.b();
                v0 = this.a.a(this.b);
            }
        }

        if(!v0) {
            return 0;
        }

        if(this.e != -9223372036854775808L && this.b.e >= this.e) {
            return 0;
        }

        return 1;
    }
}

