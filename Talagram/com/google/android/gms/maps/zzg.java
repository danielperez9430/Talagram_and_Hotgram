package com.google.android.gms.maps;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.internal.zzi;
import com.google.android.gms.maps.model.Marker;

final class zzg extends zzi {
    zzg(GoogleMap arg1, InfoWindowAdapter arg2) {
        this.zzo = arg2;
        super();
    }

    public final IObjectWrapper zzh(zzt arg3) {
        return ObjectWrapper.wrap(this.zzo.getInfoWindow(new Marker(arg3)));
    }

    public final IObjectWrapper zzi(zzt arg3) {
        return ObjectWrapper.wrap(this.zzo.getInfoContents(new Marker(arg3)));
    }
}

