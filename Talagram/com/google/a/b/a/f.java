package com.google.a.b.a;

import com.google.a.d.c;
import com.google.a.i;
import com.google.a.l;
import com.google.a.n;
import com.google.a.o;
import com.google.a.q;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public final class f extends c {
    final class com.google.a.b.a.f$1 extends Writer {
        com.google.a.b.a.f$1() {
            super();
        }

        public void close() {
            throw new AssertionError();
        }

        public void flush() {
            throw new AssertionError();
        }

        public void write(char[] arg1, int arg2, int arg3) {
            throw new AssertionError();
        }
    }

    private static final Writer a;
    private static final q b;
    private final List c;
    private String d;
    private l e;

    static {
        f.a = new com.google.a.b.a.f$1();
        f.b = new q("closed");
    }

    public f() {
        super(f.a);
        this.c = new ArrayList();
        this.e = n.a;
    }

    public l a() {
        if(this.c.isEmpty()) {
            return this.e;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Expected one JSON element but was ");
        v1.append(this.c);
        throw new IllegalStateException(v1.toString());
    }

    private void a(l arg3) {
        if(this.d != null) {
            if(!arg3.j() || (this.i())) {
                this.j().a(this.d, arg3);
            }

            this.d = null;
        }
        else {
            if(this.c.isEmpty()) {
                this.e = arg3;
                return;
            }

            l v0 = this.j();
            if(!(v0 instanceof i)) {
                goto label_22;
            }

            ((i)v0).a(arg3);
        }

        return;
    label_22:
        throw new IllegalStateException();
    }

    public c a(long arg2) {
        this.a(new q(Long.valueOf(arg2)));
        return this;
    }

    public c a(Boolean arg2) {
        if(arg2 == null) {
            return this.f();
        }

        this.a(new q(arg2));
        return this;
    }

    public c a(Number arg4) {
        if(arg4 == null) {
            return this.f();
        }

        if(!this.g()) {
            double v0 = arg4.doubleValue();
            if(!Double.isNaN(v0) && !Double.isInfinite(v0)) {
                goto label_20;
            }

            StringBuilder v1 = new StringBuilder();
            v1.append("JSON forbids NaN and infinities: ");
            v1.append(arg4);
            throw new IllegalArgumentException(v1.toString());
        }

    label_20:
        this.a(new q(arg4));
        return this;
    }

    public c a(String arg2) {
        if(!this.c.isEmpty() && this.d == null) {
            if((this.j() instanceof o)) {
                this.d = arg2;
                return this;
            }
            else {
                throw new IllegalStateException();
            }
        }

        throw new IllegalStateException();
    }

    public c a(boolean arg2) {
        this.a(new q(Boolean.valueOf(arg2)));
        return this;
    }

    public c b() {
        i v0 = new i();
        this.a(((l)v0));
        this.c.add(v0);
        return this;
    }

    public c b(String arg2) {
        if(arg2 == null) {
            return this.f();
        }

        this.a(new q(arg2));
        return this;
    }

    public c c() {
        if(!this.c.isEmpty() && this.d == null) {
            if((this.j() instanceof i)) {
                this.c.remove(this.c.size() - 1);
                return this;
            }
            else {
                throw new IllegalStateException();
            }
        }

        throw new IllegalStateException();
    }

    public void close() {
        if(this.c.isEmpty()) {
            this.c.add(f.b);
            return;
        }

        throw new IOException("Incomplete document");
    }

    public c d() {
        o v0 = new o();
        this.a(((l)v0));
        this.c.add(v0);
        return this;
    }

    public c e() {
        if(!this.c.isEmpty() && this.d == null) {
            if((this.j() instanceof o)) {
                this.c.remove(this.c.size() - 1);
                return this;
            }
            else {
                throw new IllegalStateException();
            }
        }

        throw new IllegalStateException();
    }

    public c f() {
        this.a(n.a);
        return this;
    }

    public void flush() {
    }

    private l j() {
        return this.c.get(this.c.size() - 1);
    }
}

