package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzm;
import com.google.android.gms.maps.model.CameraPosition;

final class zzt extends zzm {
    zzt(GoogleMap arg1, OnCameraChangeListener arg2) {
        this.zzab = arg2;
        super();
    }

    public final void onCameraChange(CameraPosition arg2) {
        this.zzab.onCameraChange(arg2);
    }
}

