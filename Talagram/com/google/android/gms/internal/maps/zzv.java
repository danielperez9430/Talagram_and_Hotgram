package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;

public final class zzv extends zza implements zzt {
    zzv(IBinder arg2) {
        super(arg2, "com.google.android.gms.maps.model.internal.IMarkerDelegate");
    }

    public final float getAlpha() {
        Parcel v0 = ((zza)this).transactAndReadException(26, ((zza)this).obtainAndWriteInterfaceToken());
        float v1 = v0.readFloat();
        v0.recycle();
        return v1;
    }

    public final String getId() {
        Parcel v0 = ((zza)this).transactAndReadException(2, ((zza)this).obtainAndWriteInterfaceToken());
        String v1 = v0.readString();
        v0.recycle();
        return v1;
    }

    public final LatLng getPosition() {
        Parcel v0 = ((zza)this).transactAndReadException(4, ((zza)this).obtainAndWriteInterfaceToken());
        Parcelable v1 = zzc.zza(v0, LatLng.CREATOR);
        v0.recycle();
        return ((LatLng)v1);
    }

    public final float getRotation() {
        Parcel v0 = ((zza)this).transactAndReadException(23, ((zza)this).obtainAndWriteInterfaceToken());
        float v1 = v0.readFloat();
        v0.recycle();
        return v1;
    }

    public final String getSnippet() {
        Parcel v0 = ((zza)this).transactAndReadException(8, ((zza)this).obtainAndWriteInterfaceToken());
        String v1 = v0.readString();
        v0.recycle();
        return v1;
    }

    public final String getTitle() {
        Parcel v0 = ((zza)this).transactAndReadException(6, ((zza)this).obtainAndWriteInterfaceToken());
        String v1 = v0.readString();
        v0.recycle();
        return v1;
    }

    public final float getZIndex() {
        Parcel v0 = ((zza)this).transactAndReadException(28, ((zza)this).obtainAndWriteInterfaceToken());
        float v1 = v0.readFloat();
        v0.recycle();
        return v1;
    }

    public final void hideInfoWindow() {
        ((zza)this).transactAndReadExceptionReturnVoid(12, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final boolean isDraggable() {
        Parcel v0 = ((zza)this).transactAndReadException(10, ((zza)this).obtainAndWriteInterfaceToken());
        boolean v1 = zzc.zza(v0);
        v0.recycle();
        return v1;
    }

    public final boolean isFlat() {
        Parcel v0 = ((zza)this).transactAndReadException(21, ((zza)this).obtainAndWriteInterfaceToken());
        boolean v1 = zzc.zza(v0);
        v0.recycle();
        return v1;
    }

    public final boolean isInfoWindowShown() {
        Parcel v0 = ((zza)this).transactAndReadException(13, ((zza)this).obtainAndWriteInterfaceToken());
        boolean v1 = zzc.zza(v0);
        v0.recycle();
        return v1;
    }

    public final boolean isVisible() {
        Parcel v0 = ((zza)this).transactAndReadException(15, ((zza)this).obtainAndWriteInterfaceToken());
        boolean v1 = zzc.zza(v0);
        v0.recycle();
        return v1;
    }

    public final void remove() {
        ((zza)this).transactAndReadExceptionReturnVoid(1, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void setAlpha(float arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeFloat(arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(25, v0);
    }

    public final void setAnchor(float arg2, float arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeFloat(arg2);
        v0.writeFloat(arg3);
        ((zza)this).transactAndReadExceptionReturnVoid(19, v0);
    }

    public final void setDraggable(boolean arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(9, v0);
    }

    public final void setFlat(boolean arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(20, v0);
    }

    public final void setInfoWindowAnchor(float arg2, float arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeFloat(arg2);
        v0.writeFloat(arg3);
        ((zza)this).transactAndReadExceptionReturnVoid(24, v0);
    }

    public final void setPosition(LatLng arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(3, v0);
    }

    public final void setRotation(float arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeFloat(arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(22, v0);
    }

    public final void setSnippet(String arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeString(arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(7, v0);
    }

    public final void setTitle(String arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeString(arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(5, v0);
    }

    public final void setVisible(boolean arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(14, v0);
    }

    public final void setZIndex(float arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeFloat(arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(27, v0);
    }

    public final void showInfoWindow() {
        ((zza)this).transactAndReadExceptionReturnVoid(11, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void zze(IObjectWrapper arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(29, v0);
    }

    public final void zzg(IObjectWrapper arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(18, v0);
    }

    public final int zzi() {
        Parcel v0 = ((zza)this).transactAndReadException(17, ((zza)this).obtainAndWriteInterfaceToken());
        int v1 = v0.readInt();
        v0.recycle();
        return v1;
    }

    public final IObjectWrapper zzj() {
        Parcel v0 = ((zza)this).transactAndReadException(30, ((zza)this).obtainAndWriteInterfaceToken());
        IObjectWrapper v1 = Stub.asInterface(v0.readStrongBinder());
        v0.recycle();
        return v1;
    }

    public final boolean zzj(zzt arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        Parcel v2 = ((zza)this).transactAndReadException(16, v0);
        boolean v0_1 = zzc.zza(v2);
        v2.recycle();
        return v0_1;
    }
}

