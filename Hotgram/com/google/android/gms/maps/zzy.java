package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzak;
import com.google.android.gms.maps.model.LatLng;

final class zzy extends zzak {
    zzy(GoogleMap arg1, OnMapClickListener arg2) {
        this.zzag = arg2;
        super();
    }

    public final void onMapClick(LatLng arg2) {
        this.zzag.onMapClick(arg2);
    }
}

