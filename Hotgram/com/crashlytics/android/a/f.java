package com.crashlytics.android.a;

import android.content.Context;
import c.a.a.a.a.d.d;
import c.a.a.a.a.e.e;
import c.a.a.a.a.g.b;
import c.a.a.a.c;
import c.a.a.a.i;
import java.util.concurrent.ScheduledExecutorService;

class f implements d {
    final ScheduledExecutorService a;
    ac b;
    private final i c;
    private final Context d;
    private final g e;
    private final ag f;
    private final e g;
    private final r h;

    public f(i arg2, Context arg3, g arg4, ag arg5, e arg6, ScheduledExecutorService arg7, r arg8) {
        super();
        this.b = new n();
        this.c = arg2;
        this.d = arg3;
        this.e = arg4;
        this.f = arg5;
        this.g = arg6;
        this.a = arg7;
        this.h = arg8;
    }

    public void a(a arg2) {
        this.a(arg2, false, false);
    }

    public void a(b arg2, String arg3) {
        this.b(new Runnable(arg2, arg3) {
            public void run() {
                try {
                    this.c.b.a(this.a, this.b);
                }
                catch(Exception v0) {
                    c.h().e("Answers", "Failed to set analytics settings data", ((Throwable)v0));
                }
            }
        });
    }

    public void a() {
        this.b(new Runnable() {
            public void run() {
                try {
                    ac v0_1 = this.a.b;
                    this.a.b = new n();
                    v0_1.b();
                }
                catch(Exception v0) {
                    c.h().e("Answers", "Failed to disable events", ((Throwable)v0));
                }
            }
        });
    }

    static ag a(f arg0) {
        return arg0.f;
    }

    private void a(Runnable arg4) {
        try {
            this.a.submit(arg4).get();
        }
        catch(Exception v4) {
            c.h().e("Answers", "Failed to run events task", ((Throwable)v4));
        }
    }

    void a(a arg2, boolean arg3, boolean arg4) {
        com.crashlytics.android.a.f$6 v0 = new Runnable(arg2, arg4) {
            public void run() {
                try {
                    this.c.b.a(this.a);
                    if(!this.b) {
                        return;
                    }

                    this.c.b.c();
                }
                catch(Exception v0) {
                    c.h().e("Answers", "Failed to process event", ((Throwable)v0));
                }
            }
        };
        if(arg3) {
            this.a(((Runnable)v0));
        }
        else {
            this.b(((Runnable)v0));
        }
    }

    public void a(String arg1) {
        this.b(new Runnable() {
            public void run() {
                try {
                    this.a.b.a();
                }
                catch(Exception v0) {
                    c.h().e("Answers", "Failed to send events files", ((Throwable)v0));
                }
            }
        });
    }

    public void b(a arg3) {
        this.a(arg3, false, true);
    }

    public void b() {
        this.b(new Runnable() {
            public void run() {
                try {
                    ae v7 = f.a(this.a).a();
                    z v5 = f.b(this.a).a();
                    v5.a(this.a);
                    this.a.b = new o(f.c(this.a), f.d(this.a), this.a.a, v5, f.e(this.a), v7, f.f(this.a));
                }
                catch(Exception v0) {
                    c.h().e("Answers", "Failed to enable events", ((Throwable)v0));
                }
            }
        });
    }

    static g b(f arg0) {
        return arg0.e;
    }

    private void b(Runnable arg4) {
        try {
            this.a.submit(arg4);
        }
        catch(Exception v4) {
            c.h().e("Answers", "Failed to submit events task", ((Throwable)v4));
        }
    }

    public void c() {
        this.b(new Runnable() {
            public void run() {
                try {
                    this.a.b.c();
                }
                catch(Exception v0) {
                    c.h().e("Answers", "Failed to flush events", ((Throwable)v0));
                }
            }
        });
    }

    public void c(a arg3) {
        this.a(arg3, true, false);
    }

    static i c(f arg0) {
        return arg0.c;
    }

    static Context d(f arg0) {
        return arg0.d;
    }

    static e e(f arg0) {
        return arg0.g;
    }

    static r f(f arg0) {
        return arg0.h;
    }
}

