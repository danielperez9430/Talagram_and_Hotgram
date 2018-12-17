package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager$WakeLock;
import android.text.TextUtils;
import androidx.work.impl.a;
import androidx.work.impl.utils.i;
import androidx.work.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class e implements a {
    class androidx.work.impl.background.systemalarm.e$a implements Runnable {
        private final e a;
        private final Intent b;
        private final int c;

        androidx.work.impl.background.systemalarm.e$a(e arg1, Intent arg2, int arg3) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
        }

        public void run() {
            this.a.a(this.b, this.c);
        }
    }

    interface b {
        void a();
    }

    class c implements Runnable {
        private final e a;

        c(e arg1) {
            super();
            this.a = arg1;
        }

        public void run() {
            this.a.e();
        }
    }

    final Context a;
    final androidx.work.impl.background.systemalarm.b b;
    final List c;
    Intent d;
    private final g e;
    private final androidx.work.impl.b f;
    private final androidx.work.impl.g g;
    private final Handler h;
    private b i;

    e(Context arg2) {
        this(arg2, null, null);
    }

    e(Context arg2, androidx.work.impl.b arg3, androidx.work.impl.g arg4) {
        super();
        this.a = arg2.getApplicationContext();
        this.b = new androidx.work.impl.background.systemalarm.b(this.a);
        this.e = new g();
        if(arg4 != null) {
        }
        else {
            arg4 = androidx.work.impl.g.b();
        }

        this.g = arg4;
        if(arg3 != null) {
        }
        else {
            arg3 = this.g.g();
        }

        this.f = arg3;
        this.f.a(((a)this));
        this.c = new ArrayList();
        this.d = null;
        this.h = new Handler(Looper.getMainLooper());
    }

    void a(Runnable arg2) {
        this.h.post(arg2);
    }

    public void a(String arg3, boolean arg4) {
        this.a(new androidx.work.impl.background.systemalarm.e$a(this, androidx.work.impl.background.systemalarm.b.a(this.a, arg3, arg4), 0));
    }

    private boolean a(String arg4) {
        this.g();
        List v0 = this.c;
        __monitor_enter(v0);
        try {
            Iterator v1 = this.c.iterator();
            do {
                if(!v1.hasNext()) {
                    goto label_14;
                }
            }
            while(!arg4.equals(v1.next().getAction()));

            __monitor_exit(v0);
            return 1;
        label_14:
            __monitor_exit(v0);
            return 0;
        label_18:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_18;
        }

        throw v4;
    }

    void a() {
        this.f.b(((a)this));
        this.i = null;
    }

    void a(b arg3) {
        if(this.i != null) {
            j.e("SystemAlarmDispatcher", "A completion listener for SystemAlarmDispatcher already exists.", new Throwable[0]);
            return;
        }

        this.i = arg3;
    }

    public boolean a(Intent arg7, int arg8) {
        j.b("SystemAlarmDispatcher", String.format("Adding command %s (%s)", arg7, Integer.valueOf(arg8)), new Throwable[0]);
        this.g();
        String v0 = arg7.getAction();
        if(TextUtils.isEmpty(((CharSequence)v0))) {
            j.d("SystemAlarmDispatcher", "Unknown command. Ignoring", new Throwable[0]);
            return 0;
        }

        if(("ACTION_CONSTRAINTS_CHANGED".equals(v0)) && (this.a("ACTION_CONSTRAINTS_CHANGED"))) {
            return 0;
        }

        arg7.putExtra("KEY_START_ID", arg8);
        List v8 = this.c;
        __monitor_enter(v8);
        try {
            int v0_1 = this.c.isEmpty() ^ 1;
            this.c.add(arg7);
            if(v0_1 == 0) {
                this.f();
            }

            __monitor_exit(v8);
            return 1;
        label_42:
            __monitor_exit(v8);
        }
        catch(Throwable v7) {
            goto label_42;
        }

        throw v7;
    }

    androidx.work.impl.b b() {
        return this.f;
    }

    g c() {
        return this.e;
    }

    androidx.work.impl.g d() {
        return this.g;
    }

    void e() {
        j.b("SystemAlarmDispatcher", "Checking if commands are complete.", new Throwable[0]);
        this.g();
        List v0 = this.c;
        __monitor_enter(v0);
        try {
            if(this.d != null) {
                j.b("SystemAlarmDispatcher", String.format("Removing command %s", this.d), new Throwable[0]);
                if(this.c.remove(0).equals(this.d)) {
                    this.d = null;
                }
                else {
                    throw new IllegalStateException("Dequeue-d command is not the first.");
                }
            }

            if(!this.b.a() && (this.c.isEmpty())) {
                j.b("SystemAlarmDispatcher", "No more commands & intents.", new Throwable[0]);
                if(this.i != null) {
                    this.i.a();
                }
            }
            else if(!this.c.isEmpty()) {
                this.f();
            }

            __monitor_exit(v0);
            return;
        label_53:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_53;
        }

        throw v1;
    }

    private void f() {
        this.g();
        PowerManager$WakeLock v0 = i.a(this.a, "ProcessCommand");
        try {
            v0.acquire();
            this.g.h().a(new Runnable() {
                public void run() {
                    c v1_2;
                    e v0_2;
                    List v0 = this.a.c;
                    __monitor_enter(v0);
                    try {
                        this.a.d = this.a.c.get(0);
                        __monitor_exit(v0);
                    }
                    catch(Throwable v1) {
                        try {
                        label_111:
                            __monitor_exit(v0);
                        }
                        catch(Throwable v1) {
                            goto label_111;
                        }

                        throw v1;
                    }

                    if(this.a.d != null) {
                        String v0_1 = this.a.d.getAction();
                        int v1_1 = this.a.d.getIntExtra("KEY_START_ID", 0);
                        int v5 = 2;
                        j.b("SystemAlarmDispatcher", String.format("Processing command %s, %s", this.a.d, Integer.valueOf(v1_1)), new Throwable[0]);
                        Context v2 = this.a.a;
                        PowerManager$WakeLock v2_1 = i.a(v2, String.format("%s (%s)", v0_1, Integer.valueOf(v1_1)));
                        try {
                            j.b("SystemAlarmDispatcher", String.format("Acquiring operation wake lock (%s) %s", v0_1, v2_1), new Throwable[0]);
                            v2_1.acquire();
                            this.a.b.a(this.a.d, v1_1, this.a);
                        }
                        catch(Throwable v1) {
                        label_94:
                            j.b("SystemAlarmDispatcher", String.format("Releasing operation wake lock (%s) %s", v0_1, v2_1), new Throwable[0]);
                            v2_1.release();
                            this.a.a(new c(this.a));
                            throw v1;
                        }
                        catch(Throwable v1) {
                            try {
                                j.e("SystemAlarmDispatcher", "Unexpected error in onHandleIntent", new Throwable[]{v1});
                            }
                            catch(Throwable v1) {
                                goto label_94;
                            }

                            j.b("SystemAlarmDispatcher", String.format("Releasing operation wake lock (%s) %s", v0_1, v2_1), new Throwable[0]);
                            v2_1.release();
                            v0_2 = this.a;
                            v1_2 = new c(this.a);
                            goto label_92;
                        }

                        j.b("SystemAlarmDispatcher", String.format("Releasing operation wake lock (%s) %s", v0_1, v2_1), new Throwable[0]);
                        v2_1.release();
                        v0_2 = this.a;
                        v1_2 = new c(this.a);
                    label_92:
                        v0_2.a(((Runnable)v1_2));
                    }
                }
            });
        }
        catch(Throwable v1) {
            v0.release();
            throw v1;
        }

        v0.release();
    }

    private void g() {
        if(this.h.getLooper().getThread() == Thread.currentThread()) {
            return;
        }

        throw new IllegalStateException("Needs to be invoked on the main thread.");
    }
}

