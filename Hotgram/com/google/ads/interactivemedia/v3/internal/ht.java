package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

final class ht extends gp {
    private final fz a;
    private final gp b;
    private final Type c;

    ht(fz arg1, gp arg2, Type arg3) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
    }

    private Type a(Type arg2, Object arg3) {
        Class v2;
        if(arg3 != null && (arg2 == Object.class || ((arg2 instanceof TypeVariable)) || ((arg2 instanceof Class)))) {
            v2 = arg3.getClass();
        }

        return ((Type)v2);
    }

    public Object read(hx arg2) {
        return this.b.read(arg2);
    }

    public void write(hz arg4, Object arg5) {
        gp v0 = this.b;
        Type v1 = this.a(this.c, arg5);
        if(v1 != this.c) {
            v0 = this.a.a(hw.a(v1));
            if(!(v0 instanceof a)) {
            }
            else if(!(this.b instanceof a)) {
                v0 = this.b;
            }
        }

        v0.write(arg4, arg5);
    }
}

