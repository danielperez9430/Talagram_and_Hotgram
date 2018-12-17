package com.google.a.b.a;

import com.google.a.b.h;
import com.google.a.d.c;
import com.google.a.f;
import com.google.a.v;
import com.google.a.w;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

public final class b implements w {
    final class a extends v {
        private final v a;
        private final h b;

        public a(f arg2, Type arg3, v arg4, h arg5) {
            super();
            this.a = new m(arg2, arg4, arg3);
            this.b = arg5;
        }

        public Collection a(com.google.a.d.a arg3) {
            if(arg3.f() == com.google.a.d.b.i) {
                arg3.j();
                return null;
            }

            Object v0 = this.b.a();
            arg3.a();
            while(arg3.e()) {
                ((Collection)v0).add(this.a.read(arg3));
            }

            arg3.b();
            return ((Collection)v0);
        }

        public void a(c arg3, Collection arg4) {
            if(arg4 == null) {
                arg3.f();
                return;
            }

            arg3.b();
            Iterator v4 = arg4.iterator();
            while(v4.hasNext()) {
                this.a.write(arg3, v4.next());
            }

            arg3.c();
        }

        public Object read(com.google.a.d.a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((Collection)arg2));
        }
    }

    private final com.google.a.b.c a;

    public b(com.google.a.b.c arg1) {
        super();
        this.a = arg1;
    }

    public v create(f arg4, com.google.a.c.a arg5) {
        Type v0 = arg5.b();
        Class v1 = arg5.a();
        if(!Collection.class.isAssignableFrom(v1)) {
            return null;
        }

        v0 = com.google.a.b.b.a(v0, v1);
        return new a(arg4, v0, arg4.a(com.google.a.c.a.a(v0)), this.a.a(arg5));
    }
}

