package com.google.android.gms.internal.measurement;

public final class zzgc extends zzza {
    public String value;
    private static volatile zzgc[] zzawk;
    public String zzoj;

    public zzgc() {
        super();
        this.zzoj = null;
        this.value = null;
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object arg5) {
        if((((zzgc)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzgc)) {
            return 0;
        }

        if(this.zzoj == null) {
            if(((zzgc)arg5).zzoj != null) {
                return 0;
            }
        }
        else if(!this.zzoj.equals(((zzgc)arg5).zzoj)) {
            return 0;
        }

        if(this.value == null) {
            if(((zzgc)arg5).value != null) {
                return 0;
            }
        }
        else if(!this.value.equals(((zzgc)arg5).value)) {
            return 0;
        }

        if(this.zzcfc != null) {
            if(this.zzcfc.isEmpty()) {
            }
            else {
                return this.zzcfc.equals(((zzgc)arg5).zzcfc);
            }
        }

        if(((zzgc)arg5).zzcfc != null) {
            if(((zzgc)arg5).zzcfc.isEmpty()) {
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
        int v1 = this.zzoj == null ? 0 : this.zzoj.hashCode();
        v0 = (v0 + v1) * 31;
        v1 = this.value == null ? 0 : this.value.hashCode();
        v0 = (v0 + v1) * 31;
        if(this.zzcfc != null) {
            if(this.zzcfc.isEmpty()) {
            }
            else {
                v2 = this.zzcfc.hashCode();
            }
        }

        return v0 + v2;
    }

    public final zzzg zza(zzyx arg3) {
        while(true) {
            int v0 = arg3.zzug();
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

                this.value = arg3.readString();
                continue;
            }

            this.zzoj = arg3.readString();
        }

        return this;
    }

    public final void zza(zzyy arg3) {
        if(this.zzoj != null) {
            arg3.zzb(1, this.zzoj);
        }

        if(this.value != null) {
            arg3.zzb(2, this.value);
        }

        super.zza(arg3);
    }

    protected final int zzf() {
        int v0 = super.zzf();
        if(this.zzoj != null) {
            v0 += zzyy.zzc(1, this.zzoj);
        }

        if(this.value != null) {
            v0 += zzyy.zzc(2, this.value);
        }

        return v0;
    }

    public static zzgc[] zzmn() {
        if(zzgc.zzawk == null) {
            Object v0 = zzze.zzcfl;
            __monitor_enter(v0);
            try {
                if(zzgc.zzawk == null) {
                    zzgc.zzawk = new zzgc[0];
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
        return zzgc.zzawk;
    }
}

