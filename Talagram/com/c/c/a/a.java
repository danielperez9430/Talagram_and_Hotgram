package com.c.c.a;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build$VERSION;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public final class a extends Animation {
    public static final boolean a;
    private static final WeakHashMap b;
    private final WeakReference c;
    private final Camera d;
    private boolean e;
    private float f;
    private float g;
    private float h;
    private float i;
    private float j;
    private float k;
    private float l;
    private float m;
    private float n;
    private float o;
    private final RectF p;
    private final RectF q;
    private final Matrix r;

    static {
        boolean v0 = Integer.valueOf(Build$VERSION.SDK).intValue() < 11 ? true : false;
        a.a = v0;
        a.b = new WeakHashMap();
    }

    private a(View arg3) {
        super();
        this.d = new Camera();
        this.f = 1f;
        this.l = 1f;
        this.m = 1f;
        this.p = new RectF();
        this.q = new RectF();
        this.r = new Matrix();
        this.setDuration(0);
        this.setFillAfter(true);
        arg3.setAnimation(((Animation)this));
        this.c = new WeakReference(arg3);
    }

    public static a a(View arg2) {
        Object v0 = a.b.get(arg2);
        if(v0 == null || v0 != arg2.getAnimation()) {
            a v0_1 = new a(arg2);
            a.b.put(arg2, v0_1);
        }

        return ((a)v0);
    }

    public float a() {
        return this.f;
    }

    public void a(float arg2) {
        if(this.f != arg2) {
            this.f = arg2;
            Object v2 = this.c.get();
            if(v2 != null) {
                ((View)v2).invalidate();
            }
        }
    }

    public void a(int arg3) {
        Object v0 = this.c.get();
        if(v0 != null) {
            ((View)v0).scrollTo(arg3, ((View)v0).getScrollY());
        }
    }

    private void a(Matrix arg9, View arg10) {
        float v0 = ((float)arg10.getWidth());
        float v10 = ((float)arg10.getHeight());
        boolean v1 = this.e;
        float v2 = 2f;
        float v3 = v1 ? this.g : v0 / v2;
        float v1_1 = v1 ? this.h : v10 / v2;
        v2 = this.i;
        float v4 = this.j;
        float v5 = this.k;
        if(v2 != 0f || v4 != 0f || v5 != 0f) {
            Camera v6 = this.d;
            v6.save();
            v6.rotateX(v2);
            v6.rotateY(v4);
            v6.rotateZ(-v5);
            v6.getMatrix(arg9);
            v6.restore();
            arg9.preTranslate(-v3, -v1_1);
            arg9.postTranslate(v3, v1_1);
        }

        v2 = this.l;
        v4 = this.m;
        v5 = 1f;
        if(v2 != v5 || v4 != v5) {
            arg9.postScale(v2, v4);
            arg9.postTranslate(-(v3 / v0) * (v2 * v0 - v0), -(v1_1 / v10) * (v4 * v10 - v10));
        }

        arg9.postTranslate(this.n, this.o);
    }

    private void a(RectF arg4, View arg5) {
        float v5;
        arg4.set(0f, 0f, ((float)arg5.getWidth()), ((float)arg5.getHeight()));
        Matrix v0 = this.r;
        v0.reset();
        this.a(v0, arg5);
        this.r.mapRect(arg4);
        arg4.offset(((float)arg5.getLeft()), ((float)arg5.getTop()));
        if(arg4.right < arg4.left) {
            v5 = arg4.right;
            arg4.right = arg4.left;
            arg4.left = v5;
        }

        if(arg4.bottom < arg4.top) {
            v5 = arg4.top;
            arg4.top = arg4.bottom;
            arg4.bottom = v5;
        }
    }

    protected void applyTransformation(float arg2, Transformation arg3) {
        Object v2 = this.c.get();
        if(v2 != null) {
            arg3.setAlpha(this.f);
            this.a(arg3.getMatrix(), ((View)v2));
        }
    }

    public void b(int arg3) {
        Object v0 = this.c.get();
        if(v0 != null) {
            ((View)v0).scrollTo(((View)v0).getScrollX(), arg3);
        }
    }

    public float b() {
        return this.g;
    }

    public void b(float arg2) {
        if(!this.e || this.g != arg2) {
            this.o();
            this.e = true;
            this.g = arg2;
            this.p();
        }
    }

    public float c() {
        return this.h;
    }

    public void c(float arg2) {
        if(!this.e || this.h != arg2) {
            this.o();
            this.e = true;
            this.h = arg2;
            this.p();
        }
    }

    public float d() {
        return this.k;
    }

    public void d(float arg2) {
        if(this.k != arg2) {
            this.o();
            this.k = arg2;
            this.p();
        }
    }

    public float e() {
        return this.i;
    }

    public void e(float arg2) {
        if(this.i != arg2) {
            this.o();
            this.i = arg2;
            this.p();
        }
    }

    public float f() {
        return this.j;
    }

    public void f(float arg2) {
        if(this.j != arg2) {
            this.o();
            this.j = arg2;
            this.p();
        }
    }

    public float g() {
        return this.l;
    }

    public void g(float arg2) {
        if(this.l != arg2) {
            this.o();
            this.l = arg2;
            this.p();
        }
    }

    public float h() {
        return this.m;
    }

    public void h(float arg2) {
        if(this.m != arg2) {
            this.o();
            this.m = arg2;
            this.p();
        }
    }

    public int i() {
        Object v0 = this.c.get();
        if(v0 == null) {
            return 0;
        }

        return ((View)v0).getScrollX();
    }

    public void i(float arg2) {
        if(this.n != arg2) {
            this.o();
            this.n = arg2;
            this.p();
        }
    }

    public void j(float arg2) {
        if(this.o != arg2) {
            this.o();
            this.o = arg2;
            this.p();
        }
    }

    public int j() {
        Object v0 = this.c.get();
        if(v0 == null) {
            return 0;
        }

        return ((View)v0).getScrollY();
    }

    public void k(float arg2) {
        Object v0 = this.c.get();
        if(v0 != null) {
            this.i(arg2 - (((float)((View)v0).getLeft())));
        }
    }

    public float k() {
        return this.n;
    }

    public float l() {
        return this.o;
    }

    public void l(float arg2) {
        Object v0 = this.c.get();
        if(v0 != null) {
            this.j(arg2 - (((float)((View)v0).getTop())));
        }
    }

    public float m() {
        Object v0 = this.c.get();
        if(v0 == null) {
            return 0;
        }

        return (((float)((View)v0).getLeft())) + this.n;
    }

    public float n() {
        Object v0 = this.c.get();
        if(v0 == null) {
            return 0;
        }

        return (((float)((View)v0).getTop())) + this.o;
    }

    private void o() {
        Object v0 = this.c.get();
        if(v0 != null) {
            this.a(this.p, ((View)v0));
        }
    }

    private void p() {
        Object v0 = this.c.get();
        if(v0 != null) {
            if(((View)v0).getParent() == null) {
            }
            else {
                RectF v1 = this.q;
                this.a(v1, ((View)v0));
                v1.union(this.p);
                ((View)v0).getParent().invalidate(((int)Math.floor(((double)v1.left))), ((int)Math.floor(((double)v1.top))), ((int)Math.ceil(((double)v1.right))), ((int)Math.ceil(((double)v1.bottom))));
            }
        }
    }
}

