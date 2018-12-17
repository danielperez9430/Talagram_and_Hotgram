package com.google.android.gms.measurement.internal;

final class zzbk implements Runnable {
    zzbk(zzbj arg1, zzbt arg2, zzap arg3) {
        this.zzaoj = arg2;
        this.zzaok = arg3;
        super();
    }

    public final void run() {
        if(this.zzaoj.zzkg() == null) {
            this.zzaok.zzjd().zzbx("Install Referrer Reporter is null");
            return;
        }

        zzbg v0 = this.zzaoj.zzkg();
        v0.zzadj.zzgb();
        v0.zzcd(v0.zzadj.getContext().getPackageName());
    }
}

