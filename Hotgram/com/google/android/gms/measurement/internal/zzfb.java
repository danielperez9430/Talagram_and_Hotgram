package com.google.android.gms.measurement.internal;

final class zzfb implements Runnable {
    zzfb(zzfa arg1, zzff arg2) {
        this.zzaty = arg1;
        this.zzatx = arg2;
        super();
    }

    public final void run() {
        zzfa.zza(this.zzaty, this.zzatx);
        this.zzaty.start();
    }
}

