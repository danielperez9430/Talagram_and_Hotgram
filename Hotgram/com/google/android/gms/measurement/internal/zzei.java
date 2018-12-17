package com.google.android.gms.measurement.internal;

final class zzei implements Runnable {
    zzei(zzef arg1, zzag arg2) {
        this.zzasp = arg1;
        this.zzasq = arg2;
        super();
    }

    public final void run() {
        zzef v0 = this.zzasp;
        __monitor_enter(v0);
        try {
            zzef.zza(this.zzasp, false);
            if(!this.zzasp.zzasg.isConnected()) {
                this.zzasp.zzasg.zzgo().zzjk().zzbx("Connected to remote service");
                this.zzasp.zzasg.zza(this.zzasq);
            }

            __monitor_exit(v0);
            return;
        label_22:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_22;
        }

        throw v1;
    }
}

