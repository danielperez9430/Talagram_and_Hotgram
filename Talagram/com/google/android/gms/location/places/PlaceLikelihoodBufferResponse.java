package com.google.android.gms.location.places;

import com.google.android.gms.common.api.DataBufferResponse;

public class PlaceLikelihoodBufferResponse extends DataBufferResponse {
    PlaceLikelihoodBufferResponse() {
        super();
    }

    public CharSequence getAttributions() {
        return this.getResult().getAttributions();
    }

    public String toString() {
        return this.getResult().toString();
    }
}

