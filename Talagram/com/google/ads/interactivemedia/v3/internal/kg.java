package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

class kg implements ke {
    private lr a;
    private byte[] b;
    private final int c;

    public kg(int arg1) {
        super();
        this.c = arg1;
        this.a();
    }

    public void a() {
        this.b = new byte[this.c];
        this.a = lr.a(this.b);
    }

    public void a(int arg2, long arg3) {
        this.a.a(arg2, arg3);
    }

    public void a(int arg2, String arg3) {
        this.a.a(arg2, arg3);
    }

    public byte[] b() {
        int v0 = this.a.a();
        if(v0 >= 0) {
            if(v0 == 0) {
                return this.b;
            }

            byte[] v0_1 = new byte[this.b.length - v0];
            System.arraycopy(this.b, 0, v0_1, 0, v0_1.length);
            return v0_1;
        }

        throw new IOException();
    }
}

