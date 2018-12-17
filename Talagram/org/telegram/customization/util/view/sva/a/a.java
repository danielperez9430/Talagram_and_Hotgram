package org.telegram.customization.util.view.sva.a;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PathMeasure;
import android.view.View;
import android.view.animation.LinearInterpolator;
import java.lang.ref.WeakReference;

public abstract class a {
    protected float a;
    protected float[] b;
    protected int c;
    protected int d;
    protected float e;
    protected float f;
    protected int g;
    private WeakReference h;

    public a() {
        super();
        this.a = -1f;
        this.b = new float[2];
        this.g = 0;
    }

    public void a(int arg1) {
        this.c = arg1;
    }

    public void a(float arg1) {
        this.e = arg1;
    }

    public abstract void a(Canvas arg1, Paint arg2);

    public void a(View arg2) {
        this.h = new WeakReference(arg2);
    }

    public ValueAnimator a(float arg7, float arg8, long arg9) {
        return this.a(arg7, arg8, arg9, null);
    }

    public ValueAnimator a(float arg3, float arg4, long arg5, PathMeasure arg7) {
        ValueAnimator v3 = ValueAnimator.ofFloat(new float[]{arg3, arg4});
        v3.setDuration(arg5);
        v3.setInterpolator(new LinearInterpolator());
        v3.addUpdateListener(new ValueAnimator$AnimatorUpdateListener(arg7) {
            public void onAnimationUpdate(ValueAnimator arg4) {
                this.b.a = arg4.getAnimatedValue().floatValue();
                if(this.a != null) {
                    this.a.getPosTan(this.b.a, this.b.b, null);
                }

                this.b.a().invalidate();
            }
        });
        v3.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator arg1) {
                super.onAnimationEnd(arg1);
            }
        });
        if(!v3.isRunning()) {
            v3.start();
        }

        this.a = 0f;
        return v3;
    }

    public View a() {
        Object v0;
        if(this.h != null) {
            v0 = this.h.get();
        }
        else {
            View v0_1 = null;
        }

        return ((View)v0);
    }

    public void b(int arg1) {
        this.d = arg1;
    }

    public void b(float arg1) {
        this.f = arg1;
    }

    public int b() {
        int v0 = this.a() != null ? this.a().getWidth() : 0;
        return v0;
    }

    public int c() {
        int v0 = this.a() != null ? this.a().getHeight() : 0;
        return v0;
    }

    public void d() {
    }

    public void e() {
    }

    public ValueAnimator f() {
        return this.a(0f, 1f, 500);
    }
}

