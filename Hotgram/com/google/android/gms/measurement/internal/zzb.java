package com.google.android.gms.measurement.internal;

final class zzb implements Runnable {
    zzb(zza arg1, String arg2, long arg3) {
        this.zzafu = arg1;
        this.zzaet = arg2;
        this.zzaft = arg3;
        super();
    }

    public final void run() {
        zza.zza(this.zzafu, this.zzaet, this.zzaft);
    }
}

