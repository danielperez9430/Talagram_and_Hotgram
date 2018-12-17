package com.google.android.gms.location;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.internal.location.zzad;
import com.google.android.gms.internal.location.zzak;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzp extends zzak {
    zzp(FusedLocationProviderClient arg1, TaskCompletionSource arg2) {
        this.zzab = arg2;
        super();
    }

    public final void zza(zzad arg5) {
        Status v5 = arg5.getStatus();
        if(v5 == null) {
            this.zzab.trySetException(new ApiException(new Status(8, "Got null status from location service")));
            return;
        }

        if(v5.getStatusCode() == 0) {
            this.zzab.setResult(Boolean.valueOf(true));
            return;
        }

        this.zzab.trySetException(ApiExceptionUtil.fromStatus(v5));
    }
}

