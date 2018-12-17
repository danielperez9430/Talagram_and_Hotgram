package com.google.android.gms.measurement.internal;

final class zzcn implements Runnable {
    zzcn(zzbv arg1, String arg2, String arg3, String arg4, long arg5) {
        this.zzaqo = arg1;
        this.zzaqt = arg2;
        this.zzaqq = arg3;
        this.zzaeq = arg4;
        this.zzaqu = arg5;
        super();
    }

    public final void run() {
        if(this.zzaqt == null) {
            zzbv.zza(this.zzaqo).zzmb().zzgh().zza(this.zzaqq, null);
            return;
        }

        zzbv.zza(this.zzaqo).zzmb().zzgh().zza(this.zzaqq, new zzdn(this.zzaeq, this.zzaqt, this.zzaqu));
    }
}

