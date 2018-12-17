package com.google.android.gms.phenotype;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class zza {
    private final Uri uri;
    private static final ConcurrentHashMap zzg;
    private final ContentResolver zzh;
    private final ContentObserver zzi;
    private final Object zzj;
    private volatile Map zzk;
    private static final String[] zzl;

    static {
        zza.zzg = new ConcurrentHashMap();
        zza.zzl = new String[]{"key", "value"};
    }

    private zza(ContentResolver arg2, Uri arg3) {
        super();
        this.zzj = new Object();
        this.zzh = arg2;
        this.uri = arg3;
        this.zzi = new zzb(this, null);
    }

    public static zza zza(ContentResolver arg3, Uri arg4) {
        zza v0_1;
        Object v0 = zza.zzg.get(arg4);
        if(v0 == null) {
            v0_1 = new zza(arg3, arg4);
            Object v3 = zza.zzg.putIfAbsent(arg4, v0_1);
            if(v3 == null) {
                v0_1.zzh.registerContentObserver(v0_1.uri, false, v0_1.zzi);
            }
            else {
                v0 = v3;
            }
        }

        return v0_1;
    }

    public final Map zza() {
        Map v0 = PhenotypeFlag.zza("gms:phenotype:phenotype_flag:debug_disable_caching", false) ? this.zzc() : this.zzk;
        if(v0 == null) {
            Object v1 = this.zzj;
            __monitor_enter(v1);
            try {
                v0 = this.zzk;
                if(v0 == null) {
                    v0 = this.zzc();
                    this.zzk = v0;
                }

                __monitor_exit(v1);
                return v0;
            label_17:
                __monitor_exit(v1);
            }
            catch(Throwable v0_1) {
                goto label_17;
            }

            throw v0_1;
        }

        return v0;
    }

    public final void zzb() {
        Object v0 = this.zzj;
        __monitor_enter(v0);
        Map v1 = null;
        try {
            this.zzk = v1;
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

    private final Map zzc() {
        HashMap v0 = new HashMap();
        Cursor v1 = this.zzh.query(this.uri, zza.zzl, null, null, null);
        if(v1 != null) {
            try {
                while(v1.moveToNext()) {
                    ((Map)v0).put(v1.getString(0), v1.getString(1));
                }
            }
            catch(Throwable v0_1) {
                v1.close();
                throw v0_1;
            }

            v1.close();
        }

        return ((Map)v0);
    }
}

