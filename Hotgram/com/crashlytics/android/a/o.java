package com.crashlytics.android.a;

import android.content.Context;
import c.a.a.a.a.b.g;
import c.a.a.a.a.d.f;
import c.a.a.a.a.e.e;
import c.a.a.a.a.g.b;
import c.a.a.a.c;
import c.a.a.a.i;
import c.a.a.a.l;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

class o implements ac {
    final ae a;
    f b;
    g c;
    p d;
    boolean e;
    boolean f;
    volatile int g;
    boolean h;
    boolean i;
    private final i j;
    private final e k;
    private final Context l;
    private final z m;
    private final ScheduledExecutorService n;
    private final AtomicReference o;
    private final r p;

    public o(i arg2, Context arg3, ScheduledExecutorService arg4, z arg5, e arg6, ae arg7, r arg8) {
        super();
        this.o = new AtomicReference();
        this.c = new g();
        this.d = new u();
        this.e = true;
        this.f = true;
        this.g = -1;
        this.h = false;
        this.i = false;
        this.j = arg2;
        this.l = arg3;
        this.n = arg4;
        this.m = arg5;
        this.k = arg6;
        this.a = arg7;
        this.p = arg8;
    }

    public void a() {
        if(this.b == null) {
            c.a.a.a.a.b.i.a(this.l, "skipping files send because we don\'t yet know the target endpoint");
            return;
        }

        c.a.a.a.a.b.i.a(this.l, "Sending all files");
        List v0 = this.m.e();
        int v2 = 0;
        try {
            while(v0.size() > 0) {
                c.a.a.a.a.b.i.a(this.l, String.format(Locale.US, "attempt to send batch of %d files", Integer.valueOf(v0.size())));
                boolean v3 = this.b.a(v0);
                if(v3) {
                    v2 += v0.size();
                    this.m.a(v0);
                }

                if(!v3) {
                    break;
                }

                v0 = this.m.e();
            }
        }
        catch(Exception v0_1) {
            Context v1 = this.l;
            c.a.a.a.a.b.i.a(v1, "Failed to send batch of analytics files to server: " + v0_1.getMessage(), ((Throwable)v0_1));
        }

        if(v2 == 0) {
            this.m.g();
        }
    }

    void a(long arg9, long arg11) {
        int v0 = this.o.get() == null ? 1 : 0;
        if(v0 != 0) {
            c.a.a.a.a.d.i v2 = new c.a.a.a.a.d.i(this.l, ((c.a.a.a.a.d.e)this));
            Context v0_1 = this.l;
            c.a.a.a.a.b.i.a(v0_1, "Scheduling time based file roll over every " + arg11 + " seconds");
            try {
                this.o.set(this.n.scheduleAtFixedRate(((Runnable)v2), arg9, arg11, TimeUnit.SECONDS));
            }
            catch(RejectedExecutionException v9) {
                c.a.a.a.a.b.i.a(this.l, "Failed to schedule time based file roll over", ((Throwable)v9));
            }
        }
    }

    public void a(b arg8, String arg9) {
        this.b = j.a(new aa(this.j, arg9, arg8.a, this.k, this.c.a(this.l)));
        this.m.a(arg8);
        this.h = arg8.f;
        this.i = arg8.g;
        l v9 = c.h();
        String v0 = "Answers";
        StringBuilder v1 = new StringBuilder();
        v1.append("Firebase analytics forwarding ");
        String v2 = this.h ? "enabled" : "disabled";
        v1.append(v2);
        v9.a(v0, v1.toString());
        v9 = c.h();
        v0 = "Answers";
        v1 = new StringBuilder();
        v1.append("Firebase analytics including purchase events ");
        v2 = this.i ? "enabled" : "disabled";
        v1.append(v2);
        v9.a(v0, v1.toString());
        this.e = arg8.h;
        v9 = c.h();
        v0 = "Answers";
        v1 = new StringBuilder();
        v1.append("Custom event tracking ");
        v2 = this.e ? "enabled" : "disabled";
        v1.append(v2);
        v9.a(v0, v1.toString());
        this.f = arg8.i;
        v9 = c.h();
        v0 = "Answers";
        v1 = new StringBuilder();
        v1.append("Predefined event tracking ");
        v2 = this.f ? "enabled" : "disabled";
        v1.append(v2);
        v9.a(v0, v1.toString());
        if(arg8.k > 1) {
            c.h().a("Answers", "Event sampling enabled");
            this.d = new y(arg8.k);
        }

        this.g = arg8.b;
        this.a(0, ((long)this.g));
    }

    public void a(a arg6) {
        l v1_1;
        String v3;
        StringBuilder v2;
        String v1;
        l v0;
        ad v6 = arg6.a(this.a);
        if((this.e) || !com.crashlytics.android.a.ad$b.g.equals(v6.c)) {
            if(!this.f && (com.crashlytics.android.a.ad$b.h.equals(v6.c))) {
                v0 = c.h();
                v1 = "Answers";
                v2 = new StringBuilder();
                v3 = "Predefined events tracking disabled - skipping event: ";
                goto label_13;
            }

            if(!this.d.a(v6)) {
                goto label_39;
            }

            v0 = c.h();
            v1 = "Answers";
            v2 = new StringBuilder();
            v3 = "Skipping filtered event: ";
        }
        else {
            v0 = c.h();
            v1 = "Answers";
            v2 = new StringBuilder();
            v3 = "Custom events tracking disabled - skipping event: ";
        }

    label_13:
        v2.append(v3);
        v2.append(v6);
        v0.a(v1, v2.toString());
        return;
        try {
        label_39:
            this.m.a(v6);
        }
        catch(IOException v0_1) {
            v1_1 = c.h();
            v1_1.e("Answers", "Failed to write event: " + v6, ((Throwable)v0_1));
        }

        this.e();
        int v0_2 = (com.crashlytics.android.a.ad$b.g.equals(v6.c)) || (com.crashlytics.android.a.ad$b.h.equals(v6.c)) ? 1 : 0;
        boolean v1_2 = "purchase".equals(v6.g);
        if((this.h) && v0_2 != 0) {
            if((v1_2) && !this.i) {
                return;
            }

            try {
                this.p.a(v6);
            }
            catch(Exception v0_3) {
                v1_1 = c.h();
                v1_1.e("Answers", "Failed to map event to Firebase: " + v6, ((Throwable)v0_3));
            }
        }
    }

    public void b() {
        this.m.f();
    }

    public boolean c() {
        try {
            return this.m.d();
        }
        catch(IOException v0) {
            c.a.a.a.a.b.i.a(this.l, "Failed to roll file over.", ((Throwable)v0));
            return 0;
        }
    }

    public void d() {
        if(this.o.get() != null) {
            c.a.a.a.a.b.i.a(this.l, "Cancelling time-based rollover because no events are currently being generated.");
            this.o.get().cancel(false);
            this.o.set(null);
        }
    }

    public void e() {
        int v0 = this.g != -1 ? 1 : 0;
        if(v0 != 0) {
            this.a(((long)this.g), ((long)this.g));
        }
    }
}

