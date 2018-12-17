package android.support.design.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v4.content.a;
import android.support.v7.d.a.c;

public class m extends c {
    static final double a;
    final Paint b;
    final Paint c;
    final RectF d;
    float e;
    Path f;
    float g;
    float h;
    float i;
    float j;
    private boolean k;
    private final int l;
    private final int m;
    private final int n;
    private boolean o;
    private float p;
    private boolean q;

    static {
        m.a = Math.cos(Math.toRadians(45));
    }

    public m(Context arg2, Drawable arg3, float arg4, float arg5, float arg6) {
        super(arg3);
        this.k = true;
        this.o = true;
        this.q = false;
        this.l = a.c(arg2, android.support.design.a$c.design_fab_shadow_start_color);
        this.m = a.c(arg2, android.support.design.a$c.design_fab_shadow_mid_color);
        this.n = a.c(arg2, android.support.design.a$c.design_fab_shadow_end_color);
        this.b = new Paint(5);
        this.b.setStyle(Paint$Style.FILL);
        this.e = ((float)Math.round(arg4));
        this.d = new RectF();
        this.c = new Paint(this.b);
        this.c.setAntiAlias(false);
        this.a(arg5, arg6);
    }

    public void a(float arg3, float arg4) {
        if(arg3 >= 0f && arg4 >= 0f) {
            arg3 = ((float)m.c(arg3));
            arg4 = ((float)m.c(arg4));
            if(Float.compare(arg3, arg4) > 0) {
                if(!this.q) {
                    this.q = true;
                }

                arg3 = arg4;
            }

            if(this.j == arg3 && this.h == arg4) {
                return;
            }

            this.j = arg3;
            this.h = arg4;
            this.i = ((float)Math.round(arg3 * 1.5f));
            this.g = arg4;
            this.k = true;
            this.invalidateSelf();
            return;
        }

        throw new IllegalArgumentException("invalid shadow size");
    }

    public void a(boolean arg1) {
        this.o = arg1;
        this.invalidateSelf();
    }

    public void a(float arg2) {
        this.a(arg2, this.h);
    }

    public float a() {
        return this.j;
    }

    public static float a(float arg6, float arg7, boolean arg8) {
        float v0 = 1.5f;
        if(arg8) {
            double v0_1 = ((double)(arg6 * v0));
            double v2 = 1 - m.a;
            double v6 = ((double)arg7);
            Double.isNaN(v6);
            Double.isNaN(v0_1);
            return ((float)(v0_1 + v2 * v6));
        }

        return arg6 * v0;
    }

    private void a(Canvas arg23) {
        // Method was not decompiled
    }

    private void a(Rect arg7) {
        float v0 = this.h * 1.5f;
        this.d.set((((float)arg7.left)) + this.h, (((float)arg7.top)) + v0, (((float)arg7.right)) - this.h, (((float)arg7.bottom)) - v0);
        this.b().setBounds(((int)this.d.left), ((int)this.d.top), ((int)this.d.right), ((int)this.d.bottom));
        this.c();
    }

    public static float b(float arg6, float arg7, boolean arg8) {
        if(arg8) {
            double v0 = ((double)arg6);
            double v2 = 1 - m.a;
            double v6 = ((double)arg7);
            Double.isNaN(v6);
            Double.isNaN(v0);
            arg6 = ((float)(v0 + v2 * v6));
        }

        return arg6;
    }

    public final void b(float arg2) {
        if(this.p != arg2) {
            this.p = arg2;
            this.invalidateSelf();
        }
    }

    private void c() {
        // Method was not decompiled
    }

    private static int c(float arg2) {
        int v2 = Math.round(arg2);
        if(v2 % 2 == 1) {
            --v2;
        }

        return v2;
    }

    public void draw(Canvas arg2) {
        if(this.k) {
            this.a(this.getBounds());
            this.k = false;
        }

        this.a(arg2);
        super.draw(arg2);
    }

    public int getOpacity() {
        return -3;
    }

    public boolean getPadding(Rect arg5) {
        int v0 = ((int)Math.ceil(((double)m.a(this.h, this.e, this.o))));
        int v1 = ((int)Math.ceil(((double)m.b(this.h, this.e, this.o))));
        arg5.set(v1, v0, v1, v0);
        return 1;
    }

    protected void onBoundsChange(Rect arg1) {
        this.k = true;
    }

    public void setAlpha(int arg2) {
        super.setAlpha(arg2);
        this.b.setAlpha(arg2);
        this.c.setAlpha(arg2);
    }
}

