package com.google.android.gms.tasks;

final class zzh implements Runnable {
    zzh(zzg arg1) {
        this.zzafr = arg1;
        super();
    }

    public final void run() {
        Object v0 = zzg.zza(this.zzafr);
        __monitor_enter(v0);
        try {
            if(zzg.zzb(this.zzafr) != null) {
                zzg.zzb(this.zzafr).onCanceled();
            }

            __monitor_exit(v0);
            return;
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_12;
        }

        throw v1;
    }
}

