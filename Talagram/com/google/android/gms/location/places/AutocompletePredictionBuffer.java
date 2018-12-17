package com.google.android.gms.location.places;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.location.places.internal.zze;

public class AutocompletePredictionBuffer extends AbstractDataBuffer implements Result {
    public AutocompletePredictionBuffer(DataHolder arg1) {
        super(arg1);
    }

    public AutocompletePrediction get(int arg3) {
        return new zze(this.mDataHolder, arg3);
    }

    public Object get(int arg1) {
        return this.get(arg1);
    }

    public Status getStatus() {
        return PlacesStatusCodes.zze(this.mDataHolder.getStatusCode());
    }

    public String toString() {
        return Objects.toStringHelper(this).add("status", this.getStatus()).toString();
    }
}

