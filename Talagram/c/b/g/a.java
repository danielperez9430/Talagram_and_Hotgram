package c.b.g;

import c.b.c;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class a extends b {
    final class c.b.g.a$a extends AtomicBoolean implements c.b.b.b {
        final c a;
        final a b;

        c.b.g.a$a(c arg1, a arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        public void a(Object arg2) {
            if(!this.get()) {
                this.a.a(arg2);
            }
        }

        public void a(Throwable arg2) {
            if(this.get()) {
                c.b.f.a.a(arg2);
            }
            else {
                this.a.a(arg2);
            }
        }

        public void a() {
            if(this.compareAndSet(false, true)) {
                this.b.b(this);
            }
        }

        public void b() {
            if(!this.get()) {
                this.a.i_();
            }
        }

        public boolean c() {
            return this.get();
        }
    }

    static final c.b.g.a$a[] a;
    static final c.b.g.a$a[] b;
    final AtomicReference c;
    Throwable d;

    static {
        a.a = new c.b.g.a$a[0];
        a.b = new c.b.g.a$a[0];
    }

    a() {
        super();
        this.c = new AtomicReference(a.b);
    }

    public void a(c.b.b.b arg3) {
        if(this.c.get() == a.a) {
            arg3.a();
        }
    }

    public void a(Object arg5) {
        c.b.e.b.b.a(arg5, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        Object v0 = this.c.get();
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0[v2].a(arg5);
        }
    }

    public void a(Throwable arg5) {
        c.b.e.b.b.a(arg5, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if(this.c.get() == a.a) {
            c.b.f.a.a(arg5);
            return;
        }

        this.d = arg5;
        Object v0 = this.c.getAndSet(a.a);
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0[v2].a(arg5);
        }
    }

    boolean a(c.b.g.a$a arg5) {
        do {
            Object v0 = this.c.get();
            if(v0 == a.a) {
                return 0;
            }

            int v1 = v0.length;
            c.b.g.a$a[] v3 = new c.b.g.a$a[v1 + 1];
            System.arraycopy(v0, 0, v3, 0, v1);
            v3[v1] = arg5;
        }
        while(!this.c.compareAndSet(v0, v3));

        return 1;
    }

    public static a b() {
        return new a();
    }

    public void b(c arg3) {
        c.b.g.a$a v0 = new c.b.g.a$a(arg3, this);
        arg3.a(((c.b.b.b)v0));
        if(!this.a(v0)) {
            Throwable v0_1 = this.d;
            if(v0_1 != null) {
                arg3.a(v0_1);
            }
            else {
                arg3.i_();
            }
        }
        else if(v0.c()) {
            this.b(v0);
        }
    }

    void b(c.b.g.a$a arg7) {
        c.b.g.a$a[] v1_1;
        do {
            Object v0 = this.c.get();
            if(v0 == a.a) {
                return;
            }

            if(v0 == a.b) {
            }
            else {
                int v1 = v0.length;
                int v2 = -1;
                int v4 = 0;
                while(v4 < v1) {
                    if(v0[v4] == arg7) {
                        v2 = v4;
                    }
                    else {
                        ++v4;
                        continue;
                    }

                    break;
                }

                if(v2 < 0) {
                    return;
                }

                if(v1 == 1) {
                    v1_1 = a.b;
                }
                else {
                    c.b.g.a$a[] v5 = new c.b.g.a$a[v1 - 1];
                    System.arraycopy(v0, 0, v5, 0, v2);
                    System.arraycopy(v0, v2 + 1, v5, v2, v1 - v2 - 1);
                    v1_1 = v5;
                }

                if(!this.c.compareAndSet(v0, v1_1)) {
                    continue;
                }
            }

            return;
        }
        while(true);
    }

    public void i_() {
        if(this.c.get() == a.a) {
            return;
        }

        Object v0 = this.c.getAndSet(a.a);
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0[v2].b();
        }
    }
}

