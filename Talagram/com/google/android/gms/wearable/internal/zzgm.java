package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

class zzgm extends zza {
    private ResultHolder zzet;

    public zzgm(ResultHolder arg1) {
        super();
        this.zzet = arg1;
    }

    public final void zza(Object arg2) {
        ResultHolder v0 = this.zzet;
        if(v0 != null) {
            v0.setResult(arg2);
            this.zzet = null;
        }
    }
}

