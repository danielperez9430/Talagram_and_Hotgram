package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class zzsi {
    private final Uri uri;
    private static final Object zzbqp;
    private static final Map zzbqq;
    private final ContentResolver zzbqr;
    private final Object zzbqs;
    private volatile Map zzbqt;
    private final Object zzbqu;
    private final List zzbqv;
    private static final String[] zzbqw;

    static {
        zzsi.zzbqp = new Object();
        zzsi.zzbqq = new HashMap();
        zzsi.zzbqw = new String[]{"key", "value"};
    }

    private zzsi(ContentResolver arg3, Uri arg4) {
        super();
        this.zzbqs = new Object();
        this.zzbqu = new Object();
        this.zzbqv = new ArrayList();
        this.zzbqr = arg3;
        this.uri = arg4;
        this.zzbqr.registerContentObserver(arg4, false, new zzsj(this, null));
    }

    public static zzsi zza(ContentResolver arg2, Uri arg3) {
        Object v0 = zzsi.zzbqp;
        __monitor_enter(v0);
        try {
            Object v1 = zzsi.zzbqq.get(arg3);
            if(v1 == null) {
                zzsi v1_1 = new zzsi(arg2, arg3);
                zzsi.zzbqq.put(arg3, v1_1);
            }

            __monitor_exit(v0);
            return ((zzsi)v1);
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_12;
        }

        throw v2;
    }

    static void zza(zzsi arg0) {
        arg0.zztc();
    }

    public final Map zzsz() {
        Map v0 = zzsl.zzd("gms:phenotype:phenotype_flag:debug_disable_caching", false) ? this.zztb() : this.zzbqt;
        if(v0 == null) {
            Object v1 = this.zzbqs;
            __monitor_enter(v1);
            try {
                v0 = this.zzbqt;
                if(v0 == null) {
                    v0 = this.zztb();
                    this.zzbqt = v0;
                }

                __monitor_exit(v1);
                goto label_19;
            label_17:
                __monitor_exit(v1);
            }
            catch(Throwable v0_1) {
                goto label_17;
            }

            throw v0_1;
        }

    label_19:
        if(v0 != null) {
            return v0;
        }

        return Collections.emptyMap();
    }

    public final void zzta() {
        Object v0 = this.zzbqs;
        __monitor_enter(v0);
        Map v1 = null;
        try {
            this.zzbqt = v1;
            __monitor_exit(v0);
            return;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v1_1) {
            goto label_7;
        }

        throw v1_1;
    }

    private final Map zztb() {
        Cursor v1;
        HashMap v0;
        try {
            v0 = new HashMap();
            v1 = this.zzbqr.query(this.uri, zzsi.zzbqw, null, null, null);
            if(v1 != null) {
                goto label_10;
            }

            goto label_23;
        }
        catch(SQLiteException ) {
            goto label_24;
        }

        try {
            while(true) {
            label_10:
                if(v1.moveToNext()) {
                    ((Map)v0).put(v1.getString(0), v1.getString(1));
                    continue;
                }
                else {
                    goto label_18;
                }
            }
        }
        catch(Throwable v0_1) {
            try {
                v1.close();
                throw v0_1;
            label_18:
                v1.close();
            }
            catch(SQLiteException ) {
            label_24:
                Log.e("ConfigurationContentLoader", "PhenotypeFlag unable to load ContentProvider, using default values");
                return null;
            }
        }

    label_23:
        return ((Map)v0);
    }

    private final void zztc() {
        Object v0 = this.zzbqu;
        __monitor_enter(v0);
        try {
            Iterator v1_1 = this.zzbqv.iterator();
            while(v1_1.hasNext()) {
                v1_1.next().zztd();
            }

            __monitor_exit(v0);
            return;
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_12;
        }

        throw v1;
    }
}

