package com.google.android.gms.internal.config;

public final class zzax extends zzbb {
    public String namespace;
    public int resourceId;
    private static volatile zzax[] zzbu;
    public long zzbv;

    public zzax() {
        super();
        this.resourceId = 0;
        this.zzbv = 0;
        this.namespace = "";
        this.zzch = null;
        this.zzcq = -1;
    }

    public final boolean equals(Object arg8) {
        if((((zzax)arg8)) == this) {
            return 1;
        }

        if(!(arg8 instanceof zzax)) {
            return 0;
        }

        if(this.resourceId != ((zzax)arg8).resourceId) {
            return 0;
        }

        if(this.zzbv != ((zzax)arg8).zzbv) {
            return 0;
        }

        if(this.namespace == null) {
            if(((zzax)arg8).namespace != null) {
                return 0;
            }
        }
        else if(!this.namespace.equals(((zzax)arg8).namespace)) {
            return 0;
        }

        if(this.zzch != null) {
            if(this.zzch.isEmpty()) {
            }
            else {
                return this.zzch.equals(((zzax)arg8).zzch);
            }
        }

        if(((zzax)arg8).zzch != null) {
            if(((zzax)arg8).zzch.isEmpty()) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public final int hashCode() {
        int v0 = (((this.getClass().getName().hashCode() + 527) * 31 + this.resourceId) * 31 + (((int)(this.zzbv ^ this.zzbv >>> 32)))) * 31;
        int v2 = 0;
        int v1 = this.namespace == null ? 0 : this.namespace.hashCode();
        v0 = (v0 + v1) * 31;
        if(this.zzch != null) {
            if(this.zzch.isEmpty()) {
            }
            else {
                v2 = this.zzch.hashCode();
            }
        }

        return v0 + v2;
    }

    public final zzbh zza(zzay arg3) {
        while(true) {
            int v0 = arg3.zzx();
            if(v0 == 0) {
                return this;
            }

            if(v0 != 8) {
                if(v0 != 17) {
                    if(v0 != 26) {
                        if(super.zza(arg3, v0)) {
                            continue;
                        }

                        return this;
                    }

                    this.namespace = arg3.readString();
                    continue;
                }

                this.zzbv = arg3.zzz();
                continue;
            }

            this.resourceId = arg3.zzy();
        }

        return this;
    }

    public final void zza(zzaz arg6) {
        if(this.resourceId != 0) {
            arg6.zzc(1, this.resourceId);
        }

        if(this.zzbv != 0) {
            arg6.zza(2, this.zzbv);
        }

        if(this.namespace != null && !this.namespace.equals("")) {
            arg6.zza(3, this.namespace);
        }

        super.zza(arg6);
    }

    protected final int zzt() {
        int v0 = super.zzt();
        if(this.resourceId != 0) {
            v0 += zzaz.zzd(1, this.resourceId);
        }

        if(this.zzbv != 0) {
            v0 += zzaz.zzl(2) + 8;
        }

        if(this.namespace != null && !this.namespace.equals("")) {
            v0 += zzaz.zzb(3, this.namespace);
        }

        return v0;
    }

    public static zzax[] zzw() {
        if(zzax.zzbu == null) {
            Object v0 = zzbf.zzcp;
            __monitor_enter(v0);
            try {
                if(zzax.zzbu == null) {
                    zzax.zzbu = new zzax[0];
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
        return zzax.zzbu;
    }
}

