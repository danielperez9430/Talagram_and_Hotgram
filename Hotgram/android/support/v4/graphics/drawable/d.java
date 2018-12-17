package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.drawable.Drawable;

class d extends Drawable implements Drawable$Callback, b, c {
    public abstract class a extends Drawable$ConstantState {
        int a;
        Drawable$ConstantState b;
        ColorStateList c;
        PorterDuff$Mode d;

        a(a arg1, Resources arg2) {
            super();
            this.c = null;
            this.d = d.a;
            if(arg1 != null) {
                this.a = arg1.a;
                this.b = arg1.b;
                this.c = arg1.c;
                this.d = arg1.d;
            }
        }

        boolean a() {
            boolean v0 = this.b != null ? true : false;
            return v0;
        }

        public int getChangingConfigurations() {
            int v0 = this.a;
            int v1 = this.b != null ? this.b.getChangingConfigurations() : 0;
            return v0 | v1;
        }

        public Drawable newDrawable() {
            return this.newDrawable(null);
        }

        public abstract Drawable newDrawable(Resources arg1);
    }

    class android.support.v4.graphics.drawable.d$b extends a {
        android.support.v4.graphics.drawable.d$b(a arg1, Resources arg2) {
            super(arg1, arg2);
        }

        public Drawable newDrawable(Resources arg2) {
            return new d(((a)this), arg2);
        }
    }

    static final PorterDuff$Mode a;
    a b;
    Drawable c;
    private int d;
    private PorterDuff$Mode e;
    private boolean f;
    private boolean g;

    static {
        d.a = PorterDuff$Mode.SRC_IN;
    }

    d(Drawable arg2) {
        super();
        this.b = this.b();
        this.a(arg2);
    }

    d(a arg1, Resources arg2) {
        super();
        this.b = arg1;
        this.a(arg2);
    }

    public final void a(Drawable arg3) {
        if(this.c != null) {
            this.c.setCallback(null);
        }

        this.c = arg3;
        if(arg3 != null) {
            arg3.setCallback(((Drawable$Callback)this));
            this.setVisible(arg3.isVisible(), true);
            this.setState(arg3.getState());
            this.setLevel(arg3.getLevel());
            this.setBounds(arg3.getBounds());
            if(this.b != null) {
                this.b.b = arg3.getConstantState();
            }
        }

        this.invalidateSelf();
    }

    private void a(Resources arg2) {
        if(this.b != null && this.b.b != null) {
            this.a(this.b.b.newDrawable(arg2));
        }
    }

    private boolean a(int[] arg5) {
        if(!this.c()) {
            return 0;
        }

        ColorStateList v0 = this.b.c;
        PorterDuff$Mode v2 = this.b.d;
        if(v0 == null || v2 == null) {
            this.f = false;
            this.clearColorFilter();
        }
        else {
            int v5 = v0.getColorForState(arg5, v0.getDefaultColor());
            if((this.f) && v5 == this.d && v2 == this.e) {
                return 0;
            }

            this.setColorFilter(v5, v2);
            this.d = v5;
            this.e = v2;
            this.f = true;
            return 1;
        }

        return 0;
    }

    public final Drawable a() {
        return this.c;
    }

    a b() {
        return new android.support.v4.graphics.drawable.d$b(this.b, null);
    }

    protected boolean c() {
        return 1;
    }

    public void draw(Canvas arg2) {
        this.c.draw(arg2);
    }

    public int getChangingConfigurations() {
        int v0 = super.getChangingConfigurations();
        int v1 = this.b != null ? this.b.getChangingConfigurations() : 0;
        return v0 | v1 | this.c.getChangingConfigurations();
    }

    public Drawable$ConstantState getConstantState() {
        if(this.b != null && (this.b.a())) {
            this.b.a = this.getChangingConfigurations();
            return this.b;
        }

        return null;
    }

    public Drawable getCurrent() {
        return this.c.getCurrent();
    }

    public int getIntrinsicHeight() {
        return this.c.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        return this.c.getIntrinsicWidth();
    }

    public int getMinimumHeight() {
        return this.c.getMinimumHeight();
    }

    public int getMinimumWidth() {
        return this.c.getMinimumWidth();
    }

    public int getOpacity() {
        return this.c.getOpacity();
    }

    public boolean getPadding(Rect arg2) {
        return this.c.getPadding(arg2);
    }

    public int[] getState() {
        return this.c.getState();
    }

    public Region getTransparentRegion() {
        return this.c.getTransparentRegion();
    }

    public void invalidateDrawable(Drawable arg1) {
        this.invalidateSelf();
    }

    public boolean isAutoMirrored() {
        return this.c.isAutoMirrored();
    }

    public boolean isStateful() {
        ColorStateList v0 = !this.c() || this.b == null ? null : this.b.c;
        boolean v0_1 = v0 != null && (v0.isStateful()) || (this.c.isStateful()) ? true : false;
        return v0_1;
    }

    public void jumpToCurrentState() {
        this.c.jumpToCurrentState();
    }

    public Drawable mutate() {
        if(!this.g && super.mutate() == this) {
            this.b = this.b();
            if(this.c != null) {
                this.c.mutate();
            }

            if(this.b != null) {
                a v0 = this.b;
                Drawable$ConstantState v1 = this.c != null ? this.c.getConstantState() : null;
                v0.b = v1;
            }

            this.g = true;
        }

        return this;
    }

    protected void onBoundsChange(Rect arg2) {
        if(this.c != null) {
            this.c.setBounds(arg2);
        }
    }

    protected boolean onLevelChange(int arg2) {
        return this.c.setLevel(arg2);
    }

    public void scheduleDrawable(Drawable arg1, Runnable arg2, long arg3) {
        this.scheduleSelf(arg2, arg3);
    }

    public void setAlpha(int arg2) {
        this.c.setAlpha(arg2);
    }

    public void setAutoMirrored(boolean arg2) {
        this.c.setAutoMirrored(arg2);
    }

    public void setChangingConfigurations(int arg2) {
        this.c.setChangingConfigurations(arg2);
    }

    public void setColorFilter(ColorFilter arg2) {
        this.c.setColorFilter(arg2);
    }

    public void setDither(boolean arg2) {
        this.c.setDither(arg2);
    }

    public void setFilterBitmap(boolean arg2) {
        this.c.setFilterBitmap(arg2);
    }

    public boolean setState(int[] arg2) {
        boolean v0 = this.c.setState(arg2);
        boolean v2 = (this.a(arg2)) || (v0) ? true : false;
        return v2;
    }

    public void setTint(int arg1) {
        this.setTintList(ColorStateList.valueOf(arg1));
    }

    public void setTintList(ColorStateList arg2) {
        this.b.c = arg2;
        this.a(this.getState());
    }

    public void setTintMode(PorterDuff$Mode arg2) {
        this.b.d = arg2;
        this.a(this.getState());
    }

    public boolean setVisible(boolean arg2, boolean arg3) {
        return (super.setVisible(arg2, arg3)) || (this.c.setVisible(arg2, arg3)) ? true : false;
    }

    public void unscheduleDrawable(Drawable arg1, Runnable arg2) {
        this.unscheduleSelf(arg2);
    }
}

