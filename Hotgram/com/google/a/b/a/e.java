package com.google.a.b.a;

import com.google.a.d.a;
import com.google.a.d.b;
import com.google.a.i;
import com.google.a.l;
import com.google.a.n;
import com.google.a.o;
import com.google.a.q;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map$Entry;

public final class e extends a {
    final class com.google.a.b.a.e$1 extends Reader {
        com.google.a.b.a.e$1() {
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
        e.b = new com.google.a.b.a.e$1();
        e.c = new Object();
    }

    public e(l arg3) {
        super(e.b);
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

    private void a(b arg4) {
        if(this.f() == arg4) {
            return;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Expected ");
        v1.append(arg4);
        v1.append(" but was ");
        v1.append(this.f());
        v1.append(this.v());
        throw new IllegalStateException(v1.toString());
    }

    public void a() {
        this.a(b.a);
        this.a(this.t().iterator());
        this.g[this.e - 1] = 0;
    }

    public void b() {
        this.a(b.b);
        this.u();
        this.u();
        if(this.e > 0) {
            int[] v0 = this.g;
            int v1 = this.e - 1;
            ++v0[v1];
        }
    }

    public void c() {
        this.a(b.c);
        this.a(this.t().o().iterator());
    }

    public void close() {
        this.d = new Object[]{e.c};
        this.e = 1;
    }

    public void d() {
        this.a(b.d);
        this.u();
        this.u();
        if(this.e > 0) {
            int[] v0 = this.g;
            int v1 = this.e - 1;
            ++v0[v1];
        }
    }

    public boolean e() {
        b v0 = this.f();
        boolean v0_1 = v0 == b.d || v0 == b.b ? false : true;
        return v0_1;
    }

    public b f() {
        if(this.e == 0) {
            return b.j;
        }

        Object v0 = this.t();
        if((v0 instanceof Iterator)) {
            boolean v1 = this.d[this.e - 2] instanceof o;
            if(((Iterator)v0).hasNext()) {
                if(v1) {
                    return b.e;
                }

                this.a(((Iterator)v0).next());
                return this.f();
            }

            b v0_1 = v1 ? b.d : b.b;
            return v0_1;
        }

        if((v0 instanceof o)) {
            return b.c;
        }

        if((v0 instanceof i)) {
            return b.a;
        }

        if((v0 instanceof q)) {
            if(((q)v0).q()) {
                return b.f;
            }

            if(((q)v0).o()) {
                return b.h;
            }

            if(((q)v0).p()) {
                return b.g;
            }

            throw new AssertionError();
        }

        if((v0 instanceof n)) {
            return b.i;
        }

        if(v0 == e.c) {
            throw new IllegalStateException("JsonReader is closed");
        }

        throw new AssertionError();
    }

    public String g() {
        this.a(b.e);
        Object v0 = this.t().next();
        Object v1 = ((Map$Entry)v0).getKey();
        this.f[this.e - 1] = v1;
        this.a(((Map$Entry)v0).getValue());
        return ((String)v1);
    }

    public String h() {
        b v0 = this.f();
        if(v0 != b.f) {
            if(v0 == b.g) {
            }
            else {
                StringBuilder v2 = new StringBuilder();
                v2.append("Expected ");
                v2.append(b.f);
                v2.append(" but was ");
                v2.append(v0);
                v2.append(this.v());
                throw new IllegalStateException(v2.toString());
            }
        }

        String v0_1 = this.u().b();
        if(this.e > 0) {
            int[] v1 = this.g;
            int v2_1 = this.e - 1;
            ++v1[v2_1];
        }

        return v0_1;
    }

    public boolean i() {
        this.a(b.h);
        boolean v0 = this.u().f();
        if(this.e > 0) {
            int[] v1 = this.g;
            int v2 = this.e - 1;
            ++v1[v2];
        }

        return v0;
    }

    public void j() {
        this.a(b.i);
        this.u();
        if(this.e > 0) {
            int[] v0 = this.g;
            int v1 = this.e - 1;
            ++v0[v1];
        }
    }

    public double k() {
        b v0 = this.f();
        if(v0 != b.g) {
            if(v0 == b.f) {
            }
            else {
                StringBuilder v2 = new StringBuilder();
                v2.append("Expected ");
                v2.append(b.g);
                v2.append(" but was ");
                v2.append(v0);
                v2.append(this.v());
                throw new IllegalStateException(v2.toString());
            }
        }

        double v0_1 = this.t().c();
        if(!this.q() && ((Double.isNaN(v0_1)) || (Double.isInfinite(v0_1)))) {
            StringBuilder v3 = new StringBuilder();
            v3.append("JSON forbids NaN and infinities: ");
            v3.append(v0_1);
            throw new NumberFormatException(v3.toString());
        }

        this.u();
        if(this.e > 0) {
            int[] v2_1 = this.g;
            int v3_1 = this.e - 1;
            ++v2_1[v3_1];
        }

        return v0_1;
    }

    public long l() {
        b v0 = this.f();
        if(v0 != b.g) {
            if(v0 == b.f) {
            }
            else {
                StringBuilder v2 = new StringBuilder();
                v2.append("Expected ");
                v2.append(b.g);
                v2.append(" but was ");
                v2.append(v0);
                v2.append(this.v());
                throw new IllegalStateException(v2.toString());
            }
        }

        long v0_1 = this.t().d();
        this.u();
        if(this.e > 0) {
            int[] v2_1 = this.g;
            int v3 = this.e - 1;
            ++v2_1[v3];
        }

        return v0_1;
    }

    public int m() {
        b v0 = this.f();
        if(v0 != b.g) {
            if(v0 == b.f) {
            }
            else {
                StringBuilder v2 = new StringBuilder();
                v2.append("Expected ");
                v2.append(b.g);
                v2.append(" but was ");
                v2.append(v0);
                v2.append(this.v());
                throw new IllegalStateException(v2.toString());
            }
        }

        int v0_1 = this.t().e();
        this.u();
        if(this.e > 0) {
            int[] v1 = this.g;
            int v2_1 = this.e - 1;
            ++v1[v2_1];
        }

        return v0_1;
    }

    public void n() {
        if(this.f() == b.e) {
            this.g();
            this.f[this.e - 2] = "null";
        }
        else {
            this.u();
            if(this.e > 0) {
                this.f[this.e - 1] = "null";
            }
        }

        if(this.e > 0) {
            int[] v0 = this.g;
            int v1 = this.e - 1;
            ++v0[v1];
        }
    }

    public void o() {
        this.a(b.e);
        Object v0 = this.t().next();
        this.a(((Map$Entry)v0).getValue());
        this.a(new q(((Map$Entry)v0).getKey()));
    }

    public String p() {
        StringBuilder v0 = new StringBuilder();
        v0.append('$');
        int v1;
        for(v1 = 0; v1 < this.e; ++v1) {
            if((this.d[v1] instanceof i)) {
                ++v1;
                if((this.d[v1] instanceof Iterator)) {
                    v0.append('[');
                    v0.append(this.g[v1]);
                    v0.append(']');
                }
            }
            else if((this.d[v1] instanceof o)) {
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

    private Object t() {
        return this.d[this.e - 1];
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }

    private Object u() {
        Object[] v0 = this.d;
        int v1 = this.e - 1;
        this.e = v1;
        Object v0_1 = v0[v1];
        this.d[this.e] = null;
        return v0_1;
    }

    private String v() {
        return " at path " + this.p();
    }
}

