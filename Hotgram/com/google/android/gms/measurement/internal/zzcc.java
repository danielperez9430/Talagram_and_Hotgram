package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

final class zzcc implements Callable {
    zzcc(zzbv arg1, String arg2, String arg3, String arg4) {
        this.zzaqo = arg1;
        this.zzaqq = arg2;
        this.zzaeh = arg3;
        this.zzaeo = arg4;
        super();
    }

    public final Object call() {
        zzbv.zza(this.zzaqo).zzly();
        return zzbv.zza(this.zzaqo).zzjq().zzb(this.zzaqq, this.zzaeh, this.zzaeo);
    }
}

