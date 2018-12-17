package com.google.android.gms.location.places;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

public interface PlaceDetectionApi {
    PendingResult getCurrentPlace(GoogleApiClient arg1, PlaceFilter arg2);

    PendingResult reportDeviceAtPlace(GoogleApiClient arg1, PlaceReport arg2);
}

