package com.google.ads.interactivemedia.v3.internal;

import java.io.Reader;
import java.util.Iterator;
import java.util.Map$Entry;

public final class hl extends hx {
    final class com.google.ads.interactivemedia.v3.internal.hl$1 extends Reader {
        com.google.ads.interactivemedia.v3.internal.hl$1() {
            super();
        }

        public void close() {
            throw new AssertionError();
        }

        public int read(char[] arg1, int arg2, int arg3) {
            throw new AssertionError();
        }
    }

    private static final Reader b;
    private static final Object c;
    private Object[] d;
    private int e;
    private String[] f;
    private int[] g;

    static {
        hl.b = new com.google.ads.interactivemedia.v3.internal.hl$1();
        hl.c = new Object();
    }

    public hl(gf arg3) {
        super(hl.b);
        this.d = new Object[32];
        this.e = 0;
        this.f = new String[32];
        this.g = new int[32];
        this.a(arg3);
    }

    private void a(Object arg7) {
        Object[] v0;
        if(this.e == this.d.length) {
            v0 = new Object[this.e * 2];
            int[] v1 = new int[this.e * 2];
            String[] v2 = new String[this.e * 2];
            System.arraycopy(this.d, 0, v0, 0, this.e);
            System.arraycopy(this.g, 0, v1, 0, this.e);
            System.arraycopy(this.f, 0, v2, 0, this.e);
            this.d = v0;
            this.g = v1;
            this.f = v2;
        }

        v0 = this.d;
        int v1_1 = this.e;
        this.e = v1_1 + 1;
        v0[v1_1] = arg7;
    }

    private void a(hy arg4) {
        if(this.f() == arg4) {
            return;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Expected ");
        v1.append(arg4);
        v1.append(" but was ");
        v1.append(this.f());
        v1.append(this.u());
        throw new IllegalStateException(v1.toString());
    }

    public void a() {
        this.a(hy.a);
        this.a(this.s().iterator());
        this.g[this.e - 1] = 0;
    }

    public void b() {
        this.a(hy.b);
        this.t();
        this.t();
        if(this.e > 0) {
            int[] v0 = this.g;
            int v1 = this.e - 1;
            ++v0[v1];
        }
    }

    public void c() {
        this.a(hy.c);
        this.a(this.s().o().iterator());
    }

    public void close() {
        this.d = new Object[]{hl.c};
        this.e = 1;
    }

    public void d() {
        this.a(hy.d);
        this.t();
        this.t();
        if(this.e > 0) {
            int[] v0 = this.g;
            int v1 = this.e - 1;
            ++v0[v1];
        }
    }

    public boolean e() {
        hy v0 = this.f();
        boolean v0_1 = v0 == hy.d || v0 == hy.b ? false : true;
        return v0_1;
    }

    public hy f() {
        if(this.e == 0) {
            return hy.j;
        }

        Object v0 = this.s();
        if((v0 instanceof Iterator)) {
            boolean v1 = this.d[this.e - 2] instanceof gi;
            if(((Iterator)v0).hasNext()) {
                if(v1) {
                    return hy.e;
                }

                this.a(((Iterator)v0).next());
                return this.f();
            }

            hy v0_1 = v1 ? hy.d : hy.b;
            return v0_1;
        }

        if((v0 instanceof gi)) {
            return hy.c;
        }

        if((v0 instanceof gc)) {
            return hy.a;
        }

        if((v0 instanceof gk)) {
            if(((gk)v0).q()) {
                return hy.f;
            }

            if(((gk)v0).o()) {
                return hy.h;
            }

            if(((gk)v0).p()) {
                return hy.g;
            }

            throw new AssertionError();
        }

        if((v0 instanceof gh)) {
            return hy.i;
        }

        if(v0 == hl.c) {
            throw new IllegalStateException("JsonReader is closed");
        }

        throw new AssertionError();
    }

    public String g() {
        this.a(hy.e);
        Object v0 = this.s().next();
        Object v1 = ((Map$Entry)v0).getKey();
        this.f[this.e - 1] = v1;
        this.a(((Map$Entry)v0).getValue());
        return ((String)v1);
    }

    public String h() {
        hy v0 = this.f();
        if(v0 != hy.f) {
            if(v0 == hy.g) {
            }
            else {
                StringBuilder v2 = new StringBuilder();
                v2.append("Expected ");
                v2.append(hy.f);
                v2.append(" but was ");
                v2.append(v0);
                v2.append(this.u());
                throw new IllegalStateException(v2.toString());
            }
        }

        String v0_1 = this.t().b();
        if(this.e > 0) {
            int[] v1 = this.g;
            int v2_1 = this.e - 1;
            ++v1[v2_1];
        }

        return v0_1;
    }

    public boolean i() {
        this.a(hy.h);
        boolean v0 = this.t().f();
        if(this.e > 0) {
            int[] v1 = this.g;
            int v2 = this.e - 1;
            ++v1[v2];
        }

        return v0;
    }

    public void j() {
        this.a(hy.i);
        this.t();
        if(this.e > 0) {
            int[] v0 = this.g;
            int v1 = this.e - 1;
            ++v0[v1];
        }
    }

    public double k() {
        hy v0 = this.f();
        if(v0 != hy.g) {
            if(v0 == hy.f) {
            }
            else {
                StringBuilder v2 = new StringBuilder();
                v2.append("Expected ");
                v2.append(hy.g);
                v2.append(" but was ");
                v2.append(v0);
                v2.append(this.u());
                throw new IllegalStateException(v2.toString());
            }
        }

        double v0_1 = this.s().c();
        if(!this.q() && ((Double.isNaN(v0_1)) || (Double.isInfinite(v0_1)))) {
            StringBuilder v3 = new StringBuilder();
            v3.append("JSON forbids NaN and infinities: ");
            v3.append(v0_1);
            throw new NumberFormatException(v3.toString());
        }

        this.t();
        if(this.e > 0) {
            int[] v2_1 = this.g;
            int v3_1 = this.e - 1;
            ++v2_1[v3_1];
        }

        return v0_1;
    }

    public long l() {
        hy v0 = this.f();
        if(v0 != hy.g) {
            if(v0 == hy.f) {
            }
            else {
                StringBuilder v2 = new StringBuilder();
                v2.append("Expected ");
                v2.append(hy.g);
                v2.append(" but was ");
                v2.append(v0);
                v2.append(this.u());
                throw new IllegalStateException(v2.toString());
            }
        }

        long v0_1 = this.s().d();
        this.t();
        if(this.e > 0) {
            int[] v2_1 = this.g;
            int v3 = this.e - 1;
            ++v2_1[v3];
        }

        return v0_1;
    }

    public int m() {
        hy v0 = this.f();
        if(v0 != hy.g) {
            if(v0 == hy.f) {
            }
            else {
                StringBuilder v2 = new StringBuilder();
                v2.append("Expected ");
                v2.append(hy.g);
                v2.append(" but was ");
                v2.append(v0);
                v2.append(this.u());
                throw new IllegalStateException(v2.toString());
            }
        }

        int v0_1 = this.s().e();
        this.t();
        if(this.e > 0) {
            int[] v1 = this.g;
            int v2_1 = this.e - 1;
            ++v1[v2_1];
        }

        return v0_1;
    }

    public void n() {
        if(this.f() == hy.e) {
            this.g();
            this.f[this.e - 2] = "null";
        }
        else {
            this.t();
            this.f[this.e - 1] = "null";
        }

        int[] v0 = this.g;
        int v1 = this.e - 1;
        ++v0[v1];
    }

    public void o() {
        this.a(hy.e);
        Object v0 = this.s().next();
        this.a(((Map$Entry)v0).getValue());
        this.a(new gk(((Map$Entry)v0).getKey()));
    }

    public String p() {
        StringBuilder v0 = new StringBuilder();
        v0.append('$');
        int v1;
        for(v1 = 0; v1 < this.e; ++v1) {
            if((this.d[v1] instanceof gc)) {
                ++v1;
                if((this.d[v1] instanceof Iterator)) {
                    v0.append('[');
                    v0.append(this.g[v1]);
                    v0.append(']');
                }
            }
            else if((this.d[v1] instanceof gi)) {
                ++v1;
                if((this.d[v1] instanceof Iterator)) {
                    v0.append('.');
                    if(this.f[v1] != null) {
                        v0.append(this.f[v1]);
                    }
                }
            }
        }

        return v0.toString();
    }

    private Object s() {
        return this.d[this.e - 1];
    }

    private Object t() {
        Object[] v0 = this.d;
        int v1 = this.e - 1;
        this.e = v1;
        Object v0_1 = v0[v1];
        this.d[this.e] = null;
        return v0_1;
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }

    private String u() {
        return " at path " + this.p();
    }
}

