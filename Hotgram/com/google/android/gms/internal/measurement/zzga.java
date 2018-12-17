package com.google.android.gms.internal.measurement;

public final class zzga extends zzza {
    public String name;
    private static volatile zzga[] zzawa;
    public Boolean zzawb;
    public Boolean zzawc;
    public Integer zzawd;

    public zzga() {
        super();
        this.name = null;
        this.zzawb = null;
        this.zzawc = null;
        this.zzawd = null;
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object arg5) {
        if((((zzga)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzga)) {
            return 0;
        }

        if(this.name == null) {
            if(((zzga)arg5).name != null) {
                return 0;
            }
        }
        else if(!this.name.equals(((zzga)arg5).name)) {
            return 0;
        }

        if(this.zzawb == null) {
            if(((zzga)arg5).zzawb != null) {
                return 0;
            }
        }
        else if(!this.zzawb.equals(((zzga)arg5).zzawb)) {
            return 0;
        }

        if(this.zzawc == null) {
            if(((zzga)arg5).zzawc != null) {
                return 0;
            }
        }
        else if(!this.zzawc.equals(((zzga)arg5).zzawc)) {
            return 0;
        }

        if(this.zzawd == null) {
            if(((zzga)arg5).zzawd != null) {
                return 0;
            }
        }
        else if(!this.zzawd.equals(((zzga)arg5).zzawd)) {
            return 0;
        }

        if(this.zzcfc != null) {
            if(this.zzcfc.isEmpty()) {
            }
            else {
                return this.zzcfc.equals(((zzga)arg5).zzcfc);
            }
        }

        if(((zzga)arg5).zzcfc != null) {
            if(((zzga)arg5).zzcfc.isEmpty()) {
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
        v1 = this.zzawb == null ? 0 : this.zzawb.hashCode();
        v0 = (v0 + v1) * 31;
        v1 = this.zzawc == null ? 0 : this.zzawc.hashCode();
        v0 = (v0 + v1) * 31;
        v1 = this.zzawd == null ? 0 : this.zzawd.hashCode();
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
                if(v0 != 16) {
                    if(v0 != 24) {
                        if(v0 != 32) {
                            if(super.zza(arg3, v0)) {
                                continue;
                            }

                            return this;
                        }

                        this.zzawd = Integer.valueOf(arg3.zzuy());
                        continue;
                    }

                    this.zzawc = Boolean.valueOf(arg3.zzum());
                    continue;
                }

                this.zzawb = Boolean.valueOf(arg3.zzum());
                continue;
            }

            this.name = arg3.readString();
        }

        return this;
    }

    public final void zza(zzyy arg3) {
        if(this.name != null) {
            arg3.zzb(1, this.name);
        }

        if(this.zzawb != null) {
            arg3.zzb(2, this.zzawb.booleanValue());
        }

        if(this.zzawc != null) {
            arg3.zzb(3, this.zzawc.booleanValue());
        }

        if(this.zzawd != null) {
            arg3.zzd(4, this.zzawd.intValue());
        }

        super.zza(arg3);
    }

    protected final int zzf() {
        int v0 = super.zzf();
        if(this.name != null) {
            v0 += zzyy.zzc(1, this.name);
        }

        if(this.zzawb != null) {
            this.zzawb.booleanValue();
            v0 += zzyy.zzbb(2) + 1;
        }

        if(this.zzawc != null) {
            this.zzawc.booleanValue();
            v0 += zzyy.zzbb(3) + 1;
        }

        if(this.zzawd != null) {
            v0 += zzyy.zzh(4, this.zzawd.intValue());
        }

        return v0;
    }

    public static zzga[] zzmm() {
        if(zzga.zzawa == null) {
            Object v0 = zzze.zzcfl;
            __monitor_enter(v0);
            try {
                if(zzga.zzawa == null) {
                    zzga.zzawa = new zzga[0];
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
        return zzga.zzawa;
    }
}

