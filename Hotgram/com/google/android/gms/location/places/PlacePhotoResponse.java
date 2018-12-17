package com.google.android.gms.location.places;

import android.graphics.Bitmap;
import com.google.android.gms.common.api.Response;

public class PlacePhotoResponse extends Response {
    PlacePhotoResponse() {
        super();
    }

    public Bitmap getBitmap() {
        return this.getResult().getBitmap();
    }
}

