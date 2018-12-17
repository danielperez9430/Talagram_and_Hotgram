package com.google.android.gms.internal.measurement;

public final class zzfw extends zzza {
    private static volatile zzfw[] zzavj;
    public zzfz zzavk;
    public zzfx zzavl;
    public Boolean zzavm;
    public String zzavn;

    public zzfw() {
        super();
        this.zzavk = null;
        this.zzavl = null;
        this.zzavm = null;
        this.zzavn = null;
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object arg5) {
        if((((zzfw)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzfw)) {
            return 0;
        }

        if(this.zzavk == null) {
            if(((zzfw)arg5).zzavk != null) {
                return 0;
            }
        }
        else if(!this.zzavk.equals(((zzfw)arg5).zzavk)) {
            return 0;
        }

        if(this.zzavl == null) {
            if(((zzfw)arg5).zzavl != null) {
                return 0;
            }
        }
        else if(!this.zzavl.equals(((zzfw)arg5).zzavl)) {
            return 0;
        }

        if(this.zzavm == null) {
            if(((zzfw)arg5).zzavm != null) {
                return 0;
            }
        }
        else if(!this.zzavm.equals(((zzfw)arg5).zzavm)) {
            return 0;
        }

        if(this.zzavn == null) {
            if(((zzfw)arg5).zzavn != null) {
                return 0;
            }
        }
        else if(!this.zzavn.equals(((zzfw)arg5).zzavn)) {
            return 0;
        }

        if(this.zzcfc != null) {
            if(this.zzcfc.isEmpty()) {
            }
            else {
                return this.zzcfc.equals(((zzfw)arg5).zzcfc);
            }
        }

        if(((zzfw)arg5).zzcfc != null) {
            if(((zzfw)arg5).zzcfc.isEmpty()) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public final int hashCode() {
        int v0 = this.getClass().getName().hashCode() + 527;
        zzfz v1 = this.zzavk;
        v0 *= 31;
        int v2 = 0;
        int v1_1 = v1 == null ? 0 : v1.hashCode();
        v0 += v1_1;
        zzfx v1_2 = this.zzavl;
        v0 *= 31;
        v1_1 = v1_2 == null ? 0 : v1_2.hashCode();
        v0 = (v0 + v1_1) * 31;
        v1_1 = this.zzavm == null ? 0 : this.zzavm.hashCode();
        v0 = (v0 + v1_1) * 31;
        v1_1 = this.zzavn == null ? 0 : this.zzavn.hashCode();
        v0 = (v0 + v1_1) * 31;
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
        zzfz v0_2;
        while(true) {
            int v0 = arg3.zzug();
            if(v0 == 0) {
                return this;
            }

            if(v0 == 10) {
                if(this.zzavk == null) {
                    this.zzavk = new zzfz();
                }

                v0_2 = this.zzavk;
            }
            else if(v0 == 18) {
                if(this.zzavl == null) {
                    this.zzavl = new zzfx();
                }

                zzfx v0_1 = this.zzavl;
            }
            else if(v0 == 24) {
                this.zzavm = Boolean.valueOf(arg3.zzum());
                continue;
            }
            else if(v0 != 34) {
                if(super.zza(arg3, v0)) {
                    continue;
                }

                return this;
            }
            else {
                this.zzavn = arg3.readString();
                continue;
            }

            arg3.zza(((zzzg)v0_2));
        }

        return this;
    }

    public final void zza(zzyy arg3) {
        if(this.zzavk != null) {
            arg3.zza(1, this.zzavk);
        }

        if(this.zzavl != null) {
            arg3.zza(2, this.zzavl);
        }

        if(this.zzavm != null) {
            arg3.zzb(3, this.zzavm.booleanValue());
        }

        if(this.zzavn != null) {
            arg3.zzb(4, this.zzavn);
        }

        super.zza(arg3);
    }

    protected final int zzf() {
        int v0 = super.zzf();
        if(this.zzavk != null) {
            v0 += zzyy.zzb(1, this.zzavk);
        }

        if(this.zzavl != null) {
            v0 += zzyy.zzb(2, this.zzavl);
        }

        if(this.zzavm != null) {
            this.zzavm.booleanValue();
            v0 += zzyy.zzbb(3) + 1;
        }

        if(this.zzavn != null) {
            v0 += zzyy.zzc(4, this.zzavn);
        }

        return v0;
    }

    public static zzfw[] zzmk() {
        if(zzfw.zzavj == null) {
            Object v0 = zzze.zzcfl;
            __monitor_enter(v0);
            try {
                if(zzfw.zzavj == null) {
                    zzfw.zzavj = new zzfw[0];
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
        return zzfw.zzavj;
    }
}

