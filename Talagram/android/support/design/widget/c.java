package android.support.design.widget;

import android.animation.TimeInterpolator;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build$VERSION;
import android.support.design.a.a;
import android.support.v4.e.d;
import android.support.v4.e.e;
import android.support.v4.view.t;
import android.support.v7.a.a$j;
import android.support.v7.widget.bk;
import android.text.TextPaint;
import android.text.TextUtils$TruncateAt;
import android.text.TextUtils;
import android.view.View;

public final class c {
    private boolean A;
    private Bitmap B;
    private Paint C;
    private float D;
    private float E;
    private float F;
    private float G;
    private int[] H;
    private boolean I;
    private final TextPaint J;
    private final TextPaint K;
    private TimeInterpolator L;
    private TimeInterpolator M;
    private float N;
    private float O;
    private float P;
    private int Q;
    private float R;
    private float S;
    private float T;
    private int U;
    private static final boolean a;
    private static final Paint b;
    private final View c;
    private boolean d;
    private float e;
    private final Rect f;
    private final Rect g;
    private final RectF h;
    private int i;
    private int j;
    private float k;
    private float l;
    private ColorStateList m;
    private ColorStateList n;
    private float o;
    private float p;
    private float q;
    private float r;
    private float s;
    private float t;
    private Typeface u;
    private Typeface v;
    private Typeface w;
    private CharSequence x;
    private CharSequence y;
    private boolean z;

    static {
        boolean v0 = Build$VERSION.SDK_INT < 18 ? true : false;
        c.a = v0;
        c.b = null;
        if(c.b != null) {
            c.b.setAntiAlias(true);
            c.b.setColor(-65281);
        }
    }

    public c(View arg2) {
        super();
        this.i = 16;
        this.j = 16;
        this.k = 15f;
        this.l = 15f;
        this.c = arg2;
        this.J = new TextPaint(129);
        this.K = new TextPaint(this.J);
        this.g = new Rect();
        this.f = new Rect();
        this.h = new RectF();
    }

    public void a(TimeInterpolator arg1) {
        this.M = arg1;
        this.g();
    }

    public void a(ColorStateList arg2) {
        if(this.n != arg2) {
            this.n = arg2;
            this.g();
        }
    }

    public void a(Typeface arg1) {
        this.v = arg1;
        this.u = arg1;
        this.g();
    }

    public void a(float arg2) {
        if(this.k != arg2) {
            this.k = arg2;
            this.g();
        }
    }

    public void a(int arg2) {
        if(this.i != arg2) {
            this.i = arg2;
            this.g();
        }
    }

    public void a(CharSequence arg2) {
        if(arg2 == null || !arg2.equals(this.x)) {
            this.x = arg2;
            this.y = null;
            this.m();
            this.g();
        }
    }

    public void a(RectF arg4) {
        boolean v0 = this.b(this.x);
        float v1 = !v0 ? ((float)this.g.left) : (((float)this.g.right)) - this.a();
        arg4.left = v1;
        arg4.top = ((float)this.g.top);
        float v0_1 = !v0 ? arg4.left + this.a() : ((float)this.g.right);
        arg4.right = v0_1;
        arg4.bottom = (((float)this.g.top)) + this.b();
    }

    public void a(Canvas arg10) {
        float v3;
        int v0 = arg10.save();
        if(this.y != null && (this.d)) {
            float v6 = this.s;
            float v1 = this.t;
            int v2 = !this.A || this.B == null ? 0 : 1;
            if(v2 != 0) {
                v3 = this.D * this.F;
            }
            else {
                v3 = this.J.ascent() * this.F;
                this.J.descent();
            }

            if(v2 != 0) {
                v1 += v3;
            }

            float v7 = v1;
            if(this.F != 1f) {
                arg10.scale(this.F, this.F, v6, v7);
            }

            if(v2 != 0) {
                arg10.drawBitmap(this.B, v6, v7, this.C);
                goto label_49;
            }

            arg10.drawText(this.y, 0, this.y.length(), v6, v7, this.J);
        }

    label_49:
        arg10.restoreToCount(v0);
    }

    public final boolean a(int[] arg1) {
        this.H = arg1;
        if(this.d()) {
            this.g();
            return 1;
        }

        return 0;
    }

    public void a(int arg2, int arg3, int arg4, int arg5) {
        if(!c.a(this.f, arg2, arg3, arg4, arg5)) {
            this.f.set(arg2, arg3, arg4, arg5);
            this.I = true;
            this.c();
        }
    }

    private static float a(float arg0, float arg1, float arg2, TimeInterpolator arg3) {
        if(arg3 != null) {
            arg2 = arg3.getInterpolation(arg2);
        }

        return a.a(arg0, arg1, arg2);
    }

    private static int a(int arg5, int arg6, float arg7) {
        float v0 = 1f - arg7;
        return Color.argb(((int)((((float)Color.alpha(arg5))) * v0 + (((float)Color.alpha(arg6))) * arg7)), ((int)((((float)Color.red(arg5))) * v0 + (((float)Color.red(arg6))) * arg7)), ((int)((((float)Color.green(arg5))) * v0 + (((float)Color.green(arg6))) * arg7)), ((int)((((float)Color.blue(arg5))) * v0 + (((float)Color.blue(arg6))) * arg7)));
    }

    private void a(TextPaint arg2) {
        arg2.setTextSize(this.l);
        arg2.setTypeface(this.u);
    }

    private static boolean a(float arg0, float arg1) {
        boolean v0 = Math.abs(arg0 - arg1) < 0.001f ? true : false;
        return v0;
    }

    private static boolean a(Rect arg1, int arg2, int arg3, int arg4, int arg5) {
        boolean v1 = arg1.left != arg2 || arg1.top != arg3 || arg1.right != arg4 || arg1.bottom != arg5 ? false : true;
        return v1;
    }

    public float a() {
        if(this.x == null) {
            return 0;
        }

        this.a(this.K);
        return this.K.measureText(this.x, 0, this.x.length());
    }

    public void b(TimeInterpolator arg1) {
        this.L = arg1;
        this.g();
    }

    public void b(int arg2) {
        if(this.j != arg2) {
            this.j = arg2;
            this.g();
        }
    }

    public void b(ColorStateList arg2) {
        if(this.m != arg2) {
            this.m = arg2;
            this.g();
        }
    }

    public void b(float arg3) {
        arg3 = android.support.v4.b.a.a(arg3, 0f, 1f);
        if(arg3 != this.e) {
            this.e = arg3;
            this.i();
        }
    }

    public float b() {
        this.a(this.K);
        return -this.K.ascent();
    }

    public void b(int arg2, int arg3, int arg4, int arg5) {
        if(!c.a(this.g, arg2, arg3, arg4, arg5)) {
            this.g.set(arg2, arg3, arg4, arg5);
            this.I = true;
            this.c();
        }
    }

    private boolean b(CharSequence arg4) {
        int v2 = 1;
        if(t.f(this.c) == 1) {
        }
        else {
            v2 = 0;
        }

        d v0 = v2 != 0 ? e.d : e.c;
        return v0.a(arg4, 0, arg4.length());
    }

    public void c(int arg4) {
        bk v0 = bk.a(this.c.getContext(), arg4, j.TextAppearance);
        if(v0.g(j.TextAppearance_android_textColor)) {
            this.n = v0.e(j.TextAppearance_android_textColor);
        }

        if(v0.g(j.TextAppearance_android_textSize)) {
            this.l = ((float)v0.e(j.TextAppearance_android_textSize, ((int)this.l)));
        }

        this.Q = v0.a(j.TextAppearance_android_shadowColor, 0);
        this.O = v0.a(j.TextAppearance_android_shadowDx, 0f);
        this.P = v0.a(j.TextAppearance_android_shadowDy, 0f);
        this.N = v0.a(j.TextAppearance_android_shadowRadius, 0f);
        v0.a();
        if(Build$VERSION.SDK_INT >= 16) {
            this.u = this.d(arg4);
        }

        this.g();
    }

    private void c(float arg7) {
        int v1;
        TextPaint v0;
        this.d(arg7);
        this.s = c.a(this.q, this.r, arg7, this.L);
        this.t = c.a(this.o, this.p, arg7, this.L);
        this.e(c.a(this.k, this.l, arg7, this.M));
        if(this.n != this.m) {
            v0 = this.J;
            v1 = c.a(this.j(), this.f(), arg7);
        }
        else {
            v0 = this.J;
            v1 = this.f();
        }

        v0.setColor(v1);
        this.J.setShadowLayer(c.a(this.R, this.N, arg7, null), c.a(this.S, this.O, arg7, null), c.a(this.T, this.P, arg7, null), c.a(this.U, this.Q, arg7));
        t.d(this.c);
    }

    void c() {
        boolean v0 = this.g.width() <= 0 || this.g.height() <= 0 || this.f.width() <= 0 || this.f.height() <= 0 ? false : true;
        this.d = v0;
    }

    private void d(float arg5) {
        this.h.left = c.a(((float)this.f.left), ((float)this.g.left), arg5, this.L);
        this.h.top = c.a(this.o, this.p, arg5, this.L);
        this.h.right = c.a(((float)this.f.right), ((float)this.g.right), arg5, this.L);
        this.h.bottom = c.a(((float)this.f.bottom), ((float)this.g.bottom), arg5, this.L);
    }

    private Typeface d(int arg5) {
        Typeface v0_2;
        TypedArray v5 = this.c.getContext().obtainStyledAttributes(arg5, new int[]{16843692});
        try {
            String v0_1 = v5.getString(0);
            if(v0_1 == null) {
                goto label_13;
            }

            v0_2 = Typeface.create(v0_1, 0);
        }
        catch(Throwable v0) {
            v5.recycle();
            throw v0;
        }

        v5.recycle();
        return v0_2;
    label_13:
        v5.recycle();
        return null;
    }

    public final boolean d() {
        boolean v0;
        if(this.n == null || !this.n.isStateful()) {
            if(this.m != null && (this.m.isStateful())) {
            label_10:
                v0 = true;
                return v0;
            }

            v0 = false;
        }
        else {
            goto label_10;
        }

        return v0;
    }

    public float e() {
        return this.e;
    }

    private void e(float arg2) {
        this.f(arg2);
        boolean v2 = !c.a || this.F == 1f ? false : true;
        this.A = v2;
        if(this.A) {
            this.l();
        }

        t.d(this.c);
    }

    public int f() {
        if(this.H != null) {
            return this.n.getColorForState(this.H, 0);
        }

        return this.n.getDefaultColor();
    }

    private void f(float arg9) {
        int v6;
        float v2;
        int v1_1;
        if(this.x == null) {
            return;
        }

        float v0 = ((float)this.g.width());
        float v1 = ((float)this.f.width());
        float v3 = 1f;
        boolean v4 = true;
        if(c.a(arg9, this.l)) {
            arg9 = this.l;
            this.F = v3;
            if(this.w != this.u) {
                this.w = this.u;
                v1_1 = 1;
            }
            else {
                v1_1 = 0;
            }

            v2 = arg9;
            v6 = v1_1;
        }
        else {
            v2 = this.k;
            if(this.w != this.v) {
                this.w = this.v;
                v6 = 1;
            }
            else {
                v6 = 0;
            }

            this.F = c.a(arg9, this.k) ? v3 : arg9 / this.k;
            arg9 = this.l / this.k;
            if(v1 * arg9 > v0) {
                v0 = Math.min(v0 / arg9, v1);
                goto label_54;
            }

            v0 = v1;
        }

    label_54:
        if(v0 > 0f) {
            v6 = this.G != v2 || (this.I) || v6 != 0 ? 1 : 0;
            this.G = v2;
            this.I = false;
        }

        if(this.y == null || v6 != 0) {
            this.J.setTextSize(this.G);
            this.J.setTypeface(this.w);
            TextPaint v9 = this.J;
            if(this.F != v3) {
            }
            else {
                v4 = false;
            }

            v9.setLinearText(v4);
            CharSequence v9_1 = TextUtils.ellipsize(this.x, this.J, v0, TextUtils$TruncateAt.END);
            if(TextUtils.equals(v9_1, this.y)) {
                return;
            }

            this.y = v9_1;
            this.z = this.b(this.y);
        }
    }

    public void g() {
        if(this.c.getHeight() > 0 && this.c.getWidth() > 0) {
            this.k();
            this.i();
        }
    }

    public ColorStateList h() {
        return this.n;
    }

    private void i() {
        this.c(this.e);
    }

    private int j() {
        if(this.H != null) {
            return this.m.getColorForState(this.H, 0);
        }

        return this.m.getDefaultColor();
    }

    private void k() {
        float v3_1;
        float v4_1;
        float v5_1;
        float v0 = this.G;
        this.f(this.l);
        float v2 = 0f;
        float v1 = this.y != null ? this.J.measureText(this.y, 0, this.y.length()) : 0f;
        int v4 = android.support.v4.view.d.a(this.j, this.z);
        int v5 = v4 & 112;
        int v6 = 80;
        int v7 = 48;
        float v8 = 2f;
        if(v5 == v7) {
            v5_1 = (((float)this.g.top)) - this.J.ascent();
        label_48:
            this.p = v5_1;
        }
        else if(v5 != v6) {
            this.p = (((float)this.g.centerY())) + ((this.J.descent() - this.J.ascent()) / v8 - this.J.descent());
        }
        else {
            v5_1 = ((float)this.g.bottom);
            goto label_48;
        }

        v5 = 8388615;
        v4 &= v5;
        int v9 = 5;
        if(v4 == 1) {
            v4_1 = ((float)this.g.centerX());
            v1 /= v8;
        label_68:
            this.r = v4_1 - v1;
        }
        else if(v4 != v9) {
            this.r = ((float)this.g.left);
        }
        else {
            v4_1 = ((float)this.g.right);
            goto label_68;
        }

        this.f(this.k);
        if(this.y != null) {
            v2 = this.J.measureText(this.y, 0, this.y.length());
        }

        int v1_1 = android.support.v4.view.d.a(this.i, this.z);
        int v3 = v1_1 & 112;
        if(v3 == v7) {
            v3_1 = (((float)this.f.top)) - this.J.ascent();
        label_110:
            this.o = v3_1;
        }
        else if(v3 != v6) {
            this.o = (((float)this.f.centerY())) + ((this.J.descent() - this.J.ascent()) / v8 - this.J.descent());
        }
        else {
            v3_1 = ((float)this.f.bottom);
            goto label_110;
        }

        v1_1 &= v5;
        if(v1_1 == 1) {
            v1 = ((float)this.f.centerX());
            v2 /= v8;
        label_127:
            v1 -= v2;
        }
        else if(v1_1 != v9) {
            v1 = ((float)this.f.left);
        }
        else {
            v1 = ((float)this.f.right);
            goto label_127;
        }

        this.q = v1;
        this.m();
        this.e(v0);
    }

    private void l() {
        if(this.B == null && !this.f.isEmpty()) {
            if(TextUtils.isEmpty(this.y)) {
            }
            else {
                this.c(0f);
                this.D = this.J.ascent();
                this.E = this.J.descent();
                int v0 = Math.round(this.J.measureText(this.y, 0, this.y.length()));
                int v1 = Math.round(this.E - this.D);
                if(v0 > 0) {
                    if(v1 <= 0) {
                    }
                    else {
                        this.B = Bitmap.createBitmap(v0, v1, Bitmap$Config.ARGB_8888);
                        new Canvas(this.B).drawText(this.y, 0, this.y.length(), 0f, (((float)v1)) - this.J.descent(), this.J);
                        if(this.C == null) {
                            this.C = new Paint(3);
                        }
                    }
                }
            }
        }
    }

    private void m() {
        if(this.B != null) {
            this.B.recycle();
            this.B = null;
        }
    }
}

