package androidx.work.impl.a;

import android.content.Context;
import androidx.work.impl.a.a.b;
import androidx.work.impl.a.a.c$a;
import androidx.work.impl.a.a.e;
import androidx.work.impl.a.a.f;
import androidx.work.impl.a.a.g;
import androidx.work.impl.a.a.h;
import androidx.work.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class d implements a {
    private final c a;
    private final androidx.work.impl.a.a.c[] b;
    private final Object c;

    public d(Context arg3, c arg4) {
        super();
        arg3 = arg3.getApplicationContext();
        this.a = arg4;
        this.b = new androidx.work.impl.a.a.c[]{new androidx.work.impl.a.a.a(arg3), new b(arg3), new h(arg3), new androidx.work.impl.a.a.d(arg3), new g(arg3), new f(arg3), new e(arg3)};
        this.c = new Object();
    }

    public void a(List arg8) {
        Object v0 = this.c;
        __monitor_enter(v0);
        try {
            androidx.work.impl.a.a.c[] v1 = this.b;
            int v2 = v1.length;
            int v3 = 0;
            int v4;
            for(v4 = 0; v4 < v2; ++v4) {
                v1[v4].a(null);
            }

            v1 = this.b;
            v2 = v1.length;
            for(v4 = 0; v4 < v2; ++v4) {
                v1[v4].a(arg8);
            }

            androidx.work.impl.a.a.c[] v8_1 = this.b;
            int v1_1 = v8_1.length;
            while(v3 < v1_1) {
                v8_1[v3].a(((a)this));
                ++v3;
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

    public boolean a(String arg9) {
        Object v0 = this.c;
        __monitor_enter(v0);
        try {
            androidx.work.impl.a.a.c[] v1 = this.b;
            int v2 = v1.length;
            int v4;
            for(v4 = 0; v4 < v2; ++v4) {
                androidx.work.impl.a.a.c v6 = v1[v4];
                if(v6.a(arg9)) {
                    j.b("WorkConstraintsTracker", String.format("Work %s constrained by %s", arg9, v6.getClass().getSimpleName()), new Throwable[0]);
                    __monitor_exit(v0);
                    return 0;
                }
            }

            __monitor_exit(v0);
            return 1;
        label_29:
            __monitor_exit(v0);
        }
        catch(Throwable v9) {
            goto label_29;
        }

        throw v9;
    }

    public void a() {
        Object v0 = this.c;
        __monitor_enter(v0);
        try {
            androidx.work.impl.a.a.c[] v1_1 = this.b;
            int v2 = v1_1.length;
            int v3;
            for(v3 = 0; v3 < v2; ++v3) {
                v1_1[v3].a();
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

    public void b(List arg8) {
        Object v0 = this.c;
        __monitor_enter(v0);
        try {
            ArrayList v1 = new ArrayList();
            Iterator v8_1 = arg8.iterator();
            while(v8_1.hasNext()) {
                Object v2 = v8_1.next();
                if(!this.a(((String)v2))) {
                    continue;
                }

                j.b("WorkConstraintsTracker", String.format("Constraints met for %s", v2), new Throwable[0]);
                ((List)v1).add(v2);
            }

            if(this.a != null) {
                this.a.a(((List)v1));
            }

            __monitor_exit(v0);
            return;
        label_28:
            __monitor_exit(v0);
        }
        catch(Throwable v8) {
            goto label_28;
        }

        throw v8;
    }

    public void c(List arg3) {
        Object v0 = this.c;
        __monitor_enter(v0);
        try {
            if(this.a != null) {
                this.a.b(arg3);
            }

            __monitor_exit(v0);
            return;
        label_9:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_9;
        }

        throw v3;
    }
}

