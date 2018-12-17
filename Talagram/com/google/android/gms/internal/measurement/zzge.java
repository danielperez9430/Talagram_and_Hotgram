package com.google.android.gms.internal.measurement;

public final class zzge extends zzza {
    private static volatile zzge[] zzawp;
    public Integer zzawq;
    public Long zzawr;

    public zzge() {
        super();
        this.zzawq = null;
        this.zzawr = null;
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object arg5) {
        if((((zzge)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzge)) {
            return 0;
        }

        if(this.zzawq == null) {
            if(((zzge)arg5).zzawq != null) {
                return 0;
            }
        }
        else if(!this.zzawq.equals(((zzge)arg5).zzawq)) {
            return 0;
        }

        if(this.zzawr == null) {
            if(((zzge)arg5).zzawr != null) {
                return 0;
            }
        }
        else if(!this.zzawr.equals(((zzge)arg5).zzawr)) {
            return 0;
        }

        if(this.zzcfc != null) {
            if(this.zzcfc.isEmpty()) {
            }
            else {
                return this.zzcfc.equals(((zzge)arg5).zzcfc);
            }
        }

        if(((zzge)arg5).zzcfc != null) {
            if(((zzge)arg5).zzcfc.isEmpty()) {
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
        int v1 = this.zzawq == null ? 0 : this.zzawq.hashCode();
        v0 = (v0 + v1) * 31;
        v1 = this.zzawr == null ? 0 : this.zzawr.hashCode();
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
                if(v0 != 16) {
                    if(super.zza(arg3, v0)) {
                        continue;
                    }

                    return this;
                }

                this.zzawr = Long.valueOf(arg3.zzuz());
                continue;
            }

            this.zzawq = Integer.valueOf(arg3.zzuy());
        }

        return this;
    }

    public final void zza(zzyy arg4) {
        if(this.zzawq != null) {
            arg4.zzd(1, this.zzawq.intValue());
        }

        if(this.zzawr != null) {
            arg4.zzi(2, this.zzawr.longValue());
        }

        super.zza(arg4);
    }

    protected final int zzf() {
        int v0 = super.zzf();
        if(this.zzawq != null) {
            v0 += zzyy.zzh(1, this.zzawq.intValue());
        }

        if(this.zzawr != null) {
            v0 += zzyy.zzd(2, this.zzawr.longValue());
        }

        return v0;
    }

    public static zzge[] zzmp() {
        if(zzge.zzawp == null) {
            Object v0 = zzze.zzcfl;
            __monitor_enter(v0);
            try {
                if(zzge.zzawp == null) {
                    zzge.zzawp = new zzge[0];
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
        return zzge.zzawp;
    }
}

