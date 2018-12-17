package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.zzm$zzg;
import com.google.android.gms.location.places.zzm;

final class zzac extends zzg {
    zzac(zzaa arg1, Api arg2, GoogleApiClient arg3, PlaceReport arg4) {
        this.zzge = arg4;
        super(arg2, arg3);
    }

    protected final void doExecute(AnyClient arg3) {
        ((zzad)arg3).zzb(new zzm(((zzg)this)), this.zzge);
    }
}

