package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognition$zza;

abstract class zzj extends zza {
    public zzj(GoogleApiClient arg1) {
        super(arg1);
    }

    public Result createFailedResult(Status arg1) {
        return arg1;
    }
}

