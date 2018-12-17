package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;

public final class zzal extends zzf {
    private final zzam zzalq;
    private boolean zzalr;

    zzal(zzbt arg3) {
        super(arg3);
        this.zzalq = new zzam(this, ((zzco)this).getContext(), "google_app_measurement_local.db");
    }

    public final Context getContext() {
        return super.getContext();
    }

    @VisibleForTesting private final SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase v1 = null;
        if(this.zzalr) {
            return v1;
        }

        SQLiteDatabase v0 = this.zzalq.getWritableDatabase();
        if(v0 == null) {
            this.zzalr = true;
            return v1;
        }

        return v0;
    }

    public final void resetAnalyticsData() {
        ((zzco)this).zzgb();
        ((zzco)this).zzaf();
        try {
            int v0_1 = this.getWritableDatabase().delete("messages", null, null);
            if(v0_1 > 0) {
                ((zzco)this).zzgo().zzjl().zzg("Reset local analytics data. records", Integer.valueOf(v0_1));
            }
        }
        catch(SQLiteException v0) {
            ((zzco)this).zzgo().zzjd().zzg("Error resetting local analytics data. error", v0);
            return;
        }
    }

    private final boolean zza(int arg18, byte[] arg19) {
        Cursor v12;
        long v10;
        SQLiteDatabase v9;
        zzal v1 = this;
        ((zzco)this).zzgb();
        ((zzco)this).zzaf();
        boolean v2 = false;
        if(v1.zzalr) {
            return 0;
        }

        ContentValues v3 = new ContentValues();
        v3.put("type", Integer.valueOf(arg18));
        v3.put("entry", arg19);
        int v4 = 5;
        int v5 = 0;
        int v6 = 5;
    label_18:
        if(v5 >= v4) {
            goto label_142;
        }

        String[] v7 = null;
        try {
            v9 = this.getWritableDatabase();
            if(v9 != null) {
                goto label_33;
            }
        }
        catch(SQLiteDatabaseLockedException ) {
            goto label_111;
        }
        catch(Throwable v0) {
            goto label_88;
        }
        catch(SQLiteFullException v0_1) {
            goto label_121;
        }
        catch(SQLiteException v0_2) {
            goto label_92;
        }

        try {
            v1.zzalr = true;
            if(v9 != null) {
                goto label_25;
            }

            return v2;
        }
        catch(Throwable v0) {
            goto label_136;
        }
        catch(SQLiteDatabaseLockedException ) {
            goto label_112;
        }
        catch(SQLiteException v0_2) {
            goto label_28;
        }
        catch(SQLiteFullException v0_1) {
            goto label_32;
        }

    label_25:
        v9.close();
        return v2;
        try {
        label_33:
            v9.beginTransaction();
            v10 = 0;
            v12 = v9.rawQuery("select count(1) from messages", v7);
            if(v12 != null) {
                goto label_38;
            }

            goto label_49;
        }
        catch(Throwable v0) {
            goto label_136;
        }
        catch(SQLiteDatabaseLockedException ) {
            goto label_112;
        }
        catch(SQLiteException v0_2) {
            goto label_28;
        }
        catch(SQLiteFullException v0_1) {
            goto label_32;
        }

        try {
        label_38:
            if(v12.moveToFirst()) {
                v10 = v12.getLong(((int)v2));
            }

        label_49:
            long v13 = 100000;
            if(v10 >= v13) {
                ((zzco)this).zzgo().zzjd().zzbx("Data loss, local db full");
                v13 = v13 - v10 + 1;
                String[] v11 = new String[1];
                v11[((int)v2)] = Long.toString(v13);
                v10 = ((long)v9.delete("messages", "rowid in (select rowid from messages order by rowid asc limit ?)", v11));
                if(v10 != v13) {
                    ((zzco)this).zzgo().zzjd().zzd("Different delete count than expected in local db. expected, received, difference", Long.valueOf(v13), Long.valueOf(v10), Long.valueOf(v13 - v10));
                }
            }

            v9.insertOrThrow("messages", ((String)v7), v3);
            v9.setTransactionSuccessful();
            v9.endTransaction();
            if(v12 != null) {
                goto label_81;
            }

            goto label_82;
        }
        catch(Throwable v0) {
            goto label_43;
        }
        catch(SQLiteException v0_2) {
            goto label_45;
        }
        catch(SQLiteFullException v0_1) {
            goto label_47;
        }
        catch(SQLiteDatabaseLockedException ) {
            goto label_85;
        }

    label_81:
        v12.close();
    label_82:
        if(v9 != null) {
            v9.close();
        }

        return 1;
    label_88:
        v9 = ((SQLiteDatabase)v7);
        v12 = ((Cursor)v9);
        goto label_137;
    label_92:
        v12 = ((Cursor)v7);
        goto label_93;
    label_111:
        v9 = ((SQLiteDatabase)v7);
        goto label_112;
    label_121:
        v9 = ((SQLiteDatabase)v7);
        goto label_122;
    label_142:
        ((zzco)this).zzgo().zzjg().zzbx("Failed to write entry to local database");
        return 0;
    label_28:
        v12 = ((Cursor)v7);
        goto label_29;
    label_32:
        goto label_122;
    label_29:
        SQLiteDatabase v7_1 = v9;
        goto label_93;
    label_43:
        goto label_137;
    label_45:
        goto label_29;
    label_47:
        Cursor v7_2 = v12;
        goto label_122;
    label_85:
        v7_2 = v12;
        goto label_112;
    label_93:
        if(v7_1 != null) {
            try {
                if(v7_1.inTransaction()) {
                    v7_1.endTransaction();
                }

            label_101:
                ((zzco)this).zzgo().zzjd().zzg("Error writing entry to local database", v0_2);
                v1.zzalr = true;
                if(v12 != null) {
                    goto label_107;
                }

                goto label_108;
            }
            catch(Throwable v0) {
                goto label_99;
            }
        }

        goto label_101;
    label_107:
        v12.close();
    label_108:
        if(v7_1 == null) {
        }
        else {
            v7_1.close();
            goto label_131;
        label_99:
            v9 = v7_1;
            goto label_137;
        }

    label_131:
        ++v5;
        v2 = false;
        v4 = 5;
        goto label_18;
    label_112:
        v10 = ((long)v6);
        try {
            SystemClock.sleep(v10);
            v6 += 20;
            if((((Cursor)v7)) != null) {
                goto label_116;
            }

            goto label_117;
        }
        catch(Throwable v0) {
            goto label_136;
        }

    label_116:
        ((Cursor)v7).close();
    label_117:
        if(v9 == null) {
            goto label_131;
        }

    label_118:
        v9.close();
        goto label_131;
        try {
        label_122:
            ((zzco)this).zzgo().zzjd().zzg("Error writing entry to local database", v0_1);
            v1.zzalr = true;
            if((((Cursor)v7)) != null) {
                goto label_128;
            }

            goto label_129;
        }
        catch(Throwable v0) {
            goto label_136;
        }

    label_128:
        ((Cursor)v7).close();
    label_129:
        if(v9 == null) {
            goto label_131;
        }

        goto label_118;
    label_136:
        v12 = ((Cursor)v7);
    label_137:
        if(v12 != null) {
            v12.close();
        }

        if(v9 != null) {
            v9.close();
        }

        throw v0;
    }

    public final boolean zza(zzad arg4) {
        Parcel v0 = Parcel.obtain();
        arg4.writeToParcel(v0, 0);
        byte[] v4 = v0.marshall();
        v0.recycle();
        if(v4.length > 131072) {
            ((zzco)this).zzgo().zzjg().zzbx("Event is too long for local database. Sending event directly to service");
            return 0;
        }

        return this.zza(0, v4);
    }

    public final boolean zza(zzfh arg4) {
        Parcel v0 = Parcel.obtain();
        arg4.writeToParcel(v0, 0);
        byte[] v4 = v0.marshall();
        v0.recycle();
        if(v4.length > 131072) {
            ((zzco)this).zzgo().zzjg().zzbx("User property too long for local database. Sending directly to service");
            return 0;
        }

        return this.zza(1, v4);
    }

    public final void zzaf() {
        super.zzaf();
    }

    public final Clock zzbx() {
        return super.zzbx();
    }

    public final boolean zzc(zzl arg3) {
        ((zzco)this).zzgm();
        byte[] v3 = zzfk.zza(((Parcelable)arg3));
        if(v3.length > 131072) {
            ((zzco)this).zzgo().zzjg().zzbx("Conditional user property too long for local database. Sending directly to service");
            return 0;
        }

        return this.zza(2, v3);
    }

    public final void zzga() {
        super.zzga();
    }

    public final void zzgb() {
        super.zzgb();
    }

    public final void zzgc() {
        super.zzgc();
    }

    public final zza zzgd() {
        return super.zzgd();
    }

    public final zzcs zzge() {
        return super.zzge();
    }

    public final zzaj zzgf() {
        return super.zzgf();
    }

    public final zzdr zzgg() {
        return super.zzgg();
    }

    public final zzdo zzgh() {
        return super.zzgh();
    }

    public final zzal zzgi() {
        return super.zzgi();
    }

    public final zzeq zzgj() {
        return super.zzgj();
    }

    public final zzx zzgk() {
        return super.zzgk();
    }

    public final zzan zzgl() {
        return super.zzgl();
    }

    public final zzfk zzgm() {
        return super.zzgm();
    }

    public final zzbo zzgn() {
        return super.zzgn();
    }

    public final zzap zzgo() {
        return super.zzgo();
    }

    public final zzba zzgp() {
        return super.zzgp();
    }

    public final zzn zzgq() {
        return super.zzgq();
    }

    public final zzk zzgr() {
        return super.zzgr();
    }

    protected final boolean zzgt() {
        return 0;
    }

    public final List zzr(int arg20) {
        String v17;
        String v16;
        String v14;
        SQLiteDatabase v9_1;
        String[] v13;
        String v12;
        String[] v11;
        String v10;
        Cursor v9;
        Object v0_4;
        Parcel v13_1;
        byte[] v12_1;
        long v10_1;
        Cursor v2_1;
        SQLiteDatabase v4_1;
        SQLiteDatabase v2_2;
        SQLiteDatabase v15;
        zzal v1 = this;
        ((zzco)this).zzaf();
        ((zzco)this).zzgb();
        List v2 = null;
        if(v1.zzalr) {
            return v2;
        }

        ArrayList v3 = new ArrayList();
        if(!((zzco)this).getContext().getDatabasePath("google_app_measurement_local.db").exists()) {
            return ((List)v3);
        }

        int v4 = 5;
        int v6 = 0;
        int v7 = 5;
        while(true) {
            if(v6 >= v4) {
                goto label_245;
            }

            try {
                v15 = this.getWritableDatabase();
                if(v15 != null) {
                    goto label_35;
                }
            }
            catch(Throwable v0) {
                v2_2 = null;
                goto label_185;
            }
            catch(SQLiteException v0_1) {
                v2_2 = null;
                goto label_189;
            }
            catch(SQLiteDatabaseLockedException ) {
                v2_1 = null;
                v4_1 = null;
                goto label_209;
            }
            catch(SQLiteFullException v0_2) {
                v2_1 = null;
                v15 = null;
                goto label_224;
            }

            try {
                v1.zzalr = true;
                if(v15 == null) {
                    return v2;
                }

                goto label_25;
            }
            catch(Throwable v0) {
            }
            catch(SQLiteDatabaseLockedException ) {
                try {
                label_60:
                    v9 = v9_1.query(v10, v11, v12, v13, v14, ((String)v15), v16, v17);
                    v10_1 = -1;
                    goto label_62;
                }
                catch(Throwable v0) {
                }
                catch(SQLiteException v0_1) {
                label_189:
                    v9 = null;
                    goto label_190;
                }
                catch(SQLiteDatabaseLockedException ) {
                label_176:
                    v4_1 = v2_2;
                    v2_1 = null;
                    goto label_209;
                }
                catch(SQLiteFullException v0_2) {
                    v15 = v2_2;
                label_181:
                    v2_1 = null;
                    goto label_224;
                }

            label_185:
                v9 = null;
                break;
                try {
                    while(true) {
                    label_62:
                        if(!v9.moveToNext()) {
                            goto label_135;
                        }

                        v10_1 = v9.getLong(0);
                        int v0_3 = v9.getInt(1);
                        v12_1 = v9.getBlob(v4);
                        if(v0_3 != 0) {
                            goto label_88;
                        }

                        v13_1 = Parcel.obtain();
                        break;
                    }
                }
                catch(SQLiteFullException v0_2) {
                    goto label_160;
                }
                catch(SQLiteDatabaseLockedException ) {
                    goto label_156;
                }
                catch(SQLiteException v0_1) {
                    goto label_155;
                }
                catch(Throwable v0) {
                    goto label_196;
                }

                try {
                    v13_1.unmarshall(v12_1, 0, v12_1.length);
                    v13_1.setDataPosition(0);
                    v0_4 = zzad.CREATOR.createFromParcel(v13_1);
                }
                catch(ParseException ) {
                    goto label_80;
                }
                catch(Throwable v0) {
                    goto label_79;
                }

                try {
                    v13_1.recycle();
                    goto label_75;
                }
                catch(Throwable v0) {
                }
                catch(SQLiteException v0_1) {
                }
                catch(SQLiteDatabaseLockedException ) {
                }
                catch(SQLiteFullException v0_2) {
                }
                catch(Throwable v0) {
                label_79:
                    try {
                        v13_1.recycle();
                        throw v0;
                    label_75:
                        if(v0_4 == null) {
                            goto label_62;
                        }

                        goto label_76;
                    label_88:
                        if(v0_3 != 1) {
                            goto label_109;
                        }

                        v13_1 = Parcel.obtain();
                    }
                    catch(SQLiteFullException v0_2) {
                        goto label_160;
                    }
                    catch(SQLiteDatabaseLockedException ) {
                        goto label_156;
                    }
                    catch(SQLiteException v0_1) {
                        goto label_155;
                    }
                    catch(Throwable v0) {
                        goto label_196;
                    }

                    try {
                        v13_1.unmarshall(v12_1, 0, v12_1.length);
                        v13_1.setDataPosition(0);
                        v0_4 = zzfh.CREATOR.createFromParcel(v13_1);
                    }
                    catch(ParseException ) {
                        goto label_99;
                    }
                    catch(Throwable v0) {
                        goto label_98;
                    }

                    try {
                        v13_1.recycle();
                        goto label_105;
                    }
                    catch(Throwable v0) {
                    }
                    catch(SQLiteException v0_1) {
                    }
                    catch(SQLiteDatabaseLockedException ) {
                    }
                    catch(SQLiteFullException v0_2) {
                    }
                    catch(Throwable v0) {
                    label_98:
                        try {
                            v13_1.recycle();
                            throw v0;
                        label_105:
                            if(v0_4 == null) {
                                goto label_62;
                            }

                            goto label_76;
                        label_109:
                            if(v0_3 != v4) {
                                goto label_130;
                            }

                            v13_1 = Parcel.obtain();
                        }
                        catch(SQLiteFullException v0_2) {
                            goto label_160;
                        }
                        catch(SQLiteDatabaseLockedException ) {
                            goto label_156;
                        }
                        catch(SQLiteException v0_1) {
                            goto label_155;
                        }
                        catch(Throwable v0) {
                            goto label_196;
                        }

                        try {
                            v13_1.unmarshall(v12_1, 0, v12_1.length);
                            v13_1.setDataPosition(0);
                            v0_4 = zzl.CREATOR.createFromParcel(v13_1);
                        }
                        catch(ParseException ) {
                            goto label_120;
                        }
                        catch(Throwable v0) {
                            goto label_119;
                        }

                        try {
                            v13_1.recycle();
                            goto label_126;
                        }
                        catch(Throwable v0) {
                        }
                        catch(SQLiteException v0_1) {
                        }
                        catch(SQLiteDatabaseLockedException ) {
                        }
                        catch(SQLiteFullException v0_2) {
                        }
                        catch(Throwable v0) {
                        label_119:
                            try {
                                v13_1.recycle();
                                throw v0;
                            label_126:
                                if(v0_4 == null) {
                                    goto label_62;
                                }

                            label_76:
                                ((List)v3).add(v0_4);
                                goto label_62;
                            label_130:
                                ((zzco)this).zzgo().zzjd().zzbx("Unknown record type in local database");
                                goto label_62;
                            label_135:
                                if(v2_2.delete("messages", "rowid <= ?", new String[]{Long.toString(v10_1)}) < ((List)v3).size()) {
                                    ((zzco)this).zzgo().zzjd().zzbx("Fewer entries removed from local database than expected");
                                }

                                v2_2.setTransactionSuccessful();
                                v2_2.endTransaction();
                                if(v9 == null) {
                                    goto label_151;
                                }

                                goto label_150;
                            }
                            catch(Throwable v0) {
                            label_196:
                                break;
                            }
                            catch(SQLiteException v0_1) {
                            label_155:
                                goto label_190;
                            label_150:
                                v9.close();
                            label_151:
                                if(v2_2 != null) {
                                    v2_2.close();
                                }

                                return ((List)v3);
                                try {
                                label_224:
                                    ((zzco)this).zzgo().zzjd().zzg("Error reading entries from local database", v0_2);
                                    v1.zzalr = true;
                                    if((((Cursor)v2)) == null) {
                                        goto label_231;
                                    }
                                }
                                catch(Throwable v0) {
                                    v9 = ((Cursor)v2);
                                    v2_2 = v15;
                                    break;
                                }

                                ((Cursor)v2).close();
                            label_231:
                                if(v15 == null) {
                                    goto label_233;
                                }

                                v15.close();
                                goto label_233;
                            label_209:
                                long v8 = ((long)v7);
                                try {
                                    SystemClock.sleep(v8);
                                    v7 += 20;
                                    if(v2_1 == null) {
                                        goto label_214;
                                    }
                                }
                                catch(Throwable v0) {
                                    v9 = v2_1;
                                    v2_2 = v4_1;
                                    break;
                                }

                                v2_1.close();
                            label_214:
                                if(v4_1 == null) {
                                    goto label_233;
                                }

                                v4_1.close();
                                goto label_233;
                            label_190:
                                if(v2_2 != null) {
                                    try {
                                        if(v2_2.inTransaction()) {
                                            v2_2.endTransaction();
                                        }

                                    label_197:
                                        ((zzco)this).zzgo().zzjd().zzg("Error reading entries from local database", v0_1);
                                        v1.zzalr = true;
                                        if(v9 != null) {
                                            goto label_203;
                                        }

                                        goto label_204;
                                    }
                                    catch(Throwable v0) {
                                        goto label_196;
                                    }
                                }

                                goto label_197;
                            label_203:
                                v9.close();
                            label_204:
                                if(v2_2 != null) {
                                    v2_2.close();
                                }

                            label_233:
                                ++v6;
                                v2 = null;
                                v4 = 5;
                                continue;
                            }
                            catch(SQLiteDatabaseLockedException ) {
                            label_156:
                                v4_1 = v2_2;
                                v2_1 = v9;
                                goto label_209;
                            }
                            catch(SQLiteFullException v0_2) {
                            label_160:
                                v15 = v2_2;
                                v2_1 = v9;
                                goto label_224;
                            }
                        }
                        catch(ParseException ) {
                            try {
                            label_120:
                                ((zzco)this).zzgo().zzjd().zzbx("Failed to load user property from local database");
                            }
                            catch(Throwable v0) {
                                goto label_119;
                            }

                            try {
                                v13_1.recycle();
                                v0_4 = null;
                                goto label_126;
                            }
                            catch(SQLiteFullException v0_2) {
                                goto label_160;
                            }
                            catch(SQLiteDatabaseLockedException ) {
                                goto label_156;
                            }
                            catch(SQLiteException v0_1) {
                                goto label_155;
                            }
                            catch(Throwable v0) {
                                goto label_196;
                            }
                        }
                    }
                    catch(ParseException ) {
                        try {
                        label_99:
                            ((zzco)this).zzgo().zzjd().zzbx("Failed to load user property from local database");
                        }
                        catch(Throwable v0) {
                            goto label_98;
                        }

                        try {
                            v13_1.recycle();
                            v0_4 = null;
                            goto label_105;
                        }
                        catch(SQLiteFullException v0_2) {
                            goto label_160;
                        }
                        catch(SQLiteDatabaseLockedException ) {
                            goto label_156;
                        }
                        catch(SQLiteException v0_1) {
                            goto label_155;
                        }
                        catch(Throwable v0) {
                            goto label_196;
                        }
                    }
                }
                catch(ParseException ) {
                    try {
                    label_80:
                        ((zzco)this).zzgo().zzjd().zzbx("Failed to load event from local database");
                    }
                    catch(Throwable v0) {
                        goto label_79;
                    }

                    try {
                        v13_1.recycle();
                        goto label_62;
                    }
                    catch(SQLiteFullException v0_2) {
                        goto label_160;
                    }
                    catch(SQLiteDatabaseLockedException ) {
                        goto label_156;
                    }
                    catch(SQLiteException v0_1) {
                        goto label_155;
                    }
                    catch(Throwable v0) {
                        goto label_196;
                    }
                }
            }
            catch(SQLiteException v0_1) {
                v9 = ((Cursor)v2);
                v2_2 = v15;
                goto label_190;
            label_25:
                v15.close();
                return v2;
                try {
                label_35:
                    v15.beginTransaction();
                    v10 = "messages";
                    v11 = new String[]{"rowid", "type", "entry"};
                    v12 = null;
                    v13 = null;
                    v9_1 = v15;
                    v4 = 2;
                    v14 = null;
                    v2_2 = v15;
                    v15 = null;
                    v16 = "rowid asc";
                    v17 = Integer.toString(100);
                    goto label_60;
                }
                catch(Throwable v0) {
                    v2_2 = v15;
                    goto label_185;
                }
                catch(SQLiteException v0_1) {
                    v2_2 = v15;
                    goto label_189;
                }
                catch(SQLiteDatabaseLockedException ) {
                    v2_2 = v15;
                    goto label_176;
                }
                catch(SQLiteFullException v0_2) {
                    goto label_181;
                }
            }
            catch(SQLiteFullException v0_2) {
                goto label_224;
            }
        }

        if(v9 != null) {
            v9.close();
        }

        if(v2_2 != null) {
            v2_2.close();
        }

        throw v0;
    label_245:
        ((zzco)this).zzgo().zzjg().zzbx("Failed to read events from database in reasonable time");
        return null;
    }
}

