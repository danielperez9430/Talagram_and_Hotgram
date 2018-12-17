package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface IMapViewDelegate extends IInterface {
    IGoogleMapDelegate getMap();

    void getMapAsync(zzap arg1);

    IObjectWrapper getView();

    void onCreate(Bundle arg1);

    void onDestroy();

    void onEnterAmbient(Bundle arg1);

    void onExitAmbient();

    void onLowMemory();

    void onPause();

    void onResume();

    void onSaveInstanceState(Bundle arg1);

    void onStart();

    void onStop();
}

