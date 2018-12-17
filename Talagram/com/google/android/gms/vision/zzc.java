package com.google.android.gms.vision;

import android.util.SparseArray;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.concurrent.GuardedBy;

@VisibleForTesting public final class zzc {
    private static final Object lock;
    @GuardedBy(value="lock") private static int zzau;
    @GuardedBy(value="lock") private SparseArray zzav;
    @GuardedBy(value="lock") private SparseArray zzaw;

    static {
        zzc.lock = new Object();
        zzc.zzau = 0;
    }

    public zzc() {
        super();
        this.zzav = new SparseArray();
        this.zzaw = new SparseArray();
    }

    public final int zzb(int arg5) {
        Object v0 = zzc.lock;
        __monitor_enter(v0);
        try {
            Object v1 = this.zzav.get(arg5);
            if(v1 != null) {
                __monitor_exit(v0);
                return ((Integer)v1).intValue();
            }

            int v1_1 = zzc.zzau;
            ++zzc.zzau;
            this.zzav.append(arg5, Integer.valueOf(v1_1));
            this.zzaw.append(v1_1, Integer.valueOf(arg5));
            __monitor_exit(v0);
            return v1_1;
        label_21:
            __monitor_exit(v0);
        }
        catch(Throwable v5) {
            goto label_21;
        }

        throw v5;
    }

    public final int zzc(int arg3) {
        Object v0 = zzc.lock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.zzaw.get(arg3).intValue();
        label_8:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_8;
        }

        throw v3;
    }
}

