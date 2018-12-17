package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public abstract class gp {
    public gp() {
        super();
    }

    public final Object fromJson(Reader arg2) {
        return this.read(new hx(arg2));
    }

    public final Object fromJson(String arg2) {
        return this.fromJson(new StringReader(arg2));
    }

    public final Object fromJsonTree(gf arg2) {
        try {
            return this.read(new hl(arg2));
        }
        catch(IOException v2) {
            throw new gg(((Throwable)v2));
        }
    }

    public final gp nullSafe() {
        return new gp() {
            public Object read(hx arg3) {
                if(arg3.f() == hy.i) {
                    arg3.j();
                    return null;
                }

                return this.a.read(arg3);
            }

            public void write(hz arg2, Object arg3) {
                if(arg3 == null) {
                    arg2.f();
                }
                else {
                    this.a.write(arg2, arg3);
                }
            }
        };
    }

    public abstract Object read(hx arg1);

    public final String toJson(Object arg2) {
        StringWriter v0 = new StringWriter();
        try {
            this.toJson(((Writer)v0), arg2);
        }
        catch(IOException v2) {
            throw new AssertionError(v2);
        }

        return v0.toString();
    }

    public final void toJson(Writer arg2, Object arg3) {
        this.write(new hz(arg2), arg3);
    }

    public final gf toJsonTree(Object arg2) {
        try {
            hm v0 = new hm();
            this.write(((hz)v0), arg2);
            return v0.a();
        }
        catch(IOException v2) {
            throw new gg(((Throwable)v2));
        }
    }

    public abstract void write(hz arg1, Object arg2);
}

