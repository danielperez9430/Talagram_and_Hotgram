package com.google.android.gms.dynamic;

import java.util.Iterator;

final class zza implements OnDelegateCreatedListener {
    zza(DeferredLifecycleHelper arg1) {
        this.zzabg = arg1;
        super();
    }

    public final void onDelegateCreated(LifecycleDelegate arg3) {
        DeferredLifecycleHelper.zza(this.zzabg, arg3);
        Iterator v3 = DeferredLifecycleHelper.zza(this.zzabg).iterator();
        while(v3.hasNext()) {
            v3.next().zza(DeferredLifecycleHelper.zzb(this.zzabg));
        }

        DeferredLifecycleHelper.zza(this.zzabg).clear();
        DeferredLifecycleHelper.zza(this.zzabg, null);
    }
}

