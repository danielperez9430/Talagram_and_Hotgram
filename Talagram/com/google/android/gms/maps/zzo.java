package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzh;
import com.google.android.gms.maps.internal.zzw;
import com.google.android.gms.maps.model.Circle;

final class zzo extends zzw {
    zzo(GoogleMap arg1, OnCircleClickListener arg2) {
        this.zzw = arg2;
        super();
    }

    public final void zza(zzh arg3) {
        this.zzw.onCircleClick(new Circle(arg3));
    }
}

