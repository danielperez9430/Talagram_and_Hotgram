package com.c.a;

import android.view.View;
import com.c.b.c;
import java.util.HashMap;
import java.util.Map;

public final class h extends l {
    private static final Map h;
    private Object i;
    private String j;
    private c k;

    static {
        h.h = new HashMap();
        h.h.put("alpha", i.a);
        h.h.put("pivotX", i.b);
        h.h.put("pivotY", i.c);
        h.h.put("translationX", i.d);
        h.h.put("translationY", i.e);
        h.h.put("rotation", i.f);
        h.h.put("rotationX", i.g);
        h.h.put("rotationY", i.h);
        h.h.put("scaleX", i.i);
        h.h.put("scaleY", i.j);
        h.h.put("scrollX", i.k);
        h.h.put("scrollY", i.l);
        h.h.put("x", i.m);
        h.h.put("y", i.n);
    }

    public h() {
        super();
    }

    private h(Object arg1, String arg2) {
        super();
        this.i = arg1;
        this.a(arg2);
    }

    public void a(String arg5) {
        if(this.f != null) {
            j v0 = this.f[0];
            String v2 = v0.c();
            v0.a(arg5);
            this.g.remove(v2);
            this.g.put(arg5, v0);
        }

        this.j = arg5;
        this.e = false;
    }

    public static h a(Object arg1, String arg2, float[] arg3) {
        h v0 = new h(arg1, arg2);
        v0.a(arg3);
        return v0;
    }

    public void a(float[] arg4) {
        if(this.f == null || this.f.length == 0) {
            j[] v0 = this.k != null ? new j[]{j.a(this.k, arg4)} : new j[]{j.a(this.j, arg4)};
            this.a(v0);
        }
        else {
            super.a(arg4);
        }
    }

    public static h a(Object arg1, String arg2, int[] arg3) {
        h v0 = new h(arg1, arg2);
        v0.a(arg3);
        return v0;
    }

    public void a(int[] arg4) {
        if(this.f == null || this.f.length == 0) {
            j[] v0 = this.k != null ? new j[]{j.a(this.k, arg4)} : new j[]{j.a(this.j, arg4)};
            this.a(v0);
        }
        else {
            super.a(arg4);
        }
    }

    public h a(long arg1) {
        super.b(arg1);
        return this;
    }

    public void a() {
        super.a();
    }

    void a(float arg4) {
        super.a(arg4);
        int v4 = this.f.length;
        int v0;
        for(v0 = 0; v0 < v4; ++v0) {
            this.f[v0].b(this.i);
        }
    }

    public void a(c arg5) {
        if(this.f != null) {
            j v0 = this.f[0];
            String v2 = v0.c();
            v0.a(arg5);
            this.g.remove(v2);
            this.g.put(this.j, v0);
        }

        if(this.k != null) {
            this.j = arg5.a();
        }

        this.k = arg5;
        this.e = false;
    }

    public l b(long arg1) {
        return this.a(arg1);
    }

    public a c() {
        return this.e();
    }

    public Object clone() {
        return this.e();
    }

    void d() {
        if(!this.e) {
            if(this.k == null && (com.c.c.a.a.a) && ((this.i instanceof View)) && (h.h.containsKey(this.j))) {
                this.a(h.h.get(this.j));
            }

            int v0 = this.f.length;
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                this.f[v1].a(this.i);
            }

            super.d();
        }
    }

    public h e() {
        return super.f();
    }

    public l f() {
        return this.e();
    }

    public String toString() {
        String v0_1 = "ObjectAnimator@" + Integer.toHexString(this.hashCode()) + ", target " + this.i;
        if(this.f != null) {
            int v1;
            for(v1 = 0; v1 < this.f.length; ++v1) {
                v0_1 = v0_1 + "\n    " + this.f[v1].toString();
            }
        }

        return v0_1;
    }
}

