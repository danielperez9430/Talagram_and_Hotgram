package com.google.android.gms.location.places;

import com.google.android.gms.common.api.DataBufferResponse;

public class PlaceBufferResponse extends DataBufferResponse {
    PlaceBufferResponse() {
        super();
    }

    public CharSequence getAttributions() {
        return this.getResult().getAttributions();
    }
}

