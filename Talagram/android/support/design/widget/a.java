package android.support.design.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader$TileMode;
import android.graphics.Shader;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.drawable.Drawable;

public class a extends Drawable {
    class android.support.design.widget.a$1 {
    }

    class android.support.design.widget.a$a extends Drawable$ConstantState {
        android.support.design.widget.a$a(a arg1, android.support.design.widget.a$1 arg2) {
            this(arg1);
        }

        private android.support.design.widget.a$a(a arg1) {
            this.a = arg1;
            super();
        }

        public int getChangingConfigurations() {
            return 0;
        }

        public Drawable newDrawable() {
            return this.a;
        }
    }

    final Paint a;
    final Rect b;
    final RectF c;
    final android.support.design.widget.a$a d;
    float e;
    private int f;
    private int g;
    private int h;
    private int i;
    private ColorStateList j;
    private int k;
    private boolean l;
    private float m;

    public a() {
        super();
        this.b = new Rect();
        this.c = new RectF();
        this.d = new android.support.design.widget.a$a(this, null);
        this.l = true;
        this.a = new Paint(1);
        this.a.setStyle(Paint$Style.STROKE);
    }

    private Shader a() {
        Rect v0 = this.b;
        this.copyBounds(v0);
        float v1 = this.e / (((float)v0.height()));
        return new LinearGradient(0f, ((float)v0.top), 0f, ((float)v0.bottom), new int[]{android.support.v4.graphics.a.a(this.f, this.k), android.support.v4.graphics.a.a(this.g, this.k), android.support.v4.graphics.a.a(android.support.v4.graphics.a.c(this.g, 0), this.k), android.support.v4.graphics.a.a(android.support.v4.graphics.a.c(this.i, 0), this.k), android.support.v4.graphics.a.a(this.i, this.k), android.support.v4.graphics.a.a(this.h, this.k)}, new float[]{0f, v1, 0.5f, 0.5f, 1f - v1, 1f}, Shader$TileMode.CLAMP);
    }

    public void a(float arg3) {
        if(this.e != arg3) {
            this.e = arg3;
            this.a.setStrokeWidth(arg3 * 1.3333f);
            this.l = true;
            this.invalidateSelf();
        }
    }

    public void a(int arg1, int arg2, int arg3, int arg4) {
        this.f = arg1;
        this.g = arg2;
        this.h = arg3;
        this.i = arg4;
    }

    public void a(ColorStateList arg3) {
        if(arg3 != null) {
            this.k = arg3.getColorForState(this.getState(), this.k);
        }

        this.j = arg3;
        this.l = true;
        this.invalidateSelf();
    }

    public final void b(float arg2) {
        if(arg2 != this.m) {
            this.m = arg2;
            this.invalidateSelf();
        }
    }

    public void draw(Canvas arg5) {
        if(this.l) {
            this.a.setShader(this.a());
            this.l = false;
        }

        float v0 = this.a.getStrokeWidth() / 2f;
        RectF v1 = this.c;
        this.copyBounds(this.b);
        v1.set(this.b);
        v1.left += v0;
        v1.top += v0;
        v1.right -= v0;
        v1.bottom -= v0;
        arg5.save();
        arg5.rotate(this.m, v1.centerX(), v1.centerY());
        arg5.drawOval(v1, this.a);
        arg5.restore();
    }

    public Drawable$ConstantState getConstantState() {
        return this.d;
    }

    public int getOpacity() {
        int v0 = this.e > 0f ? -3 : -2;
        return v0;
    }

    public boolean getPadding(Rect arg2) {
        int v0 = Math.round(this.e);
        arg2.set(v0, v0, v0, v0);
        return 1;
    }

    public boolean isStateful() {
        boolean v0 = this.j != null && (this.j.isStateful()) || (super.isStateful()) ? true : false;
        return v0;
    }

    protected void onBoundsChange(Rect arg1) {
        this.l = true;
    }

    protected boolean onStateChange(int[] arg3) {
        if(this.j != null) {
            int v3 = this.j.getColorForState(arg3, this.k);
            if(v3 != this.k) {
                this.l = true;
                this.k = v3;
            }
        }

        if(this.l) {
            this.invalidateSelf();
        }

        return this.l;
    }

    public void setAlpha(int arg2) {
        this.a.setAlpha(arg2);
        this.invalidateSelf();
    }

    public void setColorFilter(ColorFilter arg2) {
        this.a.setColorFilter(arg2);
        this.invalidateSelf();
    }
}

