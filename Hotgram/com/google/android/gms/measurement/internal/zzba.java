package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.os.Build$VERSION;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.util.VisibleForTesting;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

final class zzba extends zzcp {
    private SharedPreferences zzabr;
    @VisibleForTesting static final Pair zzanc;
    public zzbe zzand;
    public final zzbd zzane;
    public final zzbd zzanf;
    public final zzbd zzang;
    public final zzbd zzanh;
    public final zzbd zzani;
    public final zzbd zzanj;
    public final zzbd zzank;
    public final zzbf zzanl;
    private String zzanm;
    private boolean zzann;
    private long zzano;
    public final zzbd zzanp;
    public final zzbd zzanq;
    public final zzbc zzanr;
    public final zzbf zzans;
    public final zzbd zzant;
    public final zzbd zzanu;
    public boolean zzanv;

    static {
        zzba.zzanc = new Pair("", Long.valueOf(0));
    }

    zzba(zzbt arg6) {
        super(arg6);
        this.zzane = new zzbd(this, "last_upload", 0);
        this.zzanf = new zzbd(this, "last_upload_attempt", 0);
        this.zzang = new zzbd(this, "backoff", 0);
        this.zzanh = new zzbd(this, "last_delete_stale", 0);
        this.zzanp = new zzbd(this, "time_before_start", 10000);
        this.zzanq = new zzbd(this, "session_timeout", 1800000);
        this.zzanr = new zzbc(this, "start_new_session", true);
        this.zzans = new zzbf(this, "allow_ad_personalization", null);
        this.zzant = new zzbd(this, "last_pause_time", 0);
        this.zzanu = new zzbd(this, "time_active", 0);
        this.zzani = new zzbd(this, "midnight_offset", 0);
        this.zzanj = new zzbd(this, "first_open_time", 0);
        this.zzank = new zzbd(this, "app_install_time", 0);
        this.zzanl = new zzbf(this, "app_instance_id", null);
    }

    final void setMeasurementEnabled(boolean arg4) {
        ((zzco)this).zzaf();
        ((zzco)this).zzgo().zzjl().zzg("Setting measurementEnabled", Boolean.valueOf(arg4));
        SharedPreferences$Editor v0 = this.zzjr().edit();
        v0.putBoolean("measurement_enabled", arg4);
        v0.apply();
    }

    static SharedPreferences zza(zzba arg0) {
        return arg0.zzjr();
    }

    final Pair zzby(String arg6) {
        ((zzco)this).zzaf();
        long v0 = ((zzco)this).zzbx().elapsedRealtime();
        if(this.zzanm != null && v0 < this.zzano) {
            return new Pair(this.zzanm, Boolean.valueOf(this.zzann));
        }

        this.zzano = v0 + ((zzco)this).zzgq().zza(arg6, zzaf.zzaje);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            Info v6_1 = AdvertisingIdClient.getAdvertisingIdInfo(((zzco)this).getContext());
            if(v6_1 != null) {
                this.zzanm = v6_1.getId();
                this.zzann = v6_1.isLimitAdTrackingEnabled();
            }

            if(this.zzanm != null) {
                goto label_39;
            }

            this.zzanm = "";
        }
        catch(Exception v6) {
            ((zzco)this).zzgo().zzjk().zzg("Unable to get advertising id", v6);
            this.zzanm = "";
        }

    label_39:
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair(this.zzanm, Boolean.valueOf(this.zzann));
    }

    final String zzbz(String arg8) {
        ((zzco)this).zzaf();
        Object v8 = this.zzby(arg8).first;
        MessageDigest v0 = zzfk.getMessageDigest();
        if(v0 == null) {
            return null;
        }

        return String.format(Locale.US, "%032X", new BigInteger(1, v0.digest(((String)v8).getBytes())));
    }

    final void zzca(String arg3) {
        ((zzco)this).zzaf();
        SharedPreferences$Editor v0 = this.zzjr().edit();
        v0.putString("gmp_app_id", arg3);
        v0.apply();
    }

    final void zzcb(String arg3) {
        ((zzco)this).zzaf();
        SharedPreferences$Editor v0 = this.zzjr().edit();
        v0.putString("admob_app_id", arg3);
        v0.apply();
    }

    final void zzg(boolean arg4) {
        ((zzco)this).zzaf();
        ((zzco)this).zzgo().zzjl().zzg("Setting useService", Boolean.valueOf(arg4));
        SharedPreferences$Editor v0 = this.zzjr().edit();
        v0.putBoolean("use_service", arg4);
        v0.apply();
    }

    protected final boolean zzgt() {
        return 1;
    }

    protected final void zzgu() {
        this.zzabr = ((zzco)this).getContext().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzanv = this.zzabr.getBoolean("has_been_opened", false);
        if(!this.zzanv) {
            SharedPreferences$Editor v0 = this.zzabr.edit();
            v0.putBoolean("has_been_opened", true);
            v0.apply();
        }

        this.zzand = new zzbe(this, "health_monitor", Math.max(0, zzaf.zzajf.get().longValue()), null);
    }

    final boolean zzh(boolean arg3) {
        ((zzco)this).zzaf();
        return this.zzjr().getBoolean("measurement_enabled", arg3);
    }

    final void zzi(boolean arg4) {
        ((zzco)this).zzaf();
        ((zzco)this).zzgo().zzjl().zzg("Updating deferred analytics collection", Boolean.valueOf(arg4));
        SharedPreferences$Editor v0 = this.zzjr().edit();
        v0.putBoolean("deferred_analytics_collection", arg4);
        v0.apply();
    }

    private final SharedPreferences zzjr() {
        ((zzco)this).zzaf();
        ((zzcp)this).zzcl();
        return this.zzabr;
    }

    final String zzjs() {
        ((zzco)this).zzaf();
        return this.zzjr().getString("gmp_app_id", null);
    }

    final String zzjt() {
        ((zzco)this).zzaf();
        return this.zzjr().getString("admob_app_id", null);
    }

    final Boolean zzju() {
        ((zzco)this).zzaf();
        if(!this.zzjr().contains("use_service")) {
            return null;
        }

        return Boolean.valueOf(this.zzjr().getBoolean("use_service", false));
    }

    final void zzjv() {
        ((zzco)this).zzaf();
        ((zzco)this).zzgo().zzjl().zzbx("Clearing collection preferences.");
        boolean v0 = this.zzjr().contains("measurement_enabled");
        boolean v1 = true;
        if(v0) {
            v1 = this.zzh(true);
        }

        SharedPreferences$Editor v2 = this.zzjr().edit();
        v2.clear();
        v2.apply();
        if(v0) {
            this.setMeasurementEnabled(v1);
        }
    }

    protected final String zzjw() {
        ((zzco)this).zzaf();
        String v0 = this.zzjr().getString("previous_os_version", null);
        ((zzco)this).zzgk().zzcl();
        String v1 = Build$VERSION.RELEASE;
        if(!TextUtils.isEmpty(((CharSequence)v1)) && !v1.equals(v0)) {
            SharedPreferences$Editor v2 = this.zzjr().edit();
            v2.putString("previous_os_version", v1);
            v2.apply();
        }

        return v0;
    }

    final boolean zzjx() {
        ((zzco)this).zzaf();
        return this.zzjr().getBoolean("deferred_analytics_collection", false);
    }

    final boolean zzjy() {
        return this.zzabr.contains("deferred_analytics_collection");
    }
}

