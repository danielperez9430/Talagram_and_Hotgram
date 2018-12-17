package com.google.ads.interactivemedia.v3.internal;

import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class an implements a {
    private final BlockingQueue a;
    private final ThreadPoolExecutor b;
    private final ArrayDeque c;
    private am d;

    public an() {
        super();
        this.c = new ArrayDeque();
        this.d = null;
        this.a = new LinkedBlockingQueue();
        this.b = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, this.a);
    }

    private void a() {
        this.d = this.c.poll();
        if(this.d != null) {
            this.d.a(this.b);
        }
    }

    public void a(am arg1) {
        this.d = null;
        this.a();
    }

    public void b(am arg2) {
        arg2.a(((a)this));
        this.c.add(arg2);
        if(this.d == null) {
            this.a();
        }
    }
}

