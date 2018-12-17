package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.internal.zzag;
import com.google.android.gms.maps.model.Marker;

final class zze extends zzag {
    zze(GoogleMap arg1, OnInfoWindowLongClickListener arg2) {
        this.zzm = arg2;
        super();
    }

    public final void zzf(zzt arg3) {
        this.zzm.onInfoWindowLongClick(new Marker(arg3));
    }
}

