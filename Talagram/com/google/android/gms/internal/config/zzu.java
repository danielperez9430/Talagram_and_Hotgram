package com.google.android.gms.internal.config;

import com.google.android.gms.common.api.Status;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class zzu implements zzk {
    private final Status mStatus;
    private final Map zzq;
    private final long zzr;
    private final List zzs;

    public zzu(Status arg3, Map arg4) {
        this(arg3, arg4, -1);
    }

    private zzu(Status arg7, Map arg8, long arg9) {
        this(arg7, arg8, -1, null);
    }

    public zzu(Status arg1, Map arg2, long arg3, List arg5) {
        super();
        this.mStatus = arg1;
        this.zzq = arg2;
        this.zzr = arg3;
        this.zzs = arg5;
    }

    public zzu(Status arg7, Map arg8, List arg9) {
        this(arg7, arg8, -1, arg9);
    }

    public final Status getStatus() {
        return this.mStatus;
    }

    public final long getThrottleEndTimeMillis() {
        return this.zzr;
    }

    public final byte[] zza(String arg2, byte[] arg3, String arg4) {
        int v0 = 0;
        if(this.zzq != null) {
            if(this.zzq.get(arg4) == null) {
            }
            else if(this.zzq.get(arg4).get(arg2) != null) {
                v0 = 1;
            }
        }

        if(v0 != 0) {
            return this.zzq.get(arg4).get(arg2);
        }

        return null;
    }

    public final List zzg() {
        return this.zzs;
    }

    public final Map zzh() {
        HashMap v0 = new HashMap();
        if(this.zzq != null) {
            Iterator v1 = this.zzq.keySet().iterator();
            while(v1.hasNext()) {
                Object v2 = v1.next();
                Object v3 = this.zzq.get(v2);
                if(v3 == null) {
                    continue;
                }

                ((Map)v0).put(v2, ((Map)v3).keySet());
            }
        }

        return ((Map)v0);
    }
}

