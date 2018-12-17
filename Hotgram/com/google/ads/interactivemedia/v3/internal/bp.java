package com.google.ads.interactivemedia.v3.internal;

import android.os.SystemClock;

final class bp implements bd {
    private boolean a;
    private long b;
    private long c;

    bp() {
        super();
    }

    public void a(long arg1) {
        this.b = arg1;
        this.c = this.b(arg1);
    }

    public long a() {
        long v0 = this.a ? this.b(this.c) : this.b;
        return v0;
    }

    public void b() {
        if(!this.a) {
            this.a = true;
            this.c = this.b(this.b);
        }
    }

    private long b(long arg5) {
        return SystemClock.elapsedRealtime() * 1000 - arg5;
    }

    public void c() {
        if(this.a) {
            this.b = this.b(this.c);
            this.a = false;
        }
    }
}

