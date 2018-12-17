package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzs;

final class zzv extends zzs {
    zzv(GoogleMap arg1, OnCameraMoveListener arg2) {
        this.zzad = arg2;
        super();
    }

    public final void onCameraMove() {
        this.zzad.onCameraMove();
    }
}

