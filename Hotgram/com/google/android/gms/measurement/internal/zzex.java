package com.google.android.gms.measurement.internal;

final class zzex extends zzv {
    zzex(zzew arg1, zzcq arg2, zzfa arg3) {
        this.zzatb = arg1;
        this.zzasv = arg3;
        super(arg2);
    }

    public final void run() {
        this.zzatb.cancel();
        this.zzatb.zzgo().zzjl().zzbx("Starting upload from DelayedRunnable");
        this.zzasv.zzlt();
    }
}

