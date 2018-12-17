package com.google.android.gms.tasks;

import java.util.ArrayDeque;
import java.util.Queue;
import javax.annotation.concurrent.GuardedBy;

final class zzr {
    private final Object mLock;
    @GuardedBy(value="mLock") private Queue zzaga;
    @GuardedBy(value="mLock") private boolean zzagb;

    zzr() {
        super();
        this.mLock = new Object();
    }

    public final void zza(Task arg3) {
        Object v1;
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            if(this.zzaga != null) {
                if(this.zzagb) {
                }
                else {
                    this.zzagb = true;
                    __monitor_exit(v0);
                    goto label_10;
                }
            }

            goto label_25;
        }
        catch(Throwable v3) {
            goto label_28;
        }

        while(true) {
        label_10:
            v1 = this.mLock;
            __monitor_enter(v1);
            try {
                v0 = this.zzaga.poll();
                if(v0 == null) {
                    this.zzagb = false;
                    __monitor_exit(v1);
                    return;
                }
                else {
                    __monitor_exit(v1);
                    goto label_20;
                }

                goto label_25;
            }
            catch(Throwable v3) {
                break;
            }

        label_20:
            ((zzq)v0).onComplete(arg3);
        }

        try {
        label_23:
            __monitor_exit(v1);
        }
        catch(Throwable v3) {
            goto label_23;
        }

        throw v3;
        try {
        label_25:
            __monitor_exit(v0);
            return;
        label_28:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_28;
        }

        throw v3;
    }

    public final void zza(zzq arg3) {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            if(this.zzaga == null) {
                this.zzaga = new ArrayDeque();
            }

            this.zzaga.add(arg3);
            __monitor_exit(v0);
            return;
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_12;
        }

        throw v3;
    }
}

