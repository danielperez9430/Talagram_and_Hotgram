package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.zzbq;

final class zzaj extends zzbq {
    zzaj(zza arg1, OnStreetViewPanoramaReadyCallback arg2) {
        this.zzbu = arg2;
        super();
    }

    public final void zza(IStreetViewPanoramaDelegate arg3) {
        this.zzbu.onStreetViewPanoramaReady(new StreetViewPanorama(arg3));
    }
}

