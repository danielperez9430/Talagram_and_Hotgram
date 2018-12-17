package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzdx;

public final class zzeq extends zzf {
    private Handler handler;
    @VisibleForTesting private long zzasw;
    private final zzv zzasx;
    private final zzv zzasy;

    zzeq(zzbt arg3) {
        super(arg3);
        this.zzasx = new zzer(this, this.zzadj);
        this.zzasy = new zzes(this, this.zzadj);
        this.zzasw = ((zzco)this).zzbx().elapsedRealtime();
    }

    public final Context getContext() {
        return super.getContext();
    }

    static void zza(zzeq arg0, long arg1) {
        arg0.zzal(arg1);
    }

    static void zza(zzeq arg0) {
        arg0.zzll();
    }

    public final void zzaf() {
        super.zzaf();
    }

    private final void zzal(long arg6) {
        // Method was not decompiled
    }

    final void zzam(long arg7) {
        // Method was not decompiled
    }

    private final void zzan(long arg6) {
        ((zzco)this).zzaf();
        this.zzli();
        this.zzasx.cancel();
        this.zzasy.cancel();
        ((zzco)this).zzgo().zzjl().zzg("Activity paused, time", Long.valueOf(arg6));
        if(this.zzasw != 0) {
            ((zzco)this).zzgp().zzanu.set(((zzco)this).zzgp().zzanu.get() + (arg6 - this.zzasw));
        }
    }

    private final void zzao(long arg9) {
        Long v3;
        String v2;
        String v1;
        zzcs v0;
        ((zzco)this).zzaf();
        ((zzco)this).zzgo().zzjl().zzg("Session started, time", Long.valueOf(((zzco)this).zzbx().elapsedRealtime()));
        long v6 = 1000;
        if(((zzco)this).zzgq().zzbi(((zze)this).zzgf().zzal())) {
            v0 = ((zze)this).zzge();
            v1 = "auto";
            v2 = "_sid";
            v3 = Long.valueOf(arg9 / v6);
        }
        else {
            v0 = ((zze)this).zzge();
            v1 = "auto";
            v2 = "_sid";
            Object v3_1 = null;
        }

        v0.zza(v1, v2, v3, arg9);
        ((zzco)this).zzgp().zzanr.set(false);
        Bundle v5 = new Bundle();
        if(((zzco)this).zzgq().zzbi(((zze)this).zzgf().zzal())) {
            v5.putLong("_sid", arg9 / v6);
        }

        ((zze)this).zzge().zza("auto", "_s", arg9, v5);
        ((zzco)this).zzgp().zzant.set(arg9);
    }

    static void zzb(zzeq arg0, long arg1) {
        arg0.zzan(arg1);
    }

    public final Clock zzbx() {
        return super.zzbx();
    }

    public final void zzga() {
        super.zzga();
    }

    public final void zzgb() {
        super.zzgb();
    }

    public final void zzgc() {
        super.zzgc();
    }

    public final zza zzgd() {
        return super.zzgd();
    }

    public final zzcs zzge() {
        return super.zzge();
    }

    public final zzaj zzgf() {
        return super.zzgf();
    }

    public final zzdr zzgg() {
        return super.zzgg();
    }

    public final zzdo zzgh() {
        return super.zzgh();
    }

    public final zzal zzgi() {
        return super.zzgi();
    }

    public final zzeq zzgj() {
        return super.zzgj();
    }

    public final zzx zzgk() {
        return super.zzgk();
    }

    public final zzan zzgl() {
        return super.zzgl();
    }

    public final zzfk zzgm() {
        return super.zzgm();
    }

    public final zzbo zzgn() {
        return super.zzgn();
    }

    public final zzap zzgo() {
        return super.zzgo();
    }

    public final zzba zzgp() {
        return super.zzgp();
    }

    public final zzn zzgq() {
        return super.zzgq();
    }

    public final zzk zzgr() {
        return super.zzgr();
    }

    protected final boolean zzgt() {
        return 0;
    }

    private final void zzli() {
        __monitor_enter(this);
        try {
            if(this.handler == null) {
                this.handler = new zzdx(Looper.getMainLooper());
            }

            __monitor_exit(this);
            return;
        label_10:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_10;
        }

        throw v0;
    }

    final void zzlj() {
        this.zzasx.cancel();
        this.zzasy.cancel();
        this.zzasw = 0;
    }

    @VisibleForTesting protected final void zzlk() {
        ((zzco)this).zzaf();
        this.zzao(((zzco)this).zzbx().currentTimeMillis());
    }

    private final void zzll() {
        ((zzco)this).zzaf();
        this.zzn(false);
        ((zze)this).zzgd().zzq(((zzco)this).zzbx().elapsedRealtime());
    }

    public final boolean zzn(boolean arg9) {
        ((zzco)this).zzaf();
        ((zzf)this).zzcl();
        long v0 = ((zzco)this).zzbx().elapsedRealtime();
        ((zzco)this).zzgp().zzant.set(((zzco)this).zzbx().currentTimeMillis());
        long v2 = v0 - this.zzasw;
        if(!arg9 && v2 < 1000) {
            ((zzco)this).zzgo().zzjl().zzg("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(v2));
            return 0;
        }

        ((zzco)this).zzgp().zzanu.set(v2);
        ((zzco)this).zzgo().zzjl().zzg("Recording user engagement, ms", Long.valueOf(v2));
        Bundle v9 = new Bundle();
        v9.putLong("_et", v2);
        zzdo.zza(((zze)this).zzgh().zzla(), v9, true);
        ((zze)this).zzge().logEvent("auto", "_e", v9);
        this.zzasw = v0;
        this.zzasy.cancel();
        this.zzasy.zzh(Math.max(0, 3600000 - ((zzco)this).zzgp().zzanu.get()));
        return 1;
    }
}

