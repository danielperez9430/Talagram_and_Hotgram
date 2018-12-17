package com.google.android.gms.internal.clearcut;

public class zzfu extends zzfz {
    protected zzfw zzrj;

    public zzfu() {
        super();
    }

    public Object clone() {
        return this.zzeo();
    }

    public void zza(zzfs arg3) {
        if(this.zzrj == null) {
            return;
        }

        int v0;
        for(v0 = 0; v0 < this.zzrj.size(); ++v0) {
            this.zzrj.zzaq(v0).zza(arg3);
        }
    }

    protected int zzen() {
        if(this.zzrj != null) {
            int v0;
            for(v0 = 0; v0 < this.zzrj.size(); ++v0) {
                this.zzrj.zzaq(v0).zzen();
            }
        }

        return 0;
    }

    public zzfu zzeo() {
        zzfz v0 = super.zzep();
        zzfy.zza(this, ((zzfu)v0));
        return ((zzfu)v0);
    }

    public zzfz zzep() {
        return ((zzfz)this).clone();
    }
}

