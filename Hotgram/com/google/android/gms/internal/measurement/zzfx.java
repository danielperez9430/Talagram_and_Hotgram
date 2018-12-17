package com.google.android.gms.internal.measurement;

public final class zzfx extends zzza {
    public Integer zzavo;
    public Boolean zzavp;
    public String zzavq;
    public String zzavr;
    public String zzavs;

    public zzfx() {
        super();
        this.zzavo = null;
        this.zzavp = null;
        this.zzavq = null;
        this.zzavr = null;
        this.zzavs = null;
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object arg5) {
        if((((zzfx)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzfx)) {
            return 0;
        }

        if(this.zzavo == null) {
            if(((zzfx)arg5).zzavo != null) {
                return 0;
            }
        }
        else if(!this.zzavo.equals(((zzfx)arg5).zzavo)) {
            return 0;
        }

        if(this.zzavp == null) {
            if(((zzfx)arg5).zzavp != null) {
                return 0;
            }
        }
        else if(!this.zzavp.equals(((zzfx)arg5).zzavp)) {
            return 0;
        }

        if(this.zzavq == null) {
            if(((zzfx)arg5).zzavq != null) {
                return 0;
            }
        }
        else if(!this.zzavq.equals(((zzfx)arg5).zzavq)) {
            return 0;
        }

        if(this.zzavr == null) {
            if(((zzfx)arg5).zzavr != null) {
                return 0;
            }
        }
        else if(!this.zzavr.equals(((zzfx)arg5).zzavr)) {
            return 0;
        }

        if(this.zzavs == null) {
            if(((zzfx)arg5).zzavs != null) {
                return 0;
            }
        }
        else if(!this.zzavs.equals(((zzfx)arg5).zzavs)) {
            return 0;
        }

        if(this.zzcfc != null) {
            if(this.zzcfc.isEmpty()) {
            }
            else {
                return this.zzcfc.equals(((zzfx)arg5).zzcfc);
            }
        }

        if(((zzfx)arg5).zzcfc != null) {
            if(((zzfx)arg5).zzcfc.isEmpty()) {
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
        int v1 = this.zzavo == null ? 0 : this.zzavo.intValue();
        v0 = (v0 + v1) * 31;
        v1 = this.zzavp == null ? 0 : this.zzavp.hashCode();
        v0 = (v0 + v1) * 31;
        v1 = this.zzavq == null ? 0 : this.zzavq.hashCode();
        v0 = (v0 + v1) * 31;
        v1 = this.zzavr == null ? 0 : this.zzavr.hashCode();
        v0 = (v0 + v1) * 31;
        v1 = this.zzavs == null ? 0 : this.zzavs.hashCode();
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

    public final zzzg zza(zzyx arg1) {
        return this.zzc(arg1);
    }

    public final void zza(zzyy arg3) {
        if(this.zzavo != null) {
            arg3.zzd(1, this.zzavo.intValue());
        }

        if(this.zzavp != null) {
            arg3.zzb(2, this.zzavp.booleanValue());
        }

        if(this.zzavq != null) {
            arg3.zzb(3, this.zzavq);
        }

        if(this.zzavr != null) {
            arg3.zzb(4, this.zzavr);
        }

        if(this.zzavs != null) {
            arg3.zzb(5, this.zzavs);
        }

        super.zza(arg3);
    }

    private final zzfx zzc(zzyx arg7) {
        while(true) {
            int v0 = arg7.zzug();
            if(v0 == 0) {
                return this;
            }

            if(v0 != 8) {
                if(v0 != 16) {
                    if(v0 != 26) {
                        if(v0 != 34) {
                            if(v0 != 42) {
                                if(super.zza(arg7, v0)) {
                                    continue;
                                }

                                return this;
                            }

                            this.zzavs = arg7.readString();
                            continue;
                        }

                        this.zzavr = arg7.readString();
                        continue;
                    }

                    this.zzavq = arg7.readString();
                    continue;
                }

                this.zzavp = Boolean.valueOf(arg7.zzum());
                continue;
            }

            int v1 = arg7.getPosition();
            try {
                int v2 = arg7.zzuy();
                if(v2 >= 0 && v2 <= 4) {
                    this.zzavo = Integer.valueOf(v2);
                    continue;
                }

                StringBuilder v5 = new StringBuilder(46);
                v5.append(v2);
                v5.append(" is not a valid enum ComparisonType");
                throw new IllegalArgumentException(v5.toString());
            }
            catch(IllegalArgumentException ) {
                arg7.zzby(v1);
                ((zzza)this).zza(arg7, v0);
                continue;
            }
        }

        return this;
    }

    protected final int zzf() {
        int v0 = super.zzf();
        if(this.zzavo != null) {
            v0 += zzyy.zzh(1, this.zzavo.intValue());
        }

        if(this.zzavp != null) {
            this.zzavp.booleanValue();
            v0 += zzyy.zzbb(2) + 1;
        }

        if(this.zzavq != null) {
            v0 += zzyy.zzc(3, this.zzavq);
        }

        if(this.zzavr != null) {
            v0 += zzyy.zzc(4, this.zzavr);
        }

        if(this.zzavs != null) {
            v0 += zzyy.zzc(5, this.zzavs);
        }

        return v0;
    }
}

