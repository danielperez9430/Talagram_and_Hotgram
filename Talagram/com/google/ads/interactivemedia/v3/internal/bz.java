package com.google.ads.interactivemedia.v3.internal;

import java.io.EOFException;
import java.util.Arrays;

public final class bz implements cd {
    private final byte[] a;
    private final et b;
    private final long c;
    private long d;
    private byte[] e;
    private int f;
    private int g;

    public bz(et arg1, long arg2, long arg4) {
        super();
        this.b = arg1;
        this.d = arg2;
        this.c = arg4;
        this.e = new byte[8192];
        this.a = new byte[4096];
    }

    private int a(byte[] arg2, int arg3, int arg4, int arg5, boolean arg6) {
        if(!Thread.interrupted()) {
            int v2 = this.b.a(arg2, arg3 + arg5, arg4 - arg5);
            arg3 = -1;
            if(v2 == arg3) {
                if(arg5 == 0 && (arg6)) {
                    return arg3;
                }

                throw new EOFException();
            }

            return arg5 + v2;
        }

        throw new InterruptedException();
    }

    public int a(int arg8) {
        int v0 = this.e(arg8);
        if(v0 == 0) {
            v0 = this.a(this.a, 0, Math.min(arg8, this.a.length), 0, true);
        }

        this.g(v0);
        return v0;
    }

    public int a(byte[] arg8, int arg9, int arg10) {
        int v0 = this.d(arg8, arg9, arg10);
        if(v0 == 0) {
            v0 = this.a(arg8, arg9, arg10, 0, true);
        }

        this.g(v0);
        return v0;
    }

    public void a() {
        this.f = 0;
    }

    public boolean a(int arg8, boolean arg9) {
        int v0;
        int v5 = this.e(arg8);
        while(true) {
            v0 = -1;
            if(v5 < arg8 && v5 != v0) {
                v5 = this.a(this.a, -v5, Math.min(arg8, this.a.length + v5), v5, arg9);
                continue;
            }

            break;
        }

        this.g(v5);
        boolean v8 = v5 != v0 ? true : false;
        return v8;
    }

    public boolean a(byte[] arg8, int arg9, int arg10, boolean arg11) {
        int v0;
        int v5 = this.d(arg8, arg9, arg10);
        while(true) {
            v0 = -1;
            if(v5 < arg10 && v5 != v0) {
                v5 = this.a(arg8, arg9, arg10, v5, arg11);
                continue;
            }

            break;
        }

        this.g(v5);
        boolean v8 = v5 != v0 ? true : false;
        return v8;
    }

    public long b() {
        return this.d + (((long)this.f));
    }

    public void b(int arg2) {
        this.a(arg2, false);
    }

    public void b(byte[] arg2, int arg3, int arg4) {
        this.a(arg2, arg3, arg4, false);
    }

    public boolean b(int arg8, boolean arg9) {
        this.d(arg8);
        int v5 = Math.min(this.g - this.f, arg8);
        do {
            if(v5 >= arg8) {
                goto label_17;
            }

            v5 = this.a(this.e, this.f, arg8, v5, arg9);
        }
        while(v5 != -1);

        return 0;
    label_17:
        this.f += arg8;
        this.g = Math.max(this.g, this.f);
        return 1;
    }

    public boolean b(byte[] arg2, int arg3, int arg4, boolean arg5) {
        if(!this.b(arg4, arg5)) {
            return 0;
        }

        System.arraycopy(this.e, this.f - arg4, arg2, arg3, arg4);
        return 1;
    }

    public long c() {
        return this.d;
    }

    public void c(int arg2) {
        this.b(arg2, false);
    }

    public void c(byte[] arg2, int arg3, int arg4) {
        this.b(arg2, arg3, arg4, false);
    }

    private int d(byte[] arg3, int arg4, int arg5) {
        if(this.g == 0) {
            return 0;
        }

        arg5 = Math.min(this.g, arg5);
        System.arraycopy(this.e, 0, arg3, arg4, arg5);
        this.f(arg5);
        return arg5;
    }

    private void d(int arg3) {
        int v0 = this.f + arg3;
        if(v0 > this.e.length) {
            this.e = Arrays.copyOf(this.e, Math.max(this.e.length * 2, v0));
        }
    }

    public long d() {
        return this.c;
    }

    private int e(int arg2) {
        arg2 = Math.min(this.g, arg2);
        this.f(arg2);
        return arg2;
    }

    private void f(int arg5) {
        this.g -= arg5;
        this.f = 0;
        System.arraycopy(this.e, arg5, this.e, 0, this.g);
    }

    private void g(int arg5) {
        if(arg5 != -1) {
            this.d += ((long)arg5);
        }
    }
}

