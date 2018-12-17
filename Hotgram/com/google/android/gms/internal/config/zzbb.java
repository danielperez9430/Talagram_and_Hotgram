package com.google.android.gms.internal.config;

public abstract class zzbb extends zzbh {
    protected zzbd zzch;

    public zzbb() {
        super();
    }

    public Object clone() {
        zzbh v0 = super.zzad();
        zzbf.zza(this, ((zzbb)v0));
        return v0;
    }

    public void zza(zzaz arg3) {
        if(this.zzch == null) {
            return;
        }

        int v0;
        for(v0 = 0; v0 < this.zzch.size(); ++v0) {
            this.zzch.zzp(v0).zza(arg3);
        }
    }

    protected final boolean zza(zzay arg4, int arg5) {
        int v0 = arg4.getPosition();
        if(!arg4.zzh(arg5)) {
            return 0;
        }

        int v1 = arg5 >>> 3;
        zzbj v0_1 = new zzbj(arg5, arg4.zza(v0, arg4.getPosition() - v0));
        zzbe v4 = null;
        if(this.zzch == null) {
            this.zzch = new zzbd();
        }
        else {
            v4 = this.zzch.zzo(v1);
        }

        if(v4 == null) {
            v4 = new zzbe();
            this.zzch.zza(v1, v4);
        }

        v4.zza(v0_1);
        return 1;
    }

    public final zzbh zzad() {
        return ((zzbh)this).clone();
    }

    protected int zzt() {
        int v0;
        int v1 = 0;
        if(this.zzch != null) {
            v0 = 0;
            while(v1 < this.zzch.size()) {
                v0 += this.zzch.zzp(v1).zzt();
                ++v1;
            }
        }
        else {
            v0 = 0;
        }

        return v0;
    }
}

