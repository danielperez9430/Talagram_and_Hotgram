package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.v4.view.t;
import android.support.v7.a.a$j;
import android.util.AttributeSet;
import android.view.View;

class g {
    private final View a;
    private final k b;
    private int c;
    private bi d;
    private bi e;
    private bi f;

    g(View arg2) {
        super();
        this.c = -1;
        this.a = arg2;
        this.b = k.a();
    }

    ColorStateList a() {
        ColorStateList v0 = this.e != null ? this.e.a : null;
        return v0;
    }

    void a(int arg3) {
        this.c = arg3;
        ColorStateList v3 = this.b != null ? this.b.b(this.a.getContext(), arg3) : null;
        this.b(v3);
        this.c();
    }

    void a(ColorStateList arg2) {
        if(this.e == null) {
            this.e = new bi();
        }

        this.e.a = arg2;
        this.e.d = true;
        this.c();
    }

    void a(PorterDuff$Mode arg2) {
        if(this.e == null) {
            this.e = new bi();
        }

        this.e.b = arg2;
        this.e.c = true;
        this.c();
    }

    void a(Drawable arg1) {
        this.c = -1;
        this.b(null);
        this.c();
    }

    void a(AttributeSet arg4, int arg5) {
        bk v4 = bk.a(this.a.getContext(), arg4, j.ViewBackgroundHelper, arg5, 0);
        try {
            int v0 = -1;
            if(v4.g(j.ViewBackgroundHelper_android_background)) {
                this.c = v4.g(j.ViewBackgroundHelper_android_background, v0);
                ColorStateList v5_1 = this.b.b(this.a.getContext(), this.c);
                if(v5_1 != null) {
                    this.b(v5_1);
                }
            }

            if(v4.g(j.ViewBackgroundHelper_backgroundTint)) {
                t.a(this.a, v4.e(j.ViewBackgroundHelper_backgroundTint));
            }

            if(v4.g(j.ViewBackgroundHelper_backgroundTintMode)) {
                t.a(this.a, ai.a(v4.a(j.ViewBackgroundHelper_backgroundTintMode, v0), null));
            }
        }
        catch(Throwable v5) {
            v4.a();
            throw v5;
        }

        v4.a();
    }

    private boolean b(Drawable arg4) {
        if(this.f == null) {
            this.f = new bi();
        }

        bi v0 = this.f;
        v0.a();
        ColorStateList v1 = t.w(this.a);
        if(v1 != null) {
            v0.d = true;
            v0.a = v1;
        }

        PorterDuff$Mode v1_1 = t.x(this.a);
        if(v1_1 != null) {
            v0.c = true;
            v0.b = v1_1;
        }

        if(!v0.d) {
            if(v0.c) {
            }
            else {
                return 0;
            }
        }

        k.a(arg4, v0, this.a.getDrawableState());
        return 1;
    }

    void b(ColorStateList arg2) {
        if(arg2 != null) {
            if(this.d == null) {
                this.d = new bi();
            }

            this.d.a = arg2;
            this.d.d = true;
        }
        else {
            this.d = null;
        }

        this.c();
    }

    PorterDuff$Mode b() {
        PorterDuff$Mode v0 = this.e != null ? this.e.b : null;
        return v0;
    }

    void c() {
        bi v1;
        Drawable v0 = this.a.getBackground();
        if(v0 != null) {
            if((this.d()) && (this.b(v0))) {
                return;
            }

            if(this.e != null) {
                v1 = this.e;
            }
            else if(this.d != null) {
                v1 = this.d;
            }
            else {
                return;
            }

            k.a(v0, v1, this.a.getDrawableState());
        }
    }

    private boolean d() {
        int v0 = Build$VERSION.SDK_INT;
        boolean v1 = false;
        int v3 = 21;
        if(v0 > v3) {
            if(this.d != null) {
                v1 = true;
            }

            return v1;
        }

        if(v0 == v3) {
            return 1;
        }

        return 0;
    }
}

