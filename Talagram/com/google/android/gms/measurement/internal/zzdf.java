package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzdf implements Runnable {
    zzdf(zzcs arg1, AtomicReference arg2) {
        this.zzarc = arg1;
        this.zzarb = arg2;
        super();
    }

    public final void run() {
        AtomicReference v0 = this.zzarb;
        __monitor_enter(v0);
        try {
            this.zzarb.set(Long.valueOf(this.zzarc.zzgq().zza(this.zzarc.zzgf().zzal(), zzaf.zzakm)));
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

