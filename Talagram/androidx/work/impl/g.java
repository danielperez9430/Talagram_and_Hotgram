package androidx.work.impl;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver$PendingResult;
import android.content.Context;
import android.os.Build$VERSION;
import androidx.work.b;
import androidx.work.h;
import androidx.work.impl.utils.ForceStopRunnable;
import androidx.work.impl.utils.a.a;
import androidx.work.impl.utils.e;
import androidx.work.impl.utils.f;
import androidx.work.j;
import androidx.work.m;
import androidx.work.q;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class g extends q {
    private Context a;
    private b b;
    private WorkDatabase c;
    private a d;
    private List e;
    private androidx.work.impl.b f;
    private e g;
    private boolean h;
    private BroadcastReceiver$PendingResult i;
    private static g j;
    private static g k;
    private static final Object l;

    static {
        g.l = new Object();
    }

    public g(Context arg3, b arg4, a arg5) {
        this(arg3, arg4, arg5, arg3.getResources().getBoolean(androidx.work.n$a.workmanager_test_configuration));
    }

    public g(Context arg7, b arg8, a arg9, boolean arg10) {
        super();
        arg7 = arg7.getApplicationContext();
        this.a = arg7;
        this.b = arg8;
        this.c = WorkDatabase.a(arg7, arg10);
        this.d = arg9;
        this.f = new androidx.work.impl.b(arg7, this.b, this.d, this.c, this.f());
        this.g = new e(this.a);
        this.h = false;
        j.a(this.b.c());
        this.d.a(new ForceStopRunnable(arg7, this));
    }

    public void a(BroadcastReceiver$PendingResult arg2) {
        Object v0 = g.l;
        __monitor_enter(v0);
        try {
            this.i = arg2;
            if(this.h) {
                this.i.finish();
                this.i = null;
            }

            __monitor_exit(v0);
            return;
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_12;
        }

        throw v2;
    }

    public void a(String arg3, androidx.work.u$a arg4) {
        this.d.a(new f(this, arg3, arg4));
    }

    public void a(String arg2) {
        this.d.a(androidx.work.impl.utils.a.a(arg2, this));
    }

    public void a(String arg1, androidx.work.g arg2, m arg3) {
        this.b(arg1, arg2, arg3).i();
    }

    public void a(List arg2) {
        if(!arg2.isEmpty()) {
            new androidx.work.impl.e(this, arg2).i();
            return;
        }

        throw new IllegalArgumentException("enqueue needs at least one WorkRequest.");
    }

    public static g b() {
        Object v0 = g.l;
        __monitor_enter(v0);
        try {
            if(g.j != null) {
                __monitor_exit(v0);
                return g.j;
            }

            __monitor_exit(v0);
            return g.k;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_11;
        }

        throw v1;
    }

    public static void b(Context arg3, b arg4) {
        Object v0 = g.l;
        __monitor_enter(v0);
        try {
            if(g.j == null) {
                arg3 = arg3.getApplicationContext();
                if(g.k == null) {
                    g.k = new g(arg3, arg4, new androidx.work.impl.utils.a.b());
                }

                g.j = g.k;
            }

            __monitor_exit(v0);
            return;
        label_17:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_17;
        }

        throw v3;
    }

    public void b(String arg2) {
        this.a(arg2, null);
    }

    private androidx.work.impl.e b(String arg2, androidx.work.g arg3, m arg4) {
        h v3 = arg3 == androidx.work.g.b ? h.b : h.a;
        return new androidx.work.impl.e(this, arg2, v3, Collections.singletonList(arg4));
    }

    public Context c() {
        return this.a;
    }

    public void c(String arg3) {
        this.d.a(new androidx.work.impl.utils.g(this, arg3));
    }

    public WorkDatabase d() {
        return this.c;
    }

    public b e() {
        return this.b;
    }

    public List f() {
        if(this.e == null) {
            this.e = Arrays.asList(new c[]{d.a(this.a, this), new androidx.work.impl.background.a.a(this.a, this)});
        }

        return this.e;
    }

    public androidx.work.impl.b g() {
        return this.f;
    }

    public a h() {
        return this.d;
    }

    public e i() {
        return this.g;
    }

    @TargetApi(value=23) public void j() {
        if(Build$VERSION.SDK_INT >= 23) {
            androidx.work.impl.background.systemjob.b.a(this.c());
        }

        this.d().m().b();
        d.a(this.e(), this.d(), this.f());
    }

    public void k() {
        Object v0 = g.l;
        __monitor_enter(v0);
        try {
            this.h = true;
            if(this.i != null) {
                this.i.finish();
                this.i = null;
            }

            __monitor_exit(v0);
            return;
        label_13:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_13;
        }

        throw v1;
    }
}

