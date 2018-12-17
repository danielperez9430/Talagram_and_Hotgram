package com.google.android.gms.dynamic;

import android.os.Bundle;

final class zzc implements zza {
    zzc(DeferredLifecycleHelper arg1, Bundle arg2) {
        this.zzabg = arg1;
        this.zzabi = arg2;
        super();
    }

    public final int getState() {
        return 1;
    }

    public final void zza(LifecycleDelegate arg2) {
        DeferredLifecycleHelper.zzb(this.zzabg).onCreate(this.zzabi);
    }
}

