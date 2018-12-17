package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzdd implements Runnable {
    zzdd(zzcs arg1, AtomicReference arg2) {
        this.zzarc = arg1;
        this.zzarb = arg2;
        super();
    }

    public final void run() {
        AtomicReference v0 = this.zzarb;
        __monitor_enter(v0);
        try {
            this.zzarb.set(this.zzarc.zzgq().zzbb(this.zzarc.zzgf().zzal()));
            goto label_10;
        }
        catch(Throwable v1) {
            try {
                this.zzarb.notify();
                throw v1;
            label_10:
                this.zzarb.notify();
                __monitor_exit(v0);
                return;
            label_19:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_19;
            }
        }

        throw v1;
    }
}

