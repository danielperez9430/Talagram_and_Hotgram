package android.support.design.b;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build$VERSION;
import android.support.design.a$k;
import android.support.v4.view.t;

class c {
    private static final boolean a;
    private final a b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private PorterDuff$Mode i;
    private ColorStateList j;
    private ColorStateList k;
    private ColorStateList l;
    private final Paint m;
    private final Rect n;
    private final RectF o;
    private GradientDrawable p;
    private Drawable q;
    private GradientDrawable r;
    private Drawable s;
    private GradientDrawable t;
    private GradientDrawable u;
    private GradientDrawable v;
    private boolean w;

    static {
        boolean v0 = Build$VERSION.SDK_INT >= 21 ? true : false;
        c.a = v0;
    }

    public c(a arg3) {
        super();
        this.m = new Paint(1);
        this.n = new Rect();
        this.o = new RectF();
        this.w = false;
        this.b = arg3;
    }

    private InsetDrawable a(Drawable arg8) {
        return new InsetDrawable(arg8, this.c, this.e, this.d, this.f);
    }

    void a() {
        this.w = true;
        this.b.setSupportBackgroundTintList(this.j);
        this.b.setSupportBackgroundTintMode(this.i);
    }

    void a(int arg2) {
        GradientDrawable v0;
        if((c.a) && this.t != null) {
            v0 = this.t;
            goto label_5;
        }
        else if(!c.a && this.p != null) {
            v0 = this.p;
        label_5:
            v0.setColor(arg2);
        }
    }

    void a(int arg5, int arg6) {
        if(this.v != null) {
            this.v.setBounds(this.c, this.e, arg6 - this.d, arg5 - this.f);
        }
    }

    void a(ColorStateList arg2) {
        if(this.j != arg2) {
            this.j = arg2;
            if(c.a) {
                this.j();
            }
            else if(this.q != null) {
                android.support.v4.graphics.drawable.a.a(this.q, this.j);
            }
        }
    }

    public void a(TypedArray arg6) {
        int v1 = 0;
        this.c = arg6.getDimensionPixelOffset(k.MaterialButton_android_insetLeft, 0);
        this.d = arg6.getDimensionPixelOffset(k.MaterialButton_android_insetRight, 0);
        this.e = arg6.getDimensionPixelOffset(k.MaterialButton_android_insetTop, 0);
        this.f = arg6.getDimensionPixelOffset(k.MaterialButton_android_insetBottom, 0);
        this.g = arg6.getDimensionPixelSize(k.MaterialButton_cornerRadius, 0);
        this.h = arg6.getDimensionPixelSize(k.MaterialButton_strokeWidth, 0);
        this.i = android.support.design.internal.c.a(arg6.getInt(k.MaterialButton_backgroundTintMode, -1), PorterDuff$Mode.SRC_IN);
        this.j = android.support.design.e.a.a(this.b.getContext(), arg6, k.MaterialButton_backgroundTint);
        this.k = android.support.design.e.a.a(this.b.getContext(), arg6, k.MaterialButton_strokeColor);
        this.l = android.support.design.e.a.a(this.b.getContext(), arg6, k.MaterialButton_rippleColor);
        this.m.setStyle(Paint$Style.STROKE);
        this.m.setStrokeWidth(((float)this.h));
        Paint v6 = this.m;
        if(this.k != null) {
            v1 = this.k.getColorForState(this.b.getDrawableState(), 0);
        }

        v6.setColor(v1);
        int v6_1 = t.h(this.b);
        int v0 = this.b.getPaddingTop();
        v1 = t.i(this.b);
        int v2 = this.b.getPaddingBottom();
        a v3 = this.b;
        Drawable v4 = c.a ? this.k() : this.i();
        v3.setInternalBackground(v4);
        t.a(this.b, v6_1 + this.c, v0 + this.e, v1 + this.d, v2 + this.f);
    }

    void a(Canvas arg8) {
        if(arg8 != null && this.k != null && this.h > 0) {
            this.n.set(this.b.getBackground().getBounds());
            this.o.set((((float)this.n.left)) + (((float)this.h)) / 2f + (((float)this.c)), (((float)this.n.top)) + (((float)this.h)) / 2f + (((float)this.e)), (((float)this.n.right)) - (((float)this.h)) / 2f - (((float)this.d)), (((float)this.n.bottom)) - (((float)this.h)) / 2f - (((float)this.f)));
            float v0 = (((float)this.g)) - (((float)this.h)) / 2f;
            arg8.drawRoundRect(this.o, v0, v0, this.m);
        }
    }

    void a(PorterDuff$Mode arg2) {
        if(this.i != arg2) {
            this.i = arg2;
            if(c.a) {
                this.j();
            }
            else if(this.q != null && this.i != null) {
                android.support.v4.graphics.drawable.a.a(this.q, this.i);
            }
        }
    }

    void b(int arg2) {
        if(this.h != arg2) {
            this.h = arg2;
            this.m.setStrokeWidth(((float)arg2));
            this.l();
        }
    }

    void b(ColorStateList arg2) {
        if(this.l != arg2) {
            this.l = arg2;
            if((c.a) && ((this.b.getBackground() instanceof RippleDrawable))) {
                this.b.getBackground().setColor(arg2);
                return;
            }

            if(c.a) {
                return;
            }

            if(this.s == null) {
                return;
            }

            android.support.v4.graphics.drawable.a.a(this.s, arg2);
        }
    }

    boolean b() {
        return this.w;
    }

    ColorStateList c() {
        return this.j;
    }

    void c(int arg4) {
        float v4;
        if(this.g != arg4) {
            this.g = arg4;
            float v1 = 0.00001f;
            if((c.a) && this.t != null && this.u != null && this.v != null) {
                if(Build$VERSION.SDK_INT == 21) {
                    float v2 = (((float)arg4)) + v1;
                    this.n().setCornerRadius(v2);
                    this.m().setCornerRadius(v2);
                }

                v4 = (((float)arg4)) + v1;
                this.t.setCornerRadius(v4);
                this.u.setCornerRadius(v4);
                this.v.setCornerRadius(v4);
                return;
            }

            if(c.a) {
                return;
            }

            if(this.p == null) {
                return;
            }

            if(this.r == null) {
                return;
            }

            v4 = (((float)arg4)) + v1;
            this.p.setCornerRadius(v4);
            this.r.setCornerRadius(v4);
            this.b.invalidate();
        }
    }

    void c(ColorStateList arg4) {
        if(this.k != arg4) {
            this.k = arg4;
            Paint v0 = this.m;
            int v1 = 0;
            if(arg4 != null) {
                v1 = arg4.getColorForState(this.b.getDrawableState(), 0);
            }

            v0.setColor(v1);
            this.l();
        }
    }

    PorterDuff$Mode d() {
        return this.i;
    }

    ColorStateList e() {
        return this.l;
    }

    ColorStateList f() {
        return this.k;
    }

    int g() {
        return this.h;
    }

    int h() {
        return this.g;
    }

    private Drawable i() {
        this.p = new GradientDrawable();
        float v2 = 0.00001f;
        this.p.setCornerRadius((((float)this.g)) + v2);
        int v1 = -1;
        this.p.setColor(v1);
        this.q = android.support.v4.graphics.drawable.a.g(this.p);
        android.support.v4.graphics.drawable.a.a(this.q, this.j);
        if(this.i != null) {
            android.support.v4.graphics.drawable.a.a(this.q, this.i);
        }

        this.r = new GradientDrawable();
        this.r.setCornerRadius((((float)this.g)) + v2);
        this.r.setColor(v1);
        this.s = android.support.v4.graphics.drawable.a.g(this.r);
        android.support.v4.graphics.drawable.a.a(this.s, this.l);
        return this.a(new LayerDrawable(new Drawable[]{this.q, this.s}));
    }

    private void j() {
        if(this.t != null) {
            android.support.v4.graphics.drawable.a.a(this.t, this.j);
            if(this.i != null) {
                android.support.v4.graphics.drawable.a.a(this.t, this.i);
            }
        }
    }

    @TargetApi(value=21) private Drawable k() {
        this.t = new GradientDrawable();
        this.t.setCornerRadius((((float)this.g)) + 0.00001f);
        this.t.setColor(-1);
        this.j();
        this.u = new GradientDrawable();
        this.u.setCornerRadius((((float)this.g)) + 0.00001f);
        this.u.setColor(0);
        this.u.setStroke(this.h, this.k);
        InsetDrawable v0 = this.a(new LayerDrawable(new Drawable[]{this.t, this.u}));
        this.v = new GradientDrawable();
        this.v.setCornerRadius((((float)this.g)) + 0.00001f);
        this.v.setColor(-1);
        return new b(android.support.design.f.a.a(this.l), v0, this.v);
    }

    private void l() {
        if((c.a) && this.u != null) {
            this.b.setInternalBackground(this.k());
        }
        else if(!c.a) {
            this.b.invalidate();
        }
    }

    private GradientDrawable m() {
        if((c.a) && this.b.getBackground() != null) {
            return this.b.getBackground().getDrawable(0).getDrawable().getDrawable(1);
        }

        return null;
    }

    private GradientDrawable n() {
        if((c.a) && this.b.getBackground() != null) {
            return this.b.getBackground().getDrawable(0).getDrawable().getDrawable(0);
        }

        return null;
    }
}

