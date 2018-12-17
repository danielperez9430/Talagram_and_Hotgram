package com.google.android.gms.measurement.internal;

final class zzch implements Runnable {
    zzch(zzbv arg1, zzad arg2, String arg3) {
        this.zzaqo = arg1;
        this.zzaqr = arg2;
        this.zzaqq = arg3;
        super();
    }

    public final void run() {
        zzbv.zza(this.zzaqo).zzly();
        zzbv.zza(this.zzaqo).zzc(this.zzaqr, this.zzaqq);
    }
}

