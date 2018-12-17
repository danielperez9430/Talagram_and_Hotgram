package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.zzm$zze;
import com.google.android.gms.location.places.zzm;

final class zzab extends zze {
    zzab(zzaa arg1, Api arg2, GoogleApiClient arg3, PlaceFilter arg4) {
        this.zzgd = arg4;
        super(arg2, arg3);
    }

    protected final void doExecute(AnyClient arg3) {
        ((zzad)arg3).zzb(new zzm(((zze)this)), this.zzgd);
    }
}

