package android.arch.lifecycle;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

public class p implements g {
    class android.arch.lifecycle.p$1 implements Runnable {
        android.arch.lifecycle.p$1(p arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            p.a(this.a);
            p.b(this.a);
        }
    }

    class android.arch.lifecycle.p$2 implements a {
        android.arch.lifecycle.p$2(p arg1) {
            this.a = arg1;
            super();
        }

        public void a() {
        }

        public void b() {
            this.a.a();
        }

        public void c() {
            this.a.b();
        }
    }

    private int a;
    private int b;
    private boolean c;
    private boolean d;
    private Handler e;
    private final h f;
    private Runnable g;
    private a h;
    private static final p i;

    static {
        p.i = new p();
    }

    private p() {
        super();
        this.a = 0;
        this.b = 0;
        this.c = true;
        this.d = true;
        this.f = new h(((g)this));
        this.g = new android.arch.lifecycle.p$1(this);
        this.h = new android.arch.lifecycle.p$2(this);
    }

    static void a(Context arg1) {
        p.i.b(arg1);
    }

    static void a(p arg0) {
        arg0.e();
    }

    void a() {
        ++this.a;
        if(this.a == 1 && (this.d)) {
            this.f.a(android.arch.lifecycle.d$a.ON_START);
            this.d = false;
        }
    }

    void b(Context arg3) {
        this.e = new Handler();
        this.f.a(android.arch.lifecycle.d$a.ON_CREATE);
        arg3.getApplicationContext().registerActivityLifecycleCallbacks(new b() {
            public void onActivityCreated(Activity arg1, Bundle arg2) {
                q.b(arg1).a(p.c(this.a));
            }

            public void onActivityPaused(Activity arg1) {
                this.a.c();
            }

            public void onActivityStopped(Activity arg1) {
                this.a.d();
            }
        });
    }

    static void b(p arg0) {
        arg0.f();
    }

    void b() {
        ++this.b;
        if(this.b == 1) {
            if(this.c) {
                this.f.a(android.arch.lifecycle.d$a.ON_RESUME);
                this.c = false;
            }
            else {
                this.e.removeCallbacks(this.g);
            }
        }
    }

    static a c(p arg0) {
        return arg0.h;
    }

    void c() {
        --this.b;
        if(this.b == 0) {
            this.e.postDelayed(this.g, 700);
        }
    }

    void d() {
        --this.a;
        this.f();
    }

    private void e() {
        if(this.b == 0) {
            this.c = true;
            this.f.a(android.arch.lifecycle.d$a.ON_PAUSE);
        }
    }

    private void f() {
        if(this.a == 0 && (this.c)) {
            this.f.a(android.arch.lifecycle.d$a.ON_STOP);
            this.d = true;
        }
    }

    public d getLifecycle() {
        return this.f;
    }
}

