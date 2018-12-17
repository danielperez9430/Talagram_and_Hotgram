package com.google.android.gms.dynamic;

final class zzf implements zza {
    zzf(DeferredLifecycleHelper arg1) {
        this.zzabg = arg1;
        super();
    }

    public final int getState() {
        return 4;
    }

    public final void zza(LifecycleDelegate arg1) {
        DeferredLifecycleHelper.zzb(this.zzabg).onStart();
    }
}

