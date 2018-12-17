package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

final class zzbr extends FutureTask implements Comparable {
    private final String zzapf;
    private final long zzaph;
    final boolean zzapi;

    zzbr(zzbo arg3, Runnable arg4, boolean arg5, String arg6) {
        this.zzapg = arg3;
        super(arg4, null);
        Preconditions.checkNotNull(arg6);
        this.zzaph = zzbo.zzkd().getAndIncrement();
        this.zzapf = arg6;
        this.zzapi = false;
        if(this.zzaph == 9223372036854775807L) {
            ((zzco)arg3).zzgo().zzjd().zzbx("Tasks index overflow");
        }
    }

    zzbr(zzbo arg3, Callable arg4, boolean arg5, String arg6) {
        this.zzapg = arg3;
        super(arg4);
        Preconditions.checkNotNull(arg6);
        this.zzaph = zzbo.zzkd().getAndIncrement();
        this.zzapf = arg6;
        this.zzapi = arg5;
        if(this.zzaph == 9223372036854775807L) {
            ((zzco)arg3).zzgo().zzjd().zzbx("Tasks index overflow");
        }
    }

    public final int compareTo(Object arg8) {
        int v3 = -1;
        if(this.zzapi != ((zzbr)arg8).zzapi) {
            if(this.zzapi) {
                return v3;
            }

            return 1;
        }

        if(this.zzaph < ((zzbr)arg8).zzaph) {
            return v3;
        }

        if(this.zzaph > ((zzbr)arg8).zzaph) {
            return 1;
        }

        this.zzapg.zzgo().zzje().zzg("Two tasks share the same index. index", Long.valueOf(this.zzaph));
        return 0;
    }

    protected final void setException(Throwable arg3) {
        this.zzapg.zzgo().zzjd().zzg(this.zzapf, arg3);
        if((arg3 instanceof zzbp)) {
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), arg3);
        }

        super.setException(arg3);
    }
}

