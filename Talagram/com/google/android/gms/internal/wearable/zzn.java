package com.google.android.gms.internal.wearable;

public abstract class zzn extends zzt {
    protected zzp zzhc;

    public zzn() {
        super();
    }

    public Object clone() {
        zzt v0 = super.zzs();
        zzr.zza(this, ((zzn)v0));
        return v0;
    }

    public void zza(zzl arg3) {
        if(this.zzhc == null) {
            return;
        }

        int v0;
        for(v0 = 0; v0 < this.zzhc.size(); ++v0) {
            this.zzhc.zzp(v0).zza(arg3);
        }
    }

    protected final boolean zza(zzk arg4, int arg5) {
        int v0 = arg4.getPosition();
        if(!arg4.zzd(arg5)) {
            return 0;
        }

        int v1 = arg5 >>> 3;
        zzv v0_1 = new zzv(arg5, arg4.zzb(v0, arg4.getPosition() - v0));
        zzq v4 = null;
        if(this.zzhc == null) {
            this.zzhc = new zzp();
        }
        else {
            v4 = this.zzhc.zzo(v1);
        }

        if(v4 == null) {
            v4 = new zzq();
            this.zzhc.zza(v1, v4);
        }

        v4.zza(v0_1);
        return 1;
    }

    protected int zzg() {
        int v0;
        int v1 = 0;
        if(this.zzhc != null) {
            v0 = 0;
            while(v1 < this.zzhc.size()) {
                v0 += this.zzhc.zzp(v1).zzg();
                ++v1;
            }
        }
        else {
            v0 = 0;
        }

        return v0;
    }

    public final zzt zzs() {
        return ((zzt)this).clone();
    }
}

