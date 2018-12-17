package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.a;
import android.support.v4.view.t;
import android.support.v7.a.a$j;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.SeekBar;

class u extends q {
    private final SeekBar a;
    private Drawable b;
    private ColorStateList c;
    private PorterDuff$Mode d;
    private boolean e;
    private boolean f;

    u(SeekBar arg2) {
        super(((ProgressBar)arg2));
        this.c = null;
        this.d = null;
        this.e = false;
        this.f = false;
        this.a = arg2;
    }

    void a(AttributeSet arg4, int arg5) {
        super.a(arg4, arg5);
        bk v4 = bk.a(this.a.getContext(), arg4, j.AppCompatSeekBar, arg5, 0);
        Drawable v5 = v4.b(j.AppCompatSeekBar_android_thumb);
        if(v5 != null) {
            this.a.setThumb(v5);
        }

        this.a(v4.a(j.AppCompatSeekBar_tickMark));
        if(v4.g(j.AppCompatSeekBar_tickMarkTintMode)) {
            this.d = ai.a(v4.a(j.AppCompatSeekBar_tickMarkTintMode, -1), this.d);
            this.f = true;
        }

        if(v4.g(j.AppCompatSeekBar_tickMarkTint)) {
            this.c = v4.e(j.AppCompatSeekBar_tickMarkTint);
            this.e = true;
        }

        v4.a();
        this.d();
    }

    void a(Canvas arg7) {
        if(this.b != null) {
            int v0 = this.a.getMax();
            int v1 = 1;
            if(v0 > 1) {
                int v2 = this.b.getIntrinsicWidth();
                int v3 = this.b.getIntrinsicHeight();
                if(v2 >= 0) {
                    v2 /= 2;
                }
                else {
                    v2 = 1;
                }

                if(v3 >= 0) {
                    v1 = v3 / 2;
                }

                this.b.setBounds(-v2, -v1, v2, v1);
                float v1_1 = (((float)(this.a.getWidth() - this.a.getPaddingLeft() - this.a.getPaddingRight()))) / (((float)v0));
                v2 = arg7.save();
                arg7.translate(((float)this.a.getPaddingLeft()), ((float)(this.a.getHeight() / 2)));
                for(v3 = 0; v3 <= v0; ++v3) {
                    this.b.draw(arg7);
                    arg7.translate(v1_1, 0f);
                }

                arg7.restoreToCount(v2);
            }
        }
    }

    void a(Drawable arg3) {
        if(this.b != null) {
            this.b.setCallback(null);
        }

        this.b = arg3;
        if(arg3 != null) {
            arg3.setCallback(this.a);
            a.b(arg3, t.f(this.a));
            if(arg3.isStateful()) {
                arg3.setState(this.a.getDrawableState());
            }

            this.d();
        }

        this.a.invalidate();
    }

    void b() {
        if(this.b != null) {
            this.b.jumpToCurrentState();
        }
    }

    void c() {
        Drawable v0 = this.b;
        if(v0 != null && (v0.isStateful()) && (v0.setState(this.a.getDrawableState()))) {
            this.a.invalidateDrawable(v0);
        }
    }

    private void d() {
        if(this.b != null && ((this.e) || (this.f))) {
            this.b = a.g(this.b.mutate());
            if(this.e) {
                a.a(this.b, this.c);
            }

            if(this.f) {
                a.a(this.b, this.d);
            }

            if(!this.b.isStateful()) {
                return;
            }

            this.b.setState(this.a.getDrawableState());
        }
    }
}

