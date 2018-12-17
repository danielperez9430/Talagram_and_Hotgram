package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zzac;
import com.google.android.gms.internal.maps.zzh;
import com.google.android.gms.internal.maps.zzk;
import com.google.android.gms.internal.maps.zzn;
import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.internal.maps.zzw;
import com.google.android.gms.internal.maps.zzz;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;

public interface IGoogleMapDelegate extends IInterface {
    zzh addCircle(CircleOptions arg1);

    zzk addGroundOverlay(GroundOverlayOptions arg1);

    zzt addMarker(MarkerOptions arg1);

    zzw addPolygon(PolygonOptions arg1);

    zzz addPolyline(PolylineOptions arg1);

    zzac addTileOverlay(TileOverlayOptions arg1);

    void animateCamera(IObjectWrapper arg1);

    void animateCameraWithCallback(IObjectWrapper arg1, zzc arg2);

    void animateCameraWithDurationAndCallback(IObjectWrapper arg1, int arg2, zzc arg3);

    void clear();

    CameraPosition getCameraPosition();

    zzn getFocusedBuilding();

    void getMapAsync(zzap arg1);

    int getMapType();

    float getMaxZoomLevel();

    float getMinZoomLevel();

    Location getMyLocation();

    IProjectionDelegate getProjection();

    IUiSettingsDelegate getUiSettings();

    boolean isBuildingsEnabled();

    boolean isIndoorEnabled();

    boolean isMyLocationEnabled();

    boolean isTrafficEnabled();

    void moveCamera(IObjectWrapper arg1);

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

    void resetMinMaxZoomPreference();

    void setBuildingsEnabled(boolean arg1);

    void setContentDescription(String arg1);

    boolean setIndoorEnabled(boolean arg1);

    void setInfoWindowAdapter(com.google.android.gms.maps.internal.zzh arg1);

    void setLatLngBoundsForCameraTarget(LatLngBounds arg1);

    void setLocationSource(ILocationSourceDelegate arg1);

    boolean setMapStyle(MapStyleOptions arg1);

    void setMapType(int arg1);

    void setMaxZoomPreference(float arg1);

    void setMinZoomPreference(float arg1);

    void setMyLocationEnabled(boolean arg1);

    void setOnCameraChangeListener(zzl arg1);

    void setOnCameraIdleListener(com.google.android.gms.maps.internal.zzn arg1);

    void setOnCameraMoveCanceledListener(zzp arg1);

    void setOnCameraMoveListener(zzr arg1);

    void setOnCameraMoveStartedListener(com.google.android.gms.maps.internal.zzt arg1);

    void setOnCircleClickListener(zzv arg1);

    void setOnGroundOverlayClickListener(zzx arg1);

    void setOnIndoorStateChangeListener(com.google.android.gms.maps.internal.zzz arg1);

    void setOnInfoWindowClickListener(zzab arg1);

    void setOnInfoWindowCloseListener(zzad arg1);

    void setOnInfoWindowLongClickListener(zzaf arg1);

    void setOnMapClickListener(zzaj arg1);

    void setOnMapLoadedCallback(zzal arg1);

    void setOnMapLongClickListener(zzan arg1);

    void setOnMarkerClickListener(zzar arg1);

    void setOnMarkerDragListener(zzat arg1);

    void setOnMyLocationButtonClickListener(zzav arg1);

    void setOnMyLocationChangeListener(zzax arg1);

    void setOnMyLocationClickListener(zzaz arg1);

    void setOnPoiClickListener(zzbb arg1);

    void setOnPolygonClickListener(zzbd arg1);

    void setOnPolylineClickListener(zzbf arg1);

    void setPadding(int arg1, int arg2, int arg3, int arg4);

    void setTrafficEnabled(boolean arg1);

    void setWatermarkEnabled(boolean arg1);

    void snapshot(zzbs arg1, IObjectWrapper arg2);

    void snapshotForTest(zzbs arg1);

    void stopAnimation();

    boolean useViewLifecycleWhenInFragment();
}

