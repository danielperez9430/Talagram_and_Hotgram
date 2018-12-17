package androidx.work;

import android.os.Build$VERSION;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class b {
    public final class a {
        Executor a;
        t b;
        int c;
        int d;
        int e;
        int f;

        public a() {
            super();
            this.c = 4;
            this.d = 0;
            this.e = 2147483647;
            this.f = 20;
        }

        public b a() {
            return new b(this);
        }
    }

    private final Executor a;
    private final t b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;

    b(a arg2) {
        f v0_1;
        super();
        Executor v0 = arg2.a == null ? this.g() : arg2.a;
        this.a = v0;
        if(arg2.b == null) {
            v0_1 = new f();
        }
        else {
            t v0_2 = arg2.b;
        }

        this.b = ((t)v0_1);
        this.c = arg2.c;
        this.d = arg2.d;
        this.e = arg2.e;
        this.f = arg2.f;
    }

    public Executor a() {
        return this.a;
    }

    public t b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }

    public int f() {
        if(Build$VERSION.SDK_INT == 23) {
            return this.f / 2;
        }

        return this.f;
    }

    private Executor g() {
        return Executors.newFixedThreadPool(Math.max(2, Math.min(Runtime.getRuntime().availableProcessors() - 1, 4)));
    }
}

