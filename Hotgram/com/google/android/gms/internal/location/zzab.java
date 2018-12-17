package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices$zza;

abstract class zzab extends zza {
    public zzab(GoogleApiClient arg1) {
        super(arg1);
    }

    public Result createFailedResult(Status arg1) {
        return arg1;
    }
}

