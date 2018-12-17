package com.google.android.gms.internal.measurement;

public abstract class zzza extends zzzg {
    protected zzzc zzcfc;

    public zzza() {
        super();
    }

    public Object clone() {
        zzzg v0 = super.zzyu();
        zzze.zza(this, ((zzza)v0));
        return v0;
    }

    public final Object zza(zzzb arg4) {
        Object v1 = null;
        if(this.zzcfc == null) {
            return v1;
        }

        zzzd v0 = this.zzcfc.zzcb(arg4.tag >>> 3);
        if(v0 == null) {
            return v1;
        }

        return v0.zzb(arg4);
    }

    public void zza(zzyy arg3) {
        if(this.zzcfc == null) {
            return;
        }

        int v0;
        for(v0 = 0; v0 < this.zzcfc.size(); ++v0) {
            this.zzcfc.zzcc(v0).zza(arg3);
        }
    }

    protected final boolean zza(zzyx arg4, int arg5) {
        int v0 = arg4.getPosition();
        if(!arg4.zzao(arg5)) {
            return 0;
        }

        int v1 = arg5 >>> 3;
        zzzi v0_1 = new zzzi(arg5, arg4.zzs(v0, arg4.getPosition() - v0));
        zzzd v4 = null;
        if(this.zzcfc == null) {
            this.zzcfc = new zzzc();
        }
        else {
            v4 = this.zzcfc.zzcb(v1);
        }

        if(v4 == null) {
            v4 = new zzzd();
            this.zzcfc.zza(v1, v4);
        }

        v4.zza(v0_1);
        return 1;
    }

    protected int zzf() {
        int v0;
        int v1 = 0;
        if(this.zzcfc != null) {
            v0 = 0;
            while(v1 < this.zzcfc.size()) {
                v0 += this.zzcfc.zzcc(v1).zzf();
                ++v1;
            }
        }
        else {
            v0 = 0;
        }

        return v0;
    }

    public final zzzg zzyu() {
        return ((zzzg)this).clone();
    }
}

