package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build$VERSION;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

class h extends g {
    class a extends GradientDrawable {
        a() {
            super();
        }

        public boolean isStateful() {
            return 1;
        }
    }

    private InsetDrawable x;

    h(w arg1, n arg2) {
        super(arg1, arg2);
    }

    private Animator a(float arg7, float arg8) {
        AnimatorSet v0 = new AnimatorSet();
        v0.play(ObjectAnimator.ofFloat(this.v, "elevation", new float[]{arg7}).setDuration(0)).with(ObjectAnimator.ofFloat(this.v, View.TRANSLATION_Z, new float[]{arg8}).setDuration(100));
        v0.setInterpolator(h.a);
        return ((Animator)v0);
    }

    public float a() {
        return this.v.getElevation();
    }

    void a(float arg9, float arg10, float arg11) {
        if(Build$VERSION.SDK_INT == 21) {
            this.v.refreshDrawableState();
        }
        else {
            StateListAnimator v0 = new StateListAnimator();
            v0.addState(h.p, this.a(arg9, arg11));
            v0.addState(h.q, this.a(arg9, arg10));
            v0.addState(h.r, this.a(arg9, arg10));
            v0.addState(h.s, this.a(arg9, arg10));
            AnimatorSet v10 = new AnimatorSet();
            ArrayList v11 = new ArrayList();
            ((List)v11).add(ObjectAnimator.ofFloat(this.v, "elevation", new float[]{arg9}).setDuration(0));
            long v6 = 100;
            if(Build$VERSION.SDK_INT >= 22 && Build$VERSION.SDK_INT <= 24) {
                ((List)v11).add(ObjectAnimator.ofFloat(this.v, View.TRANSLATION_Z, new float[]{this.v.getTranslationZ()}).setDuration(v6));
            }

            ((List)v11).add(ObjectAnimator.ofFloat(this.v, View.TRANSLATION_Z, new float[]{0f}).setDuration(v6));
            v10.playSequentially(((List)v11).toArray(new Animator[0]));
            v10.setInterpolator(h.a);
            v0.addState(h.t, ((Animator)v10));
            v0.addState(h.u, this.a(0f, 0f));
            this.v.setStateListAnimator(v0);
        }

        if(this.w.b()) {
            this.j();
        }
    }

    void a(ColorStateList arg3, PorterDuff$Mode arg4, ColorStateList arg5, int arg6) {
        Drawable v3_1;
        this.g = android.support.v4.graphics.drawable.a.g(this.p());
        android.support.v4.graphics.drawable.a.a(this.g, arg3);
        if(arg4 != null) {
            android.support.v4.graphics.drawable.a.a(this.g, arg4);
        }

        android.support.design.widget.a v4 = null;
        if(arg6 > 0) {
            this.i = this.a(arg6, arg3);
            LayerDrawable v3 = new LayerDrawable(new Drawable[]{this.i, this.g});
        }
        else {
            this.i = v4;
            v3_1 = this.g;
        }

        this.h = new RippleDrawable(android.support.design.f.a.a(arg5), v3_1, ((Drawable)v4));
        this.j = this.h;
        this.w.a(this.h);
    }

    void a(Rect arg6) {
        if(this.w.b()) {
            float v0 = this.w.a();
            float v2 = this.a() + this.m;
            int v3 = ((int)Math.ceil(((double)m.b(v2, v0, false))));
            int v0_1 = ((int)Math.ceil(((double)m.a(v2, v0, false))));
            arg6.set(v3, v0_1, v3, v0_1);
        }
        else {
            arg6.set(0, 0, 0, 0);
        }
    }

    void a(int[] arg3) {
        w v3;
        if(Build$VERSION.SDK_INT == 21) {
            float v0 = 0f;
            if(this.v.isEnabled()) {
                this.v.setElevation(this.k);
                if(this.v.isPressed()) {
                    v3 = this.v;
                    v0 = this.m;
                }
                else {
                    if(!this.v.isFocused() && !this.v.isHovered()) {
                        goto label_27;
                    }

                    v3 = this.v;
                    v0 = this.l;
                }
            }
            else {
                this.v.setElevation(0f);
            label_27:
                v3 = this.v;
            }

            v3.setTranslationZ(v0);
        }
    }

    void b(ColorStateList arg2) {
        if((this.h instanceof RippleDrawable)) {
            this.h.setColor(android.support.design.f.a.a(arg2));
        }
        else {
            super.b(arg2);
        }
    }

    void b(Rect arg8) {
        Drawable v0_1;
        n v8;
        if(this.w.b()) {
            this.x = new InsetDrawable(this.h, arg8.left, arg8.top, arg8.right, arg8.bottom);
            v8 = this.w;
            InsetDrawable v0 = this.x;
        }
        else {
            v8 = this.w;
            v0_1 = this.h;
        }

        v8.a(v0_1);
    }

    void g() {
    }

    void i() {
        this.j();
    }

    boolean m() {
        return 0;
    }

    android.support.design.widget.a n() {
        return new b();
    }

    GradientDrawable q() {
        return new a();
    }
}

