package com.google.android.gms.measurement.internal;

import android.app.job.JobParameters;

final class zzen implements Runnable {
    private final JobParameters zzace;
    private final zzel zzasr;
    private final zzap zzasu;

    zzen(zzel arg1, zzap arg2, JobParameters arg3) {
        super();
        this.zzasr = arg1;
        this.zzasu = arg2;
        this.zzace = arg3;
    }

    public final void run() {
        this.zzasr.zza(this.zzasu, this.zzace);
    }
}

