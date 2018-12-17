package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzbm;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

final class zzaf extends zzbm {
    zzaf(StreetViewPanorama arg1, OnStreetViewPanoramaClickListener arg2) {
        this.zzbq = arg2;
        super();
    }

    public final void onStreetViewPanoramaClick(StreetViewPanoramaOrientation arg2) {
        this.zzbq.onStreetViewPanoramaClick(arg2);
    }
}

