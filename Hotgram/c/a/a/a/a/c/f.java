package c.a.a.a.a.c;

import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public abstract class f extends a implements b, i, l {
    class c.a.a.a.a.c.f$a implements Executor {
        private final Executor a;
        private final f b;

        public c.a.a.a.a.c.f$a(Executor arg1, f arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        static f a(c.a.a.a.a.c.f$a arg0) {
            return arg0.b;
        }

        public void execute(Runnable arg4) {
            this.a.execute(new h(arg4, null) {
                public b a() {
                    return c.a.a.a.a.c.f$a.a(this.a);
                }
            });
        }
    }

    private final j a;

    public f() {
        super();
        this.a = new j();
    }

    public void a(l arg3) {
        if(this.g_() == d.a) {
            this.g().c(arg3);
            return;
        }

        throw new IllegalStateException("Must not add Dependency after task is running");
    }

    public void a(Throwable arg2) {
        this.g().a(arg2);
    }

    public final void a(ExecutorService arg2, Object[] arg3) {
        super.a(new c.a.a.a.a.c.f$a(((Executor)arg2), this), arg3);
    }

    public e b() {
        return this.g().b();
    }

    public void b(boolean arg2) {
        this.g().b(arg2);
    }

    public Collection c() {
        return this.g().c();
    }

    public void c(Object arg1) {
        this.a(((l)arg1));
    }

    public int compareTo(Object arg1) {
        return e.a(((i)this), arg1);
    }

    public boolean d() {
        return this.g().d();
    }

    public boolean f() {
        return this.g().f();
    }

    public b g() {
        return this.a;
    }
}

