package com.google.android.gms.internal.places;

public final class zzdq extends zzko {
    public String[] zzhw;
    public int[] zzhx;
    public byte[][] zzhy;

    public zzdq() {
        super();
        this.zzhw = zzkx.zzaaz;
        this.zzhx = zzkx.zzaau;
        this.zzhy = zzkx.zzaba;
        this.zzaaf = null;
        this.zzaap = -1;
    }

    public final boolean equals(Object arg5) {
        if((((zzdq)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzdq)) {
            return 0;
        }

        if(!zzks.equals(this.zzhw, ((zzdq)arg5).zzhw)) {
            return 0;
        }

        if(!zzks.equals(this.zzhx, ((zzdq)arg5).zzhx)) {
            return 0;
        }

        if(!zzks.zzb(this.zzhy, ((zzdq)arg5).zzhy)) {
            return 0;
        }

        if(this.zzaaf != null) {
            if(this.zzaaf.isEmpty()) {
            }
            else {
                return this.zzaaf.equals(((zzdq)arg5).zzaaf);
            }
        }

        if(((zzdq)arg5).zzaaf != null) {
            if(((zzdq)arg5).zzaaf.isEmpty()) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public final int hashCode() {
        int v0 = ((((this.getClass().getName().hashCode() + 527) * 31 + zzks.hashCode(this.zzhw)) * 31 + zzks.hashCode(this.zzhx)) * 31 + zzks.zzb(this.zzhy)) * 31;
        int v1 = this.zzaaf == null || (this.zzaaf.isEmpty()) ? 0 : this.zzaaf.hashCode();
        return v0 + v1;
    }

    protected final int zzal() {
        int v3;
        int v1;
        int v0 = super.zzal();
        int v2 = 0;
        if(this.zzhw != null && this.zzhw.length > 0) {
            v1 = 0;
            v3 = 0;
            int v4 = 0;
            while(v1 < this.zzhw.length) {
                String v5 = this.zzhw[v1];
                if(v5 != null) {
                    ++v4;
                    v3 += zzkm.zzl(v5);
                }

                ++v1;
            }

            v0 = v0 + v3 + v4;
        }

        if(this.zzhx != null && this.zzhx.length > 0) {
            v1 = 0;
            v3 = 0;
            while(v1 < this.zzhx.length) {
                v3 += zzkm.zzat(this.zzhx[v1]);
                ++v1;
            }

            v0 = v0 + v3 + this.zzhx.length;
        }

        if(this.zzhy != null && this.zzhy.length > 0) {
            v1 = 0;
            v3 = 0;
            while(v2 < this.zzhy.length) {
                byte[] v4_1 = this.zzhy[v2];
                if(v4_1 != null) {
                    ++v3;
                    v1 += zzkm.zzj(v4_1);
                }

                ++v2;
            }

            v0 = v0 + v1 + v3;
        }

        return v0;
    }

    public static zzdq zzb(byte[] arg1) {
        return zzku.zzb(new zzdq(), arg1);
    }

    public final zzku zzb(zzkl arg6) {
        int v1;
        int v0;
        do {
        label_0:
            v0 = arg6.zzcj();
            if(v0 == 0) {
                return this;
            }

            v1 = 10;
            if(v0 == v1) {
                goto label_92;
            }

            v1 = 16;
            if(v0 == v1) {
                goto label_68;
            }

            if(v0 == 18) {
                goto label_38;
            }

            v1 = 26;
            if(v0 == v1) {
                goto label_14;
            }
        }
        while(super.zzb(arg6, v0));

        return this;
    label_14:
        v0 = zzkx.zzc(arg6, v1);
        v1 = this.zzhy == null ? 0 : this.zzhy.length;
        byte[][] v0_1 = new byte[v0 + v1][];
        if(v1 != 0) {
            System.arraycopy(this.zzhy, 0, v0_1, 0, v1);
            goto label_26;
        label_38:
            v0 = arg6.zzak(arg6.zzcm());
            v1 = arg6.getPosition();
            int v3 = 0;
            goto label_42;
        label_68:
            v0 = zzkx.zzc(arg6, v1);
            v1 = this.zzhx == null ? 0 : this.zzhx.length;
            int[] v0_2 = new int[v0 + v1];
            if(v1 != 0) {
                System.arraycopy(this.zzhx, 0, v0_2, 0, v1);
                goto label_80;
            label_92:
                v0 = zzkx.zzc(arg6, v1);
                v1 = this.zzhw == null ? 0 : this.zzhw.length;
                String[] v0_3 = new String[v0 + v1];
                if(v1 != 0) {
                    System.arraycopy(this.zzhw, 0, v0_3, 0, v1);
                    goto label_104;
                    return this;
                }

            label_104:
                while(v1 < v0_3.length - 1) {
                    v0_3[v1] = arg6.readString();
                    arg6.zzcj();
                    ++v1;
                }

                v0_3[v1] = arg6.readString();
                this.zzhw = v0_3;
                goto label_0;
            }

        label_80:
            while(v1 < v0_2.length - 1) {
                v0_2[v1] = arg6.zzcm();
                arg6.zzcj();
                ++v1;
            }

            v0_2[v1] = arg6.zzcm();
            this.zzhx = v0_2;
            goto label_0;
        label_42:
            while(arg6.zzhb() > 0) {
                arg6.zzcm();
                ++v3;
            }

            arg6.zzbr(v1);
            v1 = this.zzhx == null ? 0 : this.zzhx.length;
            int[] v3_1 = new int[v3 + v1];
            if(v1 != 0) {
                System.arraycopy(this.zzhx, 0, v3_1, 0, v1);
            }

            while(v1 < v3_1.length) {
                v3_1[v1] = arg6.zzcm();
                ++v1;
            }

            this.zzhx = v3_1;
            arg6.zzal(v0);
            goto label_0;
        }

    label_26:
        while(v1 < v0_1.length - 1) {
            v0_1[v1] = arg6.readBytes();
            arg6.zzcj();
            ++v1;
        }

        v0_1[v1] = arg6.readBytes();
        this.zzhy = v0_1;
        goto label_0;
    }

    public final void zzb(zzkm arg5) {
        int v1 = 0;
        if(this.zzhw != null && this.zzhw.length > 0) {
            int v0;
            for(v0 = 0; v0 < this.zzhw.length; ++v0) {
                String v2 = this.zzhw[v0];
                if(v2 != null) {
                    arg5.zzb(1, v2);
                }
            }
        }

        if(this.zzhx != null && this.zzhx.length > 0) {
            for(v0 = 0; v0 < this.zzhx.length; ++v0) {
                arg5.zze(2, this.zzhx[v0]);
            }
        }

        if(this.zzhy != null && this.zzhy.length > 0) {
            while(v1 < this.zzhy.length) {
                byte[] v0_1 = this.zzhy[v1];
                if(v0_1 != null) {
                    arg5.zzb(3, v0_1);
                }

                ++v1;
            }
        }

        super.zzb(arg5);
    }
}

