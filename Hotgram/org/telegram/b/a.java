package org.telegram.b;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;
import android.util.LongSparseArray;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.telegram.customization.Model.ContactChangeLog;
import org.telegram.customization.Model.DialogStatus;
import org.telegram.customization.Model.Favourite;
import org.telegram.customization.Model.MarkModel;
import org.telegram.customization.service.c;
import org.telegram.messenger.ApplicationLoader;

public class a extends SQLiteOpenHelper {
    private static SQLiteDatabase a;
    private static volatile LongSparseArray b;
    private HashMap c;

    static {
    }

    public a(Context arg4) {
        super(arg4, "favourites", null, 13);
        this.c = new HashMap();
        if(a.a == null) {
            a.a = this.getWritableDatabase();
        }

        if(a.b == null) {
            this.h();
        }
    }

    static String a(String arg2, String arg3, String arg4, String arg5) {
        arg2 = "ALTER TABLE " + arg2 + " ADD " + arg3 + " " + arg4;
        if(!TextUtils.isEmpty(((CharSequence)arg5))) {
            arg2 = arg2 + " DEFAULT " + arg5;
        }

        return arg2;
    }

    private void a(SQLiteDatabase arg2) {
        try {
            arg2.execSQL("CREATE TABLE IF NOT EXISTS TABLE_DIALOG_MARK(chat_id INTEGER PRIMARY KEY ,msg_id INTEGER DEFAULT 0)");
        }
        catch(Exception v2) {
            v2.printStackTrace();
        }
    }

    public DialogStatus a(long arg16, boolean arg18) {
        DialogStatus v0_4;
        Cursor v2;
        int v14;
        int v12;
        boolean v10;
        Object v1 = null;
        Object v0 = a.b.get(arg16, v1);
        if(v0 == null) {
            if(arg18) {
            }
            else {
                a.a.beginTransaction();
                int v0_1 = 5;
                try {
                    String[] v4 = new String[v0_1];
                    v10 = false;
                    v4[0] = "id";
                    v4[1] = "has_hotgram";
                    v12 = 2;
                    v4[v12] = "invite_sent";
                    int v13 = 3;
                    v4[v13] = "is_filter";
                    v14 = 4;
                    v4[v14] = "last_time_update";
                    v2 = a.a.query("TABLE_DIALOG_STATUS", v4, "id=? ", new String[]{String.valueOf(arg16)}, null, null, null);
                    if(v2 == null) {
                        goto label_74;
                    }
                }
                catch(Throwable v0_2) {
                    v2 = ((Cursor)v1);
                    goto label_90;
                }
                catch(Exception v0_3) {
                    v2 = ((Cursor)v1);
                    goto label_84;
                }

                try {
                    if(v2.moveToFirst()) {
                        a.a.setTransactionSuccessful();
                        v0_4 = new DialogStatus();
                        v0_4.setDialogId(v2.getLong(0));
                        boolean v3 = v2.getInt(1) == 1 ? true : false;
                        v0_4.setHasHotgram(v3);
                        v3 = v2.getInt(v12) == 1 ? true : false;
                        v0_4.setInviteSent(v3);
                        if(v2.getInt(v13) == 1) {
                            v10 = true;
                        }

                        v0_4.setFilter(v10);
                        v0_4.setLastUpdate(((long)v2.getInt(v14)));
                        a.b.put(v0_4.getDialogId(), v0_4);
                        if(v2 == null) {
                            goto label_69;
                        }

                        goto label_68;
                    }

                    goto label_74;
                }
                catch(Throwable v0_2) {
                }
                catch(Exception v0_3) {
                    try {
                    label_84:
                        v0_3.printStackTrace();
                        if(v2 != null) {
                        }
                        else {
                            goto label_76;
                        }
                    }
                    catch(Throwable v0_2) {
                        goto label_90;
                    }

                    v2.close();
                    try {
                    label_76:
                        a.a.endTransaction();
                    }
                    catch(Exception ) {
                    }

                    goto label_88;
                }

            label_90:
                if(v2 != null) {
                    v2.close();
                }

                try {
                    a.a.endTransaction();
                    goto label_94;
                }
                catch(Exception ) {
                label_94:
                    throw v0_2;
                }

            label_68:
                v2.close();
                try {
                label_69:
                    a.a.endTransaction();
                    return v0_4;
                }
                catch(Exception ) {
                    return v0_4;
                }

            label_74:
                if(v2 == null) {
                    goto label_76;
                }

                v2.close();
                goto label_76;
            label_88:
                return ((DialogStatus)v1);
            }
        }

        return ((DialogStatus)v0);
    }

    public ArrayList a() {
        Cursor v1_2;
        int v13;
        ArrayList v0 = new ArrayList();
        a.a.beginTransaction();
        int v1 = 3;
        Cursor v2 = null;
        try {
            String[] v5 = new String[v1];
            v5[0] = "id";
            v5[1] = "chat_id";
            v13 = 2;
            v5[v13] = "msg_id";
            v1_2 = a.a.query("tbl_favs_messages", v5, "", new String[0], null, null, null);
            if(v1_2 == null) {
                goto label_47;
            }

            goto label_25;
        }
        catch(Throwable v0_1) {
        }
        catch(Exception v1_1) {
            goto label_55;
            try {
            label_25:
                while(v1_2.moveToNext()) {
                    v0.add(new Favourite(v1_2.getLong(0), v1_2.getLong(1), v1_2.getLong(v13)));
                }

                a.a.setTransactionSuccessful();
                goto label_47;
            }
            catch(Throwable v0_1) {
                v2 = v1_2;
                goto label_60;
            }
            catch(Exception v2_1) {
                Exception v14 = v2_1;
                v2 = v1_2;
                v1_1 = v14;
            }

            try {
            label_55:
                v1_1.printStackTrace();
                if(v2 == null) {
                    goto label_49;
                }
            }
            catch(Throwable v0_1) {
                goto label_60;
            }
        }

        v2.close();
        goto label_49;
    label_60:
        if(v2 != null) {
            v2.close();
        }

        try {
            a.a.endTransaction();
            goto label_64;
        }
        catch(Exception ) {
        label_64:
            throw v0_1;
        }

    label_47:
        if(v1_2 == null) {
            goto label_49;
        }

        v1_2.close();
        try {
        label_49:
            a.a.endTransaction();
            return v0;
        }
        catch(Exception ) {
            return v0;
        }
    }

    public ArrayList a(int arg18) {
        Cursor v3_2;
        String v6;
        int v15;
        int v14;
        ArrayList v1 = new ArrayList();
        a.a.beginTransaction();
        int v0 = 5;
        Cursor v2 = null;
        try {
            String[] v5 = new String[v0];
            v5[0] = "id";
            v5[1] = "chat_id";
            v14 = 2;
            v5[v14] = "change_type";
            v15 = 3;
            v5[v15] = "previous_name";
            v5[4] = "date";
            if(arg18 != 0) {
                v6 = "change_type = ?";
            }
            else {
                CharSequence v6_1 = ((CharSequence)v2);
            }

            String[] v3 = new String[]{String.valueOf(arg18)};
            SQLiteDatabase v0_3 = a.a;
            String v4 = "TABLE_CONTACT_CHANGE_LOG";
            String[] v7 = !TextUtils.isEmpty(((CharSequence)v6)) ? v3 : ((String[])v2);
            SQLiteDatabase v3_1 = v0_3;
            v0 = 4;
            v3_2 = v3_1.query(v4, v5, v6, v7, null, null, "date DESC ", "200");
            if(v3_2 == null) {
                goto label_67;
            }

            goto label_46;
        }
        catch(Throwable v0_1) {
        }
        catch(Exception v0_2) {
            goto label_75;
            try {
            label_46:
                a.a.setTransactionSuccessful();
                while(true) {
                    if(v3_2.moveToNext()) {
                        v1.add(new ContactChangeLog(v3_2.getInt(0), v3_2.getLong(1), v3_2.getInt(v14), v3_2.getString(v15), v3_2.getLong(v0)));
                        continue;
                    }

                    goto label_67;
                }
            }
            catch(Throwable v0_1) {
                v2 = v3_2;
            }
            catch(Exception v0_2) {
                v2 = v3_2;
                try {
                label_75:
                    v0_2.printStackTrace();
                    if(v2 == null) {
                        goto label_69;
                    }
                }
                catch(Throwable v0_1) {
                    goto label_80;
                }

                v2.close();
                goto label_69;
            }
        }

    label_80:
        if(v2 != null) {
            v2.close();
        }

        try {
            a.a.endTransaction();
            goto label_84;
        }
        catch(Exception ) {
        label_84:
            throw v0_1;
        }

    label_67:
        if(v3_2 == null) {
            goto label_69;
        }

        v3_2.close();
        try {
        label_69:
            a.a.endTransaction();
            return v1;
        }
        catch(Exception ) {
            return v1;
        }
    }

    public Favourite a(long arg12) {
        Favourite v13_1;
        Cursor v12;
        a.a.beginTransaction();
        int v0 = 2;
        Cursor v1 = null;
        try {
            String[] v4 = new String[v0];
            v4[0] = "id";
            v4[1] = "chat_id";
            v12 = a.a.query("tbl_favs", v4, "chat_id=?", new String[]{String.valueOf(arg12)}, null, null, null);
            if(v12 == null) {
                goto label_37;
            }
        }
        catch(Throwable v13) {
            goto label_43;
        }
        catch(Exception ) {
            v12 = v1;
            goto label_49;
        }

        try {
            if(v12.moveToFirst()) {
                a.a.setTransactionSuccessful();
                v13_1 = new Favourite(v12.getLong(1));
                if(v12 == null) {
                    goto label_31;
                }

                goto label_30;
            }

            goto label_37;
        }
        catch(Throwable v13) {
            v1 = v12;
        }
        catch(Exception ) {
        label_49:
            if(v12 != null) {
                v12.close();
                goto label_39;
            }
            else {
                try {
                label_39:
                    a.a.endTransaction();
                }
                catch(Exception ) {
                }

                goto label_52;
            }
        }

    label_43:
        if(v1 != null) {
            v1.close();
        }

        try {
            a.a.endTransaction();
            goto label_47;
        }
        catch(Exception ) {
        label_47:
            throw v13;
        }

    label_30:
        v12.close();
        try {
        label_31:
            a.a.endTransaction();
            return v13_1;
        }
        catch(Exception ) {
            return v13_1;
        }

    label_37:
        if(v12 == null) {
            goto label_39;
        }

        v12.close();
        goto label_39;
    label_52:
        return ((Favourite)v1);
    }

    public Favourite a(Long arg24, long arg25) {
        Cursor v1_1;
        Favourite v14;
        Cursor v2;
        int v13;
        int v12;
        a.a.beginTransaction();
        int v0 = 4;
        Favourite v1 = null;
        try {
            String[] v4 = new String[v0];
            v4[0] = "id";
            v4[1] = "chat_id";
            v12 = 2;
            v4[v12] = "msg_id";
            v13 = 3;
            v4[v13] = "cloud_id";
            String[] v6 = new String[v12];
            v6[0] = String.valueOf(arg24);
            v6[1] = String.valueOf(arg25);
            v2 = a.a.query("tbl_favs_messages", v4, "chat_id=? AND msg_id=?", v6, null, null, null);
            if(v2 == null) {
                goto label_49;
            }
        }
        catch(Throwable v0_1) {
            goto label_65;
        }
        catch(Exception v0_2) {
            v2 = ((Cursor)v1);
            goto label_58;
        }

        try {
            if(v2.moveToFirst()) {
                a.a.setTransactionSuccessful();
                v14 = new Favourite(v2.getLong(0), v2.getLong(1), v2.getLong(v12), v2.getLong(v13));
                goto label_41;
            }

            goto label_49;
        }
        catch(Throwable v0_1) {
        label_64:
            v1_1 = v2;
        }
        catch(Exception v0_2) {
            try {
            label_58:
                v0_2.printStackTrace();
                if(v2 != null) {
                }
                else {
                    goto label_51;
                }
            }
            catch(Throwable v0_1) {
                goto label_64;
            }

            v2.close();
            try {
            label_51:
                a.a.endTransaction();
            }
            catch(Exception ) {
            }

            return v1;
        }

    label_65:
        if(v1_1 != null) {
            v1_1.close();
        }

        try {
            a.a.endTransaction();
            goto label_69;
        }
        catch(Exception ) {
        label_69:
            throw v0_1;
        }

    label_41:
        Favourite v0_3 = v14;
        if(v2 != null) {
            v2.close();
        }

        try {
            a.a.endTransaction();
            return v0_3;
        }
        catch(Exception ) {
            return v0_3;
        }

    label_49:
        if(v2 == null) {
            goto label_51;
        }

        v2.close();
        goto label_51;
        return v1;
    }

    public void a(long arg6, int arg8) {
        if(this.a(arg6, ((long)arg8))) {
            a.a.beginTransaction();
            a.a.delete("TABLE_DIALOG_MARK", "chat_id = ?", new String[]{String.valueOf(arg6)});
            a.a.setTransactionSuccessful();
            a.a.endTransaction();
            this.i().remove(Long.valueOf(arg6));
        }
    }

    public boolean a(long arg4, long arg6) {
        boolean v1 = false;
        if(this.i().containsKey(Long.valueOf(arg4))) {
            if(this.i().get(Long.valueOf(arg4)).longValue() == arg6) {
                v1 = true;
            }

            return v1;
        }

        if(ApplicationLoader.databaseHandler.e(arg4) == arg6) {
            return 1;
        }

        return 0;
    }

    public void a(Long arg6) {
        a.a.beginTransaction();
        a.a.delete("tbl_favs", "chat_id = ?", new String[]{String.valueOf(arg6)});
        a.a.setTransactionSuccessful();
        a.a.endTransaction();
    }

    public void a(Long arg6, Long arg7) {
        a.a.beginTransaction();
        a.a.delete("tbl_favs_messages", "chat_id = ? AND msg_id = ?", new String[]{String.valueOf(arg6), String.valueOf(arg7)});
        a.a.setTransactionSuccessful();
        a.a.endTransaction();
    }

    public void a(ArrayList arg6) {
        String v6 = TextUtils.join(",", ((Iterable)arg6));
        a.a.beginTransaction();
        a.a.delete("tbl_favs_messages", "cloud_id IN (?)", new String[]{v6});
        a.a.setTransactionSuccessful();
        a.a.endTransaction();
    }

    public void a(ContactChangeLog arg5) {
        if(this.b(arg5)) {
            a.a.beginTransaction();
            ContentValues v0 = new ContentValues();
            v0.put("chat_id", Long.valueOf(arg5.getChatId()));
            v0.put("change_type", Integer.valueOf(arg5.getType()));
            v0.put("date", Long.valueOf(arg5.getDate()));
            v0.put("previous_name", arg5.getPreviousName());
            a.a.insert("TABLE_CONTACT_CHANGE_LOG", null, v0);
            a.a.setTransactionSuccessful();
            a.a.endTransaction();
        }
    }

    public void a(DialogStatus arg8) {
        DialogStatus v0 = this.b(arg8.getDialogId());
        a.a.beginTransaction();
        ContentValues v1 = new ContentValues();
        if(v0 != null) {
            v1.put("id", Long.valueOf(arg8.getDialogId()));
            v1.put("has_hotgram", Boolean.valueOf(v0.isHasHotgram()));
            v1.put("invite_sent", Boolean.valueOf(v0.isInviteSent()));
            v1.put("is_filter", Boolean.valueOf(arg8.isFilter()));
            v1.put("last_time_update", Long.valueOf(arg8.getLastUpdate()));
            a.a.update("TABLE_DIALOG_STATUS", v1, "id=? ", new String[]{String.valueOf(v0.getDialogId())});
        }
        else {
            v1.put("id", Long.valueOf(arg8.getDialogId()));
            v1.put("has_hotgram", Boolean.valueOf(arg8.isHasHotgram()));
            v1.put("invite_sent", Boolean.valueOf(arg8.isInviteSent()));
            v1.put("is_filter", Boolean.valueOf(arg8.isFilter()));
            v1.put("last_time_update", Long.valueOf(arg8.getLastUpdate()));
            a.a.insert("TABLE_DIALOG_STATUS", null, v1);
        }

        a.b.put(arg8.getDialogId(), arg8);
        a.a.setTransactionSuccessful();
        a.a.endTransaction();
    }

    public void a(Favourite arg5) {
        a.a.beginTransaction();
        ContentValues v0 = new ContentValues();
        v0.put("chat_id", Long.valueOf(arg5.getChatID()));
        a.a.insert("tbl_favs", null, v0);
        a.a.setTransactionSuccessful();
        a.a.endTransaction();
    }

    public void a(MarkModel arg7) {
        long v0 = this.e(arg7.getChatId());
        a.a.beginTransaction();
        ContentValues v2 = new ContentValues();
        if(v0 != 0) {
            v2.put("chat_id", Long.valueOf(arg7.getChatId()));
            v2.put("msg_id", Long.valueOf(arg7.getMessageId()));
            a.a.update("TABLE_DIALOG_MARK", v2, "chat_id=? ", new String[]{String.valueOf(arg7.getChatId())});
        }
        else {
            v2.put("chat_id", Long.valueOf(arg7.getChatId()));
            v2.put("msg_id", Long.valueOf(arg7.getMessageId()));
            a.a.insert("TABLE_DIALOG_MARK", null, v2);
        }

        a.a.setTransactionSuccessful();
        a.a.endTransaction();
        this.i().put(Long.valueOf(arg7.getChatId()), Long.valueOf(arg7.getMessageId()));
    }

    private void b(SQLiteDatabase arg5) {
        try {
            Log.d("LEE", "CREATE_DIALOG_STATUS_TABLE" + "CREATE TABLE IF NOT EXISTS TABLE_DIALOG_STATUS(id INTEGER PRIMARY KEY ,has_hotgram INTEGER DEFAULT 0,invite_sent INTEGER DEFAULT 0 ,is_filter INTEGER DEFAULT 0 ,last_time_update INTEGER DEFAULT 0)");
            arg5.execSQL("CREATE TABLE IF NOT EXISTS TABLE_DIALOG_STATUS(id INTEGER PRIMARY KEY ,has_hotgram INTEGER DEFAULT 0,invite_sent INTEGER DEFAULT 0 ,is_filter INTEGER DEFAULT 0 ,last_time_update INTEGER DEFAULT 0)");
        }
        catch(Exception v5) {
            v5.printStackTrace();
        }
    }

    private boolean b(ContactChangeLog arg15) {
        Cursor v2_1;
        if(arg15 == null) {
            return 0;
        }

        a.a.beginTransaction();
        Cursor v1 = null;
        int v2 = 2;
        try {
            String[] v6 = new String[]{"id"};
            String v7 = "date = ? AND chat_id = ? ";
            String[] v8 = new String[v2];
            v8[0] = String.valueOf(arg15.getDate());
            v8[1] = String.valueOf(arg15.getChatId());
            if(!a.a.isDbLockedByOtherThreads()) {
                v1 = a.a.query("TABLE_CONTACT_CHANGE_LOG", v6, v7, v8, null, null, "date DESC ");
            }

            if(v1 != null) {
                a.a.setTransactionSuccessful();
                if(!v1.moveToNext()) {
                    goto label_39;
                }

                goto label_34;
            }

            goto label_39;
        }
        catch(Throwable v15) {
        }
        catch(Exception v4) {
            try {
                v4.printStackTrace();
                if(v1 == null) {
                    goto label_47;
                }

                goto label_46;
            }
            catch(Throwable v15) {
                if(v1 != null) {
                    v1.close();
                }

                a.a.endTransaction();
                throw v15;
            }
        }

    label_34:
        if(v1 != null) {
            v1.close();
        }

        a.a.endTransaction();
        return 0;
    label_39:
        if(v1 != null) {
        label_46:
            v1.close();
        }

    label_47:
        a.a.endTransaction();
        a.a.beginTransaction();
        try {
            String[] v7_1 = new String[]{"previous_name"};
            String[] v9 = new String[v2];
            v9[0] = String.valueOf(arg15.getType());
            v9[1] = String.valueOf(arg15.getChatId());
            v2_1 = a.a.query("TABLE_CONTACT_CHANGE_LOG", v7_1, "change_type = ? AND chat_id = ? ", v9, null, null, "date DESC ", "1");
            if(v2_1 == null) {
                goto label_90;
            }

            goto label_70;
        }
        catch(Throwable v15) {
        }
        catch(Exception v15_1) {
            goto label_98;
            try {
            label_70:
                a.a.setTransactionSuccessful();
                do {
                    if(v2_1.moveToNext()) {
                        String v1_1 = v2_1.getString(0);
                        if(v1_1 == null) {
                            continue;
                        }

                        if(!v1_1.equals(arg15.getPreviousName())) {
                            continue;
                        }

                        break;
                    }

                    goto label_90;
                }
                while(true);
            }
            catch(Throwable v15) {
                goto label_85;
            }
            catch(Exception v15_1) {
                goto label_88;
            }

            if(v2_1 != null) {
                v2_1.close();
            }

            try {
                a.a.endTransaction();
                return 0;
            }
            catch(Exception ) {
                return 0;
            }

        label_88:
            v1 = v2_1;
            try {
            label_98:
                v15_1.printStackTrace();
                if(v1 == null) {
                    goto label_92;
                }
            }
            catch(Throwable v15) {
                goto label_103;
            }
        }

        v1.close();
        goto label_92;
    label_85:
        v1 = v2_1;
    label_103:
        if(v1 != null) {
            v1.close();
        }

        try {
            a.a.endTransaction();
            goto label_107;
        }
        catch(Exception ) {
        label_107:
            throw v15;
        }

    label_90:
        if(v2_1 == null) {
            goto label_92;
        }

        v2_1.close();
        try {
        label_92:
            a.a.endTransaction();
            return 1;
        }
        catch(Exception ) {
            return 1;
        }
    }

    public DialogStatus b(long arg2) {
        return this.a(arg2, false);
    }

    public void b(long arg6, boolean arg8) {
        a.a.beginTransaction();
        ContentValues v0 = new ContentValues();
        DialogStatus v1 = this.b(arg6);
        if(v1 != null) {
            v0.put("id", Long.valueOf(v1.getDialogId()));
            v0.put("has_hotgram", Boolean.valueOf(v1.isHasHotgram()));
            v0.put("invite_sent", Boolean.valueOf(arg8));
            a.a.update("TABLE_DIALOG_STATUS", v0, "id=? ", new String[]{String.valueOf(arg6)});
            a.b.put(v1.getDialogId(), v1);
        }

        a.a.setTransactionSuccessful();
        a.a.endTransaction();
    }

    public void b(Favourite arg5) {
        a.a.beginTransaction();
        ContentValues v0 = new ContentValues();
        v0.put("chat_id", Long.valueOf(arg5.getChatID()));
        v0.put("msg_id", Long.valueOf(arg5.getMsg_id()));
        v0.put("cloud_id", Long.valueOf(arg5.getCloudId()));
        a.a.insert("tbl_favs_messages", null, v0);
        a.a.setTransactionSuccessful();
        a.a.endTransaction();
    }

    public boolean b() {
        Cursor v3;
        a.a.beginTransaction();
        boolean v1 = false;
        Cursor v2 = null;
        try {
            v3 = a.a.query("tbl_favs_messages", new String[]{"id"}, "", new String[0], null, null, null);
            if(v3 == null) {
                goto label_29;
            }

            goto label_17;
        }
        catch(Throwable v0) {
        }
        catch(Exception v0_1) {
            goto label_37;
            try {
            label_17:
                a.a.setTransactionSuccessful();
                if(v3.getCount() <= 0) {
                    goto label_29;
                }

                goto label_21;
            }
            catch(Throwable v0) {
                v2 = v3;
            }
            catch(Exception v0_1) {
                v2 = v3;
                try {
                label_37:
                    v0_1.printStackTrace();
                    if(v2 == null) {
                        goto label_31;
                    }
                }
                catch(Throwable v0) {
                    goto label_42;
                }

                v2.close();
                try {
                label_31:
                    a.a.endTransaction();
                }
                catch(Exception ) {
                }

                return v1;
            }
        }

    label_42:
        if(v2 != null) {
            v2.close();
        }

        try {
            a.a.endTransaction();
            goto label_46;
        }
        catch(Exception ) {
        label_46:
            throw v0;
        }

    label_21:
        v1 = true;
    label_29:
        if(v3 == null) {
            goto label_31;
        }

        v3.close();
        goto label_31;
        return v1;
    }

    private void c(SQLiteDatabase arg2) {
        try {
            arg2.execSQL("CREATE TABLE IF NOT EXISTS tbl_favs_messages(id INTEGER PRIMARY KEY AUTOINCREMENT,chat_id INTEGER,msg_id INTEGER,cloud_id INTEGER DEFAULT -100 )");
        }
        catch(Exception v2) {
            v2.printStackTrace();
        }
    }

    public static boolean c(long arg2) {
        DialogStatus v2 = ApplicationLoader.databaseHandler.a(Math.abs(arg2), true);
        if(v2 != null) {
            return v2.isFilter();
        }

        return 0;
    }

    public List c() {
        Cursor v1_1;
        ArrayList v0 = new ArrayList();
        a.a.beginTransaction();
        int v1 = 2;
        Cursor v2 = null;
        try {
            String[] v5 = new String[v1];
            v5[0] = "id";
            v5[1] = "chat_id";
            v1_1 = a.a.query("tbl_favs", v5, "", new String[0], null, null, null);
            if(v1_1 == null) {
                goto label_38;
            }
        }
        catch(Throwable v0_1) {
            v1_1 = v2;
            goto label_47;
        }
        catch(Exception ) {
            v1_1 = v2;
            goto label_53;
        }

        try {
            if(v1_1.moveToFirst()) {
                do {
                    Favourite v2_1 = new Favourite();
                    v2_1.setID(((long)Integer.parseInt(v1_1.getString(0))));
                    v2_1.setChatID(v1_1.getLong(1));
                    ((List)v0).add(v2_1);
                }
                while(v1_1.moveToNext());
            }

        label_38:
            a.a.setTransactionSuccessful();
            if(v1_1 == null) {
                goto label_42;
            }

            goto label_41;
        label_37:
            goto label_47;
        }
        catch(Throwable v0_1) {
            goto label_37;
        }
        catch(Exception ) {
        }

    label_53:
        if(v1_1 == null) {
            goto label_42;
        }

        v1_1.close();
        goto label_42;
    label_47:
        if(v1_1 != null) {
            v1_1.close();
        }

        try {
            a.a.endTransaction();
            goto label_51;
        }
        catch(Exception ) {
        label_51:
            throw v0_1;
        }

    label_41:
        v1_1.close();
        try {
        label_42:
            a.a.endTransaction();
            goto label_56;
        }
        catch(Exception ) {
        label_56:
            return ((List)v0_1);
        }
    }

    public void close() {
        try {
            a.a.close();
            return;
        }
        catch(Exception ) {
            return;
        }
    }

    public static long d(long arg2) {
        DialogStatus v2 = ApplicationLoader.databaseHandler.a(Math.abs(arg2), true);
        if(v2 != null) {
            return v2.getLastUpdate();
        }

        return 0;
    }

    private void d(SQLiteDatabase arg2) {
        try {
            arg2.execSQL("CREATE TABLE IF NOT EXISTS tbl_favs(id INTEGER PRIMARY KEY AUTOINCREMENT,chat_id INTEGER)");
        }
        catch(Exception v2) {
            v2.printStackTrace();
        }
    }

    public void d() {
        a.a.beginTransaction();
        a.a.delete("TABLE_CONTACT_CHANGE_LOG", " 1 ", null);
        a.a.setTransactionSuccessful();
        a.a.endTransaction();
    }

    private void e(SQLiteDatabase arg2) {
        try {
            arg2.execSQL("CREATE TABLE IF NOT EXISTS TABLE_DOWNLOAD_QUEUE(id INTEGER PRIMARY KEY AUTOINCREMENT,chat_id INTEGER,msg_id INTEGER)");
        }
        catch(Exception v2) {
            v2.printStackTrace();
        }
    }

    public long e(long arg13) {
        long v2;
        Cursor v1_2;
        if(this.i().containsKey(Long.valueOf(arg13))) {
            return this.i().get(Long.valueOf(arg13)).longValue();
        }

        a.a.beginTransaction();
        Cursor v0 = null;
        int v1 = 2;
        try {
            String[] v4 = new String[v1];
            v4[0] = "chat_id";
            v4[1] = "msg_id";
            v1_2 = a.a.query("TABLE_DIALOG_MARK", v4, "chat_id=? ", new String[]{String.valueOf(arg13)}, null, null, null);
            if(v1_2 == null) {
                goto label_53;
            }

            goto label_31;
        }
        catch(Throwable v13) {
        }
        catch(Exception v1_1) {
            goto label_61;
            try {
            label_31:
                if(v1_2.moveToFirst()) {
                    a.a.setTransactionSuccessful();
                    v2 = v1_2.getLong(1);
                    this.i().put(Long.valueOf(arg13), Long.valueOf(v2));
                    if(v1_2 == null) {
                        goto label_42;
                    }

                    goto label_41;
                }

                goto label_53;
            }
            catch(Throwable v13) {
                v0 = v1_2;
            }
            catch(Exception v0_1) {
                Cursor v11 = v1_2;
                v1_1 = v0_1;
                v0 = v11;
                try {
                label_61:
                    v1_1.printStackTrace();
                    if(v0 == null) {
                        goto label_55;
                    }
                }
                catch(Throwable v13) {
                    goto label_71;
                }

                v0.close();
                try {
                label_55:
                    a.a.endTransaction();
                }
                catch(Exception ) {
                }

                goto label_65;
            }
        }

    label_71:
        if(v0 != null) {
            v0.close();
        }

        try {
            a.a.endTransaction();
            goto label_75;
        }
        catch(Exception ) {
        label_75:
            throw v13;
        }

    label_41:
        v1_2.close();
        try {
        label_42:
            a.a.endTransaction();
            return v2;
        }
        catch(Exception ) {
            return v2;
        }

    label_53:
        if(v1_2 == null) {
            goto label_55;
        }

        v1_2.close();
        goto label_55;
    label_65:
        this.i().put(Long.valueOf(arg13), Long.valueOf(0));
        return 0;
    }

    public void e() {
        a.a.beginTransaction();
        ContentValues v0 = new ContentValues();
        v0.put("is_filter", Boolean.valueOf(false));
        v0.put("last_time_update", Integer.valueOf(0));
        a.a.update("TABLE_DIALOG_STATUS", v0, null, null);
        a.a.setTransactionSuccessful();
        a.a.endTransaction();
    }

    static SQLiteDatabase f() {
        return a.a;
    }

    private void f(SQLiteDatabase arg2) {
        try {
            arg2.execSQL("CREATE TABLE IF NOT EXISTS TABLE_CONTACT_CHANGE_LOG(id INTEGER PRIMARY KEY AUTOINCREMENT,chat_id INTEGER,change_type INTEGER, date INTEGER, previous_name TEXT)");
        }
        catch(Exception v2) {
            v2.printStackTrace();
        }
    }

    static LongSparseArray g() {
        return a.b;
    }

    private void h() {
        a.b = new LongSparseArray();
        new Thread() {
            public void run() {
                Cursor v2;
                int v14;
                int v13;
                int v12;
                int v0;
                Cursor v1;
                try {
                    a.f().beginTransaction();
                    v1 = null;
                    v0 = 5;
                }
                catch(Exception ) {
                    return;
                }

                try {
                    String[] v4 = new String[v0];
                    v4[0] = "id";
                    v4[1] = "has_hotgram";
                    v12 = 2;
                    v4[v12] = "invite_sent";
                    v13 = 3;
                    v4[v13] = "is_filter";
                    v14 = 4;
                    v4[v14] = "last_time_update";
                    v2 = a.f().query("TABLE_DIALOG_STATUS", v4, "", new String[0], null, null, null);
                    if(v2 == null) {
                        goto label_67;
                    }

                    goto label_29;
                }
                catch(Throwable v0_1) {
                }
                catch(Exception v0_2) {
                    goto label_76;
                    try {
                    label_29:
                        a.f().setTransactionSuccessful();
                        while(true) {
                            if(v2.moveToNext()) {
                                DialogStatus v0_3 = new DialogStatus();
                                v0_3.setDialogId(v2.getLong(0));
                                boolean v1_1 = v2.getInt(1) == 1 ? true : false;
                                v0_3.setHasHotgram(v1_1);
                                v1_1 = v2.getInt(v12) == 1 ? true : false;
                                v0_3.setInviteSent(v1_1);
                                v1_1 = v2.getInt(v13) == 1 ? true : false;
                                v0_3.setFilter(v1_1);
                                v0_3.setLastUpdate(((long)v2.getInt(v14)));
                                a.g().put(v0_3.getDialogId(), v0_3);
                                continue;
                            }

                            goto label_67;
                        }
                    }
                    catch(Throwable v0_1) {
                    }
                    catch(Exception v0_2) {
                        v1 = v2;
                        try {
                        label_76:
                            v0_2.printStackTrace();
                            if(v1 == null) {
                                goto label_69;
                            }
                        }
                        catch(Throwable v0_1) {
                            v2 = v1;
                            goto label_80;
                        }

                        try {
                            v1.close();
                            goto label_69;
                        }
                        catch(Exception ) {
                            return;
                        }
                    }
                }

                try {
                label_80:
                    if(v2 != null) {
                        v2.close();
                    }
                }
                catch(Exception ) {
                    return;
                }

                try {
                    a.f().endTransaction();
                    goto label_84;
                }
                catch(Exception ) {
                    try {
                    label_84:
                        throw v0_1;
                    }
                    catch(Exception ) {
                        return;
                    }
                }

            label_67:
                if(v2 == null) {
                    goto label_69;
                }

                try {
                    v2.close();
                label_69:
                    a.f().endTransaction();
                    return;
                }
                catch(Exception ) {
                    return;
                }
            }
        }.start();
    }

    private HashMap i() {
        if(this.c == null) {
            this.c = new HashMap();
        }

        return this.c;
    }

    public void onCreate(SQLiteDatabase arg1) {
        this.c(arg1);
        this.d(arg1);
        this.e(arg1);
        this.f(arg1);
        this.b(arg1);
        this.a(arg1);
    }

    public void onUpgrade(SQLiteDatabase arg3, int arg4, int arg5) {
        this.c(arg3);
        this.d(arg3);
        this.e(arg3);
        this.f(arg3);
        switch(arg4) {
            case 1: 
            case 2: {
                goto label_6;
            }
            case 3: {
                goto label_21;
            }
            case 4: 
            case 5: {
                goto label_22;
            }
            case 6: 
            case 7: 
            case 8: 
            case 9: 
            case 10: {
                goto label_23;
            }
            case 11: {
                goto label_32;
            }
            case 12: {
                goto label_33;
            }
        }

        return;
        try {
        label_6:
            arg3.execSQL(a.a("tbl_favs_messages", "cloud_id", "INTEGER", "-100"));
            ApplicationLoader.applicationContext.startService(new Intent(ApplicationLoader.applicationContext, c.class));
        }
        catch(Exception v4) {
            v4.printStackTrace();
        }

    label_21:
        this.f(arg3);
    label_22:
        this.b(arg3);
        try {
        label_23:
            arg3.execSQL(a.a("TABLE_DIALOG_STATUS", "is_filter", "INTEGER", "0"));
        }
        catch(Exception v4) {
            v4.printStackTrace();
        }

    label_32:
        this.a(arg3);
        try {
        label_33:
            arg3.execSQL(a.a("TABLE_DIALOG_STATUS", "last_time_update", "INTEGER", "0"));
        }
        catch(Exception v3) {
            v3.printStackTrace();
        }
    }
}

