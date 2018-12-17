package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.zzaq;

final class zzak extends zzaq {
    zzak(zza arg1, OnMapReadyCallback arg2) {
        this.zzbb = arg2;
        super();
    }

    public final void zza(IGoogleMapDelegate arg3) {
        this.zzbb.onMapReady(new GoogleMap(arg3));
    }
}

