package com.google.android.gms.stats;

final class zzb implements Runnable {
    zzb(WakeLock arg1) {
        this.zzaei = arg1;
        super();
    }

    public final void run() {
        WakeLock.zza(this.zzaei, 0);
    }
}

