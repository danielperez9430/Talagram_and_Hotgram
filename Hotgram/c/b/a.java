package c.b;

public abstract class a implements b {
    public a() {
        super();
    }

    public final a a(d arg2) {
        c.b.e.b.b.a(arg2, "scheduler is null");
        return c.b.f.a.a(new c.b.e.e.a.b(((b)this), arg2));
    }

    public final c.b.b.b a(c.b.d.d arg4) {
        return this.a(arg4, c.b.e.b.a.f, c.b.e.b.a.c, c.b.e.b.a.a());
    }

    public final c.b.b.b a(c.b.d.d arg2, c.b.d.d arg3, c.b.d.a arg4, c.b.d.d arg5) {
        c.b.e.b.b.a(arg2, "onNext is null");
        c.b.e.b.b.a(arg3, "onError is null");
        c.b.e.b.b.a(arg4, "onComplete is null");
        c.b.e.b.b.a(arg5, "onSubscribe is null");
        c.b.e.d.a v0 = new c.b.e.d.a(arg2, arg3, arg4, arg5);
        this.a(((c)v0));
        return ((c.b.b.b)v0);
    }

    public final void a(c arg3) {
        c.b.e.b.b.a(arg3, "observer is null");
        try {
            arg3 = c.b.f.a.a(this, arg3);
            c.b.e.b.b.a(arg3, "Plugin returned null Observer");
            this.b(arg3);
            return;
        }
        catch(Throwable v3) {
            c.b.c.b.b(v3);
            c.b.f.a.a(v3);
            NullPointerException v0 = new NullPointerException("Actually not, but can\'t throw other exceptions due to RS");
            v0.initCause(v3);
            throw v0;
        }
        catch(NullPointerException v3_1) {
            throw v3_1;
        }
    }

    protected abstract void b(c arg1);
}

