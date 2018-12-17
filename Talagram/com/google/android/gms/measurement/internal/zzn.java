package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import java.lang.reflect.InvocationTargetException;

public final class zzn extends zzco {
    private Boolean zzahf;
    private zzp zzahg;
    private Boolean zzyk;

    zzn(zzbt arg2) {
        super(arg2);
        this.zzahg = zzo.zzahh;
        zzaf.zza(arg2);
    }

    public final Context getContext() {
        return super.getContext();
    }

    public final boolean zza(zza arg2) {
        return this.zzd(null, arg2);
    }

    public final long zza(String arg3, zza arg4) {
        if(arg3 != null) {
            arg3 = this.zzahg.zzf(arg3, arg4.getKey());
            if(TextUtils.isEmpty(((CharSequence)arg3))) {
            }
            else {
                try {
                    return arg4.get(Long.valueOf(Long.parseLong(arg3))).longValue();
                }
                catch(NumberFormatException ) {
                label_1:
                    return arg4.get().longValue();
                }
            }
        }

        goto label_1;
    }

    final void zza(zzp arg1) {
        this.zzahg = arg1;
    }

    public final void zzaf() {
        super.zzaf();
    }

    public final int zzat(String arg2) {
        return this.zzb(arg2, zzaf.zzajr);
    }

    @VisibleForTesting final Boolean zzau(String arg5) {
        Preconditions.checkNotEmpty(arg5);
        Boolean v0 = null;
        try {
            if(((zzco)this).getContext().getPackageManager() == null) {
                ((zzco)this).zzgo().zzjd().zzbx("Failed to load metadata: PackageManager is null");
                return v0;
            }

            ApplicationInfo v1 = Wrappers.packageManager(((zzco)this).getContext()).getApplicationInfo(((zzco)this).getContext().getPackageName(), 128);
            if(v1 == null) {
                ((zzco)this).zzgo().zzjd().zzbx("Failed to load metadata: ApplicationInfo is null");
                return v0;
            }

            if(v1.metaData == null) {
                ((zzco)this).zzgo().zzjd().zzbx("Failed to load metadata: Metadata bundle is null");
                return v0;
            }

            if(!v1.metaData.containsKey(arg5)) {
                return v0;
            }

            return Boolean.valueOf(v1.metaData.getBoolean(arg5));
        }
        catch(PackageManager$NameNotFoundException v5) {
            ((zzco)this).zzgo().zzjd().zzg("Failed to load metadata: Package name not found", v5);
            return v0;
        }
    }

    public final boolean zzav(String arg4) {
        return "1".equals(this.zzahg.zzf(arg4, "gaia_collection_enabled"));
    }

    public final boolean zzaw(String arg4) {
        return "1".equals(this.zzahg.zzf(arg4, "measurement.event_sampling_enabled"));
    }

    final boolean zzax(String arg2) {
        return this.zzd(arg2, zzaf.zzakp);
    }

    final boolean zzay(String arg2) {
        return this.zzd(arg2, zzaf.zzakr);
    }

    final boolean zzaz(String arg2) {
        return this.zzd(arg2, zzaf.zzaks);
    }

    public final int zzb(String arg3, zza arg4) {
        if(arg3 != null) {
            arg3 = this.zzahg.zzf(arg3, arg4.getKey());
            if(TextUtils.isEmpty(((CharSequence)arg3))) {
            }
            else {
                try {
                    return arg4.get(Integer.valueOf(Integer.parseInt(arg3))).intValue();
                }
                catch(NumberFormatException ) {
                label_1:
                    return arg4.get().intValue();
                }
            }
        }

        goto label_1;
    }

    final boolean zzba(String arg2) {
        return this.zzd(arg2, zzaf.zzakk);
    }

    final String zzbb(String arg4) {
        zza v0 = zzaf.zzakl;
        Object v4 = arg4 == null ? v0.get() : v0.get(this.zzahg.zzf(arg4, v0.getKey()));
        return ((String)v4);
    }

    final boolean zzbc(String arg2) {
        return this.zzd(arg2, zzaf.zzakt);
    }

    final boolean zzbd(String arg2) {
        return this.zzd(arg2, zzaf.zzaku);
    }

    final boolean zzbe(String arg2) {
        return this.zzd(arg2, zzaf.zzakx);
    }

    final boolean zzbf(String arg2) {
        return this.zzd(arg2, zzaf.zzaky);
    }

    final boolean zzbg(String arg2) {
        return this.zzd(arg2, zzaf.zzala);
    }

    final boolean zzbh(String arg2) {
        return this.zzd(arg2, zzaf.zzakz);
    }

    final boolean zzbi(String arg2) {
        return this.zzd(arg2, zzaf.zzale);
    }

    final boolean zzbj(String arg2) {
        return this.zzd(arg2, zzaf.zzalf);
    }

    public final Clock zzbx() {
        return super.zzbx();
    }

    public final double zzc(String arg3, zza arg4) {
        if(arg3 != null) {
            arg3 = this.zzahg.zzf(arg3, arg4.getKey());
            if(TextUtils.isEmpty(((CharSequence)arg3))) {
            }
            else {
                try {
                    return arg4.get(Double.valueOf(Double.parseDouble(arg3))).doubleValue();
                }
                catch(NumberFormatException ) {
                label_1:
                    return arg4.get().doubleValue();
                }
            }
        }

        goto label_1;
    }

    public final boolean zzd(String arg3, zza arg4) {
        Object v3;
        if(arg3 != null) {
            arg3 = this.zzahg.zzf(arg3, arg4.getKey());
            if(TextUtils.isEmpty(((CharSequence)arg3))) {
                goto label_1;
            }
            else {
                goto label_10;
            }
        }
        else {
        label_1:
            v3 = arg4.get();
            goto label_2;
        label_10:
            v3 = arg4.get(Boolean.valueOf(Boolean.parseBoolean(arg3)));
        }

    label_2:
        return ((Boolean)v3).booleanValue();
    }

    public final boolean zzdw() {
        if(this.zzyk == null) {
            __monitor_enter(this);
            try {
                if(this.zzyk == null) {
                    ApplicationInfo v0_1 = ((zzco)this).getContext().getApplicationInfo();
                    String v1 = ProcessUtils.getMyProcessName();
                    if(v0_1 != null) {
                        String v0_2 = v0_1.processName;
                        boolean v0_3 = v0_2 == null || !v0_2.equals(v1) ? false : true;
                        this.zzyk = Boolean.valueOf(v0_3);
                    }

                    if(this.zzyk != null) {
                        goto label_26;
                    }

                    this.zzyk = Boolean.TRUE;
                    ((zzco)this).zzgo().zzjd().zzbx("My process not in the list of running processes");
                }

            label_26:
                __monitor_exit(this);
                goto label_31;
            label_29:
                __monitor_exit(this);
            }
            catch(Throwable v0) {
                goto label_29;
            }

            throw v0;
        }

    label_31:
        return this.zzyk.booleanValue();
    }

    public final boolean zze(String arg1, zza arg2) {
        return this.zzd(arg1, arg2);
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

    public final long zzhc() {
        ((zzco)this).zzgr();
        return 13001;
    }

    static String zzht() {
        return zzaf.zzajd.get();
    }

    public final boolean zzhu() {
        ((zzco)this).zzgr();
        Boolean v0 = this.zzau("firebase_analytics_collection_deactivated");
        if(v0 != null && (v0.booleanValue())) {
            return 1;
        }

        return 0;
    }

    public final Boolean zzhv() {
        ((zzco)this).zzgr();
        return this.zzau("firebase_analytics_collection_enabled");
    }

    public static long zzhw() {
        return zzaf.zzakg.get().longValue();
    }

    public static long zzhx() {
        return zzaf.zzajg.get().longValue();
    }

    public final String zzhy() {
        String v2;
        zzar v1;
        try {
            return Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, "debug.firebase.analytics.app", "");
        }
        catch(InvocationTargetException v0) {
            v1 = ((zzco)this).zzgo().zzjd();
            v2 = "SystemProperties.get() threw an exception";
        }
        catch(IllegalAccessException v0_1) {
            v1 = ((zzco)this).zzgo().zzjd();
            v2 = "Could not access SystemProperties.get()";
        }
        catch(NoSuchMethodException v0_2) {
            v1 = ((zzco)this).zzgo().zzjd();
            v2 = "Could not find SystemProperties.get() method";
        }
        catch(ClassNotFoundException v0_3) {
            v1 = ((zzco)this).zzgo().zzjd();
            v2 = "Could not find SystemProperties class";
        }

        v1.zzg(v2, v0_3);
        return "";
    }

    public static boolean zzhz() {
        return zzaf.zzajc.get().booleanValue();
    }

    static boolean zzia() {
        return zzaf.zzalb.get().booleanValue();
    }

    final boolean zzib() {
        if(this.zzahf == null) {
            this.zzahf = this.zzau("app_measurement_lite");
            if(this.zzahf == null) {
                this.zzahf = Boolean.valueOf(false);
            }
        }

        if(!this.zzahf.booleanValue()) {
            if(!this.zzadj.zzkn()) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    static boolean zzic() {
        return zzaf.zzald.get().booleanValue();
    }
}

