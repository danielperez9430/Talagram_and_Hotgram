package com.google.android.gms.internal.clearcut;

import android.content.ContentResolver;
import android.database.ContentObserver;
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
import java.util.concurrent.ConcurrentHashMap;

public final class zzab {
    private final Uri uri;
    private static final ConcurrentHashMap zzde;
    private final ContentResolver zzdf;
    private final ContentObserver zzdg;
    private final Object zzdh;
    private volatile Map zzdi;
    private final Object zzdj;
    private final List zzdk;
    private static final String[] zzdl;

    static {
        zzab.zzde = new ConcurrentHashMap();
        zzab.zzdl = new String[]{"key", "value"};
    }

    private zzab(ContentResolver arg2, Uri arg3) {
        super();
        this.zzdh = new Object();
        this.zzdj = new Object();
        this.zzdk = new ArrayList();
        this.zzdf = arg2;
        this.uri = arg3;
        this.zzdg = new zzac(this, null);
    }

    public static zzab zza(ContentResolver arg3, Uri arg4) {
        Object v0 = zzab.zzde.get(arg4);
        if(v0 == null) {
            zzab v0_1 = new zzab(arg3, arg4);
            Object v3 = zzab.zzde.putIfAbsent(arg4, v0_1);
            if(v3 == null) {
                v0_1.zzdf.registerContentObserver(v0_1.uri, false, v0_1.zzdg);
            }
            else {
                v0 = v3;
            }
        }

        return ((zzab)v0);
    }

    static void zza(zzab arg0) {
        arg0.zzj();
    }

    public final Map zzg() {
        Map v0 = zzae.zza("gms:phenotype:phenotype_flag:debug_disable_caching", false) ? this.zzi() : this.zzdi;
        if(v0 == null) {
            Object v1 = this.zzdh;
            __monitor_enter(v1);
            try {
                v0 = this.zzdi;
                if(v0 == null) {
                    v0 = this.zzi();
                    this.zzdi = v0;
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

    public final void zzh() {
        Object v0 = this.zzdh;
        __monitor_enter(v0);
        Map v1 = null;
        try {
            this.zzdi = v1;
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

    private final Map zzi() {
        Cursor v1;
        HashMap v0;
        try {
            v0 = new HashMap();
            v1 = this.zzdf.query(this.uri, zzab.zzdl, null, null, null);
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

    private final void zzj() {
        Object v0 = this.zzdj;
        __monitor_enter(v0);
        try {
            Iterator v1_1 = this.zzdk.iterator();
            while(v1_1.hasNext()) {
                v1_1.next().zzk();
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

