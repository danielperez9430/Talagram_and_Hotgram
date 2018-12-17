package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzaw;

final class zzi extends zzaw {
    zzi(GoogleMap arg1, OnMyLocationButtonClickListener arg2) {
        this.zzq = arg2;
        super();
    }

    public final boolean onMyLocationButtonClick() {
        return this.zzq.onMyLocationButtonClick();
    }
}

