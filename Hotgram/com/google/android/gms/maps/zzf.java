package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.internal.zzae;
import com.google.android.gms.maps.model.Marker;

final class zzf extends zzae {
    zzf(GoogleMap arg1, OnInfoWindowCloseListener arg2) {
        this.zzn = arg2;
        super();
    }

    public final void zzg(zzt arg3) {
        this.zzn.onInfoWindowClose(new Marker(arg3));
    }
}

