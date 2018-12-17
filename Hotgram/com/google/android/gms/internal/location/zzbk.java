package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsApi;

public final class zzbk implements SettingsApi {
    public zzbk() {
        super();
    }

    public final PendingResult checkLocationSettings(GoogleApiClient arg3, LocationSettingsRequest arg4) {
        return arg3.enqueue(new zzbl(this, arg3, arg4, null));
    }
}

