package com.google.android.gms.measurement.internal;

final class zzby implements Runnable {
    zzby(zzbv arg1, zzl arg2, zzh arg3) {
        this.zzaqo = arg1;
        this.zzaqp = arg2;
        this.zzaqn = arg3;
        super();
    }

    public final void run() {
        zzbv.zza(this.zzaqo).zzly();
        zzbv.zza(this.zzaqo).zzb(this.zzaqp, this.zzaqn);
    }
}

