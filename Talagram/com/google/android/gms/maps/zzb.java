package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.internal.zzas;
import com.google.android.gms.maps.model.Marker;

final class zzb extends zzas {
    zzb(GoogleMap arg1, OnMarkerClickListener arg2) {
        this.zzj = arg2;
        super();
    }

    public final boolean zza(zzt arg3) {
        return this.zzj.onMarkerClick(new Marker(arg3));
    }
}

