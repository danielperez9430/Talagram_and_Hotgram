package com.google.android.gms.location.places.ui;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;

public interface PlaceSelectionListener {
    void onError(Status arg1);

    void onPlaceSelected(Place arg1);
}

