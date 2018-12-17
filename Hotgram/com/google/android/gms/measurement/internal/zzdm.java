package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application$ActivityLifecycleCallbacks;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

@TargetApi(value=14) final class zzdm implements Application$ActivityLifecycleCallbacks {
    private zzdm(zzcs arg1) {
        this.zzarc = arg1;
        super();
    }

    zzdm(zzcs arg1, zzct arg2) {
        this(arg1);
    }

    public final void onActivityCreated(Activity arg6, Bundle arg7) {
        int v1_1;
        String v0_2;
        try {
            this.zzarc.zzgo().zzjl().zzbx("onActivityCreated");
            Intent v0_1 = arg6.getIntent();
            if(v0_1 == null) {
                goto label_78;
            }

            Uri v1 = v0_1.getData();
            if(v1 == null) {
                goto label_78;
            }

            if(!v1.isHierarchical()) {
                goto label_78;
            }

            if(arg7 == null) {
                Bundle v2 = this.zzarc.zzgm().zza(v1);
                this.zzarc.zzgm();
                v0_2 = zzfk.zzd(v0_1) ? "gs" : "auto";
                if(v2 == null) {
                    goto label_26;
                }

                this.zzarc.logEvent(v0_2, "_cmp", v2);
            }

        label_26:
            v0_2 = v1.getQueryParameter("referrer");
            if(TextUtils.isEmpty(((CharSequence)v0_2))) {
                return;
            }

            if(v0_2.contains("gclid")) {
                if(!v0_2.contains("utm_campaign") && !v0_2.contains("utm_source") && !v0_2.contains("utm_medium") && !v0_2.contains("utm_term") && !v0_2.contains("utm_content")) {
                    goto label_52;
                }

                v1_1 = 1;
            }
            else {
            label_52:
                v1_1 = 0;
            }

            if(v1_1 == 0) {
                this.zzarc.zzgo().zzjk().zzbx("Activity created with data \'referrer\' param without gclid and at least one utm field");
                return;
            }

            this.zzarc.zzgo().zzjk().zzg("Activity created with referrer", v0_2);
            if(TextUtils.isEmpty(((CharSequence)v0_2))) {
                goto label_78;
            }

            this.zzarc.zzb("auto", "_ldl", v0_2, true);
        }
        catch(Exception v0) {
            this.zzarc.zzgo().zzjd().zzg("Throwable caught in onActivityCreated", v0);
        }

    label_78:
        this.zzarc.zzgh().onActivityCreated(arg6, arg7);
    }

    public final void onActivityDestroyed(Activity arg2) {
        this.zzarc.zzgh().onActivityDestroyed(arg2);
    }

    public final void onActivityPaused(Activity arg5) {
        this.zzarc.zzgh().onActivityPaused(arg5);
        zzeq v5 = this.zzarc.zzgj();
        ((zzco)v5).zzgn().zzc(new zzeu(v5, ((zzco)v5).zzbx().elapsedRealtime()));
    }

    public final void onActivityResumed(Activity arg5) {
        this.zzarc.zzgh().onActivityResumed(arg5);
        zzeq v5 = this.zzarc.zzgj();
        ((zzco)v5).zzgn().zzc(new zzet(v5, ((zzco)v5).zzbx().elapsedRealtime()));
    }

    public final void onActivitySaveInstanceState(Activity arg2, Bundle arg3) {
        this.zzarc.zzgh().onActivitySaveInstanceState(arg2, arg3);
    }

    public final void onActivityStarted(Activity arg1) {
    }

    public final void onActivityStopped(Activity arg1) {
    }
}

