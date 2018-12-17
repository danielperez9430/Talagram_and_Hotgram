package com.google.android.gms.location;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzm extends TaskApiCall {
    zzm(FusedLocationProviderClient arg1) {
        super();
    }

    protected final void doExecute(AnyClient arg1, TaskCompletionSource arg2) {
        arg2.setResult(((zzaz)arg1).zza());
    }
}

