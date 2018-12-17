package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;

public final class zzal extends zzav implements PlaceLikelihood {
    public zzal(DataHolder arg1, int arg2) {
        super(arg1, arg2);
    }

    public final Object freeze() {
        return new zzaj(Preconditions.checkNotNull(this.getPlace().freeze()), this.getLikelihood());
    }

    public final float getLikelihood() {
        return ((zzav)this).zzb("place_likelihood", -1f);
    }

    public final Place getPlace() {
        return new zzas(this.mDataHolder, this.mDataRow);
    }
}

