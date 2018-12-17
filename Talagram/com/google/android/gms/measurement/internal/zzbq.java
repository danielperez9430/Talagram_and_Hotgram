package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

final class zzbq implements Thread$UncaughtExceptionHandler {
    private final String zzapf;

    public zzbq(zzbo arg1, String arg2) {
        this.zzapg = arg1;
        super();
        Preconditions.checkNotNull(arg2);
        this.zzapf = arg2;
    }

    public final void uncaughtException(Thread arg2, Throwable arg3) {
        __monitor_enter(this);
        try {
            this.zzapg.zzgo().zzjd().zzg(this.zzapf, arg3);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }
}

