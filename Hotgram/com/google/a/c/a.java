package com.google.a.c;

import com.google.a.b.b;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class a {
    final Class a;
    final Type b;
    final int c;

    protected a() {
        super();
        this.b = a.a(this.getClass());
        this.a = b.e(this.b);
        this.c = this.b.hashCode();
    }

    a(Type arg1) {
        super();
        this.b = b.d(com.google.a.b.a.a(arg1));
        this.a = b.e(this.b);
        this.c = this.b.hashCode();
    }

    public final Class a() {
        return this.a;
    }

    public static a a(Type arg1) {
        return new a(arg1);
    }

    static Type a(Class arg1) {
        Type v1 = arg1.getGenericSuperclass();
        if(!(v1 instanceof Class)) {
            return b.d(((ParameterizedType)v1).getActualTypeArguments()[0]);
        }

        throw new RuntimeException("Missing type parameter.");
    }

    public final Type b() {
        return this.b;
    }

    public static a b(Class arg1) {
        return new a(((Type)arg1));
    }

    public final boolean equals(Object arg2) {
        boolean v2 = !(arg2 instanceof a) || !b.a(this.b, ((a)arg2).b) ? false : true;
        return v2;
    }

    public final int hashCode() {
        return this.c;
    }

    public final String toString() {
        return b.f(this.b);
    }
}

