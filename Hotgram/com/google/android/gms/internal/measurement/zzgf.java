package com.google.android.gms.internal.measurement;

public final class zzgf extends zzza {
    public Integer count;
    public String name;
    private static volatile zzgf[] zzaws;
    public zzgg[] zzawt;
    public Long zzawu;
    public Long zzawv;

    public zzgf() {
        super();
        this.zzawt = zzgg.zzmr();
        this.name = null;
        this.zzawu = null;
        this.zzawv = null;
        this.count = null;
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object arg5) {
        if((((zzgf)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzgf)) {
            return 0;
        }

        if(!zzze.equals(this.zzawt, ((zzgf)arg5).zzawt)) {
            return 0;
        }

        if(this.name == null) {
            if(((zzgf)arg5).name != null) {
                return 0;
            }
        }
        else if(!this.name.equals(((zzgf)arg5).name)) {
            return 0;
        }

        if(this.zzawu == null) {
            if(((zzgf)arg5).zzawu != null) {
                return 0;
            }
        }
        else if(!this.zzawu.equals(((zzgf)arg5).zzawu)) {
            return 0;
        }

        if(this.zzawv == null) {
            if(((zzgf)arg5).zzawv != null) {
                return 0;
            }
        }
        else if(!this.zzawv.equals(((zzgf)arg5).zzawv)) {
            return 0;
        }

        if(this.count == null) {
            if(((zzgf)arg5).count != null) {
                return 0;
            }
        }
        else if(!this.count.equals(((zzgf)arg5).count)) {
            return 0;
        }

        if(this.zzcfc != null) {
            if(this.zzcfc.isEmpty()) {
            }
            else {
                return this.zzcfc.equals(((zzgf)arg5).zzcfc);
            }
        }

        if(((zzgf)arg5).zzcfc != null) {
            if(((zzgf)arg5).zzcfc.isEmpty()) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public final int hashCode() {
        int v0 = ((this.getClass().getName().hashCode() + 527) * 31 + zzze.hashCode(this.zzawt)) * 31;
        int v2 = 0;
        int v1 = this.name == null ? 0 : this.name.hashCode();
        v0 = (v0 + v1) * 31;
        v1 = this.zzawu == null ? 0 : this.zzawu.hashCode();
        v0 = (v0 + v1) * 31;
        v1 = this.zzawv == null ? 0 : this.zzawv.hashCode();
        v0 = (v0 + v1) * 31;
        v1 = this.count == null ? 0 : this.count.hashCode();
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
        int v1;
        int v0;
        while(true) {
        label_0:
            v0 = arg5.zzug();
            if(v0 == 0) {
                return this;
            }

            v1 = 10;
            if(v0 == v1) {
                break;
            }

            if(v0 != 18) {
                if(v0 != 24) {
                    if(v0 != 32) {
                        if(v0 != 40) {
                            if(super.zza(arg5, v0)) {
                                continue;
                            }

                            return this;
                        }

                        this.count = Integer.valueOf(arg5.zzuy());
                        continue;
                    }

                    this.zzawv = Long.valueOf(arg5.zzuz());
                    continue;
                }

                this.zzawu = Long.valueOf(arg5.zzuz());
                continue;
            }

            this.name = arg5.readString();
        }

        v0 = zzzj.zzb(arg5, v1);
        v1 = this.zzawt == null ? 0 : this.zzawt.length;
        zzgg[] v0_1 = new zzgg[v0 + v1];
        if(v1 != 0) {
            System.arraycopy(this.zzawt, 0, v0_1, 0, v1);
            goto label_43;
            return this;
        }

    label_43:
        while(v1 < v0_1.length - 1) {
            v0_1[v1] = new zzgg();
            arg5.zza(v0_1[v1]);
            arg5.zzug();
            ++v1;
        }

        v0_1[v1] = new zzgg();
        arg5.zza(v0_1[v1]);
        this.zzawt = v0_1;
        goto label_0;
    }

    public final void zza(zzyy arg4) {
        if(this.zzawt != null && this.zzawt.length > 0) {
            int v0;
            for(v0 = 0; v0 < this.zzawt.length; ++v0) {
                zzgg v1 = this.zzawt[v0];
                if(v1 != null) {
                    arg4.zza(1, ((zzzg)v1));
                }
            }
        }

        if(this.name != null) {
            arg4.zzb(2, this.name);
        }

        if(this.zzawu != null) {
            arg4.zzi(3, this.zzawu.longValue());
        }

        if(this.zzawv != null) {
            arg4.zzi(4, this.zzawv.longValue());
        }

        if(this.count != null) {
            arg4.zzd(5, this.count.intValue());
        }

        super.zza(arg4);
    }

    protected final int zzf() {
        int v0 = super.zzf();
        if(this.zzawt != null && this.zzawt.length > 0) {
            int v1;
            for(v1 = 0; v1 < this.zzawt.length; ++v1) {
                zzgg v2 = this.zzawt[v1];
                if(v2 != null) {
                    v0 += zzyy.zzb(1, ((zzzg)v2));
                }
            }
        }

        if(this.name != null) {
            v0 += zzyy.zzc(2, this.name);
        }

        if(this.zzawu != null) {
            v0 += zzyy.zzd(3, this.zzawu.longValue());
        }

        if(this.zzawv != null) {
            v0 += zzyy.zzd(4, this.zzawv.longValue());
        }

        if(this.count != null) {
            v0 += zzyy.zzh(5, this.count.intValue());
        }

        return v0;
    }

    public static zzgf[] zzmq() {
        if(zzgf.zzaws == null) {
            Object v0 = zzze.zzcfl;
            __monitor_enter(v0);
            try {
                if(zzgf.zzaws == null) {
                    zzgf.zzaws = new zzgf[0];
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
        return zzgf.zzaws;
    }
}

