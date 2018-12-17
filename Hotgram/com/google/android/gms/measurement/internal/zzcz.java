package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzcz implements Runnable {
    zzcz(zzcs arg1, long arg2) {
        this.zzarc = arg1;
        this.zzari = arg2;
        super();
    }

    public final void run() {
        zzcs v0 = this.zzarc;
        long v1 = this.zzari;
        ((zzco)v0).zzaf();
        ((zzco)v0).zzgb();
        ((zzf)v0).zzcl();
        ((zzco)v0).zzgo().zzjk().zzbx("Resetting analytics data (FE)");
        ((zze)v0).zzgj().zzlj();
        if(((zzco)v0).zzgq().zzbe(((zze)v0).zzgf().zzal())) {
            ((zzco)v0).zzgp().zzanj.set(v1);
        }

        boolean v1_1 = v0.zzadj.isEnabled();
        if(!((zzco)v0).zzgq().zzhu()) {
            ((zzco)v0).zzgp().zzi((((int)v1_1)) ^ 1);
        }

        ((zze)v0).zzgg().resetAnalyticsData();
        v0.zzara = (((int)v1_1)) ^ 1;
        if(this.zzarc.zzgq().zza(zzaf.zzalk)) {
            this.zzarc.zzgg().zza(new AtomicReference());
        }
    }
}

