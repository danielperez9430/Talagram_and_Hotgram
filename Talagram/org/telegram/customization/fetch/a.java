package org.telegram.customization.fetch;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import org.telegram.customization.fetch.b.b;

final class a extends SQLiteOpenHelper {
    private static a a;
    private final SQLiteDatabase b;
    private boolean c;

    private a(Context arg4) {
        super(arg4, "com_tonyodev_fetch.db", null, 2);
        this.c = true;
        this.b = this.getWritableDatabase();
    }

    Cursor a(int arg5) {
        Cursor v5_2;
        __monitor_enter(this);
        String[] v0 = null;
        try {
            SQLiteDatabase v1 = this.b;
            StringBuilder v2 = new StringBuilder();
            v2.append("SELECT * FROM requests WHERE _status = ");
            v2.append(arg5);
            v5_2 = v1.rawQuery(v2.toString(), v0);
        }
        catch(Throwable v5) {
        label_20:
            __monitor_exit(this);
            throw v5;
        }
        catch(SQLiteException v5_1) {
            try {
                if(this.c) {
                    v5_1.printStackTrace();
                }
            }
            catch(Throwable v5) {
                goto label_20;
            }

            __monitor_exit(this);
            return ((Cursor)v0);
        }

        __monitor_exit(this);
        return v5_2;
    }

    boolean a(long arg5, int arg7) {
        __monitor_enter(this);
        try {
            this.b.beginTransaction();
            SQLiteDatabase v1 = this.b;
            v1.execSQL("UPDATE requests SET _priority = " + arg7 + " WHERE " + "_id" + " = " + arg5);
            this.b.setTransactionSuccessful();
            goto label_28;
        }
        catch(Throwable v5) {
        }
        catch(SQLiteException v5_1) {
            try {
                if(!this.c) {
                    goto label_28;
                }

                v5_1.printStackTrace();
                try {
                label_28:
                    this.b.endTransaction();
                }
                catch(SQLiteException v5_1) {
                    try {
                        if(!this.c) {
                            goto label_36;
                        }

                        v5_1.printStackTrace();
                    }
                    catch(Throwable v5) {
                    label_38:
                        __monitor_exit(this);
                        throw v5;
                    }
                }
            }
            catch(Throwable v5) {
                goto label_38;
            }
        }

    label_36:
        __monitor_exit(this);
        return 1;
    }

    boolean a(long arg3, String arg5, String arg6, int arg7, String arg8, long arg9, long arg11, int arg13, int arg14) {
        boolean v3_1;
        __monitor_enter(this);
        try {
            this.a(arg5, arg6);
            StringBuilder v0 = new StringBuilder();
            v0.append(this.a());
            v0.append(this.b(arg3, arg5, arg6, arg7, arg8, arg9, arg11, arg13, arg14));
            v0.append(this.b());
            v3_1 = this.a(v0.toString());
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
        return v3_1;
    }

    void a(boolean arg1) {
        __monitor_enter(this);
        try {
            this.c = arg1;
        }
        catch(Throwable v1) {
            __monitor_exit(this);
            throw v1;
        }

        __monitor_exit(this);
    }

    boolean a(long arg5, int arg7, int arg8) {
        __monitor_enter(this);
        try {
            this.b.beginTransaction();
            SQLiteDatabase v1 = this.b;
            v1.execSQL("UPDATE requests SET _status = " + arg7 + ", " + "_error" + " = " + arg8 + " WHERE " + "_id" + " = " + arg5);
            this.b.setTransactionSuccessful();
            goto label_35;
        }
        catch(Throwable v5) {
        }
        catch(SQLiteException v5_1) {
            try {
                if(!this.c) {
                    goto label_35;
                }

                v5_1.printStackTrace();
                try {
                label_35:
                    this.b.endTransaction();
                }
                catch(SQLiteException v5_1) {
                    try {
                        if(!this.c) {
                            goto label_43;
                        }

                        v5_1.printStackTrace();
                    }
                    catch(Throwable v5) {
                    label_45:
                        __monitor_exit(this);
                        throw v5;
                    }
                }
            }
            catch(Throwable v5) {
                goto label_45;
            }
        }

    label_43:
        __monitor_exit(this);
        return 1;
    }

    boolean a(long arg6, String arg8) {
        Cursor v1_3;
        Cursor v6_1;
        __monitor_enter(this);
        boolean v0 = false;
        try {
            this.b.beginTransaction();
            SQLiteDatabase v1_1 = this.b;
            v1_1.execSQL("UPDATE requests SET _url = " + DatabaseUtils.sqlEscapeString(arg8) + " WHERE " + "_id" + " = " + arg6);
            this.b.setTransactionSuccessful();
            goto label_29;
        }
        catch(Throwable v6) {
        }
        catch(SQLiteException v1) {
            try {
                if(!this.c) {
                    goto label_29;
                }

                v1.printStackTrace();
            }
            catch(Throwable v6) {
                goto label_76;
            }

        label_29:
            String[] v1_2 = null;
            try {
                this.b.endTransaction();
                SQLiteDatabase v2_1 = this.b;
                StringBuilder v3 = new StringBuilder();
                v3.append("SELECT _id FROM requests WHERE _id = ");
                v3.append(arg6);
                v3.append(" AND ");
                v3.append("_url");
                v3.append(" = ");
                v3.append(DatabaseUtils.sqlEscapeString(arg8));
                v6_1 = v2_1.rawQuery(v3.toString(), v1_2);
                if(v6_1 == null) {
                    goto label_60;
                }

                goto label_49;
            }
            catch(Throwable v7) {
            }
            catch(SQLiteException v7_1) {
                goto label_66;
                try {
                label_49:
                    if(v6_1.getCount() <= 0) {
                        goto label_60;
                    }

                    goto label_51;
                }
                catch(Throwable v7) {
                    v1_3 = v6_1;
                }
                catch(SQLiteException v7_1) {
                    v1_3 = v6_1;
                    try {
                    label_66:
                        if(this.c) {
                            v7_1.printStackTrace();
                        }
                    }
                    catch(Throwable v7) {
                        goto label_73;
                    }

                    if((((Cursor)v1_2)) == null) {
                        goto label_71;
                    }

                    try {
                        ((Cursor)v1_2).close();
                        goto label_71;
                    }
                    catch(Throwable v6) {
                        goto label_76;
                    }
                }
            }

        label_73:
            if((((Cursor)v1_2)) != null) {
                try {
                    ((Cursor)v1_2).close();
                label_75:
                    throw v7;
                }
                catch(Throwable v6) {
                    goto label_76;
                }
            }

            goto label_75;
        label_51:
            v0 = true;
        label_60:
            if(v6_1 == null) {
                goto label_71;
            }

            try {
                v6_1.close();
            }
            catch(Throwable v6) {
            label_76:
                __monitor_exit(this);
                throw v6;
            }
        }

    label_71:
        __monitor_exit(this);
        return v0;
    }

    boolean a(long arg6) {
        Cursor v1_3;
        Cursor v6_1;
        __monitor_enter(this);
        boolean v0 = false;
        try {
            this.b.beginTransaction();
            SQLiteDatabase v1_1 = this.b;
            v1_1.execSQL("UPDATE requests SET _status = 902 WHERE _id = " + arg6 + " AND " + "_status" + " != " + 903 + " AND " + "_status" + " != " + 904);
            this.b.setTransactionSuccessful();
            goto label_37;
        }
        catch(Throwable v6) {
        }
        catch(SQLiteException v1) {
            try {
                if(!this.c) {
                    goto label_37;
                }

                v1.printStackTrace();
            }
            catch(Throwable v6) {
                goto label_84;
            }

        label_37:
            String[] v1_2 = null;
            try {
                this.b.endTransaction();
                SQLiteDatabase v2_1 = this.b;
                StringBuilder v3 = new StringBuilder();
                v3.append("SELECT _id FROM requests WHERE _id = ");
                v3.append(arg6);
                v3.append(" AND ");
                v3.append("_status");
                v3.append(" = ");
                v3.append(902);
                v6_1 = v2_1.rawQuery(v3.toString(), v1_2);
                if(v6_1 == null) {
                    goto label_68;
                }

                goto label_57;
            }
            catch(Throwable v7) {
            }
            catch(SQLiteException v7_1) {
                goto label_74;
                try {
                label_57:
                    if(v6_1.getCount() <= 0) {
                        goto label_68;
                    }

                    goto label_59;
                }
                catch(Throwable v7) {
                    v1_3 = v6_1;
                }
                catch(SQLiteException v7_1) {
                    v1_3 = v6_1;
                    try {
                    label_74:
                        if(this.c) {
                            v7_1.printStackTrace();
                        }
                    }
                    catch(Throwable v7) {
                        goto label_81;
                    }

                    if((((Cursor)v1_2)) == null) {
                        goto label_79;
                    }

                    try {
                        ((Cursor)v1_2).close();
                        goto label_79;
                    }
                    catch(Throwable v6) {
                        goto label_84;
                    }
                }
            }

        label_81:
            if((((Cursor)v1_2)) != null) {
                try {
                    ((Cursor)v1_2).close();
                label_83:
                    throw v7;
                }
                catch(Throwable v6) {
                    goto label_84;
                }
            }

            goto label_83;
        label_59:
            v0 = true;
        label_68:
            if(v6_1 == null) {
                goto label_79;
            }

            try {
                v6_1.close();
            }
            catch(Throwable v6) {
            label_84:
                __monitor_exit(this);
                throw v6;
            }
        }

    label_79:
        __monitor_exit(this);
        return v0;
    }

    static a a(Context arg2) {
        a v2_1;
        Class v0 = a.class;
        __monitor_enter(v0);
        try {
            if(a.a == null) {
                goto label_7;
            }

            v2_1 = a.a;
        }
        catch(Throwable v2) {
            goto label_20;
        }

        __monitor_exit(v0);
        return v2_1;
    label_7:
        if(arg2 != null) {
            try {
                a.a = new a(arg2.getApplicationContext());
                v2_1 = a.a;
            }
            catch(Throwable v2) {
                goto label_20;
            }

            __monitor_exit(v0);
            return v2_1;
        }

        try {
            throw new NullPointerException("Context cannot be null");
        }
        catch(Throwable v2) {
        label_20:
            __monitor_exit(v0);
            throw v2;
        }
    }

    String a() {
        return "INSERT INTO requests ( _id, _url, _file_path, _status, _headers, _written_bytes, _file_size, _error, _priority ) VALUES ";
    }

    boolean a(long arg5, long arg7, long arg9) {
        __monitor_enter(this);
        try {
            this.b.beginTransaction();
            SQLiteDatabase v1 = this.b;
            v1.execSQL("UPDATE requests SET _file_size = " + arg9 + ", " + "_written_bytes" + " = " + arg7 + " WHERE " + "_id" + " = " + arg5);
            this.b.setTransactionSuccessful();
            goto label_35;
        }
        catch(Throwable v5) {
        }
        catch(SQLiteException v5_1) {
            try {
                if(!this.c) {
                    goto label_35;
                }

                v5_1.printStackTrace();
                try {
                label_35:
                    this.b.endTransaction();
                }
                catch(SQLiteException v5_1) {
                    try {
                        if(!this.c) {
                            goto label_43;
                        }

                        v5_1.printStackTrace();
                    }
                    catch(Throwable v5) {
                    label_45:
                        __monitor_exit(this);
                        throw v5;
                    }
                }
            }
            catch(Throwable v5) {
                goto label_45;
            }
        }

    label_43:
        __monitor_exit(this);
        return 1;
    }

    boolean a(String arg5, String arg6) {
        __monitor_enter(this);
        try {
            this.b.beginTransaction();
            SQLiteDatabase v1 = this.b;
            v1.execSQL("DELETE FROM requests WHERE _url = " + DatabaseUtils.sqlEscapeString(arg5) + " OR " + "_file_path" + " = " + DatabaseUtils.sqlEscapeString(arg6));
            this.b.setTransactionSuccessful();
            goto label_30;
        }
        catch(Throwable v5) {
        }
        catch(SQLiteException v5_1) {
            try {
                if(!this.c) {
                    goto label_30;
                }

                v5_1.printStackTrace();
                try {
                label_30:
                    this.b.endTransaction();
                }
                catch(SQLiteException v5_1) {
                    try {
                        if(!this.c) {
                            goto label_38;
                        }

                        v5_1.printStackTrace();
                    }
                    catch(Throwable v5) {
                    label_40:
                        __monitor_exit(this);
                        throw v5;
                    }
                }
            }
            catch(Throwable v5) {
                goto label_40;
            }
        }

    label_38:
        __monitor_exit(this);
        return 1;
    }

    boolean a(String arg3) {
        __monitor_enter(this);
        if(arg3 == null) {
            __monitor_exit(this);
            return 0;
        }

        try {
            this.b.beginTransaction();
            this.b.execSQL(arg3);
            this.b.setTransactionSuccessful();
        }
        catch(Throwable v3) {
        }
        catch(Exception v3_1) {
            try {
                if(this.c) {
                    v3_1.printStackTrace();
                }

                throw new b(v3_1.getMessage(), org.telegram.customization.fetch.b.a(v3_1.getMessage()));
            }
            catch(Throwable v3) {
                try {
                    this.b.endTransaction();
                }
                catch(SQLiteException v3_2) {
                    if(this.c) {
                        v3_2.printStackTrace();
                    }

                    throw new b(v3_2.getMessage(), org.telegram.customization.fetch.b.a(v3_2.getMessage()));
                }

                throw v3;
            }
        }

        try {
            this.b.endTransaction();
        }
        catch(Throwable v3) {
        label_53:
            __monitor_exit(this);
            throw v3;
        }
        catch(SQLiteException v3_2) {
            try {
                if(this.c) {
                    v3_2.printStackTrace();
                }

                throw new b(v3_2.getMessage(), org.telegram.customization.fetch.b.a(v3_2.getMessage()));
            label_42:
                goto label_53;
            }
            catch(Throwable v3) {
                goto label_42;
            }
        }

        __monitor_exit(this);
        return 1;
    }

    boolean b(long arg6) {
        Cursor v1_3;
        Cursor v6_1;
        __monitor_enter(this);
        boolean v0 = false;
        try {
            this.b.beginTransaction();
            SQLiteDatabase v1_1 = this.b;
            v1_1.execSQL("UPDATE requests SET _status = 900 WHERE _id = " + arg6 + " AND " + "_status" + " != " + 903 + " AND " + "_status" + " != " + 904);
            this.b.setTransactionSuccessful();
            goto label_37;
        }
        catch(Throwable v6) {
        }
        catch(SQLiteException v1) {
            try {
                if(!this.c) {
                    goto label_37;
                }

                v1.printStackTrace();
            }
            catch(Throwable v6) {
                goto label_84;
            }

        label_37:
            String[] v1_2 = null;
            try {
                this.b.endTransaction();
                SQLiteDatabase v2_1 = this.b;
                StringBuilder v3 = new StringBuilder();
                v3.append("SELECT _id FROM requests WHERE _id = ");
                v3.append(arg6);
                v3.append(" AND ");
                v3.append("_status");
                v3.append(" = ");
                v3.append(900);
                v6_1 = v2_1.rawQuery(v3.toString(), v1_2);
                if(v6_1 == null) {
                    goto label_68;
                }

                goto label_57;
            }
            catch(Throwable v7) {
            }
            catch(SQLiteException v7_1) {
                goto label_74;
                try {
                label_57:
                    if(v6_1.getCount() <= 0) {
                        goto label_68;
                    }

                    goto label_59;
                }
                catch(Throwable v7) {
                    v1_3 = v6_1;
                }
                catch(SQLiteException v7_1) {
                    v1_3 = v6_1;
                    try {
                    label_74:
                        if(this.c) {
                            v7_1.printStackTrace();
                        }
                    }
                    catch(Throwable v7) {
                        goto label_81;
                    }

                    if((((Cursor)v1_2)) == null) {
                        goto label_79;
                    }

                    try {
                        ((Cursor)v1_2).close();
                        goto label_79;
                    }
                    catch(Throwable v6) {
                        goto label_84;
                    }
                }
            }

        label_81:
            if((((Cursor)v1_2)) != null) {
                try {
                    ((Cursor)v1_2).close();
                label_83:
                    throw v7;
                }
                catch(Throwable v6) {
                    goto label_84;
                }
            }

            goto label_83;
        label_59:
            v0 = true;
        label_68:
            if(v6_1 == null) {
                goto label_79;
            }

            try {
                v6_1.close();
            }
            catch(Throwable v6) {
            label_84:
                __monitor_exit(this);
                throw v6;
            }
        }

    label_79:
        __monitor_exit(this);
        return v0;
    }

    String b(long arg3, String arg5, String arg6, int arg7, String arg8, long arg9, long arg11, int arg13, int arg14) {
        return "( " + arg3 + ", " + DatabaseUtils.sqlEscapeString(arg5) + ", " + DatabaseUtils.sqlEscapeString(arg6) + ", " + arg7 + ", " + DatabaseUtils.sqlEscapeString(arg8) + ", " + arg9 + ", " + arg11 + ", " + arg14 + ", " + arg13 + " )";
    }

    String b() {
        return ";";
    }

    boolean c() {
        __monitor_enter(this);
        try {
            this.b.beginTransaction();
            this.b.execSQL("DELETE FROM requests");
            this.b.setTransactionSuccessful();
            goto label_16;
        }
        catch(Throwable v0) {
        }
        catch(SQLiteException v1) {
            try {
                if(!this.c) {
                    goto label_16;
                }

                v1.printStackTrace();
                try {
                label_16:
                    this.b.endTransaction();
                }
                catch(SQLiteException v1) {
                    try {
                        if(!this.c) {
                            goto label_24;
                        }

                        v1.printStackTrace();
                    }
                    catch(Throwable v0) {
                    label_26:
                        __monitor_exit(this);
                        throw v0;
                    }
                }
            }
            catch(Throwable v0) {
                goto label_26;
            }
        }

    label_24:
        __monitor_exit(this);
        return 1;
    }

    boolean c(long arg5) {
        __monitor_enter(this);
        try {
            this.b.beginTransaction();
            SQLiteDatabase v1 = this.b;
            v1.execSQL("DELETE FROM requests WHERE _id = " + arg5);
            this.b.setTransactionSuccessful();
            goto label_21;
        }
        catch(Throwable v5) {
        }
        catch(SQLiteException v5_1) {
            try {
                if(!this.c) {
                    goto label_21;
                }

                v5_1.printStackTrace();
                try {
                label_21:
                    this.b.endTransaction();
                }
                catch(SQLiteException v5_1) {
                    try {
                        if(!this.c) {
                            goto label_29;
                        }

                        v5_1.printStackTrace();
                    }
                    catch(Throwable v5) {
                    label_31:
                        __monitor_exit(this);
                        throw v5;
                    }
                }
            }
            catch(Throwable v5) {
                goto label_31;
            }
        }

    label_29:
        __monitor_exit(this);
        return 1;
    }

    Cursor d() {
        Cursor v1_1;
        __monitor_enter(this);
        String[] v0 = null;
        try {
            v1_1 = this.b.rawQuery("SELECT * FROM requests", v0);
        }
        catch(Throwable v0_1) {
        label_15:
            __monitor_exit(this);
            throw v0_1;
        }
        catch(SQLiteException v1) {
            try {
                if(this.c) {
                    v1.printStackTrace();
                }
            }
            catch(Throwable v0_1) {
                goto label_15;
            }

            __monitor_exit(this);
            return ((Cursor)v0);
        }

        __monitor_exit(this);
        return v1_1;
    }

    boolean d(long arg6) {
        Cursor v1_3;
        Cursor v6_1;
        __monitor_enter(this);
        boolean v0 = false;
        try {
            this.b.beginTransaction();
            SQLiteDatabase v1_1 = this.b;
            v1_1.execSQL("UPDATE requests SET _status = 900, _error = -1 WHERE _id = " + arg6 + " AND " + "_status" + " = " + 904);
            this.b.setTransactionSuccessful();
            goto label_29;
        }
        catch(Throwable v6) {
        }
        catch(SQLiteException v1) {
            try {
                if(!this.c) {
                    goto label_29;
                }

                v1.printStackTrace();
            }
            catch(Throwable v6) {
                goto label_76;
            }

        label_29:
            String[] v1_2 = null;
            try {
                this.b.endTransaction();
                SQLiteDatabase v2_1 = this.b;
                StringBuilder v3 = new StringBuilder();
                v3.append("SELECT _id FROM requests WHERE _id = ");
                v3.append(arg6);
                v3.append(" AND ");
                v3.append("_status");
                v3.append(" = ");
                v3.append(900);
                v6_1 = v2_1.rawQuery(v3.toString(), v1_2);
                if(v6_1 == null) {
                    goto label_60;
                }

                goto label_49;
            }
            catch(Throwable v7) {
            }
            catch(SQLiteException v7_1) {
                goto label_66;
                try {
                label_49:
                    if(v6_1.getCount() <= 0) {
                        goto label_60;
                    }

                    goto label_51;
                }
                catch(Throwable v7) {
                    v1_3 = v6_1;
                }
                catch(SQLiteException v7_1) {
                    v1_3 = v6_1;
                    try {
                    label_66:
                        if(this.c) {
                            v7_1.printStackTrace();
                        }
                    }
                    catch(Throwable v7) {
                        goto label_73;
                    }

                    if((((Cursor)v1_2)) == null) {
                        goto label_71;
                    }

                    try {
                        ((Cursor)v1_2).close();
                        goto label_71;
                    }
                    catch(Throwable v6) {
                        goto label_76;
                    }
                }
            }

        label_73:
            if((((Cursor)v1_2)) != null) {
                try {
                    ((Cursor)v1_2).close();
                label_75:
                    throw v7;
                }
                catch(Throwable v6) {
                    goto label_76;
                }
            }

            goto label_75;
        label_51:
            v0 = true;
        label_60:
            if(v6_1 == null) {
                goto label_71;
            }

            try {
                v6_1.close();
            }
            catch(Throwable v6) {
            label_76:
                __monitor_exit(this);
                throw v6;
            }
        }

    label_71:
        __monitor_exit(this);
        return v0;
    }

    Cursor e(long arg5) {
        Cursor v5_2;
        __monitor_enter(this);
        String[] v0 = null;
        try {
            SQLiteDatabase v1 = this.b;
            StringBuilder v2 = new StringBuilder();
            v2.append("SELECT * FROM requests WHERE _id = ");
            v2.append(arg5);
            v5_2 = v1.rawQuery(v2.toString(), v0);
        }
        catch(Throwable v5) {
        label_20:
            __monitor_exit(this);
            throw v5;
        }
        catch(SQLiteException v5_1) {
            try {
                if(this.c) {
                    v5_1.printStackTrace();
                }
            }
            catch(Throwable v5) {
                goto label_20;
            }

            __monitor_exit(this);
            return ((Cursor)v0);
        }

        __monitor_exit(this);
        return v5_2;
    }

    Cursor e() {
        Cursor v0_1;
        String[] v2;
        __monitor_enter(this);
        try {
            v2 = null;
            v0_1 = this.b.rawQuery("SELECT * FROM requests WHERE _status = 900 AND _priority = 601 LIMIT 1", v2);
            if(v0_1 != null && v0_1.getCount() > 0) {
                goto label_8;
            }

            goto label_10;
        }
        catch(Throwable v0) {
            goto label_18;
        }

    label_8:
        __monitor_exit(this);
        return v0_1;
    label_10:
        if(v0_1 != null) {
            try {
                v0_1.close();
            label_12:
                v0_1 = this.b.rawQuery("SELECT * FROM requests WHERE _status = 900 LIMIT 1", v2);
                goto label_15;
            }
            catch(Throwable v0) {
            label_18:
                __monitor_exit(this);
                throw v0;
            }
        }

        goto label_12;
    label_15:
        __monitor_exit(this);
        return v0_1;
    }

    boolean f() {
        boolean v1;
        __monitor_enter(this);
        try {
            Cursor v0_1 = this.b.rawQuery("SELECT _id FROM requests WHERE _status = 900 LIMIT 1", null);
            v1 = false;
            if(v0_1 != null && v0_1.getCount() > 0) {
                v1 = true;
            }

            if(v0_1 != null) {
                v0_1.close();
            }
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v1;
    }

    void g() {
        __monitor_enter(this);
        try {
            this.b.beginTransaction();
            this.b.execSQL("UPDATE requests SET _status = 900 WHERE _status = 901");
            this.b.setTransactionSuccessful();
            goto label_15;
        }
        catch(Throwable v0) {
        }
        catch(SQLiteException v0_1) {
            try {
                if(!this.c) {
                    goto label_15;
                }

                v0_1.printStackTrace();
                try {
                label_15:
                    this.b.endTransaction();
                }
                catch(SQLiteException v0_1) {
                    try {
                        if(!this.c) {
                            goto label_22;
                        }

                        v0_1.printStackTrace();
                    }
                    catch(Throwable v0) {
                    label_24:
                        __monitor_exit(this);
                        throw v0;
                    }
                }
            }
            catch(Throwable v0) {
                goto label_24;
            }
        }

    label_22:
        __monitor_exit(this);
    }

    void h() {
        Cursor v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.b.rawQuery("SELECT _id, _file_path FROM requests WHERE _status = 903", null);
            if(v0_1 != null) {
                goto label_8;
            }
        }
        catch(Throwable v0) {
            goto label_61;
        }

        __monitor_exit(this);
        return;
        try {
        label_8:
            if(v0_1.getCount() >= 1) {
                goto label_14;
            }

            v0_1.close();
        }
        catch(Throwable v0) {
            goto label_61;
        }

        __monitor_exit(this);
        return;
        try {
        label_14:
            this.b.beginTransaction();
            v0_1.moveToFirst();
            while(!v0_1.isAfterLast()) {
                String v1_1 = v0_1.getString(v0_1.getColumnIndex("_file_path"));
                if(v1_1 != null && !f.e(v1_1)) {
                    long v1_2 = v0_1.getLong(v0_1.getColumnIndex("_id"));
                    SQLiteDatabase v3 = this.b;
                    v3.execSQL("UPDATE requests SET _status = 904, _error = -111 WHERE _id = " + v1_2);
                }

                v0_1.moveToNext();
            }

            this.b.setTransactionSuccessful();
            goto label_45;
        }
        catch(Throwable v0) {
        label_61:
            __monitor_exit(this);
            throw v0;
        }
        catch(SQLiteException v1) {
            try {
                if(!this.c) {
                    goto label_45;
                }

                v1.printStackTrace();
            }
            catch(Throwable v0) {
                goto label_61;
            }

            try {
            label_45:
                this.b.endTransaction();
                goto label_47;
            }
            catch(Throwable v1_3) {
            }
            catch(SQLiteException v1) {
                try {
                    if(!this.c) {
                        goto label_47;
                    }

                    v1.printStackTrace();
                    goto label_47;
                }
                catch(Throwable v1_3) {
                    try {
                        v0_1.close();
                        throw v1_3;
                    label_47:
                        v0_1.close();
                    }
                    catch(Throwable v0) {
                        goto label_61;
                    }
                }
            }

            __monitor_exit(this);
            return;
        }
    }

    public void onCreate(SQLiteDatabase arg2) {
        arg2.execSQL("CREATE TABLE requests ( _id INTEGER PRIMARY KEY NOT NULL, _url TEXT NOT NULL, _file_path TEXT NOT NULL, _status INTEGER NOT NULL, _headers TEXT NOT NULL, _written_bytes INTEGER NOT NULL, _file_size INTEGER NOT NULL, _error INTEGER NOT NULL, _priority INTEGER NOT NULL, unique( _file_path ) )");
    }

    public void onUpgrade(SQLiteDatabase arg1, int arg2, int arg3) {
        if(arg2 != 1) {
        }
        else {
            arg1.execSQL("CREATE UNIQUE INDEX table_unique ON requests ( _file_path)");
        }
    }
}

