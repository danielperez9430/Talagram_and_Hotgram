package com.persianswitch.a.a.a;

import java.util.Arrays;

public final class n {
    private int a;
    private int b;
    private int c;
    private final int[] d;

    public n() {
        super();
        this.d = new int[10];
    }

    n a(int arg4, int arg5, int arg6) {
        if(arg4 >= this.d.length) {
            return this;
        }

        int v0 = 1 << arg4;
        this.a |= v0;
        int v1 = (arg5 & 1) != 0 ? this.b | v0 : this.b & (v0 ^ -1);
        this.b = v1;
        arg5 = (arg5 & 2) != 0 ? this.c | v0 : this.c & (v0 ^ -1);
        this.c = arg5;
        this.d[arg4] = arg6;
        return this;
    }

    void a() {
        this.c = 0;
        this.b = 0;
        this.a = 0;
        Arrays.fill(this.d, 0);
    }

    void a(n arg4) {
        int v0;
        for(v0 = 0; v0 < 10; ++v0) {
            if(!arg4.a(v0)) {
            }
            else {
                this.a(v0, arg4.c(v0), arg4.b(v0));
            }
        }
    }

    boolean a(int arg3) {
        boolean v0 = true;
        if((1 << arg3 & this.a) != 0) {
        }
        else {
            v0 = false;
        }

        return v0;
    }

    int b(int arg2) {
        return this.d[arg2];
    }

    int b() {
        return Integer.bitCount(this.a);
    }

    int c(int arg2) {
        int v0 = this.h(arg2) ? 2 : 0;
        if(this.g(arg2)) {
            v0 |= 1;
        }

        return v0;
    }

    int c() {
        int v0 = (this.a & 2) != 0 ? this.d[1] : -1;
        return v0;
    }

    int d(int arg2) {
        if((this.a & 16) != 0) {
            arg2 = this.d[4];
        }

        return arg2;
    }

    int e(int arg2) {
        if((this.a & 32) != 0) {
            arg2 = this.d[5];
        }

        return arg2;
    }

    int f(int arg2) {
        if((this.a & 128) != 0) {
            arg2 = this.d[7];
        }

        return arg2;
    }

    boolean g(int arg3) {
        boolean v0 = true;
        if((1 << arg3 & this.b) != 0) {
        }
        else {
            v0 = false;
        }

        return v0;
    }

    boolean h(int arg3) {
        boolean v0 = true;
        if((1 << arg3 & this.c) != 0) {
        }
        else {
            v0 = false;
        }

        return v0;
    }
}

