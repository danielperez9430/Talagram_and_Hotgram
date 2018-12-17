package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

final class zzcb implements Callable {
    zzcb(zzbv arg1, zzh arg2, String arg3, String arg4) {
        this.zzaqo = arg1;
        this.zzaqn = arg2;
        this.zzaeh = arg3;
        this.zzaeo = arg4;
        super();
    }

    public final Object call() {
        zzbv.zza(this.zzaqo).zzly();
        return zzbv.zza(this.zzaqo).zzjq().zzb(this.zzaqn.packageName, this.zzaeh, this.zzaeo);
    }
}

