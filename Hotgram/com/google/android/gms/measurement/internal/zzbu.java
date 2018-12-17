package com.google.android.gms.measurement.internal;

final class zzbu implements Runnable {
    zzbu(zzbt arg1, zzcr arg2) {
        this.zzaqk = arg1;
        this.zzaqj = arg2;
        super();
    }

    public final void run() {
        zzbt.zza(this.zzaqk, this.zzaqj);
        this.zzaqk.start();
    }
}

