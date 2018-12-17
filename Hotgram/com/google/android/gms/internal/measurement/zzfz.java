package com.google.android.gms.internal.measurement;

public final class zzfz extends zzza {
    public Integer zzavw;
    public String zzavx;
    public Boolean zzavy;
    public String[] zzavz;

    public zzfz() {
        super();
        this.zzavw = null;
        this.zzavx = null;
        this.zzavy = null;
        this.zzavz = zzzj.zzcfv;
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object arg5) {
        if((((zzfz)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzfz)) {
            return 0;
        }

        if(this.zzavw == null) {
            if(((zzfz)arg5).zzavw != null) {
                return 0;
            }
        }
        else if(!this.zzavw.equals(((zzfz)arg5).zzavw)) {
            return 0;
        }

        if(this.zzavx == null) {
            if(((zzfz)arg5).zzavx != null) {
                return 0;
            }
        }
        else if(!this.zzavx.equals(((zzfz)arg5).zzavx)) {
            return 0;
        }

        if(this.zzavy == null) {
            if(((zzfz)arg5).zzavy != null) {
                return 0;
            }
        }
        else if(!this.zzavy.equals(((zzfz)arg5).zzavy)) {
            return 0;
        }

        if(!zzze.equals(this.zzavz, ((zzfz)arg5).zzavz)) {
            return 0;
        }

        if(this.zzcfc != null) {
            if(this.zzcfc.isEmpty()) {
            }
            else {
                return this.zzcfc.equals(((zzfz)arg5).zzcfc);
            }
        }

        if(((zzfz)arg5).zzcfc != null) {
            if(((zzfz)arg5).zzcfc.isEmpty()) {
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
        int v1 = this.zzavw == null ? 0 : this.zzavw.intValue();
        v0 = (v0 + v1) * 31;
        v1 = this.zzavx == null ? 0 : this.zzavx.hashCode();
        v0 = (v0 + v1) * 31;
        v1 = this.zzavy == null ? 0 : this.zzavy.hashCode();
        v0 = ((v0 + v1) * 31 + zzze.hashCode(this.zzavz)) * 31;
        if(this.zzcfc != null) {
            if(this.zzcfc.isEmpty()) {
            }
            else {
                v2 = this.zzcfc.hashCode();
            }
        }

        return v0 + v2;
    }

    public final zzzg zza(zzyx arg1) {
        return this.zzd(arg1);
    }

    public final void zza(zzyy arg4) {
        if(this.zzavw != null) {
            arg4.zzd(1, this.zzavw.intValue());
        }

        if(this.zzavx != null) {
            arg4.zzb(2, this.zzavx);
        }

        if(this.zzavy != null) {
            arg4.zzb(3, this.zzavy.booleanValue());
        }

        if(this.zzavz != null && this.zzavz.length > 0) {
            int v0;
            for(v0 = 0; v0 < this.zzavz.length; ++v0) {
                String v1 = this.zzavz[v0];
                if(v1 != null) {
                    arg4.zzb(4, v1);
                }
            }
        }

        super.zza(arg4);
    }

    private final zzfz zzd(zzyx arg7) {
        String[] v0_1;
        int v1;
        while(true) {
        label_0:
            int v0 = arg7.zzug();
            if(v0 == 0) {
                return this;
            }

            if(v0 == 8) {
                goto label_45;
            }

            if(v0 == 18) {
                goto label_42;
            }

            if(v0 == 24) {
                goto label_38;
            }

            v1 = 34;
            if(v0 != v1) {
                if(super.zza(arg7, v0)) {
                    continue;
                }

                return this;
            }

            v0 = zzzj.zzb(arg7, v1);
            v1 = this.zzavz == null ? 0 : this.zzavz.length;
            v0_1 = new String[v0 + v1];
            if(v1 != 0) {
                System.arraycopy(this.zzavz, 0, v0_1, 0, v1);
                goto label_26;
            label_38:
                this.zzavy = Boolean.valueOf(arg7.zzum());
                continue;
            label_42:
                this.zzavx = arg7.readString();
                continue;
            label_45:
                v1 = arg7.getPosition();
                try {
                    int v2 = arg7.zzuy();
                    if(v2 >= 0 && v2 <= 6) {
                        this.zzavw = Integer.valueOf(v2);
                        continue;
                    }

                    StringBuilder v5 = new StringBuilder(41);
                    v5.append(v2);
                    v5.append(" is not a valid enum MatchType");
                    throw new IllegalArgumentException(v5.toString());
                }
                catch(IllegalArgumentException ) {
                    arg7.zzby(v1);
                    ((zzza)this).zza(arg7, v0);
                    continue;
                }
            }

            goto label_26;
        }

        return this;
    label_26:
        while(v1 < v0_1.length - 1) {
            v0_1[v1] = arg7.readString();
            arg7.zzug();
            ++v1;
        }

        v0_1[v1] = arg7.readString();
        this.zzavz = v0_1;
        goto label_0;
    }

    protected final int zzf() {
        int v0 = super.zzf();
        if(this.zzavw != null) {
            v0 += zzyy.zzh(1, this.zzavw.intValue());
        }

        if(this.zzavx != null) {
            v0 += zzyy.zzc(2, this.zzavx);
        }

        if(this.zzavy != null) {
            this.zzavy.booleanValue();
            v0 += zzyy.zzbb(3) + 1;
        }

        if(this.zzavz != null && this.zzavz.length > 0) {
            int v1 = 0;
            int v3 = 0;
            int v4 = 0;
            while(v1 < this.zzavz.length) {
                String v5 = this.zzavz[v1];
                if(v5 != null) {
                    ++v4;
                    v3 += zzyy.zzfx(v5);
                }

                ++v1;
            }

            v0 = v0 + v3 + v4;
        }

        return v0;
    }
}

