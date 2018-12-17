package com.google.android.gms.internal.firebase_abt;

public abstract class zzd extends zzj {
    protected zzf zzs;

    public zzd() {
        super();
    }

    public Object clone() {
        zzj v0 = super.zzj();
        zzh.zza(this, ((zzd)v0));
        return v0;
    }

    protected final boolean zza(zza arg4, int arg5) {
        int v0 = arg4.getPosition();
        if(!arg4.zzb(arg5)) {
            return 0;
        }

        int v1 = arg5 >>> 3;
        zzl v0_1 = new zzl(arg5, arg4.zza(v0, arg4.getPosition() - v0));
        zzg v4 = null;
        if(this.zzs == null) {
            this.zzs = new zzf();
        }
        else {
            v4 = this.zzs.zzg(v1);
        }

        if(v4 == null) {
            v4 = new zzg();
            this.zzs.zza(v1, v4);
        }

        v4.zza(v0_1);
        return 1;
    }

    public final zzj zzj() {
        return ((zzj)this).clone();
    }
}

