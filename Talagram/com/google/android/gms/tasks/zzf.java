package com.google.android.gms.tasks;

final class zzf implements Runnable {
    zzf(zze arg1, Task arg2) {
        this.zzafp = arg1;
        this.zzafn = arg2;
        super();
    }

    public final void run() {
        Object v0_2;
        try {
            v0_2 = zze.zza(this.zzafp).then(this.zzafn);
            if(v0_2 != null) {
                goto label_11;
            }
        }
        catch(Exception v0) {
            zze.zzb(this.zzafp).setException(v0);
            return;
        }
        catch(RuntimeExecutionException v0_1) {
            if((v0_1.getCause() instanceof Exception)) {
                zze.zzb(this.zzafp).setException(v0_1.getCause());
                return;
            }

            zze.zzb(this.zzafp).setException(((Exception)v0_1));
            return;
        }

        this.zzafp.onFailure(new NullPointerException("Continuation returned null"));
        return;
    label_11:
        ((Task)v0_2).addOnSuccessListener(TaskExecutors.zzagd, this.zzafp);
        ((Task)v0_2).addOnFailureListener(TaskExecutors.zzagd, this.zzafp);
        ((Task)v0_2).addOnCanceledListener(TaskExecutors.zzagd, this.zzafp);
    }
}

