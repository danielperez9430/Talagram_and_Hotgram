package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import android.os.Build$VERSION;
import android.text.TextUtils;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.InstantApps;

public final class zzaj extends zzf {
    private String zzafx;
    private String zzage;
    private long zzagh;
    private String zzagk;
    private int zzagy;
    private int zzalo;
    private long zzalp;
    private String zztr;
    private String zzts;
    private String zztt;

    zzaj(zzbt arg1) {
        super(arg1);
    }

    public final Context getContext() {
        return super.getContext();
    }

    final String getGmpAppId() {
        ((zzf)this).zzcl();
        return this.zzafx;
    }

    public final void zzaf() {
        super.zzaf();
    }

    final String zzal() {
        ((zzf)this).zzcl();
        return this.zztt;
    }

    final zzh zzbr(String arg30) {
        zzaj v0 = this;
        ((zzco)this).zzaf();
        ((zzco)this).zzgb();
        zzh v25 = null;
        String v2 = this.zzal();
        String v3 = this.getGmpAppId();
        ((zzf)this).zzcl();
        String v4 = v0.zzts;
        long v5 = ((long)this.zzja());
        ((zzf)this).zzcl();
        String v7 = v0.zzage;
        long v8 = ((zzco)this).zzgq().zzhc();
        ((zzf)this).zzcl();
        ((zzco)this).zzaf();
        if(v0.zzalp == 0) {
            v0.zzalp = v0.zzadj.zzgm().zzd(((zzco)this).getContext(), ((zzco)this).getContext().getPackageName());
        }

        long v10 = v0.zzalp;
        boolean v13 = v0.zzadj.isEnabled();
        int v14 = ((zzco)this).zzgp().zzanv ^ 1;
        ((zzco)this).zzaf();
        ((zzco)this).zzgb();
        String v1 = !((zzco)this).zzgq().zzbc(v0.zztt) || (v0.zzadj.isEnabled()) ? this.zziz() : null;
        ((zzf)this).zzcl();
        int v26 = v14;
        String v27 = v1;
        long v14_1 = v0.zzagh;
        long v18 = v0.zzadj.zzkp();
        int v20 = this.zzjb();
        zzn v1_1 = ((zzco)this).zzgq();
        ((zzco)v1_1).zzgb();
        Boolean v1_2 = v1_1.zzau("google_analytics_adid_collection_enabled");
        boolean v1_3 = v1_2 == null || (v1_2.booleanValue()) ? true : false;
        boolean v21 = Boolean.valueOf(v1_3).booleanValue();
        v1_1 = ((zzco)this).zzgq();
        ((zzco)v1_1).zzgb();
        v1_2 = v1_1.zzau("google_analytics_ssaid_collection_enabled");
        boolean v28 = v1_2 == null || (v1_2.booleanValue()) ? true : false;
        super(v2, v3, v4, v5, v7, v8, v10, arg30, v13, v26, v27, v14_1, v18, v20, v21, Boolean.valueOf(v28).booleanValue(), ((zzco)this).zzgp().zzjx(), this.zzgw());
        return v25;
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
        return 1;
    }

    protected final void zzgu() {
        zzar v2_3;
        String v4_2;
        PackageInfo v7;
        String v0 = "unknown";
        String v1 = "Unknown";
        String v2 = "Unknown";
        String v3 = ((zzco)this).getContext().getPackageName();
        PackageManager v4 = ((zzco)this).getContext().getPackageManager();
        int v6 = -2147483648;
        if(v4 == null) {
            ((zzco)this).zzgo().zzjd().zzg("PackageManager is null, app identity information might be inaccurate. appId", zzap.zzbv(v3));
            goto label_52;
        }

        try {
            v0 = v4.getInstallerPackageName(v3);
        }
        catch(IllegalArgumentException ) {
            ((zzco)this).zzgo().zzjd().zzg("Error retrieving app installer package name. appId", zzap.zzbv(v3));
        }

        if(v0 == null) {
            v0 = "manual_install";
        }
        else if("com.android.vending".equals(v0)) {
            v0 = "";
        }

        try {
            v7 = v4.getPackageInfo(((zzco)this).getContext().getPackageName(), 0);
            if(v7 == null) {
                goto label_52;
            }

            CharSequence v4_1 = v4.getApplicationLabel(v7.applicationInfo);
            if(!TextUtils.isEmpty(v4_1)) {
                v2 = v4_1.toString();
            }

            v4_2 = v7.versionName;
        }
        catch(PackageManager$NameNotFoundException ) {
            goto label_47;
        }

        try {
            v6 = v7.versionCode;
            v1 = v4_2;
            goto label_52;
        }
        catch(PackageManager$NameNotFoundException ) {
            v1 = v4_2;
        }

    label_47:
        ((zzco)this).zzgo().zzjd().zze("Error retrieving package info. appId, appName", zzap.zzbv(v3), v2);
    label_52:
        this.zztt = v3;
        this.zzage = v0;
        this.zzts = v1;
        this.zzalo = v6;
        this.zztr = v2;
        long v0_1 = 0;
        this.zzalp = v0_1;
        ((zzco)this).zzgr();
        Status v2_1 = GoogleServices.initialize(((zzco)this).getContext());
        int v4_3 = 1;
        v6 = v2_1 == null || !v2_1.isSuccess() ? 0 : 1;
        int v7_1 = (TextUtils.isEmpty(this.zzadj.zzkk())) || !"am".equals(this.zzadj.zzkl()) ? 0 : 1;
        v6 |= v7_1;
        if(v6 == 0) {
            if(v2_1 == null) {
                ((zzco)this).zzgo().zzjd().zzbx("GoogleService failed to initialize (no status)");
            }
            else {
                ((zzco)this).zzgo().zzjd().zze("GoogleService failed to initialize, status", Integer.valueOf(v2_1.getStatusCode()), v2_1.getStatusMessage());
            }
        }

        if(v6 != 0) {
            Boolean v2_2 = ((zzco)this).zzgq().zzhv();
            if(!((zzco)this).zzgq().zzhu()) {
                if(v2_2 != null && !v2_2.booleanValue()) {
                    if(this.zzadj.zzkj()) {
                        v2_3 = ((zzco)this).zzgo().zzjj();
                        v4_2 = "Collection disabled with firebase_analytics_collection_enabled=0";
                        goto label_108;
                    }
                    else {
                        goto label_132;
                    }
                }

                if(v2_2 == null && (GoogleServices.isMeasurementExplicitlyDisabled())) {
                    v2_3 = ((zzco)this).zzgo().zzjj();
                    v4_2 = "Collection disabled with google_app_measurement_enable=0";
                    goto label_108;
                }

                goto label_127;
            }
            else if(this.zzadj.zzkj()) {
                v2_3 = ((zzco)this).zzgo().zzjj();
                v4_2 = "Collection disabled with firebase_analytics_collection_deactivated=1";
            }
            else {
                goto label_132;
            }

        label_108:
            v2_3.zzbx(v4_2);
            goto label_132;
        label_127:
            ((zzco)this).zzgo().zzjl().zzbx("Collection enabled");
        }
        else {
        label_132:
            v4_3 = 0;
        }

        this.zzafx = "";
        this.zzagk = "";
        this.zzagh = v0_1;
        ((zzco)this).zzgr();
        if(!TextUtils.isEmpty(this.zzadj.zzkk()) && ("am".equals(this.zzadj.zzkl()))) {
            this.zzagk = this.zzadj.zzkk();
        }

        try {
            v0 = GoogleServices.getGoogleAppId();
            v1 = TextUtils.isEmpty(((CharSequence)v0)) ? "" : v0;
            this.zzafx = v1;
            if(!TextUtils.isEmpty(((CharSequence)v0))) {
                this.zzagk = new StringResourceValueReader(((zzco)this).getContext()).getString("gma_app_id");
            }

            if(v4_3 == 0) {
                goto label_180;
            }

            ((zzco)this).zzgo().zzjl().zze("App package, google app id", this.zztt, this.zzafx);
        }
        catch(IllegalStateException v0_2) {
            ((zzco)this).zzgo().zzjd().zze("getGoogleAppId or isMeasurementEnabled failed with exception. appId", zzap.zzbv(v3), v0_2);
        }

    label_180:
        if(Build$VERSION.SDK_INT >= 16) {
            this.zzagy = InstantApps.isInstantApp(((zzco)this).getContext());
            return;
        }

        this.zzagy = 0;
    }

    final String zzgw() {
        ((zzf)this).zzcl();
        return this.zzagk;
    }

    @VisibleForTesting private final String zziz() {
        String v2_1;
        zzar v1_1;
        Object v2;
        Class v1;
        String v0 = null;
        try {
            v1 = ((zzco)this).getContext().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
            if(v1 != null) {
                goto label_7;
            }
        }
        catch(ClassNotFoundException ) {
            return v0;
        }

        return v0;
        try {
        label_7:
            v2 = v1.getDeclaredMethod("getInstance", Context.class).invoke(v0, ((zzco)this).getContext());
            if(v2 != null) {
                goto label_20;
            }
        }
        catch(Exception ) {
            v1_1 = ((zzco)this).zzgo().zzjh();
            v2_1 = "Failed to obtain Firebase Analytics instance";
            goto label_29;
        }

        return v0;
        try {
        label_20:
            return v1.getDeclaredMethod("getFirebaseInstanceId").invoke(v2);
        }
        catch(Exception ) {
            v1_1 = ((zzco)this).zzgo().zzji();
            v2_1 = "Failed to retrieve Firebase Instance Id";
        }

    label_29:
        v1_1.zzbx(v2_1);
        return v0;
    }

    final int zzja() {
        ((zzf)this).zzcl();
        return this.zzalo;
    }

    final int zzjb() {
        ((zzf)this).zzcl();
        return this.zzagy;
    }
}

