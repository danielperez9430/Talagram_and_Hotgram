package com.e.a;

import android.graphics.Bitmap;
import android.os.Handler;

class r {
    final c a;
    final Handler b;
    long c;
    long d;
    long e;
    long f;
    long g;
    long h;
    long i;
    long j;
    int k;
    int l;
    int m;

    void a() {
        this.b.sendEmptyMessage(0);
    }

    void a(Bitmap arg2) {
        this.a(arg2, 2);
    }

    private void a(Bitmap arg4, int arg5) {
        this.b.sendMessage(this.b.obtainMessage(arg5, v.a(arg4), 0));
    }

    void a(long arg3) {
        this.b.sendMessage(this.b.obtainMessage(4, Long.valueOf(arg3)));
    }

    void b(Bitmap arg2) {
        this.a(arg2, 3);
    }

    void b() {
        this.b.sendEmptyMessage(1);
    }

    s c() {
        return new s(this.a.b(), this.a.a(), this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, System.currentTimeMillis());
    }
}

