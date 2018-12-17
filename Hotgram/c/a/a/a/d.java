package c.a.a.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase$CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import java.io.File;

class d extends ContextWrapper {
    private final String a;
    private final String b;

    public d(Context arg1, String arg2, String arg3) {
        super(arg1);
        this.b = arg2;
        this.a = arg3;
    }

    public File getCacheDir() {
        return new File(super.getCacheDir(), this.a);
    }

    public File getDatabasePath(String arg4) {
        File v0 = new File(super.getDatabasePath(arg4).getParentFile(), this.a);
        v0.mkdirs();
        return new File(v0, arg4);
    }

    @TargetApi(value=8) public File getExternalCacheDir() {
        return new File(super.getExternalCacheDir(), this.a);
    }

    @TargetApi(value=8) public File getExternalFilesDir(String arg3) {
        return new File(super.getExternalFilesDir(arg3), this.a);
    }

    public File getFilesDir() {
        return new File(super.getFilesDir(), this.a);
    }

    public SharedPreferences getSharedPreferences(String arg3, int arg4) {
        StringBuilder v0 = new StringBuilder();
        v0.append(this.b);
        v0.append(":");
        v0.append(arg3);
        return super.getSharedPreferences(v0.toString(), arg4);
    }

    public SQLiteDatabase openOrCreateDatabase(String arg1, int arg2, SQLiteDatabase$CursorFactory arg3) {
        return SQLiteDatabase.openOrCreateDatabase(this.getDatabasePath(arg1), arg3);
    }

    @TargetApi(value=11) public SQLiteDatabase openOrCreateDatabase(String arg1, int arg2, SQLiteDatabase$CursorFactory arg3, DatabaseErrorHandler arg4) {
        return SQLiteDatabase.openOrCreateDatabase(this.getDatabasePath(arg1).getPath(), arg3, arg4);
    }
}

