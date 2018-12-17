package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.Path$FillType;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader$TileMode;
import android.graphics.drawable.Drawable;
import android.support.v7.b.a$b;
import android.support.v7.b.a$c;

class az extends Drawable {
    interface a {
        void a(Canvas arg1, RectF arg2, float arg3, Paint arg4);
    }

    static a a;
    private static final double b;
    private final int c;
    private Paint d;
    private Paint e;
    private Paint f;
    private final RectF g;
    private float h;
    private Path i;
    private float j;
    private float k;
    private float l;
    private ColorStateList m;
    private boolean n;
    private final int o;
    private final int p;
    private boolean q;
    private boolean r;

    static {
        az.b = Math.cos(Math.toRadians(45));
    }

    az(Resources arg3, ColorStateList arg4, float arg5, float arg6, float arg7) {
        super();
        this.n = true;
        this.q = true;
        this.r = false;
        this.o = arg3.getColor(b.cardview_shadow_start_color);
        this.p = arg3.getColor(b.cardview_shadow_end_color);
        this.c = arg3.getDimensionPixelSize(c.cardview_compat_inset_shadow);
        this.d = new Paint(5);
        this.b(arg4);
        this.e = new Paint(5);
        this.e.setStyle(Paint$Style.FILL);
        this.h = ((float)(((int)(arg5 + 0.5f))));
        this.g = new RectF();
        this.f = new Paint(this.e);
        this.f.setAntiAlias(false);
        this.a(arg6, arg7);
    }

    static float a(float arg6, float arg7, boolean arg8) {
        float v0 = 1.5f;
        if(arg8) {
            double v0_1 = ((double)(arg6 * v0));
            double v2 = 1 - az.b;
            double v6 = ((double)arg7);
            Double.isNaN(v6);
            Double.isNaN(v0_1);
            return ((float)(v0_1 + v2 * v6));
        }

        return arg6 * v0;
    }

    private void a(float arg3, float arg4) {
        StringBuilder v0;
        if(arg3 >= 0f) {
            if(arg4 >= 0f) {
                arg3 = ((float)this.d(arg3));
                arg4 = ((float)this.d(arg4));
                if(Float.compare(arg3, arg4) > 0) {
                    if(!this.r) {
                        this.r = true;
                    }

                    arg3 = arg4;
                }

                if(this.l == arg3 && this.j == arg4) {
                    return;
                }

                this.l = arg3;
                this.j = arg4;
                this.k = ((float)(((int)(arg3 * 1.5f + (((float)this.c)) + 0.5f))));
                this.n = true;
                this.invalidateSelf();
                return;
            }

            v0 = new StringBuilder();
            v0.append("Invalid max shadow size ");
            v0.append(arg4);
            v0.append(". Must be >= 0");
            throw new IllegalArgumentException(v0.toString());
        }

        v0 = new StringBuilder();
        v0.append("Invalid shadow size ");
        v0.append(arg3);
        v0.append(". Must be >= 0");
        throw new IllegalArgumentException(v0.toString());
    }

    private void a(Canvas arg13) {
        // Method was not decompiled
    }

    float a() {
        return this.h;
    }

    void a(float arg4) {
        if(arg4 >= 0f) {
            arg4 = ((float)(((int)(arg4 + 0.5f))));
            if(this.h == arg4) {
                return;
            }

            this.h = arg4;
            this.n = true;
            this.invalidateSelf();
            return;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Invalid radius ");
        v1.append(arg4);
        v1.append(". Must be >= 0");
        throw new IllegalArgumentException(v1.toString());
    }

    void a(ColorStateList arg1) {
        this.b(arg1);
        this.invalidateSelf();
    }

    void a(Rect arg1) {
        this.getPadding(arg1);
    }

    void a(boolean arg1) {
        this.q = arg1;
        this.invalidateSelf();
    }

    static float b(float arg6, float arg7, boolean arg8) {
        if(arg8) {
            double v0 = ((double)arg6);
            double v2 = 1 - az.b;
            double v6 = ((double)arg7);
            Double.isNaN(v6);
            Double.isNaN(v0);
            arg6 = ((float)(v0 + v2 * v6));
        }

        return arg6;
    }

    private void b(ColorStateList arg4) {
        if(arg4 == null) {
            arg4 = ColorStateList.valueOf(0);
        }

        this.m = arg4;
        this.d.setColor(this.m.getColorForState(this.getState(), this.m.getDefaultColor()));
    }

    private void b(Rect arg7) {
        float v0 = this.j * 1.5f;
        this.g.set((((float)arg7.left)) + this.j, (((float)arg7.top)) + v0, (((float)arg7.right)) - this.j, (((float)arg7.bottom)) - v0);
        this.g();
    }

    float b() {
        return this.l;
    }

    void b(float arg2) {
        this.a(arg2, this.j);
    }

    float c() {
        return this.j;
    }

    void c(float arg2) {
        this.a(this.l, arg2);
    }

    private int d(float arg3) {
        int v3 = ((int)(arg3 + 0.5f));
        if(v3 % 2 == 1) {
            --v3;
        }

        return v3;
    }

    float d() {
        return Math.max(this.j, this.h + (((float)this.c)) + this.j / 2f) * 2f + (this.j + (((float)this.c))) * 2f;
    }

    public void draw(Canvas arg5) {
        if(this.n) {
            this.b(this.getBounds());
            this.n = false;
        }

        arg5.translate(0f, this.l / 2f);
        this.a(arg5);
        arg5.translate(0f, -this.l / 2f);
        az.a.a(arg5, this.g, this.h, this.d);
    }

    float e() {
        return Math.max(this.j, this.h + (((float)this.c)) + this.j * 1.5f / 2f) * 2f + (this.j * 1.5f + (((float)this.c))) * 2f;
    }

    ColorStateList f() {
        return this.m;
    }

    private void g() {
        az v0 = this;
        RectF v1 = new RectF(-v0.h, -v0.h, v0.h, v0.h);
        RectF v2 = new RectF(v1);
        v2.inset(-v0.k, -v0.k);
        if(v0.i == null) {
            v0.i = new Path();
        }
        else {
            v0.i.reset();
        }

        v0.i.setFillType(Path$FillType.EVEN_ODD);
        v0.i.moveTo(-v0.h, 0f);
        v0.i.rLineTo(-v0.k, 0f);
        v0.i.arcTo(v2, 180f, 90f, false);
        v0.i.arcTo(v1, 270f, -90f, false);
        v0.i.close();
        v0.e.setShader(new RadialGradient(0f, 0f, v0.h + v0.k, new int[]{v0.o, v0.o, v0.p}, new float[]{0f, v0.h / (v0.h + v0.k), 1f}, Shader$TileMode.CLAMP));
        v0.f.setShader(new LinearGradient(0f, -v0.h + v0.k, 0f, -v0.h - v0.k, new int[]{v0.o, v0.o, v0.p}, new float[]{0f, 0.5f, 1f}, Shader$TileMode.CLAMP));
        v0.f.setAntiAlias(false);
    }

    public int getOpacity() {
        return -3;
    }

    public boolean getPadding(Rect arg5) {
        int v0 = ((int)Math.ceil(((double)az.a(this.j, this.h, this.q))));
        int v1 = ((int)Math.ceil(((double)az.b(this.j, this.h, this.q))));
        arg5.set(v1, v0, v1, v0);
        return 1;
    }

    public boolean isStateful() {
        boolean v0 = this.m != null && (this.m.isStateful()) || (super.isStateful()) ? true : false;
        return v0;
    }

    protected void onBoundsChange(Rect arg1) {
        super.onBoundsChange(arg1);
        this.n = true;
    }

    protected boolean onStateChange(int[] arg3) {
        int v3 = this.m.getColorForState(arg3, this.m.getDefaultColor());
        if(this.d.getColor() == v3) {
            return 0;
        }

        this.d.setColor(v3);
        this.n = true;
        this.invalidateSelf();
        return 1;
    }

    public void setAlpha(int arg2) {
        this.d.setAlpha(arg2);
        this.e.setAlpha(arg2);
        this.f.setAlpha(arg2);
    }

    public void setColorFilter(ColorFilter arg2) {
        this.d.setColorFilter(arg2);
    }
}

