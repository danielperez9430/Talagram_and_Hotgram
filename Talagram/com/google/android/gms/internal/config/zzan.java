package com.google.android.gms.internal.config;

import android.content.Context;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class zzan implements Runnable {
    private final Context mContext;
    private final zzar zzaj;
    private final zzao zzat;
    private final zzao zzau;
    private final zzao zzav;

    public zzan(Context arg1, zzao arg2, zzao arg3, zzao arg4, zzar arg5) {
        super();
        this.mContext = arg1;
        this.zzat = arg2;
        this.zzau = arg3;
        this.zzav = arg4;
        this.zzaj = arg5;
    }

    public final void run() {
        zzaw v0 = new zzaw();
        if(this.zzat != null) {
            v0.zzbp = zzan.zza(this.zzat);
        }

        if(this.zzau != null) {
            v0.zzbq = zzan.zza(this.zzau);
        }

        if(this.zzav != null) {
            v0.zzbr = zzan.zza(this.zzav);
        }

        if(this.zzaj != null) {
            zzau v1 = new zzau();
            v1.zzbk = this.zzaj.getLastFetchStatus();
            v1.zzbl = this.zzaj.isDeveloperModeEnabled();
            v0.zzbs = v1;
        }

        if(this.zzaj != null && this.zzaj.zzr() != null) {
            ArrayList v1_1 = new ArrayList();
            Map v2 = this.zzaj.zzr();
            Iterator v3 = v2.keySet().iterator();
            while(v3.hasNext()) {
                Object v4 = v3.next();
                if(v2.get(v4) == null) {
                    continue;
                }

                zzax v5 = new zzax();
                v5.namespace = ((String)v4);
                v5.zzbv = v2.get(v4).zzo();
                v5.resourceId = v2.get(v4).getResourceId();
                ((List)v1_1).add(v5);
            }

            v0.zzbt = ((List)v1_1).toArray(new zzax[((List)v1_1).size()]);
        }

        byte[] v1_2 = new byte[((zzbh)v0).zzah()];
        int v2_1 = v1_2.length;
        try {
            zzaz v2_2 = zzaz.zzb(v1_2, 0, v2_1);
            ((zzbh)v0).zza(v2_2);
            v2_2.zzac();
        }
        catch(IOException v0_1) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", ((Throwable)v0_1));
        }

        try {
            FileOutputStream v0_2 = this.mContext.openFileOutput("persisted_config", 0);
            v0_2.write(v1_2);
            v0_2.close();
            return;
        }
        catch(IOException v0_1) {
            Log.e("AsyncPersisterTask", "Could not persist config.", ((Throwable)v0_1));
            return;
        }
    }

    private static zzas zza(zzao arg10) {
        zzas v0 = new zzas();
        if(arg10.zzp() != null) {
            Map v1 = arg10.zzp();
            ArrayList v2 = new ArrayList();
            if(v1 != null) {
                Iterator v3 = v1.keySet().iterator();
                while(v3.hasNext()) {
                    Object v4 = v3.next();
                    ArrayList v5 = new ArrayList();
                    Object v6 = v1.get(v4);
                    if(v6 != null) {
                        Iterator v7 = ((Map)v6).keySet().iterator();
                        while(v7.hasNext()) {
                            Object v8 = v7.next();
                            zzat v9 = new zzat();
                            v9.zzbi = ((String)v8);
                            v9.zzbj = ((Map)v6).get(v8);
                            ((List)v5).add(v9);
                        }
                    }

                    zzav v6_1 = new zzav();
                    v6_1.namespace = ((String)v4);
                    v6_1.zzbo = ((List)v5).toArray(new zzat[((List)v5).size()]);
                    ((List)v2).add(v6_1);
                }
            }

            v0.zzbf = ((List)v2).toArray(new zzav[((List)v2).size()]);
        }

        if(arg10.zzg() != null) {
            List v1_1 = arg10.zzg();
            v0.zzbg = v1_1.toArray(new byte[v1_1.size()][]);
        }

        v0.timestamp = arg10.getTimestamp();
        return v0;
    }
}

