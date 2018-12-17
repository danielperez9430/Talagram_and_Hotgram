package com.google.a;

import com.google.a.b.j;
import com.google.a.d.c;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public abstract class l {
    public l() {
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
        return this instanceof i;
    }

    public boolean h() {
        return this instanceof o;
    }

    public boolean i() {
        return this instanceof q;
    }

    public boolean j() {
        return this instanceof n;
    }

    public o k() {
        if(this.h()) {
            return this;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Not a JSON Object: ");
        v1.append(this);
        throw new IllegalStateException(v1.toString());
    }

    public i l() {
        if(this.g()) {
            return this;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Not a JSON Array: ");
        v1.append(this);
        throw new IllegalStateException(v1.toString());
    }

    public q m() {
        if(this.i()) {
            return this;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Not a JSON Primitive: ");
        v1.append(this);
        throw new IllegalStateException(v1.toString());
    }

    Boolean n() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }

    public String toString() {
        try {
            StringWriter v0_1 = new StringWriter();
            c v1 = new c(((Writer)v0_1));
            v1.b(true);
            j.a(this, v1);
            return v0_1.toString();
        }
        catch(IOException v0) {
            throw new AssertionError(v0);
        }
    }
}

