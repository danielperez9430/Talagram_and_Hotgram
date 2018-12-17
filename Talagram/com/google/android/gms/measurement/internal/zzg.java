package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;

final class zzg {
    private final zzbt zzadj;
    private long zzadt;
    private String zzafw;
    private String zzafx;
    private String zzafy;
    private String zzafz;
    private long zzaga;
    private long zzagb;
    private long zzagc;
    private long zzagd;
    private String zzage;
    private long zzagf;
    private boolean zzagg;
    private long zzagh;
    private boolean zzagi;
    private boolean zzagj;
    private String zzagk;
    private long zzagl;
    private long zzagm;
    private long zzagn;
    private long zzago;
    private long zzagp;
    private long zzagq;
    private String zzagr;
    private boolean zzags;
    private long zzagt;
    private long zzagu;
    private String zzts;
    private final String zztt;

    zzg(zzbt arg1, String arg2) {
        super();
        Preconditions.checkNotNull(arg1);
        Preconditions.checkNotEmpty(arg2);
        this.zzadj = arg1;
        this.zztt = arg2;
        this.zzadj.zzgn().zzaf();
    }

    public final String getAppInstanceId() {
        this.zzadj.zzgn().zzaf();
        return this.zzafw;
    }

    public final String getFirebaseInstanceId() {
        this.zzadj.zzgn().zzaf();
        return this.zzafz;
    }

    public final String getGmpAppId() {
        this.zzadj.zzgn().zzaf();
        return this.zzafx;
    }

    public final boolean isMeasurementEnabled() {
        this.zzadj.zzgn().zzaf();
        return this.zzagg;
    }

    public final void setAppVersion(String arg3) {
        this.zzadj.zzgn().zzaf();
        this.zzags |= zzfk.zzu(this.zzts, arg3) ^ 1;
        this.zzts = arg3;
    }

    public final void setMeasurementEnabled(boolean arg3) {
        this.zzadj.zzgn().zzaf();
        boolean v0 = this.zzags;
        int v1 = this.zzagg != arg3 ? 1 : 0;
        this.zzags = (((int)v0)) | v1;
        this.zzagg = arg3;
    }

    public final void zzaa(long arg5) {
        this.zzadj.zzgn().zzaf();
        boolean v0 = this.zzags;
        int v1 = this.zzagl != arg5 ? 1 : 0;
        this.zzags = (((int)v0)) | v1;
        this.zzagl = arg5;
    }

    public final void zzab(long arg5) {
        this.zzadj.zzgn().zzaf();
        boolean v0 = this.zzags;
        int v1 = this.zzagm != arg5 ? 1 : 0;
        this.zzags = (((int)v0)) | v1;
        this.zzagm = arg5;
    }

    public final void zzac(long arg5) {
        this.zzadj.zzgn().zzaf();
        boolean v0 = this.zzags;
        int v1 = this.zzagn != arg5 ? 1 : 0;
        this.zzags = (((int)v0)) | v1;
        this.zzagn = arg5;
    }

    public final void zzad(long arg5) {
        this.zzadj.zzgn().zzaf();
        boolean v0 = this.zzags;
        int v1 = this.zzago != arg5 ? 1 : 0;
        this.zzags = (((int)v0)) | v1;
        this.zzago = arg5;
    }

    public final void zzae(long arg5) {
        this.zzadj.zzgn().zzaf();
        boolean v0 = this.zzags;
        int v1 = this.zzagq != arg5 ? 1 : 0;
        this.zzags = (((int)v0)) | v1;
        this.zzagq = arg5;
    }

    public final void zzaf(long arg5) {
        this.zzadj.zzgn().zzaf();
        boolean v0 = this.zzags;
        int v1 = this.zzagp != arg5 ? 1 : 0;
        this.zzags = (((int)v0)) | v1;
        this.zzagp = arg5;
    }

    public final void zzag(long arg5) {
        this.zzadj.zzgn().zzaf();
        boolean v0 = this.zzags;
        int v1 = this.zzagh != arg5 ? 1 : 0;
        this.zzags = (((int)v0)) | v1;
        this.zzagh = arg5;
    }

    public final String zzak() {
        this.zzadj.zzgn().zzaf();
        return this.zzts;
    }

    public final String zzal() {
        this.zzadj.zzgn().zzaf();
        return this.zztt;
    }

    public final void zzam(String arg3) {
        this.zzadj.zzgn().zzaf();
        this.zzags |= zzfk.zzu(this.zzafw, arg3) ^ 1;
        this.zzafw = arg3;
    }

    public final void zzan(String arg3) {
        this.zzadj.zzgn().zzaf();
        if(TextUtils.isEmpty(((CharSequence)arg3))) {
            arg3 = null;
        }

        this.zzags |= zzfk.zzu(this.zzafx, arg3) ^ 1;
        this.zzafx = arg3;
    }

    public final void zzao(String arg3) {
        this.zzadj.zzgn().zzaf();
        if(TextUtils.isEmpty(((CharSequence)arg3))) {
            arg3 = null;
        }

        this.zzags |= zzfk.zzu(this.zzagk, arg3) ^ 1;
        this.zzagk = arg3;
    }

    public final void zzap(String arg3) {
        this.zzadj.zzgn().zzaf();
        this.zzags |= zzfk.zzu(this.zzafy, arg3) ^ 1;
        this.zzafy = arg3;
    }

    public final void zzaq(String arg3) {
        this.zzadj.zzgn().zzaf();
        this.zzags |= zzfk.zzu(this.zzafz, arg3) ^ 1;
        this.zzafz = arg3;
    }

    public final void zzar(String arg3) {
        this.zzadj.zzgn().zzaf();
        this.zzags |= zzfk.zzu(this.zzage, arg3) ^ 1;
        this.zzage = arg3;
    }

    public final void zzas(String arg3) {
        this.zzadj.zzgn().zzaf();
        this.zzags |= zzfk.zzu(this.zzagr, arg3) ^ 1;
        this.zzagr = arg3;
    }

    public final void zze(boolean arg2) {
        this.zzadj.zzgn().zzaf();
        boolean v0 = this.zzagi != arg2 ? true : false;
        this.zzags = v0;
        this.zzagi = arg2;
    }

    public final void zzf(boolean arg2) {
        this.zzadj.zzgn().zzaf();
        boolean v0 = this.zzagj != arg2 ? true : false;
        this.zzags = v0;
        this.zzagj = arg2;
    }

    public final void zzgv() {
        this.zzadj.zzgn().zzaf();
        this.zzags = false;
    }

    public final String zzgw() {
        this.zzadj.zzgn().zzaf();
        return this.zzagk;
    }

    public final String zzgx() {
        this.zzadj.zzgn().zzaf();
        return this.zzafy;
    }

    public final long zzgy() {
        this.zzadj.zzgn().zzaf();
        return this.zzagb;
    }

    public final long zzgz() {
        this.zzadj.zzgn().zzaf();
        return this.zzagc;
    }

    public final long zzha() {
        this.zzadj.zzgn().zzaf();
        return this.zzagd;
    }

    public final String zzhb() {
        this.zzadj.zzgn().zzaf();
        return this.zzage;
    }

    public final long zzhc() {
        this.zzadj.zzgn().zzaf();
        return this.zzadt;
    }

    public final long zzhd() {
        this.zzadj.zzgn().zzaf();
        return this.zzagf;
    }

    public final long zzhe() {
        this.zzadj.zzgn().zzaf();
        return this.zzaga;
    }

    public final long zzhf() {
        this.zzadj.zzgn().zzaf();
        return this.zzagt;
    }

    public final long zzhg() {
        this.zzadj.zzgn().zzaf();
        return this.zzagu;
    }

    public final void zzhh() {
        this.zzadj.zzgn().zzaf();
        long v0 = this.zzaga + 1;
        if(v0 > 2147483647) {
            this.zzadj.zzgo().zzjg().zzg("Bundle index overflow. appId", zzap.zzbv(this.zztt));
            v0 = 0;
        }

        this.zzags = true;
        this.zzaga = v0;
    }

    public final long zzhi() {
        this.zzadj.zzgn().zzaf();
        return this.zzagl;
    }

    public final long zzhj() {
        this.zzadj.zzgn().zzaf();
        return this.zzagm;
    }

    public final long zzhk() {
        this.zzadj.zzgn().zzaf();
        return this.zzagn;
    }

    public final long zzhl() {
        this.zzadj.zzgn().zzaf();
        return this.zzago;
    }

    public final long zzhm() {
        this.zzadj.zzgn().zzaf();
        return this.zzagq;
    }

    public final long zzhn() {
        this.zzadj.zzgn().zzaf();
        return this.zzagp;
    }

    public final String zzho() {
        this.zzadj.zzgn().zzaf();
        return this.zzagr;
    }

    public final String zzhp() {
        this.zzadj.zzgn().zzaf();
        String v0 = this.zzagr;
        this.zzas(null);
        return v0;
    }

    public final long zzhq() {
        this.zzadj.zzgn().zzaf();
        return this.zzagh;
    }

    public final boolean zzhr() {
        this.zzadj.zzgn().zzaf();
        return this.zzagi;
    }

    public final boolean zzhs() {
        this.zzadj.zzgn().zzaf();
        return this.zzagj;
    }

    public final void zzs(long arg5) {
        this.zzadj.zzgn().zzaf();
        boolean v0 = this.zzags;
        int v1 = this.zzagb != arg5 ? 1 : 0;
        this.zzags = (((int)v0)) | v1;
        this.zzagb = arg5;
    }

    public final void zzt(long arg5) {
        this.zzadj.zzgn().zzaf();
        boolean v0 = this.zzags;
        int v1 = this.zzagc != arg5 ? 1 : 0;
        this.zzags = (((int)v0)) | v1;
        this.zzagc = arg5;
    }

    public final void zzu(long arg5) {
        this.zzadj.zzgn().zzaf();
        boolean v0 = this.zzags;
        int v1 = this.zzagd != arg5 ? 1 : 0;
        this.zzags = (((int)v0)) | v1;
        this.zzagd = arg5;
    }

    public final void zzv(long arg5) {
        this.zzadj.zzgn().zzaf();
        boolean v0 = this.zzags;
        int v1 = this.zzadt != arg5 ? 1 : 0;
        this.zzags = (((int)v0)) | v1;
        this.zzadt = arg5;
    }

    public final void zzw(long arg5) {
        this.zzadj.zzgn().zzaf();
        boolean v0 = this.zzags;
        int v1 = this.zzagf != arg5 ? 1 : 0;
        this.zzags = (((int)v0)) | v1;
        this.zzagf = arg5;
    }

    public final void zzx(long arg7) {
        // Method was not decompiled
    }

    public final void zzy(long arg5) {
        this.zzadj.zzgn().zzaf();
        boolean v0 = this.zzags;
        int v1 = this.zzagt != arg5 ? 1 : 0;
        this.zzags = (((int)v0)) | v1;
        this.zzagt = arg5;
    }

    public final void zzz(long arg5) {
        this.zzadj.zzgn().zzaf();
        boolean v0 = this.zzags;
        int v1 = this.zzagu != arg5 ? 1 : 0;
        this.zzags = (((int)v0)) | v1;
        this.zzagu = arg5;
    }
}

