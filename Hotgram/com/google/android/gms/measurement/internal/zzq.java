package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v4.f.a;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzfu;
import com.google.android.gms.internal.measurement.zzfv;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzgf;
import com.google.android.gms.internal.measurement.zzgg;
import com.google.android.gms.internal.measurement.zzgi;
import com.google.android.gms.internal.measurement.zzgj;
import com.google.android.gms.internal.measurement.zzyx;
import com.google.android.gms.internal.measurement.zzyy;
import com.google.android.gms.internal.measurement.zzzg;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzq extends zzez {
    private static final String[] zzahi;
    private static final String[] zzahj;
    private static final String[] zzahk;
    private static final String[] zzahl;
    private static final String[] zzahm;
    private static final String[] zzahn;
    private final zzt zzaho;
    private final zzev zzahp;

    static {
        zzq.zzahi = new String[]{"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;"};
        zzq.zzahj = new String[]{"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
        zzq.zzahk = new String[]{"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;"};
        zzq.zzahl = new String[]{"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
        zzq.zzahm = new String[]{"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
        zzq.zzahn = new String[]{"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    }

    zzq(zzfa arg3) {
        super(arg3);
        this.zzahp = new zzev(((zzco)this).zzbx());
        this.zzaho = new zzt(this, ((zzco)this).getContext(), "google_app_measurement.db");
    }

    public final void beginTransaction() {
        ((zzez)this).zzcl();
        this.getWritableDatabase().beginTransaction();
    }

    public final void endTransaction() {
        ((zzez)this).zzcl();
        this.getWritableDatabase().endTransaction();
    }

    @VisibleForTesting final SQLiteDatabase getWritableDatabase() {
        ((zzco)this).zzaf();
        try {
            return this.zzaho.getWritableDatabase();
        }
        catch(SQLiteException v0) {
            ((zzco)this).zzgo().zzjg().zzg("Error opening database", v0);
            throw v0;
        }
    }

    public final void setTransactionSuccessful() {
        ((zzez)this).zzcl();
        this.getWritableDatabase().setTransactionSuccessful();
    }

    static zzev zza(zzq arg0) {
        return arg0.zzahp;
    }

    public final boolean zza(zzfj arg10) {
        Preconditions.checkNotNull(arg10);
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        if(this.zzi(arg10.zztt, arg10.name) == null) {
            long v2 = 25;
            if(!zzfk.zzcq(arg10.name)) {
                long v5 = this.zza("select count(1) from user_attributes where app_id=? and origin=? AND name like \'!_%\' escape \'!\'", new String[]{arg10.zztt, arg10.origin});
                if(((zzco)this).zzgq().zze(arg10.zztt, zzaf.zzalj)) {
                    if(!"_ap".equals(arg10.name) && v5 >= v2) {
                        return 0;
                    }
                }
                else if(v5 >= v2) {
                    return 0;
                }
            }
            else if(this.zza("select count(1) from user_attributes where app_id=? and name not like \'!_%\' escape \'!\'", new String[]{arg10.zztt}) >= v2) {
                return 0;
            }
        }

        ContentValues v0 = new ContentValues();
        v0.put("app_id", arg10.zztt);
        v0.put("origin", arg10.origin);
        v0.put("name", arg10.name);
        v0.put("set_timestamp", Long.valueOf(arg10.zzaue));
        zzq.zza(v0, "value", arg10.value);
        try {
            if(this.getWritableDatabase().insertWithOnConflict("user_attributes", null, v0, 5) != -1) {
                return 1;
            }

            ((zzco)this).zzgo().zzjd().zzg("Failed to insert/update user property (got -1). appId", zzap.zzbv(arg10.zztt));
        }
        catch(SQLiteException v0_1) {
            ((zzco)this).zzgo().zzjd().zze("Error storing user property. appId", zzap.zzbv(arg10.zztt), v0_1);
        }

        return 1;
    }

    public final zzr zza(long arg20, String arg22, boolean arg23, boolean arg24, boolean arg25, boolean arg26, boolean arg27) {
        Cursor v5_1;
        Cursor v6;
        int v5;
        int v15;
        SQLiteDatabase v14;
        Preconditions.checkNotEmpty(arg22);
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        String[] v2 = new String[]{arg22};
        zzr v4 = new zzr();
        try {
            v14 = this.getWritableDatabase();
            String[] v8 = new String[6];
            v8[0] = "day";
            v8[1] = "daily_events_count";
            v15 = 2;
            v8[v15] = "daily_public_events_count";
            v8[3] = "daily_conversions_count";
            v8[4] = "daily_error_events_count";
            v8[5] = "daily_realtime_events_count";
            v5 = 3;
            v6 = v14.query("apps", v8, "app_id=?", new String[]{arg22}, null, null, null);
        }
        catch(Throwable v0) {
            v6 = null;
            goto label_144;
        }
        catch(SQLiteException v0_1) {
            v5_1 = null;
            goto label_134;
        }

        try {
            if(v6.moveToFirst()) {
                goto label_53;
            }

            ((zzco)this).zzgo().zzjg().zzg("Not updating daily counts, app is not known. appId", zzap.zzbv(arg22));
            if(v6 != null) {
                goto label_51;
            }

            return v4;
        }
        catch(Throwable v0) {
            goto label_125;
        }
        catch(SQLiteException v0_1) {
            goto label_127;
        }

    label_51:
        v6.close();
        return v4;
        try {
        label_53:
            if(v6.getLong(0) == arg20) {
                v4.zzahr = v6.getLong(1);
                v4.zzahq = v6.getLong(v15);
                v4.zzahs = v6.getLong(v5);
                v4.zzaht = v6.getLong(4);
                v4.zzahu = v6.getLong(5);
            }

            long v7 = 1;
            if(arg23) {
                v4.zzahr += v7;
            }

            if(arg24) {
                v4.zzahq += v7;
            }

            if(arg25) {
                v4.zzahs += v7;
            }

            if(arg26) {
                v4.zzaht += v7;
            }

            if(arg27) {
                v4.zzahu += v7;
            }

            ContentValues v0_2 = new ContentValues();
            v0_2.put("day", Long.valueOf(arg20));
            v0_2.put("daily_public_events_count", Long.valueOf(v4.zzahq));
            v0_2.put("daily_events_count", Long.valueOf(v4.zzahr));
            v0_2.put("daily_conversions_count", Long.valueOf(v4.zzahs));
            v0_2.put("daily_error_events_count", Long.valueOf(v4.zzaht));
            v0_2.put("daily_realtime_events_count", Long.valueOf(v4.zzahu));
            v14.update("apps", v0_2, "app_id=?", v2);
            if(v6 == null) {
                return v4;
            }

            goto label_122;
        }
        catch(Throwable v0) {
        label_125:
        }
        catch(SQLiteException v0_1) {
        label_127:
            v5_1 = v6;
            try {
            label_134:
                ((zzco)this).zzgo().zzjd().zze("Error updating daily counts. appId", zzap.zzbv(arg22), v0_1);
                if(v5_1 == null) {
                    return v4;
                }
            }
            catch(Throwable v0) {
                v6 = v5_1;
                goto label_144;
            }

            v5_1.close();
            return v4;
        }

    label_144:
        if(v6 != null) {
            v6.close();
        }

        throw v0;
    label_122:
        v6.close();
        return v4;
    }

    public final void zza(zzz arg6) {
        Preconditions.checkNotNull(arg6);
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        ContentValues v0 = new ContentValues();
        v0.put("app_id", arg6.zztt);
        v0.put("name", arg6.name);
        v0.put("lifetime_count", Long.valueOf(arg6.zzaie));
        v0.put("current_bundle_count", Long.valueOf(arg6.zzaif));
        v0.put("last_fire_timestamp", Long.valueOf(arg6.zzaig));
        v0.put("last_bundled_timestamp", Long.valueOf(arg6.zzaih));
        v0.put("last_bundled_day", arg6.zzaii);
        v0.put("last_sampled_complex_event_id", arg6.zzaij);
        v0.put("last_sampling_rate", arg6.zzaik);
        String v2 = null;
        Long v1 = arg6.zzail == null || !arg6.zzail.booleanValue() ? ((Long)v2) : Long.valueOf(1);
        v0.put("last_exempt_from_sampling", v1);
        try {
            if(this.getWritableDatabase().insertWithOnConflict("events", v2, v0, 5) == -1) {
                ((zzco)this).zzgo().zzjd().zzg("Failed to insert/update event aggregates (got -1). appId", zzap.zzbv(arg6.zztt));
            }
        }
        catch(SQLiteException v0_1) {
            ((zzco)this).zzgo().zzjd().zze("Error storing event aggregates. appId", zzap.zzbv(arg6.zztt), v0_1);
            return;
        }
    }

    public final void zza(zzg arg8) {
        Preconditions.checkNotNull(arg8);
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        ContentValues v0 = new ContentValues();
        v0.put("app_id", arg8.zzal());
        v0.put("app_instance_id", arg8.getAppInstanceId());
        v0.put("gmp_app_id", arg8.getGmpAppId());
        v0.put("resettable_device_id_hash", arg8.zzgx());
        v0.put("last_bundle_index", Long.valueOf(arg8.zzhe()));
        v0.put("last_bundle_start_timestamp", Long.valueOf(arg8.zzgy()));
        v0.put("last_bundle_end_timestamp", Long.valueOf(arg8.zzgz()));
        v0.put("app_version", arg8.zzak());
        v0.put("app_store", arg8.zzhb());
        v0.put("gmp_version", Long.valueOf(arg8.zzhc()));
        v0.put("dev_cert_hash", Long.valueOf(arg8.zzhd()));
        v0.put("measurement_enabled", Boolean.valueOf(arg8.isMeasurementEnabled()));
        v0.put("day", Long.valueOf(arg8.zzhi()));
        v0.put("daily_public_events_count", Long.valueOf(arg8.zzhj()));
        v0.put("daily_events_count", Long.valueOf(arg8.zzhk()));
        v0.put("daily_conversions_count", Long.valueOf(arg8.zzhl()));
        v0.put("config_fetched_time", Long.valueOf(arg8.zzhf()));
        v0.put("failed_config_fetch_time", Long.valueOf(arg8.zzhg()));
        v0.put("app_version_int", Long.valueOf(arg8.zzha()));
        v0.put("firebase_instance_id", arg8.getFirebaseInstanceId());
        v0.put("daily_error_events_count", Long.valueOf(arg8.zzhn()));
        v0.put("daily_realtime_events_count", Long.valueOf(arg8.zzhm()));
        v0.put("health_monitor_sample", arg8.zzho());
        v0.put("android_id", Long.valueOf(arg8.zzhq()));
        v0.put("adid_reporting_enabled", Boolean.valueOf(arg8.zzhr()));
        v0.put("ssaid_reporting_enabled", Boolean.valueOf(arg8.zzhs()));
        v0.put("admob_app_id", arg8.zzgw());
        try {
            SQLiteDatabase v1 = this.getWritableDatabase();
            if((((long)v1.update("apps", v0, "app_id = ?", new String[]{arg8.zzal()}))) == 0 && v1.insertWithOnConflict("apps", null, v0, 5) == -1) {
                ((zzco)this).zzgo().zzjd().zzg("Failed to insert/update app (got -1). appId", zzap.zzbv(arg8.zzal()));
            }
        }
        catch(SQLiteException v0_1) {
            ((zzco)this).zzgo().zzjd().zze("Error storing app. appId", zzap.zzbv(arg8.zzal()), v0_1);
            return;
        }
    }

    public final boolean zza(zzgi arg8, boolean arg9) {
        String v2_1;
        zzar v1_1;
        byte[] v1;
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        Preconditions.checkNotNull(arg8);
        Preconditions.checkNotEmpty(arg8.zztt);
        Preconditions.checkNotNull(arg8.zzaxf);
        this.zzif();
        long v0 = ((zzco)this).zzbx().currentTimeMillis();
        if(arg8.zzaxf.longValue() < v0 - zzn.zzhw() || arg8.zzaxf.longValue() > zzn.zzhw() + v0) {
            ((zzco)this).zzgo().zzjg().zzd("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzap.zzbv(arg8.zztt), Long.valueOf(v0), arg8.zzaxf);
        }

        try {
            v1 = new byte[((zzzg)arg8).zzvu()];
            zzyy v2 = zzyy.zzk(v1, 0, v1.length);
            ((zzzg)arg8).zza(v2);
            v2.zzyt();
            v1 = ((zzey)this).zzjo().zzb(v1);
        }
        catch(IOException v9) {
            v1_1 = ((zzco)this).zzgo().zzjd();
            v2_1 = "Data loss. Failed to serialize bundle. appId";
            goto label_80;
        }

        ((zzco)this).zzgo().zzjl().zzg("Saving bundle, size", Integer.valueOf(v1.length));
        ContentValues v2_2 = new ContentValues();
        v2_2.put("app_id", arg8.zztt);
        v2_2.put("bundle_end_timestamp", arg8.zzaxf);
        v2_2.put("data", v1);
        v2_2.put("has_realtime", Integer.valueOf(((int)arg9)));
        if(arg8.zzayc != null) {
            v2_2.put("retry_count", arg8.zzayc);
        }

        try {
            if(this.getWritableDatabase().insert("queue", null, v2_2) != -1) {
                return 1;
            }

            ((zzco)this).zzgo().zzjd().zzg("Failed to insert bundle (got -1). appId", zzap.zzbv(arg8.zztt));
            return 0;
        }
        catch(SQLiteException v9_1) {
            v1_1 = ((zzco)this).zzgo().zzjd();
            v2_1 = "Error storing bundle. appId";
        }

    label_80:
        v1_1.zze(v2_1, zzap.zzbv(arg8.zztt), v9);
        return 0;
    }

    public final boolean zza(zzl arg8) {
        Preconditions.checkNotNull(arg8);
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        if(this.zzi(arg8.packageName, arg8.zzahb.name) == null && this.zza("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{arg8.packageName}) >= 1000) {
            return 0;
        }

        ContentValues v0 = new ContentValues();
        v0.put("app_id", arg8.packageName);
        v0.put("origin", arg8.origin);
        v0.put("name", arg8.zzahb.name);
        zzq.zza(v0, "value", arg8.zzahb.getValue());
        v0.put("active", Boolean.valueOf(arg8.active));
        v0.put("trigger_event_name", arg8.triggerEventName);
        v0.put("trigger_timeout", Long.valueOf(arg8.triggerTimeout));
        ((zzco)this).zzgm();
        v0.put("timed_out_event", zzfk.zza(arg8.zzahc));
        v0.put("creation_timestamp", Long.valueOf(arg8.creationTimestamp));
        ((zzco)this).zzgm();
        v0.put("triggered_event", zzfk.zza(arg8.zzahd));
        v0.put("triggered_timestamp", Long.valueOf(arg8.zzahb.zzaue));
        v0.put("time_to_live", Long.valueOf(arg8.timeToLive));
        ((zzco)this).zzgm();
        v0.put("expired_event", zzfk.zza(arg8.zzahe));
        try {
            if(this.getWritableDatabase().insertWithOnConflict("conditional_properties", null, v0, 5) != -1) {
                return 1;
            }

            ((zzco)this).zzgo().zzjd().zzg("Failed to insert/update conditional user property (got -1)", zzap.zzbv(arg8.packageName));
        }
        catch(SQLiteException v0_1) {
            ((zzco)this).zzgo().zzjd().zze("Error storing conditional user property", zzap.zzbv(arg8.packageName), v0_1);
        }

        return 1;
    }

    final void zza(String arg13, zzfu[] arg14) {
        int v7_3;
        Integer v3_1;
        Object v8_1;
        String v7_1;
        zzar v5_2;
        ((zzez)this).zzcl();
        ((zzco)this).zzaf();
        Preconditions.checkNotEmpty(arg13);
        Preconditions.checkNotNull(arg14);
        SQLiteDatabase v0 = this.getWritableDatabase();
        v0.beginTransaction();
        try {
            ((zzez)this).zzcl();
            ((zzco)this).zzaf();
            Preconditions.checkNotEmpty(arg13);
            SQLiteDatabase v1 = this.getWritableDatabase();
            String[] v5 = new String[1];
            int v6 = 0;
            v5[0] = arg13;
            v1.delete("property_filters", "app_id=?", v5);
            v1.delete("event_filters", "app_id=?", new String[]{arg13});
            int v1_1 = arg14.length;
            int v2;
            for(v2 = 0; v2 < v1_1; ++v2) {
                zzfu v3 = arg14[v2];
                ((zzez)this).zzcl();
                ((zzco)this).zzaf();
                Preconditions.checkNotEmpty(arg13);
                Preconditions.checkNotNull(v3);
                Preconditions.checkNotNull(v3.zzava);
                Preconditions.checkNotNull(v3.zzauz);
                if(v3.zzauy == null) {
                    ((zzco)this).zzgo().zzjg().zzg("Audience with no ID. appId", zzap.zzbv(arg13));
                }
                else {
                    int v5_1 = v3.zzauy.intValue();
                    zzfv[] v7 = v3.zzava;
                    int v8 = v7.length;
                    int v9 = 0;
                    while(true) {
                        if(v9 >= v8) {
                            break;
                        }
                        else if(v7[v9].zzave == null) {
                            v5_2 = ((zzco)this).zzgo().zzjg();
                            v7_1 = "Event filter with no ID. Audience definition ignored. appId, audienceId";
                            v8_1 = zzap.zzbv(arg13);
                            v3_1 = v3.zzauy;
                        }
                        else {
                            ++v9;
                            continue;
                        }

                        goto label_56;
                    }

                    zzfy[] v7_2 = v3.zzauz;
                    v8 = v7_2.length;
                    v9 = 0;
                    while(true) {
                        if(v9 >= v8) {
                            break;
                        }
                        else if(v7_2[v9].zzave == null) {
                            v5_2 = ((zzco)this).zzgo().zzjg();
                            v7_1 = "Property filter with no ID. Audience definition ignored. appId, audienceId";
                            v8_1 = zzap.zzbv(arg13);
                            v3_1 = v3.zzauy;
                        }
                        else {
                            ++v9;
                            continue;
                        }

                        goto label_56;
                    }

                    v7 = v3.zzava;
                    v8 = v7.length;
                    v9 = 0;
                    while(true) {
                        if(v9 >= v8) {
                            break;
                        }
                        else if(!this.zza(arg13, v5_1, v7[v9])) {
                            v7_3 = 0;
                        }
                        else {
                            ++v9;
                            continue;
                        }

                        goto label_87;
                    }

                    v7_3 = 1;
                label_87:
                    if(v7_3 != 0) {
                        zzfy[] v3_2 = v3.zzauz;
                        v8 = v3_2.length;
                        v9 = 0;
                        while(v9 < v8) {
                            if(!this.zza(arg13, v5_1, v3_2[v9])) {
                                v7_3 = 0;
                            }
                            else {
                                ++v9;
                                continue;
                            }

                            break;
                        }
                    }

                    if(v7_3 != 0) {
                        goto label_119;
                    }

                    ((zzez)this).zzcl();
                    ((zzco)this).zzaf();
                    Preconditions.checkNotEmpty(arg13);
                    SQLiteDatabase v3_3 = this.getWritableDatabase();
                    v3_3.delete("property_filters", "app_id=? and audience_id=?", new String[]{arg13, String.valueOf(v5_1)});
                    v3_3.delete("event_filters", "app_id=? and audience_id=?", new String[]{arg13, String.valueOf(v5_1)});
                    goto label_119;
                label_56:
                    v5_2.zze(v7_1, v8_1, v3_1);
                }

            label_119:
            }

            ArrayList v1_2 = new ArrayList();
            v2 = arg14.length;
            while(v6 < v2) {
                ((List)v1_2).add(arg14[v6].zzauy);
                ++v6;
            }

            this.zza(arg13, ((List)v1_2));
            v0.setTransactionSuccessful();
        }
        catch(Throwable v13) {
            goto label_135;
        }

        v0.endTransaction();
        return;
    label_135:
        v0.endTransaction();
        throw v13;
    }

    public final Pair zza(String arg8, Long arg9) {
        Pair v8_2;
        zzgf v4;
        Long v3;
        Cursor v1;
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        Pair v0 = null;
        try {
            v1 = this.getWritableDatabase().rawQuery("select main_event, children_to_process from main_event_params where app_id=? and event_id=?", new String[]{arg8, String.valueOf(arg9)});
        }
        catch(Throwable v8) {
            v1 = ((Cursor)v0);
            goto label_58;
        }
        catch(SQLiteException v8_1) {
            v1 = ((Cursor)v0);
            goto label_50;
        }

        try {
            if(v1.moveToFirst()) {
                goto label_22;
            }

            ((zzco)this).zzgo().zzjl().zzbx("Main event not found");
            if(v1 != null) {
                goto label_20;
            }

            return v0;
        }
        catch(Throwable v8) {
            goto label_58;
        }
        catch(SQLiteException v8_1) {
            goto label_44;
        }

    label_20:
        v1.close();
        return v0;
        try {
        label_22:
            byte[] v2 = v1.getBlob(0);
            v3 = Long.valueOf(v1.getLong(1));
            zzyx v2_1 = zzyx.zzj(v2, 0, v2.length);
            v4 = new zzgf();
            try {
                ((zzzg)v4).zza(v2_1);
            }
            catch(IOException v2_2) {
                try {
                    ((zzco)this).zzgo().zzjd().zzd("Failed to merge main event. appId, eventId", zzap.zzbv(arg8), arg9, v2_2);
                    if(v1 != null) {
                        goto label_41;
                    }

                    return v0;
                }
                catch(Throwable v8) {
                    goto label_58;
                }
                catch(SQLiteException v8_1) {
                    goto label_44;
                }

            label_41:
                v1.close();
                return v0;
            }
        }
        catch(Throwable v8) {
            goto label_58;
        }
        catch(SQLiteException v8_1) {
            goto label_44;
        }

        try {
            v8_2 = Pair.create(v4, v3);
            if(v1 == null) {
                return v8_2;
            }

            goto label_32;
        }
        catch(Throwable v8) {
        }
        catch(SQLiteException v8_1) {
        label_44:
            try {
            label_50:
                ((zzco)this).zzgo().zzjd().zzg("Error selecting main event", v8_1);
                if(v1 != null) {
                    goto label_55;
                }

                return v0;
            }
            catch(Throwable v8) {
                goto label_58;
            }

        label_55:
            v1.close();
            return v0;
        }

    label_58:
        if(v1 != null) {
            v1.close();
        }

        throw v8;
    label_32:
        v1.close();
        return v8_2;
    }

    public final boolean zza(String arg6, Long arg7, long arg8, zzgf arg10) {
        byte[] v1;
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        Preconditions.checkNotNull(arg10);
        Preconditions.checkNotEmpty(arg6);
        Preconditions.checkNotNull(arg7);
        try {
            v1 = new byte[((zzzg)arg10).zzvu()];
            zzyy v2 = zzyy.zzk(v1, 0, v1.length);
            ((zzzg)arg10).zza(v2);
            v2.zzyt();
        }
        catch(IOException v8) {
            ((zzco)this).zzgo().zzjd().zzd("Data loss. Failed to serialize event params/data. appId, eventId", zzap.zzbv(arg6), arg7, v8);
            return 0;
        }

        ((zzco)this).zzgo().zzjl().zze("Saving complex main event, appId, data size", ((zzco)this).zzgl().zzbs(arg6), Integer.valueOf(v1.length));
        ContentValues v10 = new ContentValues();
        v10.put("app_id", arg6);
        v10.put("event_id", arg7);
        v10.put("children_to_process", Long.valueOf(arg8));
        v10.put("main_event", v1);
        try {
            if(this.getWritableDatabase().insertWithOnConflict("main_event_params", null, v10, 5) != -1) {
                return 1;
            }

            ((zzco)this).zzgo().zzjd().zzg("Failed to insert complex main event (got -1). appId", zzap.zzbv(arg6));
            return 0;
        }
        catch(SQLiteException v7) {
            ((zzco)this).zzgo().zzjd().zze("Error storing complex main event. appId", zzap.zzbv(arg6), v7);
            return 0;
        }

        return 1;
    }

    private final long zza(String arg4, String[] arg5) {
        long v0_2;
        Cursor v5;
        SQLiteDatabase v0 = this.getWritableDatabase();
        Cursor v1 = null;
        try {
            v5 = v0.rawQuery(arg4, arg5);
            goto label_3;
        }
        catch(Throwable v4) {
        }
        catch(SQLiteException v0_1) {
            goto label_23;
            try {
            label_3:
                if(!v5.moveToFirst()) {
                    goto label_10;
                }

                v0_2 = v5.getLong(0);
                if(v5 != null) {
                    goto label_8;
                }

                return v0_2;
            }
            catch(SQLiteException v0_1) {
                goto label_17;
            }
            catch(Throwable v4) {
                goto label_15;
            }

        label_8:
            v5.close();
            return v0_2;
            try {
            label_10:
                throw new SQLiteException("Database returned empty set");
            }
            catch(Throwable v4) {
            label_15:
            }
            catch(SQLiteException v0_1) {
            label_17:
                v1 = v5;
                try {
                label_23:
                    ((zzco)this).zzgo().zzjd().zze("Database error", arg4, v0_1);
                    throw v0_1;
                }
                catch(Throwable v4) {
                    v5 = v1;
                }
            }
        }

        if(v5 != null) {
            v5.close();
        }

        throw v4;
    }

    private final long zza(String arg3, String[] arg4, long arg5) {
        Cursor v4;
        SQLiteDatabase v0 = this.getWritableDatabase();
        Cursor v1 = null;
        try {
            v4 = v0.rawQuery(arg3, arg4);
            goto label_3;
        }
        catch(Throwable v3) {
        }
        catch(SQLiteException v5) {
            goto label_22;
            try {
            label_3:
                if(!v4.moveToFirst()) {
                    goto label_10;
                }

                arg5 = v4.getLong(0);
                if(v4 == null) {
                    return arg5;
                }

                goto label_8;
            }
            catch(Throwable v3) {
                v1 = v4;
            }
            catch(SQLiteException v5) {
                v1 = v4;
                try {
                label_22:
                    ((zzco)this).zzgo().zzjd().zze("Database error", arg3, v5);
                    throw v5;
                }
                catch(Throwable v3) {
                }
            }
        }

        if(v1 != null) {
            v1.close();
        }

        throw v3;
    label_8:
        v4.close();
        return arg5;
    label_10:
        if(v4 != null) {
            v4.close();
        }

        return arg5;
    }

    @VisibleForTesting private final Object zza(Cursor arg3, int arg4) {
        int v0 = arg3.getType(arg4);
        Object v1 = null;
        switch(v0) {
            case 0: {
                goto label_22;
            }
            case 1: {
                goto label_19;
            }
            case 2: {
                goto label_16;
            }
            case 3: {
                goto label_14;
            }
            case 4: {
                goto label_9;
            }
        }

        ((zzco)this).zzgo().zzjd().zzg("Loaded invalid unknown value type, ignoring it", Integer.valueOf(v0));
        return v1;
    label_19:
        return Long.valueOf(arg3.getLong(arg4));
    label_22:
        ((zzco)this).zzgo().zzjd().zzbx("Loaded invalid null value from database");
        return v1;
    label_9:
        ((zzco)this).zzgo().zzjd().zzbx("Loaded invalid blob type value, ignoring it");
        return v1;
    label_14:
        return arg3.getString(arg4);
    label_16:
        return Double.valueOf(arg3.getDouble(arg4));
    }

    private static void zza(ContentValues arg1, String arg2, Object arg3) {
        Preconditions.checkNotEmpty(arg2);
        Preconditions.checkNotNull(arg3);
        if((arg3 instanceof String)) {
            arg1.put(arg2, ((String)arg3));
            return;
        }

        if((arg3 instanceof Long)) {
            arg1.put(arg2, ((Long)arg3));
            return;
        }

        if((arg3 instanceof Double)) {
            arg1.put(arg2, ((Double)arg3));
            return;
        }

        throw new IllegalArgumentException("Invalid value type");
    }

    private final boolean zza(String arg5, int arg6, zzfv arg7) {
        byte[] v0;
        ((zzez)this).zzcl();
        ((zzco)this).zzaf();
        Preconditions.checkNotEmpty(arg5);
        Preconditions.checkNotNull(arg7);
        if(TextUtils.isEmpty(arg7.zzavf)) {
            ((zzco)this).zzgo().zzjg().zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zzap.zzbv(arg5), Integer.valueOf(arg6), String.valueOf(arg7.zzave));
            return 0;
        }

        try {
            v0 = new byte[((zzzg)arg7).zzvu()];
            zzyy v2 = zzyy.zzk(v0, 0, v0.length);
            ((zzzg)arg7).zza(v2);
            v2.zzyt();
        }
        catch(IOException v6) {
            ((zzco)this).zzgo().zzjd().zze("Configuration loss. Failed to serialize event filter. appId", zzap.zzbv(arg5), v6);
            return 0;
        }

        ContentValues v2_1 = new ContentValues();
        v2_1.put("app_id", arg5);
        v2_1.put("audience_id", Integer.valueOf(arg6));
        v2_1.put("filter_id", arg7.zzave);
        v2_1.put("event_name", arg7.zzavf);
        v2_1.put("data", v0);
        try {
            if(this.getWritableDatabase().insertWithOnConflict("event_filters", null, v2_1, 5) == -1) {
                ((zzco)this).zzgo().zzjd().zzg("Failed to insert event filter (got -1). appId", zzap.zzbv(arg5));
            }
        }
        catch(SQLiteException v6_1) {
            ((zzco)this).zzgo().zzjd().zze("Error storing event filter. appId", zzap.zzbv(arg5), v6_1);
            return 0;
        }

        return 1;
    }

    private final boolean zza(String arg5, int arg6, zzfy arg7) {
        byte[] v0;
        ((zzez)this).zzcl();
        ((zzco)this).zzaf();
        Preconditions.checkNotEmpty(arg5);
        Preconditions.checkNotNull(arg7);
        if(TextUtils.isEmpty(arg7.zzavu)) {
            ((zzco)this).zzgo().zzjg().zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zzap.zzbv(arg5), Integer.valueOf(arg6), String.valueOf(arg7.zzave));
            return 0;
        }

        try {
            v0 = new byte[((zzzg)arg7).zzvu()];
            zzyy v2 = zzyy.zzk(v0, 0, v0.length);
            ((zzzg)arg7).zza(v2);
            v2.zzyt();
        }
        catch(IOException v6) {
            ((zzco)this).zzgo().zzjd().zze("Configuration loss. Failed to serialize property filter. appId", zzap.zzbv(arg5), v6);
            return 0;
        }

        ContentValues v2_1 = new ContentValues();
        v2_1.put("app_id", arg5);
        v2_1.put("audience_id", Integer.valueOf(arg6));
        v2_1.put("filter_id", arg7.zzave);
        v2_1.put("property_name", arg7.zzavu);
        v2_1.put("data", v0);
        try {
            if(this.getWritableDatabase().insertWithOnConflict("property_filters", null, v2_1, 5) != -1) {
                return 1;
            }

            ((zzco)this).zzgo().zzjd().zzg("Failed to insert property filter (got -1). appId", zzap.zzbv(arg5));
            return 0;
        }
        catch(SQLiteException v6_1) {
            ((zzco)this).zzgo().zzjd().zze("Error storing property filter. appId", zzap.zzbv(arg5), v6_1);
            return 0;
        }

        return 1;
    }

    private final boolean zza(String arg10, List arg11) {
        Preconditions.checkNotEmpty(arg10);
        ((zzez)this).zzcl();
        ((zzco)this).zzaf();
        SQLiteDatabase v0 = this.getWritableDatabase();
        try {
            long v4 = this.zza("select count(1) from audience_filter_values where app_id=?", new String[]{arg10});
        }
        catch(SQLiteException v11) {
            ((zzco)this).zzgo().zzjd().zze("Database error querying filters. appId", zzap.zzbv(arg10), v11);
            return 0;
        }

        int v2 = Math.max(0, Math.min(2000, ((zzco)this).zzgq().zzb(arg10, zzaf.zzaki)));
        if(v4 <= (((long)v2))) {
            return 0;
        }

        ArrayList v4_1 = new ArrayList();
        int v5 = 0;
        while(true) {
            if(v5 >= arg11.size()) {
                goto label_35;
            }

            Object v6 = arg11.get(v5);
            if(v6 != null) {
                if(!(v6 instanceof Integer)) {
                }
                else {
                    ((List)v4_1).add(Integer.toString(((Integer)v6).intValue()));
                    ++v5;
                    continue;
                }
            }

            return 0;
        }

        return 0;
    label_35:
        String v11_1 = TextUtils.join(",", ((Iterable)v4_1));
        StringBuilder v6_1 = new StringBuilder(String.valueOf(v11_1).length() + 2);
        v6_1.append("(");
        v6_1.append(v11_1);
        v6_1.append(")");
        v11_1 = v6_1.toString();
        StringBuilder v7 = new StringBuilder(String.valueOf(v11_1).length() + 140);
        v7.append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ");
        v7.append(v11_1);
        v7.append(" order by rowid desc limit -1 offset ?)");
        if(v0.delete("audience_filter_values", v7.toString(), new String[]{arg10, Integer.toString(v2)}) > 0) {
            return 1;
        }

        return 0;
    }

    public final long zza(zzgi arg8) {
        long v1_2;
        byte[] v0_1;
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        Preconditions.checkNotNull(arg8);
        Preconditions.checkNotEmpty(arg8.zztt);
        try {
            v0_1 = new byte[((zzzg)arg8).zzvu()];
            zzyy v1 = zzyy.zzk(v0_1, 0, v0_1.length);
            ((zzzg)arg8).zza(v1);
            v1.zzyt();
        }
        catch(IOException v0) {
            ((zzco)this).zzgo().zzjd().zze("Data loss. Failed to serialize event metadata. appId", zzap.zzbv(arg8.zztt), v0);
            throw v0;
        }

        zzfg v1_1 = ((zzey)this).zzjo();
        Preconditions.checkNotNull(v0_1);
        ((zzco)v1_1).zzgm().zzaf();
        MessageDigest v2 = zzfk.getMessageDigest();
        if(v2 == null) {
            ((zzco)v1_1).zzgo().zzjd().zzbx("Failed to get MD5");
            v1_2 = 0;
        }
        else {
            v1_2 = zzfk.zzc(v2.digest(v0_1));
        }

        ContentValues v3 = new ContentValues();
        v3.put("app_id", arg8.zztt);
        v3.put("metadata_fingerprint", Long.valueOf(v1_2));
        v3.put("metadata", v0_1);
        try {
            this.getWritableDatabase().insertWithOnConflict("raw_events_metadata", null, v3, 4);
            return v1_2;
        }
        catch(SQLiteException v0_2) {
            ((zzco)this).zzgo().zzjd().zze("Error storing raw event metadata. appId", zzap.zzbv(arg8.zztt), v0_2);
            throw v0_2;
        }
    }

    public final boolean zza(zzy arg9, long arg10, boolean arg12) {
        String v12;
        zzar v11;
        byte[] v1_1;
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        Preconditions.checkNotNull(arg9);
        Preconditions.checkNotEmpty(arg9.zztt);
        zzgf v0 = new zzgf();
        v0.zzawv = Long.valueOf(arg9.zzaic);
        v0.zzawt = new zzgg[arg9.zzaid.size()];
        Iterator v1 = arg9.zzaid.iterator();
        int v3;
        for(v3 = 0; v1.hasNext(); ++v3) {
            Object v4 = v1.next();
            zzgg v5 = new zzgg();
            v0.zzawt[v3] = v5;
            v5.name = ((String)v4);
            ((zzey)this).zzjo().zza(v5, arg9.zzaid.get(((String)v4)));
        }

        try {
            v1_1 = new byte[((zzzg)v0).zzvu()];
            zzyy v3_1 = zzyy.zzk(v1_1, 0, v1_1.length);
            ((zzzg)v0).zza(v3_1);
            v3_1.zzyt();
        }
        catch(IOException v10) {
            v11 = ((zzco)this).zzgo().zzjd();
            v12 = "Data loss. Failed to serialize event params/data. appId";
            goto label_87;
        }

        ((zzco)this).zzgo().zzjl().zze("Saving event, name, data size", ((zzco)this).zzgl().zzbs(arg9.name), Integer.valueOf(v1_1.length));
        ContentValues v0_1 = new ContentValues();
        v0_1.put("app_id", arg9.zztt);
        v0_1.put("name", arg9.name);
        v0_1.put("timestamp", Long.valueOf(arg9.timestamp));
        v0_1.put("metadata_fingerprint", Long.valueOf(arg10));
        v0_1.put("data", v1_1);
        v0_1.put("realtime", Integer.valueOf(((int)arg12)));
        try {
            if(this.getWritableDatabase().insert("raw_events", null, v0_1) != -1) {
                return 1;
            }

            ((zzco)this).zzgo().zzjd().zzg("Failed to insert raw event (got -1). appId", zzap.zzbv(arg9.zztt));
            return 0;
        }
        catch(SQLiteException v10_1) {
            v11 = ((zzco)this).zzgo().zzjd();
            v12 = "Error storing raw event. appId";
        }

    label_87:
        v11.zze(v12, zzap.zzbv(arg9.zztt), v10);
        return 0;
    }

    public final String zzah(long arg5) {
        String v6_2;
        Cursor v5;
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        String v0 = null;
        try {
            v5 = this.getWritableDatabase().rawQuery("select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;", new String[]{String.valueOf(arg5)});
        }
        catch(Throwable v6) {
            v5 = ((Cursor)v0);
            goto label_39;
        }
        catch(SQLiteException v6_1) {
            v5 = ((Cursor)v0);
            goto label_31;
        }

        try {
            if(v5.moveToFirst()) {
                goto label_20;
            }

            ((zzco)this).zzgo().zzjl().zzbx("No expired configs for apps with pending events");
            if(v5 != null) {
                goto label_18;
            }

            return v0;
        }
        catch(Throwable v6) {
            goto label_39;
        }
        catch(SQLiteException v6_1) {
            goto label_25;
        }

    label_18:
        v5.close();
        return v0;
        try {
        label_20:
            v6_2 = v5.getString(0);
            if(v5 == null) {
                return v6_2;
            }

            goto label_22;
        }
        catch(Throwable v6) {
        }
        catch(SQLiteException v6_1) {
        label_25:
            try {
            label_31:
                ((zzco)this).zzgo().zzjd().zzg("Error selecting expired configs", v6_1);
                if(v5 != null) {
                    goto label_36;
                }

                return v0;
            }
            catch(Throwable v6) {
                goto label_39;
            }

        label_36:
            v5.close();
            return v0;
        }

    label_39:
        if(v5 != null) {
            v5.close();
        }

        throw v6;
    label_22:
        v5.close();
        return v6_2;
    }

    public final List zzb(String arg22, String arg23, String arg24) {
        Object v10_1;
        long v8_1;
        Object v3_1;
        zzfj v4_1;
        String v16_1;
        String v6_1;
        Object v15;
        zzq v14_1;
        long v12_1;
        String v7;
        int v6;
        Cursor v2_1;
        int v9;
        int v8;
        int v10;
        StringBuilder v4;
        String v11;
        ArrayList v2;
        int v3;
        Preconditions.checkNotEmpty(arg22);
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        ArrayList v0 = new ArrayList();
        List v1 = null;
        try {
            v3 = 3;
            v2 = new ArrayList(v3);
            v11 = arg22;
        }
        catch(SQLiteException v0_1) {
            goto label_139;
        }

        try {
            ((List)v2).add(v11);
            v4 = new StringBuilder("app_id=?");
            if(TextUtils.isEmpty(((CharSequence)arg23))) {
                goto label_24;
            }

            goto label_16;
        }
        catch(SQLiteException v0_1) {
        }

    label_139:
        String v5 = arg23;
        goto label_140;
    label_16:
        v5 = arg23;
        try {
            ((List)v2).add(v5);
            v4.append(" and origin=?");
            goto label_25;
        label_24:
            v5 = arg23;
        label_25:
            if(!TextUtils.isEmpty(((CharSequence)arg24))) {
                ((List)v2).add(String.valueOf(arg24).concat("*"));
                v4.append(" and name glob ?");
            }

            Object[] v16 = ((List)v2).toArray(new String[((List)v2).size()]);
            SQLiteDatabase v12 = this.getWritableDatabase();
            String[] v14 = new String[4];
            v10 = 0;
            v14[0] = "name";
            v8 = 1;
            v14[1] = "set_timestamp";
            v9 = 2;
            v14[v9] = "value";
            v14[v3] = "origin";
            v2_1 = v12.query("user_attributes", v14, v4.toString(), ((String[])v16), null, null, "rowid", "1001");
            goto label_58;
        label_23:
        }
        catch(Throwable v0_2) {
            goto label_135;
        }
        catch(SQLiteException v0_1) {
            goto label_23;
        }

    label_140:
        v2_1 = ((Cursor)v1);
        goto label_141;
    label_135:
        goto label_151;
        try {
        label_58:
            if(!v2_1.moveToFirst()) {
                goto label_60;
            }

            goto label_63;
        }
        catch(Throwable v0_2) {
            goto label_150;
        }
        catch(SQLiteException v0_1) {
            goto label_129;
        }

    label_60:
        if(v2_1 != null) {
            v2_1.close();
        }

        return ((List)v0);
        try {
            while(true) {
            label_63:
                v6 = 1000;
                if(((List)v0).size() < v6) {
                    v7 = v2_1.getString(v10);
                    v12_1 = v2_1.getLong(v8);
                    v14_1 = this;
                    break;
                }
                else {
                    goto label_66;
                }
            }
        }
        catch(Throwable v0_2) {
            goto label_150;
        }
        catch(SQLiteException v0_1) {
            goto label_129;
        }

        try {
            v15 = v14_1.zza(v2_1, v9);
            v6_1 = v2_1.getString(v3);
            if(v15 != null) {
                goto label_93;
            }
        }
        catch(SQLiteException v0_1) {
            goto label_141;
        }

        try {
            ((zzco)this).zzgo().zzjd().zzd("(2)Read invalid user property value, ignoring it", zzap.zzbv(arg22), v6_1, arg24);
            v16_1 = v6_1;
            goto label_105;
        }
        catch(SQLiteException v0_1) {
            v5 = v6_1;
            goto label_141;
        }

        try {
        label_93:
            v4_1 = null;
            v3_1 = null;
            v5 = arg22;
            v16_1 = v6_1;
            v8_1 = v12_1;
            v10_1 = v15;
        }
        catch(SQLiteException v0_1) {
            v16_1 = v6_1;
            goto label_120;
        }

        try {
            super(v5, v6_1, v7, v8_1, v10_1);
            ((List)v0).add(v3_1);
        label_105:
            if(v2_1.moveToNext()) {
                goto label_110;
            }

            goto label_107;
        }
        catch(SQLiteException v0_1) {
        }

    label_120:
        v5 = v16_1;
        goto label_141;
    label_110:
        v5 = v16_1;
        v3 = 3;
        v8 = 1;
        v9 = 2;
        v10 = 0;
        goto label_63;
        try {
        label_66:
            ((zzco)this).zzgo().zzjd().zzg("Read more than the max allowed user properties, ignoring excess", Integer.valueOf(v6));
        }
        catch(Throwable v0_2) {
            goto label_150;
        }
        catch(SQLiteException v0_1) {
            goto label_129;
        }

    label_107:
        if(v2_1 != null) {
            v2_1.close();
        }

        return ((List)v0);
    label_129:
        try {
        label_141:
            ((zzco)this).zzgo().zzjd().zzd("(2)Error querying user properties", zzap.zzbv(arg22), v5, v0_1);
            if(v2_1 == null) {
                return v1;
            }
        }
        catch(Throwable v0_2) {
            goto label_150;
        }

        v2_1.close();
        return v1;
    label_150:
        Cursor v1_1 = v2_1;
    label_151:
        if((((Cursor)v1)) != null) {
            ((Cursor)v1).close();
        }

        throw v0_2;
    }

    public final List zzb(String arg40, String[] arg41) {
        List v0_3;
        int v4_1;
        Cursor v1_1;
        Cursor v2_1;
        int v1;
        int v15;
        int v14;
        int v13;
        int v11;
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        ArrayList v0 = new ArrayList();
        try {
            SQLiteDatabase v2 = this.getWritableDatabase();
            String[] v4 = new String[13];
            v11 = 0;
            v4[0] = "app_id";
            v4[1] = "origin";
            v13 = 2;
            v4[v13] = "name";
            v14 = 3;
            v4[v14] = "value";
            v15 = 4;
            v4[v15] = "active";
            v4[5] = "trigger_event_name";
            v4[6] = "trigger_timeout";
            v4[7] = "timed_out_event";
            v4[8] = "creation_timestamp";
            v4[9] = "triggered_event";
            v4[10] = "triggered_timestamp";
            v4[11] = "time_to_live";
            v4[12] = "expired_event";
            v1 = 5;
            v2_1 = v2.query("conditional_properties", v4, arg40, arg41, null, null, "rowid", "1001");
        }
        catch(Throwable v0_1) {
            v2_1 = null;
            goto label_164;
        }
        catch(SQLiteException v0_2) {
            v1_1 = null;
            goto label_154;
        }

        try {
            if(!v2_1.moveToFirst()) {
                goto label_65;
            }

            goto label_68;
        }
        catch(Throwable v0_1) {
            goto label_164;
        }
        catch(SQLiteException v0_2) {
            goto label_147;
        }

    label_65:
        if(v2_1 != null) {
            v2_1.close();
        }

        return ((List)v0);
        try {
            while(true) {
            label_68:
                v4_1 = 1000;
                if(((List)v0).size() < v4_1) {
                    String v3 = v2_1.getString(v11);
                    String v10 = v2_1.getString(1);
                    String v5 = v2_1.getString(v13);
                    Object v8 = this.zza(v2_1, v14);
                    boolean v22 = v2_1.getInt(v15) != 0 ? true : false;
                    ((List)v0).add(new zzl(v3, v10, new zzfh(v5, v2_1.getLong(10), v8, v10), v2_1.getLong(8), v22, v2_1.getString(v1), ((zzey)this).zzjo().zza(v2_1.getBlob(7), zzad.CREATOR), v2_1.getLong(6), ((zzey)this).zzjo().zza(v2_1.getBlob(9), zzad.CREATOR), v2_1.getLong(11), ((zzey)this).zzjo().zza(v2_1.getBlob(12), zzad.CREATOR)));
                    if(v2_1.moveToNext()) {
                        break;
                    }
                }
                else {
                    goto label_71;
                }

                goto label_138;
            }
        }
        catch(Throwable v0_1) {
            goto label_164;
        }
        catch(SQLiteException v0_2) {
            goto label_147;
        }

        v1 = 5;
        v11 = 0;
        goto label_68;
        try {
        label_71:
            ((zzco)this).zzgo().zzjd().zzg("Read more than the max allowed conditional properties, ignoring extra", Integer.valueOf(v4_1));
        }
        catch(Throwable v0_1) {
            goto label_164;
        }
        catch(SQLiteException v0_2) {
            goto label_147;
        }

    label_138:
        if(v2_1 != null) {
            v2_1.close();
        }

        return ((List)v0);
    label_147:
        v1_1 = v2_1;
        try {
        label_154:
            ((zzco)this).zzgo().zzjd().zzg("Error querying conditional user property value", v0_2);
            v0_3 = Collections.emptyList();
            if(v1_1 == null) {
                return v0_3;
            }
        }
        catch(Throwable v0_1) {
            v2_1 = v1_1;
            goto label_164;
        }

        v1_1.close();
        return v0_3;
    label_164:
        if(v2_1 != null) {
            v2_1.close();
        }

        throw v0_1;
    }

    public final List zzb(String arg16, int arg17, int arg18) {
        byte[] v0_4;
        ArrayList v4_2;
        List v0_2;
        Cursor v5_1;
        int v14;
        int v1 = arg18;
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        boolean v4 = arg17 > 0 ? true : false;
        Preconditions.checkArgument(v4);
        v4 = v1 > 0 ? true : false;
        Preconditions.checkArgument(v4);
        Preconditions.checkNotEmpty(arg16);
        Cursor v4_1 = null;
        try {
            SQLiteDatabase v5 = this.getWritableDatabase();
            String[] v7 = new String[3];
            v7[0] = "rowid";
            v7[1] = "data";
            v14 = 2;
            v7[v14] = "retry_count";
            v5_1 = v5.query("queue", v7, "app_id=?", new String[]{arg16}, null, null, "rowid", String.valueOf(arg17));
            goto label_36;
        }
        catch(Throwable v0) {
        }
        catch(SQLiteException v0_1) {
            goto label_99;
            try {
            label_36:
                if(v5_1.moveToFirst()) {
                    goto label_42;
                }

                v0_2 = Collections.emptyList();
                if(v5_1 != null) {
                    goto label_40;
                }

                return v0_2;
            }
            catch(Throwable v0) {
                goto label_108;
            }
            catch(SQLiteException v0_1) {
                goto label_93;
            }

        label_40:
            v5_1.close();
            return v0_2;
            try {
            label_42:
                v4_2 = new ArrayList();
                int v6 = 0;
                do {
                label_45:
                    long v7_1 = v5_1.getLong(0);
                    try {
                        v0_4 = ((zzey)this).zzjo().zza(v5_1.getBlob(1));
                    }
                    catch(IOException v0_3) {
                        ((zzco)this).zzgo().zzjd().zze("Failed to unzip queued bundle. appId", zzap.zzbv(arg16), v0_3);
                        goto label_84;
                    }

                    if(!((List)v4_2).isEmpty() && v0_4.length + v6 > v1) {
                        goto label_87;
                    }

                    zzyx v9 = zzyx.zzj(v0_4, 0, v0_4.length);
                    zzgi v10 = new zzgi();
                    try {
                        ((zzzg)v10).zza(v9);
                    }
                    catch(IOException v0_3) {
                        ((zzco)this).zzgo().zzjd().zze("Failed to merge queued bundle. appId", zzap.zzbv(arg16), v0_3);
                        goto label_84;
                    }

                    if(!v5_1.isNull(v14)) {
                        v10.zzayc = Integer.valueOf(v5_1.getInt(v14));
                    }

                    v6 += v0_4.length;
                    ((List)v4_2).add(Pair.create(v10, Long.valueOf(v7_1)));
                label_84:
                    if(!v5_1.moveToNext()) {
                        goto label_87;
                    }

                    break;
                }
                while(true);
            }
            catch(Throwable v0) {
                goto label_108;
            }
            catch(SQLiteException v0_1) {
                goto label_93;
            }

            if(v6 <= v1) {
                goto label_45;
            }

        label_87:
            if(v5_1 != null) {
                v5_1.close();
            }

            return ((List)v4_2);
        label_93:
            v4_1 = v5_1;
            try {
            label_99:
                ((zzco)this).zzgo().zzjd().zze("Error querying bundles. appId", zzap.zzbv(arg16), v0_1);
                v0_2 = Collections.emptyList();
                if(v4_1 == null) {
                    return v0_2;
                }
            }
            catch(Throwable v0) {
                v5_1 = v4_1;
                goto label_108;
            }
        }

        v4_1.close();
        return v0_2;
    label_108:
        if(v5_1 != null) {
            v5_1.close();
        }

        throw v0;
    }

    public final List zzbk(String arg23) {
        zzq v3_1;
        long v19;
        String v17;
        String v18;
        Cursor v2_1;
        int v14;
        int v13;
        Preconditions.checkNotEmpty(arg23);
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        ArrayList v0 = new ArrayList();
        List v1 = null;
        try {
            SQLiteDatabase v2 = this.getWritableDatabase();
            String[] v4 = new String[4];
            v4[0] = "name";
            v4[1] = "origin";
            v13 = 2;
            v4[v13] = "set_timestamp";
            v14 = 3;
            v4[v14] = "value";
            v2_1 = v2.query("user_attributes", v4, "app_id=?", new String[]{arg23}, null, null, "rowid", "1000");
        }
        catch(Throwable v0_1) {
            v2_1 = ((Cursor)v1);
            goto label_85;
        }
        catch(SQLiteException v0_2) {
            v2_1 = ((Cursor)v1);
            goto label_76;
        }

        try {
            if(!v2_1.moveToFirst()) {
                goto label_32;
            }

            goto label_35;
        }
        catch(SQLiteException v0_2) {
            goto label_68;
        }
        catch(Throwable v0_1) {
            goto label_85;
        }

    label_32:
        if(v2_1 != null) {
            v2_1.close();
        }

        return ((List)v0);
        try {
            do {
            label_35:
                v18 = v2_1.getString(0);
                String v3 = v2_1.getString(1);
                if(v3 == null) {
                    v3 = "";
                }

                v17 = v3;
                v19 = v2_1.getLong(v13);
                v3_1 = this;
                break;
            }
            while(true);
        }
        catch(SQLiteException v0_2) {
            goto label_68;
        }
        catch(Throwable v0_1) {
            goto label_85;
        }

        try {
            Object v21 = v3_1.zza(v2_1, v14);
            if(v21 == null) {
                ((zzco)this).zzgo().zzjd().zzg("Read invalid user property value, ignoring it. appId", zzap.zzbv(arg23));
            }
            else {
                ((List)v0).add(new zzfj(arg23, v17, v18, v19, v21));
            }

            if(v2_1.moveToNext()) {
                goto label_35;
            }

            goto label_58;
        }
        catch(Throwable v0_1) {
        }
        catch(SQLiteException v0_2) {
            goto label_76;
        label_58:
            if(v2_1 != null) {
                v2_1.close();
            }

            return ((List)v0);
        label_68:
            try {
            label_76:
                ((zzco)this).zzgo().zzjd().zze("Error querying user properties. appId", zzap.zzbv(arg23), v0_2);
                if(v2_1 == null) {
                    return v1;
                }
            }
            catch(Throwable v0_1) {
                goto label_85;
            }
        }

        v2_1.close();
        return v1;
    label_85:
        if(v2_1 != null) {
            v2_1.close();
        }

        throw v0_1;
    }

    public final zzg zzbl(String arg20) {
        zzg v4;
        Cursor v3_1;
        int v15;
        int v0_2;
        int v14;
        int v13;
        boolean v11;
        String v1 = arg20;
        Preconditions.checkNotEmpty(arg20);
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        zzg v2 = null;
        try {
            SQLiteDatabase v3 = this.getWritableDatabase();
            String[] v5 = new String[26];
            v11 = false;
            v5[0] = "app_instance_id";
            v5[1] = "gmp_app_id";
            v13 = 2;
            v5[v13] = "resettable_device_id_hash";
            v14 = 3;
            v5[v14] = "last_bundle_index";
            v5[4] = "last_bundle_start_timestamp";
            v5[5] = "last_bundle_end_timestamp";
            v5[6] = "app_version";
            v5[7] = "app_store";
            v5[8] = "gmp_version";
            v5[9] = "dev_cert_hash";
            v5[10] = "measurement_enabled";
            v5[11] = "day";
            v5[12] = "daily_public_events_count";
            v5[13] = "daily_events_count";
            v5[14] = "daily_conversions_count";
            v5[15] = "config_fetched_time";
            v5[16] = "failed_config_fetch_time";
            v5[17] = "app_version_int";
            v5[18] = "firebase_instance_id";
            v5[19] = "daily_error_events_count";
            v5[20] = "daily_realtime_events_count";
            v5[21] = "health_monitor_sample";
            v5[22] = "android_id";
            v5[23] = "adid_reporting_enabled";
            v5[24] = "ssaid_reporting_enabled";
            v5[25] = "admob_app_id";
            v0_2 = 6;
            v15 = 5;
            v3_1 = v3.query("apps", v5, "app_id=?", new String[]{v1}, null, null, null);
        }
        catch(Throwable v0) {
            v3_1 = ((Cursor)v2);
            goto label_247;
        }
        catch(SQLiteException v0_1) {
            v3_1 = ((Cursor)v2);
            goto label_238;
        }

        try {
            if(v3_1.moveToFirst()) {
                goto label_108;
            }
        }
        catch(Throwable v0) {
            goto label_227;
        }
        catch(SQLiteException v0_1) {
            goto label_230;
        }

        if(v3_1 != null) {
            v3_1.close();
        }

        return v2;
    label_108:
        zzq v5_1 = this;
        goto label_110;
    label_230:
        goto label_238;
    label_227:
        goto label_247;
        try {
        label_110:
            v4 = new zzg(v5_1.zzamz.zzmb(), v1);
            v4.zzam(v3_1.getString(0));
            v4.zzan(v3_1.getString(1));
            v4.zzap(v3_1.getString(v13));
            v4.zzx(v3_1.getLong(v14));
            v4.zzs(v3_1.getLong(4));
            v4.zzt(v3_1.getLong(v15));
            v4.setAppVersion(v3_1.getString(v0_2));
            v4.zzar(v3_1.getString(7));
            v4.zzv(v3_1.getLong(8));
            v4.zzw(v3_1.getLong(9));
            v0_2 = 10;
            boolean v0_3 = (v3_1.isNull(v0_2)) || v3_1.getInt(v0_2) != 0 ? true : false;
            v4.setMeasurementEnabled(v0_3);
            v4.zzaa(v3_1.getLong(11));
            v4.zzab(v3_1.getLong(12));
            v4.zzac(v3_1.getLong(13));
            v4.zzad(v3_1.getLong(14));
            v4.zzy(v3_1.getLong(15));
            v4.zzz(v3_1.getLong(16));
            v0_2 = 17;
            long v6 = v3_1.isNull(v0_2) ? -2147483648 : ((long)v3_1.getInt(v0_2));
            v4.zzu(v6);
            v4.zzaq(v3_1.getString(18));
            v4.zzaf(v3_1.getLong(19));
            v4.zzae(v3_1.getLong(20));
            v4.zzas(v3_1.getString(21));
            v0_2 = 22;
            v6 = v3_1.isNull(v0_2) ? 0 : v3_1.getLong(v0_2);
            v4.zzag(v6);
            v0_2 = 23;
            v0_3 = (v3_1.isNull(v0_2)) || v3_1.getInt(v0_2) != 0 ? true : false;
            v4.zze(v0_3);
            v0_2 = 24;
            if((v3_1.isNull(v0_2)) || v3_1.getInt(v0_2) != 0) {
                v11 = true;
            }

            v4.zzf(v11);
            v4.zzao(v3_1.getString(25));
            v4.zzgv();
            if(v3_1.moveToNext()) {
                ((zzco)this).zzgo().zzjd().zzg("Got multiple records for app, expected one. appId", zzap.zzbv(arg20));
            }

            goto label_220;
        }
        catch(Throwable v0) {
        }
        catch(SQLiteException v0_1) {
            try {
            label_238:
                ((zzco)this).zzgo().zzjd().zze("Error querying app. appId", zzap.zzbv(arg20), v0_1);
                if(v3_1 != null) {
                    goto label_244;
                }

                return v2;
            }
            catch(Throwable v0) {
                goto label_247;
            }

        label_244:
            v3_1.close();
            return v2;
        }

    label_247:
        if(v3_1 != null) {
            v3_1.close();
        }

        throw v0;
    label_220:
        if(v3_1 != null) {
            v3_1.close();
        }

        return v4;
    }

    public final long zzbm(String arg7) {
        Preconditions.checkNotEmpty(arg7);
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        try {
            return ((long)this.getWritableDatabase().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{arg7, String.valueOf(Math.max(0, Math.min(1000000, ((zzco)this).zzgq().zzb(arg7, zzaf.zzajs))))}));
        }
        catch(SQLiteException v0) {
            ((zzco)this).zzgo().zzjd().zze("Error deleting over the limit events. appId", zzap.zzbv(arg7), v0);
            return 0;
        }
    }

    public final byte[] zzbn(String arg12) {
        byte[] v2_1;
        Cursor v1;
        Preconditions.checkNotEmpty(arg12);
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        byte[] v0 = null;
        try {
            v1 = this.getWritableDatabase().query("apps", new String[]{"remote_config"}, "app_id=?", new String[]{arg12}, null, null, null);
        }
        catch(Throwable v12) {
            v1 = ((Cursor)v0);
            goto label_56;
        }
        catch(SQLiteException v2) {
            v1 = ((Cursor)v0);
            goto label_47;
        }

        try {
            if(v1.moveToFirst()) {
                goto label_29;
            }
        }
        catch(Throwable v12) {
            goto label_56;
        }
        catch(SQLiteException v2) {
            goto label_41;
        }

        if(v1 != null) {
            v1.close();
        }

        return v0;
        try {
        label_29:
            v2_1 = v1.getBlob(0);
            if(v1.moveToNext()) {
                ((zzco)this).zzgo().zzjd().zzg("Got multiple records for app config, expected one. appId", zzap.zzbv(arg12));
            }

            goto label_37;
        }
        catch(Throwable v12) {
        }
        catch(SQLiteException v2) {
        label_41:
            try {
            label_47:
                ((zzco)this).zzgo().zzjd().zze("Error querying remote config. appId", zzap.zzbv(arg12), v2);
                if(v1 != null) {
                    goto label_53;
                }

                return v0;
            }
            catch(Throwable v12) {
                goto label_56;
            }

        label_53:
            v1.close();
            return v0;
        }

    label_56:
        if(v1 != null) {
            v1.close();
        }

        throw v12;
    label_37:
        if(v1 != null) {
            v1.close();
        }

        return v2_1;
    }

    final Map zzbo(String arg12) {
        a v1_1;
        Cursor v0_1;
        ((zzez)this).zzcl();
        ((zzco)this).zzaf();
        Preconditions.checkNotEmpty(arg12);
        SQLiteDatabase v0 = this.getWritableDatabase();
        Map v8 = null;
        try {
            v0_1 = v0.query("audience_filter_values", new String[]{"audience_id", "current_results"}, "app_id=?", new String[]{arg12}, null, null, null);
        }
        catch(Throwable v12) {
            v0_1 = ((Cursor)v8);
            goto label_66;
        }
        catch(SQLiteException v1) {
            v0_1 = ((Cursor)v8);
            goto label_57;
        }

        try {
            if(v0_1.moveToFirst()) {
                goto label_26;
            }
        }
        catch(Throwable v12) {
            goto label_66;
        }
        catch(SQLiteException v1) {
            goto label_51;
        }

        if(v0_1 != null) {
            v0_1.close();
        }

        return v8;
        try {
        label_26:
            v1_1 = new a();
            do {
                int v2 = v0_1.getInt(0);
                byte[] v3 = v0_1.getBlob(1);
                zzyx v3_1 = zzyx.zzj(v3, 0, v3.length);
                zzgj v4 = new zzgj();
                try {
                    ((zzzg)v4).zza(v3_1);
                }
                catch(IOException v3_2) {
                    ((zzco)this).zzgo().zzjd().zzd("Failed to merge filter results. appId, audienceId, error", zzap.zzbv(arg12), Integer.valueOf(v2), v3_2);
                    goto label_45;
                }

                ((Map)v1_1).put(Integer.valueOf(v2), v4);
            label_45:
                if(v0_1.moveToNext()) {
                    continue;
                }

                break;
            }
            while(true);
        }
        catch(Throwable v12) {
            goto label_66;
        }
        catch(SQLiteException v1) {
            goto label_51;
        }

        if(v0_1 != null) {
            v0_1.close();
        }

        return ((Map)v1_1);
    label_51:
        try {
        label_57:
            ((zzco)this).zzgo().zzjd().zze("Database error querying filter results. appId", zzap.zzbv(arg12), v1);
            if(v0_1 != null) {
                goto label_63;
            }

            return v8;
        }
        catch(Throwable v12) {
            goto label_66;
        }

    label_63:
        v0_1.close();
        return v8;
    label_66:
        if(v0_1 != null) {
            v0_1.close();
        }

        throw v12;
    }

    public final long zzbp(String arg5) {
        Preconditions.checkNotEmpty(arg5);
        return this.zza("select count(1) from events where app_id=? and name not like \'!_%\' escape \'!\'", new String[]{arg5}, 0);
    }

    public final List zzc(String arg3, String arg4, String arg5) {
        Preconditions.checkNotEmpty(arg3);
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        ArrayList v0 = new ArrayList(3);
        ((List)v0).add(arg3);
        StringBuilder v3 = new StringBuilder("app_id=?");
        if(!TextUtils.isEmpty(((CharSequence)arg4))) {
            ((List)v0).add(arg4);
            v3.append(" and origin=?");
        }

        if(!TextUtils.isEmpty(((CharSequence)arg5))) {
            ((List)v0).add(String.valueOf(arg5).concat("*"));
            v3.append(" and name glob ?");
        }

        return this.zzb(v3.toString(), ((List)v0).toArray(new String[((List)v0).size()]));
    }

    @VisibleForTesting final void zzc(List arg6) {
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        Preconditions.checkNotNull(arg6);
        Preconditions.checkNotZero(arg6.size());
        if(!this.zzil()) {
            return;
        }

        String v6 = TextUtils.join(",", ((Iterable)arg6));
        StringBuilder v1 = new StringBuilder(String.valueOf(v6).length() + 2);
        v1.append("(");
        v1.append(v6);
        v1.append(")");
        v6 = v1.toString();
        v1 = new StringBuilder(String.valueOf(v6).length() + 80);
        v1.append("SELECT COUNT(1) FROM queue WHERE rowid IN ");
        v1.append(v6);
        v1.append(" AND retry_count =  2147483647 LIMIT 1");
        if(this.zza(v1.toString(), null) > 0) {
            ((zzco)this).zzgo().zzjg().zzbx("The number of upload retries exceeds the limit. Will remain unchanged.");
        }

        try {
            SQLiteDatabase v0 = this.getWritableDatabase();
            StringBuilder v2 = new StringBuilder(String.valueOf(v6).length() + 127);
            v2.append("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN ");
            v2.append(v6);
            v2.append(" AND (retry_count IS NULL OR retry_count < 2147483647)");
            v0.execSQL(v2.toString());
            return;
        }
        catch(SQLiteException v6_1) {
            ((zzco)this).zzgo().zzjd().zzg("Error incrementing retry count. error", v6_1);
            return;
        }
    }

    public final zzz zzg(String arg23, String arg24) {
        Boolean v15_1;
        Long v12_1;
        long v10;
        long v8_1;
        String v3_1;
        String v2;
        zzz v1_3;
        zzz v18;
        Boolean v17;
        Long v14_1;
        Long v13_1;
        long v6;
        long v4;
        Cursor v21;
        Cursor v8;
        int v0_2;
        int v14;
        int v13;
        int v12;
        int v11;
        boolean v9;
        String v15 = arg24;
        Preconditions.checkNotEmpty(arg23);
        Preconditions.checkNotEmpty(arg24);
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        zzz v16 = null;
        try {
            SQLiteDatabase v1 = this.getWritableDatabase();
            String[] v3 = new String[8];
            v9 = false;
            v3[0] = "lifetime_count";
            v3[1] = "current_bundle_count";
            v11 = 2;
            v3[v11] = "last_fire_timestamp";
            v12 = 3;
            v3[v12] = "last_bundled_timestamp";
            v13 = 4;
            v3[v13] = "last_bundled_day";
            v14 = 5;
            v3[v14] = "last_sampled_complex_event_id";
            v3[6] = "last_sampling_rate";
            v3[7] = "last_exempt_from_sampling";
            String[] v5 = new String[v11];
            v5[0] = arg23;
            v5[1] = v15;
            v0_2 = 6;
            v8 = v1.query("events", v3, "app_id=? and name=?", v5, null, null, null);
        }
        catch(Throwable v0) {
            v21 = ((Cursor)v16);
            goto label_139;
        }
        catch(SQLiteException v0_1) {
            v21 = ((Cursor)v16);
            goto label_127;
        }

        try {
            if(v8.moveToFirst()) {
                goto label_50;
            }
        }
        catch(Throwable v0) {
            goto label_117;
        }
        catch(SQLiteException v0_1) {
            goto label_120;
        }

        if(v8 != null) {
            v8.close();
        }

        return v16;
        try {
        label_50:
            v4 = v8.getLong(0);
            v6 = v8.getLong(1);
            long v19 = v8.getLong(v11);
            long v1_1 = v8.isNull(v12) ? 0 : v8.getLong(v12);
            long v11_1 = v1_1;
            v13_1 = v8.isNull(v13) ? ((Long)v16) : Long.valueOf(v8.getLong(v13));
            v14_1 = v8.isNull(v14) ? ((Long)v16) : Long.valueOf(v8.getLong(v14));
            Long v0_3 = v8.isNull(v0_2) ? ((Long)v16) : Long.valueOf(v8.getLong(v0_2));
            int v1_2 = 7;
            if(!v8.isNull(v1_2)) {
                if(v8.getLong(v1_2) == 1) {
                    v9 = true;
                }

                v17 = Boolean.valueOf(v9);
            }
            else {
                v17 = ((Boolean)v16);
            }

            v18 = null;
            v1_3 = v18;
            v2 = arg23;
            v3_1 = arg24;
            v21 = v8;
            v8_1 = v19;
            v10 = v11_1;
            v12_1 = v13_1;
            v13_1 = v14_1;
            v14_1 = v0_3;
            v15_1 = v17;
        }
        catch(Throwable v0) {
        label_117:
            v21 = v8;
            goto label_139;
        }
        catch(SQLiteException v0_1) {
        label_120:
            v21 = v8;
            goto label_127;
        }

        try {
            super(v2, v3_1, v4, v6, v8_1, v10, v12_1, v13_1, v14_1, v15_1);
            if(v21.moveToNext()) {
                ((zzco)this).zzgo().zzjd().zzg("Got multiple records for event aggregates, expected one. appId", zzap.zzbv(arg23));
            }

            goto label_111;
        }
        catch(Throwable v0) {
        }
        catch(SQLiteException v0_1) {
            try {
            label_127:
                ((zzco)this).zzgo().zzjd().zzd("Error querying events. appId", zzap.zzbv(arg23), ((zzco)this).zzgl().zzbs(arg24), v0_1);
                if(v21 != null) {
                    goto label_136;
                }

                return v16;
            }
            catch(Throwable v0) {
                goto label_139;
            }

        label_136:
            v21.close();
            return v16;
        }

    label_139:
        if(v21 != null) {
            v21.close();
        }

        throw v0;
    label_111:
        if(v21 != null) {
            v21.close();
        }

        return v18;
    }

    protected final boolean zzgt() {
        return 0;
    }

    public final void zzh(String arg6, String arg7) {
        Preconditions.checkNotEmpty(arg6);
        Preconditions.checkNotEmpty(arg7);
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        try {
            ((zzco)this).zzgo().zzjl().zzg("Deleted user attribute rows", Integer.valueOf(this.getWritableDatabase().delete("user_attributes", "app_id=? and name=?", new String[]{arg6, arg7})));
            return;
        }
        catch(SQLiteException v0) {
            ((zzco)this).zzgo().zzjd().zzd("Error deleting user attribute. appId", zzap.zzbv(arg6), ((zzco)this).zzgl().zzbu(arg7), v0);
            return;
        }
    }

    public final zzfj zzi(String arg19, String arg20) {
        zzfj v0_2;
        zzq v11;
        long v5;
        Cursor v10_1;
        int v3;
        String v8 = arg20;
        Preconditions.checkNotEmpty(arg19);
        Preconditions.checkNotEmpty(arg20);
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        zzfj v9 = null;
        try {
            SQLiteDatabase v10 = this.getWritableDatabase();
            String[] v12 = new String[3];
            v12[0] = "set_timestamp";
            v12[1] = "value";
            v3 = 2;
            v12[v3] = "origin";
            String[] v14 = new String[v3];
            v14[0] = arg19;
            v14[1] = v8;
            v10_1 = v10.query("user_attributes", v12, "app_id=? and name=?", v14, null, null, null);
        }
        catch(Throwable v0) {
            v10_1 = ((Cursor)v9);
            goto label_78;
        }
        catch(SQLiteException v0_1) {
            v10_1 = ((Cursor)v9);
            goto label_67;
        }

        try {
            if(v10_1.moveToFirst()) {
                goto label_32;
            }
        }
        catch(Throwable v0) {
            goto label_56;
        }
        catch(SQLiteException v0_1) {
            goto label_59;
        }

        if(v10_1 != null) {
            v10_1.close();
        }

        return v9;
        try {
        label_32:
            v5 = v10_1.getLong(0);
            v11 = this;
        }
        catch(Throwable v0) {
        label_56:
            goto label_78;
        }
        catch(SQLiteException v0_1) {
        label_59:
            goto label_67;
        }

        try {
            v0_2 = new zzfj(arg19, v10_1.getString(v3), arg20, v5, v11.zza(v10_1, 1));
            if(v10_1.moveToNext()) {
                ((zzco)this).zzgo().zzjd().zzg("Got multiple records for user property, expected one. appId", zzap.zzbv(arg19));
            }

            goto label_49;
        }
        catch(Throwable v0) {
        }
        catch(SQLiteException v0_1) {
            try {
            label_67:
                ((zzco)this).zzgo().zzjd().zzd("Error querying user property. appId", zzap.zzbv(arg19), ((zzco)this).zzgl().zzbu(v8), v0_1);
                if(v10_1 != null) {
                    goto label_75;
                }

                return v9;
            }
            catch(Throwable v0) {
                goto label_78;
            }

        label_75:
            v10_1.close();
            return v9;
        }

    label_78:
        if(v10_1 != null) {
            v10_1.close();
        }

        throw v0;
    label_49:
        if(v10_1 != null) {
            v10_1.close();
        }

        return v0_2;
    }

    public final String zzid() {
        String v2_1;
        Throwable v1_1;
        Cursor v0_2;
        SQLiteDatabase v0 = this.getWritableDatabase();
        String[] v1 = null;
        try {
            v0_2 = v0.rawQuery("select app_id from queue order by has_realtime desc, rowid asc limit 1;", v1);
        }
        catch(Throwable v0_1) {
            String[] v5 = v1;
            v1_1 = v0_1;
            v0_2 = ((Cursor)v5);
            goto label_31;
        }
        catch(SQLiteException v2) {
            v0_2 = ((Cursor)v1);
            goto label_23;
        }

        try {
            if(!v0_2.moveToFirst()) {
                goto label_11;
            }

            v2_1 = v0_2.getString(0);
            if(v0_2 == null) {
                return v2_1;
            }

            goto label_9;
        }
        catch(Throwable v1_1) {
        }
        catch(SQLiteException v2) {
            try {
            label_23:
                ((zzco)this).zzgo().zzjd().zzg("Database error getting next bundle app id", v2);
                if(v0_2 != null) {
                    goto label_28;
                }

                goto label_29;
            }
            catch(Throwable v1_1) {
                goto label_31;
            }

        label_28:
            v0_2.close();
        label_29:
            return ((String)v1);
        }

    label_31:
        if(v0_2 != null) {
            v0_2.close();
        }

        throw v1_1;
    label_9:
        v0_2.close();
        return v2_1;
    label_11:
        if(v0_2 != null) {
            v0_2.close();
        }

        return ((String)v1);
    }

    public final boolean zzie() {
        if(this.zza("select count(1) > 0 from queue where has_realtime = 1", null) != 0) {
            return 1;
        }

        return 0;
    }

    final void zzif() {
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        if(!this.zzil()) {
            return;
        }

        long v0 = ((zzco)this).zzgp().zzanh.get();
        long v2 = ((zzco)this).zzbx().elapsedRealtime();
        if(Math.abs(v2 - v0) > zzaf.zzakb.get().longValue()) {
            ((zzco)this).zzgp().zzanh.set(v2);
            ((zzco)this).zzaf();
            ((zzez)this).zzcl();
            if(this.zzil()) {
                int v0_1 = this.getWritableDatabase().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(((zzco)this).zzbx().currentTimeMillis()), String.valueOf(zzn.zzhw())});
                if(v0_1 > 0) {
                    ((zzco)this).zzgo().zzjl().zzg("Deleted stale rows. rowsDeleted", Integer.valueOf(v0_1));
                }
            }
        }
    }

    public final long zzig() {
        return this.zza("select max(bundle_end_timestamp) from queue", null, 0);
    }

    public final long zzih() {
        return this.zza("select max(timestamp) from raw_events", null, 0);
    }

    public final boolean zzii() {
        if(this.zza("select count(1) > 0 from raw_events", null) != 0) {
            return 1;
        }

        return 0;
    }

    public final boolean zzij() {
        if(this.zza("select count(1) > 0 from raw_events where realtime = 1", null) != 0) {
            return 1;
        }

        return 0;
    }

    public final long zzik() {
        Cursor v2_2;
        long v4;
        Cursor v3_1;
        long v0 = -1;
        String[] v2 = null;
        try {
            v3_1 = this.getWritableDatabase().rawQuery("select rowid from raw_events order by rowid desc limit 1;", v2);
            goto label_5;
        }
        catch(Throwable v0_1) {
        }
        catch(SQLiteException v3) {
            goto label_26;
            try {
            label_5:
                if(v3_1.moveToFirst()) {
                    goto label_11;
                }
            }
            catch(SQLiteException v2_1) {
                goto label_19;
            }
            catch(Throwable v0_1) {
                goto label_16;
            }

            if(v3_1 != null) {
                v3_1.close();
            }

            return v0;
            try {
            label_11:
                v4 = v3_1.getLong(0);
                if(v3_1 == null) {
                    return v4;
                }

                goto label_13;
            }
            catch(Throwable v0_1) {
            label_16:
                v2_2 = v3_1;
            }
            catch(SQLiteException v2_1) {
            label_19:
                Cursor v6 = v3_1;
                v3 = v2_1;
                v2_2 = v6;
                try {
                label_26:
                    ((zzco)this).zzgo().zzjd().zzg("Error querying raw events", v3);
                    if((((Cursor)v2)) == null) {
                        return v0;
                    }
                }
                catch(Throwable v0_1) {
                    goto label_33;
                }

                ((Cursor)v2).close();
                return v0;
            }
        }

    label_33:
        if((((Cursor)v2)) != null) {
            ((Cursor)v2).close();
        }

        throw v0_1;
    label_13:
        v3_1.close();
        return v4;
    }

    private final boolean zzil() {
        return ((zzco)this).getContext().getDatabasePath("google_app_measurement.db").exists();
    }

    static String[] zzim() {
        return zzq.zzahi;
    }

    static String[] zzin() {
        return zzq.zzahj;
    }

    static String[] zzio() {
        return zzq.zzahk;
    }

    static String[] zzip() {
        return zzq.zzahm;
    }

    static String[] zziq() {
        return zzq.zzahl;
    }

    static String[] zzir() {
        return zzq.zzahn;
    }

    public final zzl zzj(String arg33, String arg34) {
        zzl v0_3;
        zzq v10;
        String v19;
        Cursor v9_1;
        int v0_2;
        int v6;
        int v5;
        int v4;
        int v3;
        String v7 = arg34;
        Preconditions.checkNotEmpty(arg33);
        Preconditions.checkNotEmpty(arg34);
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        zzl v8 = null;
        try {
            SQLiteDatabase v9 = this.getWritableDatabase();
            String[] v11 = new String[11];
            v11[0] = "origin";
            v11[1] = "value";
            v3 = 2;
            v11[v3] = "active";
            v4 = 3;
            v11[v4] = "trigger_event_name";
            v5 = 4;
            v11[v5] = "trigger_timeout";
            v11[5] = "timed_out_event";
            v11[6] = "creation_timestamp";
            v11[7] = "triggered_event";
            v11[8] = "triggered_timestamp";
            v11[9] = "time_to_live";
            v11[10] = "expired_event";
            String[] v13 = new String[v3];
            v13[0] = arg33;
            v13[1] = v7;
            v6 = 7;
            v0_2 = 6;
            v9_1 = v9.query("conditional_properties", v11, "app_id=? and name=?", v13, null, null, null);
        }
        catch(Throwable v0) {
            v9_1 = ((Cursor)v8);
            goto label_146;
        }
        catch(SQLiteException v0_1) {
            v9_1 = ((Cursor)v8);
            goto label_135;
        }

        try {
            if(v9_1.moveToFirst()) {
                goto label_64;
            }
        }
        catch(Throwable v0) {
            goto label_124;
        }
        catch(SQLiteException v0_1) {
            goto label_127;
        }

        if(v9_1 != null) {
            v9_1.close();
        }

        return v8;
        try {
        label_64:
            v19 = v9_1.getString(0);
            v10 = this;
        }
        catch(Throwable v0) {
        label_124:
            goto label_146;
        }
        catch(SQLiteException v0_1) {
        label_127:
            goto label_135;
        }

        try {
            Object v11_1 = v10.zza(v9_1, 1);
            boolean v23 = v9_1.getInt(v3) != 0 ? true : false;
            v0_3 = new zzl(arg33, v19, new zzfh(arg34, v9_1.getLong(8), v11_1, v19), v9_1.getLong(v0_2), v23, v9_1.getString(v4), ((zzey)this).zzjo().zza(v9_1.getBlob(5), zzad.CREATOR), v9_1.getLong(v5), ((zzey)this).zzjo().zza(v9_1.getBlob(v6), zzad.CREATOR), v9_1.getLong(9), ((zzey)this).zzjo().zza(v9_1.getBlob(10), zzad.CREATOR));
            if(v9_1.moveToNext()) {
                ((zzco)this).zzgo().zzjd().zze("Got multiple records for conditional property, expected one", zzap.zzbv(arg33), ((zzco)this).zzgl().zzbu(v7));
            }

            goto label_117;
        }
        catch(Throwable v0) {
        }
        catch(SQLiteException v0_1) {
            try {
            label_135:
                ((zzco)this).zzgo().zzjd().zzd("Error querying conditional property", zzap.zzbv(arg33), ((zzco)this).zzgl().zzbu(v7), v0_1);
                if(v9_1 != null) {
                    goto label_143;
                }

                return v8;
            }
            catch(Throwable v0) {
                goto label_146;
            }

        label_143:
            v9_1.close();
            return v8;
        }

    label_146:
        if(v9_1 != null) {
            v9_1.close();
        }

        throw v0;
    label_117:
        if(v9_1 != null) {
            v9_1.close();
        }

        return v0_3;
    }

    public final int zzk(String arg7, String arg8) {
        Preconditions.checkNotEmpty(arg7);
        Preconditions.checkNotEmpty(arg8);
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        try {
            return this.getWritableDatabase().delete("conditional_properties", "app_id=? and name=?", new String[]{arg7, arg8});
        }
        catch(SQLiteException v1) {
            ((zzco)this).zzgo().zzjd().zzd("Error deleting conditional property", zzap.zzbv(arg7), ((zzco)this).zzgl().zzbu(arg8), v1);
            return 0;
        }
    }

    final Map zzl(String arg13, String arg14) {
        ArrayList v3_1;
        Map v0_2;
        Cursor v14;
        ((zzez)this).zzcl();
        ((zzco)this).zzaf();
        Preconditions.checkNotEmpty(arg13);
        Preconditions.checkNotEmpty(arg14);
        a v0 = new a();
        SQLiteDatabase v1 = this.getWritableDatabase();
        Map v9 = null;
        try {
            v14 = v1.query("event_filters", new String[]{"audience_id", "data"}, "app_id=? AND event_name=?", new String[]{arg13, arg14}, null, null, null);
        }
        catch(Throwable v13) {
            v14 = ((Cursor)v9);
            goto label_78;
        }
        catch(SQLiteException v0_1) {
            v14 = ((Cursor)v9);
            goto label_69;
        }

        try {
            if(v14.moveToFirst()) {
                goto label_35;
            }

            v0_2 = Collections.emptyMap();
            if(v14 != null) {
                goto label_33;
            }

            return v0_2;
        }
        catch(Throwable v13) {
            goto label_78;
        }
        catch(SQLiteException v0_1) {
            goto label_63;
        }

    label_33:
        v14.close();
        return v0_2;
        try {
            do {
            label_35:
                byte[] v1_1 = v14.getBlob(1);
                zzyx v1_2 = zzyx.zzj(v1_1, 0, v1_1.length);
                zzfv v2 = new zzfv();
                try {
                    ((zzzg)v2).zza(v1_2);
                }
                catch(IOException v1_3) {
                    ((zzco)this).zzgo().zzjd().zze("Failed to merge filter. appId", zzap.zzbv(arg13), v1_3);
                    goto label_57;
                }

                int v1_4 = v14.getInt(0);
                Object v3 = ((Map)v0).get(Integer.valueOf(v1_4));
                if(v3 == null) {
                    v3_1 = new ArrayList();
                    ((Map)v0).put(Integer.valueOf(v1_4), v3_1);
                }

                ((List)v3_1).add(v2);
            label_57:
                if(v14.moveToNext()) {
                    continue;
                }

                break;
            }
            while(true);
        }
        catch(Throwable v13) {
            goto label_78;
        }
        catch(SQLiteException v0_1) {
            goto label_63;
        }

        if(v14 != null) {
            v14.close();
        }

        return ((Map)v0);
    label_63:
        try {
        label_69:
            ((zzco)this).zzgo().zzjd().zze("Database error querying filters. appId", zzap.zzbv(arg13), v0_1);
            if(v14 != null) {
                goto label_75;
            }

            return v9;
        }
        catch(Throwable v13) {
            goto label_78;
        }

    label_75:
        v14.close();
        return v9;
    label_78:
        if(v14 != null) {
            v14.close();
        }

        throw v13;
    }

    final Map zzm(String arg13, String arg14) {
        Map v0_2;
        Cursor v14;
        ((zzez)this).zzcl();
        ((zzco)this).zzaf();
        Preconditions.checkNotEmpty(arg13);
        Preconditions.checkNotEmpty(arg14);
        a v0 = new a();
        SQLiteDatabase v1 = this.getWritableDatabase();
        Map v9 = null;
        try {
            v14 = v1.query("property_filters", new String[]{"audience_id", "data"}, "app_id=? AND property_name=?", new String[]{arg13, arg14}, null, null, null);
        }
        catch(Throwable v13) {
            v14 = ((Cursor)v9);
            goto label_78;
        }
        catch(SQLiteException v0_1) {
            v14 = ((Cursor)v9);
            goto label_69;
        }

        try {
            if(v14.moveToFirst()) {
                goto label_35;
            }

            v0_2 = Collections.emptyMap();
            if(v14 != null) {
                goto label_33;
            }

            return v0_2;
        }
        catch(Throwable v13) {
            goto label_78;
        }
        catch(SQLiteException v0_1) {
            goto label_63;
        }

    label_33:
        v14.close();
        return v0_2;
        try {
            do {
            label_35:
                byte[] v1_1 = v14.getBlob(1);
                zzyx v1_2 = zzyx.zzj(v1_1, 0, v1_1.length);
                zzfy v2 = new zzfy();
                try {
                    ((zzzg)v2).zza(v1_2);
                }
                catch(IOException v1_3) {
                    ((zzco)this).zzgo().zzjd().zze("Failed to merge filter", zzap.zzbv(arg13), v1_3);
                    goto label_57;
                }

                int v1_4 = v14.getInt(0);
                Object v3 = ((Map)v0).get(Integer.valueOf(v1_4));
                if(v3 == null) {
                    ArrayList v3_1 = new ArrayList();
                    ((Map)v0).put(Integer.valueOf(v1_4), v3_1);
                }

                ((List)v3).add(v2);
            label_57:
                if(v14.moveToNext()) {
                    continue;
                }

                break;
            }
            while(true);
        }
        catch(Throwable v13) {
            goto label_78;
        }
        catch(SQLiteException v0_1) {
            goto label_63;
        }

        if(v14 != null) {
            v14.close();
        }

        return ((Map)v0);
    label_63:
        try {
        label_69:
            ((zzco)this).zzgo().zzjd().zze("Database error querying filters. appId", zzap.zzbv(arg13), v0_1);
            if(v14 != null) {
                goto label_75;
            }

            return v9;
        }
        catch(Throwable v13) {
            goto label_78;
        }

    label_75:
        v14.close();
        return v9;
    label_78:
        if(v14 != null) {
            v14.close();
        }

        throw v13;
    }

    @VisibleForTesting protected final long zzn(String arg14, String arg15) {
        ContentValues v3_1;
        long v9;
        long v7;
        Preconditions.checkNotEmpty(arg14);
        Preconditions.checkNotEmpty(arg15);
        ((zzco)this).zzaf();
        ((zzez)this).zzcl();
        SQLiteDatabase v0 = this.getWritableDatabase();
        v0.beginTransaction();
        long v1 = 0;
        try {
            StringBuilder v4 = new StringBuilder(String.valueOf(arg15).length() + 32);
            v4.append("select ");
            v4.append(arg15);
            v4.append(" from app2 where app_id=?");
            v7 = -1;
            v9 = this.zza(v4.toString(), new String[]{arg14}, v7);
            if(v9 == v7) {
                v3_1 = new ContentValues();
                v3_1.put("app_id", arg14);
                v3_1.put("first_open_count", Integer.valueOf(0));
                v3_1.put("previous_install_count", Integer.valueOf(0));
                if(v0.insertWithOnConflict("app2", null, v3_1, 5) == v7) {
                    ((zzco)this).zzgo().zzjd().zze("Failed to insert column (got -1). appId", zzap.zzbv(arg14), arg15);
                    goto label_45;
                }
                else {
                    goto label_47;
                }
            }

            goto label_48;
        }
        catch(Throwable v14) {
        }
        catch(SQLiteException v3) {
            v9 = v1;
            goto label_78;
        label_45:
            v0.endTransaction();
            return v7;
        label_47:
            v9 = v1;
            try {
            label_48:
                v3_1 = new ContentValues();
                v3_1.put("app_id", arg14);
                v3_1.put(arg15, Long.valueOf(1 + v9));
                if((((long)v0.update("app2", v3_1, "app_id = ?", new String[]{arg14}))) != v1) {
                    goto label_70;
                }

                ((zzco)this).zzgo().zzjd().zze("Failed to update column (got 0). appId", zzap.zzbv(arg14), arg15);
            }
            catch(SQLiteException v3) {
                goto label_73;
            }
            catch(Throwable v14) {
                goto label_85;
            }

            v0.endTransaction();
            return v7;
            try {
            label_70:
                v0.setTransactionSuccessful();
            }
            catch(Throwable v14) {
            }
            catch(SQLiteException v3) {
            label_73:
                try {
                label_78:
                    ((zzco)this).zzgo().zzjd().zzd("Error inserting column. appId", zzap.zzbv(arg14), arg15, v3);
                }
                catch(Throwable v14) {
                label_85:
                    v0.endTransaction();
                    throw v14;
                }
            }
        }

        v0.endTransaction();
        return v9;
    }
}

