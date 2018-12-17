package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzam;

final class zzk extends zzam {
    zzk(GoogleMap arg1, OnMapLoadedCallback arg2) {
        this.zzs = arg2;
        super();
    }

    public final void onMapLoaded() {
        this.zzs.onMapLoaded();
    }
}

