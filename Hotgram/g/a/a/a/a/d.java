package g.a.a.a.a;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.PorterDuff$Mode;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import g.a.a.a.a.a.a;

public class d {
    private Typeface A;
    private Typeface B;
    private int C;
    private int D;
    private ColorStateList E;
    private PorterDuff$Mode F;
    private boolean G;
    private int H;
    private View I;
    private boolean J;
    private int K;
    private int L;
    private View M;
    private b N;
    private c O;
    private e P;
    private g.a.a.a.d a;
    private boolean b;
    private View c;
    private PointF d;
    private CharSequence e;
    private CharSequence f;
    private int g;
    private int h;
    private int i;
    private int j;
    private float k;
    private float l;
    private float m;
    private float n;
    private float o;
    private float p;
    private Interpolator q;
    private Drawable r;
    private boolean s;
    private g.a.a.a.b$c t;
    private g.a.a.a.b$c u;
    private boolean v;
    private float w;
    private boolean x;
    private boolean y;
    private boolean z;

    public d(g.a.a.a.d arg6) {
        super();
        this.g = -1;
        this.h = Color.argb(179, 255, 255, 255);
        this.i = Color.argb(244, 63, 81, 181);
        this.j = -1;
        this.s = true;
        this.x = true;
        this.y = true;
        this.E = null;
        this.F = PorterDuff$Mode.MULTIPLY;
        this.J = true;
        this.K = 8388611;
        this.L = 8388611;
        this.N = new a();
        this.O = new g.a.a.a.a.b.a();
        this.P = new e();
        this.a = arg6;
        float v6 = this.a.c().getDisplayMetrics().density;
        this.k = 44f * v6;
        this.l = 22f * v6;
        this.m = 18f * v6;
        this.n = 400f * v6;
        this.o = 40f * v6;
        this.p = 20f * v6;
        this.w = v6 * 16f;
    }

    public boolean A() {
        return this.y;
    }

    public boolean B() {
        return this.z;
    }

    public int C() {
        return this.K;
    }

    public int D() {
        return this.L;
    }

    public View E() {
        return this.M;
    }

    public boolean F() {
        return this.s;
    }

    public b G() {
        return this.N;
    }

    public c H() {
        return this.O;
    }

    public e I() {
        return this.P;
    }

    public g.a.a.a.b J() {
        if((this.b) && (this.e != null || this.f != null)) {
            g.a.a.a.b v0 = g.a.a.a.b.a(this);
            if(this.q == null) {
                this.q = new AccelerateDecelerateInterpolator();
            }

            if(this.r != null) {
                this.r.mutate();
                this.r.setBounds(0, 0, this.r.getIntrinsicWidth(), this.r.getIntrinsicHeight());
                if(this.G) {
                    if(this.E == null) {
                        this.r.setColorFilter(this.H, this.F);
                        this.r.setAlpha(Color.alpha(this.H));
                    }
                    else if(Build$VERSION.SDK_INT >= 21) {
                        this.r.setTintList(this.E);
                    }
                }
            }

            this.N.a(this.w());
            this.O.b(this.x());
            this.O.a(150);
            this.O.a(this.s());
            if((this.O instanceof g.a.a.a.a.b.a)) {
                this.O.a(this.y());
            }

            return v0;
        }

        return null;
    }

    public g.a.a.a.b K() {
        g.a.a.a.b v0 = this.J();
        if(v0 != null) {
            v0.a();
        }

        return v0;
    }

    public d a(Typeface arg2) {
        return this.a(arg2, 0);
    }

    public d a(Typeface arg1, int arg2) {
        this.A = arg1;
        this.C = arg2;
        return this;
    }

    public d a(View arg1) {
        this.c = arg1;
        this.d = null;
        boolean v1 = this.c != null ? true : false;
        this.b = v1;
        return this;
    }

    public d a(g.a.a.a.b$c arg1) {
        this.t = arg1;
        return this;
    }

    public d a(String arg1) {
        this.e = ((CharSequence)arg1);
        return this;
    }

    public g.a.a.a.d a() {
        return this.a;
    }

    public void a(int arg6) {
        if(arg6 == 0) {
            TypedValue v6 = new TypedValue();
            this.a.d().resolveAttribute(g.a.a.a.c$a.MaterialTapTargetPromptTheme, v6, true);
            arg6 = v6.resourceId;
        }

        TypedArray v6_1 = this.a.a(arg6, g.a.a.a.c$c.PromptView);
        this.g = v6_1.getColor(g.a.a.a.c$c.PromptView_mttp_primaryTextColour, this.g);
        this.h = v6_1.getColor(g.a.a.a.c$c.PromptView_mttp_secondaryTextColour, this.h);
        this.e = v6_1.getString(g.a.a.a.c$c.PromptView_mttp_primaryText);
        this.f = v6_1.getString(g.a.a.a.c$c.PromptView_mttp_secondaryText);
        this.i = v6_1.getColor(g.a.a.a.c$c.PromptView_mttp_backgroundColour, this.i);
        this.j = v6_1.getColor(g.a.a.a.c$c.PromptView_mttp_focalColour, this.j);
        this.k = v6_1.getDimension(g.a.a.a.c$c.PromptView_mttp_focalRadius, this.k);
        this.l = v6_1.getDimension(g.a.a.a.c$c.PromptView_mttp_primaryTextSize, this.l);
        this.m = v6_1.getDimension(g.a.a.a.c$c.PromptView_mttp_secondaryTextSize, this.m);
        this.n = v6_1.getDimension(g.a.a.a.c$c.PromptView_mttp_maxTextWidth, this.n);
        this.o = v6_1.getDimension(g.a.a.a.c$c.PromptView_mttp_textPadding, this.o);
        this.p = v6_1.getDimension(g.a.a.a.c$c.PromptView_mttp_focalToTextPadding, this.p);
        this.w = v6_1.getDimension(g.a.a.a.c$c.PromptView_mttp_textSeparation, this.w);
        this.x = v6_1.getBoolean(g.a.a.a.c$c.PromptView_mttp_autoDismiss, this.x);
        this.y = v6_1.getBoolean(g.a.a.a.c$c.PromptView_mttp_autoFinish, this.y);
        this.z = v6_1.getBoolean(g.a.a.a.c$c.PromptView_mttp_captureTouchEventOutsidePrompt, this.z);
        this.v = v6_1.getBoolean(g.a.a.a.c$c.PromptView_mttp_captureTouchEventOnFocal, this.v);
        this.C = v6_1.getInt(g.a.a.a.c$c.PromptView_mttp_primaryTextStyle, this.C);
        this.D = v6_1.getInt(g.a.a.a.c$c.PromptView_mttp_secondaryTextStyle, this.D);
        this.A = g.a(v6_1.getString(g.a.a.a.c$c.PromptView_mttp_primaryTextFontFamily), v6_1.getInt(g.a.a.a.c$c.PromptView_mttp_primaryTextTypeface, 0), this.C);
        this.B = g.a(v6_1.getString(g.a.a.a.c$c.PromptView_mttp_secondaryTextFontFamily), v6_1.getInt(g.a.a.a.c$c.PromptView_mttp_secondaryTextTypeface, 0), this.D);
        this.H = v6_1.getColor(g.a.a.a.c$c.PromptView_mttp_iconColourFilter, this.i);
        this.E = v6_1.getColorStateList(g.a.a.a.c$c.PromptView_mttp_iconTint);
        this.F = g.a(v6_1.getInt(g.a.a.a.c$c.PromptView_mttp_iconTintMode, -1), this.F);
        this.G = true;
        int v1 = v6_1.getResourceId(g.a.a.a.c$c.PromptView_mttp_target, 0);
        v6_1.recycle();
        if(v1 != 0) {
            this.c = this.a.a(v1);
            if(this.c != null) {
                this.b = true;
            }
        }

        View v6_2 = this.a.a(16908290);
        if(v6_2 != null) {
            this.M = v6_2.getParent();
        }
    }

    public void a(g.a.a.a.b arg2, int arg3) {
        if(this.t != null) {
            this.t.onPromptStateChanged(arg2, arg3);
        }
    }

    public View b() {
        return this.c;
    }

    public d b(int arg1) {
        this.K = arg1;
        this.L = arg1;
        return this;
    }

    public d b(Typeface arg2) {
        return this.b(arg2, 0);
    }

    public d b(Typeface arg1, int arg2) {
        this.B = arg1;
        this.D = arg2;
        return this;
    }

    public d b(String arg1) {
        this.f = ((CharSequence)arg1);
        return this;
    }

    public void b(g.a.a.a.b arg2, int arg3) {
        if(this.u != null) {
            this.u.onPromptStateChanged(arg2, arg3);
        }
    }

    public PointF c() {
        return this.d;
    }

    public View d() {
        return this.I;
    }

    public CharSequence e() {
        return this.e;
    }

    public float f() {
        return this.l;
    }

    public int g() {
        return this.g;
    }

    public Typeface h() {
        return this.A;
    }

    public int i() {
        return this.C;
    }

    public CharSequence j() {
        return this.f;
    }

    public float k() {
        return this.m;
    }

    public int l() {
        return this.h;
    }

    public Typeface m() {
        return this.B;
    }

    public int n() {
        return this.D;
    }

    public float o() {
        return this.o;
    }

    public float p() {
        return this.w;
    }

    public float q() {
        return this.p;
    }

    public Interpolator r() {
        return this.q;
    }

    public boolean s() {
        return this.J;
    }

    public Drawable t() {
        return this.r;
    }

    public boolean u() {
        return this.v;
    }

    public float v() {
        return this.n;
    }

    public int w() {
        return this.i;
    }

    public int x() {
        return this.j;
    }

    public float y() {
        return this.k;
    }

    public boolean z() {
        return this.x;
    }
}

