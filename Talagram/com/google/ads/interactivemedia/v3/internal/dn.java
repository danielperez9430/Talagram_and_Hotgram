package com.google.ads.interactivemedia.v3.internal;

final class dn {
    public final byte[] a;
    private int b;
    private int c;
    private int d;

    public dn(byte[] arg2) {
        this(arg2, arg2.length);
    }

    public dn(byte[] arg1, int arg2) {
        super();
        this.a = arg1;
        this.b = arg2 * 8;
    }

    public int a(int arg12) {
        int v5;
        int v0_1;
        int v3 = 0;
        boolean v0 = this.b() + arg12 <= this.b ? true : false;
        fe.b(v0);
        if(arg12 == 0) {
            return 0;
        }

        int v1 = 255;
        int v4 = 8;
        if(this.d != 0) {
            v0_1 = Math.min(arg12, 8 - this.d);
            v5 = v1 >>> 8 - v0_1 & this.a[this.c] >>> this.d;
            this.d += v0_1;
            if(this.d == v4) {
                ++this.c;
                this.d = 0;
            }
        }
        else {
            v0_1 = 0;
            v5 = 0;
        }

        int v2 = arg12 - v0_1;
        if(v2 > 7) {
            v2 /= v4;
            while(v3 < v2) {
                byte[] v7 = this.a;
                int v8 = this.c;
                this.c = v8 + 1;
                v5 = ((int)((((long)v5)) | ((((long)v7[v8])) & 255) << v0_1));
                v0_1 += 8;
                ++v3;
            }
        }

        if(arg12 > v0_1) {
            arg12 -= v0_1;
            v5 |= (v1 >>> v4 - arg12 & this.a[this.c]) << v0_1;
            this.d += arg12;
        }

        return v5;
    }

    public boolean a() {
        boolean v0 = true;
        if(this.a(1) == 1) {
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public int b() {
        return this.c * 8 + this.d;
    }

    public void b(int arg4) {
        boolean v0 = this.b() + arg4 <= this.b ? true : false;
        fe.b(v0);
        this.c += arg4 / 8;
        this.d += arg4 % 8;
        if(this.d > 7) {
            ++this.c;
            this.d += -8;
        }
    }
}

