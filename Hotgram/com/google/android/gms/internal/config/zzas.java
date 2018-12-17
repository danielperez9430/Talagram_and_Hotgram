package com.google.android.gms.internal.config;

public final class zzas extends zzbb {
    public long timestamp;
    public zzav[] zzbf;
    public byte[][] zzbg;

    public zzas() {
        super();
        this.zzbf = zzav.zzv();
        this.timestamp = 0;
        this.zzbg = zzbk.zzdc;
        this.zzch = null;
        this.zzcq = -1;
    }

    public final boolean equals(Object arg8) {
        if((((zzas)arg8)) == this) {
            return 1;
        }

        if(!(arg8 instanceof zzas)) {
            return 0;
        }

        if(!zzbf.equals(this.zzbf, ((zzas)arg8).zzbf)) {
            return 0;
        }

        if(this.timestamp != ((zzas)arg8).timestamp) {
            return 0;
        }

        if(!zzbf.zza(this.zzbg, ((zzas)arg8).zzbg)) {
            return 0;
        }

        if(this.zzch != null) {
            if(this.zzch.isEmpty()) {
            }
            else {
                return this.zzch.equals(((zzas)arg8).zzch);
            }
        }

        if(((zzas)arg8).zzch != null) {
            if(((zzas)arg8).zzch.isEmpty()) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public final int hashCode() {
        int v0 = ((((this.getClass().getName().hashCode() + 527) * 31 + zzbf.hashCode(this.zzbf)) * 31 + (((int)(this.timestamp ^ this.timestamp >>> 32)))) * 31 + zzbf.zza(this.zzbg)) * 31;
        int v1 = this.zzch == null || (this.zzch.isEmpty()) ? 0 : this.zzch.hashCode();
        return v0 + v1;
    }

    public final zzbh zza(zzay arg5) {
        byte[][] v0_1;
        int v1;
        int v0;
        while(true) {
        label_0:
            v0 = arg5.zzx();
            if(v0 == 0) {
                return this;
            }

            v1 = 10;
            if(v0 == v1) {
                break;
            }

            if(v0 == 17) {
                goto label_36;
            }

            v1 = 26;
            if(v0 != v1) {
                if(super.zza(arg5, v0)) {
                    continue;
                }

                return this;
            }

            v0 = zzbk.zzb(arg5, v1);
            v1 = this.zzbg == null ? 0 : this.zzbg.length;
            v0_1 = new byte[v0 + v1][];
            if(v1 != 0) {
                System.arraycopy(this.zzbg, 0, v0_1, 0, v1);
                goto label_24;
            label_36:
                this.timestamp = arg5.zzz();
                continue;
            }

            goto label_24;
        }

        v0 = zzbk.zzb(arg5, v1);
        v1 = this.zzbf == null ? 0 : this.zzbf.length;
        zzav[] v0_2 = new zzav[v0 + v1];
        if(v1 != 0) {
            System.arraycopy(this.zzbf, 0, v0_2, 0, v1);
            goto label_51;
            return this;
        }

    label_51:
        while(v1 < v0_2.length - 1) {
            v0_2[v1] = new zzav();
            arg5.zza(v0_2[v1]);
            arg5.zzx();
            ++v1;
        }

        v0_2[v1] = new zzav();
        arg5.zza(v0_2[v1]);
        this.zzbf = v0_2;
        goto label_0;
    label_24:
        while(v1 < v0_1.length - 1) {
            v0_1[v1] = arg5.readBytes();
            arg5.zzx();
            ++v1;
        }

        v0_1[v1] = arg5.readBytes();
        this.zzbg = v0_1;
        goto label_0;
    }

    public final void zza(zzaz arg7) {
        int v1 = 0;
        if(this.zzbf != null && this.zzbf.length > 0) {
            int v0;
            for(v0 = 0; v0 < this.zzbf.length; ++v0) {
                zzav v2 = this.zzbf[v0];
                if(v2 != null) {
                    arg7.zza(1, ((zzbh)v2));
                }
            }
        }

        if(this.timestamp != 0) {
            arg7.zza(2, this.timestamp);
        }

        if(this.zzbg != null && this.zzbg.length > 0) {
            while(v1 < this.zzbg.length) {
                byte[] v0_1 = this.zzbg[v1];
                if(v0_1 != null) {
                    arg7.zza(3, v0_1);
                }

                ++v1;
            }
        }

        super.zza(arg7);
    }

    protected final int zzt() {
        int v1;
        int v0 = super.zzt();
        int v2 = 0;
        if(this.zzbf != null && this.zzbf.length > 0) {
            v1 = v0;
            for(v0 = 0; v0 < this.zzbf.length; ++v0) {
                zzav v4 = this.zzbf[v0];
                if(v4 != null) {
                    v1 += zzaz.zzb(1, ((zzbh)v4));
                }
            }

            v0 = v1;
        }

        if(this.timestamp != 0) {
            v0 += zzaz.zzl(2) + 8;
        }

        if(this.zzbg != null && this.zzbg.length > 0) {
            v1 = 0;
            int v4_1 = 0;
            while(v2 < this.zzbg.length) {
                byte[] v5 = this.zzbg[v2];
                if(v5 != null) {
                    ++v4_1;
                    v1 += zzaz.zzb(v5);
                }

                ++v2;
            }

            v0 = v0 + v1 + v4_1;
        }

        return v0;
    }
}

