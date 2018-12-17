package com.google.android.gms.common.api.internal;

import android.support.v4.f.a;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Iterator;
import java.util.Set;

public final class zzj {
    private final a zzcc;
    private final a zzei;
    private final TaskCompletionSource zzej;
    private int zzek;
    private boolean zzel;

    public zzj(Iterable arg4) {
        super();
        this.zzei = new a();
        this.zzej = new TaskCompletionSource();
        this.zzel = false;
        this.zzcc = new a();
        Iterator v4 = arg4.iterator();
        while(v4.hasNext()) {
            this.zzcc.put(v4.next().zzm(), null);
        }

        this.zzek = this.zzcc.keySet().size();
    }

    public final Task getTask() {
        return this.zzej.getTask();
    }

    public final void zza(zzh arg2, ConnectionResult arg3, String arg4) {
        this.zzcc.put(arg2, arg3);
        this.zzei.put(arg2, arg4);
        --this.zzek;
        if(!arg3.isSuccess()) {
            this.zzel = true;
        }

        if(this.zzek == 0) {
            if(this.zzel) {
                this.zzej.setException(new AvailabilityException(this.zzcc));
                return;
            }
            else {
                this.zzej.setResult(this.zzei);
            }
        }
    }

    public final Set zzs() {
        return this.zzcc.keySet();
    }
}

