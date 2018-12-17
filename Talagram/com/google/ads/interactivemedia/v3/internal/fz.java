package com.google.ads.interactivemedia.v3.internal;

import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

public final class fz {
    final class com.google.ads.interactivemedia.v3.internal.fz$1 extends hw {
        com.google.ads.interactivemedia.v3.internal.fz$1() {
            super();
        }
    }

    class a extends gp {
        private gp a;

        a() {
            super();
        }

        public void a(gp arg2) {
            if(this.a == null) {
                this.a = arg2;
                return;
            }

            throw new AssertionError();
        }

        public Object read(hx arg2) {
            if(this.a != null) {
                return this.a.read(arg2);
            }

            throw new IllegalStateException();
        }

        public void write(hz arg2, Object arg3) {
            if(this.a != null) {
                this.a.write(arg2, arg3);
                return;
            }

            throw new IllegalStateException();
        }
    }

    private static final hw a;
    private final ThreadLocal b;
    private final Map c;
    private final List d;
    private final gy e;
    private final gz f;
    private final fy g;
    private final boolean h;
    private final boolean i;
    private final boolean j;
    private final boolean k;
    private final boolean l;
    private final hk m;

    static {
        fz.a = new com.google.ads.interactivemedia.v3.internal.fz$1();
    }

    public fz() {
        this(gz.a, fx.a, Collections.emptyMap(), false, false, false, true, false, false, false, go.a, Collections.emptyList());
    }

    fz(gz arg2, fy arg3, Map arg4, boolean arg5, boolean arg6, boolean arg7, boolean arg8, boolean arg9, boolean arg10, boolean arg11, go arg12, List arg13) {
        super();
        this.b = new ThreadLocal();
        this.c = new ConcurrentHashMap();
        this.e = new gy(arg4);
        this.f = arg2;
        this.g = arg3;
        this.h = arg5;
        this.j = arg7;
        this.i = arg8;
        this.k = arg9;
        this.l = arg10;
        ArrayList v4 = new ArrayList();
        ((List)v4).add(hu.Y);
        ((List)v4).add(ho.a);
        ((List)v4).add(arg2);
        ((List)v4).addAll(((Collection)arg13));
        ((List)v4).add(hu.D);
        ((List)v4).add(hu.m);
        ((List)v4).add(hu.g);
        ((List)v4).add(hu.i);
        ((List)v4).add(hu.k);
        gp v5 = fz.a(arg12);
        ((List)v4).add(hu.a(Long.TYPE, Long.class, v5));
        ((List)v4).add(hu.a(Double.TYPE, Double.class, this.a(arg11)));
        ((List)v4).add(hu.a(Float.TYPE, Float.class, this.b(arg11)));
        ((List)v4).add(hu.x);
        ((List)v4).add(hu.o);
        ((List)v4).add(hu.q);
        ((List)v4).add(hu.a(AtomicLong.class, fz.a(v5)));
        ((List)v4).add(hu.a(AtomicLongArray.class, fz.b(v5)));
        ((List)v4).add(hu.s);
        ((List)v4).add(hu.z);
        ((List)v4).add(hu.F);
        ((List)v4).add(hu.H);
        ((List)v4).add(hu.a(BigDecimal.class, hu.B));
        ((List)v4).add(hu.a(BigInteger.class, hu.C));
        ((List)v4).add(hu.J);
        ((List)v4).add(hu.L);
        ((List)v4).add(hu.P);
        ((List)v4).add(hu.R);
        ((List)v4).add(hu.W);
        ((List)v4).add(hu.N);
        ((List)v4).add(hu.d);
        ((List)v4).add(hj.a);
        ((List)v4).add(hu.U);
        ((List)v4).add(hr.a);
        ((List)v4).add(hq.a);
        ((List)v4).add(hu.S);
        ((List)v4).add(hh.a);
        ((List)v4).add(hu.b);
        ((List)v4).add(new hi(this.e));
        ((List)v4).add(new hn(this.e, arg6));
        this.m = new hk(this.e);
        ((List)v4).add(this.m);
        ((List)v4).add(hu.Z);
        ((List)v4).add(new hp(this.e, arg3, arg2, this.m));
        this.d = Collections.unmodifiableList(((List)v4));
    }

    private static gp a(go arg1) {
        if(arg1 == go.a) {
            return hu.t;
        }

        return new gp() {
            public Number a(hx arg3) {
                if(arg3.f() == hy.i) {
                    arg3.j();
                    return null;
                }

                return Long.valueOf(arg3.l());
            }

            public void a(hz arg1, Number arg2) {
                if(arg2 == null) {
                    arg1.f();
                    return;
                }

                arg1.b(arg2.toString());
            }

            public Object read(hx arg1) {
                return this.a(arg1);
            }

            public void write(hz arg1, Object arg2) {
                this.a(arg1, ((Number)arg2));
            }
        };
    }

    private gp a(boolean arg1) {
        if(arg1) {
            return hu.v;
        }

        return new gp() {
            public Double a(hx arg3) {
                if(arg3.f() == hy.i) {
                    arg3.j();
                    return null;
                }

                return Double.valueOf(arg3.k());
            }

            public void a(hz arg3, Number arg4) {
                if(arg4 == null) {
                    arg3.f();
                    return;
                }

                fz.a(arg4.doubleValue());
                arg3.a(arg4);
            }

            public Object read(hx arg1) {
                return this.a(arg1);
            }

            public void write(hz arg1, Object arg2) {
                this.a(arg1, ((Number)arg2));
            }
        };
    }

    private static gp a(gp arg1) {
        return new gp(arg1) {
            public AtomicLong a(hx arg4) {
                return new AtomicLong(this.a.read(arg4).longValue());
            }

            public void a(hz arg4, AtomicLong arg5) {
                this.a.write(arg4, Long.valueOf(arg5.get()));
            }

            public Object read(hx arg1) {
                return this.a(arg1);
            }

            public void write(hz arg1, Object arg2) {
                this.a(arg1, ((AtomicLong)arg2));
            }
        }.nullSafe();
    }

    static void a(double arg2) {
        if(!Double.isNaN(arg2) && !Double.isInfinite(arg2)) {
            return;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append(arg2);
        v1.append(" is not a valid double value as per JSON specification. To override this");
        v1.append(" behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        throw new IllegalArgumentException(v1.toString());
    }

    private static void a(Object arg0, hx arg1) {
        if(arg0 != null) {
            try {
                if(arg1.f() == hy.j) {
                }
                else {
                    throw new gg("JSON document was not fully consumed.");
                }
            }
            catch(IOException v0) {
                throw new gg(((Throwable)v0));
            }
            catch(ia v0_1) {
                throw new gn(((Throwable)v0_1));
            }
        }
    }

    public gp a(gq arg4, hw arg5) {
        gp v2_1;
        if(!this.d.contains(arg4)) {
            hk v4 = this.m;
        }

        int v0 = 0;
        Iterator v1 = this.d.iterator();
        do {
        label_7:
            if(!v1.hasNext()) {
                goto label_17;
            }

            Object v2 = v1.next();
            if(v0 == 0) {
                if((((gq)v2)) != (((gq)v4))) {
                    goto label_7;
                }

                v0 = 1;
                goto label_7;
            }

            v2_1 = ((gq)v2).a(this, arg5);
        }
        while(v2_1 == null);

        return v2_1;
    label_17:
        StringBuilder v0_1 = new StringBuilder();
        v0_1.append("GSON cannot serialize ");
        v0_1.append(arg5);
        throw new IllegalArgumentException(v0_1.toString());
    }

    public gp a(hw arg6) {
        gp v4;
        Map v0 = this.c;
        hw v1 = arg6 == null ? fz.a : arg6;
        Object v0_1 = v0.get(v1);
        if(v0_1 != null) {
            return ((gp)v0_1);
        }

        v0_1 = this.b.get();
        int v1_1 = 0;
        if(v0_1 == null) {
            HashMap v0_2 = new HashMap();
            this.b.set(v0_2);
            v1_1 = 1;
        }

        Object v2 = ((Map)v0_1).get(arg6);
        if(v2 != null) {
            return ((gp)v2);
        }

        try {
            a v2_2 = new a();
            ((Map)v0_1).put(arg6, v2_2);
            Iterator v3 = this.d.iterator();
            do {
                if(!v3.hasNext()) {
                    goto label_38;
                }

                v4 = v3.next().a(this, arg6);
            }
            while(v4 == null);

            v2_2.a(v4);
            this.c.put(arg6, v4);
        }
        catch(Throwable v2_1) {
            goto label_48;
        }

        ((Map)v0_1).remove(arg6);
        if(v1_1 != 0) {
            this.b.remove();
        }

        return v4;
        try {
        label_38:
            StringBuilder v3_1 = new StringBuilder();
            v3_1.append("GSON cannot handle ");
            v3_1.append(arg6);
            throw new IllegalArgumentException(v3_1.toString());
        }
        catch(Throwable v2_1) {
        label_48:
            ((Map)v0_1).remove(arg6);
            if(v1_1 != 0) {
                this.b.remove();
            }

            throw v2_1;
        }
    }

    public gp a(Class arg1) {
        return this.a(hw.b(arg1));
    }

    public hx a(Reader arg2) {
        hx v0 = new hx(arg2);
        v0.a(this.l);
        return v0;
    }

    public hz a(Writer arg2) {
        if(this.j) {
            arg2.write(")]}\'\n");
        }

        hz v0 = new hz(arg2);
        if(this.k) {
            v0.c("  ");
        }

        v0.d(this.h);
        return v0;
    }

    public Object a(hx arg3, Type arg4) {
        Object v4_4;
        boolean v0 = arg3.q();
        arg3.a(true);
        try {
            arg3.f();
            v4_4 = this.a(hw.a(arg4)).read(arg3);
        }
        catch(Throwable v4) {
        }
        catch(IOException v4_1) {
            try {
                throw new gn(((Throwable)v4_1));
            }
            catch(Throwable v4) {
            label_28:
                arg3.a(v0);
                throw v4;
            }
        }
        catch(IllegalStateException v4_2) {
        }
        catch(EOFException v4_3) {
            if(0 != 0) {
                arg3.a(v0);
                return null;
            }

            try {
                throw new gn(((Throwable)v4_3));
                throw new gn(((Throwable)v4_2));
            }
            catch(Throwable v4) {
                goto label_28;
            }
        }

        arg3.a(v0);
        return v4_4;
    }

    public Object a(Reader arg1, Type arg2) {
        hx v1 = this.a(arg1);
        Object v2 = this.a(v1, arg2);
        fz.a(v2, v1);
        return v2;
    }

    public Object a(String arg1, Class arg2) {
        return he.a(arg2).cast(this.a(arg1, ((Type)arg2)));
    }

    public Object a(String arg2, Type arg3) {
        if(arg2 == null) {
            return null;
        }

        return this.a(new StringReader(arg2), arg3);
    }

    public String a(gf arg2) {
        StringWriter v0 = new StringWriter();
        this.a(arg2, ((Appendable)v0));
        return v0.toString();
    }

    public void a(gf arg1, Appendable arg2) {
        try {
            this.a(arg1, this.a(hf.a(arg2)));
            return;
        }
        catch(IOException v1) {
            throw new gg(((Throwable)v1));
        }
    }

    public String a(Object arg2) {
        if(arg2 == null) {
            return this.a(gh.a);
        }

        return this.a(arg2, arg2.getClass());
    }

    public String a(Object arg2, Type arg3) {
        StringWriter v0 = new StringWriter();
        this.a(arg2, arg3, ((Appendable)v0));
        return v0.toString();
    }

    public void a(Object arg1, Type arg2, Appendable arg3) {
        try {
            this.a(arg1, arg2, this.a(hf.a(arg3)));
            return;
        }
        catch(IOException v1) {
            throw new gg(((Throwable)v1));
        }
    }

    public void a(gf arg5, hz arg6) {
        boolean v0 = arg6.g();
        arg6.b(true);
        boolean v1 = arg6.h();
        arg6.c(this.i);
        boolean v2 = arg6.i();
        arg6.d(this.h);
        try {
            hf.a(arg5, arg6);
        }
        catch(Throwable v5) {
        }
        catch(IOException v5_1) {
            try {
                throw new gg(((Throwable)v5_1));
            }
            catch(Throwable v5) {
                arg6.b(v0);
                arg6.c(v1);
                arg6.d(v2);
                throw v5;
            }
        }

        arg6.b(v0);
        arg6.c(v1);
        arg6.d(v2);
    }

    public void a(Object arg5, Type arg6, hz arg7) {
        gp v6 = this.a(hw.a(arg6));
        boolean v0 = arg7.g();
        arg7.b(true);
        boolean v1 = arg7.h();
        arg7.c(this.i);
        boolean v2 = arg7.i();
        arg7.d(this.h);
        try {
            v6.write(arg7, arg5);
        }
        catch(Throwable v5) {
        }
        catch(IOException v5_1) {
            try {
                throw new gg(((Throwable)v5_1));
            }
            catch(Throwable v5) {
                arg7.b(v0);
                arg7.c(v1);
                arg7.d(v2);
                throw v5;
            }
        }

        arg7.b(v0);
        arg7.c(v1);
        arg7.d(v2);
    }

    private gp b(boolean arg1) {
        if(arg1) {
            return hu.u;
        }

        return new gp() {
            public Float a(hx arg3) {
                if(arg3.f() == hy.i) {
                    arg3.j();
                    return null;
                }

                return Float.valueOf(((float)arg3.k()));
            }

            public void a(hz arg3, Number arg4) {
                if(arg4 == null) {
                    arg3.f();
                    return;
                }

                fz.a(((double)arg4.floatValue()));
                arg3.a(arg4);
            }

            public Object read(hx arg1) {
                return this.a(arg1);
            }

            public void write(hz arg1, Object arg2) {
                this.a(arg1, ((Number)arg2));
            }
        };
    }

    private static gp b(gp arg1) {
        return new gp(arg1) {
            public AtomicLongArray a(hx arg6) {
                ArrayList v0 = new ArrayList();
                arg6.a();
                while(arg6.e()) {
                    ((List)v0).add(Long.valueOf(this.a.read(arg6).longValue()));
                }

                arg6.b();
                int v6 = ((List)v0).size();
                AtomicLongArray v1 = new AtomicLongArray(v6);
                int v2;
                for(v2 = 0; v2 < v6; ++v2) {
                    v1.set(v2, ((List)v0).get(v2).longValue());
                }

                return v1;
            }

            public void a(hz arg6, AtomicLongArray arg7) {
                arg6.b();
                int v0 = arg7.length();
                int v1;
                for(v1 = 0; v1 < v0; ++v1) {
                    this.a.write(arg6, Long.valueOf(arg7.get(v1)));
                }

                arg6.c();
            }

            public Object read(hx arg1) {
                return this.a(arg1);
            }

            public void write(hz arg1, Object arg2) {
                this.a(arg1, ((AtomicLongArray)arg2));
            }
        }.nullSafe();
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder("{serializeNulls:");
        v0.append(this.h);
        v0.append("factories:");
        v0.append(this.d);
        v0.append(",instanceCreators:");
        v0.append(this.e);
        v0.append("}");
        return v0.toString();
    }
}

