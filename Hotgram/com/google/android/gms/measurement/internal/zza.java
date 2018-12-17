package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.f.a;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.util.Iterator;
import java.util.Map;

public final class zza extends zze {
    private final Map zzafq;
    private final Map zzafr;
    private long zzafs;

    public zza(zzbt arg1) {
        super(arg1);
        this.zzafr = new a();
        this.zzafq = new a();
    }

    public final void beginAdUnitExposure(String arg3, long arg4) {
        if(arg3 != null) {
            if(arg3.length() == 0) {
            }
            else {
                ((zzco)this).zzgn().zzc(new zzb(this, arg3, arg4));
                return;
            }
        }

        ((zzco)this).zzgo().zzjd().zzbx("Ad unit id must be a non-empty string");
    }

    public final void endAdUnitExposure(String arg3, long arg4) {
        if(arg3 != null) {
            if(arg3.length() == 0) {
            }
            else {
                ((zzco)this).zzgn().zzc(new zzc(this, arg3, arg4));
                return;
            }
        }

        ((zzco)this).zzgo().zzjd().zzbx("Ad unit id must be a non-empty string");
    }

    public final Context getContext() {
        return super.getContext();
    }

    static void zza(zza arg0, String arg1, long arg2) {
        arg0.zza(arg1, arg2);
    }

    static void zza(zza arg0, long arg1) {
        arg0.zzr(arg1);
    }

    private final void zza(long arg4, zzdn arg6) {
        if(arg6 == null) {
            ((zzco)this).zzgo().zzjl().zzbx("Not logging ad exposure. No active activity");
            return;
        }

        if(arg4 < 1000) {
            ((zzco)this).zzgo().zzjl().zzg("Not logging ad exposure. Less than 1000 ms. exposure", Long.valueOf(arg4));
            return;
        }

        Bundle v0 = new Bundle();
        v0.putLong("_xt", arg4);
        zzdo.zza(arg6, v0, true);
        ((zze)this).zzge().logEvent("am", "_xa", v0);
    }

    private final void zza(String arg4, long arg5) {
        ((zzco)this).zzgb();
        ((zzco)this).zzaf();
        Preconditions.checkNotEmpty(arg4);
        if(this.zzafr.isEmpty()) {
            this.zzafs = arg5;
        }

        Object v0 = this.zzafr.get(arg4);
        if(v0 != null) {
            this.zzafr.put(arg4, Integer.valueOf(((Integer)v0).intValue() + 1));
            return;
        }

        if(this.zzafr.size() >= 100) {
            ((zzco)this).zzgo().zzjg().zzbx("Too many ads visible");
            return;
        }

        this.zzafr.put(arg4, Integer.valueOf(1));
        this.zzafq.put(arg4, Long.valueOf(arg5));
    }

    private final void zza(String arg4, long arg5, zzdn arg7) {
        if(arg7 == null) {
            ((zzco)this).zzgo().zzjl().zzbx("Not logging ad unit exposure. No active activity");
            return;
        }

        if(arg5 < 1000) {
            ((zzco)this).zzgo().zzjl().zzg("Not logging ad unit exposure. Less than 1000 ms. exposure", Long.valueOf(arg5));
            return;
        }

        Bundle v0 = new Bundle();
        v0.putString("_ai", arg4);
        v0.putLong("_xt", arg5);
        zzdo.zza(arg7, v0, true);
        ((zze)this).zzge().logEvent("am", "_xu", v0);
    }

    public final void zzaf() {
        super.zzaf();
    }

    static void zzb(zza arg0, String arg1, long arg2) {
        arg0.zzb(arg1, arg2);
    }

    private final void zzb(String arg7, long arg8) {
        ((zzco)this).zzgb();
        ((zzco)this).zzaf();
        Preconditions.checkNotEmpty(arg7);
        Object v0 = this.zzafr.get(arg7);
        if(v0 != null) {
            zzdn v1 = ((zze)this).zzgh().zzla();
            int v0_1 = ((Integer)v0).intValue() - 1;
            if(v0_1 == 0) {
                this.zzafr.remove(arg7);
                v0 = this.zzafq.get(arg7);
                if(v0 == null) {
                    ((zzco)this).zzgo().zzjd().zzbx("First ad unit exposure time was never set");
                }
                else {
                    long v2 = arg8 - ((Long)v0).longValue();
                    this.zzafq.remove(arg7);
                    this.zza(arg7, v2, v1);
                }

                if(this.zzafr.isEmpty()) {
                    long v4 = 0;
                    if(this.zzafs == v4) {
                        ((zzco)this).zzgo().zzjd().zzbx("First ad exposure time was never set");
                        return;
                    }
                    else {
                        this.zza(arg8 - this.zzafs, v1);
                        this.zzafs = v4;
                    }
                }

                return;
            }

            this.zzafr.put(arg7, Integer.valueOf(v0_1));
            return;
        }

        ((zzco)this).zzgo().zzjd().zzg("Call to endAdUnitExposure for unknown ad unit id", arg7);
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

    public final void zzq(long arg6) {
        zzdn v0 = ((zze)this).zzgh().zzla();
        Iterator v1 = this.zzafq.keySet().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            this.zza(((String)v2), arg6 - this.zzafq.get(v2).longValue(), v0);
        }

        if(!this.zzafq.isEmpty()) {
            this.zza(arg6 - this.zzafs, v0);
        }

        this.zzr(arg6);
    }

    private final void zzr(long arg5) {
        Iterator v0 = this.zzafq.keySet().iterator();
        while(v0.hasNext()) {
            this.zzafq.put(v0.next(), Long.valueOf(arg5));
        }

        if(!this.zzafq.isEmpty()) {
            this.zzafs = arg5;
        }
    }
}

