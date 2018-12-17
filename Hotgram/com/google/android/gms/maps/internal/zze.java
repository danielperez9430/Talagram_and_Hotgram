package com.google.android.gms.maps.internal;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.StreetViewPanoramaOptions;

public interface zze extends IInterface {
    IMapViewDelegate zza(IObjectWrapper arg1, GoogleMapOptions arg2);

    IStreetViewPanoramaViewDelegate zza(IObjectWrapper arg1, StreetViewPanoramaOptions arg2);

    void zza(IObjectWrapper arg1, int arg2);

    IMapFragmentDelegate zzc(IObjectWrapper arg1);

    ICameraUpdateFactoryDelegate zzd();

    IStreetViewPanoramaFragmentDelegate zzd(IObjectWrapper arg1);

    com.google.android.gms.internal.maps.zze zze();
}

