package com.google.android.gms.tasks;

import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class zzm implements zzq {
    private final Object mLock;
    private final Executor zzafk;
    @GuardedBy(value="mLock") private OnSuccessListener zzafw;

    public zzm(Executor arg2, OnSuccessListener arg3) {
        super();
        this.mLock = new Object();
        this.zzafk = arg2;
        this.zzafw = arg3;
    }

    public final void cancel() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        OnSuccessListener v1 = null;
        try {
            this.zzafw = v1;
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
        if(arg3.isSuccessful()) {
            Object v0 = this.mLock;
            __monitor_enter(v0);
            try {
                if(this.zzafw == null) {
                    __monitor_exit(v0);
                    return;
                }
                else {
                    __monitor_exit(v0);
                    goto label_9;
                }

                return;
            }
            catch(Throwable v3) {
                try {
                label_15:
                    __monitor_exit(v0);
                }
                catch(Throwable v3) {
                    goto label_15;
                }

                throw v3;
            }

        label_9:
            this.zzafk.execute(new zzn(this, arg3));
        }
    }

    static Object zza(zzm arg0) {
        return arg0.mLock;
    }

    static OnSuccessListener zzb(zzm arg0) {
        return arg0.zzafw;
    }
}

