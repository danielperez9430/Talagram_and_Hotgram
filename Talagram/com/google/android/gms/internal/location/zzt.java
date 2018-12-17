package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

final class zzt extends zzab {
    zzt(zzq arg1, GoogleApiClient arg2, boolean arg3) {
        this.zzcn = arg3;
        super(arg2);
    }

    protected final void doExecute(AnyClient arg2) {
        ((zzaz)arg2).zza(this.zzcn);
        this.setResult(Status.RESULT_SUCCESS);
    }
}

