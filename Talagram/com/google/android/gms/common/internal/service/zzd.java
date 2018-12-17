package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class zzd extends zzc {
    public zzd(GoogleApiClient arg1) {
        super(arg1);
    }

    public Result createFailedResult(Status arg1) {
        return arg1;
    }
}

