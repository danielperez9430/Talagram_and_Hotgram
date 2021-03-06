package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting final class zzt extends SQLiteOpenHelper {
    zzt(zzq arg2, Context arg3, String arg4) {
        this.zzahv = arg2;
        super(arg3, arg4, null, 1);
    }

    public final SQLiteDatabase getWritableDatabase() {
        if(zzq.zza(this.zzahv).zzj(3600000)) {
            try {
                return super.getWritableDatabase();
            }
            catch(SQLiteException ) {
                zzq.zza(this.zzahv).start();
                this.zzahv.zzgo().zzjd().zzbx("Opening the database failed, dropping and recreating it");
                String v0 = "google_app_measurement.db";
                if(!this.zzahv.getContext().getDatabasePath(v0).delete()) {
                    this.zzahv.zzgo().zzjd().zzg("Failed to delete corrupted db file", v0);
                }

                try {
                    SQLiteDatabase v0_2 = super.getWritableDatabase();
                    zzq.zza(this.zzahv).clear();
                    return v0_2;
                }
                catch(SQLiteException v0_1) {
                    this.zzahv.zzgo().zzjd().zzg("Failed to open freshly created database", v0_1);
                    throw v0_1;
                }
            }
        }

        throw new SQLiteException("Database open failed");
    }

    public final void onCreate(SQLiteDatabase arg2) {
        zzu.zza(this.zzahv.zzgo(), arg2);
    }

    public final void onDowngrade(SQLiteDatabase arg1, int arg2, int arg3) {
    }

    public final void onOpen(SQLiteDatabase arg8) {
        zzu.zza(this.zzahv.zzgo(), arg8, "events", "CREATE TABLE IF NOT EXISTS events ( app_id TEXT NOT NULL, name TEXT NOT NULL, lifetime_count INTEGER NOT NULL, current_bundle_count INTEGER NOT NULL, last_fire_timestamp INTEGER NOT NULL, PRIMARY KEY (app_id, name)) ;", "app_id,name,lifetime_count,current_bundle_count,last_fire_timestamp", zzq.zzim());
        zzu.zza(this.zzahv.zzgo(), arg8, "conditional_properties", "CREATE TABLE IF NOT EXISTS conditional_properties ( app_id TEXT NOT NULL, origin TEXT NOT NULL, name TEXT NOT NULL, value BLOB NOT NULL, creation_timestamp INTEGER NOT NULL, active INTEGER NOT NULL, trigger_event_name TEXT, trigger_timeout INTEGER NOT NULL, timed_out_event BLOB,triggered_event BLOB, triggered_timestamp INTEGER NOT NULL, time_to_live INTEGER NOT NULL, expired_event BLOB, PRIMARY KEY (app_id, name)) ;", "app_id,origin,name,value,active,trigger_event_name,trigger_timeout,creation_timestamp,timed_out_event,triggered_event,triggered_timestamp,time_to_live,expired_event", null);
        zzu.zza(this.zzahv.zzgo(), arg8, "user_attributes", "CREATE TABLE IF NOT EXISTS user_attributes ( app_id TEXT NOT NULL, name TEXT NOT NULL, set_timestamp INTEGER NOT NULL, value BLOB NOT NULL, PRIMARY KEY (app_id, name)) ;", "app_id,name,set_timestamp,value", zzq.zzin());
        zzu.zza(this.zzahv.zzgo(), arg8, "apps", "CREATE TABLE IF NOT EXISTS apps ( app_id TEXT NOT NULL, app_instance_id TEXT, gmp_app_id TEXT, resettable_device_id_hash TEXT, last_bundle_index INTEGER NOT NULL, last_bundle_end_timestamp INTEGER NOT NULL, PRIMARY KEY (app_id)) ;", "app_id,app_instance_id,gmp_app_id,resettable_device_id_hash,last_bundle_index,last_bundle_end_timestamp", zzq.zzio());
        zzu.zza(this.zzahv.zzgo(), arg8, "queue", "CREATE TABLE IF NOT EXISTS queue ( app_id TEXT NOT NULL, bundle_end_timestamp INTEGER NOT NULL, data BLOB NOT NULL);", "app_id,bundle_end_timestamp,data", zzq.zzip());
        zzu.zza(this.zzahv.zzgo(), arg8, "raw_events_metadata", "CREATE TABLE IF NOT EXISTS raw_events_metadata ( app_id TEXT NOT NULL, metadata_fingerprint INTEGER NOT NULL, metadata BLOB NOT NULL, PRIMARY KEY (app_id, metadata_fingerprint));", "app_id,metadata_fingerprint,metadata", null);
        zzu.zza(this.zzahv.zzgo(), arg8, "raw_events", "CREATE TABLE IF NOT EXISTS raw_events ( app_id TEXT NOT NULL, name TEXT NOT NULL, timestamp INTEGER NOT NULL, metadata_fingerprint INTEGER NOT NULL, data BLOB NOT NULL);", "app_id,name,timestamp,metadata_fingerprint,data", zzq.zziq());
        zzu.zza(this.zzahv.zzgo(), arg8, "event_filters", "CREATE TABLE IF NOT EXISTS event_filters ( app_id TEXT NOT NULL, audience_id INTEGER NOT NULL, filter_id INTEGER NOT NULL, event_name TEXT NOT NULL, data BLOB NOT NULL, PRIMARY KEY (app_id, event_name, audience_id, filter_id));", "app_id,audience_id,filter_id,event_name,data", null);
        zzu.zza(this.zzahv.zzgo(), arg8, "property_filters", "CREATE TABLE IF NOT EXISTS property_filters ( app_id TEXT NOT NULL, audience_id INTEGER NOT NULL, filter_id INTEGER NOT NULL, property_name TEXT NOT NULL, data BLOB NOT NULL, PRIMARY KEY (app_id, property_name, audience_id, filter_id));", "app_id,audience_id,filter_id,property_name,data", null);
        zzu.zza(this.zzahv.zzgo(), arg8, "audience_filter_values", "CREATE TABLE IF NOT EXISTS audience_filter_values ( app_id TEXT NOT NULL, audience_id INTEGER NOT NULL, current_results BLOB, PRIMARY KEY (app_id, audience_id));", "app_id,audience_id,current_results", null);
        zzu.zza(this.zzahv.zzgo(), arg8, "app2", "CREATE TABLE IF NOT EXISTS app2 ( app_id TEXT NOT NULL, first_open_count INTEGER NOT NULL, PRIMARY KEY (app_id));", "app_id,first_open_count", zzq.zzir());
        zzu.zza(this.zzahv.zzgo(), arg8, "main_event_params", "CREATE TABLE IF NOT EXISTS main_event_params ( app_id TEXT NOT NULL, event_id TEXT NOT NULL, children_to_process INTEGER NOT NULL, main_event BLOB NOT NULL, PRIMARY KEY (app_id));", "app_id,event_id,children_to_process,main_event", null);
    }

    public final void onUpgrade(SQLiteDatabase arg1, int arg2, int arg3) {
    }
}

