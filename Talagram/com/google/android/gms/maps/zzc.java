package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.internal.zzau;
import com.google.android.gms.maps.model.Marker;

final class zzc extends zzau {
    zzc(GoogleMap arg1, OnMarkerDragListener arg2) {
        this.zzk = arg2;
        super();
    }

    public final void zzb(zzt arg3) {
        this.zzk.onMarkerDragStart(new Marker(arg3));
    }

    public final void zzc(zzt arg3) {
        this.zzk.onMarkerDragEnd(new Marker(arg3));
    }

    public final void zzd(zzt arg3) {
        this.zzk.onMarkerDrag(new Marker(arg3));
    }
}

