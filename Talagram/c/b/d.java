package c.b;

import c.b.b.b;
import java.util.concurrent.TimeUnit;

public abstract class d {
    final class a implements b, Runnable {
        final Runnable a;
        final c.b.d$b b;
        Thread c;

        a(Runnable arg1, c.b.d$b arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        public void a() {
            if(this.c != Thread.currentThread() || !(this.b instanceof c.b.e.f.a)) {
                this.b.a();
            }
            else {
                this.b.b();
            }
        }

        public void run() {
            this.c = Thread.currentThread();
            Thread v0 = null;
            try {
                this.a.run();
            }
            catch(Throwable v1) {
                this.a();
                this.c = v0;
                throw v1;
            }

            this.a();
            this.c = v0;
        }
    }

    public abstract class c.b.d$b implements b {
        public c.b.d$b() {
            super();
        }

        public abstract b a(Runnable arg1, long arg2, TimeUnit arg3);
    }

    static final long a;

    static {
        d.a = TimeUnit.MINUTES.toNanos(Long.getLong("rx2.scheduler.drift-tolerance", 15).longValue());
    }

    public d() {
        super();
    }

    public b a(Runnable arg4) {
        return this.a(arg4, 0, TimeUnit.NANOSECONDS);
    }

    public b a(Runnable arg3, long arg4, TimeUnit arg6) {
        c.b.d$b v0 = this.a();
        a v1 = new a(c.b.f.a.a(arg3), v0);
        v0.a(((Runnable)v1), arg4, arg6);
        return ((b)v1);
    }

    public abstract c.b.d$b a();
}

