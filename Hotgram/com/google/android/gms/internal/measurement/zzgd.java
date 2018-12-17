package com.google.android.gms.internal.measurement;

public final class zzgd extends zzza {
    public Integer zzauy;
    private static volatile zzgd[] zzawl;
    public zzgj zzawm;
    public zzgj zzawn;
    public Boolean zzawo;

    public zzgd() {
        super();
        this.zzauy = null;
        this.zzawm = null;
        this.zzawn = null;
        this.zzawo = null;
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object arg5) {
        if((((zzgd)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzgd)) {
            return 0;
        }

        if(this.zzauy == null) {
            if(((zzgd)arg5).zzauy != null) {
                return 0;
            }
        }
        else if(!this.zzauy.equals(((zzgd)arg5).zzauy)) {
            return 0;
        }

        if(this.zzawm == null) {
            if(((zzgd)arg5).zzawm != null) {
                return 0;
            }
        }
        else if(!this.zzawm.equals(((zzgd)arg5).zzawm)) {
            return 0;
        }

        if(this.zzawn == null) {
            if(((zzgd)arg5).zzawn != null) {
                return 0;
            }
        }
        else if(!this.zzawn.equals(((zzgd)arg5).zzawn)) {
            return 0;
        }

        if(this.zzawo == null) {
            if(((zzgd)arg5).zzawo != null) {
                return 0;
            }
        }
        else if(!this.zzawo.equals(((zzgd)arg5).zzawo)) {
            return 0;
        }

        if(this.zzcfc != null) {
            if(this.zzcfc.isEmpty()) {
            }
            else {
                return this.zzcfc.equals(((zzgd)arg5).zzcfc);
            }
        }

        if(((zzgd)arg5).zzcfc != null) {
            if(((zzgd)arg5).zzcfc.isEmpty()) {
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
        v0 += v1;
        zzgj v1_1 = this.zzawm;
        v0 *= 31;
        v1 = v1_1 == null ? 0 : v1_1.hashCode();
        v0 += v1;
        v1_1 = this.zzawn;
        v0 *= 31;
        v1 = v1_1 == null ? 0 : v1_1.hashCode();
        v0 = (v0 + v1) * 31;
        v1 = this.zzawo == null ? 0 : this.zzawo.hashCode();
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
        zzgj v0_1;
        while(true) {
            int v0 = arg3.zzug();
            if(v0 == 0) {
                return this;
            }

            if(v0 != 8) {
                if(v0 == 18) {
                    if(this.zzawm == null) {
                        this.zzawm = new zzgj();
                    }

                    v0_1 = this.zzawm;
                }
                else if(v0 == 26) {
                    if(this.zzawn == null) {
                        this.zzawn = new zzgj();
                    }

                    v0_1 = this.zzawn;
                }
                else if(v0 != 32) {
                    if(super.zza(arg3, v0)) {
                        continue;
                    }

                    return this;
                }
                else {
                    this.zzawo = Boolean.valueOf(arg3.zzum());
                    continue;
                }

                arg3.zza(((zzzg)v0_1));
                continue;
            }

            this.zzauy = Integer.valueOf(arg3.zzuy());
        }

        return this;
    }

    public final void zza(zzyy arg3) {
        if(this.zzauy != null) {
            arg3.zzd(1, this.zzauy.intValue());
        }

        if(this.zzawm != null) {
            arg3.zza(2, this.zzawm);
        }

        if(this.zzawn != null) {
            arg3.zza(3, this.zzawn);
        }

        if(this.zzawo != null) {
            arg3.zzb(4, this.zzawo.booleanValue());
        }

        super.zza(arg3);
    }

    protected final int zzf() {
        int v0 = super.zzf();
        if(this.zzauy != null) {
            v0 += zzyy.zzh(1, this.zzauy.intValue());
        }

        if(this.zzawm != null) {
            v0 += zzyy.zzb(2, this.zzawm);
        }

        if(this.zzawn != null) {
            v0 += zzyy.zzb(3, this.zzawn);
        }

        if(this.zzawo != null) {
            this.zzawo.booleanValue();
            v0 += zzyy.zzbb(4) + 1;
        }

        return v0;
    }

    public static zzgd[] zzmo() {
        if(zzgd.zzawl == null) {
            Object v0 = zzze.zzcfl;
            __monitor_enter(v0);
            try {
                if(zzgd.zzawl == null) {
                    zzgd.zzawl = new zzgd[0];
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
        return zzgd.zzawl;
    }
}

