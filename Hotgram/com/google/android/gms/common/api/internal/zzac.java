package com.google.android.gms.common.api.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzac implements OnCompleteListener {
    zzac(zzaa arg1, TaskCompletionSource arg2) {
        this.zzgz = arg1;
        this.zzha = arg2;
        super();
    }

    public final void onComplete(Task arg2) {
        zzaa.zzb(this.zzgz).remove(this.zzha);
    }
}

