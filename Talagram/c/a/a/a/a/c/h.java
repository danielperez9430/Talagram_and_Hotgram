package c.a.a.a.a.c;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class h extends FutureTask implements b, i, l {
    final Object b;

    public h(Runnable arg1, Object arg2) {
        super(arg1, arg2);
        this.b = this.a(arg1);
    }

    public h(Callable arg1) {
        super(arg1);
        this.b = this.a(arg1);
    }

    protected b a(Object arg2) {
        if(j.a(arg2)) {
            return arg2;
        }

        return new j();
    }

    public b a() {
        return this.b;
    }

    public void a(l arg2) {
        this.a().c(arg2);
    }

    public void a(Throwable arg2) {
        this.a().a(arg2);
    }

    public e b() {
        return this.a().b();
    }

    public void b(boolean arg2) {
        this.a().b(arg2);
    }

    public Collection c() {
        return this.a().c();
    }

    public void c(Object arg1) {
        this.a(((l)arg1));
    }

    public int compareTo(Object arg2) {
        return this.a().compareTo(arg2);
    }

    public boolean d() {
        return this.a().d();
    }

    public boolean f() {
        return this.a().f();
    }
}

