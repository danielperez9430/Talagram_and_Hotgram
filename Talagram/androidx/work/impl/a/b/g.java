package androidx.work.impl.a.b;

import android.content.Context;

public class g {
    private static g a;
    private a b;
    private b c;
    private e d;
    private f e;

    private g(Context arg2) {
        super();
        arg2 = arg2.getApplicationContext();
        this.b = new a(arg2);
        this.c = new b(arg2);
        this.d = new e(arg2);
        this.e = new f(arg2);
    }

    public static g a(Context arg2) {
        g v2_1;
        Class v0 = g.class;
        __monitor_enter(v0);
        try {
            if(g.a == null) {
                g.a = new g(arg2);
            }

            v2_1 = g.a;
        }
        catch(Throwable v2) {
            __monitor_exit(v0);
            throw v2;
        }

        __monitor_exit(v0);
        return v2_1;
    }

    public a a() {
        return this.b;
    }

    public b b() {
        return this.c;
    }

    public e c() {
        return this.d;
    }

    public f d() {
        return this.e;
    }
}

