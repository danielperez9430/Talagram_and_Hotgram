package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzaa;
import com.google.android.gms.internal.maps.zzac;
import com.google.android.gms.internal.maps.zzad;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.internal.maps.zzh;
import com.google.android.gms.internal.maps.zzi;
import com.google.android.gms.internal.maps.zzk;
import com.google.android.gms.internal.maps.zzl;
import com.google.android.gms.internal.maps.zzn;
import com.google.android.gms.internal.maps.zzo;
import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.internal.maps.zzu;
import com.google.android.gms.internal.maps.zzw;
import com.google.android.gms.internal.maps.zzx;
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

public final class zzg extends zza implements IGoogleMapDelegate {
    zzg(IBinder arg2) {
        super(arg2, "com.google.android.gms.maps.internal.IGoogleMapDelegate");
    }

    public final zzh addCircle(CircleOptions arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        Parcel v2 = ((zza)this).transactAndReadException(35, v0);
        zzh v0_1 = zzi.zzc(v2.readStrongBinder());
        v2.recycle();
        return v0_1;
    }

    public final zzk addGroundOverlay(GroundOverlayOptions arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        Parcel v2 = ((zza)this).transactAndReadException(12, v0);
        zzk v0_1 = zzl.zzd(v2.readStrongBinder());
        v2.recycle();
        return v0_1;
    }

    public final zzt addMarker(MarkerOptions arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        Parcel v2 = ((zza)this).transactAndReadException(11, v0);
        zzt v0_1 = zzu.zzg(v2.readStrongBinder());
        v2.recycle();
        return v0_1;
    }

    public final zzw addPolygon(PolygonOptions arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        Parcel v2 = ((zza)this).transactAndReadException(10, v0);
        zzw v0_1 = zzx.zzh(v2.readStrongBinder());
        v2.recycle();
        return v0_1;
    }

    public final zzz addPolyline(PolylineOptions arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        Parcel v2 = ((zza)this).transactAndReadException(9, v0);
        zzz v0_1 = zzaa.zzi(v2.readStrongBinder());
        v2.recycle();
        return v0_1;
    }

    public final zzac addTileOverlay(TileOverlayOptions arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        Parcel v2 = ((zza)this).transactAndReadException(13, v0);
        zzac v0_1 = zzad.zzj(v2.readStrongBinder());
        v2.recycle();
        return v0_1;
    }

    public final void animateCamera(IObjectWrapper arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(5, v0);
    }

    public final void animateCameraWithCallback(IObjectWrapper arg2, com.google.android.gms.maps.internal.zzc arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        zzc.zza(v0, ((IInterface)arg3));
        ((zza)this).transactAndReadExceptionReturnVoid(6, v0);
    }

    public final void animateCameraWithDurationAndCallback(IObjectWrapper arg2, int arg3, com.google.android.gms.maps.internal.zzc arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        v0.writeInt(arg3);
        zzc.zza(v0, ((IInterface)arg4));
        ((zza)this).transactAndReadExceptionReturnVoid(7, v0);
    }

    public final void clear() {
        ((zza)this).transactAndReadExceptionReturnVoid(14, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final CameraPosition getCameraPosition() {
        Parcel v0 = ((zza)this).transactAndReadException(1, ((zza)this).obtainAndWriteInterfaceToken());
        Parcelable v1 = zzc.zza(v0, CameraPosition.CREATOR);
        v0.recycle();
        return ((CameraPosition)v1);
    }

    public final zzn getFocusedBuilding() {
        Parcel v0 = ((zza)this).transactAndReadException(44, ((zza)this).obtainAndWriteInterfaceToken());
        zzn v1 = zzo.zze(v0.readStrongBinder());
        v0.recycle();
        return v1;
    }

    public final void getMapAsync(zzap arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(53, v0);
    }

    public final int getMapType() {
        Parcel v0 = ((zza)this).transactAndReadException(15, ((zza)this).obtainAndWriteInterfaceToken());
        int v1 = v0.readInt();
        v0.recycle();
        return v1;
    }

    public final float getMaxZoomLevel() {
        Parcel v0 = ((zza)this).transactAndReadException(2, ((zza)this).obtainAndWriteInterfaceToken());
        float v1 = v0.readFloat();
        v0.recycle();
        return v1;
    }

    public final float getMinZoomLevel() {
        Parcel v0 = ((zza)this).transactAndReadException(3, ((zza)this).obtainAndWriteInterfaceToken());
        float v1 = v0.readFloat();
        v0.recycle();
        return v1;
    }

    public final Location getMyLocation() {
        Parcel v0 = ((zza)this).transactAndReadException(23, ((zza)this).obtainAndWriteInterfaceToken());
        Parcelable v1 = zzc.zza(v0, Location.CREATOR);
        v0.recycle();
        return ((Location)v1);
    }

    public final IProjectionDelegate getProjection() {
        IInterface v1_2;
        Parcel v0 = ((zza)this).transactAndReadException(26, ((zza)this).obtainAndWriteInterfaceToken());
        IBinder v1 = v0.readStrongBinder();
        if(v1 == null) {
            IProjectionDelegate v1_1 = null;
        }
        else {
            IInterface v2 = v1.queryLocalInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
            if((v2 instanceof IProjectionDelegate)) {
                v1_2 = v2;
            }
            else {
                zzbr v1_3 = new zzbr(v1);
            }
        }

        v0.recycle();
        return ((IProjectionDelegate)v1_2);
    }

    public final IUiSettingsDelegate getUiSettings() {
        zzbx v1_3;
        Parcel v0 = ((zza)this).transactAndReadException(25, ((zza)this).obtainAndWriteInterfaceToken());
        IBinder v1 = v0.readStrongBinder();
        if(v1 == null) {
            IUiSettingsDelegate v1_1 = null;
        }
        else {
            IInterface v2 = v1.queryLocalInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            if((v2 instanceof IUiSettingsDelegate)) {
                IInterface v1_2 = v2;
            }
            else {
                v1_3 = new zzbx(v1);
            }
        }

        v0.recycle();
        return ((IUiSettingsDelegate)v1_3);
    }

    public final boolean isBuildingsEnabled() {
        Parcel v0 = ((zza)this).transactAndReadException(40, ((zza)this).obtainAndWriteInterfaceToken());
        boolean v1 = zzc.zza(v0);
        v0.recycle();
        return v1;
    }

    public final boolean isIndoorEnabled() {
        Parcel v0 = ((zza)this).transactAndReadException(19, ((zza)this).obtainAndWriteInterfaceToken());
        boolean v1 = zzc.zza(v0);
        v0.recycle();
        return v1;
    }

    public final boolean isMyLocationEnabled() {
        Parcel v0 = ((zza)this).transactAndReadException(21, ((zza)this).obtainAndWriteInterfaceToken());
        boolean v1 = zzc.zza(v0);
        v0.recycle();
        return v1;
    }

    public final boolean isTrafficEnabled() {
        Parcel v0 = ((zza)this).transactAndReadException(17, ((zza)this).obtainAndWriteInterfaceToken());
        boolean v1 = zzc.zza(v0);
        v0.recycle();
        return v1;
    }

    public final void moveCamera(IObjectWrapper arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(4, v0);
    }

    public final void onCreate(Bundle arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(54, v0);
    }

    public final void onDestroy() {
        ((zza)this).transactAndReadExceptionReturnVoid(57, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void onEnterAmbient(Bundle arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(81, v0);
    }

    public final void onExitAmbient() {
        ((zza)this).transactAndReadExceptionReturnVoid(82, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void onLowMemory() {
        ((zza)this).transactAndReadExceptionReturnVoid(58, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void onPause() {
        ((zza)this).transactAndReadExceptionReturnVoid(56, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void onResume() {
        ((zza)this).transactAndReadExceptionReturnVoid(55, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void onSaveInstanceState(Bundle arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg3));
        v0 = ((zza)this).transactAndReadException(60, v0);
        if(v0.readInt() != 0) {
            arg3.readFromParcel(v0);
        }

        v0.recycle();
    }

    public final void onStart() {
        ((zza)this).transactAndReadExceptionReturnVoid(101, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void onStop() {
        ((zza)this).transactAndReadExceptionReturnVoid(102, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void resetMinMaxZoomPreference() {
        ((zza)this).transactAndReadExceptionReturnVoid(94, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void setBuildingsEnabled(boolean arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(41, v0);
    }

    public final void setContentDescription(String arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeString(arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(61, v0);
    }

    public final boolean setIndoorEnabled(boolean arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, arg2);
        Parcel v2 = ((zza)this).transactAndReadException(20, v0);
        boolean v0_1 = zzc.zza(v2);
        v2.recycle();
        return v0_1;
    }

    public final void setInfoWindowAdapter(com.google.android.gms.maps.internal.zzh arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(33, v0);
    }

    public final void setLatLngBoundsForCameraTarget(LatLngBounds arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(95, v0);
    }

    public final void setLocationSource(ILocationSourceDelegate arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(24, v0);
    }

    public final boolean setMapStyle(MapStyleOptions arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        Parcel v2 = ((zza)this).transactAndReadException(91, v0);
        boolean v0_1 = zzc.zza(v2);
        v2.recycle();
        return v0_1;
    }

    public final void setMapType(int arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeInt(arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(16, v0);
    }

    public final void setMaxZoomPreference(float arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeFloat(arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(93, v0);
    }

    public final void setMinZoomPreference(float arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeFloat(arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(92, v0);
    }

    public final void setMyLocationEnabled(boolean arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(22, v0);
    }

    public final void setOnCameraChangeListener(com.google.android.gms.maps.internal.zzl arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(27, v0);
    }

    public final void setOnCameraIdleListener(com.google.android.gms.maps.internal.zzn arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(99, v0);
    }

    public final void setOnCameraMoveCanceledListener(zzp arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(98, v0);
    }

    public final void setOnCameraMoveListener(zzr arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(97, v0);
    }

    public final void setOnCameraMoveStartedListener(com.google.android.gms.maps.internal.zzt arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(96, v0);
    }

    public final void setOnCircleClickListener(zzv arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(89, v0);
    }

    public final void setOnGroundOverlayClickListener(com.google.android.gms.maps.internal.zzx arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(83, v0);
    }

    public final void setOnIndoorStateChangeListener(com.google.android.gms.maps.internal.zzz arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(45, v0);
    }

    public final void setOnInfoWindowClickListener(zzab arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(32, v0);
    }

    public final void setOnInfoWindowCloseListener(com.google.android.gms.maps.internal.zzad arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(86, v0);
    }

    public final void setOnInfoWindowLongClickListener(zzaf arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(84, v0);
    }

    public final void setOnMapClickListener(zzaj arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(28, v0);
    }

    public final void setOnMapLoadedCallback(zzal arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(42, v0);
    }

    public final void setOnMapLongClickListener(zzan arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(29, v0);
    }

    public final void setOnMarkerClickListener(zzar arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(30, v0);
    }

    public final void setOnMarkerDragListener(zzat arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(31, v0);
    }

    public final void setOnMyLocationButtonClickListener(zzav arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(37, v0);
    }

    public final void setOnMyLocationChangeListener(zzax arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(36, v0);
    }

    public final void setOnMyLocationClickListener(zzaz arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(107, v0);
    }

    public final void setOnPoiClickListener(zzbb arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(80, v0);
    }

    public final void setOnPolygonClickListener(zzbd arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(85, v0);
    }

    public final void setOnPolylineClickListener(zzbf arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(87, v0);
    }

    public final void setPadding(int arg2, int arg3, int arg4, int arg5) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeInt(arg2);
        v0.writeInt(arg3);
        v0.writeInt(arg4);
        v0.writeInt(arg5);
        ((zza)this).transactAndReadExceptionReturnVoid(39, v0);
    }

    public final void setTrafficEnabled(boolean arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(18, v0);
    }

    public final void setWatermarkEnabled(boolean arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(51, v0);
    }

    public final void snapshot(zzbs arg2, IObjectWrapper arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        zzc.zza(v0, ((IInterface)arg3));
        ((zza)this).transactAndReadExceptionReturnVoid(38, v0);
    }

    public final void snapshotForTest(zzbs arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(71, v0);
    }

    public final void stopAnimation() {
        ((zza)this).transactAndReadExceptionReturnVoid(8, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final boolean useViewLifecycleWhenInFragment() {
        Parcel v0 = ((zza)this).transactAndReadException(59, ((zza)this).obtainAndWriteInterfaceToken());
        boolean v1 = zzc.zza(v0);
        v0.recycle();
        return v1;
    }
}

