package com.google.android.gms.internal.wearable;

public final class zzi extends zzn {
    public int type;
    private static volatile zzi[] zzgb;
    public zzj zzgc;

    public zzi() {
        super();
        this.type = 1;
        this.zzgc = null;
        this.zzhc = null;
        this.zzhl = -1;
    }

    public final boolean equals(Object arg5) {
        if((((zzi)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzi)) {
            return 0;
        }

        if(this.type != ((zzi)arg5).type) {
            return 0;
        }

        if(this.zzgc == null) {
            if(((zzi)arg5).zzgc != null) {
                return 0;
            }
        }
        else if(!this.zzgc.equals(((zzi)arg5).zzgc)) {
            return 0;
        }

        if(this.zzhc != null) {
            if(this.zzhc.isEmpty()) {
            }
            else {
                return this.zzhc.equals(((zzi)arg5).zzhc);
            }
        }

        if(((zzi)arg5).zzhc != null) {
            if(((zzi)arg5).zzhc.isEmpty()) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public final int hashCode() {
        int v0 = (this.getClass().getName().hashCode() + 527) * 31 + this.type;
        zzj v1 = this.zzgc;
        v0 *= 31;
        int v2 = 0;
        int v1_1 = v1 == null ? 0 : v1.hashCode();
        v0 = (v0 + v1_1) * 31;
        if(this.zzhc != null) {
            if(this.zzhc.isEmpty()) {
            }
            else {
                v2 = this.zzhc.hashCode();
            }
        }

        return v0 + v2;
    }

    public final zzt zza(zzk arg1) {
        return this.zzb(arg1);
    }

    public final void zza(zzl arg3) {
        arg3.zzd(1, this.type);
        if(this.zzgc != null) {
            arg3.zza(2, this.zzgc);
        }

        super.zza(arg3);
    }

    private final zzi zzb(zzk arg7) {
        while(true) {
            int v0 = arg7.zzj();
            if(v0 == 0) {
                return this;
            }

            if(v0 != 8) {
                if(v0 != 18) {
                    if(super.zza(arg7, v0)) {
                        continue;
                    }

                    return this;
                }

                if(this.zzgc == null) {
                    this.zzgc = new zzj();
                }

                arg7.zza(this.zzgc);
                continue;
            }

            int v1 = arg7.getPosition();
            try {
                int v2 = arg7.zzk();
                if(v2 > 0 && v2 <= 15) {
                    this.type = v2;
                    continue;
                }

                StringBuilder v5 = new StringBuilder(36);
                v5.append(v2);
                v5.append(" is not a valid enum Type");
                throw new IllegalArgumentException(v5.toString());
            }
            catch(IllegalArgumentException ) {
                arg7.zzg(v1);
                ((zzn)this).zza(arg7, v0);
                continue;
            }
        }

        return this;
    }

    protected final int zzg() {
        int v0 = super.zzg() + zzl.zze(1, this.type);
        if(this.zzgc != null) {
            v0 += zzl.zzb(2, this.zzgc);
        }

        return v0;
    }

    public static zzi[] zzi() {
        if(zzi.zzgb == null) {
            Object v0 = zzr.zzhk;
            __monitor_enter(v0);
            try {
                if(zzi.zzgb == null) {
                    zzi.zzgb = new zzi[0];
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
        return zzi.zzgb;
    }
}

