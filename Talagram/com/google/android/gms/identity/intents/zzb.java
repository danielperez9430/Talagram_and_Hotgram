package com.google.android.gms.identity.intents;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.identity.zze;

final class zzb extends zza {
    zzb(GoogleApiClient arg1, UserAddressRequest arg2, int arg3) {
        this.zze = arg2;
        this.val$requestCode = arg3;
        super(arg1);
    }

    protected final void doExecute(AnyClient arg3) {
        ((zze)arg3).zza(this.zze, this.val$requestCode);
        this.setResult(Status.RESULT_SUCCESS);
    }
}

