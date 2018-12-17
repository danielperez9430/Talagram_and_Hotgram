package com.google.android.gms.location.places;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.internal.zzas;

public class PlaceBuffer extends AbstractDataBuffer implements Result {
    private final Status zzdz;
    private final String zzea;

    public PlaceBuffer(DataHolder arg2) {
        super(arg2);
        this.zzdz = PlacesStatusCodes.zze(arg2.getStatusCode());
        String v2 = arg2 == null || arg2.getMetadata() == null ? null : arg2.getMetadata().getString("com.google.android.gms.location.places.PlaceBuffer.ATTRIBUTIONS_EXTRA_KEY");
        this.zzea = v2;
    }

    public Place get(int arg3) {
        return new zzas(this.mDataHolder, arg3);
    }

    public Object get(int arg1) {
        return this.get(arg1);
    }

    public CharSequence getAttributions() {
        return this.zzea;
    }

    public Status getStatus() {
        return this.zzdz;
    }
}

