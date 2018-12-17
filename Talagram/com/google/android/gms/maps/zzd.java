package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.internal.zzac;
import com.google.android.gms.maps.model.Marker;

final class zzd extends zzac {
    zzd(GoogleMap arg1, OnInfoWindowClickListener arg2) {
        this.zzl = arg2;
        super();
    }

    public final void zze(zzt arg3) {
        this.zzl.onInfoWindowClick(new Marker(arg3));
    }
}

