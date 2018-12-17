package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzw;
import com.google.android.gms.maps.internal.zzbe;
import com.google.android.gms.maps.model.Polygon;

final class zzp extends zzbe {
    zzp(GoogleMap arg1, OnPolygonClickListener arg2) {
        this.zzx = arg2;
        super();
    }

    public final void zza(zzw arg3) {
        this.zzx.onPolygonClick(new Polygon(arg3));
    }
}

