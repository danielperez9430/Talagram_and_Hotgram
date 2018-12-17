package com.google.android.gms.internal.places;

public abstract class zzko extends zzku {
    protected zzkq zzaaf;

    public zzko() {
        super();
    }

    public Object clone() {
        zzku v0 = super.zzhe();
        zzks.zzb(this, ((zzko)v0));
        return v0;
    }

    protected int zzal() {
        int v0;
        int v1 = 0;
        if(this.zzaaf != null) {
            v0 = 0;
            while(v1 < this.zzaaf.size()) {
                v0 += this.zzaaf.zzbv(v1).zzal();
                ++v1;
            }
        }
        else {
            v0 = 0;
        }

        return v0;
    }

    public void zzb(zzkm arg3) {
        if(this.zzaaf == null) {
            return;
        }

        int v0;
        for(v0 = 0; v0 < this.zzaaf.size(); ++v0) {
            this.zzaaf.zzbv(v0).zzb(arg3);
        }
    }

    protected final boolean zzb(zzkl arg4, int arg5) {
        int v0 = arg4.getPosition();
        if(!arg4.zzai(arg5)) {
            return 0;
        }

        int v1 = arg5 >>> 3;
        zzkw v0_1 = new zzkw(arg5, arg4.zzt(v0, arg4.getPosition() - v0));
        zzkr v4 = null;
        if(this.zzaaf == null) {
            this.zzaaf = new zzkq();
        }
        else {
            v4 = this.zzaaf.zzbu(v1);
        }

        if(v4 == null) {
            v4 = new zzkr();
            this.zzaaf.zzb(v1, v4);
        }

        v4.zzb(v0_1);
        return 1;
    }

    public final zzku zzhe() {
        return ((zzku)this).clone();
    }
}

