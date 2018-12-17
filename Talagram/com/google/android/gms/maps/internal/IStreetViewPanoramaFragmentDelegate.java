package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.StreetViewPanoramaOptions;

public interface IStreetViewPanoramaFragmentDelegate extends IInterface {
    IStreetViewPanoramaDelegate getStreetViewPanorama();

    void getStreetViewPanoramaAsync(zzbp arg1);

    boolean isReady();

    void onCreate(Bundle arg1);

    IObjectWrapper onCreateView(IObjectWrapper arg1, IObjectWrapper arg2, Bundle arg3);

    void onDestroy();

    void onDestroyView();

    void onInflate(IObjectWrapper arg1, StreetViewPanoramaOptions arg2, Bundle arg3);

    void onLowMemory();

    void onPause();

    void onResume();

    void onSaveInstanceState(Bundle arg1);

    void onStart();

    void onStop();
}

