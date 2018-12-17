package c.a.a.a.a.g;

import android.content.Context;
import c.a.a.a.a.b.g;
import c.a.a.a.a.b.l;
import c.a.a.a.a.b.p;
import c.a.a.a.a.e.e;
import c.a.a.a.c;
import c.a.a.a.i;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class q {
    class c.a.a.a.a.g.q$1 {
    }

    class a {
        private static final q a;

        static {
            a.a = new q(null);
        }

        static q a() {
            return a.a;
        }
    }

    private final AtomicReference a;
    private final CountDownLatch b;
    private s c;
    private boolean d;

    private q() {
        super();
        this.a = new AtomicReference();
        this.b = new CountDownLatch(1);
        this.d = false;
    }

    q(c.a.a.a.a.g.q$1 arg1) {
        this();
    }

    public static q a() {
        return a.a();
    }

    private void a(t arg2) {
        this.a.set(arg2);
        this.b.countDown();
    }

    public q a(i arg24, p arg25, e arg26, String arg27, String arg28, String arg29) {
        q v1 = this;
        i v0 = arg24;
        __monitor_enter(this);
        try {
            if(!v1.d) {
                goto label_7;
            }
        }
        catch(Throwable v0_1) {
            goto label_62;
        }

        __monitor_exit(this);
        return v1;
        try {
        label_7:
            if(v1.c == null) {
                Context v2 = arg24.q();
                v1.c = new j(arg24, new w(new g().a(v2), arg25.g(), arg25.f(), arg25.e(), arg25.k(), arg25.b(), arg25.l(), c.a.a.a.a.b.i.a(new String[]{c.a.a.a.a.b.i.m(v2)}), arg28, arg27, l.a(arg25.i()).a(), c.a.a.a.a.b.i.k(v2)), new c.a.a.a.a.b.t(), new k(), new c.a.a.a.a.g.i(v0), new c.a.a.a.a.g.l(v0, arg29, String.format(Locale.US, "https://settings.crashlytics.com/spi/v2/platforms/android/apps/%s/settings", arg25.c()), arg26));
            }

            v1.d = true;
        }
        catch(Throwable v0_1) {
        label_62:
            __monitor_exit(this);
            throw v0_1;
        }

        __monitor_exit(this);
        return v1;
    }

    public t b() {
        try {
            this.b.await();
            return this.a.get();
        }
        catch(InterruptedException ) {
            c.h().e("Fabric", "Interrupted while waiting for settings data.");
            return null;
        }
    }

    public boolean c() {
        __monitor_enter(this);
        try {
            t v0_1 = this.c.a();
            this.a(v0_1);
            if(v0_1 == null) {
                goto label_7;
            }
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        boolean v0_2 = true;
        goto label_8;
    label_7:
        v0_2 = false;
    label_8:
        __monitor_exit(this);
        return v0_2;
    }

    public boolean d() {
        t v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.c.a(r.b);
            this.a(v0_1);
            if(v0_1 == null) {
                c.h().e("Fabric", "Failed to force reload of settings from Crashlytics.", null);
            }
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        boolean v0_2 = v0_1 != null ? true : false;
        __monitor_exit(this);
        return v0_2;
    }
}

