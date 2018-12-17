package com.google.android.gms.tasks;

import java.util.concurrent.CancellationException;

final class zzp implements Runnable {
    zzp(zzo arg1, Task arg2) {
        this.zzafz = arg1;
        this.zzafn = arg2;
        super();
    }

    public final void run() {
        Task v0_2;
        try {
            v0_2 = zzo.zza(this.zzafz).then(this.zzafn.getResult());
            if(v0_2 != null) {
                goto label_12;
            }
        }
        catch(Exception v0) {
            this.zzafz.onFailure(v0);
            return;
        }
        catch(CancellationException ) {
            this.zzafz.onCanceled();
            return;
        }
        catch(RuntimeExecutionException v0_1) {
            if((v0_1.getCause() instanceof Exception)) {
                this.zzafz.onFailure(v0_1.getCause());
                return;
            }

            this.zzafz.onFailure(((Exception)v0_1));
            return;
        }

        this.zzafz.onFailure(new NullPointerException("Continuation returned null"));
        return;
    label_12:
        v0_2.addOnSuccessListener(TaskExecutors.zzagd, this.zzafz);
        v0_2.addOnFailureListener(TaskExecutors.zzagd, this.zzafz);
        v0_2.addOnCanceledListener(TaskExecutors.zzagd, this.zzafz);
    }
}

