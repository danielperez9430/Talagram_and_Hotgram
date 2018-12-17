package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzo;

final class zzx extends zzo {
    zzx(GoogleMap arg1, OnCameraIdleListener arg2) {
        this.zzaf = arg2;
        super();
    }

    public final void onCameraIdle() {
        this.zzaf.onCameraIdle();
    }
}

