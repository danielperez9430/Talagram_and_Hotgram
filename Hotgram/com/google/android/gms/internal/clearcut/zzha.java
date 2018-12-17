package com.google.android.gms.internal.clearcut;

import java.util.Arrays;

public final class zzha extends zzfu implements Cloneable {
    private String tag;
    public long zzbjf;
    public long zzbjg;
    private long zzbjh;
    public int zzbji;
    private String zzbjj;
    private int zzbjk;
    private boolean zzbjl;
    private zzhb[] zzbjm;
    private byte[] zzbjn;
    private zzd zzbjo;
    public byte[] zzbjp;
    private String zzbjq;
    private String zzbjr;
    private zzgy zzbjs;
    private String zzbjt;
    public long zzbju;
    private zzgz zzbjv;
    public byte[] zzbjw;
    private String zzbjx;
    private int zzbjy;
    private int[] zzbjz;
    private long zzbka;
    private zzs zzbkb;
    public boolean zzbkc;

    public zzha() {
        super();
        this.zzbjf = 0;
        this.zzbjg = 0;
        this.zzbjh = 0;
        this.tag = "";
        this.zzbji = 0;
        this.zzbjj = "";
        this.zzbjk = 0;
        this.zzbjl = false;
        this.zzbjm = zzhb.zzge();
        this.zzbjn = zzgb.zzse;
        this.zzbjo = null;
        this.zzbjp = zzgb.zzse;
        this.zzbjq = "";
        this.zzbjr = "";
        this.zzbjs = null;
        this.zzbjt = "";
        this.zzbju = 180000;
        this.zzbjv = null;
        this.zzbjw = zzgb.zzse;
        this.zzbjx = "";
        this.zzbjy = 0;
        this.zzbjz = zzgb.zzrx;
        this.zzbka = 0;
        this.zzbkb = null;
        this.zzbkc = false;
        this.zzrj = null;
        this.zzrs = -1;
    }

    public final Object clone() {
        return this.zzgd();
    }

    public final boolean equals(Object arg8) {
        if((((zzha)arg8)) == this) {
            return 1;
        }

        if(!(arg8 instanceof zzha)) {
            return 0;
        }

        if(this.zzbjf != ((zzha)arg8).zzbjf) {
            return 0;
        }

        if(this.zzbjg != ((zzha)arg8).zzbjg) {
            return 0;
        }

        if(this.tag == null) {
            if(((zzha)arg8).tag != null) {
                return 0;
            }
        }
        else if(!this.tag.equals(((zzha)arg8).tag)) {
            return 0;
        }

        if(this.zzbji != ((zzha)arg8).zzbji) {
            return 0;
        }

        if(this.zzbjj == null) {
            if(((zzha)arg8).zzbjj != null) {
                return 0;
            }
        }
        else if(!this.zzbjj.equals(((zzha)arg8).zzbjj)) {
            return 0;
        }

        if(!zzfy.equals(this.zzbjm, ((zzha)arg8).zzbjm)) {
            return 0;
        }

        if(!Arrays.equals(this.zzbjn, ((zzha)arg8).zzbjn)) {
            return 0;
        }

        if(this.zzbjo == null) {
            if(((zzha)arg8).zzbjo != null) {
                return 0;
            }
        }
        else if(!this.zzbjo.equals(((zzha)arg8).zzbjo)) {
            return 0;
        }

        if(!Arrays.equals(this.zzbjp, ((zzha)arg8).zzbjp)) {
            return 0;
        }

        if(this.zzbjq == null) {
            if(((zzha)arg8).zzbjq != null) {
                return 0;
            }
        }
        else if(!this.zzbjq.equals(((zzha)arg8).zzbjq)) {
            return 0;
        }

        if(this.zzbjr == null) {
            if(((zzha)arg8).zzbjr != null) {
                return 0;
            }
        }
        else if(!this.zzbjr.equals(((zzha)arg8).zzbjr)) {
            return 0;
        }

        if(this.zzbjs == null) {
            if(((zzha)arg8).zzbjs != null) {
                return 0;
            }
        }
        else if(!this.zzbjs.equals(((zzha)arg8).zzbjs)) {
            return 0;
        }

        if(this.zzbjt == null) {
            if(((zzha)arg8).zzbjt != null) {
                return 0;
            }
        }
        else if(!this.zzbjt.equals(((zzha)arg8).zzbjt)) {
            return 0;
        }

        if(this.zzbju != ((zzha)arg8).zzbju) {
            return 0;
        }

        if(this.zzbjv == null) {
            if(((zzha)arg8).zzbjv != null) {
                return 0;
            }
        }
        else if(!this.zzbjv.equals(((zzha)arg8).zzbjv)) {
            return 0;
        }

        if(!Arrays.equals(this.zzbjw, ((zzha)arg8).zzbjw)) {
            return 0;
        }

        if(this.zzbjx == null) {
            if(((zzha)arg8).zzbjx != null) {
                return 0;
            }
        }
        else if(!this.zzbjx.equals(((zzha)arg8).zzbjx)) {
            return 0;
        }

        if(!zzfy.equals(this.zzbjz, ((zzha)arg8).zzbjz)) {
            return 0;
        }

        if(this.zzbkb == null) {
            if(((zzha)arg8).zzbkb != null) {
                return 0;
            }
        }
        else if(!this.zzbkb.equals(((zzha)arg8).zzbkb)) {
            return 0;
        }

        if(this.zzbkc != ((zzha)arg8).zzbkc) {
            return 0;
        }

        if(this.zzrj != null) {
            if(this.zzrj.isEmpty()) {
            }
            else {
                return this.zzrj.equals(((zzha)arg8).zzrj);
            }
        }

        if(((zzha)arg8).zzrj != null) {
            if(((zzha)arg8).zzrj.isEmpty()) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public final int hashCode() {
        int v5 = 32;
        int v0 = (((this.getClass().getName().hashCode() + 527) * 31 + (((int)(this.zzbjf ^ this.zzbjf >>> v5)))) * 31 + (((int)(this.zzbjg ^ this.zzbjg >>> v5)))) * 31 * 31;
        int v2 = 0;
        int v1 = this.tag == null ? 0 : this.tag.hashCode();
        v0 = ((v0 + v1) * 31 + this.zzbji) * 31;
        v1 = this.zzbjj == null ? 0 : this.zzbjj.hashCode();
        v0 = (v0 + v1) * 31 * 31;
        v1 = 1237;
        v0 = ((v0 + v1) * 31 + zzfy.hashCode(this.zzbjm)) * 31 + Arrays.hashCode(this.zzbjn);
        zzd v3 = this.zzbjo;
        v0 *= 31;
        int v3_1 = v3 == null ? 0 : ((zzcg)v3).hashCode();
        v0 = ((v0 + v3_1) * 31 + Arrays.hashCode(this.zzbjp)) * 31;
        v3_1 = this.zzbjq == null ? 0 : this.zzbjq.hashCode();
        v0 = (v0 + v3_1) * 31;
        v3_1 = this.zzbjr == null ? 0 : this.zzbjr.hashCode();
        v0 += v3_1;
        zzgy v3_2 = this.zzbjs;
        v0 *= 31;
        v3_1 = v3_2 == null ? 0 : v3_2.hashCode();
        v0 = (v0 + v3_1) * 31;
        v3_1 = this.zzbjt == null ? 0 : this.zzbjt.hashCode();
        v0 = (v0 + v3_1) * 31 + (((int)(this.zzbju ^ this.zzbju >>> v5)));
        zzgz v3_3 = this.zzbjv;
        v0 *= 31;
        v3_1 = v3_3 == null ? 0 : v3_3.hashCode();
        v0 = ((v0 + v3_1) * 31 + Arrays.hashCode(this.zzbjw)) * 31;
        v3_1 = this.zzbjx == null ? 0 : this.zzbjx.hashCode();
        v0 = ((v0 + v3_1) * 31 * 31 + zzfy.hashCode(this.zzbjz)) * 31;
        zzs v3_4 = this.zzbkb;
        v0 *= 31;
        v3_1 = v3_4 == null ? 0 : ((zzcg)v3_4).hashCode();
        v0 = (v0 + v3_1) * 31;
        if(this.zzbkc) {
            v1 = 1231;
        }

        v0 = (v0 + v1) * 31;
        if(this.zzrj != null) {
            if(this.zzrj.isEmpty()) {
            }
            else {
                v2 = this.zzrj.hashCode();
            }
        }

        return v0 + v2;
    }

    public final void zza(zzfs arg9) {
        long v2 = 0;
        if(this.zzbjf != v2) {
            arg9.zzi(1, this.zzbjf);
        }

        if(this.tag != null && !this.tag.equals("")) {
            arg9.zza(2, this.tag);
        }

        int v1 = 0;
        if(this.zzbjm != null && this.zzbjm.length > 0) {
            int v0;
            for(v0 = 0; v0 < this.zzbjm.length; ++v0) {
                zzhb v4 = this.zzbjm[v0];
                if(v4 != null) {
                    arg9.zza(3, ((zzfz)v4));
                }
            }
        }

        if(!Arrays.equals(this.zzbjn, zzgb.zzse)) {
            arg9.zza(4, this.zzbjn);
        }

        if(!Arrays.equals(this.zzbjp, zzgb.zzse)) {
            arg9.zza(6, this.zzbjp);
        }

        if(this.zzbjs != null) {
            arg9.zza(7, this.zzbjs);
        }

        if(this.zzbjq != null && !this.zzbjq.equals("")) {
            arg9.zza(8, this.zzbjq);
        }

        if(this.zzbjo != null) {
            arg9.zze(9, this.zzbjo);
        }

        if(this.zzbji != 0) {
            arg9.zzc(11, this.zzbji);
        }

        if(this.zzbjr != null && !this.zzbjr.equals("")) {
            arg9.zza(13, this.zzbjr);
        }

        if(this.zzbjt != null && !this.zzbjt.equals("")) {
            arg9.zza(14, this.zzbjt);
        }

        if(this.zzbju != 180000) {
            long v4_1 = this.zzbju;
            arg9.zzb(15, 0);
            arg9.zzn(zzfs.zzj(v4_1));
        }

        if(this.zzbjv != null) {
            arg9.zza(16, this.zzbjv);
        }

        if(this.zzbjg != v2) {
            arg9.zzi(17, this.zzbjg);
        }

        if(!Arrays.equals(this.zzbjw, zzgb.zzse)) {
            arg9.zza(18, this.zzbjw);
        }

        if(this.zzbjz != null && this.zzbjz.length > 0) {
            while(v1 < this.zzbjz.length) {
                arg9.zzc(20, this.zzbjz[v1]);
                ++v1;
            }
        }

        if(this.zzbkb != null) {
            arg9.zze(23, this.zzbkb);
        }

        if(this.zzbjx != null && !this.zzbjx.equals("")) {
            arg9.zza(24, this.zzbjx);
        }

        if(this.zzbkc) {
            arg9.zzb(25, this.zzbkc);
        }

        if(this.zzbjj != null && !this.zzbjj.equals("")) {
            arg9.zza(26, this.zzbjj);
        }

        super.zza(arg9);
    }

    protected final int zzen() {
        // Method was not decompiled
    }

    public final zzfu zzeo() {
        return ((zzfz)this).clone();
    }

    public final zzfz zzep() {
        return ((zzfz)this).clone();
    }

    private final zzha zzgd() {
        // Method was not decompiled
    }
}

