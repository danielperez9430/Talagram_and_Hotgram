package com.google.a.b.a;

import com.google.a.d.a;
import com.google.a.d.c;
import com.google.a.f;
import com.google.a.v;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

final class m extends v {
    private final f a;
    private final v b;
    private final Type c;

    m(f arg1, v arg2, Type arg3) {
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

    public Object read(a arg2) {
        return this.b.read(arg2);
    }

    public void write(c arg4, Object arg5) {
        v v0 = this.b;
        Type v1 = this.a(this.c, arg5);
        if(v1 != this.c) {
            v0 = this.a.a(com.google.a.c.a.a(v1));
            if(!(v0 instanceof com.google.a.b.a.i$a)) {
            }
            else if(!(this.b instanceof com.google.a.b.a.i$a)) {
                v0 = this.b;
            }
        }

        v0.write(arg4, arg5);
    }
}

