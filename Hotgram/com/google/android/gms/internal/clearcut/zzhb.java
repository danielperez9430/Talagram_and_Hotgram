package com.google.android.gms.internal.clearcut;

public final class zzhb extends zzfu implements Cloneable {
    private String value;
    private static volatile zzhb[] zzbkd;
    private String zzbke;

    public zzhb() {
        super();
        this.zzbke = "";
        this.value = "";
        this.zzrj = null;
        this.zzrs = -1;
    }

    public final Object clone() {
        return this.zzgf();
    }

    public final boolean equals(Object arg5) {
        if((((zzhb)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzhb)) {
            return 0;
        }

        if(this.zzbke == null) {
            if(((zzhb)arg5).zzbke != null) {
                return 0;
            }
        }
        else if(!this.zzbke.equals(((zzhb)arg5).zzbke)) {
            return 0;
        }

        if(this.value == null) {
            if(((zzhb)arg5).value != null) {
                return 0;
            }
        }
        else if(!this.value.equals(((zzhb)arg5).value)) {
            return 0;
        }

        if(this.zzrj != null) {
            if(this.zzrj.isEmpty()) {
            }
            else {
                return this.zzrj.equals(((zzhb)arg5).zzrj);
            }
        }

        if(((zzhb)arg5).zzrj != null) {
            if(((zzhb)arg5).zzrj.isEmpty()) {
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
        int v1 = this.zzbke == null ? 0 : this.zzbke.hashCode();
        v0 = (v0 + v1) * 31;
        v1 = this.value == null ? 0 : this.value.hashCode();
        v0 = (v0 + v1) * 31;
        if(this.zzrj != null) {
            if(this.zzrj.isEmpty()) {
            }
            else {
                v2 = this.zzrj.hashCode();
            }
        }

        return v0 + v2;
    }

    public final void zza(zzfs arg3) {
        if(this.zzbke != null && !this.zzbke.equals("")) {
            arg3.zza(1, this.zzbke);
        }

        if(this.value != null && !this.value.equals("")) {
            arg3.zza(2, this.value);
        }

        super.zza(arg3);
    }

    protected final int zzen() {
        int v0 = super.zzen();
        if(this.zzbke != null && !this.zzbke.equals("")) {
            v0 += zzfs.zzb(1, this.zzbke);
        }

        if(this.value != null && !this.value.equals("")) {
            v0 += zzfs.zzb(2, this.value);
        }

        return v0;
    }

    public final zzfu zzeo() {
        return ((zzfz)this).clone();
    }

    public final zzfz zzep() {
        return ((zzfz)this).clone();
    }

    public static zzhb[] zzge() {
        if(zzhb.zzbkd == null) {
            Object v0 = zzfy.zzrr;
            __monitor_enter(v0);
            try {
                if(zzhb.zzbkd == null) {
                    zzhb.zzbkd = new zzhb[0];
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
        return zzhb.zzbkd;
    }

    private final zzhb zzgf() {
        try {
            return super.zzeo();
        }
        catch(CloneNotSupportedException v0) {
            throw new AssertionError(v0);
        }
    }
}

