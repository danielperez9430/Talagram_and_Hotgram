package com.crashlytics.android.c;

import android.content.Context;
import android.util.Log;
import c.a.a.a.a.b.n;
import c.a.a.a.a.b.o;
import c.a.a.a.a.b.p;
import c.a.a.a.a.c.d;
import c.a.a.a.a.c.m;
import c.a.a.a.a.e.e;
import c.a.a.a.a.g.q;
import c.a.a.a.a.g.t;
import c.a.a.a.c;
import c.a.a.a.l;
import com.crashlytics.android.c.a.a;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@d(a={a.class}) public class i extends c.a.a.a.i {
    final class com.crashlytics.android.c.i$a implements Callable {
        private final j a;

        public com.crashlytics.android.c.i$a(j arg1) {
            super();
            this.a = arg1;
        }

        public Boolean a() {
            if(!this.a.b()) {
                return Boolean.FALSE;
            }

            c.h().a("CrashlyticsCore", "Found previous crash marker.");
            this.a.c();
            return Boolean.TRUE;
        }

        public Object call() {
            return this.a();
        }
    }

    final class b implements k {
        b(com.crashlytics.android.c.i$1 arg1) {
            this();
        }

        private b() {
            super();
        }

        public void a() {
        }
    }

    private final long a;
    private final ConcurrentHashMap b;
    private j c;
    private j d;
    private k k;
    private h l;
    private String m;
    private String n;
    private String o;
    private float p;
    private boolean q;
    private final aa r;
    private e s;
    private g t;
    private a u;

    public i() {
        this(1f, null, null, false);
    }

    i(float arg8, k arg9, aa arg10, boolean arg11) {
        this(arg8, arg9, arg10, arg11, n.a("Crashlytics Exception Handler"));
    }

    i(float arg2, k arg3, aa arg4, boolean arg5, ExecutorService arg6) {
        b v3;
        super();
        String v0 = null;
        this.m = v0;
        this.n = v0;
        this.o = v0;
        this.p = arg2;
        if(arg3 != null) {
        }
        else {
            v3 = new b(((com.crashlytics.android.c.i$1)v0));
        }

        this.k = ((k)v3);
        this.r = arg4;
        this.q = arg5;
        this.t = new g(arg6);
        this.b = new ConcurrentHashMap();
        this.a = System.currentTimeMillis();
    }

    public String a() {
        return "2.4.1.19";
    }

    static j a(i arg0) {
        return arg0.c;
    }

    static boolean a(String arg2, boolean arg3) {
        if(!arg3) {
            c.h().a("CrashlyticsCore", "Configured not to require a build ID.");
            return 1;
        }

        if(!c.a.a.a.a.b.i.d(arg2)) {
            return 1;
        }

        Log.e("CrashlyticsCore", ".");
        Log.e("CrashlyticsCore", ".     |  | ");
        Log.e("CrashlyticsCore", ".     |  |");
        Log.e("CrashlyticsCore", ".     |  |");
        Log.e("CrashlyticsCore", ".   \\ |  | /");
        Log.e("CrashlyticsCore", ".    \\    /");
        Log.e("CrashlyticsCore", ".     \\  /");
        Log.e("CrashlyticsCore", ".      \\/");
        Log.e("CrashlyticsCore", ".");
        Log.e("CrashlyticsCore", "This app relies on Crashlytics. Please sign up for access at https://fabric.io/sign_up,\ninstall an Android build tool and ask a team member to invite you to this app\'s organization.");
        Log.e("CrashlyticsCore", ".");
        Log.e("CrashlyticsCore", ".      /\\");
        Log.e("CrashlyticsCore", ".     /  \\");
        Log.e("CrashlyticsCore", ".    /    \\");
        Log.e("CrashlyticsCore", ".   / |  | \\");
        Log.e("CrashlyticsCore", ".     |  |");
        Log.e("CrashlyticsCore", ".     |  |");
        Log.e("CrashlyticsCore", ".     |  |");
        Log.e("CrashlyticsCore", ".");
        return 0;
    }

    boolean a(Context arg17) {
        c.a.a.a.a.e.g v3_2;
        i v11 = this;
        Context v0 = arg17;
        if(v11.q) {
            return 0;
        }

        String v1 = new c.a.a.a.a.b.g().a(v0);
        if(v1 == null) {
            return 0;
        }

        String v2 = c.a.a.a.a.b.i.m(arg17);
        if(i.a(v2, c.a.a.a.a.b.i.a(v0, "com.crashlytics.RequireBuildId", true))) {
            h v14 = null;
            try {
                l v3 = c.h();
                v3.c("CrashlyticsCore", "Initializing Crashlytics " + this.a());
                c.a.a.a.a.f.b v7 = new c.a.a.a.a.f.b(((c.a.a.a.i)v11));
                v11.d = new j("crash_marker", ((c.a.a.a.a.f.a)v7));
                v11.c = new j("initialization_marker", ((c.a.a.a.a.f.a)v7));
                ab v6 = ab.a(new c.a.a.a.a.f.d(this.q(), "com.crashlytics.android.core.CrashlyticsCore"), v11);
                if(v11.r != null) {
                    com.crashlytics.android.c.l v3_1 = new com.crashlytics.android.c.l(v11.r);
                }
                else {
                    v3_2 = ((c.a.a.a.a.e.g)v14);
                }

                v11.s = new c.a.a.a.a.e.b(c.h());
                v11.s.a(v3_2);
                p v5_1 = this.p();
                com.crashlytics.android.c.a v8 = com.crashlytics.android.c.a.a(v0, v5_1, v1, v2);
                v v9 = new v(v0, v8.d);
                l v1_1 = c.h();
                v1_1.a("CrashlyticsCore", "Installer package name is: " + v8.c);
                v11.l = new h(this, v11.t, v11.s, v5_1, v6, ((c.a.a.a.a.f.a)v7), v8, ((ak)v9), new o().b(v0));
                boolean v1_2 = this.l();
                this.w();
                v11.l.a(Thread.getDefaultUncaughtExceptionHandler());
                if((v1_2) && (c.a.a.a.a.b.i.n(arg17))) {
                    c.h().a("CrashlyticsCore", "Crashlytics did not finish previous background initialization. Initializing synchronously.");
                    this.v();
                    return 0;
                }
            }
            catch(Exception v0_1) {
                c.h().e("CrashlyticsCore", "Crashlytics was not started due to an exception during initialization", ((Throwable)v0_1));
                v11.l = v14;
                return 0;
            }

            c.h().a("CrashlyticsCore", "Exception handling initialization successful");
            return 1;
        }

        throw new m("This app relies on Crashlytics. Please sign up for access at https://fabric.io/sign_up,\ninstall an Android build tool and ask a team member to invite you to this app\'s organization.");
    }

    public String b() {
        return "com.crashlytics.sdk.android.crashlytics-core";
    }

    protected boolean b_() {
        return this.a(super.q());
    }

    protected Void c() {
        t v1_1;
        this.j();
        com.crashlytics.android.c.a.a.d v0 = this.m();
        if(v0 != null) {
            this.l.a(v0);
        }

        this.l.d();
        Void v0_1 = null;
        try {
            v1_1 = q.a().b();
            if(v1_1 != null) {
                goto label_17;
            }

            c.h().d("CrashlyticsCore", "Received null settings, skipping report submission!");
        }
        catch(Throwable v0_2) {
            goto label_47;
        }
        catch(Exception v1) {
            goto label_41;
        }

        this.k();
        return v0_1;
        try {
        label_17:
            if(v1_1.d.c) {
                goto label_26;
            }

            c.h().a("CrashlyticsCore", "Collection of crash reports disabled in Crashlytics settings.");
        }
        catch(Throwable v0_2) {
            goto label_47;
        }
        catch(Exception v1) {
            goto label_41;
        }

        this.k();
        return v0_1;
        try {
        label_26:
            if(!this.l.a(v1_1.b)) {
                c.h().a("CrashlyticsCore", "Could not finalize previous sessions.");
            }

            this.l.a(this.p, v1_1);
        }
        catch(Throwable v0_2) {
        }
        catch(Exception v1) {
            try {
            label_41:
                c.h().e("CrashlyticsCore", "Crashlytics encountered a problem during asynchronous initialization.", ((Throwable)v1));
            }
            catch(Throwable v0_2) {
            label_47:
                this.k();
                throw v0_2;
            }
        }

        this.k();
        return v0_1;
    }

    protected Object e() {
        return this.c();
    }

    Map f() {
        return Collections.unmodifiableMap(this.b);
    }

    String g() {
        String v0 = this.p().a() ? this.m : null;
        return v0;
    }

    String h() {
        String v0 = this.p().a() ? this.n : null;
        return v0;
    }

    String i() {
        String v0 = this.p().a() ? this.o : null;
        return v0;
    }

    void j() {
        this.t.a(new Callable() {
            public Void a() {
                i.a(this.a).a();
                c.h().a("CrashlyticsCore", "Initialization marker file created.");
                return null;
            }

            public Object call() {
                return this.a();
            }
        });
    }

    void k() {
        this.t.b(new Callable() {
            public Boolean a() {
                try {
                    boolean v0_1 = i.a(this.a).c();
                    l v1 = c.h();
                    v1.a("CrashlyticsCore", "Initialization marker file removed: " + v0_1);
                    return Boolean.valueOf(v0_1);
                }
                catch(Exception v0) {
                    c.h().e("CrashlyticsCore", "Problem encountered deleting Crashlytics initialization marker.", ((Throwable)v0));
                    return Boolean.valueOf(false);
                }
            }

            public Object call() {
                return this.a();
            }
        });
    }

    boolean l() {
        return this.t.a(new Callable() {
            public Boolean a() {
                return Boolean.valueOf(i.a(this.a).b());
            }

            public Object call() {
                return this.a();
            }
        }).booleanValue();
    }

    com.crashlytics.android.c.a.a.d m() {
        com.crashlytics.android.c.a.a.d v0 = this.u != null ? this.u.a() : null;
        return v0;
    }

    void n() {
        this.d.a();
    }

    private void v() {
        String v3;
        String v2;
        l v1_2;
        com.crashlytics.android.c.i$1 v0 = new c.a.a.a.a.c.g() {
            public Void a() {
                return this.a.c();
            }

            public c.a.a.a.a.c.e b() {
                return c.a.a.a.a.c.e.d;
            }

            public Object call() {
                return this.a();
            }
        };
        Iterator v1 = this.u().iterator();
        while(v1.hasNext()) {
            ((c.a.a.a.a.c.g)v0).a(v1.next());
        }

        Future v0_1 = this.r().f().submit(((Callable)v0));
        c.h().a("CrashlyticsCore", "Crashlytics detected incomplete initialization on previous app launch. Will initialize synchronously.");
        long v1_1 = 4;
        try {
            v0_1.get(v1_1, TimeUnit.SECONDS);
            return;
        }
        catch(TimeoutException v0_2) {
            v1_2 = c.h();
            v2 = "CrashlyticsCore";
            v3 = "Crashlytics timed out during initialization.";
        }
        catch(ExecutionException v0_3) {
            v1_2 = c.h();
            v2 = "CrashlyticsCore";
            v3 = "Problem encountered during Crashlytics initialization.";
        }
        catch(InterruptedException v0_4) {
            v1_2 = c.h();
            v2 = "CrashlyticsCore";
            v3 = "Crashlytics was interrupted during initialization.";
        }

        v1_2.e(v2, v3, ((Throwable)v0_3));
    }

    private void w() {
        if(!Boolean.TRUE.equals(this.t.a(new com.crashlytics.android.c.i$a(this.d)))) {
            return;
        }

        try {
            this.k.a();
        }
        catch(Exception v0) {
            c.h().e("CrashlyticsCore", "Exception thrown by CrashlyticsListener while notifying of previous crash.", ((Throwable)v0));
        }
    }
}

