package com.crashlytics.android.b;

import android.annotation.SuppressLint;
import android.content.Context;
import c.a.a.a.a.b.k;
import c.a.a.a.a.b.p;
import c.a.a.a.a.e.e;
import c.a.a.a.a.g.f;
import c.a.a.a.l;
import java.util.concurrent.atomic.AtomicBoolean;

abstract class a implements j {
    private final AtomicBoolean a;
    private final AtomicBoolean b;
    private Context c;
    private c d;
    private p e;
    private f f;
    private d g;
    private c.a.a.a.a.f.c h;
    private k i;
    private e j;
    private long k;

    public a() {
        this(false);
    }

    public a(boolean arg3) {
        super();
        this.a = new AtomicBoolean();
        this.k = 0;
        this.b = new AtomicBoolean(arg3);
    }

    void a(long arg1) {
        this.k = arg1;
    }

    public void a(Context arg1, c arg2, p arg3, f arg4, d arg5, c.a.a.a.a.f.c arg6, k arg7, e arg8) {
        this.c = arg1;
        this.d = arg2;
        this.e = arg3;
        this.f = arg4;
        this.g = arg5;
        this.h = arg6;
        this.i = arg7;
        this.j = arg8;
        if(this.b()) {
            this.c();
        }
    }

    protected boolean a() {
        this.b.set(true);
        return this.a.get();
    }

    boolean b() {
        this.a.set(true);
        return this.b.get();
    }

    @SuppressLint(value={"CommitPrefEdits"}) protected void c() {
        c.a.a.a.a.f.c v0 = this.h;
        __monitor_enter(v0);
        try {
            if(this.h.a().contains("last_update_check")) {
                this.h.a(this.h.b().remove("last_update_check"));
            }

            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            try {
            label_67:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_67;
            }

            throw v1;
        }

        long v0_1 = this.i.a();
        long v2 = (((long)this.f.b)) * 1000;
        l v4 = c.a.a.a.c.h();
        v4.a("Beta", "Check for updates delay: " + v2);
        v4 = c.a.a.a.c.h();
        v4.a("Beta", "Check for updates last check time: " + this.d());
        long v4_1 = this.d() + v2;
        l v2_1 = c.a.a.a.c.h();
        v2_1.a("Beta", "Check for updates current time: " + v0_1 + ", next check time: " + v4_1);
        if(v0_1 >= v4_1) {
            try {
                this.e();
            }
            catch(Throwable v2_2) {
                this.a(v0_1);
                throw v2_2;
            }

            this.a(v0_1);
        }
        else {
            c.a.a.a.c.h().a("Beta", "Check for updates next check time was not passed");
        }
    }

    long d() {
        return this.k;
    }

    private void e() {
        c.a.a.a.c.h().a("Beta", "Performing update check");
        new com.crashlytics.android.b.e(this.d, this.d.g(), this.f.a, this.j, new g()).a(new c.a.a.a.a.b.g().a(this.c), this.e.h().get(c.a.a.a.a.b.p$a.c), this.g);
    }
}

