package com.google.android.gms.measurement.internal;

final class zzdl implements Runnable {
    zzdl(zzcs arg1, long arg2) {
        this.zzarc = arg1;
        this.zzark = arg2;
        super();
    }

    public final void run() {
        this.zzarc.zzgp().zzanq.set(this.zzark);
        this.zzarc.zzgo().zzjk().zzg("Session timeout duration set", Long.valueOf(this.zzark));
    }
}

