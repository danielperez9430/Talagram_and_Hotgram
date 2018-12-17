package androidx.work.impl.utils;

import androidx.work.impl.WorkDatabase;
import androidx.work.impl.b.k;
import androidx.work.impl.d;
import androidx.work.impl.g;
import androidx.work.o;
import java.util.Iterator;

public abstract class a implements Runnable {
    public a() {
        super();
    }

    public static Runnable a(String arg1, g arg2) {
        return new a(arg2, arg1) {
            public void run() {
                WorkDatabase v0 = this.a.d();
                v0.f();
                try {
                    Iterator v1_1 = v0.m().h(this.b).iterator();
                    while(v1_1.hasNext()) {
                        this.a(this.a, v1_1.next());
                    }

                    v0.h();
                }
                catch(Throwable v1) {
                    goto label_19;
                }

                v0.g();
                this.a(this.a);
                return;
            label_19:
                v0.g();
                throw v1;
            }
        };
    }

    public static Runnable a(String arg1, g arg2, boolean arg3) {
        return new a(arg2, arg1, arg3) {
            public void run() {
                WorkDatabase v0 = this.a.d();
                v0.f();
                try {
                    Iterator v1_1 = v0.m().i(this.b).iterator();
                    while(v1_1.hasNext()) {
                        this.a(this.a, v1_1.next());
                    }

                    v0.h();
                }
                catch(Throwable v1) {
                    goto label_21;
                }

                v0.g();
                if(this.c) {
                    this.a(this.a);
                }

                return;
            label_21:
                v0.g();
                throw v1;
            }
        };
    }

    private void a(WorkDatabase arg4, String arg5) {
        k v0 = arg4.m();
        Iterator v1 = arg4.n().b(arg5).iterator();
        while(v1.hasNext()) {
            this.a(arg4, v1.next());
        }

        o v4 = v0.f(arg5);
        if(v4 != o.c && v4 != o.d) {
            v0.a(o.f, new String[]{arg5});
        }
    }

    void a(g arg3) {
        d.a(arg3.e(), arg3.d(), arg3.f());
    }

    void a(g arg2, String arg3) {
        this.a(arg2.d(), arg3);
        arg2.g().c(arg3);
        Iterator v2 = arg2.f().iterator();
        while(v2.hasNext()) {
            v2.next().a(arg3);
        }
    }
}

