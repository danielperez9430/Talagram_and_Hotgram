package com.google.android.gms.internal.measurement;

public final class zzgk extends zzza {
    public Integer zzawq;
    private static volatile zzgk[] zzayi;
    public long[] zzayj;

    public zzgk() {
        super();
        this.zzawq = null;
        this.zzayj = zzzj.zzcfr;
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object arg5) {
        if((((zzgk)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzgk)) {
            return 0;
        }

        if(this.zzawq == null) {
            if(((zzgk)arg5).zzawq != null) {
                return 0;
            }
        }
        else if(!this.zzawq.equals(((zzgk)arg5).zzawq)) {
            return 0;
        }

        if(!zzze.equals(this.zzayj, ((zzgk)arg5).zzayj)) {
            return 0;
        }

        if(this.zzcfc != null) {
            if(this.zzcfc.isEmpty()) {
            }
            else {
                return this.zzcfc.equals(((zzgk)arg5).zzcfc);
            }
        }

        if(((zzgk)arg5).zzcfc != null) {
            if(((zzgk)arg5).zzcfc.isEmpty()) {
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
        v0 = ((v0 + v1) * 31 + zzze.hashCode(this.zzayj)) * 31;
        if(this.zzcfc != null) {
            if(this.zzcfc.isEmpty()) {
            }
            else {
                v2 = this.zzcfc.hashCode();
            }
        }

        return v0 + v2;
    }

    public final zzzg zza(zzyx arg7) {
        long[] v0_1;
        int v3;
        int v1;
        int v0;
        while(true) {
        label_0:
            v0 = arg7.zzug();
            if(v0 == 0) {
                return this;
            }

            if(v0 == 8) {
                goto label_66;
            }

            v1 = 16;
            if(v0 != v1) {
                if(v0 != 18) {
                    if(super.zza(arg7, v0)) {
                        continue;
                    }

                    return this;
                }

                v0 = arg7.zzaq(arg7.zzuy());
                v1 = arg7.getPosition();
                v3 = 0;
                goto label_16;
            }

            v0 = zzzj.zzb(arg7, v1);
            v1 = this.zzayj == null ? 0 : this.zzayj.length;
            v0_1 = new long[v0 + v1];
            if(v1 != 0) {
                System.arraycopy(this.zzayj, 0, v0_1, 0, v1);
                goto label_54;
            label_66:
                this.zzawq = Integer.valueOf(arg7.zzuy());
                continue;
            }

            goto label_54;
        }

        return this;
    label_54:
        while(v1 < v0_1.length - 1) {
            v0_1[v1] = arg7.zzuz();
            arg7.zzug();
            ++v1;
        }

        v0_1[v1] = arg7.zzuz();
        this.zzayj = v0_1;
        goto label_0;
    label_16:
        while(arg7.zzyr() > 0) {
            arg7.zzuz();
            ++v3;
        }

        arg7.zzby(v1);
        v1 = this.zzayj == null ? 0 : this.zzayj.length;
        long[] v3_1 = new long[v3 + v1];
        if(v1 != 0) {
            System.arraycopy(this.zzayj, 0, v3_1, 0, v1);
        }

        while(v1 < v3_1.length) {
            v3_1[v1] = arg7.zzuz();
            ++v1;
        }

        this.zzayj = v3_1;
        arg7.zzar(v0);
        goto label_0;
    }

    public final void zza(zzyy arg6) {
        if(this.zzawq != null) {
            arg6.zzd(1, this.zzawq.intValue());
        }

        if(this.zzayj != null && this.zzayj.length > 0) {
            int v0;
            for(v0 = 0; v0 < this.zzayj.length; ++v0) {
                arg6.zzi(2, this.zzayj[v0]);
            }
        }

        super.zza(arg6);
    }

    protected final int zzf() {
        int v0 = super.zzf();
        if(this.zzawq != null) {
            v0 += zzyy.zzh(1, this.zzawq.intValue());
        }

        if(this.zzayj != null && this.zzayj.length > 0) {
            int v1 = 0;
            int v3 = 0;
            while(v1 < this.zzayj.length) {
                v3 += zzyy.zzbi(this.zzayj[v1]);
                ++v1;
            }

            v0 = v0 + v3 + this.zzayj.length;
        }

        return v0;
    }

    public static zzgk[] zzmt() {
        if(zzgk.zzayi == null) {
            Object v0 = zzze.zzcfl;
            __monitor_enter(v0);
            try {
                if(zzgk.zzayi == null) {
                    zzgk.zzayi = new zzgk[0];
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
        return zzgk.zzayi;
    }
}

