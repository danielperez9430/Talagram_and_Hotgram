package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzdg implements Runnable {
    zzdg(zzcs arg1, AtomicReference arg2) {
        this.zzarc = arg1;
        this.zzarb = arg2;
        super();
    }

    public final void run() {
        AtomicReference v0 = this.zzarb;
        __monitor_enter(v0);
        try {
            this.zzarb.set(Integer.valueOf(this.zzarc.zzgq().zzb(this.zzarc.zzgf().zzal(), zzaf.zzakn)));
            goto label_12;
        }
        catch(Throwable v1) {
            try {
                this.zzarb.notify();
                throw v1;
            label_12:
                this.zzarb.notify();
                __monitor_exit(v0);
                return;
            label_21:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_21;
            }
        }

        throw v1;
    }
}

