package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class hh extends gp {
    final class com.google.ads.interactivemedia.v3.internal.hh$1 implements gq {
        com.google.ads.interactivemedia.v3.internal.hh$1() {
            super();
        }

        public gp a(fz arg3, hw arg4) {
            Type v4 = arg4.b();
            if(!(v4 instanceof GenericArrayType) && (!(v4 instanceof Class) || !v4.isArray())) {
                return null;
            }

            v4 = gx.g(v4);
            return new hh(arg3, arg3.a(hw.a(v4)), gx.e(v4));
        }
    }

    public static final gq a;
    private final Class b;
    private final gp c;

    static {
        hh.a = new com.google.ads.interactivemedia.v3.internal.hh$1();
    }

    public hh(fz arg2, gp arg3, Class arg4) {
        super();
        this.c = new ht(arg2, arg3, ((Type)arg4));
        this.b = arg4;
    }

    public Object read(hx arg4) {
        if(arg4.f() == hy.i) {
            arg4.j();
            return null;
        }

        ArrayList v0 = new ArrayList();
        arg4.a();
        while(arg4.e()) {
            ((List)v0).add(this.c.read(arg4));
        }

        arg4.b();
        Object v4 = Array.newInstance(this.b, ((List)v0).size());
        int v1;
        for(v1 = 0; v1 < ((List)v0).size(); ++v1) {
            Array.set(v4, v1, ((List)v0).get(v1));
        }

        return v4;
    }

    public void write(hz arg5, Object arg6) {
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

