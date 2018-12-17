package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.ArrayList;
import java.util.List;

public final class zzj extends zza implements zzh {
    zzj(IBinder arg2) {
        super(arg2, "com.google.android.gms.maps.model.internal.ICircleDelegate");
    }

    public final LatLng getCenter() {
        Parcel v0 = ((zza)this).transactAndReadException(4, ((zza)this).obtainAndWriteInterfaceToken());
        Parcelable v1 = zzc.zza(v0, LatLng.CREATOR);
        v0.recycle();
        return ((LatLng)v1);
    }

    public final int getFillColor() {
        Parcel v0 = ((zza)this).transactAndReadException(12, ((zza)this).obtainAndWriteInterfaceToken());
        int v1 = v0.readInt();
        v0.recycle();
        return v1;
    }

    public final String getId() {
        Parcel v0 = ((zza)this).transactAndReadException(2, ((zza)this).obtainAndWriteInterfaceToken());
        String v1 = v0.readString();
        v0.recycle();
        return v1;
    }

    public final double getRadius() {
        Parcel v0 = ((zza)this).transactAndReadException(6, ((zza)this).obtainAndWriteInterfaceToken());
        double v1 = v0.readDouble();
        v0.recycle();
        return v1;
    }

    public final int getStrokeColor() {
        Parcel v0 = ((zza)this).transactAndReadException(10, ((zza)this).obtainAndWriteInterfaceToken());
        int v1 = v0.readInt();
        v0.recycle();
        return v1;
    }

    public final List getStrokePattern() {
        Parcel v0 = ((zza)this).transactAndReadException(22, ((zza)this).obtainAndWriteInterfaceToken());
        ArrayList v1 = v0.createTypedArrayList(PatternItem.CREATOR);
        v0.recycle();
        return ((List)v1);
    }

    public final float getStrokeWidth() {
        Parcel v0 = ((zza)this).transactAndReadException(8, ((zza)this).obtainAndWriteInterfaceToken());
        float v1 = v0.readFloat();
        v0.recycle();
        return v1;
    }

    public final float getZIndex() {
        Parcel v0 = ((zza)this).transactAndReadException(14, ((zza)this).obtainAndWriteInterfaceToken());
        float v1 = v0.readFloat();
        v0.recycle();
        return v1;
    }

    public final boolean isClickable() {
        Parcel v0 = ((zza)this).transactAndReadException(20, ((zza)this).obtainAndWriteInterfaceToken());
        boolean v1 = zzc.zza(v0);
        v0.recycle();
        return v1;
    }

    public final boolean isVisible() {
        Parcel v0 = ((zza)this).transactAndReadException(16, ((zza)this).obtainAndWriteInterfaceToken());
        boolean v1 = zzc.zza(v0);
        v0.recycle();
        return v1;
    }

    public final void remove() {
        ((zza)this).transactAndReadExceptionReturnVoid(1, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void setCenter(LatLng arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(3, v0);
    }

    public final void setClickable(boolean arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(19, v0);
    }

    public final void setFillColor(int arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeInt(arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(11, v0);
    }

    public final void setRadius(double arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeDouble(arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(5, v0);
    }

    public final void setStrokeColor(int arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeInt(arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(9, v0);
    }

    public final void setStrokePattern(List arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeTypedList(arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(21, v0);
    }

    public final void setStrokeWidth(float arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeFloat(arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(7, v0);
    }

    public final void setVisible(boolean arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(15, v0);
    }

    public final void setZIndex(float arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeFloat(arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(13, v0);
    }

    public final boolean zzb(zzh arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        Parcel v2 = ((zza)this).transactAndReadException(17, v0);
        boolean v0_1 = zzc.zza(v2);
        v2.recycle();
        return v0_1;
    }

    public final void zze(IObjectWrapper arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(23, v0);
    }

    public final int zzi() {
        Parcel v0 = ((zza)this).transactAndReadException(18, ((zza)this).obtainAndWriteInterfaceToken());
        int v1 = v0.readInt();
        v0.recycle();
        return v1;
    }

    public final IObjectWrapper zzj() {
        Parcel v0 = ((zza)this).transactAndReadException(24, ((zza)this).obtainAndWriteInterfaceToken());
        IObjectWrapper v1 = Stub.asInterface(v0.readStrongBinder());
        v0.recycle();
        return v1;
    }
}

