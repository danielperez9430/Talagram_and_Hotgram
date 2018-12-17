package c.b.e.f;

import c.b.d$b;
import c.b.e.a.c;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class a extends b implements c.b.b.b {
    volatile boolean a;
    private final ScheduledExecutorService b;

    public c.b.b.b a(Runnable arg7, long arg8, TimeUnit arg10) {
        if(this.a) {
            return c.a;
        }

        return this.a(arg7, arg8, arg10, null);
    }

    public c.b.e.f.b a(Runnable arg4, long arg5, TimeUnit arg7, c.b.e.a.a arg8) {
        c.b.e.f.b v0 = new c.b.e.f.b(c.b.f.a.a(arg4), arg8);
        if(arg8 != null && !arg8.a(((c.b.b.b)v0))) {
            return v0;
        }

        if(arg5 <= 0) {
            try {
                Future v4_1 = this.b.submit(((Callable)v0));
                goto label_16;
            label_14:
                ScheduledFuture v4_2 = this.b.schedule(((Callable)v0), arg5, arg7);
            label_16:
                v0.a(((Future)v4_2));
                return v0;
            label_13:
                goto label_18;
            }
            catch(RejectedExecutionException v4) {
                goto label_13;
            }
        }
        else {
            goto label_14;
        }

        goto label_16;
    label_18:
        if(arg8 != null) {
            arg8.b(((c.b.b.b)v0));
        }

        c.b.f.a.a(((Throwable)v4));
        return v0;
    }

    public void a() {
        if(!this.a) {
            this.a = true;
            this.b.shutdownNow();
        }
    }

    public void b() {
        if(!this.a) {
            this.a = true;
            this.b.shutdown();
        }
    }
}

