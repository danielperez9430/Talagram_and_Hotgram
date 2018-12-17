package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build$VERSION;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting final class zzam extends SQLiteOpenHelper {
    zzam(zzal arg2, Context arg3, String arg4) {
        this.zzals = arg2;
        super(arg3, arg4, null, 1);
    }

    public final SQLiteDatabase getWritableDatabase() {
        try {
            return super.getWritableDatabase();
        }
        catch(SQLiteException ) {
            this.zzals.zzgo().zzjd().zzbx("Opening the local database failed, dropping and recreating it");
            String v0_1 = "google_app_measurement_local.db";
            if(!this.zzals.getContext().getDatabasePath(v0_1).delete()) {
                this.zzals.zzgo().zzjd().zzg("Failed to delete corrupted local db file", v0_1);
            }

            try {
                return super.getWritableDatabase();
            }
            catch(SQLiteException v0_2) {
                this.zzals.zzgo().zzjd().zzg("Failed to open local database. Events will bypass local storage", v0_2);
                return null;
            }
        }
        catch(SQLiteDatabaseLockedException v0) {
            throw v0;
        }
    }

    public final void onCreate(SQLiteDatabase arg2) {
        zzu.zza(this.zzals.zzgo(), arg2);
    }

    public final void onDowngrade(SQLiteDatabase arg1, int arg2, int arg3) {
    }

    public final void onOpen(SQLiteDatabase arg8) {
        Cursor v1;
        if(Build$VERSION.SDK_INT < 15) {
            String[] v0 = null;
            try {
                v1 = arg8.rawQuery("PRAGMA journal_mode=memory", v0);
            }
            catch(Throwable v8) {
                goto label_14;
            }

            try {
                v1.moveToFirst();
                if(v1 == null) {
                    goto label_17;
                }

                goto label_8;
            }
            catch(Throwable v8) {
                Cursor v0_1 = v1;
            }

        label_14:
            if((((Cursor)v0)) != null) {
                ((Cursor)v0).close();
            }

            throw v8;
        label_8:
            v1.close();
        }

    label_17:
        zzu.zza(this.zzals.zzgo(), arg8, "messages", "create table if not exists messages ( type INTEGER NOT NULL, entry BLOB NOT NULL)", "type,entry", null);
    }

    public final void onUpgrade(SQLiteDatabase arg1, int arg2, int arg3) {
    }
}

