package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public final class hm extends hz {
    final class com.google.ads.interactivemedia.v3.internal.hm$1 extends Writer {
        com.google.ads.interactivemedia.v3.internal.hm$1() {
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
    private static final gk b;
    private final List c;
    private String d;
    private gf e;

    static {
        hm.a = new com.google.ads.interactivemedia.v3.internal.hm$1();
        hm.b = new gk("closed");
    }

    public hm() {
        super(hm.a);
        this.c = new ArrayList();
        this.e = gh.a;
    }

    public gf a() {
        if(this.c.isEmpty()) {
            return this.e;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Expected one JSON element but was ");
        v1.append(this.c);
        throw new IllegalStateException(v1.toString());
    }

    private void a(gf arg3) {
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

            gf v0 = this.j();
            if(!(v0 instanceof gc)) {
                goto label_22;
            }

            ((gc)v0).a(arg3);
        }

        return;
    label_22:
        throw new IllegalStateException();
    }

    public hz a(long arg2) {
        this.a(new gk(Long.valueOf(arg2)));
        return this;
    }

    public hz a(Boolean arg2) {
        if(arg2 == null) {
            return this.f();
        }

        this.a(new gk(arg2));
        return this;
    }

    public hz a(Number arg4) {
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
        this.a(new gk(arg4));
        return this;
    }

    public hz a(String arg2) {
        if(!this.c.isEmpty() && this.d == null) {
            if((this.j() instanceof gi)) {
                this.d = arg2;
                return this;
            }
            else {
                throw new IllegalStateException();
            }
        }

        throw new IllegalStateException();
    }

    public hz a(boolean arg2) {
        this.a(new gk(Boolean.valueOf(arg2)));
        return this;
    }

    public hz b() {
        gc v0 = new gc();
        this.a(((gf)v0));
        this.c.add(v0);
        return this;
    }

    public hz b(String arg2) {
        if(arg2 == null) {
            return this.f();
        }

        this.a(new gk(arg2));
        return this;
    }

    public hz c() {
        if(!this.c.isEmpty() && this.d == null) {
            if((this.j() instanceof gc)) {
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
            this.c.add(hm.b);
            return;
        }

        throw new IOException("Incomplete document");
    }

    public hz d() {
        gi v0 = new gi();
        this.a(((gf)v0));
        this.c.add(v0);
        return this;
    }

    public hz e() {
        if(!this.c.isEmpty() && this.d == null) {
            if((this.j() instanceof gi)) {
                this.c.remove(this.c.size() - 1);
                return this;
            }
            else {
                throw new IllegalStateException();
            }
        }

        throw new IllegalStateException();
    }

    public hz f() {
        this.a(gh.a);
        return this;
    }

    public void flush() {
    }

    private gf j() {
        return this.c.get(this.c.size() - 1);
    }
}

