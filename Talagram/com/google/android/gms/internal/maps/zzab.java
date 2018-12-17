package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.ArrayList;
import java.util.List;

public final class zzab extends zza implements zzz {
    zzab(IBinder arg2) {
        super(arg2, "com.google.android.gms.maps.model.internal.IPolylineDelegate");
    }

    public final int getColor() {
        Parcel v0 = ((zza)this).transactAndReadException(8, ((zza)this).obtainAndWriteInterfaceToken());
        int v1 = v0.readInt();
        v0.recycle();
        return v1;
    }

    public final Cap getEndCap() {
        Parcel v0 = ((zza)this).transactAndReadException(22, ((zza)this).obtainAndWriteInterfaceToken());
        Parcelable v1 = zzc.zza(v0, Cap.CREATOR);
        v0.recycle();
        return ((Cap)v1);
    }

    public final String getId() {
        Parcel v0 = ((zza)this).transactAndReadException(2, ((zza)this).obtainAndWriteInterfaceToken());
        String v1 = v0.readString();
        v0.recycle();
        return v1;
    }

    public final int getJointType() {
        Parcel v0 = ((zza)this).transactAndReadException(24, ((zza)this).obtainAndWriteInterfaceToken());
        int v1 = v0.readInt();
        v0.recycle();
        return v1;
    }

    public final List getPattern() {
        Parcel v0 = ((zza)this).transactAndReadException(26, ((zza)this).obtainAndWriteInterfaceToken());
        ArrayList v1 = v0.createTypedArrayList(PatternItem.CREATOR);
        v0.recycle();
        return ((List)v1);
    }

    public final List getPoints() {
        Parcel v0 = ((zza)this).transactAndReadException(4, ((zza)this).obtainAndWriteInterfaceToken());
        ArrayList v1 = v0.createTypedArrayList(LatLng.CREATOR);
        v0.recycle();
        return ((List)v1);
    }

    public final Cap getStartCap() {
        Parcel v0 = ((zza)this).transactAndReadException(20, ((zza)this).obtainAndWriteInterfaceToken());
        Parcelable v1 = zzc.zza(v0, Cap.CREATOR);
        v0.recycle();
        return ((Cap)v1);
    }

    public final float getWidth() {
        Parcel v0 = ((zza)this).transactAndReadException(6, ((zza)this).obtainAndWriteInterfaceToken());
        float v1 = v0.readFloat();
        v0.recycle();
        return v1;
    }

    public final float getZIndex() {
        Parcel v0 = ((zza)this).transactAndReadException(10, ((zza)this).obtainAndWriteInterfaceToken());
        float v1 = v0.readFloat();
        v0.recycle();
        return v1;
    }

    public final boolean isClickable() {
        Parcel v0 = ((zza)this).transactAndReadException(18, ((zza)this).obtainAndWriteInterfaceToken());
        boolean v1 = zzc.zza(v0);
        v0.recycle();
        return v1;
    }

    public final boolean isGeodesic() {
        Parcel v0 = ((zza)this).transactAndReadException(14, ((zza)this).obtainAndWriteInterfaceToken());
        boolean v1 = zzc.zza(v0);
        v0.recycle();
        return v1;
    }

    public final boolean isVisible() {
        Parcel v0 = ((zza)this).transactAndReadException(12, ((zza)this).obtainAndWriteInterfaceToken());
        boolean v1 = zzc.zza(v0);
        v0.recycle();
        return v1;
    }

    public final void remove() {
        ((zza)this).transactAndReadExceptionReturnVoid(1, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void setClickable(boolean arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(17, v0);
    }

    public final void setColor(int arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeInt(arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(7, v0);
    }

    public final void setEndCap(Cap arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(21, v0);
    }

    public final void setGeodesic(boolean arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(13, v0);
    }

    public final void setJointType(int arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeInt(arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(23, v0);
    }

    public final void setPattern(List arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeTypedList(arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(25, v0);
    }

    public final void setPoints(List arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeTypedList(arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(3, v0);
    }

    public final void setStartCap(Cap arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(19, v0);
    }

    public final void setVisible(boolean arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(11, v0);
    }

    public final void setWidth(float arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeFloat(arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(5, v0);
    }

    public final void setZIndex(float arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeFloat(arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(9, v0);
    }

    public final boolean zzb(zzz arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        Parcel v2 = ((zza)this).transactAndReadException(15, v0);
        boolean v0_1 = zzc.zza(v2);
        v2.recycle();
        return v0_1;
    }

    public final void zze(IObjectWrapper arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(27, v0);
    }

    public final int zzi() {
        Parcel v0 = ((zza)this).transactAndReadException(16, ((zza)this).obtainAndWriteInterfaceToken());
        int v1 = v0.readInt();
        v0.recycle();
        return v1;
    }

    public final IObjectWrapper zzj() {
        Parcel v0 = ((zza)this).transactAndReadException(28, ((zza)this).obtainAndWriteInterfaceToken());
        IObjectWrapper v1 = Stub.asInterface(v0.readStrongBinder());
        v0.recycle();
        return v1;
    }
}

