package androidx.work.impl.utils;

import androidx.work.impl.WorkDatabase;
import androidx.work.impl.b.k;
import androidx.work.j;
import androidx.work.o;

public class g implements Runnable {
    private androidx.work.impl.g a;
    private String b;

    public g(androidx.work.impl.g arg1, String arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public void run() {
        WorkDatabase v0 = this.a.d();
        k v1 = v0.m();
        v0.f();
        try {
            if(v1.f(this.b) == o.b) {
                v1.a(o.a, new String[]{this.b});
            }

            j.b("StopWorkRunnable", String.format("StopWorkRunnable for %s; Processor.stopWork = %s", this.b, Boolean.valueOf(this.a.g().b(this.b))), new Throwable[0]);
            v0.h();
        }
        catch(Throwable v1_1) {
            v0.g();
            throw v1_1;
        }

        v0.g();
    }
}

