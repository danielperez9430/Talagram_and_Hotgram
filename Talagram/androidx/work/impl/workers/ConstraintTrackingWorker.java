package androidx.work.impl.workers;

import android.text.TextUtils;
import androidx.work.NonBlockingWorker;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.a.c;
import androidx.work.impl.a.d;
import androidx.work.impl.g;
import androidx.work.impl.utils.h;
import androidx.work.j;
import androidx.work.s$a;
import androidx.work.s;
import androidx.work.u;
import java.util.Collections;
import java.util.List;

public class ConstraintTrackingWorker extends s implements c {
    private NonBlockingWorker b;
    private final Object c;
    private volatile boolean d;

    public ConstraintTrackingWorker() {
        super();
        this.c = new Object();
        this.d = false;
    }

    public void a(List arg1) {
    }

    public void b(List arg6) {
        j.b("ConstraintTrkngWrkr", String.format("Constraints changed for %s", arg6), new Throwable[0]);
        Object v6 = this.c;
        __monitor_enter(v6);
        try {
            this.d = true;
            __monitor_exit(v6);
            return;
        label_15:
            __monitor_exit(v6);
        }
        catch(Throwable v0) {
            goto label_15;
        }

        throw v0;
    }

    public void b(boolean arg2) {
        super.b(arg2);
        if(this.b != null) {
            this.b.a(arg2);
        }
    }

    public a m() {
        Object v1_3;
        String v0 = this.c().a("androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME");
        if(TextUtils.isEmpty(((CharSequence)v0))) {
            j.e("ConstraintTrkngWrkr", "No worker to delegate to.", new Throwable[0]);
            return a.b;
        }

        this.b = this.l().a(this.a(), v0, new u(this.b(), this.c(), this.d(), this.j(), this.e(), new h(), this.l()));
        if(this.b == null) {
            j.b("ConstraintTrkngWrkr", "No worker to delegate to.", new Throwable[0]);
            return a.b;
        }

        androidx.work.impl.b.j v1 = this.n().m().b(this.b().toString());
        if(v1 == null) {
            return a.b;
        }

        d v3 = new d(this.a(), ((c)this));
        v3.a(Collections.singletonList(v1));
        if(v3.a(this.b().toString())) {
            j.b("ConstraintTrkngWrkr", String.format("Constraints met for delegate %s", v0), new Throwable[0]);
            try {
                com.google.common.a.a.a v1_2 = this.b.f();
                if(this.d) {
                    return a.c;
                }

                v1_3 = v1_2.get();
                this.a(((android.support.v4.f.j)v1_3).a);
                this.a(((android.support.v4.f.j)v1_3).b);
                return ((android.support.v4.f.j)v1_3).a;
            }
            catch(Throwable v1_1) {
                j.b("ConstraintTrkngWrkr", String.format("Delegated worker %s threw a runtime exception.", v0), new Throwable[]{v1_1});
                v1_3 = this.c;
                __monitor_enter(v1_3);
                try {
                    if(this.d) {
                        j.b("ConstraintTrkngWrkr", "Constraints were unmet, Retrying.", new Throwable[0]);
                        __monitor_exit(v1_3);
                        return a.c;
                    }

                    __monitor_exit(v1_3);
                    return a.b;
                label_98:
                    __monitor_exit(v1_3);
                }
                catch(Throwable v0_1) {
                    goto label_98;
                }

                throw v0_1;
            }
        }

        j.b("ConstraintTrkngWrkr", String.format("Constraints not met for delegate %s. Requesting retry.", v0), new Throwable[0]);
        return a.c;
    }

    public WorkDatabase n() {
        return g.b().d();
    }
}

