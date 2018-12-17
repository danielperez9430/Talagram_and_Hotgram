package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.zzm$zzd;
import com.google.android.gms.location.places.zzm;

final class zzj extends zzd {
    zzj(zzi arg1, Api arg2, GoogleApiClient arg3, AddPlaceRequest arg4) {
        this.zzfs = arg4;
        super(arg2, arg3);
    }

    protected final void doExecute(AnyClient arg3) {
        ((zzp)arg3).zzb(new zzm(((zzd)this)), this.zzfs);
    }
}

