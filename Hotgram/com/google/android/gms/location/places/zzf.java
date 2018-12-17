package com.google.android.gms.location.places;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public abstract class zzf extends zzc {
    public zzf(Api arg1, GoogleApiClient arg2) {
        super(arg1, arg2);
    }

    protected Result createFailedResult(Status arg3) {
        return new PlacePhotoResult(arg3, null);
    }
}

