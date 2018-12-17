package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class hw {
    final Class a;
    final Type b;
    final int c;

    protected hw() {
        super();
        this.b = hw.a(this.getClass());
        this.a = gx.e(this.b);
        this.c = this.b.hashCode();
    }

    hw(Type arg1) {
        super();
        this.b = gx.d(gw.a(arg1));
        this.a = gx.e(this.b);
        this.c = this.b.hashCode();
    }

    public static hw a(Type arg1) {
        return new hw(arg1);
    }

    public final Class a() {
        return this.a;
    }

    static Type a(Class arg1) {
        Type v1 = arg1.getGenericSuperclass();
        if(!(v1 instanceof Class)) {
            return gx.d(((ParameterizedType)v1).getActualTypeArguments()[0]);
        }

        throw new RuntimeException("Missing type parameter.");
    }

    public static hw b(Class arg1) {
        return new hw(((Type)arg1));
    }

    public final Type b() {
        return this.b;
    }

    public final boolean equals(Object arg2) {
        boolean v2 = !(arg2 instanceof hw) || !gx.a(this.b, ((hw)arg2).b) ? false : true;
        return v2;
    }

    public final int hashCode() {
        return this.c;
    }

    public final String toString() {
        return gx.f(this.b);
    }
}

