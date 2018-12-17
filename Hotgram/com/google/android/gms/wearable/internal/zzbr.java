package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.internal.Preconditions;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class zzbr extends zzej {
    private final Object lock;
    @Nullable @GuardedBy(value="lock") private zzav zzcw;
    @Nullable @GuardedBy(value="lock") private zzbs zzda;

    public zzbr() {
        super();
        this.lock = new Object();
    }

    public final void zza(int arg4, int arg5) {
        zzav v2;
        zzbs v1;
        Object v0 = this.lock;
        __monitor_enter(v0);
        try {
            v1 = this.zzda;
            v2 = new zzav(arg4, arg5);
            this.zzcw = v2;
            __monitor_exit(v0);
            if(v1 == null) {
                return;
            }
        }
        catch(Throwable v4) {
            try {
            label_11:
                __monitor_exit(v0);
            }
            catch(Throwable v4) {
                goto label_11;
            }

            throw v4;
        }

        v1.zzb(v2);
    }

    public final void zza(zzbs arg3) {
        zzav v1;
        Object v0 = this.lock;
        __monitor_enter(v0);
        try {
            this.zzda = Preconditions.checkNotNull(arg3);
            v1 = this.zzcw;
            __monitor_exit(v0);
            if(v1 == null) {
                return;
            }
        }
        catch(Throwable v3) {
            try {
            label_10:
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                goto label_10;
            }

            throw v3;
        }

        arg3.zzb(v1);
    }
}

