package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.StreetViewSource;

public final class zzbu extends zza implements IStreetViewPanoramaDelegate {
    zzbu(IBinder arg2) {
        super(arg2, "com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
    }

    public final void animateTo(StreetViewPanoramaCamera arg2, long arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        v0.writeLong(arg3);
        ((zza)this).transactAndReadExceptionReturnVoid(9, v0);
    }

    public final void enablePanning(boolean arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(2, v0);
    }

    public final void enableStreetNames(boolean arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(4, v0);
    }

    public final void enableUserNavigation(boolean arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(3, v0);
    }

    public final void enableZoom(boolean arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(1, v0);
    }

    public final StreetViewPanoramaCamera getPanoramaCamera() {
        Parcel v0 = ((zza)this).transactAndReadException(10, ((zza)this).obtainAndWriteInterfaceToken());
        Parcelable v1 = zzc.zza(v0, StreetViewPanoramaCamera.CREATOR);
        v0.recycle();
        return ((StreetViewPanoramaCamera)v1);
    }

    public final StreetViewPanoramaLocation getStreetViewPanoramaLocation() {
        Parcel v0 = ((zza)this).transactAndReadException(14, ((zza)this).obtainAndWriteInterfaceToken());
        Parcelable v1 = zzc.zza(v0, StreetViewPanoramaLocation.CREATOR);
        v0.recycle();
        return ((StreetViewPanoramaLocation)v1);
    }

    public final boolean isPanningGesturesEnabled() {
        Parcel v0 = ((zza)this).transactAndReadException(6, ((zza)this).obtainAndWriteInterfaceToken());
        boolean v1 = zzc.zza(v0);
        v0.recycle();
        return v1;
    }

    public final boolean isStreetNamesEnabled() {
        Parcel v0 = ((zza)this).transactAndReadException(8, ((zza)this).obtainAndWriteInterfaceToken());
        boolean v1 = zzc.zza(v0);
        v0.recycle();
        return v1;
    }

    public final boolean isUserNavigationEnabled() {
        Parcel v0 = ((zza)this).transactAndReadException(7, ((zza)this).obtainAndWriteInterfaceToken());
        boolean v1 = zzc.zza(v0);
        v0.recycle();
        return v1;
    }

    public final boolean isZoomGesturesEnabled() {
        Parcel v0 = ((zza)this).transactAndReadException(5, ((zza)this).obtainAndWriteInterfaceToken());
        boolean v1 = zzc.zza(v0);
        v0.recycle();
        return v1;
    }

    public final IObjectWrapper orientationToPoint(StreetViewPanoramaOrientation arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        Parcel v2 = ((zza)this).transactAndReadException(19, v0);
        IObjectWrapper v0_1 = Stub.asInterface(v2.readStrongBinder());
        v2.recycle();
        return v0_1;
    }

    public final StreetViewPanoramaOrientation pointToOrientation(IObjectWrapper arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        Parcel v2 = ((zza)this).transactAndReadException(18, v0);
        Parcelable v0_1 = zzc.zza(v2, StreetViewPanoramaOrientation.CREATOR);
        v2.recycle();
        return ((StreetViewPanoramaOrientation)v0_1);
    }

    public final void setOnStreetViewPanoramaCameraChangeListener(zzbh arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(16, v0);
    }

    public final void setOnStreetViewPanoramaChangeListener(zzbj arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(15, v0);
    }

    public final void setOnStreetViewPanoramaClickListener(zzbl arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(17, v0);
    }

    public final void setOnStreetViewPanoramaLongClickListener(zzbn arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(20, v0);
    }

    public final void setPosition(LatLng arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(12, v0);
    }

    public final void setPositionWithID(String arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeString(arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(11, v0);
    }

    public final void setPositionWithRadius(LatLng arg2, int arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        v0.writeInt(arg3);
        ((zza)this).transactAndReadExceptionReturnVoid(13, v0);
    }

    public final void setPositionWithRadiusAndSource(LatLng arg2, int arg3, StreetViewSource arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        v0.writeInt(arg3);
        zzc.zza(v0, ((Parcelable)arg4));
        ((zza)this).transactAndReadExceptionReturnVoid(22, v0);
    }

    public final void setPositionWithSource(LatLng arg2, StreetViewSource arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        zzc.zza(v0, ((Parcelable)arg3));
        ((zza)this).transactAndReadExceptionReturnVoid(21, v0);
    }
}

