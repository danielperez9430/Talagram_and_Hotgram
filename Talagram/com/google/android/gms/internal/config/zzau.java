package com.google.android.gms.internal.config;

public final class zzau extends zzbb {
    public int zzbk;
    public boolean zzbl;
    private long zzbm;

    public zzau() {
        super();
        this.zzbk = 0;
        this.zzbl = false;
        this.zzbm = 0;
        this.zzch = null;
        this.zzcq = -1;
    }

    public final boolean equals(Object arg8) {
        if((((zzau)arg8)) == this) {
            return 1;
        }

        if(!(arg8 instanceof zzau)) {
            return 0;
        }

        if(this.zzbk != ((zzau)arg8).zzbk) {
            return 0;
        }

        if(this.zzbl != ((zzau)arg8).zzbl) {
            return 0;
        }

        if(this.zzbm != ((zzau)arg8).zzbm) {
            return 0;
        }

        if(this.zzch != null) {
            if(this.zzch.isEmpty()) {
            }
            else {
                return this.zzch.equals(((zzau)arg8).zzch);
            }
        }

        if(((zzau)arg8).zzch != null) {
            if(((zzau)arg8).zzch.isEmpty()) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public final int hashCode() {
        int v0 = ((this.getClass().getName().hashCode() + 527) * 31 + this.zzbk) * 31;
        int v1 = this.zzbl ? 1231 : 1237;
        v0 = ((v0 + v1) * 31 + (((int)(this.zzbm ^ this.zzbm >>> 32)))) * 31;
        v1 = this.zzch == null || (this.zzch.isEmpty()) ? 0 : this.zzch.hashCode();
        return v0 + v1;
    }

    public final zzbh zza(zzay arg3) {
        while(true) {
            int v0 = arg3.zzx();
            if(v0 == 0) {
                return this;
            }

            if(v0 != 8) {
                if(v0 != 16) {
                    if(v0 != 25) {
                        if(super.zza(arg3, v0)) {
                            continue;
                        }

                        return this;
                    }

                    this.zzbm = arg3.zzz();
                    continue;
                }

                boolean v0_1 = arg3.zzy() != 0 ? true : false;
                this.zzbl = v0_1;
                continue;
            }

            this.zzbk = arg3.zzy();
        }

        return this;
    }

    public final void zza(zzaz arg6) {
        if(this.zzbk != 0) {
            arg6.zzc(1, this.zzbk);
        }

        if(this.zzbl) {
            boolean v0 = this.zzbl;
            arg6.zze(2, 0);
            arg6.zza(((byte)(((int)v0))));
        }

        if(this.zzbm != 0) {
            arg6.zza(3, this.zzbm);
        }

        super.zza(arg6);
    }

    protected final int zzt() {
        int v0 = super.zzt();
        if(this.zzbk != 0) {
            v0 += zzaz.zzd(1, this.zzbk);
        }

        if(this.zzbl) {
            v0 += zzaz.zzl(2) + 1;
        }

        if(this.zzbm != 0) {
            v0 += zzaz.zzl(3) + 8;
        }

        return v0;
    }
}

