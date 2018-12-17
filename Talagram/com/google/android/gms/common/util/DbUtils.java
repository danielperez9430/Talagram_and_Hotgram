package com.google.android.gms.common.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.stable.zzk;
import java.io.File;
import java.nio.charset.Charset;
import java.util.Set;
import javax.annotation.Nullable;

public final class DbUtils {
    private DbUtils() {
        super();
    }

    public static void clearDatabase(SQLiteDatabase arg5) {
        DbUtils.zza(arg5, "table", new String[]{"sqlite_sequence", "android_metadata"});
        DbUtils.zza(arg5, "trigger", new String[0]);
        DbUtils.zza(arg5, "view", new String[0]);
    }

    public static long countCurrentRowBytes(Cursor arg2) {
        return DbUtils.countCurrentRowBytes(arg2, Charset.forName("UTF-8"));
    }

    public static long countCurrentRowBytes(Cursor arg5, Charset arg6) {
        long v0 = 0;
        int v2;
        for(v2 = 0; v2 < arg5.getColumnCount(); ++v2) {
            switch(arg5.getType(v2)) {
                case 0: 
                case 1: 
                case 2: {
                    goto label_15;
                }
                case 3: {
                    goto label_10;
                }
                case 4: {
                    goto label_7;
                }
            }

            goto label_17;
        label_7:
            int v3 = arg5.getBlob(v2).length;
            goto label_13;
        label_10:
            v3 = arg5.getString(v2).getBytes(arg6).length;
        label_13:
            long v3_1 = ((long)v3);
            goto label_16;
        label_15:
            v3_1 = 4;
        label_16:
            v0 += v3_1;
        label_17:
        }

        return v0;
    }

    public static long getDatabaseSize(Context arg2, String arg3) {
        try {
            File v2 = arg2.getDatabasePath(arg3);
            if(v2 == null) {
                return 0;
            }

            return v2.length();
        }
        catch(SecurityException ) {
            String v2_1 = "DbUtils";
            String v0 = "Failed to get db size for ";
            arg3 = String.valueOf(arg3);
            arg3 = arg3.length() != 0 ? v0.concat(arg3) : new String(v0);
            Log.w(v2_1, arg3);
        }

        return 0;
    }

    @Nullable public static Integer getIntegerFromCursor(Cursor arg1, int arg2) {
        return DbUtils.getIntegerFromCursor(arg1, arg2, null);
    }

    @Nullable public static Integer getIntegerFromCursor(Cursor arg1, int arg2, @Nullable Integer arg3) {
        if(arg2 >= 0) {
            if(arg1.isNull(arg2)) {
            }
            else {
                return Integer.valueOf(arg1.getInt(arg2));
            }
        }

        return arg3;
    }

    @Nullable public static Long getLongFromCursor(Cursor arg1, int arg2) {
        return DbUtils.getLongFromCursor(arg1, arg2, null);
    }

    @Nullable public static Long getLongFromCursor(Cursor arg1, int arg2, @Nullable Long arg3) {
        if(arg2 >= 0) {
            if(arg1.isNull(arg2)) {
            }
            else {
                return Long.valueOf(arg1.getLong(arg2));
            }
        }

        return arg3;
    }

    @Nullable public static String getStringFromCursor(Cursor arg1, int arg2) {
        return DbUtils.getStringFromCursor(arg1, arg2, null);
    }

    @Nullable public static String getStringFromCursor(Cursor arg1, int arg2, @Nullable String arg3) {
        if(arg2 >= 0) {
            if(arg1.isNull(arg2)) {
            }
            else {
                return arg1.getString(arg2);
            }
        }

        return arg3;
    }

    public static void putIntegerIntoContentValues(ContentValues arg0, String arg1, @Nullable Integer arg2) {
        if(arg2 != null) {
            arg0.put(arg1, arg2);
            return;
        }

        arg0.putNull(arg1);
    }

    public static void putLongIntoContentValues(ContentValues arg0, String arg1, @Nullable Long arg2) {
        if(arg2 != null) {
            arg0.put(arg1, arg2);
            return;
        }

        arg0.putNull(arg1);
    }

    public static void putStringIntoContentValues(ContentValues arg0, String arg1, @Nullable String arg2) {
        if(arg2 != null) {
            arg0.put(arg1, arg2);
            return;
        }

        arg0.putNull(arg1);
    }

    private static void zza(SQLiteDatabase arg11, String arg12, String[] arg13) {
        boolean v0 = ("table".equals(arg12)) || ("view".equals(arg12)) || ("trigger".equals(arg12)) ? true : false;
        Preconditions.checkArgument(v0);
        Cursor v0_1 = arg11.query("SQLITE_MASTER", new String[]{"name"}, "type == ?", new String[]{arg12}, null, null, null);
        Throwable v1 = null;
        try {
            Set v13 = CollectionUtils.setOf(((Object[])arg13));
            while(v0_1.moveToNext()) {
                String v3 = v0_1.getString(0);
                if(v13.contains(v3)) {
                    continue;
                }

                StringBuilder v5 = new StringBuilder(String.valueOf(arg12).length() + 8 + String.valueOf(v3).length());
                v5.append("DROP ");
                v5.append(arg12);
                v5.append(" \'");
                v5.append(v3);
                v5.append("\'");
                arg11.execSQL(v5.toString());
            }
        }
        catch(Throwable v11) {
            goto label_62;
        }
        catch(Throwable v11) {
            goto label_60;
        }

        if(v0_1 != null) {
            v0_1.close();
        }

        return;
    label_60:
        v1 = v11;
        try {
            throw v1;
        }
        catch(Throwable v11) {
        label_62:
            if(v0_1 != null) {
                if(v1 != null) {
                    try {
                        v0_1.close();
                    }
                    catch(Throwable v12) {
                        zzk.zza(v1, v12);
                    }
                }
                else {
                    v0_1.close();
                }
            }

            throw v11;
        }
    }
}

