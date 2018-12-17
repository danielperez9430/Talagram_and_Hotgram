package com.persianswitch.sdk.payment.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.persianswitch.sdk.base.db.phoenix.SqliteKeyValue$SqlitePreferenceTable;
import com.persianswitch.sdk.payment.repo.CardRepo;
import com.persianswitch.sdk.payment.repo.SyncRepo;

public final class SDKDatabase extends SQLiteOpenHelper {
    interface Version {
    }

    private final Context a;

    public SDKDatabase(Context arg4) {
        super(arg4, "sdk.dat", null, 1);
        this.a = arg4;
    }

    public void a() {
        new SqlitePreferenceTable("pref").b(this.getWritableDatabase());
        new SqlitePreferenceTable("secure_pref").b(this.getWritableDatabase());
        new CardRepo(this.a).b().b(this.getWritableDatabase());
        new SyncRepo(this.a).b().b(this.getWritableDatabase());
    }

    public void onCreate(SQLiteDatabase arg3) {
        new CardRepo(this.a).b().a(arg3);
        new SyncRepo(this.a).b().a(arg3);
        new SqlitePreferenceTable("pref").a(arg3);
        new SqlitePreferenceTable("secure_pref").a(arg3);
    }

    public void onUpgrade(SQLiteDatabase arg1, int arg2, int arg3) {
    }
}

