package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzdc implements Runnable {
    zzdc(zzcs arg1, AtomicReference arg2, String arg3, String arg4, String arg5) {
        this.zzarc = arg1;
        this.zzarb = arg2;
        this.zzaqq = arg3;
        this.zzaeh = arg4;
        this.zzaeo = arg5;
        super();
    }

    public final void run() {
        this.zzarc.zzadj.zzgg().zza(this.zzarb, this.zzaqq, this.zzaeh, this.zzaeo);
    }
}

