package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.zzm$zzb;
import com.google.android.gms.location.places.zzm;
import com.google.android.gms.maps.model.LatLngBounds;

final class zzo extends zzb {
    zzo(zzi arg1, Api arg2, GoogleApiClient arg3, String arg4, LatLngBounds arg5, AutocompleteFilter arg6) {
        this.val$query = arg4;
        this.zzfz = arg5;
        this.zzgb = arg6;
        super(arg2, arg3);
    }

    protected final void doExecute(AnyClient arg7) {
        arg7.zzb(new zzm(((zzb)this)), this.val$query, this.zzfz, 1, this.zzgb);
    }
}

