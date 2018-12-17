package com.google.android.gms.common.api.internal;

abstract class zzbe {
    private final zzbc zzjg;

    protected zzbe(zzbc arg1) {
        super();
        this.zzjg = arg1;
    }

    protected abstract void zzaq();

    public final void zzc(zzbd arg3) {
        zzbd.zza(arg3).lock();
        try {
            if(zzbd.zzb(arg3) == this.zzjg) {
                this.zzaq();
            }
        }
        catch(Throwable v0) {
            zzbd.zza(arg3).unlock();
            throw v0;
        }

        zzbd.zza(arg3).unlock();
    }
}

