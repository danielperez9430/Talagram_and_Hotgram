package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzbk;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;

final class zzad extends zzbk {
    zzad(StreetViewPanorama arg1, OnStreetViewPanoramaChangeListener arg2) {
        this.zzbo = arg2;
        super();
    }

    public final void onStreetViewPanoramaChange(StreetViewPanoramaLocation arg2) {
        this.zzbo.onStreetViewPanoramaChange(arg2);
    }
}

