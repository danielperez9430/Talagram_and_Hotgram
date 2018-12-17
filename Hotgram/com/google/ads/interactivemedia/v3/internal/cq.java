package com.google.ads.interactivemedia.v3.internal;

final class cq implements a {
    private final long a;
    private final int b;
    private final long c;

    public cq(long arg1, int arg3, long arg4) {
        super();
        this.a = arg1;
        this.b = arg3;
        arg1 = -1;
        if(arg4 == arg1) {
        }
        else {
            arg1 = this.a(arg4);
        }

        this.c = arg1;
    }

    public long a(long arg3) {
        return Math.max(0, arg3 - this.a) * 1000000 * 8 / (((long)this.b));
    }

    public boolean a() {
        boolean v0 = this.c != -1 ? true : false;
        return v0;
    }

    public long b() {
        return this.c;
    }

    public long b(long arg6) {
        return this.c == -1 ? 0 : arg6 * (((long)this.b)) / 8000000 + this.a;
    }
}

