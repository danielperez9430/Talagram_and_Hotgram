package com.google.android.gms.maps.internal;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.StreetViewSource;

public interface IStreetViewPanoramaDelegate extends IInterface {
    void animateTo(StreetViewPanoramaCamera arg1, long arg2);

    void enablePanning(boolean arg1);

    void enableStreetNames(boolean arg1);

    void enableUserNavigation(boolean arg1);

    void enableZoom(boolean arg1);

    StreetViewPanoramaCamera getPanoramaCamera();

    StreetViewPanoramaLocation getStreetViewPanoramaLocation();

    boolean isPanningGesturesEnabled();

    boolean isStreetNamesEnabled();

    boolean isUserNavigationEnabled();

    boolean isZoomGesturesEnabled();

    IObjectWrapper orientationToPoint(StreetViewPanoramaOrientation arg1);

    StreetViewPanoramaOrientation pointToOrientation(IObjectWrapper arg1);

    void setOnStreetViewPanoramaCameraChangeListener(zzbh arg1);

    void setOnStreetViewPanoramaChangeListener(zzbj arg1);

    void setOnStreetViewPanoramaClickListener(zzbl arg1);

    void setOnStreetViewPanoramaLongClickListener(zzbn arg1);

    void setPosition(LatLng arg1);

    void setPositionWithID(String arg1);

    void setPositionWithRadius(LatLng arg1, int arg2);

    void setPositionWithRadiusAndSource(LatLng arg1, int arg2, StreetViewSource arg3);

    void setPositionWithSource(LatLng arg1, StreetViewSource arg2);
}

