package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzq;

final class zzw extends zzq {
    zzw(GoogleMap arg1, OnCameraMoveCanceledListener arg2) {
        this.zzae = arg2;
        super();
    }

    public final void onCameraMoveCanceled() {
        this.zzae.onCameraMoveCanceled();
    }
}

