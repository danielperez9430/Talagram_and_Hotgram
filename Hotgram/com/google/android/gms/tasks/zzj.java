package com.google.android.gms.tasks;

final class zzj implements Runnable {
    zzj(zzi arg1, Task arg2) {
        this.zzaft = arg1;
        this.zzafn = arg2;
        super();
    }

    public final void run() {
        Object v0 = zzi.zza(this.zzaft);
        __monitor_enter(v0);
        try {
            if(zzi.zzb(this.zzaft) != null) {
                zzi.zzb(this.zzaft).onComplete(this.zzafn);
            }

            __monitor_exit(v0);
            return;
        label_13:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_13;
        }

        throw v1;
    }
}

