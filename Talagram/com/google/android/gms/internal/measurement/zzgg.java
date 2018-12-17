package com.google.android.gms.internal.measurement;

public final class zzgg extends zzza {
    public String name;
    public String zzamp;
    private Float zzaug;
    public Double zzauh;
    private static volatile zzgg[] zzaww;
    public Long zzawx;

    public zzgg() {
        super();
        this.name = null;
        this.zzamp = null;
        this.zzawx = null;
        this.zzaug = null;
        this.zzauh = null;
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object arg5) {
        if((((zzgg)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzgg)) {
            return 0;
        }

        if(this.name == null) {
            if(((zzgg)arg5).name != null) {
                return 0;
            }
        }
        else if(!this.name.equals(((zzgg)arg5).name)) {
            return 0;
        }

        if(this.zzamp == null) {
            if(((zzgg)arg5).zzamp != null) {
                return 0;
            }
        }
        else if(!this.zzamp.equals(((zzgg)arg5).zzamp)) {
            return 0;
        }

        if(this.zzawx == null) {
            if(((zzgg)arg5).zzawx != null) {
                return 0;
            }
        }
        else if(!this.zzawx.equals(((zzgg)arg5).zzawx)) {
            return 0;
        }

        if(this.zzaug == null) {
            if(((zzgg)arg5).zzaug != null) {
                return 0;
            }
        }
        else if(!this.zzaug.equals(((zzgg)arg5).zzaug)) {
            return 0;
        }

        if(this.zzauh == null) {
            if(((zzgg)arg5).zzauh != null) {
                return 0;
            }
        }
        else if(!this.zzauh.equals(((zzgg)arg5).zzauh)) {
            return 0;
        }

        if(this.zzcfc != null) {
            if(this.zzcfc.isEmpty()) {
            }
            else {
                return this.zzcfc.equals(((zzgg)arg5).zzcfc);
            }
        }

        if(((zzgg)arg5).zzcfc != null) {
            if(((zzgg)arg5).zzcfc.isEmpty()) {
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
        int v1 = this.name == null ? 0 : this.name.hashCode();
        v0 = (v0 + v1) * 31;
        v1 = this.zzamp == null ? 0 : this.zzamp.hashCode();
        v0 = (v0 + v1) * 31;
        v1 = this.zzawx == null ? 0 : this.zzawx.hashCode();
        v0 = (v0 + v1) * 31;
        v1 = this.zzaug == null ? 0 : this.zzaug.hashCode();
        v0 = (v0 + v1) * 31;
        v1 = this.zzauh == null ? 0 : this.zzauh.hashCode();
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

            if(v0 != 10) {
                if(v0 != 18) {
                    if(v0 != 24) {
                        if(v0 != 37) {
                            if(v0 != 41) {
                                if(super.zza(arg3, v0)) {
                                    continue;
                                }

                                return this;
                            }

                            this.zzauh = Double.valueOf(Double.longBitsToDouble(arg3.zzvb()));
                            continue;
                        }

                        this.zzaug = Float.valueOf(Float.intBitsToFloat(arg3.zzva()));
                        continue;
                    }

                    this.zzawx = Long.valueOf(arg3.zzuz());
                    continue;
                }

                this.zzamp = arg3.readString();
                continue;
            }

            this.name = arg3.readString();
        }

        return this;
    }

    public final void zza(zzyy arg4) {
        if(this.name != null) {
            arg4.zzb(1, this.name);
        }

        if(this.zzamp != null) {
            arg4.zzb(2, this.zzamp);
        }

        if(this.zzawx != null) {
            arg4.zzi(3, this.zzawx.longValue());
        }

        if(this.zzaug != null) {
            arg4.zza(4, this.zzaug.floatValue());
        }

        if(this.zzauh != null) {
            arg4.zza(5, this.zzauh.doubleValue());
        }

        super.zza(arg4);
    }

    protected final int zzf() {
        int v0 = super.zzf();
        if(this.name != null) {
            v0 += zzyy.zzc(1, this.name);
        }

        if(this.zzamp != null) {
            v0 += zzyy.zzc(2, this.zzamp);
        }

        if(this.zzawx != null) {
            v0 += zzyy.zzd(3, this.zzawx.longValue());
        }

        if(this.zzaug != null) {
            this.zzaug.floatValue();
            v0 += zzyy.zzbb(4) + 4;
        }

        if(this.zzauh != null) {
            this.zzauh.doubleValue();
            v0 += zzyy.zzbb(5) + 8;
        }

        return v0;
    }

    public static zzgg[] zzmr() {
        if(zzgg.zzaww == null) {
            Object v0 = zzze.zzcfl;
            __monitor_enter(v0);
            try {
                if(zzgg.zzaww == null) {
                    zzgg.zzaww = new zzgg[0];
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
        return zzgg.zzaww;
    }
}

