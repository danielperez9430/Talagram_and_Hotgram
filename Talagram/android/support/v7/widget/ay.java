package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

class ay extends Drawable {
    private float a;
    private final Paint b;
    private final RectF c;
    private final Rect d;
    private float e;
    private boolean f;
    private boolean g;
    private ColorStateList h;
    private PorterDuffColorFilter i;
    private ColorStateList j;
    private PorterDuff$Mode k;

    ay(ColorStateList arg2, float arg3) {
        super();
        this.f = false;
        this.g = true;
        this.k = PorterDuff$Mode.SRC_IN;
        this.a = arg3;
        this.b = new Paint(5);
        this.b(arg2);
        this.c = new RectF();
        this.d = new Rect();
    }

    private PorterDuffColorFilter a(ColorStateList arg3, PorterDuff$Mode arg4) {
        if(arg3 != null) {
            if(arg4 == null) {
            }
            else {
                return new PorterDuffColorFilter(arg3.getColorForState(this.getState(), 0), arg4);
            }
        }

        return null;
    }

    private void a(Rect arg6) {
        if(arg6 == null) {
            arg6 = this.getBounds();
        }

        this.c.set(((float)arg6.left), ((float)arg6.top), ((float)arg6.right), ((float)arg6.bottom));
        this.d.set(arg6);
        if(this.f) {
            this.d.inset(((int)Math.ceil(((double)az.b(this.e, this.a, this.g)))), ((int)Math.ceil(((double)az.a(this.e, this.a, this.g)))));
            this.c.set(this.d);
        }
    }

    float a() {
        return this.e;
    }

    void a(float arg2) {
        if(arg2 == this.a) {
            return;
        }

        this.a = arg2;
        this.a(null);
        this.invalidateSelf();
    }

    void a(float arg2, boolean arg3, boolean arg4) {
        if(arg2 == this.e && this.f == arg3 && this.g == arg4) {
            return;
        }

        this.e = arg2;
        this.f = arg3;
        this.g = arg4;
        this.a(null);
        this.invalidateSelf();
    }

    public void a(ColorStateList arg1) {
        this.b(arg1);
        this.invalidateSelf();
    }

    private void b(ColorStateList arg4) {
        if(arg4 == null) {
            arg4 = ColorStateList.valueOf(0);
        }

        this.h = arg4;
        this.b.setColor(this.h.getColorForState(this.getState(), this.h.getDefaultColor()));
    }

    public float b() {
        return this.a;
    }

    public ColorStateList c() {
        return this.h;
    }

    public void draw(Canvas arg6) {
        int v1;
        Paint v0 = this.b;
        if(this.i == null || v0.getColorFilter() != null) {
            v1 = 0;
        }
        else {
            v0.setColorFilter(this.i);
            v1 = 1;
        }

        arg6.drawRoundRect(this.c, this.a, this.a, v0);
        if(v1 != 0) {
            v0.setColorFilter(null);
        }
    }

    public int getOpacity() {
        return -3;
    }

    public void getOutline(Outline arg3) {
        arg3.setRoundRect(this.d, this.a);
    }

    public boolean isStateful() {
        boolean v0;
        if(this.j == null || !this.j.isStateful()) {
            if(this.h != null && (this.h.isStateful())) {
                goto label_12;
            }

            if(super.isStateful()) {
            label_12:
                v0 = true;
                return v0;
            }

            v0 = false;
        }
        else {
            goto label_12;
        }

        return v0;
    }

    protected void onBoundsChange(Rect arg1) {
        super.onBoundsChange(arg1);
        this.a(arg1);
    }

    protected boolean onStateChange(int[] arg4) {
        int v4 = this.h.getColorForState(arg4, this.h.getDefaultColor());
        boolean v0 = v4 != this.b.getColor() ? true : false;
        if(v0) {
            this.b.setColor(v4);
        }

        if(this.j != null && this.k != null) {
            this.i = this.a(this.j, this.k);
            return 1;
        }

        return v0;
    }

    public void setAlpha(int arg2) {
        this.b.setAlpha(arg2);
    }

    public void setColorFilter(ColorFilter arg2) {
        this.b.setColorFilter(arg2);
    }

    public void setTintList(ColorStateList arg2) {
        this.j = arg2;
        this.i = this.a(this.j, this.k);
        this.invalidateSelf();
    }

    public void setTintMode(PorterDuff$Mode arg2) {
        this.k = arg2;
        this.i = this.a(this.j, this.k);
        this.invalidateSelf();
    }
}

