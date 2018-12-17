package com.google.ads.interactivemedia.v3.internal;

final class el {
    private final fp a;
    private int b;

    public el() {
        super();
        this.a = new fp(8);
    }

    public boolean a(cd arg19) {
        // Method was not decompiled
    }

    private long b(cd arg7) {
        int v1 = 0;
        arg7.c(this.a.a, 0, 1);
        int v0 = this.a.a[0] & 255;
        if(v0 == 0) {
            return -9223372036854775808L;
        }

        int v3 = 128;
        int v4;
        for(v4 = 0; (v0 & v3) == 0; ++v4) {
            v3 >>= 1;
        }

        v0 &= v3 ^ -1;
        arg7.c(this.a.a, 1, v4);
        while(v1 < v4) {
            ++v1;
            v0 = (this.a.a[v1] & 255) + (v0 << 8);
        }

        this.b += v4 + 1;
        return ((long)v0);
    }
}

