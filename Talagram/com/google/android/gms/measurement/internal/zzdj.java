package com.google.android.gms.measurement.internal;

final class zzdj implements Runnable {
    zzdj(zzcs arg1, boolean arg2) {
        this.zzarc = arg1;
        this.zzaes = arg2;
        super();
    }

    public final void run() {
        boolean v0 = this.zzarc.zzadj.isEnabled();
        boolean v1 = this.zzarc.zzadj.zzko();
        this.zzarc.zzadj.zzd(this.zzaes);
        if(v1 == this.zzaes) {
            this.zzarc.zzadj.zzgo().zzjl().zzg("Default data collection state already set to", Boolean.valueOf(this.zzaes));
        }

        if(this.zzarc.zzadj.isEnabled() == v0 || this.zzarc.zzadj.isEnabled() != this.zzarc.zzadj.zzko()) {
            this.zzarc.zzadj.zzgo().zzji().zze("Default data collection is different than actual status", Boolean.valueOf(this.zzaes), Boolean.valueOf(v0));
        }

        zzcs.zza(this.zzarc);
    }
}

