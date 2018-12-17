package com.google.android.gms.tasks;

import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class zzg implements zzq {
    private final Object mLock;
    private final Executor zzafk;
    @GuardedBy(value="mLock") private OnCanceledListener zzafq;

    public zzg(Executor arg2, OnCanceledListener arg3) {
        super();
        this.mLock = new Object();
        this.zzafk = arg2;
        this.zzafq = arg3;
    }

    public final void cancel() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        OnCanceledListener v1 = null;
        try {
            this.zzafq = v1;
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

    public final void onComplete(Task arg2) {
        if(arg2.isCanceled()) {
            Object v2 = this.mLock;
            __monitor_enter(v2);
            try {
                if(this.zzafq == null) {
                    __monitor_exit(v2);
                    return;
                }
                else {
                    __monitor_exit(v2);
                    goto label_9;
                }

                return;
            }
            catch(Throwable v0) {
                try {
                label_15:
                    __monitor_exit(v2);
                }
                catch(Throwable v0) {
                    goto label_15;
                }

                throw v0;
            }

        label_9:
            this.zzafk.execute(new zzh(this));
        }
    }

    static Object zza(zzg arg0) {
        return arg0.mLock;
    }

    static OnCanceledListener zzb(zzg arg0) {
        return arg0.zzafq;
    }
}

