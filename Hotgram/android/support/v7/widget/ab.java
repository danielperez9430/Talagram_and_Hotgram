package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

class ab implements ad {
    final RectF a;

    ab() {
        super();
        this.a = new RectF();
    }

    private az a(Context arg8, ColorStateList arg9, float arg10, float arg11, float arg12) {
        return new az(arg8.getResources(), arg9, arg10, arg11, arg12);
    }

    public float a(ac arg1) {
        return this.j(arg1).c();
    }

    public void a() {
        az.a = new a() {
            public void a(Canvas arg16, RectF arg17, float arg18, Paint arg19) {
                android.support.v7.widget.ab$1 v0 = this;
                Canvas v7 = arg16;
                RectF v8 = arg17;
                float v1 = 2f * arg18;
                float v10 = arg17.width() - v1 - 1f;
                float v11 = arg17.height() - v1 - 1f;
                if(arg18 >= 1f) {
                    float v12 = arg18 + 0.5f;
                    float v2 = -v12;
                    v0.a.a.set(v2, v2, v12, v12);
                    int v13 = arg16.save();
                    v7.translate(v8.left + v12, v8.top + v12);
                    arg16.drawArc(v0.a.a, 180f, 90f, true, arg19);
                    v7.translate(v10, 0f);
                    v7.rotate(90f);
                    arg16.drawArc(v0.a.a, 180f, 90f, true, arg19);
                    v7.translate(v11, 0f);
                    v7.rotate(90f);
                    arg16.drawArc(v0.a.a, 180f, 90f, true, arg19);
                    v7.translate(v10, 0f);
                    v7.rotate(90f);
                    arg16.drawArc(v0.a.a, 180f, 90f, true, arg19);
                    v7.restoreToCount(v13);
                    arg16.drawRect(v8.left + v12 - 1f, v8.top, v8.right - v12 + 1f, v8.top + v12, arg19);
                    arg16.drawRect(v8.left + v12 - 1f, v8.bottom - v12, v8.right - v12 + 1f, v8.bottom, arg19);
                }

                arg16.drawRect(v8.left, v8.top + arg18, v8.right, v8.bottom - arg18, arg19);
            }
        };
    }

    public void a(ac arg2, float arg3) {
        this.j(arg2).a(arg3);
        this.f(arg2);
    }

    public void a(ac arg7, Context arg8, ColorStateList arg9, float arg10, float arg11, float arg12) {
        az v8 = this.a(arg8, arg9, arg10, arg11, arg12);
        v8.a(arg7.b());
        arg7.a(((Drawable)v8));
        this.f(arg7);
    }

    public void a(ac arg1, ColorStateList arg2) {
        this.j(arg1).a(arg2);
    }

    public float b(ac arg1) {
        return this.j(arg1).d();
    }

    public void b(ac arg2, float arg3) {
        this.j(arg2).c(arg3);
        this.f(arg2);
    }

    public float c(ac arg1) {
        return this.j(arg1).e();
    }

    public void c(ac arg1, float arg2) {
        this.j(arg1).b(arg2);
    }

    public float d(ac arg1) {
        return this.j(arg1).a();
    }

    public float e(ac arg1) {
        return this.j(arg1).b();
    }

    public void f(ac arg5) {
        Rect v0 = new Rect();
        this.j(arg5).a(v0);
        arg5.a(((int)Math.ceil(((double)this.b(arg5)))), ((int)Math.ceil(((double)this.c(arg5)))));
        arg5.a(v0.left, v0.top, v0.right, v0.bottom);
    }

    public void g(ac arg1) {
    }

    public void h(ac arg3) {
        this.j(arg3).a(arg3.b());
        this.f(arg3);
    }

    public ColorStateList i(ac arg1) {
        return this.j(arg1).f();
    }

    private az j(ac arg1) {
        return arg1.c();
    }
}

