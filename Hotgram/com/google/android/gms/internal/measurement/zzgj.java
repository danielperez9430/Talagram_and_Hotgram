package com.google.android.gms.internal.measurement;

public final class zzgj extends zzza {
    public long[] zzaye;
    public long[] zzayf;
    public zzge[] zzayg;
    public zzgk[] zzayh;

    public zzgj() {
        super();
        this.zzaye = zzzj.zzcfr;
        this.zzayf = zzzj.zzcfr;
        this.zzayg = zzge.zzmp();
        this.zzayh = zzgk.zzmt();
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object arg5) {
        if((((zzgj)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzgj)) {
            return 0;
        }

        if(!zzze.equals(this.zzaye, ((zzgj)arg5).zzaye)) {
            return 0;
        }

        if(!zzze.equals(this.zzayf, ((zzgj)arg5).zzayf)) {
            return 0;
        }

        if(!zzze.equals(this.zzayg, ((zzgj)arg5).zzayg)) {
            return 0;
        }

        if(!zzze.equals(this.zzayh, ((zzgj)arg5).zzayh)) {
            return 0;
        }

        if(this.zzcfc != null) {
            if(this.zzcfc.isEmpty()) {
            }
            else {
                return this.zzcfc.equals(((zzgj)arg5).zzcfc);
            }
        }

        if(((zzgj)arg5).zzcfc != null) {
            if(((zzgj)arg5).zzcfc.isEmpty()) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public final int hashCode() {
        int v0 = (((((this.getClass().getName().hashCode() + 527) * 31 + zzze.hashCode(this.zzaye)) * 31 + zzze.hashCode(this.zzayf)) * 31 + zzze.hashCode(this.zzayg)) * 31 + zzze.hashCode(this.zzayh)) * 31;
        int v1 = this.zzcfc == null || (this.zzcfc.isEmpty()) ? 0 : this.zzcfc.hashCode();
        return v0 + v1;
    }

    public final zzzg zza(zzyx arg7) {
        long[] v3_1;
        int v1;
        int v0;
        do {
        label_0:
            v0 = arg7.zzug();
            if(v0 == 0) {
                return this;
            }

            v1 = 8;
            if(v0 == v1) {
                goto label_161;
            }

            if(v0 != 10) {
                v1 = 16;
                if(v0 == v1) {
                    goto label_107;
                }
                else if(v0 != 18) {
                    v1 = 26;
                    if(v0 != v1) {
                        v1 = 34;
                        if(v0 != v1) {
                            if(super.zza(arg7, v0)) {
                                continue;
                            }

                            return this;
                        }
                        else {
                            goto label_18;
                        }
                    }
                    else {
                        goto label_48;
                    }
                }
                else {
                    goto label_78;
                }
            }
            else {
                goto label_131;
            }

            goto label_159;
        }
        while(true);

        return this;
    label_18:
        v0 = zzzj.zzb(arg7, v1);
        v1 = this.zzayh == null ? 0 : this.zzayh.length;
        zzgk[] v0_1 = new zzgk[v0 + v1];
        if(v1 != 0) {
            System.arraycopy(this.zzayh, 0, v0_1, 0, v1);
            goto label_30;
        label_48:
            v0 = zzzj.zzb(arg7, v1);
            v1 = this.zzayg == null ? 0 : this.zzayg.length;
            zzge[] v0_2 = new zzge[v0 + v1];
            if(v1 != 0) {
                System.arraycopy(this.zzayg, 0, v0_2, 0, v1);
                goto label_60;
            label_78:
                v0 = arg7.zzaq(arg7.zzuy());
                v1 = arg7.getPosition();
                int v3 = 0;
                goto label_82;
            label_107:
                v0 = zzzj.zzb(arg7, v1);
                v1 = this.zzayf == null ? 0 : this.zzayf.length;
                long[] v0_3 = new long[v0 + v1];
                if(v1 != 0) {
                    System.arraycopy(this.zzayf, 0, v0_3, 0, v1);
                    goto label_119;
                label_131:
                    v0 = arg7.zzaq(arg7.zzuy());
                    v1 = arg7.getPosition();
                    v3 = 0;
                    goto label_135;
                label_161:
                    v0 = zzzj.zzb(arg7, v1);
                    v1 = this.zzaye == null ? 0 : this.zzaye.length;
                    v0_3 = new long[v0 + v1];
                    if(v1 != 0) {
                        System.arraycopy(this.zzaye, 0, v0_3, 0, v1);
                        goto label_173;
                        return this;
                    }

                label_173:
                    while(v1 < v0_3.length - 1) {
                        v0_3[v1] = arg7.zzuz();
                        arg7.zzug();
                        ++v1;
                    }

                    v0_3[v1] = arg7.zzuz();
                    this.zzaye = v0_3;
                    goto label_0;
                label_135:
                    while(arg7.zzyr() > 0) {
                        arg7.zzuz();
                        ++v3;
                    }

                    arg7.zzby(v1);
                    v1 = this.zzaye == null ? 0 : this.zzaye.length;
                    v3_1 = new long[v3 + v1];
                    if(v1 != 0) {
                        System.arraycopy(this.zzaye, 0, v3_1, 0, v1);
                    }

                    while(v1 < v3_1.length) {
                        v3_1[v1] = arg7.zzuz();
                        ++v1;
                    }

                    this.zzaye = v3_1;
                    goto label_159;
                }

            label_119:
                while(v1 < v0_3.length - 1) {
                    v0_3[v1] = arg7.zzuz();
                    arg7.zzug();
                    ++v1;
                }

                v0_3[v1] = arg7.zzuz();
                this.zzayf = v0_3;
                goto label_0;
            label_82:
                while(arg7.zzyr() > 0) {
                    arg7.zzuz();
                    ++v3;
                }

                arg7.zzby(v1);
                v1 = this.zzayf == null ? 0 : this.zzayf.length;
                v3_1 = new long[v3 + v1];
                if(v1 != 0) {
                    System.arraycopy(this.zzayf, 0, v3_1, 0, v1);
                }

                while(v1 < v3_1.length) {
                    v3_1[v1] = arg7.zzuz();
                    ++v1;
                }

                this.zzayf = v3_1;
            label_159:
                arg7.zzar(v0);
                goto label_0;
            }

        label_60:
            while(v1 < v0_2.length - 1) {
                v0_2[v1] = new zzge();
                arg7.zza(v0_2[v1]);
                arg7.zzug();
                ++v1;
            }

            v0_2[v1] = new zzge();
            arg7.zza(v0_2[v1]);
            this.zzayg = v0_2;
            goto label_0;
        }

    label_30:
        while(v1 < v0_1.length - 1) {
            v0_1[v1] = new zzgk();
            arg7.zza(v0_1[v1]);
            arg7.zzug();
            ++v1;
        }

        v0_1[v1] = new zzgk();
        arg7.zza(v0_1[v1]);
        this.zzayh = v0_1;
        goto label_0;
    }

    public final void zza(zzyy arg7) {
        int v1 = 0;
        if(this.zzaye != null && this.zzaye.length > 0) {
            int v0;
            for(v0 = 0; v0 < this.zzaye.length; ++v0) {
                arg7.zza(1, this.zzaye[v0]);
            }
        }

        if(this.zzayf != null && this.zzayf.length > 0) {
            for(v0 = 0; v0 < this.zzayf.length; ++v0) {
                arg7.zza(2, this.zzayf[v0]);
            }
        }

        if(this.zzayg != null && this.zzayg.length > 0) {
            for(v0 = 0; v0 < this.zzayg.length; ++v0) {
                zzge v2 = this.zzayg[v0];
                if(v2 != null) {
                    arg7.zza(3, ((zzzg)v2));
                }
            }
        }

        if(this.zzayh != null && this.zzayh.length > 0) {
            while(v1 < this.zzayh.length) {
                zzgk v0_1 = this.zzayh[v1];
                if(v0_1 != null) {
                    arg7.zza(4, ((zzzg)v0_1));
                }

                ++v1;
            }
        }

        super.zza(arg7);
    }

    protected final int zzf() {
        int v3;
        int v1;
        int v0 = super.zzf();
        int v2 = 0;
        if(this.zzaye != null && this.zzaye.length > 0) {
            v1 = 0;
            v3 = 0;
            while(v1 < this.zzaye.length) {
                v3 += zzyy.zzbi(this.zzaye[v1]);
                ++v1;
            }

            v0 = v0 + v3 + this.zzaye.length;
        }

        if(this.zzayf != null && this.zzayf.length > 0) {
            v1 = 0;
            v3 = 0;
            while(v1 < this.zzayf.length) {
                v3 += zzyy.zzbi(this.zzayf[v1]);
                ++v1;
            }

            v0 = v0 + v3 + this.zzayf.length;
        }

        if(this.zzayg != null && this.zzayg.length > 0) {
            v1 = v0;
            for(v0 = 0; v0 < this.zzayg.length; ++v0) {
                zzge v3_1 = this.zzayg[v0];
                if(v3_1 != null) {
                    v1 += zzyy.zzb(3, ((zzzg)v3_1));
                }
            }

            v0 = v1;
        }

        if(this.zzayh != null && this.zzayh.length > 0) {
            while(v2 < this.zzayh.length) {
                zzgk v1_1 = this.zzayh[v2];
                if(v1_1 != null) {
                    v0 += zzyy.zzb(4, ((zzzg)v1_1));
                }

                ++v2;
            }
        }

        return v0;
    }
}

