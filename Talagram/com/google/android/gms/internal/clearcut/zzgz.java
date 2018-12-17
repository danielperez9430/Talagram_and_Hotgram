package com.google.android.gms.internal.clearcut;

import java.util.Arrays;

public final class zzgz extends zzfu implements Cloneable {
    private byte[] zzbjb;
    private String zzbjc;
    private byte[][] zzbjd;
    private boolean zzbje;

    public zzgz() {
        super();
        this.zzbjb = zzgb.zzse;
        this.zzbjc = "";
        this.zzbjd = zzgb.zzsd;
        this.zzbje = false;
        this.zzrj = null;
        this.zzrs = -1;
    }

    public final Object clone() {
        return this.zzgc();
    }

    public final boolean equals(Object arg5) {
        if((((zzgz)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzgz)) {
            return 0;
        }

        if(!Arrays.equals(this.zzbjb, ((zzgz)arg5).zzbjb)) {
            return 0;
        }

        if(this.zzbjc == null) {
            if(((zzgz)arg5).zzbjc != null) {
                return 0;
            }
        }
        else if(!this.zzbjc.equals(((zzgz)arg5).zzbjc)) {
            return 0;
        }

        if(!zzfy.zza(this.zzbjd, ((zzgz)arg5).zzbjd)) {
            return 0;
        }

        if(this.zzrj != null) {
            if(this.zzrj.isEmpty()) {
            }
            else {
                return this.zzrj.equals(((zzgz)arg5).zzrj);
            }
        }

        if(((zzgz)arg5).zzrj != null) {
            if(((zzgz)arg5).zzrj.isEmpty()) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public final int hashCode() {
        int v0 = ((this.getClass().getName().hashCode() + 527) * 31 + Arrays.hashCode(this.zzbjb)) * 31;
        int v2 = 0;
        int v1 = this.zzbjc == null ? 0 : this.zzbjc.hashCode();
        v0 = (((v0 + v1) * 31 + zzfy.zza(this.zzbjd)) * 31 + 1237) * 31;
        if(this.zzrj != null) {
            if(this.zzrj.isEmpty()) {
            }
            else {
                v2 = this.zzrj.hashCode();
            }
        }

        return v0 + v2;
    }

    public final void zza(zzfs arg4) {
        if(!Arrays.equals(this.zzbjb, zzgb.zzse)) {
            arg4.zza(1, this.zzbjb);
        }

        if(this.zzbjd != null && this.zzbjd.length > 0) {
            int v0;
            for(v0 = 0; v0 < this.zzbjd.length; ++v0) {
                byte[] v1 = this.zzbjd[v0];
                if(v1 != null) {
                    arg4.zza(2, v1);
                }
            }
        }

        if(this.zzbjc != null && !this.zzbjc.equals("")) {
            arg4.zza(4, this.zzbjc);
        }

        super.zza(arg4);
    }

    protected final int zzen() {
        int v0 = super.zzen();
        if(!Arrays.equals(this.zzbjb, zzgb.zzse)) {
            v0 += zzfs.zzb(1, this.zzbjb);
        }

        if(this.zzbjd != null && this.zzbjd.length > 0) {
            int v1 = 0;
            int v3 = 0;
            int v4 = 0;
            while(v1 < this.zzbjd.length) {
                byte[] v5 = this.zzbjd[v1];
                if(v5 != null) {
                    ++v4;
                    v3 += zzfs.zzh(v5);
                }

                ++v1;
            }

            v0 = v0 + v3 + v4;
        }

        if(this.zzbjc != null && !this.zzbjc.equals("")) {
            v0 += zzfs.zzb(4, this.zzbjc);
        }

        return v0;
    }

    public final zzfu zzeo() {
        return ((zzfz)this).clone();
    }

    public final zzfz zzep() {
        return ((zzfz)this).clone();
    }

    private final zzgz zzgc() {
        // Method was not decompiled
    }
}

