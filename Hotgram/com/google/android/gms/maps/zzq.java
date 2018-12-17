package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzz;
import com.google.android.gms.maps.internal.zzbg;
import com.google.android.gms.maps.model.Polyline;

final class zzq extends zzbg {
    zzq(GoogleMap arg1, OnPolylineClickListener arg2) {
        this.zzy = arg2;
        super();
    }

    public final void zza(zzz arg3) {
        this.zzy.onPolylineClick(new Polyline(arg3));
    }
}

