package com.google.ads.interactivemedia.v3.internal;

public final class ec {
    private final long a;
    private long b;
    private volatile long c;

    public ec(long arg1) {
        super();
        this.a = arg1;
        this.c = -9223372036854775808L;
    }

    public long a(long arg10) {
        long v0;
        long v2 = -9223372036854775808L;
        if(this.c != v2) {
            v0 = (this.c + 4294967296L) / 8589934592L;
            long v6 = (v0 - 1) * 8589934592L + arg10;
            arg10 += v0 * 8589934592L;
            if(Math.abs(v6 - this.c) < Math.abs(arg10 - this.c)) {
                arg10 = v6;
            }
        }

        v0 = ec.b(arg10);
        if(this.a != 9223372036854775807L && this.c == v2) {
            this.b = this.a - v0;
        }

        this.c = arg10;
        return v0 + this.b;
    }

    public void a() {
        this.c = -9223372036854775808L;
    }

    public static long b(long arg2) {
        return arg2 * 1000000 / 90000;
    }
}

