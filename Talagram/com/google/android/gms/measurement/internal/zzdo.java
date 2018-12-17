package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.f.a;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Map;

public final class zzdo extends zzf {
    @VisibleForTesting protected zzdn zzaro;
    private volatile zzdn zzarp;
    private zzdn zzarq;
    private final Map zzarr;
    private zzdn zzars;
    private String zzart;

    public zzdo(zzbt arg1) {
        super(arg1);
        this.zzarr = new a();
    }

    public final Context getContext() {
        return super.getContext();
    }

    public final void onActivityCreated(Activity arg6, Bundle arg7) {
        if(arg7 == null) {
            return;
        }

        arg7 = arg7.getBundle("com.google.app_measurement.screen_service");
        if(arg7 == null) {
            return;
        }

        this.zzarr.put(arg6, new zzdn(arg7.getString("name"), arg7.getString("referrer_name"), arg7.getLong("id")));
    }

    public final void onActivityDestroyed(Activity arg2) {
        this.zzarr.remove(arg2);
    }

    public final void onActivityPaused(Activity arg3) {
        zzdn v3 = this.zze(arg3);
        this.zzarq = this.zzarp;
        this.zzarp = null;
        ((zzco)this).zzgn().zzc(new zzdq(this, v3));
    }

    public final void onActivityResumed(Activity arg5) {
        this.zza(arg5, this.zze(arg5), false);
        zza v5 = ((zze)this).zzgd();
        ((zzco)v5).zzgn().zzc(new zzd(v5, ((zzco)v5).zzbx().elapsedRealtime()));
    }

    public final void onActivitySaveInstanceState(Activity arg5, Bundle arg6) {
        if(arg6 == null) {
            return;
        }

        Object v5 = this.zzarr.get(arg5);
        if(v5 == null) {
            return;
        }

        Bundle v0 = new Bundle();
        v0.putLong("id", ((zzdn)v5).zzarm);
        v0.putString("name", ((zzdn)v5).zzuw);
        v0.putString("referrer_name", ((zzdn)v5).zzarl);
        arg6.putBundle("com.google.app_measurement.screen_service", v0);
    }

    public final void setCurrentScreen(Activity arg4, String arg5, String arg6) {
        if(this.zzarp == null) {
            ((zzco)this).zzgo().zzjg().zzbx("setCurrentScreen cannot be called while no activity active");
            return;
        }

        if(this.zzarr.get(arg4) == null) {
            ((zzco)this).zzgo().zzjg().zzbx("setCurrentScreen must be called with an activity in the activity lifecycle");
            return;
        }

        if(arg6 == null) {
            arg6 = zzdo.zzcn(arg4.getClass().getCanonicalName());
        }

        boolean v0 = this.zzarp.zzarl.equals(arg6);
        boolean v1 = zzfk.zzu(this.zzarp.zzuw, arg5);
        if((v0) && (v1)) {
            ((zzco)this).zzgo().zzji().zzbx("setCurrentScreen cannot be called with the same class and name");
            return;
        }

        int v0_1 = 100;
        if(arg5 != null && (arg5.length() <= 0 || arg5.length() > v0_1)) {
            ((zzco)this).zzgo().zzjg().zzg("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(arg5.length()));
            return;
        }

        if(arg6 != null && (arg6.length() <= 0 || arg6.length() > v0_1)) {
            ((zzco)this).zzgo().zzjg().zzg("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(arg6.length()));
            return;
        }

        zzar v0_2 = ((zzco)this).zzgo().zzjl();
        String v1_1 = "Setting current screen to name, class";
        String v2 = arg5 == null ? "null" : arg5;
        v0_2.zze(v1_1, v2, arg6);
        zzdn v0_3 = new zzdn(arg5, arg6, ((zzco)this).zzgm().zzmc());
        this.zzarr.put(arg4, v0_3);
        this.zza(arg4, v0_3, true);
    }

    public final void zza(String arg2, zzdn arg3) {
        ((zzco)this).zzaf();
        __monitor_enter(this);
        try {
            if(this.zzart == null || (this.zzart.equals(arg2)) || arg3 != null) {
                this.zzart = arg2;
                this.zzars = arg3;
            }

            __monitor_exit(this);
            return;
        label_13:
            __monitor_exit(this);
        }
        catch(Throwable v2) {
            goto label_13;
        }

        throw v2;
    }

    static void zza(zzdo arg0, zzdn arg1) {
        arg0.zza(arg1);
    }

    public static void zza(zzdn arg2, Bundle arg3, boolean arg4) {
        if(arg3 != null && arg2 != null && (!arg3.containsKey("_sc") || (arg4))) {
            if(arg2.zzuw != null) {
                arg3.putString("_sn", arg2.zzuw);
            }
            else {
                arg3.remove("_sn");
            }

            arg3.putString("_sc", arg2.zzarl);
            arg3.putLong("_si", arg2.zzarm);
            return;
        }

        if(arg3 != null && arg2 == null && (arg4)) {
            arg3.remove("_sn");
            arg3.remove("_sc");
            arg3.remove("_si");
        }
    }

    private final void zza(Activity arg6, zzdn arg7, boolean arg8) {
        zzdn v0 = this.zzarp == null ? this.zzarq : this.zzarp;
        if(arg7.zzarl == null) {
            arg7 = new zzdn(arg7.zzuw, zzdo.zzcn(arg6.getClass().getCanonicalName()), arg7.zzarm);
        }

        this.zzarq = this.zzarp;
        this.zzarp = arg7;
        ((zzco)this).zzgn().zzc(new zzdp(this, arg8, v0, arg7));
    }

    private final void zza(zzdn arg4) {
        ((zze)this).zzgd().zzq(((zzco)this).zzbx().elapsedRealtime());
        if(((zze)this).zzgj().zzn(arg4.zzarn)) {
            arg4.zzarn = false;
        }
    }

    public final void zzaf() {
        super.zzaf();
    }

    public final Clock zzbx() {
        return super.zzbx();
    }

    @VisibleForTesting private static String zzcn(String arg2) {
        String[] v2 = arg2.split("\\.");
        arg2 = v2.length > 0 ? v2[v2.length - 1] : "";
        int v1 = 100;
        if(arg2.length() > v1) {
            arg2 = arg2.substring(0, v1);
        }

        return arg2;
    }

    private final zzdn zze(Activity arg6) {
        Preconditions.checkNotNull(arg6);
        Object v0 = this.zzarr.get(arg6);
        if(v0 == null) {
            zzdn v1 = new zzdn(null, zzdo.zzcn(arg6.getClass().getCanonicalName()), ((zzco)this).zzgm().zzmc());
            this.zzarr.put(arg6, v1);
            zzdn v0_1 = v1;
        }

        return ((zzdn)v0);
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

    public final zzdn zzla() {
        ((zzf)this).zzcl();
        ((zzco)this).zzaf();
        return this.zzaro;
    }

    public final zzdn zzlb() {
        ((zzco)this).zzgb();
        return this.zzarp;
    }
}

