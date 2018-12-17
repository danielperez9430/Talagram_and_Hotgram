package com.google.android.gms.internal.wearable;

public final class zzh extends zzn {
    public String name;
    private static volatile zzh[] zzfz;
    public zzi zzga;

    public zzh() {
        super();
        this.name = "";
        this.zzga = null;
        this.zzhc = null;
        this.zzhl = -1;
    }

    public final boolean equals(Object arg5) {
        if((((zzh)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzh)) {
            return 0;
        }

        if(this.name == null) {
            if(((zzh)arg5).name != null) {
                return 0;
            }
        }
        else if(!this.name.equals(((zzh)arg5).name)) {
            return 0;
        }

        if(this.zzga == null) {
            if(((zzh)arg5).zzga != null) {
                return 0;
            }
        }
        else if(!this.zzga.equals(((zzh)arg5).zzga)) {
            return 0;
        }

        if(this.zzhc != null) {
            if(this.zzhc.isEmpty()) {
            }
            else {
                return this.zzhc.equals(((zzh)arg5).zzhc);
            }
        }

        if(((zzh)arg5).zzhc != null) {
            if(((zzh)arg5).zzhc.isEmpty()) {
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
        int v1 = this.name == null ? 0 : this.name.hashCode();
        v0 += v1;
        zzi v1_1 = this.zzga;
        v0 *= 31;
        v1 = v1_1 == null ? 0 : v1_1.hashCode();
        v0 = (v0 + v1) * 31;
        if(this.zzhc != null) {
            if(this.zzhc.isEmpty()) {
            }
            else {
                v2 = this.zzhc.hashCode();
            }
        }

        return v0 + v2;
    }

    public final zzt zza(zzk arg3) {
        while(true) {
            int v0 = arg3.zzj();
            if(v0 == 0) {
                return this;
            }

            if(v0 != 10) {
                if(v0 != 18) {
                    if(super.zza(arg3, v0)) {
                        continue;
                    }

                    return this;
                }

                if(this.zzga == null) {
                    this.zzga = new zzi();
                }

                arg3.zza(this.zzga);
                continue;
            }

            this.name = arg3.readString();
        }

        return this;
    }

    public final void zza(zzl arg3) {
        arg3.zza(1, this.name);
        if(this.zzga != null) {
            arg3.zza(2, this.zzga);
        }

        super.zza(arg3);
    }

    protected final int zzg() {
        int v0 = super.zzg() + zzl.zzb(1, this.name);
        if(this.zzga != null) {
            v0 += zzl.zzb(2, this.zzga);
        }

        return v0;
    }

    public static zzh[] zzh() {
        if(zzh.zzfz == null) {
            Object v0 = zzr.zzhk;
            __monitor_enter(v0);
            try {
                if(zzh.zzfz == null) {
                    zzh.zzfz = new zzh[0];
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
        return zzh.zzfz;
    }
}

