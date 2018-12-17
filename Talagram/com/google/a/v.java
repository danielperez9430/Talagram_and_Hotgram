package com.google.a;

import com.google.a.b.a.e;
import com.google.a.b.a.f;
import com.google.a.d.a;
import com.google.a.d.b;
import com.google.a.d.c;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public abstract class v {
    public v() {
        super();
    }

    public final Object fromJson(Reader arg2) {
        return this.read(new a(arg2));
    }

    public final Object fromJson(String arg2) {
        return this.fromJson(new StringReader(arg2));
    }

    public final Object fromJsonTree(l arg2) {
        try {
            return this.read(new e(arg2));
        }
        catch(IOException v2) {
            throw new m(((Throwable)v2));
        }
    }

    public final v nullSafe() {
        return new v() {
            public Object read(a arg3) {
                if(arg3.f() == b.i) {
                    arg3.j();
                    return null;
                }

                return this.a.read(arg3);
            }

            public void write(c arg2, Object arg3) {
                if(arg3 == null) {
                    arg2.f();
                }
                else {
                    this.a.write(arg2, arg3);
                }
            }
        };
    }

    public abstract Object read(a arg1);

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
        this.write(new c(arg2), arg3);
    }

    public final l toJsonTree(Object arg2) {
        try {
            f v0 = new f();
            this.write(((c)v0), arg2);
            return v0.a();
        }
        catch(IOException v2) {
            throw new m(((Throwable)v2));
        }
    }

    public abstract void write(c arg1, Object arg2);
}

