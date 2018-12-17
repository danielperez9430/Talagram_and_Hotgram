package com.google.android.gms.maps;

import android.location.Location;
import com.google.android.gms.maps.internal.zzba;

final class zzj extends zzba {
    zzj(GoogleMap arg1, OnMyLocationClickListener arg2) {
        this.zzr = arg2;
        super();
    }

    public final void onMyLocationClick(Location arg2) {
        this.zzr.onMyLocationClick(arg2);
    }
}

