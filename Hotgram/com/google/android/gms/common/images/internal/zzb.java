package com.google.android.gms.common.images.internal;

import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.drawable.Drawable;

final class zzb extends Drawable$ConstantState {
    zzb(zza arg1) {
        this();
    }

    private zzb() {
        super();
    }

    public final int getChangingConfigurations() {
        return 0;
    }

    public final Drawable newDrawable() {
        return com.google.android.gms.common.images.internal.CrossFadingDrawable$zza.zzcp();
    }
}

