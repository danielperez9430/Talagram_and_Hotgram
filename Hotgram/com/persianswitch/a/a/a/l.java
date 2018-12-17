package com.persianswitch.a.a.a;

import java.util.concurrent.CountDownLatch;

public final class l {
    private final CountDownLatch a;
    private long b;
    private long c;

    l() {
        super();
        this.a = new CountDownLatch(1);
        this.b = -1;
        this.c = -1;
    }

    void a() {
        if(this.b == -1) {
            this.b = System.nanoTime();
            return;
        }

        throw new IllegalStateException();
    }

    void b() {
        long v2 = -1;
        if(this.c == v2 && this.b != v2) {
            this.c = System.nanoTime();
            this.a.countDown();
            return;
        }

        throw new IllegalStateException();
    }

    void c() {
        long v2 = -1;
        if(this.c == v2 && this.b != v2) {
            this.c = this.b - 1;
            this.a.countDown();
            return;
        }

        throw new IllegalStateException();
    }
}

