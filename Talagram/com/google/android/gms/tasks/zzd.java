package com.google.android.gms.tasks;

final class zzd implements Runnable {
    zzd(zzc arg1, Task arg2) {
        this.zzafo = arg1;
        this.zzafn = arg2;
        super();
    }

    public final void run() {
        Object v0_2;
        if(this.zzafn.isCanceled()) {
            zzc.zza(this.zzafo).zzdp();
            return;
        }

        try {
            v0_2 = zzc.zzb(this.zzafo).then(this.zzafn);
        }
        catch(Exception v0) {
            zzc.zza(this.zzafo).setException(v0);
            return;
        }
        catch(RuntimeExecutionException v0_1) {
            if((v0_1.getCause() instanceof Exception)) {
                zzc.zza(this.zzafo).setException(v0_1.getCause());
                return;
            }

            zzc.zza(this.zzafo).setException(((Exception)v0_1));
            return;
        }

        zzc.zza(this.zzafo).setResult(v0_2);
    }
}

