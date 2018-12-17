package com.google.android.gms.tasks;

import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class zzi implements zzq {
    private final Object mLock;
    private final Executor zzafk;
    @GuardedBy(value="mLock") private OnCompleteListener zzafs;

    public zzi(Executor arg2, OnCompleteListener arg3) {
        super();
        this.mLock = new Object();
        this.zzafk = arg2;
        this.zzafs = arg3;
    }

    public final void cancel() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        OnCompleteListener v1 = null;
        try {
            this.zzafs = v1;
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

    public final void onComplete(Task arg3) {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            if(this.zzafs == null) {
                __monitor_exit(v0);
                return;
            }

            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            try {
            label_13:
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                goto label_13;
            }

            throw v3;
        }

        this.zzafk.execute(new zzj(this, arg3));
    }

    static Object zza(zzi arg0) {
        return arg0.mLock;
    }

    static OnCompleteListener zzb(zzi arg0) {
        return arg0.zzafs;
    }
}

