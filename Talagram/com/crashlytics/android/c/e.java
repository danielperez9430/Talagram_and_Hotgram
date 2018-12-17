package com.crashlytics.android.c;

import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

final class e implements Flushable {
    class a extends IOException {
        a() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }
    }

    private final byte[] a;
    private final int b;
    private int c;
    private final OutputStream d;

    private e(OutputStream arg1, byte[] arg2) {
        super();
        this.d = arg1;
        this.a = arg2;
        this.c = 0;
        this.b = arg2.length;
    }

    public void a(int arg2, long arg3) {
        this.g(arg2, 0);
        this.a(arg3);
    }

    public void a(int arg2, b arg3) {
        this.g(arg2, 2);
        this.a(arg3);
    }

    public void a(int arg2, int arg3) {
        this.g(arg2, 0);
        this.b(arg3);
    }

    public void a(int arg2, boolean arg3) {
        this.g(arg2, 0);
        this.a(arg3);
    }

    public void a(int arg2, float arg3) {
        this.g(arg2, 5);
        this.a(arg3);
    }

    public static e a(OutputStream arg1) {
        return e.a(arg1, 4096);
    }

    public static e a(OutputStream arg1, int arg2) {
        return new e(arg1, new byte[arg2]);
    }

    private void a() {
        if(this.d != null) {
            this.d.write(this.a, 0, this.c);
            this.c = 0;
            return;
        }

        throw new a();
    }

    public void a(byte arg4) {
        if(this.c == this.b) {
            this.a();
        }

        byte[] v0 = this.a;
        int v1 = this.c;
        this.c = v1 + 1;
        v0[v1] = arg4;
    }

    public void a(float arg1) {
        this.m(Float.floatToRawIntBits(arg1));
    }

    public void a(int arg3) {
        if(arg3 >= 0) {
            this.k(arg3);
        }
        else {
            this.c(((long)arg3));
        }
    }

    public void a(long arg1) {
        this.c(arg1);
    }

    public void a(b arg2) {
        this.k(arg2.a());
        this.c(arg2);
    }

    public void a(boolean arg1) {
        this.i(((int)arg1));
    }

    public void a(b arg7, int arg8, int arg9) {
        if(this.b - this.c >= arg9) {
            arg7.a(this.a, arg8, this.c, arg9);
            this.c += arg9;
        }
        else {
            int v0 = this.b - this.c;
            arg7.a(this.a, arg8, this.c, v0);
            arg8 += v0;
            arg9 -= v0;
            this.c = this.b;
            this.a();
            if(arg9 <= this.b) {
                arg7.a(this.a, arg8, 0, arg9);
                this.c = arg9;
            }
            else {
                InputStream v7 = arg7.b();
                long v2 = ((long)arg8);
                if(v2 != v7.skip(v2)) {
                    goto label_49;
                }

                while(true) {
                    if(arg9 <= 0) {
                        return;
                    }

                    arg8 = Math.min(arg9, this.b);
                    v0 = v7.read(this.a, 0, arg8);
                    if(v0 != arg8) {
                        break;
                    }

                    this.d.write(this.a, 0, v0);
                    arg9 -= v0;
                }

                throw new IllegalStateException("Read failed.");
            }
        }

        return;
    label_49:
        throw new IllegalStateException("Skip failed.");
    }

    public void a(byte[] arg3) {
        this.a(arg3, 0, arg3.length);
    }

    public void a(byte[] arg4, int arg5, int arg6) {
        if(this.b - this.c >= arg6) {
            System.arraycopy(arg4, arg5, this.a, this.c, arg6);
            this.c += arg6;
        }
        else {
            int v0 = this.b - this.c;
            System.arraycopy(arg4, arg5, this.a, this.c, v0);
            arg5 += v0;
            arg6 -= v0;
            this.c = this.b;
            this.a();
            if(arg6 <= this.b) {
                System.arraycopy(arg4, arg5, this.a, 0, arg6);
                this.c = arg6;
            }
            else {
                this.d.write(arg4, arg5, arg6);
            }
        }
    }

    public static int b(int arg0, b arg1) {
        return e.j(arg0) + e.b(arg1);
    }

    public static int b(int arg0, long arg1) {
        return e.j(arg0) + e.b(arg1);
    }

    public static int b(int arg0, boolean arg1) {
        return e.j(arg0) + e.b(arg1);
    }

    public static int b(int arg0, float arg1) {
        return e.j(arg0) + e.b(arg1);
    }

    public void b(int arg2, int arg3) {
        this.g(arg2, 0);
        this.c(arg3);
    }

    public static int b(float arg0) {
        return 4;
    }

    public static int b(long arg0) {
        return e.d(arg0);
    }

    public static int b(b arg1) {
        return e.l(arg1.a()) + arg1.a();
    }

    public static int b(boolean arg0) {
        return 1;
    }

    public void b(int arg1) {
        this.k(arg1);
    }

    public void c(int arg2, int arg3) {
        this.g(arg2, 0);
        this.d(arg3);
    }

    public void c(long arg6) {
        while((-128 & arg6) != 0) {
            this.i((((int)arg6)) & 127 | 128);
            arg6 >>>= 7;
        }

        this.i(((int)arg6));
    }

    public void c(b arg3) {
        this.a(arg3, 0, arg3.a());
    }

    public void c(int arg1) {
        this.a(arg1);
    }

    public static int d(int arg0, int arg1) {
        return e.j(arg0) + e.f(arg1);
    }

    public static int d(long arg5) {
        long v2 = 0;
        if((-128 & arg5) == v2) {
            return 1;
        }

        if((-16384 & arg5) == v2) {
            return 2;
        }

        if((-2097152 & arg5) == v2) {
            return 3;
        }

        if((-268435456 & arg5) == v2) {
            return 4;
        }

        if((-34359738368L & arg5) == v2) {
            return 5;
        }

        if((-4398046511104L & arg5) == v2) {
            return 6;
        }

        if((-562949953421312L & arg5) == v2) {
            return 7;
        }

        if((-72057594037927936L & arg5) == v2) {
            return 8;
        }

        if((arg5 & -9223372036854775808L) == v2) {
            return 9;
        }

        return 10;
    }

    public void d(int arg1) {
        this.k(e.n(arg1));
    }

    public static int e(int arg0, int arg1) {
        return e.j(arg0) + e.g(arg1);
    }

    public static int e(int arg0) {
        if(arg0 >= 0) {
            return e.l(arg0);
        }

        return 10;
    }

    public static int f(int arg0, int arg1) {
        return e.j(arg0) + e.h(arg1);
    }

    public static int f(int arg0) {
        return e.l(arg0);
    }

    public void flush() {
        if(this.d != null) {
            this.a();
        }
    }

    public void g(int arg1, int arg2) {
        this.k(an.a(arg1, arg2));
    }

    public static int g(int arg0) {
        return e.e(arg0);
    }

    public static int h(int arg0) {
        return e.l(e.n(arg0));
    }

    public void i(int arg1) {
        this.a(((byte)arg1));
    }

    public static int j(int arg1) {
        return e.l(an.a(arg1, 0));
    }

    public void k(int arg2) {
        while((arg2 & -128) != 0) {
            this.i(arg2 & 127 | 128);
            arg2 >>>= 7;
        }

        this.i(arg2);
    }

    public static int l(int arg1) {
        if((arg1 & -128) == 0) {
            return 1;
        }

        if((arg1 & -16384) == 0) {
            return 2;
        }

        if((-2097152 & arg1) == 0) {
            return 3;
        }

        if((arg1 & -268435456) == 0) {
            return 4;
        }

        return 5;
    }

    public void m(int arg2) {
        this.i(arg2 & 255);
        this.i(arg2 >> 8 & 255);
        this.i(arg2 >> 16 & 255);
        this.i(arg2 >> 24 & 255);
    }

    public static int n(int arg1) {
        return arg1 >> 31 ^ arg1 << 1;
    }
}

