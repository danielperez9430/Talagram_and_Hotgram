package com.google.android.gms.location.places;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.data.Freezable;

public interface PlacePhotoMetadata extends Freezable {
    CharSequence getAttributions();

    int getMaxHeight();

    int getMaxWidth();

    PendingResult getPhoto(GoogleApiClient arg1);

    PendingResult getScaledPhoto(GoogleApiClient arg1, int arg2, int arg3);
}

