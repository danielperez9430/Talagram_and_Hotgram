package androidx.work.impl.background.a;

import android.content.Context;
import android.os.Build$VERSION;
import android.text.TextUtils;
import androidx.work.impl.a.c;
import androidx.work.impl.a.d;
import androidx.work.impl.g;
import androidx.work.j;
import androidx.work.o;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class a implements c, androidx.work.impl.a, androidx.work.impl.c {
    private g a;
    private d b;
    private List c;
    private boolean d;
    private final Object e;

    public a(Context arg2, g arg3) {
        super();
        this.c = new ArrayList();
        this.a = arg3;
        this.b = new d(arg2, ((c)this));
        this.e = new Object();
    }

    private void a() {
        if(!this.d) {
            this.a.g().a(((androidx.work.impl.a)this));
            this.d = true;
        }
    }

    public void a(String arg5) {
        this.a();
        j.b("GreedyScheduler", String.format("Cancelling work ID %s", arg5), new Throwable[0]);
        this.a.c(arg5);
    }

    public void a(String arg1, boolean arg2) {
        this.b(arg1);
    }

    public void a(List arg6) {
        Iterator v6 = arg6.iterator();
        while(v6.hasNext()) {
            Object v0 = v6.next();
            j.b("GreedyScheduler", String.format("Constraints met: Scheduling work ID %s", v0), new Throwable[0]);
            this.a.b(((String)v0));
        }
    }

    public void a(androidx.work.impl.b.j[] arg12) {
        this.a();
        ArrayList v0 = new ArrayList();
        ArrayList v1 = new ArrayList();
        int v2 = arg12.length;
        int v4;
        for(v4 = 0; v4 < v2; ++v4) {
            androidx.work.impl.b.j v5 = arg12[v4];
            if(v5.b == o.a && !v5.a() && v5.g == 0 && !v5.b()) {
                if(v5.d()) {
                    if(Build$VERSION.SDK_INT >= 24 && (v5.j.g())) {
                        goto label_35;
                    }

                    ((List)v0).add(v5);
                    ((List)v1).add(v5.a);
                }
                else {
                    this.a.b(v5.a);
                }
            }

        label_35:
        }

        Object v12 = this.e;
        __monitor_enter(v12);
        try {
            if(!((List)v0).isEmpty()) {
                j.b("GreedyScheduler", String.format("Starting tracking for [%s]", TextUtils.join(",", ((Iterable)v1))), new Throwable[0]);
                this.c.addAll(((Collection)v0));
                this.b.a(this.c);
            }

            __monitor_exit(v12);
            return;
        label_59:
            __monitor_exit(v12);
        }
        catch(Throwable v0_1) {
            goto label_59;
        }

        throw v0_1;
    }

    private void b(String arg7) {
        Object v0 = this.e;
        __monitor_enter(v0);
        try {
            int v1 = this.c.size();
            int v3 = 0;
            while(v3 < v1) {
                if(this.c.get(v3).a.equals(arg7)) {
                    j.b("GreedyScheduler", String.format("Stopping tracking for %s", arg7), new Throwable[0]);
                    this.c.remove(v3);
                    this.b.a(this.c);
                }
                else {
                    ++v3;
                    continue;
                }

                break;
            }

            __monitor_exit(v0);
            return;
        label_31:
            __monitor_exit(v0);
        }
        catch(Throwable v7) {
            goto label_31;
        }

        throw v7;
    }

    public void b(List arg6) {
        Iterator v6 = arg6.iterator();
        while(v6.hasNext()) {
            Object v0 = v6.next();
            j.b("GreedyScheduler", String.format("Constraints not met: Cancelling work ID %s", v0), new Throwable[0]);
            this.a.c(((String)v0));
        }
    }
}

