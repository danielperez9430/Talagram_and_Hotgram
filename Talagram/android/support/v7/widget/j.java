package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.v4.widget.e;
import android.support.v7.c.a.a;
import android.util.AttributeSet;
import android.widget.CompoundButton;

class j {
    private final CompoundButton a;
    private ColorStateList b;
    private PorterDuff$Mode c;
    private boolean d;
    private boolean e;
    private boolean f;

    j(CompoundButton arg2) {
        super();
        this.b = null;
        this.c = null;
        this.d = false;
        this.e = false;
        this.a = arg2;
    }

    int a(int arg3) {
        if(Build$VERSION.SDK_INT < 17) {
            Drawable v0 = e.a(this.a);
            if(v0 != null) {
                arg3 += v0.getIntrinsicWidth();
            }
        }

        return arg3;
    }

    ColorStateList a() {
        return this.b;
    }

    void a(ColorStateList arg1) {
        this.b = arg1;
        this.d = true;
        this.d();
    }

    void a(PorterDuff$Mode arg1) {
        this.c = arg1;
        this.e = true;
        this.d();
    }

    void a(AttributeSet arg4, int arg5) {
        TypedArray v4 = this.a.getContext().obtainStyledAttributes(arg4, android.support.v7.a.a$j.CompoundButton, arg5, 0);
        try {
            if(v4.hasValue(android.support.v7.a.a$j.CompoundButton_android_button)) {
                arg5 = v4.getResourceId(android.support.v7.a.a$j.CompoundButton_android_button, 0);
                if(arg5 != 0) {
                    this.a.setButtonDrawable(a.b(this.a.getContext(), arg5));
                }
            }

            if(v4.hasValue(android.support.v7.a.a$j.CompoundButton_buttonTint)) {
                e.a(this.a, v4.getColorStateList(android.support.v7.a.a$j.CompoundButton_buttonTint));
            }

            if(v4.hasValue(android.support.v7.a.a$j.CompoundButton_buttonTintMode)) {
                e.a(this.a, ai.a(v4.getInt(android.support.v7.a.a$j.CompoundButton_buttonTintMode, -1), null));
            }
        }
        catch(Throwable v5) {
            v4.recycle();
            throw v5;
        }

        v4.recycle();
    }

    PorterDuff$Mode b() {
        return this.c;
    }

    void c() {
        if(this.f) {
            this.f = false;
            return;
        }

        this.f = true;
        this.d();
    }

    void d() {
        Drawable v0 = e.a(this.a);
        if(v0 != null && ((this.d) || (this.e))) {
            v0 = android.support.v4.graphics.drawable.a.g(v0).mutate();
            if(this.d) {
                android.support.v4.graphics.drawable.a.a(v0, this.b);
            }

            if(this.e) {
                android.support.v4.graphics.drawable.a.a(v0, this.c);
            }

            if(v0.isStateful()) {
                v0.setState(this.a.getDrawableState());
            }

            this.a.setButtonDrawable(v0);
        }
    }
}

