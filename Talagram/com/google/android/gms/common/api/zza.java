package com.google.android.gms.common.api;

final class zza implements StatusListener {
    zza(Batch arg1) {
        this.zzch = arg1;
        super();
    }

    public final void onComplete(Status arg5) {
        Object v0 = Batch.zza(this.zzch);
        __monitor_enter(v0);
        try {
            if(this.zzch.isCanceled()) {
                __monitor_exit(v0);
                return;
            }

            if(arg5.isCanceled()) {
                Batch.zza(this.zzch, true);
            }
            else if(!arg5.isSuccess()) {
                Batch.zzb(this.zzch, true);
            }

            Batch.zzb(this.zzch);
            if(Batch.zzc(this.zzch) == 0) {
                if(Batch.zzd(this.zzch)) {
                    Batch.zze(this.zzch);
                }
                else {
                    arg5 = Batch.zzf(this.zzch) ? new Status(13) : Status.RESULT_SUCCESS;
                    this.zzch.setResult(new BatchResult(arg5, Batch.zzg(this.zzch)));
                }
            }

            __monitor_exit(v0);
            return;
        label_46:
            __monitor_exit(v0);
        }
        catch(Throwable v5) {
            goto label_46;
        }

        throw v5;
    }
}

