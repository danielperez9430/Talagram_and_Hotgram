package com.google.android.gms.measurement.internal;

final class zzdq implements Runnable {
    zzdq(zzdo arg1, zzdn arg2) {
        this.zzarx = arg1;
        this.zzary = arg2;
        super();
    }

    public final void run() {
        zzdo.zza(this.zzarx, this.zzary);
        this.zzarx.zzaro = null;
        this.zzarx.zzgg().zzb(null);
    }
}

