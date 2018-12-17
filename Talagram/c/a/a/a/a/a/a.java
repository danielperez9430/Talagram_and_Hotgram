package c.a.a.a.a.a;

import android.content.Context;

public abstract class a implements c {
    private final c a;

    public a(c arg1) {
        super();
        this.a = arg1;
    }

    protected abstract void a(Context arg1, Object arg2);

    protected abstract Object a(Context arg1);

    public final Object a(Context arg2, d arg3) {
        Object v0;
        __monitor_enter(this);
        try {
            v0 = this.a(arg2);
            if(v0 == null) {
                Object v3 = this.a != null ? this.a.a(arg2, arg3) : arg3.b(arg2);
                v0 = v3;
                this.b(arg2, v0);
            }
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
        return v0;
    }

    private void b(Context arg1, Object arg2) {
        if(arg2 != null) {
            this.a(arg1, arg2);
            return;
        }

        throw new NullPointerException();
    }
}

