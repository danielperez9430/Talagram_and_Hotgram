package com.crashlytics.android.a;

import c.a.a.a.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

class l {
    public interface a {
        void a();
    }

    final AtomicReference a;
    boolean b;
    private final ScheduledExecutorService c;
    private final List d;
    private volatile boolean e;

    public l(ScheduledExecutorService arg3) {
        super();
        this.d = new ArrayList();
        this.e = true;
        this.a = new AtomicReference();
        this.b = true;
        this.c = arg3;
    }

    static void a(l arg0) {
        arg0.c();
    }

    public void a() {
        this.b = false;
        Object v1 = this.a.getAndSet(null);
        if(v1 != null) {
            ((ScheduledFuture)v1).cancel(false);
        }
    }

    public void a(a arg2) {
        this.d.add(arg2);
    }

    public void a(boolean arg1) {
        this.e = arg1;
    }

    public void b() {
        if((this.e) && !this.b) {
            this.b = true;
            try {
                this.a.compareAndSet(null, this.c.schedule(new Runnable() {
                    public void run() {
                        this.a.a.set(null);
                        l.a(this.a);
                    }
                }, 5000, TimeUnit.MILLISECONDS));
            }
            catch(RejectedExecutionException v0) {
                c.h().a("Answers", "Failed to schedule background detector", ((Throwable)v0));
            }
        }
    }

    private void c() {
        Iterator v0 = this.d.iterator();
        while(v0.hasNext()) {
            v0.next().a();
        }
    }
}

