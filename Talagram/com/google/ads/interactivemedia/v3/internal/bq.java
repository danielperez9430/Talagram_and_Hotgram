package com.google.ads.interactivemedia.v3.internal;

public abstract class bq implements a {
    private int a;

    public bq() {
        super();
    }

    protected void a(int arg1, long arg2, boolean arg4) {
    }

    public void a(int arg1, Object arg2) {
    }

    final void b(int arg3, long arg4, boolean arg6) {
        boolean v1 = true;
        if(this.a == 1) {
        }
        else {
            v1 = false;
        }

        fe.b(v1);
        this.a = 2;
        this.a(arg3, arg4, arg6);
    }

    protected bd b() {
        return null;
    }

    protected abstract bj b(int arg1);

    protected abstract void b(long arg1, long arg2);

    protected void c() {
    }

    protected abstract boolean c(long arg1);

    protected abstract void d(long arg1);

    protected void d() {
    }

    protected abstract boolean e();

    protected abstract boolean f();

    final int f(long arg2) {
        boolean v0 = this.a == 0 ? true : false;
        fe.b(v0);
        this.a = this.c(arg2);
        return this.a;
    }

    protected void g() {
    }

    protected abstract long q();

    protected abstract long r();

    protected abstract void s();

    protected void t() {
    }

    protected abstract int u();

    protected final int v() {
        return this.a;
    }

    final void w() {
        boolean v0 = this.a == 2 ? true : false;
        fe.b(v0);
        this.a = 3;
        this.c();
    }

    final void x() {
        boolean v0 = this.a == 3 ? true : false;
        fe.b(v0);
        this.a = 2;
        this.d();
    }

    final void y() {
        boolean v0 = this.a == 2 ? true : false;
        fe.b(v0);
        this.a = 1;
        this.g();
    }

    final void z() {
        int v1 = -1;
        boolean v0 = this.a == 2 || this.a == 3 || this.a == v1 ? false : true;
        fe.b(v0);
        this.a = v1;
        this.t();
    }
}

