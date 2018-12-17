package com.google.android.gms.measurement.internal;

final class zzdk implements Runnable {
    zzdk(zzcs arg1, long arg2) {
        this.zzarc = arg1;
        this.zzark = arg2;
        super();
    }

    public final void run() {
        this.zzarc.zzgp().zzanp.set(this.zzark);
        this.zzarc.zzgo().zzjk().zzg("Minimum session duration set", Long.valueOf(this.zzark));
    }
}

