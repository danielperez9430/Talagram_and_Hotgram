package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzcy implements Runnable {
    zzcy(zzcs arg1, AtomicReference arg2) {
        this.zzarc = arg1;
        this.zzarb = arg2;
        super();
    }

    public final void run() {
        this.zzarc.zzgg().zza(this.zzarb);
    }
}

