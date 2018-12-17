package com.google.a.b.a;

import com.google.a.b.b;
import com.google.a.d.c;
import com.google.a.f;
import com.google.a.v;
import com.google.a.w;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class a extends v {
    final class com.google.a.b.a.a$1 implements w {
        com.google.a.b.a.a$1() {
            super();
        }

        public v create(f arg3, com.google.a.c.a arg4) {
            Type v4 = arg4.b();
            if(!(v4 instanceof GenericArrayType) && (!(v4 instanceof Class) || !v4.isArray())) {
                return null;
            }

            v4 = b.g(v4);
            return new a(arg3, arg3.a(com.google.a.c.a.a(v4)), b.e(v4));
        }
    }

    public static final w a;
    private final Class b;
    private final v c;

    static {
        a.a = new com.google.a.b.a.a$1();
    }

    public a(f arg2, v arg3, Class arg4) {
        super();
        this.c = new m(arg2, arg3, ((Type)arg4));
        this.b = arg4;
    }

    public Object read(com.google.a.d.a arg5) {
        if(arg5.f() == com.google.a.d.b.i) {
            arg5.j();
            return null;
        }

        ArrayList v0 = new ArrayList();
        arg5.a();
        while(arg5.e()) {
            ((List)v0).add(this.c.read(arg5));
        }

        arg5.b();
        int v5 = ((List)v0).size();
        Object v1 = Array.newInstance(this.b, v5);
        int v2;
        for(v2 = 0; v2 < v5; ++v2) {
            Array.set(v1, v2, ((List)v0).get(v2));
        }

        return v1;
    }

    public void write(c arg5, Object arg6) {
        if(arg6 == null) {
            arg5.f();
            return;
        }

        arg5.b();
        int v0 = 0;
        int v1 = Array.getLength(arg6);
        while(v0 < v1) {
            this.c.write(arg5, Array.get(arg6, v0));
            ++v0;
        }

        arg5.c();
    }
}

