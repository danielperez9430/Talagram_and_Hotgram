package c.b.e.d;

import c.b.b.b;
import c.b.c;
import c.b.d.d;
import java.util.concurrent.atomic.AtomicReference;

public final class a extends AtomicReference implements b, c {
    final d a;
    final d b;
    final c.b.d.a c;
    final d d;

    public a(d arg1, d arg2, c.b.d.a arg3, d arg4) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
    }

    public void a() {
        c.b.e.a.b.a(((AtomicReference)this));
    }

    public void a(b arg2) {
        if(c.b.e.a.b.a(((AtomicReference)this), arg2)) {
            try {
                this.d.a(this);
            }
            catch(Throwable v0) {
                c.b.c.b.b(v0);
                arg2.a();
                this.a(v0);
            }
        }
    }

    public void a(Throwable arg5) {
        if(!this.b()) {
            this.lazySet(c.b.e.a.b.a);
            try {
                this.b.a(arg5);
            }
            catch(Throwable v0) {
                c.b.c.b.b(v0);
                c.b.f.a.a(new c.b.c.a(new Throwable[]{arg5, v0}));
            }
        }
    }

    public void a(Object arg2) {
        if(!this.b()) {
            try {
                this.a.a(arg2);
            }
            catch(Throwable v2) {
                c.b.c.b.b(v2);
                this.get().a();
                this.a(v2);
            }
        }
    }

    public boolean b() {
        boolean v0 = this.get() == c.b.e.a.b.a ? true : false;
        return v0;
    }

    public void i_() {
        if(!this.b()) {
            this.lazySet(c.b.e.a.b.a);
            try {
                this.c.a();
            }
            catch(Throwable v0) {
                c.b.c.b.b(v0);
                c.b.f.a.a(v0);
            }
        }
    }
}

