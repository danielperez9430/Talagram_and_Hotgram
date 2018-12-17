package com.google.android.gms.location.places;

import com.google.android.gms.common.api.Response;

public class PlacePhotoMetadataResponse extends Response {
    PlacePhotoMetadataResponse() {
        super();
    }

    public PlacePhotoMetadataBuffer getPhotoMetadata() {
        return this.getResult().getPhotoMetadata();
    }
}

