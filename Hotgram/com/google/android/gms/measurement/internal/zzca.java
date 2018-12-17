package com.google.android.gms.measurement.internal;

final class zzca implements Runnable {
    zzca(zzbv arg1, zzl arg2) {
        this.zzaqo = arg1;
        this.zzaqp = arg2;
        super();
    }

    public final void run() {
        zzbv.zza(this.zzaqo).zzly();
        zzbv.zza(this.zzaqo).zze(this.zzaqp);
    }
}

