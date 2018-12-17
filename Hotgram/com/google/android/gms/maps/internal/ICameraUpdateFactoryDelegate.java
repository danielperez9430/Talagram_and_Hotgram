package com.google.android.gms.maps.internal;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public interface ICameraUpdateFactoryDelegate extends IInterface {
    IObjectWrapper newCameraPosition(CameraPosition arg1);

    IObjectWrapper newLatLng(LatLng arg1);

    IObjectWrapper newLatLngBounds(LatLngBounds arg1, int arg2);

    IObjectWrapper newLatLngBoundsWithSize(LatLngBounds arg1, int arg2, int arg3, int arg4);

    IObjectWrapper newLatLngZoom(LatLng arg1, float arg2);

    IObjectWrapper scrollBy(float arg1, float arg2);

    IObjectWrapper zoomBy(float arg1);

    IObjectWrapper zoomByWithFocus(float arg1, int arg2, int arg3);

    IObjectWrapper zoomIn();

    IObjectWrapper zoomOut();

    IObjectWrapper zoomTo(float arg1);
}

