package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

final class zzu extends zzab {
    zzu(zzq arg1, GoogleApiClient arg2, Location arg3) {
        this.zzco = arg3;
        super(arg2);
    }

    protected final void doExecute(AnyClient arg2) {
        ((zzaz)arg2).zza(this.zzco);
        this.setResult(Status.RESULT_SUCCESS);
    }
}

