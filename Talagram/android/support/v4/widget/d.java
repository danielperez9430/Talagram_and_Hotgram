package android.support.v4.widget;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint$Cap;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.Path$FillType;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v4.f.l;
import android.support.v4.view.b.b;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

public class d extends Drawable implements Animatable {
    class a {
        final RectF a;
        final Paint b;
        final Paint c;
        final Paint d;
        float e;
        float f;
        float g;
        float h;
        int[] i;
        int j;
        float k;
        float l;
        float m;
        boolean n;
        Path o;
        float p;
        float q;
        int r;
        int s;
        int t;
        int u;

        a() {
            super();
            this.a = new RectF();
            this.b = new Paint();
            this.c = new Paint();
            this.d = new Paint();
            this.e = 0f;
            this.f = 0f;
            this.g = 0f;
            this.h = 5f;
            this.p = 1f;
            this.t = 255;
            this.b.setStrokeCap(Paint$Cap.SQUARE);
            this.b.setAntiAlias(true);
            this.b.setStyle(Paint$Style.STROKE);
            this.c.setStyle(Paint$Style.FILL);
            this.c.setAntiAlias(true);
            this.d.setColor(0);
        }

        void a(int[] arg1) {
            this.i = arg1;
            this.b(0);
        }

        void a(boolean arg2) {
            if(this.n != arg2) {
                this.n = arg2;
            }
        }

        void a(float arg2) {
            this.h = arg2;
            this.b.setStrokeWidth(arg2);
        }

        void a(float arg1, float arg2) {
            this.r = ((int)arg1);
            this.s = ((int)arg2);
        }

        int a() {
            return this.i[this.b()];
        }

        void a(int arg1) {
            this.u = arg1;
        }

        void a(Canvas arg9, Rect arg10) {
            RectF v6 = this.a;
            float v2 = 2f;
            float v0 = this.q + this.h / v2;
            if(this.q <= 0f) {
                v0 = (((float)Math.min(arg10.width(), arg10.height()))) / v2 - Math.max((((float)this.r)) * this.p / v2, this.h / v2);
            }

            v6.set((((float)arg10.centerX())) - v0, (((float)arg10.centerY())) - v0, (((float)arg10.centerX())) + v0, (((float)arg10.centerY())) + v0);
            float v10 = (this.e + this.g) * 360f;
            float v7 = (this.f + this.g) * 360f - v10;
            this.b.setColor(this.u);
            this.b.setAlpha(this.t);
            v0 = this.h / v2;
            v6.inset(v0, v0);
            arg9.drawCircle(v6.centerX(), v6.centerY(), v6.width() / v2, this.d);
            v0 = -v0;
            v6.inset(v0, v0);
            arg9.drawArc(v6, v10, v7, false, this.b);
            this.a(arg9, v10, v7, v6);
        }

        void a(ColorFilter arg2) {
            this.b.setColorFilter(arg2);
        }

        void a(Canvas arg8, float arg9, float arg10, RectF arg11) {
            if(this.n) {
                if(this.o == null) {
                    this.o = new Path();
                    this.o.setFillType(Path$FillType.EVEN_ODD);
                }
                else {
                    this.o.reset();
                }

                float v0 = Math.min(arg11.width(), arg11.height()) / 2f;
                float v2 = (((float)this.r)) * this.p / 2f;
                this.o.moveTo(0f, 0f);
                this.o.lineTo((((float)this.r)) * this.p, 0f);
                this.o.lineTo((((float)this.r)) * this.p / 2f, (((float)this.s)) * this.p);
                this.o.offset(v0 + arg11.centerX() - v2, arg11.centerY() + this.h / 2f);
                this.o.close();
                this.c.setColor(this.u);
                this.c.setAlpha(this.t);
                arg8.save();
                arg8.rotate(arg9 + arg10, arg11.centerX(), arg11.centerY());
                arg8.drawPath(this.o, this.c);
                arg8.restore();
            }
        }

        void b(int arg2) {
            this.j = arg2;
            this.u = this.i[this.j];
        }

        void b(float arg1) {
            this.e = arg1;
        }

        int b() {
            return (this.j + 1) % this.i.length;
        }

        void c() {
            this.b(this.b());
        }

        void c(float arg1) {
            this.f = arg1;
        }

        void c(int arg1) {
            this.t = arg1;
        }

        void d(float arg1) {
            this.g = arg1;
        }

        int d() {
            return this.t;
        }

        void e(float arg1) {
            this.q = arg1;
        }

        float e() {
            return this.e;
        }

        float f() {
            return this.k;
        }

        void f(float arg2) {
            if(arg2 != this.p) {
                this.p = arg2;
            }
        }

        float g() {
            return this.l;
        }

        int h() {
            return this.i[this.j];
        }

        float i() {
            return this.f;
        }

        float j() {
            return this.m;
        }

        void k() {
            this.k = this.e;
            this.l = this.f;
            this.m = this.g;
        }

        void l() {
            this.k = 0f;
            this.l = 0f;
            this.m = 0f;
            this.b(0f);
            this.c(0f);
            this.d(0f);
        }
    }

    float a;
    boolean b;
    private static final Interpolator c;
    private static final Interpolator d;
    private static final int[] e;
    private final a f;
    private float g;
    private Resources h;
    private Animator i;

    static {
        d.c = new LinearInterpolator();
        d.d = new b();
        d.e = new int[]{-16777216};
    }

    public d(Context arg2) {
        super();
        this.h = l.a(arg2).getResources();
        this.f = new a();
        this.f.a(d.e);
        this.a(2.5f);
        this.a();
    }

    public void a(float arg2) {
        this.f.a(arg2);
        this.invalidateSelf();
    }

    private void a() {
        a v0 = this.f;
        ValueAnimator v1 = ValueAnimator.ofFloat(new float[]{0f, 1f});
        v1.addUpdateListener(new ValueAnimator$AnimatorUpdateListener(v0) {
            public void onAnimationUpdate(ValueAnimator arg4) {
                float v4 = arg4.getAnimatedValue().floatValue();
                this.b.a(v4, this.a);
                this.b.a(v4, this.a, false);
                this.b.invalidateSelf();
            }
        });
        v1.setRepeatCount(-1);
        v1.setRepeatMode(1);
        v1.setInterpolator(d.c);
        v1.addListener(new Animator$AnimatorListener(v0) {
            public void onAnimationCancel(Animator arg1) {
            }

            public void onAnimationEnd(Animator arg1) {
            }

            public void onAnimationRepeat(Animator arg5) {
                float v2 = 1f;
                this.b.a(v2, this.a, true);
                this.a.k();
                this.a.c();
                if(this.b.b) {
                    this.b.b = false;
                    arg5.cancel();
                    arg5.setDuration(1332);
                    arg5.start();
                    this.a.a(false);
                }
                else {
                    this.b.a += v2;
                }
            }

            public void onAnimationStart(Animator arg2) {
                this.b.a = 0f;
            }
        });
        this.i = ((Animator)v1);
    }

    private int a(float arg7, int arg8, int arg9) {
        int v0 = arg8 >> 24 & 255;
        int v1 = arg8 >> 16 & 255;
        int v2 = arg8 >> 8 & 255;
        arg8 &= 255;
        return v0 + (((int)((((float)((arg9 >> 24 & 255) - v0))) * arg7))) << 24 | v1 + (((int)((((float)((arg9 >> 16 & 255) - v1))) * arg7))) << 16 | v2 + (((int)((((float)((arg9 >> 8 & 255) - v2))) * arg7))) << 8 | arg8 + (((int)(arg7 * (((float)((arg9 & 255) - arg8))))));
    }

    void a(float arg3, a arg4) {
        float v0 = 0.75f;
        int v3 = arg3 > v0 ? this.a((arg3 - v0) / 0.25f, arg4.h(), arg4.a()) : arg4.h();
        arg4.a(v3);
    }

    void a(float arg8, a arg9, boolean arg10) {
        if(this.b) {
            this.b(arg8, arg9);
        }
        else {
            float v0 = 1f;
            if(arg8 == v0 && !arg10) {
                return;
            }

            float v10 = arg9.j();
            float v1 = 0.5f;
            float v3 = 0.01f;
            float v4 = 0.79f;
            if(Float.compare(arg8, v1) < 0) {
                v0 = arg8 / v1;
                v1 = arg9.f();
                float v6 = v1;
                v1 = d.d.getInterpolation(v0) * v4 + v3 + v1;
                v0 = v6;
            }
            else {
                float v2 = (arg8 - v1) / v1;
                v1 = arg9.f() + v4;
                v0 = v1 - ((v0 - d.d.getInterpolation(v2)) * v4 + v3);
            }

            v10 += 0.21f * arg8;
            arg8 = (arg8 + this.a) * 216f;
            arg9.b(v0);
            arg9.c(v1);
            arg9.d(v10);
            this.d(arg8);
        }
    }

    private void a(float arg3, float arg4, float arg5, float arg6) {
        a v0 = this.f;
        float v1 = this.h.getDisplayMetrics().density;
        v0.a(arg4 * v1);
        v0.e(arg3 * v1);
        v0.b(0);
        v0.a(arg5 * v1, arg6 * v1);
    }

    public void a(float arg2, float arg3) {
        this.f.b(arg2);
        this.f.c(arg3);
        this.invalidateSelf();
    }

    public void a(int arg4) {
        float v2;
        float v1;
        float v0;
        float v4;
        if(arg4 == 0) {
            v4 = 11f;
            v0 = 3f;
            v1 = 12f;
            v2 = 6f;
        }
        else {
            v4 = 7.5f;
            v0 = 2.5f;
            v1 = 10f;
            v2 = 5f;
        }

        this.a(v4, v0, v1, v2);
        this.invalidateSelf();
    }

    public void a(boolean arg2) {
        this.f.a(arg2);
        this.invalidateSelf();
    }

    public void a(int[] arg2) {
        this.f.a(arg2);
        this.f.b(0);
        this.invalidateSelf();
    }

    private void b(float arg5, a arg6) {
        this.a(arg5, arg6);
        float v0 = ((float)(Math.floor(((double)(arg6.j() / 0.8f))) + 1));
        arg6.b(arg6.f() + (arg6.g() - 0.01f - arg6.f()) * arg5);
        arg6.c(arg6.g());
        arg6.d(arg6.j() + (v0 - arg6.j()) * arg5);
    }

    public void b(float arg2) {
        this.f.f(arg2);
        this.invalidateSelf();
    }

    public void c(float arg2) {
        this.f.d(arg2);
        this.invalidateSelf();
    }

    private void d(float arg1) {
        this.g = arg1;
    }

    public void draw(Canvas arg5) {
        Rect v0 = this.getBounds();
        arg5.save();
        arg5.rotate(this.g, v0.exactCenterX(), v0.exactCenterY());
        this.f.a(arg5, v0);
        arg5.restore();
    }

    public int getAlpha() {
        return this.f.d();
    }

    public int getOpacity() {
        return -3;
    }

    public boolean isRunning() {
        return this.i.isRunning();
    }

    public void setAlpha(int arg2) {
        this.f.c(arg2);
        this.invalidateSelf();
    }

    public void setColorFilter(ColorFilter arg2) {
        this.f.a(arg2);
        this.invalidateSelf();
    }

    public void start() {
        long v1;
        Animator v0;
        this.i.cancel();
        this.f.k();
        if(this.f.i() != this.f.e()) {
            this.b = true;
            v0 = this.i;
            v1 = 666;
        }
        else {
            this.f.b(0);
            this.f.l();
            v0 = this.i;
            v1 = 1332;
        }

        v0.setDuration(v1);
        this.i.start();
    }

    public void stop() {
        this.i.cancel();
        this.d(0f);
        this.f.a(false);
        this.f.b(0);
        this.f.l();
        this.invalidateSelf();
    }
}

