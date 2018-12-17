package com.crashlytics.android.a;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import c.a.a.a.a.b.n;
import c.a.a.a.a.b.p;
import c.a.a.a.a.e.e;
import c.a.a.a.a.f.b;
import c.a.a.a.c;
import java.util.concurrent.ScheduledExecutorService;

class ab implements a {
    final f a;
    final c.a.a.a.a b;
    final l c;
    final i d;
    private final long e;

    ab(f arg1, c.a.a.a.a arg2, l arg3, i arg4, long arg5) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
        this.e = arg5;
    }

    public static ab a(c.a.a.a.i arg13, Context arg14, p arg15, String arg16, String arg17, long arg18) {
        ag v4 = new ag(arg14, arg15, arg16, arg17);
        g v3 = new g(arg14, new b(arg13));
        c.a.a.a.a.e.b v5 = new c.a.a.a.a.e.b(c.h());
        c.a.a.a.a v9 = new c.a.a.a.a(arg14);
        ScheduledExecutorService v6 = n.b("Answers Events Handler");
        return new ab(new f(arg13, arg14, v3, v4, ((e)v5), v6, new r(arg14)), v9, new l(v6), i.a(arg14), arg18);
    }

    public void a() {
        c.h().a("Answers", "Flush events when app is backgrounded");
        this.a.c();
    }

    public void a(long arg4) {
        c.h().a("Answers", "Logged install");
        this.a.b(ad.a(arg4));
    }

    public void a(Activity arg5, com.crashlytics.android.a.ad$b arg6) {
        c.a.a.a.l v0 = c.h();
        v0.a("Answers", "Logged lifecycle event: " + arg6.name());
        this.a.a(ad.a(arg6, arg5));
    }

    public void a(c.a.a.a.a.g.b arg3, String arg4) {
        this.c.a(arg3.j);
        this.a.a(arg3, arg4);
    }

    public void a(m arg5) {
        c.a.a.a.l v0 = c.h();
        v0.a("Answers", "Logged custom event: " + arg5);
        this.a.a(ad.a(arg5));
    }

    public void a(String arg4, String arg5) {
        if(Looper.myLooper() != Looper.getMainLooper()) {
            c.h().a("Answers", "Logged crash");
            this.a.c(ad.a(arg4, arg5));
            return;
        }

        throw new IllegalStateException("onCrash called from main thread!!!");
    }

    public void b() {
        this.a.b();
        this.b.a(new h(this, this.c));
        this.c.a(((a)this));
        if(this.d()) {
            this.a(this.e);
            this.d.a();
        }
    }

    public void c() {
        this.b.a();
        this.a.a();
    }

    boolean d() {
        return this.d.b() ^ 1;
    }
}

