package com.google.android.gms.measurement.internal;

final class zzeg implements Runnable {
    zzeg(zzef arg1, zzag arg2) {
        this.zzasp = arg1;
        this.zzaso = arg2;
        super();
    }

    public final void run() {
        zzef v0 = this.zzasp;
        __monitor_enter(v0);
        try {
            zzef.zza(this.zzasp, false);
            if(!this.zzasp.zzasg.isConnected()) {
                this.zzasp.zzasg.zzgo().zzjl().zzbx("Connected to service");
                this.zzasp.zzasg.zza(this.zzaso);
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

