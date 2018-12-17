package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzao;
import com.google.android.gms.maps.model.LatLng;

final class zzz extends zzao {
    zzz(GoogleMap arg1, OnMapLongClickListener arg2) {
        this.zzah = arg2;
        super();
    }

    public final void onMapLongClick(LatLng arg2) {
        this.zzah.onMapLongClick(arg2);
    }
}

