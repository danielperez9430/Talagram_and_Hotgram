package com.google.android.gms.internal.measurement;

public final class zzfv extends zzza {
    public Boolean zzavb;
    public Boolean zzavc;
    private static volatile zzfv[] zzavd;
    public Integer zzave;
    public String zzavf;
    public zzfw[] zzavg;
    private Boolean zzavh;
    public zzfx zzavi;

    public zzfv() {
        super();
        this.zzave = null;
        this.zzavf = null;
        this.zzavg = zzfw.zzmk();
        this.zzavh = null;
        this.zzavi = null;
        this.zzavb = null;
        this.zzavc = null;
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object arg5) {
        if((((zzfv)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzfv)) {
            return 0;
        }

        if(this.zzave == null) {
            if(((zzfv)arg5).zzave != null) {
                return 0;
            }
        }
        else if(!this.zzave.equals(((zzfv)arg5).zzave)) {
            return 0;
        }

        if(this.zzavf == null) {
            if(((zzfv)arg5).zzavf != null) {
                return 0;
            }
        }
        else if(!this.zzavf.equals(((zzfv)arg5).zzavf)) {
            return 0;
        }

        if(!zzze.equals(this.zzavg, ((zzfv)arg5).zzavg)) {
            return 0;
        }

        if(this.zzavh == null) {
            if(((zzfv)arg5).zzavh != null) {
                return 0;
            }
        }
        else if(!this.zzavh.equals(((zzfv)arg5).zzavh)) {
            return 0;
        }

        if(this.zzavi == null) {
            if(((zzfv)arg5).zzavi != null) {
                return 0;
            }
        }
        else if(!this.zzavi.equals(((zzfv)arg5).zzavi)) {
            return 0;
        }

        if(this.zzavb == null) {
            if(((zzfv)arg5).zzavb != null) {
                return 0;
            }
        }
        else if(!this.zzavb.equals(((zzfv)arg5).zzavb)) {
            return 0;
        }

        if(this.zzavc == null) {
            if(((zzfv)arg5).zzavc != null) {
                return 0;
            }
        }
        else if(!this.zzavc.equals(((zzfv)arg5).zzavc)) {
            return 0;
        }

        if(this.zzcfc != null) {
            if(this.zzcfc.isEmpty()) {
            }
            else {
                return this.zzcfc.equals(((zzfv)arg5).zzcfc);
            }
        }

        if(((zzfv)arg5).zzcfc != null) {
            if(((zzfv)arg5).zzcfc.isEmpty()) {
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
        int v1 = this.zzave == null ? 0 : this.zzave.hashCode();
        v0 = (v0 + v1) * 31;
        v1 = this.zzavf == null ? 0 : this.zzavf.hashCode();
        v0 = ((v0 + v1) * 31 + zzze.hashCode(this.zzavg)) * 31;
        v1 = this.zzavh == null ? 0 : this.zzavh.hashCode();
        v0 += v1;
        zzfx v1_1 = this.zzavi;
        v0 *= 31;
        v1 = v1_1 == null ? 0 : v1_1.hashCode();
        v0 = (v0 + v1) * 31;
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
        zzfw[] v0_1;
        int v1;
        while(true) {
        label_0:
            int v0 = arg5.zzug();
            if(v0 == 0) {
                return this;
            }

            if(v0 == 8) {
                goto label_73;
            }

            if(v0 == 18) {
                goto label_70;
            }

            v1 = 26;
            if(v0 != v1) {
                if(v0 != 32) {
                    if(v0 != 42) {
                        if(v0 != 48) {
                            if(v0 != 56) {
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

                    if(this.zzavi == null) {
                        this.zzavi = new zzfx();
                    }

                    arg5.zza(this.zzavi);
                    continue;
                }

                this.zzavh = Boolean.valueOf(arg5.zzum());
                continue;
            }

            v0 = zzzj.zzb(arg5, v1);
            v1 = this.zzavg == null ? 0 : this.zzavg.length;
            v0_1 = new zzfw[v0 + v1];
            if(v1 != 0) {
                System.arraycopy(this.zzavg, 0, v0_1, 0, v1);
                goto label_52;
            label_70:
                this.zzavf = arg5.readString();
                continue;
            label_73:
                this.zzave = Integer.valueOf(arg5.zzuy());
                continue;
            }

            goto label_52;
        }

        return this;
    label_52:
        while(v1 < v0_1.length - 1) {
            v0_1[v1] = new zzfw();
            arg5.zza(v0_1[v1]);
            arg5.zzug();
            ++v1;
        }

        v0_1[v1] = new zzfw();
        arg5.zza(v0_1[v1]);
        this.zzavg = v0_1;
        goto label_0;
    }

    public final void zza(zzyy arg4) {
        if(this.zzave != null) {
            arg4.zzd(1, this.zzave.intValue());
        }

        if(this.zzavf != null) {
            arg4.zzb(2, this.zzavf);
        }

        if(this.zzavg != null && this.zzavg.length > 0) {
            int v0;
            for(v0 = 0; v0 < this.zzavg.length; ++v0) {
                zzfw v1 = this.zzavg[v0];
                if(v1 != null) {
                    arg4.zza(3, ((zzzg)v1));
                }
            }
        }

        if(this.zzavh != null) {
            arg4.zzb(4, this.zzavh.booleanValue());
        }

        if(this.zzavi != null) {
            arg4.zza(5, this.zzavi);
        }

        if(this.zzavb != null) {
            arg4.zzb(6, this.zzavb.booleanValue());
        }

        if(this.zzavc != null) {
            arg4.zzb(7, this.zzavc.booleanValue());
        }

        super.zza(arg4);
    }

    protected final int zzf() {
        int v0 = super.zzf();
        if(this.zzave != null) {
            v0 += zzyy.zzh(1, this.zzave.intValue());
        }

        if(this.zzavf != null) {
            v0 += zzyy.zzc(2, this.zzavf);
        }

        if(this.zzavg != null && this.zzavg.length > 0) {
            int v1;
            for(v1 = 0; v1 < this.zzavg.length; ++v1) {
                zzfw v3 = this.zzavg[v1];
                if(v3 != null) {
                    v0 += zzyy.zzb(3, ((zzzg)v3));
                }
            }
        }

        if(this.zzavh != null) {
            this.zzavh.booleanValue();
            v0 += zzyy.zzbb(4) + 1;
        }

        if(this.zzavi != null) {
            v0 += zzyy.zzb(5, this.zzavi);
        }

        if(this.zzavb != null) {
            this.zzavb.booleanValue();
            v0 += zzyy.zzbb(6) + 1;
        }

        if(this.zzavc != null) {
            this.zzavc.booleanValue();
            v0 += zzyy.zzbb(7) + 1;
        }

        return v0;
    }

    public static zzfv[] zzmj() {
        if(zzfv.zzavd == null) {
            Object v0 = zzze.zzcfl;
            __monitor_enter(v0);
            try {
                if(zzfv.zzavd == null) {
                    zzfv.zzavd = new zzfv[0];
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
        return zzfv.zzavd;
    }
}

