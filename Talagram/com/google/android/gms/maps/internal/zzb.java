package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public final class zzb extends zza implements ICameraUpdateFactoryDelegate {
    zzb(IBinder arg2) {
        super(arg2, "com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
    }

    public final IObjectWrapper newCameraPosition(CameraPosition arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        Parcel v2 = ((zza)this).transactAndReadException(7, v0);
        IObjectWrapper v0_1 = Stub.asInterface(v2.readStrongBinder());
        v2.recycle();
        return v0_1;
    }

    public final IObjectWrapper newLatLng(LatLng arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        Parcel v2 = ((zza)this).transactAndReadException(8, v0);
        IObjectWrapper v0_1 = Stub.asInterface(v2.readStrongBinder());
        v2.recycle();
        return v0_1;
    }

    public final IObjectWrapper newLatLngBounds(LatLngBounds arg2, int arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        v0.writeInt(arg3);
        Parcel v2 = ((zza)this).transactAndReadException(10, v0);
        IObjectWrapper v3 = Stub.asInterface(v2.readStrongBinder());
        v2.recycle();
        return v3;
    }

    public final IObjectWrapper newLatLngBoundsWithSize(LatLngBounds arg2, int arg3, int arg4, int arg5) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        v0.writeInt(arg3);
        v0.writeInt(arg4);
        v0.writeInt(arg5);
        Parcel v2 = ((zza)this).transactAndReadException(11, v0);
        IObjectWrapper v3 = Stub.asInterface(v2.readStrongBinder());
        v2.recycle();
        return v3;
    }

    public final IObjectWrapper newLatLngZoom(LatLng arg2, float arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        v0.writeFloat(arg3);
        Parcel v2 = ((zza)this).transactAndReadException(9, v0);
        IObjectWrapper v3 = Stub.asInterface(v2.readStrongBinder());
        v2.recycle();
        return v3;
    }

    public final IObjectWrapper scrollBy(float arg2, float arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeFloat(arg2);
        v0.writeFloat(arg3);
        Parcel v2 = ((zza)this).transactAndReadException(3, v0);
        IObjectWrapper v3 = Stub.asInterface(v2.readStrongBinder());
        v2.recycle();
        return v3;
    }

    public final IObjectWrapper zoomBy(float arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeFloat(arg2);
        Parcel v2 = ((zza)this).transactAndReadException(5, v0);
        IObjectWrapper v0_1 = Stub.asInterface(v2.readStrongBinder());
        v2.recycle();
        return v0_1;
    }

    public final IObjectWrapper zoomByWithFocus(float arg2, int arg3, int arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeFloat(arg2);
        v0.writeInt(arg3);
        v0.writeInt(arg4);
        Parcel v2 = ((zza)this).transactAndReadException(6, v0);
        IObjectWrapper v3 = Stub.asInterface(v2.readStrongBinder());
        v2.recycle();
        return v3;
    }

    public final IObjectWrapper zoomIn() {
        Parcel v0 = ((zza)this).transactAndReadException(1, ((zza)this).obtainAndWriteInterfaceToken());
        IObjectWrapper v1 = Stub.asInterface(v0.readStrongBinder());
        v0.recycle();
        return v1;
    }

    public final IObjectWrapper zoomOut() {
        Parcel v0 = ((zza)this).transactAndReadException(2, ((zza)this).obtainAndWriteInterfaceToken());
        IObjectWrapper v1 = Stub.asInterface(v0.readStrongBinder());
        v0.recycle();
        return v1;
    }

    public final IObjectWrapper zoomTo(float arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeFloat(arg2);
        Parcel v2 = ((zza)this).transactAndReadException(4, v0);
        IObjectWrapper v0_1 = Stub.asInterface(v2.readStrongBinder());
        v2.recycle();
        return v0_1;
    }
}

