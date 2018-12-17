package androidx.work.impl.background.systemalarm;

import androidx.work.j;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class g {
    interface a {
        void a(String arg1);
    }

    class b implements Runnable {
        private final g a;
        private final String b;

        b(g arg1, String arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        public void run() {
            Object v0 = this.a.c;
            __monitor_enter(v0);
            try {
                if(this.a.a.containsKey(this.b)) {
                    this.a.a.remove(this.b);
                    Object v1_1 = this.a.b.remove(this.b);
                    if(v1_1 != null) {
                        ((a)v1_1).a(this.b);
                    }
                }
                else {
                    j.b("WrkTimerRunnable", String.format("Timer with %s is already marked as complete.", this.b), new Throwable[0]);
                }

                __monitor_exit(v0);
                return;
            label_33:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_33;
            }

            throw v1;
        }
    }

    final Map a;
    final Map b;
    final Object c;
    private final ScheduledExecutorService d;

    g() {
        super();
        this.a = new HashMap();
        this.b = new HashMap();
        this.c = new Object();
        this.d = Executors.newSingleThreadScheduledExecutor();
    }

    void a(String arg6) {
        Object v0 = this.c;
        __monitor_enter(v0);
        try {
            if(this.a.containsKey(arg6)) {
                j.b("WorkTimer", String.format("Stopping timer for %s", arg6), new Throwable[0]);
                this.a.remove(arg6);
                this.b.remove(arg6);
            }

            __monitor_exit(v0);
            return;
        label_21:
            __monitor_exit(v0);
        }
        catch(Throwable v6) {
            goto label_21;
        }

        throw v6;
    }

    void a(String arg6, long arg7, a arg9) {
        Object v0 = this.c;
        __monitor_enter(v0);
        try {
            j.b("WorkTimer", String.format("Starting timer for %s", arg6), new Throwable[0]);
            this.a(arg6);
            b v1 = new b(this, arg6);
            this.a.put(arg6, v1);
            this.b.put(arg6, arg9);
            this.d.schedule(((Runnable)v1), arg7, TimeUnit.MILLISECONDS);
            __monitor_exit(v0);
            return;
        label_24:
            __monitor_exit(v0);
        }
        catch(Throwable v6) {
            goto label_24;
        }

        throw v6;
    }
}

