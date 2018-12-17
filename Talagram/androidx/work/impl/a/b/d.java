package androidx.work.impl.a.b;

import android.content.Context;
import androidx.work.impl.a.a;
import androidx.work.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class d {
    protected final Context a;
    private final Object b;
    private final Set c;
    private Object d;

    d(Context arg2) {
        super();
        this.b = new Object();
        this.c = new LinkedHashSet();
        this.a = arg2.getApplicationContext();
    }

    public void a(a arg8) {
        Object v0 = this.b;
        __monitor_enter(v0);
        try {
            if(this.c.add(arg8)) {
                if(this.c.size() == 1) {
                    this.d = this.c();
                    j.b("ConstraintTracker", String.format("%s: initial state = %s", this.getClass().getSimpleName(), this.d), new Throwable[0]);
                    this.d();
                }

                arg8.a(this.d);
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

    public void a(Object arg4) {
        Object v0 = this.b;
        __monitor_enter(v0);
        try {
            if(this.d != arg4 && (this.d == null || !this.d.equals(arg4))) {
                this.d = arg4;
                Iterator v4_1 = new ArrayList(this.c).iterator();
                while(v4_1.hasNext()) {
                    v4_1.next().a(this.d);
                }

                __monitor_exit(v0);
                return;
            }

            __monitor_exit(v0);
            return;
        label_26:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_26;
        }

        throw v4;
    }

    public void b(a arg3) {
        Object v0 = this.b;
        __monitor_enter(v0);
        try {
            if((this.c.remove(arg3)) && (this.c.isEmpty())) {
                this.e();
            }

            __monitor_exit(v0);
            return;
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_12;
        }

        throw v3;
    }

    public abstract Object c();

    public abstract void d();

    public abstract void e();
}

