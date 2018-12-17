package android.support.design.widget;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Matrix$ScaleToFit;
import android.graphics.Matrix;
import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.design.a.h;
import android.support.v4.view.t;
import android.view.View;
import android.view.ViewTreeObserver$OnPreDrawListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class g {
    class a extends f {
        a(g arg2) {
            this.a = arg2;
            super(arg2, null);
        }

        protected float a() {
            return 0;
        }
    }

    class b extends f {
        b(g arg2) {
            this.a = arg2;
            super(arg2, null);
        }

        protected float a() {
            return this.a.k + this.a.l;
        }
    }

    class c extends f {
        c(g arg2) {
            this.a = arg2;
            super(arg2, null);
        }

        protected float a() {
            return this.a.k + this.a.m;
        }
    }

    interface d {
        void a();

        void b();
    }

    class e extends f {
        e(g arg2) {
            this.a = arg2;
            super(arg2, null);
        }

        protected float a() {
            return this.a.k;
        }
    }

    abstract class f extends AnimatorListenerAdapter implements ValueAnimator$AnimatorUpdateListener {
        private boolean a;
        private float c;
        private float d;

        private f(g arg1) {
            this.b = arg1;
            super();
        }

        f(g arg1, android.support.design.widget.g$1 arg2) {
            this(arg1);
        }

        protected abstract float a();

        public void onAnimationEnd(Animator arg2) {
            this.b.f.a(this.d);
            this.a = false;
        }

        public void onAnimationUpdate(ValueAnimator arg5) {
            if(!this.a) {
                this.c = this.b.f.a();
                this.d = this.a();
                this.a = true;
            }

            this.b.f.a(this.c + (this.d - this.c) * arg5.getAnimatedFraction());
        }
    }

    private float A;
    private ArrayList B;
    private ArrayList C;
    private final Rect D;
    private final RectF E;
    private final RectF F;
    private final Matrix G;
    private ViewTreeObserver$OnPreDrawListener H;
    static final TimeInterpolator a;
    int b;
    Animator c;
    h d;
    h e;
    m f;
    Drawable g;
    Drawable h;
    android.support.design.widget.a i;
    Drawable j;
    float k;
    float l;
    float m;
    int n;
    float o;
    static final int[] p;
    static final int[] q;
    static final int[] r;
    static final int[] s;
    static final int[] t;
    static final int[] u;
    final w v;
    final n w;
    private h x;
    private h y;
    private final p z;

    static {
        g.a = android.support.design.a.a.c;
        g.p = new int[]{16842919, 16842910};
        g.q = new int[]{16843623, 16842908, 16842910};
        g.r = new int[]{16842908, 16842910};
        g.s = new int[]{16843623, 16842910};
        g.t = new int[]{16842910};
        g.u = new int[0];
    }

    g(w arg2, n arg3) {
        super();
        this.b = 0;
        this.o = 1f;
        this.D = new Rect();
        this.E = new RectF();
        this.F = new RectF();
        this.G = new Matrix();
        this.v = arg2;
        this.w = arg3;
        this.z = new p();
        this.z.a(g.p, this.a(new c(this)));
        this.z.a(g.q, this.a(new b(this)));
        this.z.a(g.r, this.a(new b(this)));
        this.z.a(g.s, this.a(new b(this)));
        this.z.a(g.t, this.a(new e(this)));
        this.z.a(g.u, this.a(new a(this)));
        this.A = this.v.getRotation();
    }

    private ValueAnimator a(f arg4) {
        ValueAnimator v0 = new ValueAnimator();
        v0.setInterpolator(g.a);
        v0.setDuration(100);
        v0.addListener(((Animator$AnimatorListener)arg4));
        v0.addUpdateListener(((ValueAnimator$AnimatorUpdateListener)arg4));
        v0.setFloatValues(new float[]{0f, 1f});
        return v0;
    }

    private AnimatorSet a(h arg7, float arg8, float arg9, float arg10) {
        ArrayList v0 = new ArrayList();
        ObjectAnimator v8 = ObjectAnimator.ofFloat(this.v, View.ALPHA, new float[]{arg8});
        arg7.b("opacity").a(((Animator)v8));
        ((List)v0).add(v8);
        v8 = ObjectAnimator.ofFloat(this.v, View.SCALE_X, new float[]{arg9});
        arg7.b("scale").a(((Animator)v8));
        ((List)v0).add(v8);
        v8 = ObjectAnimator.ofFloat(this.v, View.SCALE_Y, new float[]{arg9});
        arg7.b("scale").a(((Animator)v8));
        ((List)v0).add(v8);
        this.a(arg10, this.G);
        v8 = ObjectAnimator.ofObject(this.v, new android.support.design.a.f(), new android.support.design.a.g(), new Matrix[]{new Matrix(this.G)});
        arg7.b("iconScale").a(((Animator)v8));
        ((List)v0).add(v8);
        AnimatorSet v7 = new AnimatorSet();
        android.support.design.a.b.a(v7, ((List)v0));
        return v7;
    }

    private void a(float arg6, Matrix arg7) {
        arg7.reset();
        Drawable v0 = this.v.getDrawable();
        if(v0 != null && this.n != 0) {
            RectF v1 = this.E;
            RectF v2 = this.F;
            v1.set(0f, 0f, ((float)v0.getIntrinsicWidth()), ((float)v0.getIntrinsicHeight()));
            v2.set(0f, 0f, ((float)this.n), ((float)this.n));
            arg7.setRectToRect(v1, v2, Matrix$ScaleToFit.CENTER);
            arg7.postScale(arg6, arg6, (((float)this.n)) / 2f, (((float)this.n)) / 2f);
        }
    }

    float a() {
        return this.k;
    }

    android.support.design.widget.a a(int arg7, ColorStateList arg8) {
        Context v0 = this.v.getContext();
        android.support.design.widget.a v1 = this.n();
        v1.a(android.support.v4.content.a.c(v0, android.support.design.a$c.design_fab_stroke_top_outer_color), android.support.v4.content.a.c(v0, android.support.design.a$c.design_fab_stroke_top_inner_color), android.support.v4.content.a.c(v0, android.support.design.a$c.design_fab_stroke_end_inner_color), android.support.v4.content.a.c(v0, android.support.design.a$c.design_fab_stroke_end_outer_color));
        v1.a(((float)arg7));
        v1.a(arg8);
        return v1;
    }

    final void a(float arg3) {
        if(this.k != arg3) {
            this.k = arg3;
            this.a(this.k, this.l, this.m);
        }
    }

    void a(float arg1, float arg2, float arg3) {
        if(this.f != null) {
            this.f.a(arg1, this.m + arg1);
            this.j();
        }
    }

    final void a(int arg2) {
        if(this.n != arg2) {
            this.n = arg2;
            this.d();
        }
    }

    void a(Animator$AnimatorListener arg2) {
        if(this.B == null) {
            this.B = new ArrayList();
        }

        this.B.add(arg2);
    }

    void a(ColorStateList arg2) {
        if(this.g != null) {
            android.support.v4.graphics.drawable.a.a(this.g, arg2);
        }

        if(this.i != null) {
            this.i.a(arg2);
        }
    }

    void a(ColorStateList arg8, PorterDuff$Mode arg9, ColorStateList arg10, int arg11) {
        Drawable[] v8;
        this.g = android.support.v4.graphics.drawable.a.g(this.p());
        android.support.v4.graphics.drawable.a.a(this.g, arg8);
        if(arg9 != null) {
            android.support.v4.graphics.drawable.a.a(this.g, arg9);
        }

        this.h = android.support.v4.graphics.drawable.a.g(this.p());
        android.support.v4.graphics.drawable.a.a(this.h, android.support.design.f.a.a(arg10));
        int v9 = 2;
        if(arg11 > 0) {
            this.i = this.a(arg11, arg8);
            v8 = new Drawable[3];
            v8[0] = this.i;
            v8[1] = this.g;
            v8[v9] = this.h;
        }
        else {
            this.i = null;
            v8 = new Drawable[v9];
            v8[0] = this.g;
            v8[1] = this.h;
        }

        this.j = new LayerDrawable(v8);
        this.f = new m(this.v.getContext(), this.j, this.w.a(), this.k, this.k + this.m);
        this.f.a(false);
        this.w.a(this.f);
    }

    void a(PorterDuff$Mode arg2) {
        if(this.g != null) {
            android.support.v4.graphics.drawable.a.a(this.g, arg2);
        }
    }

    void a(Rect arg2) {
        this.f.getPadding(arg2);
    }

    final void a(h arg1) {
        this.d = arg1;
    }

    void a(d arg3, boolean arg4) {
        if(this.s()) {
            return;
        }

        if(this.c != null) {
            this.c.cancel();
        }

        if(this.w()) {
            h v0 = this.e != null ? this.e : this.u();
            AnimatorSet v0_1 = this.a(v0, 0f, 0f, 0f);
            v0_1.addListener(new AnimatorListenerAdapter(arg4, arg3) {
                private boolean d;

                public void onAnimationCancel(Animator arg1) {
                    this.d = true;
                }

                public void onAnimationEnd(Animator arg3) {
                    this.c.b = 0;
                    this.c.c = null;
                    if(!this.d) {
                        w v3 = this.c.v;
                        int v0 = this.a ? 8 : 4;
                        v3.a(v0, this.a);
                        if(this.b == null) {
                            return;
                        }

                        this.b.b();
                    }
                }

                public void onAnimationStart(Animator arg4) {
                    this.c.v.a(0, this.a);
                    this.c.b = 1;
                    this.c.c = arg4;
                    this.d = false;
                }
            });
            if(this.C != null) {
                Iterator v3 = this.C.iterator();
                while(v3.hasNext()) {
                    v0_1.addListener(v3.next());
                }
            }

            v0_1.start();
        }
        else {
            w v0_2 = this.v;
            int v1 = arg4 ? 8 : 4;
            v0_2.a(v1, arg4);
            if(arg3 == null) {
                return;
            }

            arg3.b();
        }
    }

    void a(int[] arg2) {
        this.z.a(arg2);
    }

    float b() {
        return this.l;
    }

    final void b(float arg3) {
        if(this.l != arg3) {
            this.l = arg3;
            this.a(this.k, this.l, this.m);
        }
    }

    void b(Animator$AnimatorListener arg2) {
        if(this.B == null) {
            return;
        }

        this.B.remove(arg2);
    }

    void b(ColorStateList arg2) {
        if(this.h != null) {
            android.support.v4.graphics.drawable.a.a(this.h, android.support.design.f.a.a(arg2));
        }
    }

    void b(Rect arg1) {
    }

    final void b(h arg1) {
        this.e = arg1;
    }

    void b(d arg4, boolean arg5) {
        if(this.r()) {
            return;
        }

        if(this.c != null) {
            this.c.cancel();
        }

        float v1 = 1f;
        if(this.w()) {
            if(this.v.getVisibility() != 0) {
                this.v.setAlpha(0f);
                this.v.setScaleY(0f);
                this.v.setScaleX(0f);
                this.d(0f);
            }

            h v0 = this.d != null ? this.d : this.t();
            AnimatorSet v0_1 = this.a(v0, v1, v1, v1);
            v0_1.addListener(new AnimatorListenerAdapter(arg5, arg4) {
                public void onAnimationEnd(Animator arg2) {
                    this.c.b = 0;
                    this.c.c = null;
                    if(this.b != null) {
                        this.b.a();
                    }
                }

                public void onAnimationStart(Animator arg4) {
                    this.c.v.a(0, this.a);
                    this.c.b = 2;
                    this.c.c = arg4;
                }
            });
            if(this.B != null) {
                Iterator v4 = this.B.iterator();
                while(v4.hasNext()) {
                    v0_1.addListener(v4.next());
                }
            }

            v0_1.start();
        }
        else {
            this.v.a(0, arg5);
            this.v.setAlpha(v1);
            this.v.setScaleY(v1);
            this.v.setScaleX(v1);
            this.d(v1);
            if(arg4 == null) {
                return;
            }

            arg4.a();
        }
    }

    float c() {
        return this.m;
    }

    final void c(float arg3) {
        if(this.m != arg3) {
            this.m = arg3;
            this.a(this.k, this.l, this.m);
        }
    }

    public void c(Animator$AnimatorListener arg2) {
        if(this.C == null) {
            this.C = new ArrayList();
        }

        this.C.add(arg2);
    }

    final void d() {
        this.d(this.o);
    }

    final void d(float arg2) {
        this.o = arg2;
        Matrix v0 = this.G;
        this.a(arg2, v0);
        this.v.setImageMatrix(v0);
    }

    public void d(Animator$AnimatorListener arg2) {
        if(this.C == null) {
            return;
        }

        this.C.remove(arg2);
    }

    final h e() {
        return this.d;
    }

    final h f() {
        return this.e;
    }

    void g() {
        this.z.a();
    }

    final Drawable h() {
        return this.j;
    }

    void i() {
    }

    final void j() {
        Rect v0 = this.D;
        this.a(v0);
        this.b(v0);
        this.w.a(v0.left, v0.top, v0.right, v0.bottom);
    }

    void k() {
        if(this.m()) {
            this.v();
            this.v.getViewTreeObserver().addOnPreDrawListener(this.H);
        }
    }

    void l() {
        if(this.H != null) {
            this.v.getViewTreeObserver().removeOnPreDrawListener(this.H);
            this.H = null;
        }
    }

    boolean m() {
        return 1;
    }

    android.support.design.widget.a n() {
        return new android.support.design.widget.a();
    }

    void o() {
        float v0 = this.v.getRotation();
        if(this.A != v0) {
            this.A = v0;
            this.x();
        }
    }

    GradientDrawable p() {
        GradientDrawable v0 = this.q();
        v0.setShape(1);
        v0.setColor(-1);
        return v0;
    }

    GradientDrawable q() {
        return new GradientDrawable();
    }

    boolean r() {
        boolean v1 = false;
        if(this.v.getVisibility() != 0) {
            if(this.b == 2) {
                v1 = true;
            }

            return v1;
        }

        if(this.b != 1) {
            v1 = true;
        }

        return v1;
    }

    boolean s() {
        boolean v1 = false;
        if(this.v.getVisibility() == 0) {
            if(this.b == 1) {
                v1 = true;
            }

            return v1;
        }

        if(this.b != 2) {
            v1 = true;
        }

        return v1;
    }

    private h t() {
        if(this.x == null) {
            this.x = h.a(this.v.getContext(), android.support.design.a$a.design_fab_show_motion_spec);
        }

        return this.x;
    }

    private h u() {
        if(this.y == null) {
            this.y = h.a(this.v.getContext(), android.support.design.a$a.design_fab_hide_motion_spec);
        }

        return this.y;
    }

    private void v() {
        if(this.H == null) {
            this.H = new ViewTreeObserver$OnPreDrawListener() {
                public boolean onPreDraw() {
                    this.a.o();
                    return 1;
                }
            };
        }
    }

    private boolean w() {
        boolean v0 = !t.A(this.v) || (this.v.isInEditMode()) ? false : true;
        return v0;
    }

    private void x() {
        // Method was not decompiled
    }
}

