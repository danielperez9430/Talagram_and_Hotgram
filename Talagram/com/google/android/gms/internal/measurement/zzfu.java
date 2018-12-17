package com.google.android.gms.internal.measurement;

public final class zzfu extends zzza {
    private static volatile zzfu[] zzaux;
    public Integer zzauy;
    public zzfy[] zzauz;
    public zzfv[] zzava;
    private Boolean zzavb;
    private Boolean zzavc;

    public zzfu() {
        super();
        this.zzauy = null;
        this.zzauz = zzfy.zzml();
        this.zzava = zzfv.zzmj();
        this.zzavb = null;
        this.zzavc = null;
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object arg5) {
        if((((zzfu)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzfu)) {
            return 0;
        }

        if(this.zzauy == null) {
            if(((zzfu)arg5).zzauy != null) {
                return 0;
            }
        }
        else if(!this.zzauy.equals(((zzfu)arg5).zzauy)) {
            return 0;
        }

        if(!zzze.equals(this.zzauz, ((zzfu)arg5).zzauz)) {
            return 0;
        }

        if(!zzze.equals(this.zzava, ((zzfu)arg5).zzava)) {
            return 0;
        }

        if(this.zzavb == null) {
            if(((zzfu)arg5).zzavb != null) {
                return 0;
            }
        }
        else if(!this.zzavb.equals(((zzfu)arg5).zzavb)) {
            return 0;
        }

        if(this.zzavc == null) {
            if(((zzfu)arg5).zzavc != null) {
                return 0;
            }
        }
        else if(!this.zzavc.equals(((zzfu)arg5).zzavc)) {
            return 0;
        }

        if(this.zzcfc != null) {
            if(this.zzcfc.isEmpty()) {
            }
            else {
                return this.zzcfc.equals(((zzfu)arg5).zzcfc);
            }
        }

        if(((zzfu)arg5).zzcfc != null) {
            if(((zzfu)arg5).zzcfc.isEmpty()) {
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
        int v1 = this.zzauy == null ? 0 : this.zzauy.hashCode();
        v0 = (((v0 + v1) * 31 + zzze.hashCode(this.zzauz)) * 31 + zzze.hashCode(this.zzava)) * 31;
        v1 = this.zzavb == null ? 0 : this.zzavb.hashCode();
        v0 = (v0 + v1) * 31;
        v1 = this.zzavc == null ? 0 : this.zzavc.hashCode();
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
        zzfy[] v0_2;
        zzfv[] v0_1;
        int v1;
        while(true) {
        label_0:
            int v0 = arg5.zzug();
            if(v0 == 0) {
                return this;
            }

            if(v0 == 8) {
                goto label_84;
            }

            v1 = 18;
            if(v0 == v1) {
                goto label_54;
            }

            v1 = 26;
            if(v0 != v1) {
                if(v0 != 32) {
                    if(v0 != 40) {
                        if(super.zza(arg5, v0)) {
                            continue;
                        }

                        return this;
                    }

                    this.zzavc = Boolean.valueOf(arg5.zzum());
                    continue;
                }

                this.zzavb = Boolean.valueOf(arg5.zzum());
                continue;
            }

            v0 = zzzj.zzb(arg5, v1);
            v1 = this.zzava == null ? 0 : this.zzava.length;
            v0_1 = new zzfv[v0 + v1];
            if(v1 != 0) {
                System.arraycopy(this.zzava, 0, v0_1, 0, v1);
                goto label_36;
            label_54:
                v0 = zzzj.zzb(arg5, v1);
                v1 = this.zzauz == null ? 0 : this.zzauz.length;
                v0_2 = new zzfy[v0 + v1];
                if(v1 != 0) {
                    System.arraycopy(this.zzauz, 0, v0_2, 0, v1);
                    goto label_66;
                label_84:
                    this.zzauy = Integer.valueOf(arg5.zzuy());
                    continue;
                }

                goto label_66;
            }

            goto label_36;
        }

        return this;
    label_66:
        while(v1 < v0_2.length - 1) {
            v0_2[v1] = new zzfy();
            arg5.zza(v0_2[v1]);
            arg5.zzug();
            ++v1;
        }

        v0_2[v1] = new zzfy();
        arg5.zza(v0_2[v1]);
        this.zzauz = v0_2;
        goto label_0;
    label_36:
        while(v1 < v0_1.length - 1) {
            v0_1[v1] = new zzfv();
            arg5.zza(v0_1[v1]);
            arg5.zzug();
            ++v1;
        }

        v0_1[v1] = new zzfv();
        arg5.zza(v0_1[v1]);
        this.zzava = v0_1;
        goto label_0;
    }

    public final void zza(zzyy arg5) {
        if(this.zzauy != null) {
            arg5.zzd(1, this.zzauy.intValue());
        }

        int v1 = 0;
        if(this.zzauz != null && this.zzauz.length > 0) {
            int v0;
            for(v0 = 0; v0 < this.zzauz.length; ++v0) {
                zzfy v2 = this.zzauz[v0];
                if(v2 != null) {
                    arg5.zza(2, ((zzzg)v2));
                }
            }
        }

        if(this.zzava != null && this.zzava.length > 0) {
            while(v1 < this.zzava.length) {
                zzfv v0_1 = this.zzava[v1];
                if(v0_1 != null) {
                    arg5.zza(3, ((zzzg)v0_1));
                }

                ++v1;
            }
        }

        if(this.zzavb != null) {
            arg5.zzb(4, this.zzavb.booleanValue());
        }

        if(this.zzavc != null) {
            arg5.zzb(5, this.zzavc.booleanValue());
        }

        super.zza(arg5);
    }

    protected final int zzf() {
        int v0 = super.zzf();
        if(this.zzauy != null) {
            v0 += zzyy.zzh(1, this.zzauy.intValue());
        }

        int v3 = 0;
        if(this.zzauz != null && this.zzauz.length > 0) {
            int v1 = v0;
            for(v0 = 0; v0 < this.zzauz.length; ++v0) {
                zzfy v4 = this.zzauz[v0];
                if(v4 != null) {
                    v1 += zzyy.zzb(2, ((zzzg)v4));
                }
            }

            v0 = v1;
        }

        if(this.zzava != null && this.zzava.length > 0) {
            while(v3 < this.zzava.length) {
                zzfv v1_1 = this.zzava[v3];
                if(v1_1 != null) {
                    v0 += zzyy.zzb(3, ((zzzg)v1_1));
                }

                ++v3;
            }
        }

        if(this.zzavb != null) {
            this.zzavb.booleanValue();
            v0 += zzyy.zzbb(4) + 1;
        }

        if(this.zzavc != null) {
            this.zzavc.booleanValue();
            v0 += zzyy.zzbb(5) + 1;
        }

        return v0;
    }

    public static zzfu[] zzmi() {
        if(zzfu.zzaux == null) {
            Object v0 = zzze.zzcfl;
            __monitor_enter(v0);
            try {
                if(zzfu.zzaux == null) {
                    zzfu.zzaux = new zzfu[0];
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
        return zzfu.zzaux;
    }
}

