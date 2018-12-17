package com.google.android.gms.location.places;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.internal.zzar;

public class PlacePhotoMetadataBuffer extends AbstractDataBuffer {
    public PlacePhotoMetadataBuffer(DataHolder arg1) {
        super(arg1);
    }

    public PlacePhotoMetadata get(int arg3) {
        return new zzar(this.mDataHolder, arg3);
    }

    public Object get(int arg1) {
        return this.get(arg1);
    }
}

