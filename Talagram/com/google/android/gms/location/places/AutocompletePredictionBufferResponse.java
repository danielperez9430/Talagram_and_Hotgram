package com.google.android.gms.location.places;

import com.google.android.gms.common.api.DataBufferResponse;

public class AutocompletePredictionBufferResponse extends DataBufferResponse {
    AutocompletePredictionBufferResponse() {
        super();
    }

    public String toString() {
        return this.getResult().toString();
    }
}

