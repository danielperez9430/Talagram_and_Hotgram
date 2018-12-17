package com.d.a.b;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

class f {
    final e a;
    private Executor b;
    private Executor c;
    private Executor d;
    private final Map e;
    private final Map f;
    private final AtomicBoolean g;
    private final AtomicBoolean h;
    private final AtomicBoolean i;
    private final Object j;

    f(e arg3) {
        super();
        this.e = Collections.synchronizedMap(new HashMap());
        this.f = new WeakHashMap();
        this.g = new AtomicBoolean(false);
        this.h = new AtomicBoolean(false);
        this.i = new AtomicBoolean(false);
        this.j = new Object();
        this.a = arg3;
        this.b = arg3.g;
        this.c = arg3.h;
        this.d = a.a();
    }

    String a(com.d.a.b.e.a arg2) {
        return this.e.get(Integer.valueOf(arg2.f()));
    }

    void a(com.d.a.b.e.a arg2, String arg3) {
        this.e.put(Integer.valueOf(arg2.f()), arg3);
    }

    ReentrantLock a(String arg3) {
        Object v0 = this.f.get(arg3);
        if(v0 == null) {
            ReentrantLock v0_1 = new ReentrantLock();
            this.f.put(arg3, v0_1);
        }

        return ((ReentrantLock)v0);
    }

    void a(i arg2) {
        this.e();
        this.c.execute(((Runnable)arg2));
    }

    void a(h arg3) {
        this.d.execute(new Runnable(arg3) {
            public void run() {
                File v0 = this.b.a.o.a(this.a.a());
                int v0_1 = v0 == null || !v0.exists() ? 0 : 1;
                f.a(this.b);
                Executor v0_2 = v0_1 != 0 ? f.b(this.b) : f.c(this.b);
                v0_2.execute(this.a);
            }
        });
    }

    static void a(f arg0) {
        arg0.e();
    }

    AtomicBoolean a() {
        return this.g;
    }

    void a(Runnable arg2) {
        this.d.execute(arg2);
    }

    void b(com.d.a.b.e.a arg2) {
        this.e.remove(Integer.valueOf(arg2.f()));
    }

    static Executor b(f arg0) {
        return arg0.c;
    }

    Object b() {
        return this.j;
    }

    static Executor c(f arg0) {
        return arg0.b;
    }

    boolean c() {
        return this.h.get();
    }

    boolean d() {
        return this.i.get();
    }

    private void e() {
        if(!this.a.i && (this.b.isShutdown())) {
            this.b = this.f();
        }

        if(!this.a.j && (this.c.isShutdown())) {
            this.c = this.f();
        }
    }

    private Executor f() {
        return a.a(this.a.k, this.a.l, this.a.m);
    }
}

