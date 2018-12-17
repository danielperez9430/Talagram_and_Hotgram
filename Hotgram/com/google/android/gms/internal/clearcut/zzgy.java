package com.google.android.gms.internal.clearcut;

public final class zzgy extends zzfu implements Cloneable {
    private String[] zzbiw;
    private String[] zzbix;
    private int[] zzbiy;
    private long[] zzbiz;
    private long[] zzbja;

    public zzgy() {
        super();
        this.zzbiw = zzgb.zzsc;
        this.zzbix = zzgb.zzsc;
        this.zzbiy = zzgb.zzrx;
        this.zzbiz = zzgb.zzry;
        this.zzbja = zzgb.zzry;
        this.zzrj = null;
        this.zzrs = -1;
    }

    public final Object clone() {
        return this.zzgb();
    }

    public final boolean equals(Object arg5) {
        if((((zzgy)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzgy)) {
            return 0;
        }

        if(!zzfy.equals(this.zzbiw, ((zzgy)arg5).zzbiw)) {
            return 0;
        }

        if(!zzfy.equals(this.zzbix, ((zzgy)arg5).zzbix)) {
            return 0;
        }

        if(!zzfy.equals(this.zzbiy, ((zzgy)arg5).zzbiy)) {
            return 0;
        }

        if(!zzfy.equals(this.zzbiz, ((zzgy)arg5).zzbiz)) {
            return 0;
        }

        if(!zzfy.equals(this.zzbja, ((zzgy)arg5).zzbja)) {
            return 0;
        }

        if(this.zzrj != null) {
            if(this.zzrj.isEmpty()) {
            }
            else {
                return this.zzrj.equals(((zzgy)arg5).zzrj);
            }
        }

        if(((zzgy)arg5).zzrj != null) {
            if(((zzgy)arg5).zzrj.isEmpty()) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public final int hashCode() {
        int v0 = ((((((this.getClass().getName().hashCode() + 527) * 31 + zzfy.hashCode(this.zzbiw)) * 31 + zzfy.hashCode(this.zzbix)) * 31 + zzfy.hashCode(this.zzbiy)) * 31 + zzfy.hashCode(this.zzbiz)) * 31 + zzfy.hashCode(this.zzbja)) * 31;
        int v1 = this.zzrj == null || (this.zzrj.isEmpty()) ? 0 : this.zzrj.hashCode();
        return v0 + v1;
    }

    public final void zza(zzfs arg7) {
        String v2;
        int v1 = 0;
        if(this.zzbiw != null && this.zzbiw.length > 0) {
            int v0;
            for(v0 = 0; v0 < this.zzbiw.length; ++v0) {
                v2 = this.zzbiw[v0];
                if(v2 != null) {
                    arg7.zza(1, v2);
                }
            }
        }

        if(this.zzbix != null && this.zzbix.length > 0) {
            for(v0 = 0; v0 < this.zzbix.length; ++v0) {
                v2 = this.zzbix[v0];
                if(v2 != null) {
                    arg7.zza(2, v2);
                }
            }
        }

        if(this.zzbiy != null && this.zzbiy.length > 0) {
            for(v0 = 0; v0 < this.zzbiy.length; ++v0) {
                arg7.zzc(3, this.zzbiy[v0]);
            }
        }

        if(this.zzbiz != null && this.zzbiz.length > 0) {
            for(v0 = 0; v0 < this.zzbiz.length; ++v0) {
                arg7.zzi(4, this.zzbiz[v0]);
            }
        }

        if(this.zzbja != null && this.zzbja.length > 0) {
            while(v1 < this.zzbja.length) {
                arg7.zzi(5, this.zzbja[v1]);
                ++v1;
            }
        }

        super.zza(arg7);
    }

    protected final int zzen() {
        String v5;
        int v4;
        int v3;
        int v1;
        int v0 = super.zzen();
        int v2 = 0;
        if(this.zzbiw != null && this.zzbiw.length > 0) {
            v1 = 0;
            v3 = 0;
            v4 = 0;
            while(v1 < this.zzbiw.length) {
                v5 = this.zzbiw[v1];
                if(v5 != null) {
                    ++v4;
                    v3 += zzfs.zzh(v5);
                }

                ++v1;
            }

            v0 = v0 + v3 + v4;
        }

        if(this.zzbix != null && this.zzbix.length > 0) {
            v1 = 0;
            v3 = 0;
            v4 = 0;
            while(v1 < this.zzbix.length) {
                v5 = this.zzbix[v1];
                if(v5 != null) {
                    ++v4;
                    v3 += zzfs.zzh(v5);
                }

                ++v1;
            }

            v0 = v0 + v3 + v4;
        }

        if(this.zzbiy != null && this.zzbiy.length > 0) {
            v1 = 0;
            v3 = 0;
            while(v1 < this.zzbiy.length) {
                v3 += zzfs.zzs(this.zzbiy[v1]);
                ++v1;
            }

            v0 = v0 + v3 + this.zzbiy.length;
        }

        if(this.zzbiz != null && this.zzbiz.length > 0) {
            v1 = 0;
            v3 = 0;
            while(v1 < this.zzbiz.length) {
                v3 += zzfs.zzo(this.zzbiz[v1]);
                ++v1;
            }

            v0 = v0 + v3 + this.zzbiz.length;
        }

        if(this.zzbja != null && this.zzbja.length > 0) {
            v1 = 0;
            while(v2 < this.zzbja.length) {
                v1 += zzfs.zzo(this.zzbja[v2]);
                ++v2;
            }

            v0 = v0 + v1 + this.zzbja.length;
        }

        return v0;
    }

    public final zzfu zzeo() {
        return ((zzfz)this).clone();
    }

    public final zzfz zzep() {
        return ((zzfz)this).clone();
    }

    private final zzgy zzgb() {
        // Method was not decompiled
    }
}

