package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzk;
import com.google.android.gms.maps.internal.zzy;
import com.google.android.gms.maps.model.GroundOverlay;

final class zzn extends zzy {
    zzn(GoogleMap arg1, OnGroundOverlayClickListener arg2) {
        this.zzv = arg2;
        super();
    }

    public final void zza(zzk arg3) {
        this.zzv.onGroundOverlayClick(new GroundOverlay(arg3));
    }
}

