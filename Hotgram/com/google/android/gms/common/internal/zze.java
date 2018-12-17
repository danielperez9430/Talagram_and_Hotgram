package com.google.android.gms.common.internal;

import android.support.v4.f.g;

final class zze extends g {
    zze(ExpirableLruCache arg1, int arg2) {
        this.zzss = arg1;
        super(arg2);
    }

    protected final Object create(Object arg2) {
        return this.zzss.create(arg2);
    }

    protected final void entryRemoved(boolean arg2, Object arg3, Object arg4, Object arg5) {
        this.zzss.entryRemoved(arg2, arg3, arg4, arg5);
        Object v2 = ExpirableLruCache.zza(this.zzss);
        __monitor_enter(v2);
        if(arg5 == null) {
            try {
                if(ExpirableLruCache.zzb(this.zzss)) {
                    ExpirableLruCache.zzc(this.zzss).remove(arg3);
                }

            label_15:
                if(arg5 == null && (ExpirableLruCache.zzd(this.zzss))) {
                    ExpirableLruCache.zze(this.zzss).remove(arg3);
                }

                __monitor_exit(v2);
                return;
            label_24:
                __monitor_exit(v2);
                goto label_25;
            }
            catch(Throwable v3) {
                goto label_24;
            }
        }

        goto label_15;
    label_25:
        throw v3;
    }

    protected final int sizeOf(Object arg2, Object arg3) {
        return this.zzss.sizeOf(arg2, arg3);
    }
}

