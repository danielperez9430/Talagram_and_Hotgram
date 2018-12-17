package com.google.a;

import com.google.a.b.a.b;
import com.google.a.b.a.g;
import com.google.a.b.a.h;
import com.google.a.b.a.i;
import com.google.a.b.a.j;
import com.google.a.b.a.k;
import com.google.a.b.a.n;
import com.google.a.b.d;
import com.google.a.d.c;
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

public final class f {
    class a extends v {
        private v a;

        a() {
            super();
        }

        public void a(v arg2) {
            if(this.a == null) {
                this.a = arg2;
                return;
            }

            throw new AssertionError();
        }

        public Object read(com.google.a.d.a arg2) {
            if(this.a != null) {
                return this.a.read(arg2);
            }

            throw new IllegalStateException();
        }

        public void write(c arg2, Object arg3) {
            if(this.a != null) {
                this.a.write(arg2, arg3);
                return;
            }

            throw new IllegalStateException();
        }
    }

    private static final com.google.a.c.a a;
    private final ThreadLocal b;
    private final Map c;
    private final List d;
    private final com.google.a.b.c e;
    private final d f;
    private final e g;
    private final boolean h;
    private final boolean i;
    private final boolean j;
    private final boolean k;
    private final boolean l;
    private final com.google.a.b.a.d m;

    static {
        f.a = com.google.a.c.a.b(Object.class);
    }

    public f() {
        this(d.a, com.google.a.d.a, Collections.emptyMap(), false, false, false, true, false, false, false, u.a, Collections.emptyList());
    }

    f(d arg2, e arg3, Map arg4, boolean arg5, boolean arg6, boolean arg7, boolean arg8, boolean arg9, boolean arg10, boolean arg11, u arg12, List arg13) {
        super();
        this.b = new ThreadLocal();
        this.c = new ConcurrentHashMap();
        this.e = new com.google.a.b.c(arg4);
        this.f = arg2;
        this.g = arg3;
        this.h = arg5;
        this.j = arg7;
        this.i = arg8;
        this.k = arg9;
        this.l = arg10;
        ArrayList v4 = new ArrayList();
        ((List)v4).add(n.Y);
        ((List)v4).add(h.a);
        ((List)v4).add(arg2);
        ((List)v4).addAll(((Collection)arg13));
        ((List)v4).add(n.D);
        ((List)v4).add(n.m);
        ((List)v4).add(n.g);
        ((List)v4).add(n.i);
        ((List)v4).add(n.k);
        v v5 = f.a(arg12);
        ((List)v4).add(n.a(Long.TYPE, Long.class, v5));
        ((List)v4).add(n.a(Double.TYPE, Double.class, this.a(arg11)));
        ((List)v4).add(n.a(Float.TYPE, Float.class, this.b(arg11)));
        ((List)v4).add(n.x);
        ((List)v4).add(n.o);
        ((List)v4).add(n.q);
        ((List)v4).add(n.a(AtomicLong.class, f.a(v5)));
        ((List)v4).add(n.a(AtomicLongArray.class, f.b(v5)));
        ((List)v4).add(n.s);
        ((List)v4).add(n.z);
        ((List)v4).add(n.F);
        ((List)v4).add(n.H);
        ((List)v4).add(n.a(BigDecimal.class, n.B));
        ((List)v4).add(n.a(BigInteger.class, n.C));
        ((List)v4).add(n.J);
        ((List)v4).add(n.L);
        ((List)v4).add(n.P);
        ((List)v4).add(n.R);
        ((List)v4).add(n.W);
        ((List)v4).add(n.N);
        ((List)v4).add(n.d);
        ((List)v4).add(com.google.a.b.a.c.a);
        ((List)v4).add(n.U);
        ((List)v4).add(k.a);
        ((List)v4).add(j.a);
        ((List)v4).add(n.S);
        ((List)v4).add(com.google.a.b.a.a.a);
        ((List)v4).add(n.b);
        ((List)v4).add(new b(this.e));
        ((List)v4).add(new g(this.e, arg6));
        this.m = new com.google.a.b.a.d(this.e);
        ((List)v4).add(this.m);
        ((List)v4).add(n.Z);
        ((List)v4).add(new i(this.e, arg3, arg2, this.m));
        this.d = Collections.unmodifiableList(((List)v4));
    }

    public String a(Object arg2) {
        if(arg2 == null) {
            return this.a(com.google.a.n.a);
        }

        return this.a(arg2, arg2.getClass());
    }

    public Object a(String arg1, Class arg2) {
        return com.google.a.b.i.a(arg2).cast(this.a(arg1, ((Type)arg2)));
    }

    public v a(Class arg1) {
        return this.a(com.google.a.c.a.b(arg1));
    }

    private static v a(u arg1) {
        if(arg1 == u.a) {
            return n.t;
        }

        return new v() {
            public Number a(com.google.a.d.a arg3) {
                if(arg3.f() == com.google.a.d.b.i) {
                    arg3.j();
                    return null;
                }

                return Long.valueOf(arg3.l());
            }

            public void a(c arg1, Number arg2) {
                if(arg2 == null) {
                    arg1.f();
                    return;
                }

                arg1.b(arg2.toString());
            }

            public Object read(com.google.a.d.a arg1) {
                return this.a(arg1);
            }

            public void write(c arg1, Object arg2) {
                this.a(arg1, ((Number)arg2));
            }
        };
    }

    private v a(boolean arg1) {
        if(arg1) {
            return n.v;
        }

        return new v() {
            public Double a(com.google.a.d.a arg3) {
                if(arg3.f() == com.google.a.d.b.i) {
                    arg3.j();
                    return null;
                }

                return Double.valueOf(arg3.k());
            }

            public void a(c arg3, Number arg4) {
                if(arg4 == null) {
                    arg3.f();
                    return;
                }

                f.a(arg4.doubleValue());
                arg3.a(arg4);
            }

            public Object read(com.google.a.d.a arg1) {
                return this.a(arg1);
            }

            public void write(c arg1, Object arg2) {
                this.a(arg1, ((Number)arg2));
            }
        };
    }

    private static v a(v arg1) {
        return new v(arg1) {
            public AtomicLong a(com.google.a.d.a arg4) {
                return new AtomicLong(this.a.read(arg4).longValue());
            }

            public void a(c arg4, AtomicLong arg5) {
                this.a.write(arg4, Long.valueOf(arg5.get()));
            }

            public Object read(com.google.a.d.a arg1) {
                return this.a(arg1);
            }

            public void write(c arg1, Object arg2) {
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
        v1.append(" is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        throw new IllegalArgumentException(v1.toString());
    }

    private static void a(Object arg0, com.google.a.d.a arg1) {
        if(arg0 != null) {
            try {
                if(arg1.f() == com.google.a.d.b.j) {
                }
                else {
                    throw new m("JSON document was not fully consumed.");
                }
            }
            catch(IOException v0) {
                throw new m(((Throwable)v0));
            }
            catch(com.google.a.d.d v0_1) {
                throw new t(((Throwable)v0_1));
            }
        }
    }

    public com.google.a.d.a a(Reader arg2) {
        com.google.a.d.a v0 = new com.google.a.d.a(arg2);
        v0.a(this.l);
        return v0;
    }

    public c a(Writer arg2) {
        if(this.j) {
            arg2.write(")]}\'\n");
        }

        c v0 = new c(arg2);
        if(this.k) {
            v0.c("  ");
        }

        v0.d(this.h);
        return v0;
    }

    public v a(com.google.a.c.a arg6) {
        v v4;
        Map v0 = this.c;
        com.google.a.c.a v1 = arg6 == null ? f.a : arg6;
        Object v0_1 = v0.get(v1);
        if(v0_1 != null) {
            return ((v)v0_1);
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
            return ((v)v2);
        }

        try {
            a v2_2 = new a();
            ((Map)v0_1).put(arg6, v2_2);
            Iterator v3 = this.d.iterator();
            do {
                if(!v3.hasNext()) {
                    goto label_38;
                }

                v4 = v3.next().create(this, arg6);
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

    public v a(w arg4, com.google.a.c.a arg5) {
        v v2_1;
        if(!this.d.contains(arg4)) {
            com.google.a.b.a.d v4 = this.m;
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
                if((((w)v2)) != (((w)v4))) {
                    goto label_7;
                }

                v0 = 1;
                goto label_7;
            }

            v2_1 = ((w)v2).create(this, arg5);
        }
        while(v2_1 == null);

        return v2_1;
    label_17:
        StringBuilder v0_1 = new StringBuilder();
        v0_1.append("GSON cannot serialize ");
        v0_1.append(arg5);
        throw new IllegalArgumentException(v0_1.toString());
    }

    public Object a(com.google.a.d.a arg3, Type arg4) {
        Object v4_4;
        boolean v0 = arg3.q();
        arg3.a(true);
        try {
            arg3.f();
            v4_4 = this.a(com.google.a.c.a.a(arg4)).read(arg3);
        }
        catch(Throwable v4) {
        }
        catch(IOException v4_1) {
            try {
                throw new t(((Throwable)v4_1));
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
                throw new t(((Throwable)v4_3));
                throw new t(((Throwable)v4_2));
            }
            catch(Throwable v4) {
                goto label_28;
            }
        }

        arg3.a(v0);
        return v4_4;
    }

    public Object a(Reader arg1, Type arg2) {
        com.google.a.d.a v1 = this.a(arg1);
        Object v2 = this.a(v1, arg2);
        f.a(v2, v1);
        return v2;
    }

    public Object a(String arg2, Type arg3) {
        if(arg2 == null) {
            return null;
        }

        return this.a(new StringReader(arg2), arg3);
    }

    public String a(l arg2) {
        StringWriter v0 = new StringWriter();
        this.a(arg2, ((Appendable)v0));
        return v0.toString();
    }

    public void a(l arg1, Appendable arg2) {
        try {
            this.a(arg1, this.a(com.google.a.b.j.a(arg2)));
            return;
        }
        catch(IOException v1) {
            throw new m(((Throwable)v1));
        }
    }

    public String a(Object arg2, Type arg3) {
        StringWriter v0 = new StringWriter();
        this.a(arg2, arg3, ((Appendable)v0));
        return v0.toString();
    }

    public void a(Object arg1, Type arg2, Appendable arg3) {
        try {
            this.a(arg1, arg2, this.a(com.google.a.b.j.a(arg3)));
            return;
        }
        catch(IOException v1) {
            throw new m(((Throwable)v1));
        }
    }

    public void a(l arg5, c arg6) {
        boolean v0 = arg6.g();
        arg6.b(true);
        boolean v1 = arg6.h();
        arg6.c(this.i);
        boolean v2 = arg6.i();
        arg6.d(this.h);
        try {
            com.google.a.b.j.a(arg5, arg6);
        }
        catch(Throwable v5) {
        }
        catch(IOException v5_1) {
            try {
                throw new m(((Throwable)v5_1));
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

    public void a(Object arg5, Type arg6, c arg7) {
        v v6 = this.a(com.google.a.c.a.a(arg6));
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
                throw new m(((Throwable)v5_1));
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

    private v b(boolean arg1) {
        if(arg1) {
            return n.u;
        }

        return new v() {
            public Float a(com.google.a.d.a arg3) {
                if(arg3.f() == com.google.a.d.b.i) {
                    arg3.j();
                    return null;
                }

                return Float.valueOf(((float)arg3.k()));
            }

            public void a(c arg3, Number arg4) {
                if(arg4 == null) {
                    arg3.f();
                    return;
                }

                f.a(((double)arg4.floatValue()));
                arg3.a(arg4);
            }

            public Object read(com.google.a.d.a arg1) {
                return this.a(arg1);
            }

            public void write(c arg1, Object arg2) {
                this.a(arg1, ((Number)arg2));
            }
        };
    }

    private static v b(v arg1) {
        return new v(arg1) {
            public AtomicLongArray a(com.google.a.d.a arg6) {
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

            public void a(c arg6, AtomicLongArray arg7) {
                arg6.b();
                int v0 = arg7.length();
                int v1;
                for(v1 = 0; v1 < v0; ++v1) {
                    this.a.write(arg6, Long.valueOf(arg7.get(v1)));
                }

                arg6.c();
            }

            public Object read(com.google.a.d.a arg1) {
                return this.a(arg1);
            }

            public void write(c arg1, Object arg2) {
                this.a(arg1, ((AtomicLongArray)arg2));
            }
        }.nullSafe();
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder("{serializeNulls:");
        v0.append(this.h);
        v0.append(",factories:");
        v0.append(this.d);
        v0.append(",instanceCreators:");
        v0.append(this.e);
        v0.append("}");
        return v0.toString();
    }
}

