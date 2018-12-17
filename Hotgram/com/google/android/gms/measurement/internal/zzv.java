package com.google.android.gms.measurement.internal;

import android.os.Handler;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdx;

abstract class zzv {
    private static volatile Handler handler;
    private final zzcq zzahw;
    private final Runnable zzyo;
    private volatile long zzyp;

    zzv(zzcq arg2) {
        super();
        Preconditions.checkNotNull(arg2);
        this.zzahw = arg2;
        this.zzyo = new zzw(this, arg2);
    }

    final void cancel() {
        this.zzyp = 0;
        this.getHandler().removeCallbacks(this.zzyo);
    }

    private final Handler getHandler() {
        if(zzv.handler != null) {
            return zzv.handler;
        }

        Class v0 = zzv.class;
        __monitor_enter(v0);
        try {
            if(zzv.handler == null) {
                zzv.handler = new zzdx(this.zzahw.getContext().getMainLooper());
            }

            __monitor_exit(v0);
            return zzv.handler;
        label_18:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_18;
        }

        throw v1;
    }

    public abstract void run();

    static long zza(zzv arg0, long arg1) {
        arg0.zzyp = 0;
        return 0;
    }

    public final boolean zzej() {
        if(this.zzyp != 0) {
            return 1;
        }

        return 0;
    }

    public final void zzh(long arg4) {
        this.cancel();
        if(arg4 >= 0) {
            this.zzyp = this.zzahw.zzbx().currentTimeMillis();
            if(!this.getHandler().postDelayed(this.zzyo, arg4)) {
                this.zzahw.zzgo().zzjd().zzg("Failed to schedule delayed post. time", Long.valueOf(arg4));
            }
        }
    }
}

