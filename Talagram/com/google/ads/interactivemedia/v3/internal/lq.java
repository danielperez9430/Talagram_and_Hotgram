package com.google.ads.interactivemedia.v3.internal;

public class lq {
    private final byte[] a;
    private int b;
    private int c;

    public lq(byte[] arg8) {
        super();
        int v0 = 256;
        this.a = new byte[v0];
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            this.a[v2] = ((byte)v2);
        }

        v2 = 0;
        int v3 = 0;
        while(v2 < v0) {
            v3 = v3 + this.a[v2] + arg8[v2 % arg8.length] & 255;
            byte v4 = this.a[v2];
            this.a[v2] = this.a[v3];
            this.a[v3] = v4;
            ++v2;
        }

        this.b = 0;
        this.c = 0;
    }

    public void a(byte[] arg8) {
        int v0 = this.b;
        int v1 = this.c;
        int v2;
        for(v2 = 0; v2 < arg8.length; ++v2) {
            v0 = v0 + 1 & 255;
            v1 = v1 + this.a[v0] & 255;
            byte v3 = this.a[v0];
            this.a[v0] = this.a[v1];
            this.a[v1] = v3;
            arg8[v2] = ((byte)(arg8[v2] ^ this.a[this.a[v0] + this.a[v1] & 255]));
        }

        this.b = v0;
        this.c = v1;
    }
}

