package com.google.android.gms.location.places;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.maps.model.LatLngBounds;
import javax.annotation.Nullable;

public interface GeoDataApi {
    @Deprecated PendingResult addPlace(GoogleApiClient arg1, AddPlaceRequest arg2);

    PendingResult getAutocompletePredictions(GoogleApiClient arg1, @Nullable String arg2, @Nullable LatLngBounds arg3, @Nullable AutocompleteFilter arg4);

    PendingResult getPlaceById(GoogleApiClient arg1, String[] arg2);

    PendingResult getPlacePhotos(GoogleApiClient arg1, String arg2);
}

