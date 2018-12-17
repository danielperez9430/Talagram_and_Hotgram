package com.google.android.gms.maps;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.internal.zzay;

final class zzh extends zzay {
    zzh(GoogleMap arg1, OnMyLocationChangeListener arg2) {
        this.zzp = arg2;
        super();
    }

    public final void zza(IObjectWrapper arg2) {
        this.zzp.onMyLocationChange(ObjectWrapper.unwrap(arg2));
    }
}

