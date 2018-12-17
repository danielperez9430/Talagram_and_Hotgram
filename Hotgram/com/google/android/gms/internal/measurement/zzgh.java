package com.google.android.gms.internal.measurement;

public final class zzgh extends zzza {
    public zzgi[] zzawy;

    public zzgh() {
        super();
        this.zzawy = zzgi.zzms();
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object arg5) {
        if((((zzgh)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzgh)) {
            return 0;
        }

        if(!zzze.equals(this.zzawy, ((zzgh)arg5).zzawy)) {
            return 0;
        }

        if(this.zzcfc != null) {
            if(this.zzcfc.isEmpty()) {
            }
            else {
                return this.zzcfc.equals(((zzgh)arg5).zzcfc);
            }
        }

        if(((zzgh)arg5).zzcfc != null) {
            if(((zzgh)arg5).zzcfc.isEmpty()) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public final int hashCode() {
        int v0 = ((this.getClass().getName().hashCode() + 527) * 31 + zzze.hashCode(this.zzawy)) * 31;
        int v1 = this.zzcfc == null || (this.zzcfc.isEmpty()) ? 0 : this.zzcfc.hashCode();
        return v0 + v1;
    }

    public final zzzg zza(zzyx arg5) {
        int v1;
        int v0;
        do {
        label_0:
            v0 = arg5.zzug();
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
        v0 = zzzj.zzb(arg5, v1);
        v1 = this.zzawy == null ? 0 : this.zzawy.length;
        zzgi[] v0_1 = new zzgi[v0 + v1];
        if(v1 != 0) {
            System.arraycopy(this.zzawy, 0, v0_1, 0, v1);
            goto label_20;
            return this;
        }

    label_20:
        while(v1 < v0_1.length - 1) {
            v0_1[v1] = new zzgi();
            arg5.zza(v0_1[v1]);
            arg5.zzug();
            ++v1;
        }

        v0_1[v1] = new zzgi();
        arg5.zza(v0_1[v1]);
        this.zzawy = v0_1;
        goto label_0;
    }

    public final void zza(zzyy arg4) {
        if(this.zzawy != null && this.zzawy.length > 0) {
            int v0;
            for(v0 = 0; v0 < this.zzawy.length; ++v0) {
                zzgi v1 = this.zzawy[v0];
                if(v1 != null) {
                    arg4.zza(1, ((zzzg)v1));
                }
            }
        }

        super.zza(arg4);
    }

    protected final int zzf() {
        int v0 = super.zzf();
        if(this.zzawy != null && this.zzawy.length > 0) {
            int v1;
            for(v1 = 0; v1 < this.zzawy.length; ++v1) {
                zzgi v2 = this.zzawy[v1];
                if(v2 != null) {
                    v0 += zzyy.zzb(1, ((zzzg)v2));
                }
            }
        }

        return v0;
    }
}

