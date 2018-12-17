package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.places.PlaceDetectionApi;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.Places;

public final class zzaa implements PlaceDetectionApi {
    public zzaa() {
        super();
    }

    public final PendingResult getCurrentPlace(GoogleApiClient arg3, PlaceFilter arg4) {
        return arg3.enqueue(new zzab(this, Places.PLACE_DETECTION_API, arg3, arg4));
    }

    public final PendingResult reportDeviceAtPlace(GoogleApiClient arg3, PlaceReport arg4) {
        Preconditions.checkNotNull(arg4, "report == null");
        return arg3.execute(new zzac(this, Places.PLACE_DETECTION_API, arg3, arg4));
    }
}

