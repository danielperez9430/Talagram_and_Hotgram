package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzbc;
import com.google.android.gms.maps.model.PointOfInterest;

final class zzs extends zzbc {
    zzs(GoogleMap arg1, OnPoiClickListener arg2) {
        this.zzaa = arg2;
        super();
    }

    public final void zza(PointOfInterest arg2) {
        this.zzaa.onPoiClick(arg2);
    }
}

