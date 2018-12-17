package com.google.ads.interactivemedia.v3.internal;

import java.util.Arrays;

final class ea {
    public byte[] a;
    public int b;
    private final int c;
    private boolean d;
    private boolean e;

    public ea(int arg2, int arg3) {
        super();
        this.c = arg2;
        this.a = new byte[arg3 + 3];
        this.a[2] = 1;
    }

    public void a() {
        this.d = false;
        this.e = false;
    }

    public void a(int arg4) {
        boolean v1 = true;
        fe.b(this.d ^ 1);
        if(arg4 == this.c) {
        }
        else {
            v1 = false;
        }

        this.d = v1;
        if(this.d) {
            this.b = 3;
            this.e = false;
        }
    }

    public void a(byte[] arg3, int arg4, int arg5) {
        if(!this.d) {
            return;
        }

        arg5 -= arg4;
        if(this.a.length < this.b + arg5) {
            this.a = Arrays.copyOf(this.a, (this.b + arg5) * 2);
        }

        System.arraycopy(arg3, arg4, this.a, this.b, arg5);
        this.b += arg5;
    }

    public boolean b() {
        return this.e;
    }

    public boolean b(int arg3) {
        if(!this.d) {
            return 0;
        }

        this.b -= arg3;
        this.d = false;
        this.e = true;
        return 1;
    }
}

