package androidx.work.impl;

import android.content.Context;
import androidx.work.j;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class b implements a {
    class androidx.work.impl.b$a implements Runnable {
        private a a;
        private String b;
        private com.google.common.a.a.a c;

        androidx.work.impl.b$a(a arg1, String arg2, com.google.common.a.a.a arg3) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
        }

        public void run() {
            boolean v0;
            try {
                v0 = this.c.get().booleanValue();
            }
            catch(ExecutionException ) {
                v0 = true;
            }

            this.a.a(this.b, v0);
        }
    }

    private Context a;
    private androidx.work.b b;
    private androidx.work.impl.utils.a.a c;
    private WorkDatabase d;
    private Map e;
    private List f;
    private Set g;
    private final List h;
    private final Object i;

    public b(Context arg1, androidx.work.b arg2, androidx.work.impl.utils.a.a arg3, WorkDatabase arg4, List arg5) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
        this.e = new HashMap();
        this.f = arg5;
        this.g = new HashSet();
        this.h = new ArrayList();
        this.i = new Object();
    }

    public boolean a(String arg11, androidx.work.u$a arg12) {
        h v12;
        Object v0 = this.i;
        __monitor_enter(v0);
        try {
            if(this.e.containsKey(arg11)) {
                j.b("Processor", String.format("Work %s is already enqueued for processing", arg11), new Throwable[0]);
                __monitor_exit(v0);
                return 0;
            }

            v12 = new androidx.work.impl.h$a(this.a, this.b, this.c, this.d, arg11).a(this.f).a(arg12).a();
            com.google.common.a.a.a v1 = v12.a();
            v1.a(new androidx.work.impl.b$a(((a)this), arg11, v1), this.c.a());
            this.e.put(arg11, v12);
            __monitor_exit(v0);
        }
        catch(Throwable v11) {
            try {
            label_54:
                __monitor_exit(v0);
            }
            catch(Throwable v11) {
                goto label_54;
            }

            throw v11;
        }

        this.c.c().execute(((Runnable)v12));
        j.b("Processor", String.format("%s: processing %s", this.getClass().getSimpleName(), arg11), new Throwable[0]);
        return 1;
    }

    public void a(a arg3) {
        Object v0 = this.i;
        __monitor_enter(v0);
        try {
            this.h.add(arg3);
            __monitor_exit(v0);
            return;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_7;
        }

        throw v3;
    }

    public void a(String arg8, boolean arg9) {
        Object v0 = this.i;
        __monitor_enter(v0);
        try {
            this.e.remove(arg8);
            j.b("Processor", String.format("%s %s executed; reschedule = %s", this.getClass().getSimpleName(), arg8, Boolean.valueOf(arg9)), new Throwable[0]);
            Iterator v1 = this.h.iterator();
            while(v1.hasNext()) {
                v1.next().a(arg8, arg9);
            }

            __monitor_exit(v0);
            return;
        label_30:
            __monitor_exit(v0);
        }
        catch(Throwable v8) {
            goto label_30;
        }

        throw v8;
    }

    public boolean a(String arg2) {
        return this.a(arg2, null);
    }

    public boolean b(String arg7) {
        Object v0 = this.i;
        __monitor_enter(v0);
        try {
            j.b("Processor", String.format("Processor stopping %s", arg7), new Throwable[0]);
            Object v1 = this.e.remove(arg7);
            if(v1 != null) {
                ((h)v1).a(false);
                j.b("Processor", String.format("WorkerWrapper stopped for %s", arg7), new Throwable[0]);
                __monitor_exit(v0);
                return 1;
            }

            j.b("Processor", String.format("WorkerWrapper could not be found for %s", arg7), new Throwable[0]);
            __monitor_exit(v0);
            return 0;
        label_34:
            __monitor_exit(v0);
        }
        catch(Throwable v7) {
            goto label_34;
        }

        throw v7;
    }

    public void b(a arg3) {
        Object v0 = this.i;
        __monitor_enter(v0);
        try {
            this.h.remove(arg3);
            __monitor_exit(v0);
            return;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_7;
        }

        throw v3;
    }

    public boolean c(String arg7) {
        Object v0 = this.i;
        __monitor_enter(v0);
        try {
            j.b("Processor", String.format("Processor cancelling %s", arg7), new Throwable[0]);
            this.g.add(arg7);
            Object v1 = this.e.remove(arg7);
            if(v1 != null) {
                ((h)v1).a(true);
                j.b("Processor", String.format("WorkerWrapper cancelled for %s", arg7), new Throwable[0]);
                __monitor_exit(v0);
                return 1;
            }

            j.b("Processor", String.format("WorkerWrapper could not be found for %s", arg7), new Throwable[0]);
            __monitor_exit(v0);
            return 0;
        label_36:
            __monitor_exit(v0);
        }
        catch(Throwable v7) {
            goto label_36;
        }

        throw v7;
    }

    public boolean d(String arg3) {
        Object v0 = this.i;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.g.contains(arg3);
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_7;
        }

        throw v3;
    }

    public boolean e(String arg3) {
        Object v0 = this.i;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.e.containsKey(arg3);
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_7;
        }

        throw v3;
    }
}

