package com.google.ads.interactivemedia.v3.internal;

import java.nio.charset.Charset;

public final class fp {
    public byte[] a;
    private int b;
    private int c;

    public fp(int arg1) {
        super();
        this.a = new byte[arg1];
        this.c = this.a.length;
    }

    public fp(byte[] arg1) {
        super();
        this.a = arg1;
        this.c = arg1.length;
    }

    public fp() {
        super();
    }

    public fp(byte[] arg1, int arg2) {
        super();
        this.a = arg1;
        this.c = arg2;
    }

    public void a(byte[] arg1, int arg2) {
        this.a = arg1;
        this.c = arg2;
        this.b = 0;
    }

    public void a(byte[] arg3, int arg4, int arg5) {
        System.arraycopy(this.a, this.b, arg3, arg4, arg5);
        this.b += arg5;
    }

    public String a(int arg4, Charset arg5) {
        String v0 = new String(this.a, this.b, arg4, arg5);
        this.b += arg4;
        return v0;
    }

    public void a() {
        this.b = 0;
        this.c = 0;
    }

    public void a(int arg2) {
        byte[] v0 = this.e() < arg2 ? new byte[arg2] : this.a;
        this.a(v0, arg2);
    }

    public void a(fo arg3, int arg4) {
        this.a(arg3.a, 0, arg4);
        arg3.a(0);
    }

    public int b() {
        return this.c - this.b;
    }

    public void b(int arg2) {
        boolean v0 = arg2 < 0 || arg2 > this.a.length ? false : true;
        fe.a(v0);
        this.c = arg2;
    }

    public void c(int arg2) {
        boolean v0 = arg2 < 0 || arg2 > this.c ? false : true;
        fe.a(v0);
        this.b = arg2;
    }

    public int c() {
        return this.c;
    }

    public void d(int arg2) {
        this.c(this.b + arg2);
    }

    public int d() {
        return this.b;
    }

    public String e(int arg2) {
        return this.a(arg2, Charset.defaultCharset());
    }

    public int e() {
        int v0 = this.a == null ? 0 : this.a.length;
        return v0;
    }

    public int f() {
        byte[] v0 = this.a;
        int v1 = this.b;
        this.b = v1 + 1;
        return v0[v1] & 255;
    }

    public int g() {
        byte[] v0 = this.a;
        int v1 = this.b;
        this.b = v1 + 1;
        int v0_1 = (v0[v1] & 255) << 8;
        byte[] v1_1 = this.a;
        int v2 = this.b;
        this.b = v2 + 1;
        return v0_1 | v1_1[v2] & 255;
    }

    public int h() {
        byte[] v0 = this.a;
        int v1 = this.b;
        this.b = v1 + 1;
        int v0_1 = v0[v1] & 255;
        byte[] v1_1 = this.a;
        int v2 = this.b;
        this.b = v2 + 1;
        return v0_1 | (v1_1[v2] & 255) << 8;
    }

    public short i() {
        byte[] v0 = this.a;
        int v1 = this.b;
        this.b = v1 + 1;
        int v0_1 = (v0[v1] & 255) << 8;
        byte[] v1_1 = this.a;
        int v2 = this.b;
        this.b = v2 + 1;
        return ((short)(v0_1 | v1_1[v2] & 255));
    }

    public int j() {
        byte[] v0 = this.a;
        int v1 = this.b;
        this.b = v1 + 1;
        int v0_1 = (v0[v1] & 255) << 16;
        byte[] v1_1 = this.a;
        int v2 = this.b;
        this.b = v2 + 1;
        v0_1 |= (v1_1[v2] & 255) << 8;
        v1_1 = this.a;
        v2 = this.b;
        this.b = v2 + 1;
        return v0_1 | v1_1[v2] & 255;
    }

    public long k() {
        byte[] v0 = this.a;
        int v1 = this.b;
        this.b = v1 + 1;
        long v0_1 = ((((long)v0[v1])) & 255) << 24;
        byte[] v4 = this.a;
        int v5 = this.b;
        this.b = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 16;
        v4 = this.a;
        v5 = this.b;
        this.b = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 8;
        v4 = this.a;
        v5 = this.b;
        this.b = v5 + 1;
        return v0_1 | 255 & (((long)v4[v5]));
    }

    public long l() {
        byte[] v0 = this.a;
        int v1 = this.b;
        this.b = v1 + 1;
        long v0_1 = (((long)v0[v1])) & 255;
        byte[] v4 = this.a;
        int v5 = this.b;
        this.b = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 8;
        v4 = this.a;
        v5 = this.b;
        this.b = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 16;
        v4 = this.a;
        v5 = this.b;
        this.b = v5 + 1;
        return v0_1 | (255 & (((long)v4[v5]))) << 24;
    }

    public int m() {
        byte[] v0 = this.a;
        int v1 = this.b;
        this.b = v1 + 1;
        int v0_1 = (v0[v1] & 255) << 24;
        byte[] v1_1 = this.a;
        int v2 = this.b;
        this.b = v2 + 1;
        v0_1 |= (v1_1[v2] & 255) << 16;
        v1_1 = this.a;
        v2 = this.b;
        this.b = v2 + 1;
        v0_1 |= (v1_1[v2] & 255) << 8;
        v1_1 = this.a;
        v2 = this.b;
        this.b = v2 + 1;
        return v0_1 | v1_1[v2] & 255;
    }

    public int n() {
        byte[] v0 = this.a;
        int v1 = this.b;
        this.b = v1 + 1;
        int v0_1 = v0[v1] & 255;
        byte[] v1_1 = this.a;
        int v2 = this.b;
        this.b = v2 + 1;
        v0_1 |= (v1_1[v2] & 255) << 8;
        v1_1 = this.a;
        v2 = this.b;
        this.b = v2 + 1;
        v0_1 |= (v1_1[v2] & 255) << 16;
        v1_1 = this.a;
        v2 = this.b;
        this.b = v2 + 1;
        return v0_1 | (v1_1[v2] & 255) << 24;
    }

    public long o() {
        byte[] v0 = this.a;
        int v1 = this.b;
        this.b = v1 + 1;
        long v0_1 = ((((long)v0[v1])) & 255) << 56;
        byte[] v4 = this.a;
        int v5 = this.b;
        this.b = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 48;
        v4 = this.a;
        v5 = this.b;
        this.b = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 40;
        v4 = this.a;
        v5 = this.b;
        this.b = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 32;
        v4 = this.a;
        v5 = this.b;
        this.b = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 24;
        v4 = this.a;
        v5 = this.b;
        this.b = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 16;
        v4 = this.a;
        v5 = this.b;
        this.b = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 8;
        v4 = this.a;
        v5 = this.b;
        this.b = v5 + 1;
        return v0_1 | 255 & (((long)v4[v5]));
    }

    public long p() {
        byte[] v0 = this.a;
        int v1 = this.b;
        this.b = v1 + 1;
        long v0_1 = (((long)v0[v1])) & 255;
        byte[] v4 = this.a;
        int v5 = this.b;
        this.b = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 8;
        v4 = this.a;
        v5 = this.b;
        this.b = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 16;
        v4 = this.a;
        v5 = this.b;
        this.b = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 24;
        v4 = this.a;
        v5 = this.b;
        this.b = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 32;
        v4 = this.a;
        v5 = this.b;
        this.b = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 40;
        v4 = this.a;
        v5 = this.b;
        this.b = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 48;
        v4 = this.a;
        v5 = this.b;
        this.b = v5 + 1;
        return v0_1 | (255 & (((long)v4[v5]))) << 56;
    }

    public int q() {
        byte[] v0 = this.a;
        int v1 = this.b;
        this.b = v1 + 1;
        int v0_1 = (v0[v1] & 255) << 8;
        byte[] v1_1 = this.a;
        int v2 = this.b;
        this.b = v2 + 1;
        v0_1 |= v1_1[v2] & 255;
        this.b += 2;
        return v0_1;
    }

    public int r() {
        return this.f() << 21 | this.f() << 14 | this.f() << 7 | this.f();
    }

    public int s() {
        int v0 = this.m();
        if(v0 >= 0) {
            return v0;
        }

        StringBuilder v3 = new StringBuilder(29);
        v3.append("Top bit not zero: ");
        v3.append(v0);
        throw new IllegalStateException(v3.toString());
    }

    public int t() {
        int v0 = this.n();
        if(v0 >= 0) {
            return v0;
        }

        StringBuilder v3 = new StringBuilder(29);
        v3.append("Top bit not zero: ");
        v3.append(v0);
        throw new IllegalStateException(v3.toString());
    }

    public long u() {
        long v0 = this.o();
        if(v0 >= 0) {
            return v0;
        }

        StringBuilder v4 = new StringBuilder(38);
        v4.append("Top bit not zero: ");
        v4.append(v0);
        throw new IllegalStateException(v4.toString());
    }

    public double v() {
        return Double.longBitsToDouble(this.o());
    }

    public long w() {
        StringBuilder v4_1;
        int v5;
        int v4;
        long v0 = ((long)this.a[this.b]);
        int v2 = 7;
        int v3 = 7;
        while(true) {
            v4 = 6;
            v5 = 1;
            if(v3 >= 0) {
                int v6 = 1 << v3;
                if(((((long)v6)) & v0) != 0) {
                    --v3;
                    continue;
                }
                else if(v3 < v4) {
                    v0 &= ((long)(v6 - 1));
                    v2 -= v3;
                }
                else if(v3 == v2) {
                    v2 = 1;
                }
                else {
                    break;
                }
            }
            else {
                break;
            }

            goto label_26;
        }

        v2 = 0;
    label_26:
        if(v2 == 0) {
            v4_1 = new StringBuilder(55);
            v4_1.append("Invalid UTF-8 sequence first byte: ");
            v4_1.append(v0);
            throw new NumberFormatException(v4_1.toString());
        }

        while(true) {
            if(v5 >= v2) {
                goto label_51;
            }

            v3 = this.a[this.b + v5];
            if((v3 & 192) != 128) {
                break;
            }

            v0 = v0 << v4 | (((long)(v3 & 63)));
            ++v5;
        }

        v4_1 = new StringBuilder(62);
        v4_1.append("Invalid UTF-8 sequence continuation byte: ");
        v4_1.append(v0);
        throw new NumberFormatException(v4_1.toString());
    label_51:
        this.b += v2;
        return v0;
    }
}

