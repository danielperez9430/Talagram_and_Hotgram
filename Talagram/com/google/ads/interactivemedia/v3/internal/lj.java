package com.google.ads.interactivemedia.v3.internal;

class lj extends la {
    static final la a;
    final transient Object[] b;
    private final transient int c;

    static {
        lj.a = new lj(new Object[0], 0);
    }

    lj(Object[] arg1, int arg2) {
        super();
        this.b = arg1;
        this.c = arg2;
    }

    int a(Object[] arg4, int arg5) {
        System.arraycopy(this.b, 0, arg4, arg5, this.c);
        return arg5 + this.c;
    }

    Object[] b() {
        return this.b;
    }

    int c() {
        return 0;
    }

    int d() {
        return this.c;
    }

    boolean f() {
        return 0;
    }

    public Object get(int arg2) {
        kr.a(arg2, this.c);
        return this.b[arg2];
    }

    public int size() {
        return this.c;
    }
}

