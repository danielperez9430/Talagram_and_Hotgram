package android.arch.b.b;

import android.arch.b.a.f;

public abstract class b extends i {
    public b(e arg1) {
        super(arg1);
    }

    protected abstract void a(f arg1, Object arg2);

    public final void a(Object arg2) {
        f v0 = this.c();
        try {
            this.a(v0, arg2);
            v0.b();
        }
        catch(Throwable v2) {
            this.a(v0);
            throw v2;
        }

        this.a(v0);
    }
}

