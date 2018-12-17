package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

final class zzci implements Callable {
    zzci(zzbv arg1, zzad arg2, String arg3) {
        this.zzaqo = arg1;
        this.zzaqr = arg2;
        this.zzaqq = arg3;
        super();
    }

    public final Object call() {
        zzbv.zza(this.zzaqo).zzly();
        return zzbv.zza(this.zzaqo).zza(this.zzaqr, this.zzaqq);
    }
}

