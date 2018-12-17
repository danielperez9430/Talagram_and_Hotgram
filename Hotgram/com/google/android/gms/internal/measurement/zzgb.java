package com.google.android.gms.internal.measurement;

public final class zzgb extends zzza {
    public String zzafx;
    public Long zzawe;
    private Integer zzawf;
    public zzgc[] zzawg;
    public zzga[] zzawh;
    public zzfu[] zzawi;
    private String zzawj;

    public zzgb() {
        super();
        this.zzawe = null;
        this.zzafx = null;
        this.zzawf = null;
        this.zzawg = zzgc.zzmn();
        this.zzawh = zzga.zzmm();
        this.zzawi = zzfu.zzmi();
        this.zzawj = null;
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object arg5) {
        if((((zzgb)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzgb)) {
            return 0;
        }

        if(this.zzawe == null) {
            if(((zzgb)arg5).zzawe != null) {
                return 0;
            }
        }
        else if(!this.zzawe.equals(((zzgb)arg5).zzawe)) {
            return 0;
        }

        if(this.zzafx == null) {
            if(((zzgb)arg5).zzafx != null) {
                return 0;
            }
        }
        else if(!this.zzafx.equals(((zzgb)arg5).zzafx)) {
            return 0;
        }

        if(this.zzawf == null) {
            if(((zzgb)arg5).zzawf != null) {
                return 0;
            }
        }
        else if(!this.zzawf.equals(((zzgb)arg5).zzawf)) {
            return 0;
        }

        if(!zzze.equals(this.zzawg, ((zzgb)arg5).zzawg)) {
            return 0;
        }

        if(!zzze.equals(this.zzawh, ((zzgb)arg5).zzawh)) {
            return 0;
        }

        if(!zzze.equals(this.zzawi, ((zzgb)arg5).zzawi)) {
            return 0;
        }

        if(this.zzawj == null) {
            if(((zzgb)arg5).zzawj != null) {
                return 0;
            }
        }
        else if(!this.zzawj.equals(((zzgb)arg5).zzawj)) {
            return 0;
        }

        if(this.zzcfc != null) {
            if(this.zzcfc.isEmpty()) {
            }
            else {
                return this.zzcfc.equals(((zzgb)arg5).zzcfc);
            }
        }

        if(((zzgb)arg5).zzcfc != null) {
            if(((zzgb)arg5).zzcfc.isEmpty()) {
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
        int v1 = this.zzawe == null ? 0 : this.zzawe.hashCode();
        v0 = (v0 + v1) * 31;
        v1 = this.zzafx == null ? 0 : this.zzafx.hashCode();
        v0 = (v0 + v1) * 31;
        v1 = this.zzawf == null ? 0 : this.zzawf.hashCode();
        v0 = ((((v0 + v1) * 31 + zzze.hashCode(this.zzawg)) * 31 + zzze.hashCode(this.zzawh)) * 31 + zzze.hashCode(this.zzawi)) * 31;
        v1 = this.zzawj == null ? 0 : this.zzawj.hashCode();
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

    public final zzzg zza(zzyx arg5) {
        zzgc[] v0_3;
        zzga[] v0_2;
        zzfu[] v0_1;
        int v1;
        while(true) {
        label_0:
            int v0 = arg5.zzug();
            if(v0 == 0) {
                return this;
            }

            if(v0 == 8) {
                goto label_120;
            }

            if(v0 == 18) {
                goto label_117;
            }

            if(v0 == 24) {
                goto label_113;
            }

            v1 = 34;
            if(v0 == v1) {
                goto label_83;
            }

            v1 = 42;
            if(v0 == v1) {
                goto label_53;
            }

            v1 = 50;
            if(v0 != v1) {
                if(v0 != 58) {
                    if(super.zza(arg5, v0)) {
                        continue;
                    }

                    return this;
                }

                this.zzawj = arg5.readString();
                continue;
            }

            v0 = zzzj.zzb(arg5, v1);
            v1 = this.zzawi == null ? 0 : this.zzawi.length;
            v0_1 = new zzfu[v0 + v1];
            if(v1 != 0) {
                System.arraycopy(this.zzawi, 0, v0_1, 0, v1);
                goto label_35;
            label_53:
                v0 = zzzj.zzb(arg5, v1);
                v1 = this.zzawh == null ? 0 : this.zzawh.length;
                v0_2 = new zzga[v0 + v1];
                if(v1 != 0) {
                    System.arraycopy(this.zzawh, 0, v0_2, 0, v1);
                    goto label_65;
                label_83:
                    v0 = zzzj.zzb(arg5, v1);
                    v1 = this.zzawg == null ? 0 : this.zzawg.length;
                    v0_3 = new zzgc[v0 + v1];
                    if(v1 != 0) {
                        System.arraycopy(this.zzawg, 0, v0_3, 0, v1);
                        goto label_95;
                    label_113:
                        this.zzawf = Integer.valueOf(arg5.zzuy());
                        continue;
                    label_117:
                        this.zzafx = arg5.readString();
                        continue;
                    label_120:
                        this.zzawe = Long.valueOf(arg5.zzuz());
                        continue;
                    }

                    goto label_95;
                }

                goto label_65;
            }

            goto label_35;
        }

        return this;
    label_95:
        while(v1 < v0_3.length - 1) {
            v0_3[v1] = new zzgc();
            arg5.zza(v0_3[v1]);
            arg5.zzug();
            ++v1;
        }

        v0_3[v1] = new zzgc();
        arg5.zza(v0_3[v1]);
        this.zzawg = v0_3;
        goto label_0;
    label_65:
        while(v1 < v0_2.length - 1) {
            v0_2[v1] = new zzga();
            arg5.zza(v0_2[v1]);
            arg5.zzug();
            ++v1;
        }

        v0_2[v1] = new zzga();
        arg5.zza(v0_2[v1]);
        this.zzawh = v0_2;
        goto label_0;
    label_35:
        while(v1 < v0_1.length - 1) {
            v0_1[v1] = new zzfu();
            arg5.zza(v0_1[v1]);
            arg5.zzug();
            ++v1;
        }

        v0_1[v1] = new zzfu();
        arg5.zza(v0_1[v1]);
        this.zzawi = v0_1;
        goto label_0;
    }

    public final void zza(zzyy arg5) {
        if(this.zzawe != null) {
            arg5.zzi(1, this.zzawe.longValue());
        }

        if(this.zzafx != null) {
            arg5.zzb(2, this.zzafx);
        }

        if(this.zzawf != null) {
            arg5.zzd(3, this.zzawf.intValue());
        }

        int v1 = 0;
        if(this.zzawg != null && this.zzawg.length > 0) {
            int v0;
            for(v0 = 0; v0 < this.zzawg.length; ++v0) {
                zzgc v2 = this.zzawg[v0];
                if(v2 != null) {
                    arg5.zza(4, ((zzzg)v2));
                }
            }
        }

        if(this.zzawh != null && this.zzawh.length > 0) {
            for(v0 = 0; v0 < this.zzawh.length; ++v0) {
                zzga v2_1 = this.zzawh[v0];
                if(v2_1 != null) {
                    arg5.zza(5, ((zzzg)v2_1));
                }
            }
        }

        if(this.zzawi != null && this.zzawi.length > 0) {
            while(v1 < this.zzawi.length) {
                zzfu v0_1 = this.zzawi[v1];
                if(v0_1 != null) {
                    arg5.zza(6, ((zzzg)v0_1));
                }

                ++v1;
            }
        }

        if(this.zzawj != null) {
            arg5.zzb(7, this.zzawj);
        }

        super.zza(arg5);
    }

    protected final int zzf() {
        int v1;
        int v0 = super.zzf();
        if(this.zzawe != null) {
            v0 += zzyy.zzd(1, this.zzawe.longValue());
        }

        if(this.zzafx != null) {
            v0 += zzyy.zzc(2, this.zzafx);
        }

        if(this.zzawf != null) {
            v0 += zzyy.zzh(3, this.zzawf.intValue());
        }

        int v2 = 0;
        if(this.zzawg != null && this.zzawg.length > 0) {
            v1 = v0;
            for(v0 = 0; v0 < this.zzawg.length; ++v0) {
                zzgc v3 = this.zzawg[v0];
                if(v3 != null) {
                    v1 += zzyy.zzb(4, ((zzzg)v3));
                }
            }

            v0 = v1;
        }

        if(this.zzawh != null && this.zzawh.length > 0) {
            v1 = v0;
            for(v0 = 0; v0 < this.zzawh.length; ++v0) {
                zzga v3_1 = this.zzawh[v0];
                if(v3_1 != null) {
                    v1 += zzyy.zzb(5, ((zzzg)v3_1));
                }
            }

            v0 = v1;
        }

        if(this.zzawi != null && this.zzawi.length > 0) {
            while(v2 < this.zzawi.length) {
                zzfu v1_1 = this.zzawi[v2];
                if(v1_1 != null) {
                    v0 += zzyy.zzb(6, ((zzzg)v1_1));
                }

                ++v2;
            }
        }

        if(this.zzawj != null) {
            v0 += zzyy.zzc(7, this.zzawj);
        }

        return v0;
    }
}

