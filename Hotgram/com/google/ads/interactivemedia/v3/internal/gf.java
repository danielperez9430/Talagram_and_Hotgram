package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public abstract class gf {
    public gf() {
        super();
    }

    public Number a() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public String b() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public double c() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public long d() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public int e() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public boolean f() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public boolean g() {
        return this instanceof gc;
    }

    public boolean h() {
        return this instanceof gi;
    }

    public boolean i() {
        return this instanceof gk;
    }

    public boolean j() {
        return this instanceof gh;
    }

    public gi k() {
        if(this.h()) {
            return this;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Not a JSON Object: ");
        v1.append(this);
        throw new IllegalStateException(v1.toString());
    }

    public gc l() {
        if(this.g()) {
            return this;
        }

        throw new IllegalStateException("This is not a JSON Array.");
    }

    public gk m() {
        if(this.i()) {
            return this;
        }

        throw new IllegalStateException("This is not a JSON Primitive.");
    }

    Boolean n() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public String toString() {
        try {
            StringWriter v0_1 = new StringWriter();
            hz v1 = new hz(((Writer)v0_1));
            v1.b(true);
            hf.a(this, v1);
            return v0_1.toString();
        }
        catch(IOException v0) {
            throw new AssertionError(v0);
        }
    }
}

