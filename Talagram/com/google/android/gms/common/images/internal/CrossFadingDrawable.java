package com.google.android.gms.common.images.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;

public final class CrossFadingDrawable extends Drawable implements Drawable$Callback {
    final class zza extends Drawable {
        private static final zza zzqj;
        private static final zzb zzqk;

        static {
            zza.zzqj = new zza();
            zza.zzqk = new zzb(null);
        }

        private zza() {
            super();
        }

        public final void draw(Canvas arg1) {
        }

        public final Drawable$ConstantState getConstantState() {
            return zza.zzqk;
        }

        public final int getOpacity() {
            return -2;
        }

        public final void setAlpha(int arg1) {
        }

        public final void setColorFilter(ColorFilter arg1) {
        }

        static zza zzcp() {
            return zza.zzqj;
        }
    }

    final class com.google.android.gms.common.images.internal.CrossFadingDrawable$zzb extends Drawable$ConstantState {
        int mChangingConfigurations;
        int zzql;

        com.google.android.gms.common.images.internal.CrossFadingDrawable$zzb(com.google.android.gms.common.images.internal.CrossFadingDrawable$zzb arg2) {
            super();
            if(arg2 != null) {
                this.mChangingConfigurations = arg2.mChangingConfigurations;
                this.zzql = arg2.zzql;
            }
        }

        public final int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }

        public final Drawable newDrawable() {
            return new CrossFadingDrawable(this);
        }
    }

    private int mAlpha;
    private int mFrom;
    private boolean zzpl;
    private int zzpw;
    private long zzpx;
    private int zzpy;
    private int zzpz;
    private int zzqa;
    private boolean zzqb;
    private com.google.android.gms.common.images.internal.CrossFadingDrawable$zzb zzqc;
    private Drawable zzqd;
    private Drawable zzqe;
    private boolean zzqf;
    private boolean zzqg;
    private boolean zzqh;
    private int zzqi;

    public CrossFadingDrawable(Drawable arg3, Drawable arg4) {
        zza v4;
        zza v3;
        this(null);
        if(arg3 == null) {
            v3 = zza.zzcp();
        }

        this.zzqd = ((Drawable)v3);
        ((Drawable)v3).setCallback(((Drawable$Callback)this));
        this.zzqc.zzql |= ((Drawable)v3).getChangingConfigurations();
        if(arg4 == null) {
            v4 = zza.zzcp();
        }

        this.zzqe = ((Drawable)v4);
        ((Drawable)v4).setCallback(((Drawable$Callback)this));
        this.zzqc.zzql |= ((Drawable)v4).getChangingConfigurations();
    }

    CrossFadingDrawable(com.google.android.gms.common.images.internal.CrossFadingDrawable$zzb arg3) {
        super();
        this.zzpw = 0;
        this.zzpz = 255;
        this.mAlpha = 0;
        this.zzpl = true;
        this.zzqc = new com.google.android.gms.common.images.internal.CrossFadingDrawable$zzb(arg3);
    }

    public final boolean canConstantState() {
        if(!this.zzqf) {
            boolean v0 = this.zzqd.getConstantState() == null || this.zzqe.getConstantState() == null ? false : true;
            this.zzqg = v0;
            this.zzqf = true;
        }

        return this.zzqg;
    }

    public final void draw(Canvas arg8) {
        int v1 = 1;
        switch(this.zzpw) {
            case 1: {
                this.zzpx = SystemClock.uptimeMillis();
                this.zzpw = 2;
                v1 = 0;
                break;
            }
            case 2: {
                if(this.zzpx < 0) {
                    goto label_35;
                }

                float v0 = (((float)(SystemClock.uptimeMillis() - this.zzpx))) / (((float)this.zzqa));
                float v3 = 1f;
                if(v0 >= v3) {
                }
                else {
                    v1 = 0;
                }

                if(v1 != 0) {
                    this.zzpw = 0;
                }

                this.mAlpha = ((int)((((float)this.zzpy)) * Math.min(v0, v3) + 0f));
                break;
            }
            default: {
                break;
            }
        }

    label_35:
        int v0_1 = this.mAlpha;
        boolean v2 = this.zzpl;
        Drawable v3_1 = this.zzqd;
        Drawable v4 = this.zzqe;
        if(v1 != 0) {
            if(!v2 || v0_1 == 0) {
                v3_1.draw(arg8);
            }

            if(v0_1 == this.zzpz) {
                v4.setAlpha(this.zzpz);
                v4.draw(arg8);
            }

            return;
        }

        if(v2) {
            v3_1.setAlpha(this.zzpz - v0_1);
        }

        v3_1.draw(arg8);
        if(v2) {
            v3_1.setAlpha(this.zzpz);
        }

        if(v0_1 > 0) {
            v4.setAlpha(v0_1);
            v4.draw(arg8);
            v4.setAlpha(this.zzpz);
        }

        this.invalidateSelf();
    }

    public final int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.zzqc.mChangingConfigurations | this.zzqc.zzql;
    }

    public final Drawable$ConstantState getConstantState() {
        if(this.canConstantState()) {
            this.zzqc.mChangingConfigurations = this.getChangingConfigurations();
            return this.zzqc;
        }

        return null;
    }

    public final Drawable getEndDrawable() {
        return this.zzqe;
    }

    public final int getIntrinsicHeight() {
        return Math.max(this.zzqd.getIntrinsicHeight(), this.zzqe.getIntrinsicHeight());
    }

    public final int getIntrinsicWidth() {
        return Math.max(this.zzqd.getIntrinsicWidth(), this.zzqe.getIntrinsicWidth());
    }

    public final int getOpacity() {
        if(!this.zzqh) {
            this.zzqi = Drawable.resolveOpacity(this.zzqd.getOpacity(), this.zzqe.getOpacity());
            this.zzqh = true;
        }

        return this.zzqi;
    }

    public final Drawable getStartDrawable() {
        return this.zzqd;
    }

    public final void invalidateDrawable(Drawable arg1) {
        Drawable$Callback v1 = this.getCallback();
        if(v1 != null) {
            v1.invalidateDrawable(((Drawable)this));
        }
    }

    public final Drawable mutate() {
        if(!this.zzqb && super.mutate() == this) {
            if(this.canConstantState()) {
                this.zzqd.mutate();
                this.zzqe.mutate();
                this.zzqb = true;
            }
            else {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
        }

        return this;
    }

    protected final void onBoundsChange(Rect arg2) {
        this.zzqd.setBounds(arg2);
        this.zzqe.setBounds(arg2);
    }

    public final void resetTransition() {
        this.mAlpha = 0;
        this.zzpw = 0;
        this.invalidateSelf();
    }

    public final void scheduleDrawable(Drawable arg1, Runnable arg2, long arg3) {
        Drawable$Callback v1 = this.getCallback();
        if(v1 != null) {
            v1.scheduleDrawable(((Drawable)this), arg2, arg3);
        }
    }

    public final void setAlpha(int arg3) {
        if(this.mAlpha == this.zzpz) {
            this.mAlpha = arg3;
        }

        this.zzpz = arg3;
        this.invalidateSelf();
    }

    public final void setColorFilter(ColorFilter arg2) {
        this.zzqd.setColorFilter(arg2);
        this.zzqe.setColorFilter(arg2);
    }

    public final void setCrossFadeEnabled(boolean arg1) {
        this.zzpl = arg1;
    }

    public final void startTransition(int arg3) {
        this.mFrom = 0;
        this.zzpy = this.zzpz;
        this.mAlpha = 0;
        this.zzqa = arg3;
        this.zzpw = 1;
        this.invalidateSelf();
    }

    public final void unscheduleDrawable(Drawable arg1, Runnable arg2) {
        Drawable$Callback v1 = this.getCallback();
        if(v1 != null) {
            v1.unscheduleDrawable(((Drawable)this), arg2);
        }
    }
}

