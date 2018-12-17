package com.google.android.gms.measurement.internal;

import android.content.Intent;

final class zzem implements Runnable {
    private final int zzacb;
    private final zzel zzasr;
    private final zzap zzass;
    private final Intent zzast;

    zzem(zzel arg1, int arg2, zzap arg3, Intent arg4) {
        super();
        this.zzasr = arg1;
        this.zzacb = arg2;
        this.zzass = arg3;
        this.zzast = arg4;
    }

    public final void run() {
        this.zzasr.zza(this.zzacb, this.zzass, this.zzast);
    }
}

