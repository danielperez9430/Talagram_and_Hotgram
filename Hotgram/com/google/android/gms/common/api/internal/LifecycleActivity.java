package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.v4.app.h;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk public class LifecycleActivity {
    private final Object zzkz;

    public LifecycleActivity(Activity arg2) {
        super();
        Preconditions.checkNotNull(arg2, "Activity must not be null");
        this.zzkz = arg2;
    }

    public final boolean zzbv() {
        return this.zzkz instanceof h;
    }

    public final boolean zzbw() {
        return this.zzkz instanceof Activity;
    }

    public final Activity zzbx() {
        return this.zzkz;
    }

    public final h zzby() {
        return this.zzkz;
    }
}

