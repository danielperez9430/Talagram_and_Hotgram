package com.google.android.gms.dynamic;

import android.app.Activity;
import android.os.Bundle;

final class zzb implements zza {
    zzb(DeferredLifecycleHelper arg1, Activity arg2, Bundle arg3, Bundle arg4) {
        this.zzabg = arg1;
        this.val$activity = arg2;
        this.zzabh = arg3;
        this.zzabi = arg4;
        super();
    }

    public final int getState() {
        return 0;
    }

    public final void zza(LifecycleDelegate arg4) {
        DeferredLifecycleHelper.zzb(this.zzabg).onInflate(this.val$activity, this.zzabh, this.zzabi);
    }
}

