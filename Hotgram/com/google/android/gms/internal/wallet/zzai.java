package com.google.android.gms.internal.wallet;

import android.os.Bundle;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzai extends zzah {
    private final TaskCompletionSource zzgl;

    zzai(TaskCompletionSource arg1) {
        super();
        this.zzgl = arg1;
    }

    public final void zza(int arg1, boolean arg2, Bundle arg3) {
        TaskUtil.setResultOrApiException(new Status(arg1), Boolean.valueOf(arg2), this.zzgl);
    }

    public final void zza(Status arg1, boolean arg2, Bundle arg3) {
        TaskUtil.setResultOrApiException(arg1, Boolean.valueOf(arg2), this.zzgl);
    }
}

