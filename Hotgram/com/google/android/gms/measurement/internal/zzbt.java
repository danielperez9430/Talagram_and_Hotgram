package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzsl;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.concurrent.atomic.AtomicReference;

public class zzbt implements zzcq {
    private final boolean zzadv;
    private final String zzadx;
    private final long zzagx;
    private final zzk zzaiq;
    private static volatile zzbt zzapl;
    private final String zzapm;
    private final String zzapn;
    private final zzn zzapo;
    private final zzba zzapp;
    private final zzap zzapq;
    private final zzbo zzapr;
    private final zzeq zzaps;
    private final AppMeasurement zzapt;
    private final zzfk zzapu;
    private final zzan zzapv;
    private final zzdo zzapw;
    private final zzcs zzapx;
    private final zza zzapy;
    private zzal zzapz;
    private zzdr zzaqa;
    private zzx zzaqb;
    private zzaj zzaqc;
    private zzbg zzaqd;
    private Boolean zzaqe;
    private long zzaqf;
    private volatile Boolean zzaqg;
    private int zzaqh;
    private int zzaqi;
    private final Context zzri;
    private final Clock zzrz;
    private boolean zzvz;

    private zzbt(zzcr arg5) {
        String v1_1;
        zzar v0_8;
        super();
        this.zzvz = false;
        Preconditions.checkNotNull(arg5);
        this.zzaiq = new zzk(arg5.zzri);
        zzaf.zza(this.zzaiq);
        this.zzri = arg5.zzri;
        this.zzadx = arg5.zzadx;
        this.zzapm = arg5.zzapm;
        this.zzapn = arg5.zzapn;
        this.zzadv = arg5.zzadv;
        this.zzaqg = arg5.zzaqg;
        zzsl.init(this.zzri);
        this.zzrz = DefaultClock.getInstance();
        this.zzagx = this.zzrz.currentTimeMillis();
        this.zzapo = new zzn(this);
        zzba v0 = new zzba(this);
        ((zzcp)v0).zzq();
        this.zzapp = v0;
        zzap v0_1 = new zzap(this);
        ((zzcp)v0_1).zzq();
        this.zzapq = v0_1;
        zzfk v0_2 = new zzfk(this);
        ((zzcp)v0_2).zzq();
        this.zzapu = v0_2;
        zzan v0_3 = new zzan(this);
        ((zzcp)v0_3).zzq();
        this.zzapv = v0_3;
        this.zzapy = new zza(this);
        zzdo v0_4 = new zzdo(this);
        ((zzf)v0_4).zzq();
        this.zzapw = v0_4;
        zzcs v0_5 = new zzcs(this);
        ((zzf)v0_5).zzq();
        this.zzapx = v0_5;
        this.zzapt = new AppMeasurement(this);
        zzeq v0_6 = new zzeq(this);
        ((zzf)v0_6).zzq();
        this.zzaps = v0_6;
        zzbo v0_7 = new zzbo(this);
        ((zzcp)v0_7).zzq();
        this.zzapr = v0_7;
        if((this.zzri.getApplicationContext() instanceof Application)) {
            v0_5 = this.zzge();
            if((((zzco)v0_5).getContext().getApplicationContext() instanceof Application)) {
                Context v1 = ((zzco)v0_5).getContext().getApplicationContext();
                if(v0_5.zzaqv == null) {
                    v0_5.zzaqv = new zzdm(v0_5, null);
                }

                ((Application)v1).unregisterActivityLifecycleCallbacks(v0_5.zzaqv);
                ((Application)v1).registerActivityLifecycleCallbacks(v0_5.zzaqv);
                v0_8 = ((zzco)v0_5).zzgo().zzjl();
                v1_1 = "Registered activity lifecycle callback";
                goto label_99;
            }
        }
        else {
            v0_8 = this.zzgo().zzjg();
            v1_1 = "Application context is not an Application";
        label_99:
            v0_8.zzbx(v1_1);
        }

        this.zzapr.zzc(new zzbu(this, arg5));
    }

    public final Context getContext() {
        return this.zzri;
    }

    public final boolean isEnabled() {
        int v0_1;
        this.zzgn().zzaf();
        this.zzcl();
        if(this.zzapo.zzhu()) {
            return 0;
        }

        Boolean v0 = this.zzapo.zzhv();
        if(v0 == null) {
            v0_1 = GoogleServices.isMeasurementExplicitlyDisabled() ^ 1;
            if(v0_1 != 0 && this.zzaqg != null && (zzaf.zzalh.get().booleanValue())) {
                v0 = this.zzaqg;
                goto label_11;
            }
        }
        else {
        label_11:
            boolean v0_2 = v0.booleanValue();
        }

        return this.zzgp().zzh(((boolean)v0_1));
    }

    protected final void start() {
        this.zzgn().zzaf();
        long v2 = 0;
        if(this.zzgp().zzane.get() == v2) {
            this.zzgp().zzane.set(this.zzrz.currentTimeMillis());
        }

        if(Long.valueOf(this.zzgp().zzanj.get()).longValue() == v2) {
            this.zzgo().zzjl().zzg("Persisting first open", Long.valueOf(this.zzagx));
            this.zzgp().zzanj.set(this.zzagx);
        }

        if(this.zzkr()) {
            if(!TextUtils.isEmpty(this.zzgf().getGmpAppId()) || !TextUtils.isEmpty(this.zzgf().zzgw())) {
                this.zzgm();
                if(zzfk.zza(this.zzgf().getGmpAppId(), this.zzgp().zzjs(), this.zzgf().zzgw(), this.zzgp().zzjt())) {
                    this.zzgo().zzjj().zzbx("Rechecking which service to use due to a GMP App Id change");
                    this.zzgp().zzjv();
                    if(this.zzapo.zza(zzaf.zzalc)) {
                        this.zzgi().resetAnalyticsData();
                    }

                    this.zzaqa.disconnect();
                    this.zzaqa.zzdj();
                    this.zzgp().zzanj.set(this.zzagx);
                    this.zzgp().zzanl.zzcc(null);
                }

                this.zzgp().zzca(this.zzgf().getGmpAppId());
                this.zzgp().zzcb(this.zzgf().zzgw());
                if(!this.zzapo.zzbj(this.zzgf().zzal())) {
                    goto label_136;
                }

                this.zzaps.zzam(this.zzagx);
            }

        label_136:
            this.zzge().zzcm(this.zzgp().zzanl.zzjz());
            if((TextUtils.isEmpty(this.zzgf().getGmpAppId())) && (TextUtils.isEmpty(this.zzgf().zzgw()))) {
                return;
            }

            boolean v0 = this.isEnabled();
            if(!this.zzgp().zzjy() && !this.zzapo.zzhu()) {
                this.zzgp().zzi((((int)v0)) ^ 1);
            }

            if(this.zzapo.zze(this.zzgf().zzal(), zzaf.zzalj)) {
                this.zzj(false);
            }

            if(!this.zzapo.zzbd(this.zzgf().zzal()) || (v0)) {
                this.zzge().zzkz();
            }

            this.zzgg().zza(new AtomicReference());
        }
        else if(this.isEnabled()) {
            if(!this.zzgm().zzx("android.permission.INTERNET")) {
                this.zzgo().zzjd().zzbx("App is missing INTERNET permission");
            }

            if(!this.zzgm().zzx("android.permission.ACCESS_NETWORK_STATE")) {
                this.zzgo().zzjd().zzbx("App is missing ACCESS_NETWORK_STATE permission");
            }

            if(!Wrappers.packageManager(this.zzri).isCallerInstantApp() && !this.zzapo.zzib()) {
                if(!zzbj.zza(this.zzri)) {
                    this.zzgo().zzjd().zzbx("AppMeasurementReceiver not registered/enabled");
                }

                if(zzfk.zza(this.zzri, false)) {
                    goto label_71;
                }

                this.zzgo().zzjd().zzbx("AppMeasurementService not registered/enabled");
            }

        label_71:
            this.zzgo().zzjd().zzbx("Uploading is not possible. App measurement disabled");
            return;
        }
    }

    public static zzbt zza(Context arg11, zzak arg12) {
        if(arg12 != null && (arg12.origin == null || arg12.zzadx == null)) {
            arg12 = new zzak(arg12.zzadt, arg12.zzadu, arg12.zzadv, arg12.zzadw, null, null, arg12.zzady);
        }

        Preconditions.checkNotNull(arg11);
        Preconditions.checkNotNull(arg11.getApplicationContext());
        if(zzbt.zzapl == null) {
            Class v0 = zzbt.class;
            __monitor_enter(v0);
            try {
                if(zzbt.zzapl == null) {
                    zzbt.zzapl = new zzbt(new zzcr(arg11, arg12));
                }

                __monitor_exit(v0);
                goto label_48;
            label_34:
                __monitor_exit(v0);
            }
            catch(Throwable v11) {
                goto label_34;
            }

            throw v11;
        }
        else {
            if(arg12 == null) {
                goto label_48;
            }

            if(arg12.zzady == null) {
                goto label_48;
            }

            if(!arg12.zzady.containsKey("dataCollectionDefaultEnabled")) {
                goto label_48;
            }

            zzbt.zzapl.zzd(arg12.zzady.getBoolean("dataCollectionDefaultEnabled"));
        }

    label_48:
        return zzbt.zzapl;
    }

    static void zza(zzbt arg0, zzcr arg1) {
        arg0.zza(arg1);
    }

    private final void zza(zzcr arg6) {
        zzar v0_2;
        this.zzgn().zzaf();
        zzn.zzht();
        zzx v6 = new zzx(this);
        ((zzcp)v6).zzq();
        this.zzaqb = v6;
        zzaj v6_1 = new zzaj(this);
        ((zzf)v6_1).zzq();
        this.zzaqc = v6_1;
        zzal v0 = new zzal(this);
        ((zzf)v0).zzq();
        this.zzapz = v0;
        zzdr v0_1 = new zzdr(this);
        ((zzf)v0_1).zzq();
        this.zzaqa = v0_1;
        this.zzapu.zzgs();
        this.zzapp.zzgs();
        this.zzaqd = new zzbg(this);
        this.zzaqc.zzgs();
        this.zzgo().zzjj().zzg("App measurement is starting up, version", Long.valueOf(this.zzapo.zzhc()));
        this.zzgo().zzjj().zzbx("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        String v6_2 = v6_1.zzal();
        if(TextUtils.isEmpty(this.zzadx)) {
            if(this.zzgm().zzcw(v6_2)) {
                v0_2 = this.zzgo().zzjj();
                v6_2 = "Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.";
            }
            else {
                v0_2 = this.zzgo().zzjj();
                String v1 = "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ";
                v6_2 = String.valueOf(v6_2);
                v6_2 = v6_2.length() != 0 ? v1.concat(v6_2) : new String(v1);
            }

            v0_2.zzbx(v6_2);
        }

        this.zzgo().zzjk().zzbx("Debug-level message logging enabled");
        if(this.zzaqh != this.zzaqi) {
            this.zzgo().zzjd().zze("Not all components initialized", Integer.valueOf(this.zzaqh), Integer.valueOf(this.zzaqi));
        }

        this.zzvz = true;
    }

    private static void zza(zzco arg1) {
        if(arg1 != null) {
            return;
        }

        throw new IllegalStateException("Component not created");
    }

    private static void zza(zzcp arg3) {
        if(arg3 != null) {
            if(arg3.isInitialized()) {
                return;
            }

            String v3 = String.valueOf(arg3.getClass());
            StringBuilder v2 = new StringBuilder(String.valueOf(v3).length() + 27);
            v2.append("Component not initialized: ");
            v2.append(v3);
            throw new IllegalStateException(v2.toString());
        }

        throw new IllegalStateException("Component not created");
    }

    private static void zza(zzf arg3) {
        if(arg3 != null) {
            if(arg3.isInitialized()) {
                return;
            }

            String v3 = String.valueOf(arg3.getClass());
            StringBuilder v2 = new StringBuilder(String.valueOf(v3).length() + 27);
            v2.append("Component not initialized: ");
            v2.append(v3);
            throw new IllegalStateException(v2.toString());
        }

        throw new IllegalStateException("Component not created");
    }

    final void zzb(zzcp arg1) {
        ++this.zzaqh;
    }

    final void zzb(zzf arg1) {
        ++this.zzaqh;
    }

    public final Clock zzbx() {
        return this.zzrz;
    }

    private final void zzcl() {
        if(this.zzvz) {
            return;
        }

        throw new IllegalStateException("AppMeasurement is not initialized");
    }

    final void zzd(boolean arg1) {
        this.zzaqg = Boolean.valueOf(arg1);
    }

    final void zzga() {
        throw new IllegalStateException("Unexpected call on client side");
    }

    final void zzgb() {
    }

    public final zza zzgd() {
        if(this.zzapy != null) {
            return this.zzapy;
        }

        throw new IllegalStateException("Component not created");
    }

    public final zzcs zzge() {
        zzbt.zza(this.zzapx);
        return this.zzapx;
    }

    public final zzaj zzgf() {
        zzbt.zza(this.zzaqc);
        return this.zzaqc;
    }

    public final zzdr zzgg() {
        zzbt.zza(this.zzaqa);
        return this.zzaqa;
    }

    public final zzdo zzgh() {
        zzbt.zza(this.zzapw);
        return this.zzapw;
    }

    public final zzal zzgi() {
        zzbt.zza(this.zzapz);
        return this.zzapz;
    }

    public final zzeq zzgj() {
        zzbt.zza(this.zzaps);
        return this.zzaps;
    }

    public final zzx zzgk() {
        zzbt.zza(this.zzaqb);
        return this.zzaqb;
    }

    public final zzan zzgl() {
        zzbt.zza(this.zzapv);
        return this.zzapv;
    }

    public final zzfk zzgm() {
        zzbt.zza(this.zzapu);
        return this.zzapu;
    }

    public final zzbo zzgn() {
        zzbt.zza(this.zzapr);
        return this.zzapr;
    }

    public final zzap zzgo() {
        zzbt.zza(this.zzapq);
        return this.zzapq;
    }

    public final zzba zzgp() {
        zzbt.zza(this.zzapp);
        return this.zzapp;
    }

    public final zzn zzgq() {
        return this.zzapo;
    }

    public final zzk zzgr() {
        return this.zzaiq;
    }

    final void zzj(boolean arg13) {
        int v13;
        this.zzgn().zzaf();
        String v4 = this.zzgp().zzans.zzjz();
        if((arg13) || v4 == null) {
        label_26:
            v13 = 1;
        }
        else if("unset".equals(v4)) {
            this.zzge().zza("app", "_ap", null, this.zzrz.currentTimeMillis());
            goto label_26;
        }
        else {
            this.zzge().zza("app", "_ap", v4, this.zzrz.currentTimeMillis());
            v13 = 0;
        }

        if(v13 != 0) {
            Boolean v13_1 = this.zzapo.zzau("google_analytics_default_allow_ad_personalization_signals");
            if(v13_1 != null) {
                zzcs v0 = this.zzge();
                String v1 = "auto";
                String v2 = "_ap";
                long v3 = v13_1.booleanValue() ? 1 : 0;
                v0.zza(v1, v2, Long.valueOf(v3), this.zzrz.currentTimeMillis());
                return;
            }
            else {
                this.zzge().zza("auto", "_ap", null, this.zzrz.currentTimeMillis());
            }
        }
    }

    public final zzap zzkf() {
        if(this.zzapq != null && (this.zzapq.isInitialized())) {
            return this.zzapq;
        }

        return null;
    }

    public final zzbg zzkg() {
        return this.zzaqd;
    }

    final zzbo zzkh() {
        return this.zzapr;
    }

    public final AppMeasurement zzki() {
        return this.zzapt;
    }

    public final boolean zzkj() {
        return TextUtils.isEmpty(this.zzadx);
    }

    public final String zzkk() {
        return this.zzadx;
    }

    public final String zzkl() {
        return this.zzapm;
    }

    public final String zzkm() {
        return this.zzapn;
    }

    public final boolean zzkn() {
        return this.zzadv;
    }

    public final boolean zzko() {
        if(this.zzaqg != null && (this.zzaqg.booleanValue())) {
            return 1;
        }

        return 0;
    }

    final long zzkp() {
        Long v0 = Long.valueOf(this.zzgp().zzanj.get());
        if(v0.longValue() == 0) {
            return this.zzagx;
        }

        return Math.min(this.zzagx, v0.longValue());
    }

    final void zzkq() {
        ++this.zzaqi;
    }

    protected final boolean zzkr() {
        boolean v0;
        this.zzcl();
        this.zzgn().zzaf();
        if(this.zzaqe == null || this.zzaqf == 0 || this.zzaqe != null && !this.zzaqe.booleanValue() && Math.abs(this.zzrz.elapsedRealtime() - this.zzaqf) > 1000) {
            this.zzaqf = this.zzrz.elapsedRealtime();
            boolean v1 = true;
            if(!this.zzgm().zzx("android.permission.INTERNET") || !this.zzgm().zzx("android.permission.ACCESS_NETWORK_STATE")) {
            label_49:
                v0 = false;
            }
            else {
                if(!Wrappers.packageManager(this.zzri).isCallerInstantApp() && !this.zzapo.zzib()) {
                    if(!zzbj.zza(this.zzri)) {
                    }
                    else if(zzfk.zza(this.zzri, false)) {
                        goto label_47;
                    }

                    goto label_49;
                }

            label_47:
                v0 = true;
            }

            this.zzaqe = Boolean.valueOf(v0);
            if(!this.zzaqe.booleanValue()) {
                goto label_70;
            }

            if(!this.zzgm().zzt(this.zzgf().getGmpAppId(), this.zzgf().zzgw())) {
                if(!TextUtils.isEmpty(this.zzgf().zzgw())) {
                }
                else {
                    v1 = false;
                }
            }

            this.zzaqe = Boolean.valueOf(v1);
        }

    label_70:
        return this.zzaqe.booleanValue();
    }
}

