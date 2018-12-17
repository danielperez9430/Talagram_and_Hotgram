package com.google.android.gms.measurement.internal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class zzu {
    static void zza(zzap arg3, SQLiteDatabase arg4) {
        if(arg3 != null) {
            File v0 = new File(arg4.getPath());
            if(!v0.setReadable(false, false)) {
                arg3.zzjg().zzbx("Failed to turn off database read permission");
            }

            if(!v0.setWritable(false, false)) {
                arg3.zzjg().zzbx("Failed to turn off database write permission");
            }

            if(!v0.setReadable(true, true)) {
                arg3.zzjg().zzbx("Failed to turn on database read permission for owner");
            }

            if(!v0.setWritable(true, true)) {
                arg3.zzjg().zzbx("Failed to turn on database write permission for owner");
            }

            return;
        }

        throw new IllegalArgumentException("Monitor must not be null");
    }

    static void zza(zzap arg5, SQLiteDatabase arg6, String arg7, String arg8, String arg9, String[] arg10) {
        String v3;
        if(arg5 != null) {
            if(!zzu.zza(arg5, arg6, arg7)) {
                arg6.execSQL(arg8);
            }

            if(arg5 == null) {
                goto label_54;
            }

            try {
                Set v8 = zzu.zzb(arg6, arg7);
                String[] v9 = arg9.split(",");
                int v0 = v9.length;
                int v1 = 0;
                int v2;
                for(v2 = 0; true; ++v2) {
                    if(v2 >= v0) {
                        goto label_35;
                    }

                    v3 = v9[v2];
                    if(!v8.remove(v3)) {
                        break;
                    }
                }

                StringBuilder v9_1 = new StringBuilder(String.valueOf(arg7).length() + 35 + String.valueOf(v3).length());
                v9_1.append("Table ");
                v9_1.append(arg7);
                v9_1.append(" is missing required column: ");
                v9_1.append(v3);
                throw new SQLiteException(v9_1.toString());
            label_35:
                if(arg10 != null) {
                    while(v1 < arg10.length) {
                        if(!v8.remove(arg10[v1])) {
                            arg6.execSQL(arg10[v1 + 1]);
                        }

                        v1 += 2;
                    }
                }

                if(!v8.isEmpty()) {
                    arg5.zzjg().zze("Table has extra columns. table, columns", arg7, TextUtils.join(", ", ((Iterable)v8)));
                }

                return;
            label_54:
                throw new IllegalArgumentException("Monitor must not be null");
            }
            catch(SQLiteException v6) {
                arg5.zzjd().zzg("Failed to verify columns on table that was just created", arg7);
                throw v6;
            }
        }

        throw new IllegalArgumentException("Monitor must not be null");
    }

    private static boolean zza(zzap arg11, SQLiteDatabase arg12, String arg13) {
        boolean v0_2;
        Cursor v12_1;
        if(arg11 == null) {
            goto label_40;
        }

        Cursor v0 = null;
        try {
            v12_1 = arg12.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{arg13}, null, null, null);
            goto label_16;
        }
        catch(Throwable v11) {
        }
        catch(SQLiteException v12) {
            goto label_31;
            try {
            label_16:
                v0_2 = v12_1.moveToFirst();
                if(v12_1 == null) {
                    return v0_2;
                }

                goto label_18;
            }
            catch(Throwable v11) {
                v0 = v12_1;
            }
            catch(SQLiteException v0_1) {
                SQLiteException v10 = v0_1;
                v0 = v12_1;
                v12 = v10;
                try {
                label_31:
                    arg11.zzjg().zze("Error querying for table", arg13, v12);
                    if(v0 == null) {
                        return 0;
                    }
                }
                catch(Throwable v11) {
                    goto label_37;
                }

                v0.close();
                return 0;
            }
        }

    label_37:
        if(v0 != null) {
            v0.close();
        }

        throw v11;
    label_18:
        v12_1.close();
        return v0_2;
    label_40:
        throw new IllegalArgumentException("Monitor must not be null");
    }

    private static Set zzb(SQLiteDatabase arg3, String arg4) {
        HashSet v0 = new HashSet();
        StringBuilder v2 = new StringBuilder(String.valueOf(arg4).length() + 22);
        v2.append("SELECT * FROM ");
        v2.append(arg4);
        v2.append(" LIMIT 0");
        Cursor v3 = arg3.rawQuery(v2.toString(), null);
        try {
            Collections.addAll(((Collection)v0), v3.getColumnNames());
        }
        catch(Throwable v4) {
            v3.close();
            throw v4;
        }

        v3.close();
        return ((Set)v0);
    }
}

