package com.google.android.gms.maps;

final class zzu extends com.google.android.gms.maps.internal.zzu {
    zzu(GoogleMap arg1, OnCameraMoveStartedListener arg2) {
        this.zzac = arg2;
        super();
    }

    public final void onCameraMoveStarted(int arg2) {
        this.zzac.onCameraMoveStarted(arg2);
    }
}

