package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

public final class hi implements gq {
    final class a extends gp {
        private final gp a;
        private final hd b;

        public a(fz arg2, Type arg3, gp arg4, hd arg5) {
            super();
            this.a = new ht(arg2, arg4, arg3);
            this.b = arg5;
        }

        public Collection a(hx arg3) {
            if(arg3.f() == hy.i) {
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

        public void a(hz arg3, Collection arg4) {
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

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((Collection)arg2));
        }
    }

    private final gy a;

    public hi(gy arg1) {
        super();
        this.a = arg1;
    }

    public gp a(fz arg4, hw arg5) {
        Type v0 = arg5.b();
        Class v1 = arg5.a();
        if(!Collection.class.isAssignableFrom(v1)) {
            return null;
        }

        v0 = gx.a(v0, v1);
        return new a(arg4, v0, arg4.a(hw.a(v0)), this.a.a(arg5));
    }
}

