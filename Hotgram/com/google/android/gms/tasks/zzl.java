package com.google.android.gms.tasks;

final class zzl implements Runnable {
    zzl(zzk arg1, Task arg2) {
        this.zzafv = arg1;
        this.zzafn = arg2;
        super();
    }

    public final void run() {
        Object v0 = zzk.zza(this.zzafv);
        __monitor_enter(v0);
        try {
            if(zzk.zzb(this.zzafv) != null) {
                zzk.zzb(this.zzafv).onFailure(this.zzafn.getException());
            }

            __monitor_exit(v0);
            return;
        label_14:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_14;
        }

        throw v1;
    }
}

