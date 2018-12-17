package com.google.android.gms.internal.config;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;

public final class zzn {
    private static AppMeasurement zza(Context arg0) {
        try {
            return AppMeasurement.getInstance(arg0);
        }
        catch(NoClassDefFoundError ) {
            return null;
        }
    }

    public static List zzb(Context arg4) {
        Map v4_2;
        AppMeasurement v4 = zzn.zza(arg4);
        int v0 = 3;
        List v1 = null;
        if(v4 == null) {
            if(Log.isLoggable("FRCAnalytics", v0)) {
                Log.d("FRCAnalytics", "Unable to get user properties: analytics library is missing.");
            }

            return v1;
        }

        try {
            v4_2 = v4.getUserProperties(false);
        }
        catch(NullPointerException v4_1) {
            if(Log.isLoggable("FRCAnalytics", v0)) {
                Log.d("FRCAnalytics", "Unable to get user properties.", ((Throwable)v4_1));
            }

            v4_2 = ((Map)v1);
        }

        if(v4_2 == null) {
            return v1;
        }

        ArrayList v0_1 = new ArrayList();
        Iterator v4_3 = v4_2.entrySet().iterator();
        while(v4_3.hasNext()) {
            Object v1_1 = v4_3.next();
            if(((Map$Entry)v1_1).getValue() == null) {
                continue;
            }

            ((List)v0_1).add(new zzl(((Map$Entry)v1_1).getKey(), ((Map$Entry)v1_1).getValue().toString()));
        }

        return ((List)v0_1);
    }
}

