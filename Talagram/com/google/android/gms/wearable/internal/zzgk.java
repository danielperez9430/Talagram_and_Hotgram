package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.CapabilityInfo;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzgk {
    private static Map zza(List arg4) {
        HashMap v0 = new HashMap();
        if(arg4 != null) {
            Iterator v4 = arg4.iterator();
            while(v4.hasNext()) {
                Object v1 = v4.next();
                v0.put(((zzah)v1).getName(), new zzw(((CapabilityInfo)v1)));
            }
        }

        return ((Map)v0);
    }

    static Map zzb(List arg0) {
        return zzgk.zza(arg0);
    }
}

