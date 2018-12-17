package com.google.android.gms.internal.measurement;

public final class zzfy extends zzza {
    public Boolean zzavb;
    public Boolean zzavc;
    public Integer zzave;
    private static volatile zzfy[] zzavt;
    public String zzavu;
    public zzfw zzavv;

    public zzfy() {
        super();
        this.zzave = null;
        this.zzavu = null;
        this.zzavv = null;
        this.zzavb = null;
        this.zzavc = null;
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object arg5) {
        if((((zzfy)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzfy)) {
            return 0;
        }

        if(this.zzave == null) {
            if(((zzfy)arg5).zzave != null) {
                return 0;
            }
        }
        else if(!this.zzave.equals(((zzfy)arg5).zzave)) {
            return 0;
        }

        if(this.zzavu == null) {
            if(((zzfy)arg5).zzavu != null) {
                return 0;
            }
        }
        else if(!this.zzavu.equals(((zzfy)arg5).zzavu)) {
            return 0;
        }

        if(this.zzavv == null) {
            if(((zzfy)arg5).zzavv != null) {
                return 0;
            }
        }
        else if(!this.zzavv.equals(((zzfy)arg5).zzavv)) {
            return 0;
        }

        if(this.zzavb == null) {
            if(((zzfy)arg5).zzavb != null) {
                return 0;
            }
        }
        else if(!this.zzavb.equals(((zzfy)arg5).zzavb)) {
            return 0;
        }

        if(this.zzavc == null) {
            if(((zzfy)arg5).zzavc != null) {
                return 0;
            }
        }
        else if(!this.zzavc.equals(((zzfy)arg5).zzavc)) {
            return 0;
        }

        if(this.zzcfc != null) {
            if(this.zzcfc.isEmpty()) {
            }
            else {
                return this.zzcfc.equals(((zzfy)arg5).zzcfc);
            }
        }

        if(((zzfy)arg5).zzcfc != null) {
            if(((zzfy)arg5).zzcfc.isEmpty()) {
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
        v1 = this.zzavu == null ? 0 : this.zzavu.hashCode();
        v0 += v1;
        zzfw v1_1 = this.zzavv;
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

    public final zzzg zza(zzyx arg3) {
        while(true) {
            int v0 = arg3.zzug();
            if(v0 == 0) {
                return this;
            }

            if(v0 != 8) {
                if(v0 != 18) {
                    if(v0 != 26) {
                        if(v0 != 32) {
                            if(v0 != 40) {
                                if(super.zza(arg3, v0)) {
                                    continue;
                                }

                                return this;
                            }

                            this.zzavc = Boolean.valueOf(arg3.zzum());
                            continue;
                        }

                        this.zzavb = Boolean.valueOf(arg3.zzum());
                        continue;
                    }

                    if(this.zzavv == null) {
                        this.zzavv = new zzfw();
                    }

                    arg3.zza(this.zzavv);
                    continue;
                }

                this.zzavu = arg3.readString();
                continue;
            }

            this.zzave = Integer.valueOf(arg3.zzuy());
        }

        return this;
    }

    public final void zza(zzyy arg3) {
        if(this.zzave != null) {
            arg3.zzd(1, this.zzave.intValue());
        }

        if(this.zzavu != null) {
            arg3.zzb(2, this.zzavu);
        }

        if(this.zzavv != null) {
            arg3.zza(3, this.zzavv);
        }

        if(this.zzavb != null) {
            arg3.zzb(4, this.zzavb.booleanValue());
        }

        if(this.zzavc != null) {
            arg3.zzb(5, this.zzavc.booleanValue());
        }

        super.zza(arg3);
    }

    protected final int zzf() {
        int v0 = super.zzf();
        if(this.zzave != null) {
            v0 += zzyy.zzh(1, this.zzave.intValue());
        }

        if(this.zzavu != null) {
            v0 += zzyy.zzc(2, this.zzavu);
        }

        if(this.zzavv != null) {
            v0 += zzyy.zzb(3, this.zzavv);
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

    public static zzfy[] zzml() {
        if(zzfy.zzavt == null) {
            Object v0 = zzze.zzcfl;
            __monitor_enter(v0);
            try {
                if(zzfy.zzavt == null) {
                    zzfy.zzavt = new zzfy[0];
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
        return zzfy.zzavt;
    }
}

