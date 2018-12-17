package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzbo;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

final class zzag extends zzbo {
    zzag(StreetViewPanorama arg1, OnStreetViewPanoramaLongClickListener arg2) {
        this.zzbr = arg2;
        super();
    }

    public final void onStreetViewPanoramaLongClick(StreetViewPanoramaOrientation arg2) {
        this.zzbr.onStreetViewPanoramaLongClick(arg2);
    }
}

