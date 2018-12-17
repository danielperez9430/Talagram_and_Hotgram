package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzct implements Runnable {
    zzct(zzcs arg1, AtomicReference arg2) {
        this.zzarc = arg1;
        this.zzarb = arg2;
        super();
    }

    public final void run() {
        AtomicReference v0 = this.zzarb;
        __monitor_enter(v0);
        try {
            this.zzarb.set(Boolean.valueOf(this.zzarc.zzgq().zzba(this.zzarc.zzgf().zzal())));
            goto label_11;
        }
        catch(Throwable v1) {
            try {
                this.zzarb.notify();
                throw v1;
            label_11:
                this.zzarb.notify();
                __monitor_exit(v0);
                return;
            label_20:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_20;
            }
        }

        throw v1;
    }
}

