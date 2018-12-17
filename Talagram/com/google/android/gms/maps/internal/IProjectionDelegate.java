package com.google.android.gms.maps.internal;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.VisibleRegion;

public interface IProjectionDelegate extends IInterface {
    LatLng fromScreenLocation(IObjectWrapper arg1);

    VisibleRegion getVisibleRegion();

    IObjectWrapper toScreenLocation(LatLng arg1);
}

