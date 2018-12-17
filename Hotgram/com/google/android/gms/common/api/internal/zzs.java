package com.google.android.gms.common.api.internal;

final class zzs implements Runnable {
    zzs(zzr arg1) {
        this.zzgc = arg1;
        super();
    }

    public final void run() {
        zzr.zza(this.zzgc).lock();
        try {
            zzr.zzb(this.zzgc);
        }
        catch(Throwable v0) {
            zzr.zza(this.zzgc).unlock();
            throw v0;
        }

        zzr.zza(this.zzgc).unlock();
    }
}

