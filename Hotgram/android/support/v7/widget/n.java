package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build$VERSION;
import android.support.v4.widget.l;
import android.support.v7.a.a$j;
import android.support.v7.c.a.a;
import android.util.AttributeSet;
import android.widget.ImageView;

public class n {
    private final ImageView a;
    private bi b;
    private bi c;
    private bi d;

    public n(ImageView arg1) {
        super();
        this.a = arg1;
    }

    private boolean a(Drawable arg4) {
        if(this.d == null) {
            this.d = new bi();
        }

        bi v0 = this.d;
        v0.a();
        ColorStateList v1 = l.a(this.a);
        if(v1 != null) {
            v0.d = true;
            v0.a = v1;
        }

        PorterDuff$Mode v1_1 = l.b(this.a);
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

    public void a(int arg2) {
        if(arg2 != 0) {
            Drawable v2 = a.b(this.a.getContext(), arg2);
            if(v2 != null) {
                ai.a(v2);
            }

            this.a.setImageDrawable(v2);
        }
        else {
            this.a.setImageDrawable(null);
        }

        this.d();
    }

    void a(ColorStateList arg2) {
        if(this.c == null) {
            this.c = new bi();
        }

        this.c.a = arg2;
        this.c.d = true;
        this.d();
    }

    void a(PorterDuff$Mode arg2) {
        if(this.c == null) {
            this.c = new bi();
        }

        this.c.b = arg2;
        this.c.c = true;
        this.d();
    }

    public void a(AttributeSet arg4, int arg5) {
        bk v4 = bk.a(this.a.getContext(), arg4, j.AppCompatImageView, arg5, 0);
        try {
            Drawable v5_1 = this.a.getDrawable();
            int v0 = -1;
            if(v5_1 == null) {
                int v1 = v4.g(j.AppCompatImageView_srcCompat, v0);
                if(v1 != v0) {
                    v5_1 = a.b(this.a.getContext(), v1);
                    if(v5_1 != null) {
                        this.a.setImageDrawable(v5_1);
                    }
                }
            }

            if(v5_1 != null) {
                ai.a(v5_1);
            }

            if(v4.g(j.AppCompatImageView_tint)) {
                l.a(this.a, v4.e(j.AppCompatImageView_tint));
            }

            if(v4.g(j.AppCompatImageView_tintMode)) {
                l.a(this.a, ai.a(v4.a(j.AppCompatImageView_tintMode, v0), null));
            }
        }
        catch(Throwable v5) {
            v4.a();
            throw v5;
        }

        v4.a();
    }

    boolean a() {
        Drawable v0 = this.a.getBackground();
        if(Build$VERSION.SDK_INT >= 21 && ((v0 instanceof RippleDrawable))) {
            return 0;
        }

        return 1;
    }

    ColorStateList b() {
        ColorStateList v0 = this.c != null ? this.c.a : null;
        return v0;
    }

    PorterDuff$Mode c() {
        PorterDuff$Mode v0 = this.c != null ? this.c.b : null;
        return v0;
    }

    void d() {
        bi v1;
        Drawable v0 = this.a.getDrawable();
        if(v0 != null) {
            ai.a(v0);
        }

        if(v0 != null) {
            if((this.e()) && (this.a(v0))) {
                return;
            }

            if(this.c != null) {
                v1 = this.c;
            }
            else if(this.b != null) {
                v1 = this.b;
            }
            else {
                return;
            }

            k.a(v0, v1, this.a.getDrawableState());
        }
    }

    private boolean e() {
        int v0 = Build$VERSION.SDK_INT;
        boolean v1 = false;
        int v3 = 21;
        if(v0 > v3) {
            if(this.b != null) {
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

