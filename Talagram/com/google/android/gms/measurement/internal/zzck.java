package com.google.android.gms.measurement.internal;

final class zzck implements Runnable {
    zzck(zzbv arg1, zzfh arg2, zzh arg3) {
        this.zzaqo = arg1;
        this.zzaqs = arg2;
        this.zzaqn = arg3;
        super();
    }

    public final void run() {
        zzbv.zza(this.zzaqo).zzly();
        zzbv.zza(this.zzaqo).zzb(this.zzaqs, this.zzaqn);
    }
}

