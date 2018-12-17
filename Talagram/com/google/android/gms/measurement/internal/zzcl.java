package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

final class zzcl implements Callable {
    zzcl(zzbv arg1, zzh arg2) {
        this.zzaqo = arg1;
        this.zzaqn = arg2;
        super();
    }

    public final Object call() {
        zzbv.zza(this.zzaqo).zzly();
        return zzbv.zza(this.zzaqo).zzjq().zzbk(this.zzaqn.packageName);
    }
}

