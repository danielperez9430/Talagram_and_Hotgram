package com.google.android.gms.internal.wearable;

public final class zzg extends zzn {
    public zzh[] zzfy;

    public zzg() {
        super();
        this.zzfy = zzh.zzh();
        this.zzhc = null;
        this.zzhl = -1;
    }

    public final boolean equals(Object arg5) {
        if((((zzg)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzg)) {
            return 0;
        }

        if(!zzr.equals(this.zzfy, ((zzg)arg5).zzfy)) {
            return 0;
        }

        if(this.zzhc != null) {
            if(this.zzhc.isEmpty()) {
            }
            else {
                return this.zzhc.equals(((zzg)arg5).zzhc);
            }
        }

        if(((zzg)arg5).zzhc != null) {
            if(((zzg)arg5).zzhc.isEmpty()) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public final int hashCode() {
        int v0 = ((this.getClass().getName().hashCode() + 527) * 31 + zzr.hashCode(this.zzfy)) * 31;
        int v1 = this.zzhc == null || (this.zzhc.isEmpty()) ? 0 : this.zzhc.hashCode();
        return v0 + v1;
    }

    public static zzg zza(byte[] arg3) {
        return zzt.zza(new zzg(), arg3, 0, arg3.length);
    }

    public final zzt zza(zzk arg5) {
        int v1;
        int v0;
        do {
        label_0:
            v0 = arg5.zzj();
            if(v0 == 0) {
                return this;
            }

            v1 = 10;
            if(v0 == v1) {
                goto label_7;
            }
        }
        while(super.zza(arg5, v0));

        return this;
    label_7:
        v0 = zzw.zzb(arg5, v1);
        v1 = this.zzfy == null ? 0 : this.zzfy.length;
        zzh[] v0_1 = new zzh[v0 + v1];
        if(v1 != 0) {
            System.arraycopy(this.zzfy, 0, v0_1, 0, v1);
            goto label_20;
            return this;
        }

    label_20:
        while(v1 < v0_1.length - 1) {
            v0_1[v1] = new zzh();
            arg5.zza(v0_1[v1]);
            arg5.zzj();
            ++v1;
        }

        v0_1[v1] = new zzh();
        arg5.zza(v0_1[v1]);
        this.zzfy = v0_1;
        goto label_0;
    }

    public final void zza(zzl arg4) {
        if(this.zzfy != null && this.zzfy.length > 0) {
            int v0;
            for(v0 = 0; v0 < this.zzfy.length; ++v0) {
                zzh v1 = this.zzfy[v0];
                if(v1 != null) {
                    arg4.zza(1, ((zzt)v1));
                }
            }
        }

        super.zza(arg4);
    }

    protected final int zzg() {
        int v0 = super.zzg();
        if(this.zzfy != null && this.zzfy.length > 0) {
            int v1;
            for(v1 = 0; v1 < this.zzfy.length; ++v1) {
                zzh v2 = this.zzfy[v1];
                if(v2 != null) {
                    v0 += zzl.zzb(1, ((zzt)v2));
                }
            }
        }

        return v0;
    }
}

