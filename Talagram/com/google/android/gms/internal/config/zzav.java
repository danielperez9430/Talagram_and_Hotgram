package com.google.android.gms.internal.config;

public final class zzav extends zzbb {
    public String namespace;
    private static volatile zzav[] zzbn;
    public zzat[] zzbo;

    public zzav() {
        super();
        this.namespace = "";
        this.zzbo = zzat.zzu();
        this.zzch = null;
        this.zzcq = -1;
    }

    public final boolean equals(Object arg5) {
        if((((zzav)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzav)) {
            return 0;
        }

        if(this.namespace == null) {
            if(((zzav)arg5).namespace != null) {
                return 0;
            }
        }
        else if(!this.namespace.equals(((zzav)arg5).namespace)) {
            return 0;
        }

        if(!zzbf.equals(this.zzbo, ((zzav)arg5).zzbo)) {
            return 0;
        }

        if(this.zzch != null) {
            if(this.zzch.isEmpty()) {
            }
            else {
                return this.zzch.equals(((zzav)arg5).zzch);
            }
        }

        if(((zzav)arg5).zzch != null) {
            if(((zzav)arg5).zzch.isEmpty()) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public final int hashCode() {
        int v0 = (this.getClass().getName().hashCode() + 527) * 31;
        int v2 = 0;
        int v1 = this.namespace == null ? 0 : this.namespace.hashCode();
        v0 = ((v0 + v1) * 31 + zzbf.hashCode(this.zzbo)) * 31;
        if(this.zzch != null) {
            if(this.zzch.isEmpty()) {
            }
            else {
                v2 = this.zzch.hashCode();
            }
        }

        return v0 + v2;
    }

    public final zzbh zza(zzay arg5) {
        zzat[] v0_1;
        int v1;
        while(true) {
        label_0:
            int v0 = arg5.zzx();
            if(v0 == 0) {
                return this;
            }

            if(v0 == 10) {
                goto label_40;
            }

            v1 = 18;
            if(v0 != v1) {
                if(super.zza(arg5, v0)) {
                    continue;
                }

                return this;
            }

            v0 = zzbk.zzb(arg5, v1);
            v1 = this.zzbo == null ? 0 : this.zzbo.length;
            v0_1 = new zzat[v0 + v1];
            if(v1 != 0) {
                System.arraycopy(this.zzbo, 0, v0_1, 0, v1);
                goto label_22;
            label_40:
                this.namespace = arg5.readString();
                continue;
            }

            goto label_22;
        }

        return this;
    label_22:
        while(v1 < v0_1.length - 1) {
            v0_1[v1] = new zzat();
            arg5.zza(v0_1[v1]);
            arg5.zzx();
            ++v1;
        }

        v0_1[v1] = new zzat();
        arg5.zza(v0_1[v1]);
        this.zzbo = v0_1;
        goto label_0;
    }

    public final void zza(zzaz arg4) {
        if(this.namespace != null && !this.namespace.equals("")) {
            arg4.zza(1, this.namespace);
        }

        if(this.zzbo != null && this.zzbo.length > 0) {
            int v0;
            for(v0 = 0; v0 < this.zzbo.length; ++v0) {
                zzat v1 = this.zzbo[v0];
                if(v1 != null) {
                    arg4.zza(2, ((zzbh)v1));
                }
            }
        }

        super.zza(arg4);
    }

    protected final int zzt() {
        int v0 = super.zzt();
        if(this.namespace != null && !this.namespace.equals("")) {
            v0 += zzaz.zzb(1, this.namespace);
        }

        if(this.zzbo != null && this.zzbo.length > 0) {
            int v1;
            for(v1 = 0; v1 < this.zzbo.length; ++v1) {
                zzat v2 = this.zzbo[v1];
                if(v2 != null) {
                    v0 += zzaz.zzb(2, ((zzbh)v2));
                }
            }
        }

        return v0;
    }

    public static zzav[] zzv() {
        if(zzav.zzbn == null) {
            Object v0 = zzbf.zzcp;
            __monitor_enter(v0);
            try {
                if(zzav.zzbn == null) {
                    zzav.zzbn = new zzav[0];
                }

                __monitor_exit(v0);
                goto label_14;
            label_12:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_12;
            }

            throw v1;
        }

    label_14:
        return zzav.zzbn;
    }
}

