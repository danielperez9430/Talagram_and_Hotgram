package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.support.v4.f.a;
import android.text.TextUtils;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty;
import com.google.android.gms.measurement.AppMeasurement$Event;
import com.google.android.gms.measurement.AppMeasurement$EventInterceptor;
import com.google.android.gms.measurement.AppMeasurement$OnEventListener;
import com.google.android.gms.measurement.AppMeasurement$UserProperty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;

public final class zzcs extends zzf {
    @VisibleForTesting protected zzdm zzaqv;
    private EventInterceptor zzaqw;
    private final Set zzaqx;
    private boolean zzaqy;
    private final AtomicReference zzaqz;
    @VisibleForTesting protected boolean zzara;

    protected zzcs(zzbt arg1) {
        super(arg1);
        this.zzaqx = new CopyOnWriteArraySet();
        this.zzara = true;
        this.zzaqz = new AtomicReference();
    }

    public final void clearConditionalUserProperty(String arg2, String arg3, Bundle arg4) {
        ((zzco)this).zzgb();
        this.zza(null, arg2, arg3, arg4);
    }

    public final void clearConditionalUserPropertyAs(String arg1, String arg2, String arg3, Bundle arg4) {
        Preconditions.checkNotEmpty(arg1);
        ((zzco)this).zzga();
        this.zza(arg1, arg2, arg3, arg4);
    }

    public final List getConditionalUserProperties(String arg2, String arg3) {
        ((zzco)this).zzgb();
        return this.zzf(null, arg2, arg3);
    }

    public final List getConditionalUserPropertiesAs(String arg1, String arg2, String arg3) {
        Preconditions.checkNotEmpty(arg1);
        ((zzco)this).zzga();
        return this.zzf(arg1, arg2, arg3);
    }

    public final Context getContext() {
        return super.getContext();
    }

    public final String getCurrentScreenClass() {
        zzdn v0 = this.zzadj.zzgh().zzlb();
        if(v0 != null) {
            return v0.zzarl;
        }

        return null;
    }

    public final String getCurrentScreenName() {
        zzdn v0 = this.zzadj.zzgh().zzlb();
        if(v0 != null) {
            return v0.zzuw;
        }

        return null;
    }

    public final String getGmpAppId() {
        if(this.zzadj.zzkk() != null) {
            return this.zzadj.zzkk();
        }

        try {
            return GoogleServices.getGoogleAppId();
        }
        catch(IllegalStateException v0) {
            this.zzadj.zzgo().zzjd().zzg("getGoogleAppId failed with exception", v0);
            return null;
        }
    }

    public final Map getUserProperties(String arg2, String arg3, boolean arg4) {
        ((zzco)this).zzgb();
        return this.zzb(null, arg2, arg3, arg4);
    }

    public final Map getUserPropertiesAs(String arg1, String arg2, String arg3, boolean arg4) {
        Preconditions.checkNotEmpty(arg1);
        ((zzco)this).zzga();
        return this.zzb(arg1, arg2, arg3, arg4);
    }

    public final void logEvent(String arg10, String arg11, Bundle arg12) {
        this.logEvent(arg10, arg11, arg12, true, true, ((zzco)this).zzbx().currentTimeMillis());
    }

    public final void logEvent(String arg12, String arg13, Bundle arg14, boolean arg15, boolean arg16, long arg17) {
        ((zzco)this).zzgb();
        String v1 = arg12 == null ? "app" : arg12;
        Bundle v5 = arg14 == null ? new Bundle() : arg14;
        boolean v7 = !arg16 || this.zzaqw == null || (zzfk.zzcv(arg13)) ? true : false;
        this.zzb(v1, arg13, arg17, v5, arg16, v7, (((int)arg15)) ^ 1, null);
    }

    public final void registerOnMeasurementEventListener(OnEventListener arg2) {
        ((zzco)this).zzgb();
        ((zzf)this).zzcl();
        Preconditions.checkNotNull(arg2);
        if(!this.zzaqx.add(arg2)) {
            ((zzco)this).zzgo().zzjg().zzbx("OnEventListener already registered");
        }
    }

    public final void resetAnalyticsData(long arg3) {
        if(((zzco)this).zzgq().zza(zzaf.zzalk)) {
            this.zzcm(null);
        }

        ((zzco)this).zzgn().zzc(new zzcz(this, arg3));
    }

    public final void setConditionalUserProperty(ConditionalUserProperty arg3) {
        Preconditions.checkNotNull(arg3);
        ((zzco)this).zzgb();
        ConditionalUserProperty v0 = new ConditionalUserProperty(arg3);
        if(!TextUtils.isEmpty(v0.mAppId)) {
            ((zzco)this).zzgo().zzjg().zzbx("Package name should be null when calling setConditionalUserProperty");
        }

        v0.mAppId = null;
        this.zza(v0);
    }

    public final void setConditionalUserPropertyAs(ConditionalUserProperty arg2) {
        Preconditions.checkNotNull(arg2);
        Preconditions.checkNotEmpty(arg2.mAppId);
        ((zzco)this).zzga();
        this.zza(new ConditionalUserProperty(arg2));
    }

    public final void setEventInterceptor(EventInterceptor arg3) {
        ((zzco)this).zzaf();
        ((zzco)this).zzgb();
        ((zzf)this).zzcl();
        if(arg3 != null && arg3 != this.zzaqw) {
            boolean v0 = this.zzaqw == null ? true : false;
            Preconditions.checkState(v0, "EventInterceptor already set.");
        }

        this.zzaqw = arg3;
    }

    public final void setMeasurementEnabled(boolean arg3) {
        ((zzf)this).zzcl();
        ((zzco)this).zzgb();
        ((zzco)this).zzgn().zzc(new zzdi(this, arg3));
    }

    public final void setMinimumSessionDuration(long arg3) {
        ((zzco)this).zzgb();
        ((zzco)this).zzgn().zzc(new zzdk(this, arg3));
    }

    public final void setSessionTimeoutDuration(long arg3) {
        ((zzco)this).zzgb();
        ((zzco)this).zzgn().zzc(new zzdl(this, arg3));
    }

    public final void unregisterOnMeasurementEventListener(OnEventListener arg2) {
        ((zzco)this).zzgb();
        ((zzf)this).zzcl();
        Preconditions.checkNotNull(arg2);
        if(!this.zzaqx.remove(arg2)) {
            ((zzco)this).zzgo().zzjg().zzbx("OnEventListener had not been registered");
        }
    }

    public final void zza(String arg9, String arg10, Bundle arg11, boolean arg12) {
        this.logEvent(arg9, arg10, arg11, false, true, ((zzco)this).zzbx().currentTimeMillis());
    }

    static void zza(zzcs arg0, String arg1, String arg2, long arg3, Bundle arg5, boolean arg6, boolean arg7, boolean arg8, String arg9) {
        arg0.zza(arg1, arg2, arg3, arg5, arg6, arg7, arg8, arg9);
    }

    final void zza(String arg8, String arg9, Object arg10, long arg11) {
        Long v10;
        Preconditions.checkNotEmpty(arg8);
        Preconditions.checkNotEmpty(arg9);
        ((zzco)this).zzaf();
        ((zzco)this).zzgb();
        ((zzf)this).zzcl();
        if(!((zzco)this).zzgq().zze(((zze)this).zzgf().zzal(), zzaf.zzalj)) {
            if(!"_ap".equals(arg9)) {
                goto label_60;
            }

            return;
        }
        else if(("_ap".equals(arg9)) && !"auto".equals(arg8)) {
            if((arg10 instanceof String)) {
                Object v0 = arg10;
                if(!TextUtils.isEmpty(((CharSequence)v0))) {
                    long v1 = 1;
                    long v3 = ("true".equals(((String)v0).toLowerCase(Locale.ENGLISH))) || ("1".equals(arg10)) ? v1 : 0;
                    v10 = Long.valueOf(v3);
                    zzbf v0_1 = ((zzco)this).zzgp().zzans;
                    String v1_1 = v10.longValue() == v1 ? "true" : "false";
                    v0_1.zzcc(v1_1);
                    goto label_60;
                }
            }

            if(arg10 != null) {
                goto label_60;
            }

            ((zzco)this).zzgp().zzans.zzcc("unset");
            ((zzco)this).zzgn().zzc(new zzcw(this));
        }

    label_60:
        Object v5 = v10;
        if(!this.zzadj.isEnabled()) {
            ((zzco)this).zzgo().zzjk().zzbx("User property not set since app measurement is disabled");
            return;
        }

        if(!this.zzadj.zzkr()) {
            return;
        }

        ((zzco)this).zzgo().zzjk().zze("Setting user property (FE)", ((zzco)this).zzgl().zzbs(arg9), v5);
        ((zze)this).zzgg().zzb(new zzfh(arg9, arg11, v5, arg8));
    }

    static void zza(zzcs arg0, ConditionalUserProperty arg1) {
        arg0.zzb(arg1);
    }

    static void zza(zzcs arg0, boolean arg1) {
        arg0.zzk(arg1);
    }

    static void zza(zzcs arg0) {
        arg0.zzky();
    }

    final void zza(String arg8, String arg9, Bundle arg10) {
        ((zzco)this).zzgb();
        ((zzco)this).zzaf();
        this.zza(arg8, arg9, ((zzco)this).zzbx().currentTimeMillis(), arg10);
    }

    private final void zza(ConditionalUserProperty arg9) {
        long v0 = ((zzco)this).zzbx().currentTimeMillis();
        Preconditions.checkNotNull(arg9);
        Preconditions.checkNotEmpty(arg9.mName);
        Preconditions.checkNotEmpty(arg9.mOrigin);
        Preconditions.checkNotNull(arg9.mValue);
        arg9.mCreationTimestamp = v0;
        String v0_1 = arg9.mName;
        Object v1 = arg9.mValue;
        if(((zzco)this).zzgm().zzcs(v0_1) != 0) {
            ((zzco)this).zzgo().zzjd().zzg("Invalid conditional user property name", ((zzco)this).zzgl().zzbu(v0_1));
            return;
        }

        if(((zzco)this).zzgm().zzi(v0_1, v1) != 0) {
            ((zzco)this).zzgo().zzjd().zze("Invalid conditional user property value", ((zzco)this).zzgl().zzbu(v0_1), v1);
            return;
        }

        Object v2 = ((zzco)this).zzgm().zzj(v0_1, v1);
        if(v2 == null) {
            ((zzco)this).zzgo().zzjd().zze("Unable to normalize conditional user property value", ((zzco)this).zzgl().zzbu(v0_1), v1);
            return;
        }

        arg9.mValue = v2;
        long v1_1 = arg9.mTriggerTimeout;
        long v4 = 1;
        long v6 = 15552000000L;
        if(!TextUtils.isEmpty(arg9.mTriggerEventName) && (v1_1 > v6 || v1_1 < v4)) {
            ((zzco)this).zzgo().zzjd().zze("Invalid conditional user property timeout", ((zzco)this).zzgl().zzbu(v0_1), Long.valueOf(v1_1));
            return;
        }

        v1_1 = arg9.mTimeToLive;
        if(v1_1 <= v6) {
            if(v1_1 < v4) {
            }
            else {
                ((zzco)this).zzgn().zzc(new zzda(this, arg9));
                return;
            }
        }

        ((zzco)this).zzgo().zzjd().zze("Invalid conditional user property time to live", ((zzco)this).zzgl().zzbu(v0_1), Long.valueOf(v1_1));
    }

    private final void zza(String arg29, String arg30, long arg31, Bundle arg33, boolean arg34, boolean arg35, boolean arg36, String arg37) {
        String v13_1;
        ArrayList v0_6;
        long v3_1;
        int v20;
        int v18_2;
        Object[] v27;
        zzdn v25;
        List v26;
        int v7;
        int v2;
        String v0_3;
        int v10_1;
        zzcs v1 = this;
        String v8 = arg29;
        String v6 = arg30;
        Bundle v5 = arg33;
        Preconditions.checkNotEmpty(arg29);
        Preconditions.checkNotEmpty(arg30);
        Preconditions.checkNotNull(arg33);
        ((zzco)this).zzaf();
        ((zzf)this).zzcl();
        if(!v1.zzadj.isEnabled()) {
            ((zzco)this).zzgo().zzjk().zzbx("Event not sent since app measurement is disabled");
            return;
        }

        Object v4 = null;
        int v16 = 0;
        if(!v1.zzaqy) {
            v1.zzaqy = true;
            try {
                Class v0 = Class.forName("com.google.android.gms.tagmanager.TagManagerService");
                try {
                    v0.getDeclaredMethod("initialize", Context.class).invoke(v4, ((zzco)this).getContext());
                }
                catch(Exception v0_1) {
                    try {
                        ((zzco)this).zzgo().zzjg().zzg("Failed to invoke Tag Manager\'s initialize() method", v0_1);
                    }
                    catch(ClassNotFoundException ) {
                    label_41:
                        ((zzco)this).zzgo().zzjj().zzbx("Tag Manager is not found and thus will not be used");
                    }
                }
            }
            catch(ClassNotFoundException ) {
                goto label_41;
            }
        }

        int v0_2 = 40;
        int v9 = 2;
        if(arg36) {
            ((zzco)this).zzgr();
            if(!"_iap".equals(v6)) {
                zzfk v10 = v1.zzadj.zzgm();
                if(!v10.zzr("event", v6) || !v10.zza("event", v0_2, v6)) {
                    v10_1 = 2;
                    goto label_70;
                label_69:
                    v10_1 = 0;
                }
                else if(!v10.zza("event", Event.zzadk, v6)) {
                    v10_1 = 13;
                }
                else {
                    goto label_69;
                }

            label_70:
                if(v10_1 == 0) {
                    goto label_90;
                }

                ((zzco)this).zzgo().zzjf().zzg("Invalid public event name. Event will not be logged (FE)", ((zzco)this).zzgl().zzbs(v6));
                v1.zzadj.zzgm();
                v0_3 = zzfk.zza(v6, v0_2, true);
                v2 = v6 != null ? arg30.length() : 0;
                v1.zzadj.zzgm().zza(v10_1, "_ev", v0_3, v2);
                return;
            }
        }

    label_90:
        ((zzco)this).zzgr();
        zzdn v15 = ((zze)this).zzgh().zzla();
        if(v15 != null && !v5.containsKey("_sc")) {
            v15.zzarn = true;
        }

        boolean v10_2 = !arg34 || !arg36 ? false : true;
        zzdo.zza(v15, v5, v10_2);
        boolean v17 = "am".equals(v8);
        v10_2 = zzfk.zzcv(arg30);
        if((arg34) && v1.zzaqw != null && !v10_2 && !v17) {
            ((zzco)this).zzgo().zzjk().zze("Passing event to registered event handler (FE)", ((zzco)this).zzgl().zzbs(v6), ((zzco)this).zzgl().zzd(v5));
            v1.zzaqw.interceptEvent(arg29, arg30, arg33, arg31);
            return;
        }

        if(!v1.zzadj.zzkr()) {
            return;
        }

        v2 = ((zzco)this).zzgm().zzcr(v6);
        if(v2 != 0) {
            ((zzco)this).zzgo().zzjf().zzg("Invalid event name. Event will not be logged (FE)", ((zzco)this).zzgl().zzbs(v6));
            ((zzco)this).zzgm();
            v0_3 = zzfk.zza(v6, v0_2, true);
            if(v6 != null) {
                v16 = arg30.length();
            }

            v1.zzadj.zzgm().zza(arg37, v2, "_ev", v0_3, v16);
            return;
        }

        String[] v0_4 = new String[4];
        v0_4[0] = "_o";
        v0_4[1] = "_sn";
        v0_4[v9] = "_sc";
        v0_4[3] = "_si";
        List v0_5 = CollectionUtils.listOf(((Object[])v0_4));
        zzdn v18 = v15;
        Bundle v2_1 = ((zzco)this).zzgm().zza(arg37, arg30, arg33, v0_5, arg36, true);
        v15 = v2_1 == null || !v2_1.containsKey("_sc") || !v2_1.containsKey("_si") ? ((zzdn)v4) : new zzdn(v2_1.getString("_sn"), v2_1.getString("_sc"), Long.valueOf(v2_1.getLong("_si")).longValue());
        zzdn v4_1 = v15 == null ? v18 : v15;
        ArrayList v15_1 = new ArrayList();
        ((List)v15_1).add(v2_1);
        long v13 = ((zzco)this).zzgm().zzmd().nextLong();
        Object[] v5_1 = v2_1.keySet().toArray(new String[arg33.size()]);
        Arrays.sort(v5_1);
        int v12 = v5_1.length;
        v10_1 = 0;
        int v11 = 0;
        while(v11 < v12) {
            Object v9_1 = v5_1[v11];
            Object v18_1 = v2_1.get(((String)v9_1));
            ((zzco)this).zzgm();
            ArrayList v19 = v15_1;
            Bundle[] v15_2 = zzfk.zze(v18_1);
            if(v15_2 != null) {
                v2_1.putInt(((String)v9_1), v15_2.length);
                v7 = 0;
                while(v7 < v15_2.length) {
                    Bundle v3 = v15_2[v7];
                    zzdo.zza(v4_1, v3, true);
                    Object v23 = v9_1;
                    Bundle v9_2 = ((zzco)this).zzgm().zza(arg37, "_ep", v3, v0_5, arg36, false);
                    v9_2.putString("_en", v6);
                    v9_2.putLong("_eid", v13);
                    v9_2.putString("_gn", v23);
                    v9_2.putInt("_ll", v15_2.length);
                    v9_2.putInt("_i", v7);
                    v19.add(v9_2);
                    ++v7;
                    v19 = v19;
                    v13 = v13;
                    v15_2 = v15_2;
                    v9_1 = v23;
                    v11 = v11;
                    v12 = v12;
                    v10_1 = v10_1;
                    v4_1 = v4_1;
                    v0_5 = v0_5;
                    v5_1 = v5_1;
                }

                v26 = v0_5;
                v25 = v4_1;
                v27 = v5_1;
                v18_2 = v11;
                v20 = v12;
                v3_1 = v13;
                v0_6 = v19;
                v10_1 += v15_2.length;
            }
            else {
                v26 = v0_5;
                v25 = v4_1;
                v27 = v5_1;
                v18_2 = v11;
                v20 = v12;
                v3_1 = v13;
                v0_6 = v19;
            }

            v11 = v18_2 + 1;
            v15_1 = v0_6;
            v13 = v3_1;
            v12 = v20;
            v4_1 = v25;
            v0_5 = v26;
            v5_1 = v27;
        }

        v7 = v10_1;
        v3_1 = v13;
        v0_6 = v15_1;
        if(v7 != 0) {
            v2_1.putLong("_eid", v3_1);
            v2_1.putInt("_epc", v7);
        }

        v9 = 0;
        while(v9 < ((List)v0_6).size()) {
            Object v2_2 = ((List)v0_6).get(v9);
            int v3_2 = v9 != 0 ? 1 : 0;
            String v3_3 = v3_2 != 0 ? "_ep" : v6;
            ((Bundle)v2_2).putString("_o", v8);
            if(arg35) {
                v2_1 = ((zzco)this).zzgm().zze(((Bundle)v2_2));
            }

            Object v11_1 = v2_2;
            ((zzco)this).zzgo().zzjk().zze("Logging event (FE)", ((zzco)this).zzgl().zzbs(v6), ((zzco)this).zzgl().zzd(((Bundle)v11_1)));
            v13_1 = v6;
            ((zze)this).zzgg().zzb(new zzad(v3_3, new zzaa(((Bundle)v11_1)), arg29, arg31), arg37);
            if(!v17) {
                Iterator v12_1 = v1.zzaqx.iterator();
                while(v12_1.hasNext()) {
                    v12_1.next().onEvent(arg29, arg30, new Bundle(((Bundle)v11_1)), arg31);
                }
            }

            ++v9;
            v6 = v13_1;
        }

        v13_1 = v6;
        ((zzco)this).zzgr();
        if(((zze)this).zzgh().zzla() != null && ("_ae".equals(v13_1))) {
            ((zze)this).zzgj().zzn(true);
        }
    }

    private final void zza(String arg10, String arg11, long arg12, Object arg14) {
        ((zzco)this).zzgn().zzc(new zzcv(this, arg10, arg11, arg14, arg12));
    }

    private final void zza(String arg4, String arg5, String arg6, Bundle arg7) {
        long v0 = ((zzco)this).zzbx().currentTimeMillis();
        Preconditions.checkNotEmpty(arg5);
        ConditionalUserProperty v2 = new ConditionalUserProperty();
        v2.mAppId = arg4;
        v2.mName = arg5;
        v2.mCreationTimestamp = v0;
        if(arg6 != null) {
            v2.mExpiredEventName = arg6;
            v2.mExpiredEventParams = arg7;
        }

        ((zzco)this).zzgn().zzc(new zzdb(this, v2));
    }

    final void zza(String arg12, String arg13, long arg14, Bundle arg16) {
        ((zzco)this).zzgb();
        ((zzco)this).zzaf();
        boolean v7 = this.zzaqw == null || (zzfk.zzcv(arg13)) ? true : false;
        this.zza(arg12, arg13, arg14, arg16, true, v7, false, null);
    }

    public final void zza(String arg7, String arg8, Object arg9, boolean arg10, long arg11) {
        if(arg7 == null) {
            arg7 = "app";
        }

        String v1 = arg7;
        int v7 = 6;
        int v0 = 0;
        int v2 = 24;
        if((arg10) || ("_ap".equals(arg8))) {
            v7 = ((zzco)this).zzgm().zzcs(arg8);
        }
        else {
            zzfk v10 = ((zzco)this).zzgm();
            if(!v10.zzr("user property", arg8)) {
            }
            else if(!v10.zza("user property", UserProperty.zzado, arg8)) {
                v7 = 15;
            }
            else if(!v10.zza("user property", v2, arg8)) {
            }
            else {
                v7 = 0;
            }
        }

        if(v7 != 0) {
            ((zzco)this).zzgm();
            String v9 = zzfk.zza(arg8, v2, true);
            if(arg8 != null) {
                v0 = arg8.length();
            }

            this.zzadj.zzgm().zza(v7, "_ev", v9, v0);
            return;
        }

        if(arg9 != null) {
            v7 = ((zzco)this).zzgm().zzi(arg8, arg9);
            if(v7 != 0) {
                ((zzco)this).zzgm();
                arg8 = zzfk.zza(arg8, v2, true);
                if(((arg9 instanceof String)) || ((arg9 instanceof CharSequence))) {
                    v0 = String.valueOf(arg9).length();
                }

                this.zzadj.zzgm().zza(v7, "_ev", arg8, v0);
                return;
            }

            Object v5 = ((zzco)this).zzgm().zzj(arg8, arg9);
            if(v5 != null) {
                this.zza(v1, arg8, arg11, v5);
            }

            return;
        }

        this.zza(v1, arg8, arg11, null);
    }

    public final void zzaf() {
        super.zzaf();
    }

    public final String zzaj(long arg6) {
        String v0;
        zzar v6;
        String v7 = null;
        if(((zzco)this).zzgn().zzkb()) {
            v6 = ((zzco)this).zzgo().zzjd();
            v0 = "Cannot retrieve app instance id from analytics worker thread";
        }
        else if(zzk.isMainThread()) {
            v6 = ((zzco)this).zzgo().zzjd();
            v0 = "Cannot retrieve app instance id from main thread";
        }
        else {
            goto label_15;
        }

        v6.zzbx(v0);
        return v7;
    label_15:
        arg6 = ((zzco)this).zzbx().elapsedRealtime();
        long v0_1 = 120000;
        String v2 = this.zzak(v0_1);
        long v3 = ((zzco)this).zzbx().elapsedRealtime() - arg6;
        if(v2 == null && v3 < v0_1) {
            v2 = this.zzak(v0_1 - v3);
        }

        return v2;
    }

    private final String zzak(long arg4) {
        AtomicReference v0 = new AtomicReference();
        __monitor_enter(v0);
        try {
            ((zzco)this).zzgn().zzc(new zzcy(this, v0));
            try {
                v0.wait(arg4);
                goto label_8;
            }
            catch(InterruptedException ) {
                try {
                    ((zzco)this).zzgo().zzjg().zzbx("Interrupted waiting for app instance id");
                    __monitor_exit(v0);
                    return null;
                label_8:
                    __monitor_exit(v0);
                    goto label_9;
                label_19:
                    __monitor_exit(v0);
                }
                catch(Throwable v4) {
                    goto label_19;
                }
            }
        }
        catch(Throwable v4) {
            goto label_19;
        }

        throw v4;
    label_9:
        return v0.get();
    }

    public final void zzb(String arg9, String arg10, Object arg11, boolean arg12) {
        this.zza(arg9, arg10, arg11, arg12, ((zzco)this).zzbx().currentTimeMillis());
    }

    static void zzb(zzcs arg0, ConditionalUserProperty arg1) {
        arg0.zzc(arg1);
    }

    private final void zzb(ConditionalUserProperty arg26) {
        zzad v17;
        zzad v11;
        zzad v14;
        ConditionalUserProperty v0 = arg26;
        ((zzco)this).zzaf();
        ((zzf)this).zzcl();
        Preconditions.checkNotNull(arg26);
        Preconditions.checkNotEmpty(v0.mName);
        Preconditions.checkNotEmpty(v0.mOrigin);
        Preconditions.checkNotNull(v0.mValue);
        if(!this.zzadj.isEnabled()) {
            ((zzco)this).zzgo().zzjk().zzbx("Conditional property not sent since collection is disabled");
            return;
        }

        zzfh v2 = new zzfh(v0.mName, v0.mTriggeredTimestamp, v0.mValue, v0.mOrigin);
        try {
            v14 = ((zzco)this).zzgm().zza(v0.mAppId, v0.mTriggeredEventName, v0.mTriggeredEventParams, v0.mOrigin, 0, true, false);
            v11 = ((zzco)this).zzgm().zza(v0.mAppId, v0.mTimedOutEventName, v0.mTimedOutEventParams, v0.mOrigin, 0, true, false);
            v17 = ((zzco)this).zzgm().zza(v0.mAppId, v0.mExpiredEventName, v0.mExpiredEventParams, v0.mOrigin, 0, true, false);
        }
        catch(IllegalArgumentException ) {
            return;
        }

        ((zze)this).zzgg().zzd(new zzl(v0.mAppId, v0.mOrigin, v2, v0.mCreationTimestamp, false, v0.mTriggerEventName, v11, v0.mTriggerTimeout, v14, v0.mTimeToLive, v17));
    }

    @VisibleForTesting private final Map zzb(String arg11, String arg12, String arg13, boolean arg14) {
        zzar v11;
        if(((zzco)this).zzgn().zzkb()) {
            v11 = ((zzco)this).zzgo().zzjd();
            arg12 = "Cannot get user properties from analytics worker thread";
            goto label_6;
        }

        if(zzk.isMainThread()) {
            v11 = ((zzco)this).zzgo().zzjd();
            arg12 = "Cannot get user properties from main thread";
            goto label_6;
        }

        AtomicReference v7 = new AtomicReference();
        __monitor_enter(v7);
        try {
            this.zzadj.zzgn().zzc(new zzde(this, v7, arg11, arg12, arg13, arg14));
            long v11_2 = 5000;
            try {
                v7.wait(v11_2);
                goto label_39;
            }
            catch(InterruptedException v11_3) {
                try {
                    ((zzco)this).zzgo().zzjg().zzg("Interrupted waiting for get user properties", v11_3);
                label_39:
                    __monitor_exit(v7);
                }
                catch(Throwable v11_1) {
                    try {
                    label_59:
                        __monitor_exit(v7);
                    }
                    catch(Throwable v11_1) {
                        goto label_59;
                    }

                    throw v11_1;
                }
            }
        }
        catch(Throwable v11_1) {
            goto label_59;
        }

        Object v11_4 = v7.get();
        if(v11_4 == null) {
            v11 = ((zzco)this).zzgo().zzjg();
            arg12 = "Timed out waiting for get user properties";
        label_6:
            v11.zzbx(arg12);
            return Collections.emptyMap();
        }

        a v12 = new a(((List)v11_4).size());
        Iterator v11_5 = ((List)v11_4).iterator();
        while(v11_5.hasNext()) {
            Object v13 = v11_5.next();
            ((Map)v12).put(((zzfh)v13).name, ((zzfh)v13).getValue());
        }

        return ((Map)v12);
    }

    private final void zzb(String arg14, String arg15, long arg16, Bundle arg18, boolean arg19, boolean arg20, boolean arg21, String arg22) {
        ((zzco)this).zzgn().zzc(new zzcu(this, arg14, arg15, arg16, zzfk.zzf(arg18), arg19, arg20, arg21, arg22));
    }

    public final Clock zzbx() {
        return super.zzbx();
    }

    private final void zzc(ConditionalUserProperty arg23) {
        zzad v17;
        ConditionalUserProperty v0 = arg23;
        ((zzco)this).zzaf();
        ((zzf)this).zzcl();
        Preconditions.checkNotNull(arg23);
        Preconditions.checkNotEmpty(v0.mName);
        if(!this.zzadj.isEnabled()) {
            ((zzco)this).zzgo().zzjk().zzbx("Conditional property not cleared since collection is disabled");
            return;
        }

        zzfh v2 = new zzfh(v0.mName, 0, null, null);
        try {
            v17 = ((zzco)this).zzgm().zza(v0.mAppId, v0.mExpiredEventName, v0.mExpiredEventParams, v0.mOrigin, v0.mCreationTimestamp, true, false);
        }
        catch(IllegalArgumentException ) {
            return;
        }

        ((zze)this).zzgg().zzd(new zzl(v0.mAppId, v0.mOrigin, v2, v0.mCreationTimestamp, v0.mActive, v0.mTriggerEventName, null, v0.mTriggerTimeout, null, v0.mTimeToLive, v17));
    }

    final void zzcm(String arg2) {
        this.zzaqz.set(arg2);
    }

    public final void zzd(boolean arg3) {
        ((zzf)this).zzcl();
        ((zzco)this).zzgb();
        ((zzco)this).zzgn().zzc(new zzdj(this, arg3));
    }

    @VisibleForTesting private final List zzf(String arg10, String arg11, String arg12) {
        zzar v10;
        if(((zzco)this).zzgn().zzkb()) {
            v10 = ((zzco)this).zzgo().zzjd();
            arg11 = "Cannot get conditional user properties from analytics worker thread";
        }
        else if(zzk.isMainThread()) {
            v10 = ((zzco)this).zzgo().zzjd();
            arg11 = "Cannot get conditional user properties from main thread";
        }
        else {
            goto label_15;
        }

        v10.zzbx(arg11);
        goto label_7;
    label_15:
        AtomicReference v6 = new AtomicReference();
        __monitor_enter(v6);
        try {
            this.zzadj.zzgn().zzc(new zzdc(this, v6, arg10, arg11, arg12));
            long v11 = 5000;
            try {
                v6.wait(v11);
                goto label_38;
            }
            catch(InterruptedException v11_1) {
                try {
                    ((zzco)this).zzgo().zzjg().zze("Interrupted waiting for get conditional user properties", arg10, v11_1);
                label_38:
                    __monitor_exit(v6);
                }
                catch(Throwable v10_1) {
                    try {
                    label_118:
                        __monitor_exit(v6);
                    }
                    catch(Throwable v10_1) {
                        goto label_118;
                    }

                    throw v10_1;
                }
            }
        }
        catch(Throwable v10_1) {
            goto label_118;
        }

        Object v11_2 = v6.get();
        if(v11_2 == null) {
            ((zzco)this).zzgo().zzjg().zzg("Timed out waiting for get conditional user properties", arg10);
        label_7:
            return Collections.emptyList();
        }

        ArrayList v10_2 = new ArrayList(((List)v11_2).size());
        Iterator v11_3 = ((List)v11_2).iterator();
        while(v11_3.hasNext()) {
            Object v12 = v11_3.next();
            ConditionalUserProperty v0 = new ConditionalUserProperty();
            v0.mAppId = ((zzl)v12).packageName;
            v0.mOrigin = ((zzl)v12).origin;
            v0.mCreationTimestamp = ((zzl)v12).creationTimestamp;
            v0.mName = ((zzl)v12).zzahb.name;
            v0.mValue = ((zzl)v12).zzahb.getValue();
            v0.mActive = ((zzl)v12).active;
            v0.mTriggerEventName = ((zzl)v12).triggerEventName;
            if(((zzl)v12).zzahc != null) {
                v0.mTimedOutEventName = ((zzl)v12).zzahc.name;
                if(((zzl)v12).zzahc.zzaid != null) {
                    v0.mTimedOutEventParams = ((zzl)v12).zzahc.zzaid.zziv();
                }
            }

            v0.mTriggerTimeout = ((zzl)v12).triggerTimeout;
            if(((zzl)v12).zzahd != null) {
                v0.mTriggeredEventName = ((zzl)v12).zzahd.name;
                if(((zzl)v12).zzahd.zzaid != null) {
                    v0.mTriggeredEventParams = ((zzl)v12).zzahd.zzaid.zziv();
                }
            }

            v0.mTriggeredTimestamp = ((zzl)v12).zzahb.zzaue;
            v0.mTimeToLive = ((zzl)v12).timeToLive;
            if(((zzl)v12).zzahe != null) {
                v0.mExpiredEventName = ((zzl)v12).zzahe.name;
                if(((zzl)v12).zzahe.zzaid != null) {
                    v0.mExpiredEventParams = ((zzl)v12).zzahe.zzaid.zziv();
                }
            }

            ((List)v10_2).add(v0);
        }

        return ((List)v10_2);
    }

    public final String zzfx() {
        ((zzco)this).zzgb();
        return this.zzaqz.get();
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

    private final void zzk(boolean arg4) {
        ((zzco)this).zzaf();
        ((zzco)this).zzgb();
        ((zzf)this).zzcl();
        ((zzco)this).zzgo().zzjk().zzg("Setting app measurement enabled (FE)", Boolean.valueOf(arg4));
        ((zzco)this).zzgp().setMeasurementEnabled(arg4);
        this.zzky();
    }

    public final void zzks() {
        if((((zzco)this).getContext().getApplicationContext() instanceof Application)) {
            ((zzco)this).getContext().getApplicationContext().unregisterActivityLifecycleCallbacks(this.zzaqv);
        }
    }

    public final Boolean zzkt() {
        AtomicReference v1 = new AtomicReference();
        return ((zzco)this).zzgn().zza(v1, 15000, "boolean test flag value", new zzct(this, v1));
    }

    public final String zzku() {
        AtomicReference v1 = new AtomicReference();
        return ((zzco)this).zzgn().zza(v1, 15000, "String test flag value", new zzdd(this, v1));
    }

    public final Long zzkv() {
        AtomicReference v1 = new AtomicReference();
        return ((zzco)this).zzgn().zza(v1, 15000, "long test flag value", new zzdf(this, v1));
    }

    public final Integer zzkw() {
        AtomicReference v1 = new AtomicReference();
        return ((zzco)this).zzgn().zza(v1, 15000, "int test flag value", new zzdg(this, v1));
    }

    public final Double zzkx() {
        AtomicReference v1 = new AtomicReference();
        return ((zzco)this).zzgn().zza(v1, 15000, "double test flag value", new zzdh(this, v1));
    }

    private final void zzky() {
        if(((zzco)this).zzgq().zze(((zze)this).zzgf().zzal(), zzaf.zzalj)) {
            this.zzadj.zzj(false);
        }

        if((((zzco)this).zzgq().zzbd(((zze)this).zzgf().zzal())) && (this.zzadj.isEnabled()) && (this.zzara)) {
            ((zzco)this).zzgo().zzjk().zzbx("Recording app launch after enabling measurement for the first time (FE)");
            this.zzkz();
            return;
        }

        ((zzco)this).zzgo().zzjk().zzbx("Updating Scion state (FE)");
        ((zze)this).zzgg().zzlc();
    }

    public final void zzkz() {
        ((zzco)this).zzaf();
        ((zzco)this).zzgb();
        ((zzf)this).zzcl();
        if(!this.zzadj.zzkr()) {
            return;
        }

        ((zze)this).zzgg().zzkz();
        this.zzara = false;
        String v0 = ((zzco)this).zzgp().zzjw();
        if(!TextUtils.isEmpty(((CharSequence)v0))) {
            ((zzco)this).zzgk().zzcl();
            if(!v0.equals(Build$VERSION.RELEASE)) {
                Bundle v1 = new Bundle();
                v1.putString("_po", v0);
                this.logEvent("auto", "_ou", v1);
            }
        }
    }

    public final List zzl(boolean arg4) {
        String v0;
        zzar v4;
        ((zzco)this).zzgb();
        ((zzf)this).zzcl();
        ((zzco)this).zzgo().zzjk().zzbx("Fetching user attributes (FE)");
        if(((zzco)this).zzgn().zzkb()) {
            v4 = ((zzco)this).zzgo().zzjd();
            v0 = "Cannot get all user properties from analytics worker thread";
            goto label_12;
        }

        if(zzk.isMainThread()) {
            v4 = ((zzco)this).zzgo().zzjd();
            v0 = "Cannot get all user properties from main thread";
            goto label_12;
        }

        AtomicReference v0_1 = new AtomicReference();
        __monitor_enter(v0_1);
        try {
            this.zzadj.zzgn().zzc(new zzcx(this, v0_1, arg4));
            long v1 = 5000;
            try {
                v0_1.wait(v1);
                goto label_37;
            }
            catch(InterruptedException v4_2) {
                try {
                    ((zzco)this).zzgo().zzjg().zzg("Interrupted waiting for get user properties", v4_2);
                label_37:
                    __monitor_exit(v0_1);
                }
                catch(Throwable v4_1) {
                    try {
                    label_46:
                        __monitor_exit(v0_1);
                    }
                    catch(Throwable v4_1) {
                        goto label_46;
                    }

                    throw v4_1;
                }
            }
        }
        catch(Throwable v4_1) {
            goto label_46;
        }

        Object v4_3 = v0_1.get();
        if(v4_3 == null) {
            v4 = ((zzco)this).zzgo().zzjg();
            v0 = "Timed out waiting for get user properties";
        label_12:
            v4.zzbx(v0);
            return Collections.emptyList();
        }

        return ((List)v4_3);
    }
}

