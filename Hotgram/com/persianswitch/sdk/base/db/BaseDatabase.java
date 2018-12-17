package com.persianswitch.sdk.base.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.persianswitch.sdk.base.db.phoenix.SqliteKeyValue$SqlitePreferenceTable;

public class BaseDatabase extends SQLiteOpenHelper {
    interface Version {
    }

    private final Context a;

    public BaseDatabase(Context arg4) {
        super(arg4, "base.dat", null, 1);
        this.a = arg4;
    }

    public void a() {
        new SqlitePreferenceTable("pref").b(this.getWritableDatabase());
        new SqlitePreferenceTable("secure_pref").b(this.getWritableDatabase());
    }

    public void onCreate(SQLiteDatabase arg3) {
        new SqlitePreferenceTable("pref").a(arg3);
        new SqlitePreferenceTable("secure_pref").a(arg3);
    }

    public void onUpgrade(SQLiteDatabase arg1, int arg2, int arg3) {
    }
}

