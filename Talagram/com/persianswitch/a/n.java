package com.persianswitch.a;

import com.persianswitch.a.a.l;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class n {
    private int a;
    private int b;
    private Runnable c;
    private ExecutorService d;
    private final Deque e;
    private final Deque f;
    private final Deque g;

    public n() {
        super();
        this.a = 64;
        this.b = 5;
        this.e = new ArrayDeque();
        this.f = new ArrayDeque();
        this.g = new ArrayDeque();
    }

    private void a(Deque arg1, Object arg2, boolean arg3) {
        Runnable v2;
        __monitor_enter(this);
        try {
            if(!arg1.remove(arg2)) {
                goto label_12;
            }

            if(arg3) {
                this.c();
            }

            int v1_1 = this.b();
            v2 = this.c;
            __monitor_exit(this);
            if(v1_1 == 0) {
                goto label_9;
            }

            return;
        }
        catch(Throwable v1) {
            goto label_17;
        }

    label_9:
        if(v2 != null) {
            v2.run();
        }

        return;
        try {
        label_12:
            throw new AssertionError("Call wasn\'t in-flight!");
        label_17:
            __monitor_exit(this);
        }
        catch(Throwable v1) {
            goto label_17;
        }

        throw v1;
    }

    public ExecutorService a() {
        ExecutorService v0_1;
        __monitor_enter(this);
        try {
            if(this.d == null) {
                this.d = new ThreadPoolExecutor(0, 2147483647, 60, TimeUnit.SECONDS, new SynchronousQueue(), l.a("OkHttp Dispatcher", false));
            }

            v0_1 = this.d;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    void a(a arg3) {
        this.a(this.f, arg3, true);
    }

    void a(w arg2) {
        __monitor_enter(this);
        try {
            this.g.add(arg2);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    public int b() {
        int v1;
        int v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.f.size();
            v1 = this.g.size();
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1 + v1;
    }

    private int b(a arg5) {
        Iterator v0 = this.f.iterator();
        int v1;
        for(v1 = 0; v0.hasNext(); ++v1) {
            if(!v0.next().a().equals(arg5.a())) {
                continue;
            }
        }

        return v1;
    }

    void b(w arg3) {
        this.a(this.g, arg3, false);
    }

    private void c() {
        if(this.f.size() >= this.a) {
            return;
        }

        if(this.e.isEmpty()) {
            return;
        }

        Iterator v0 = this.e.iterator();
        do {
            if(!v0.hasNext()) {
                return;
            }

            Object v1 = v0.next();
            if(this.b(((a)v1)) < this.b) {
                v0.remove();
                this.f.add(v1);
                this.a().execute(((Runnable)v1));
            }
        }
        while(this.f.size() < this.a);
    }
}

