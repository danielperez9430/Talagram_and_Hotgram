package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.os.PowerManager$WakeLock;
import androidx.work.impl.a.c;
import androidx.work.impl.a;
import androidx.work.impl.utils.i;
import androidx.work.j;
import java.util.Collections;
import java.util.List;

public class d implements c, a, androidx.work.impl.background.systemalarm.g$a {
    private final Context a;
    private final int b;
    private final String c;
    private final e d;
    private final androidx.work.impl.a.d e;
    private final Object f;
    private boolean g;
    private PowerManager$WakeLock h;
    private boolean i;

    d(Context arg1, int arg2, String arg3, e arg4) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.d = arg4;
        this.c = arg3;
        this.e = new androidx.work.impl.a.d(this.a, ((c)this));
        this.i = false;
        this.g = false;
        this.f = new Object();
    }

    void a() {
        this.h = i.a(this.a, String.format("%s (%s)", this.c, Integer.valueOf(this.b)));
        j.b("DelayMetCommandHandler", String.format("Acquiring wakelock %s for WorkSpec %s", this.h, this.c), new Throwable[0]);
        this.h.acquire();
        androidx.work.impl.b.j v0 = this.d.d().d().m().b(this.c);
        this.i = v0.d();
        if(!this.i) {
            j.b("DelayMetCommandHandler", String.format("No constraints for %s", this.c), new Throwable[0]);
            this.a(Collections.singletonList(this.c));
        }
        else {
            this.e.a(Collections.singletonList(v0));
        }
    }

    public void a(List arg5) {
        j.b("DelayMetCommandHandler", String.format("onAllConstraintsMet for %s", this.c), new Throwable[0]);
        if(this.d.b().a(this.c)) {
            this.d.c().a(this.c, 600000, ((androidx.work.impl.background.systemalarm.g$a)this));
        }
        else {
            this.c();
        }
    }

    public void a(String arg5) {
        j.b("DelayMetCommandHandler", String.format("Exceeded time limits on execution for %s", arg5), new Throwable[0]);
        this.b();
    }

    public void a(String arg5, boolean arg6) {
        j.b("DelayMetCommandHandler", String.format("onExecuted %s, %s", arg5, Boolean.valueOf(arg6)), new Throwable[0]);
        this.c();
        if(this.i) {
            this.d.a(new androidx.work.impl.background.systemalarm.e$a(this.d, b.a(this.a), this.b));
        }
    }

    private void b() {
        Object v0 = this.f;
        __monitor_enter(v0);
        try {
            if(!this.g) {
                j.b("DelayMetCommandHandler", String.format("Stopping work for workspec %s", this.c), new Throwable[0]);
                this.d.a(new androidx.work.impl.background.systemalarm.e$a(this.d, b.c(this.a, this.c), this.b));
                if(this.d.b().e(this.c)) {
                    j.b("DelayMetCommandHandler", String.format("WorkSpec %s needs to be rescheduled", this.c), new Throwable[0]);
                    this.d.a(new androidx.work.impl.background.systemalarm.e$a(this.d, b.a(this.a, this.c), this.b));
                }
                else {
                    j.b("DelayMetCommandHandler", String.format("Processor does not have WorkSpec %s. No need to reschedule ", this.c), new Throwable[0]);
                }

                this.g = true;
            }
            else {
                j.b("DelayMetCommandHandler", String.format("Already stopped work for %s", this.c), new Throwable[0]);
            }

            __monitor_exit(v0);
            return;
        label_67:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_67;
        }

        throw v1;
    }

    public void b(List arg1) {
        this.b();
    }

    private void c() {
        Object v0 = this.f;
        __monitor_enter(v0);
        try {
            this.d.c().a(this.c);
            if(this.h != null && (this.h.isHeld())) {
                j.b("DelayMetCommandHandler", String.format("Releasing wakelock %s for WorkSpec %s", this.h, this.c), new Throwable[0]);
                this.h.release();
            }

            __monitor_exit(v0);
            return;
        label_29:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_29;
        }

        throw v1;
    }
}

