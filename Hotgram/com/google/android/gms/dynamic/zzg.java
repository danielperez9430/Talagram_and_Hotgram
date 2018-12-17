package com.google.android.gms.dynamic;

final class zzg implements zza {
    zzg(DeferredLifecycleHelper arg1) {
        this.zzabg = arg1;
        super();
    }

    public final int getState() {
        return 5;
    }

    public final void zza(LifecycleDelegate arg1) {
        DeferredLifecycleHelper.zzb(this.zzabg).onResume();
    }
}

