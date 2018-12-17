package com.google.android.gms.internal.config;

public final class zzaw extends zzbb {
    public zzas zzbp;
    public zzas zzbq;
    public zzas zzbr;
    public zzau zzbs;
    public zzax[] zzbt;

    public zzaw() {
        super();
        this.zzbp = null;
        this.zzbq = null;
        this.zzbr = null;
        this.zzbs = null;
        this.zzbt = zzax.zzw();
        this.zzch = null;
        this.zzcq = -1;
    }

    public final boolean equals(Object arg5) {
        if((((zzaw)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzaw)) {
            return 0;
        }

        if(this.zzbp == null) {
            if(((zzaw)arg5).zzbp != null) {
                return 0;
            }
        }
        else if(!this.zzbp.equals(((zzaw)arg5).zzbp)) {
            return 0;
        }

        if(this.zzbq == null) {
            if(((zzaw)arg5).zzbq != null) {
                return 0;
            }
        }
        else if(!this.zzbq.equals(((zzaw)arg5).zzbq)) {
            return 0;
        }

        if(this.zzbr == null) {
            if(((zzaw)arg5).zzbr != null) {
                return 0;
            }
        }
        else if(!this.zzbr.equals(((zzaw)arg5).zzbr)) {
            return 0;
        }

        if(this.zzbs == null) {
            if(((zzaw)arg5).zzbs != null) {
                return 0;
            }
        }
        else if(!this.zzbs.equals(((zzaw)arg5).zzbs)) {
            return 0;
        }

        if(!zzbf.equals(this.zzbt, ((zzaw)arg5).zzbt)) {
            return 0;
        }

        if(this.zzch != null) {
            if(this.zzch.isEmpty()) {
            }
            else {
                return this.zzch.equals(((zzaw)arg5).zzch);
            }
        }

        if(((zzaw)arg5).zzch != null) {
            if(((zzaw)arg5).zzch.isEmpty()) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public final int hashCode() {
        int v0 = this.getClass().getName().hashCode() + 527;
        zzas v1 = this.zzbp;
        v0 *= 31;
        int v2 = 0;
        int v1_1 = v1 == null ? 0 : v1.hashCode();
        v0 += v1_1;
        v1 = this.zzbq;
        v0 *= 31;
        v1_1 = v1 == null ? 0 : v1.hashCode();
        v0 += v1_1;
        v1 = this.zzbr;
        v0 *= 31;
        v1_1 = v1 == null ? 0 : v1.hashCode();
        v0 += v1_1;
        zzau v1_2 = this.zzbs;
        v0 *= 31;
        v1_1 = v1_2 == null ? 0 : v1_2.hashCode();
        v0 = ((v0 + v1_1) * 31 + zzbf.hashCode(this.zzbt)) * 31;
        if(this.zzch != null) {
            if(this.zzch.isEmpty()) {
            }
            else {
                v2 = this.zzch.hashCode();
            }
        }

        return v0 + v2;
    }

    public final zzbh zza(zzay arg5) {
        zzau v0_2;
        zzax[] v0_1;
        int v1;
        zzas v0_3;
        while(true) {
        label_0:
            int v0 = arg5.zzx();
            if(v0 == 0) {
                return this;
            }

            if(v0 == 10) {
                if(this.zzbp == null) {
                    this.zzbp = new zzas();
                }

                v0_3 = this.zzbp;
            }
            else if(v0 == 18) {
                if(this.zzbq == null) {
                    this.zzbq = new zzas();
                }

                v0_3 = this.zzbq;
            }
            else if(v0 == 26) {
                if(this.zzbr == null) {
                    this.zzbr = new zzas();
                }

                v0_3 = this.zzbr;
            }
            else if(v0 != 34) {
                v1 = 42;
                if(v0 != v1) {
                    if(super.zza(arg5, v0)) {
                        continue;
                    }

                    return this;
                }
                else {
                    v0 = zzbk.zzb(arg5, v1);
                    v1 = this.zzbt == null ? 0 : this.zzbt.length;
                    v0_1 = new zzax[v0 + v1];
                    if(v1 != 0) {
                        System.arraycopy(this.zzbt, 0, v0_1, 0, v1);
                    }

                    goto label_28;
                }
            }
            else {
                if(this.zzbs == null) {
                    this.zzbs = new zzau();
                }

                v0_2 = this.zzbs;
            }

            arg5.zza(((zzbh)v0_2));
        }

        return this;
    label_28:
        while(v1 < v0_1.length - 1) {
            v0_1[v1] = new zzax();
            arg5.zza(v0_1[v1]);
            arg5.zzx();
            ++v1;
        }

        v0_1[v1] = new zzax();
        arg5.zza(v0_1[v1]);
        this.zzbt = v0_1;
        goto label_0;
    }

    public final void zza(zzaz arg4) {
        if(this.zzbp != null) {
            arg4.zza(1, this.zzbp);
        }

        if(this.zzbq != null) {
            arg4.zza(2, this.zzbq);
        }

        if(this.zzbr != null) {
            arg4.zza(3, this.zzbr);
        }

        if(this.zzbs != null) {
            arg4.zza(4, this.zzbs);
        }

        if(this.zzbt != null && this.zzbt.length > 0) {
            int v0;
            for(v0 = 0; v0 < this.zzbt.length; ++v0) {
                zzax v1 = this.zzbt[v0];
                if(v1 != null) {
                    arg4.zza(5, ((zzbh)v1));
                }
            }
        }

        super.zza(arg4);
    }

    protected final int zzt() {
        int v0 = super.zzt();
        if(this.zzbp != null) {
            v0 += zzaz.zzb(1, this.zzbp);
        }

        if(this.zzbq != null) {
            v0 += zzaz.zzb(2, this.zzbq);
        }

        if(this.zzbr != null) {
            v0 += zzaz.zzb(3, this.zzbr);
        }

        if(this.zzbs != null) {
            v0 += zzaz.zzb(4, this.zzbs);
        }

        if(this.zzbt != null && this.zzbt.length > 0) {
            int v1;
            for(v1 = 0; v1 < this.zzbt.length; ++v1) {
                zzax v2 = this.zzbt[v1];
                if(v2 != null) {
                    v0 += zzaz.zzb(5, ((zzbh)v2));
                }
            }
        }

        return v0;
    }
}

