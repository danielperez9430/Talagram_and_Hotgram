package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzbi;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

final class zzae extends zzbi {
    zzae(StreetViewPanorama arg1, OnStreetViewPanoramaCameraChangeListener arg2) {
        this.zzbp = arg2;
        super();
    }

    public final void onStreetViewPanoramaCameraChange(StreetViewPanoramaCamera arg2) {
        this.zzbp.onStreetViewPanoramaCameraChange(arg2);
    }
}

