package c.b.e.e.a;

import c.b.c;
import c.b.d;
import java.util.concurrent.atomic.AtomicReference;

public final class b extends a {
    final class c.b.e.e.a.b$a extends AtomicReference implements c.b.b.b, c {
        final c a;
        final AtomicReference b;

        c.b.e.e.a.b$a(c arg1) {
            super();
            this.a = arg1;
            this.b = new AtomicReference();
        }

        public void a() {
            c.b.e.a.b.a(this.b);
            c.b.e.a.b.a(((AtomicReference)this));
        }

        public void a(c.b.b.b arg2) {
            c.b.e.a.b.a(this.b, arg2);
        }

        public void a(Object arg2) {
            this.a.a(arg2);
        }

        public void a(Throwable arg2) {
            this.a.a(arg2);
        }

        void b(c.b.b.b arg1) {
            c.b.e.a.b.a(((AtomicReference)this), arg1);
        }

        public void i_() {
            this.a.i_();
        }
    }

    final class c.b.e.e.a.b$b implements Runnable {
        private final c.b.e.e.a.b$a b;

        c.b.e.e.a.b$b(b arg1, c.b.e.e.a.b$a arg2) {
            this.a = arg1;
            super();
            this.b = arg2;
        }

        public void run() {
            this.a.a.a(this.b);
        }
    }

    final d b;

    public b(c.b.b arg1, d arg2) {
        super(arg1);
        this.b = arg2;
    }

    public void b(c arg3) {
        c.b.e.e.a.b$a v0 = new c.b.e.e.a.b$a(arg3);
        arg3.a(((c.b.b.b)v0));
        v0.b(this.b.a(new c.b.e.e.a.b$b(this, v0)));
    }
}

