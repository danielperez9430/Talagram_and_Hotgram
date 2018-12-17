package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;
import java.util.WeakHashMap;

public final class zzaa {
    private final Map zzgw;
    private final Map zzgx;

    public zzaa() {
        super();
        this.zzgw = Collections.synchronizedMap(new WeakHashMap());
        this.zzgx = Collections.synchronizedMap(new WeakHashMap());
    }

    static Map zza(zzaa arg0) {
        return arg0.zzgw;
    }

    private final void zza(boolean arg5, Status arg6) {
        HashMap v0_1;
        HashMap v1;
        Map v0 = this.zzgw;
        __monitor_enter(v0);
        try {
            v1 = new HashMap(this.zzgw);
            __monitor_exit(v0);
        }
        catch(Throwable v5) {
            try {
            label_43:
                __monitor_exit(v0);
            }
            catch(Throwable v5) {
                goto label_43;
            }

            throw v5;
        }

        Map v2 = this.zzgx;
        __monitor_enter(v2);
        try {
            v0_1 = new HashMap(this.zzgx);
            __monitor_exit(v2);
        }
        catch(Throwable v5) {
            try {
            label_40:
                __monitor_exit(v2);
            }
            catch(Throwable v5) {
                goto label_40;
            }

            throw v5;
        }

        Iterator v1_1 = ((Map)v1).entrySet().iterator();
        while(v1_1.hasNext()) {
            Object v2_1 = v1_1.next();
            if(!arg5 && !((Map$Entry)v2_1).getValue().booleanValue()) {
                continue;
            }

            ((Map$Entry)v2_1).getKey().zzb(arg6);
        }

        Iterator v0_2 = ((Map)v0_1).entrySet().iterator();
        while(v0_2.hasNext()) {
            Object v1_2 = v0_2.next();
            if(!arg5 && !((Map$Entry)v1_2).getValue().booleanValue()) {
                continue;
            }

            ((Map$Entry)v1_2).getKey().trySetException(new ApiException(arg6));
        }
    }

    final void zza(BasePendingResult arg2, boolean arg3) {
        this.zzgw.put(arg2, Boolean.valueOf(arg3));
        ((PendingResult)arg2).addStatusListener(new zzab(this, arg2));
    }

    final void zza(TaskCompletionSource arg2, boolean arg3) {
        this.zzgx.put(arg2, Boolean.valueOf(arg3));
        arg2.getTask().addOnCompleteListener(new zzac(this, arg2));
    }

    final boolean zzaj() {
        if(this.zzgw.isEmpty()) {
            if(!this.zzgx.isEmpty()) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public final void zzak() {
        this.zza(false, GoogleApiManager.zzjj);
    }

    public final void zzal() {
        this.zza(true, zzck.zzmm);
    }

    static Map zzb(zzaa arg0) {
        return arg0.zzgx;
    }
}

