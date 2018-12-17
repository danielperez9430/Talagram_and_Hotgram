package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;

public final class zzk extends zza implements IMapViewDelegate {
    zzk(IBinder arg2) {
        super(arg2, "com.google.android.gms.maps.internal.IMapViewDelegate");
    }

    public final IGoogleMapDelegate getMap() {
        IInterface v1_2;
        Parcel v0 = ((zza)this).transactAndReadException(1, ((zza)this).obtainAndWriteInterfaceToken());
        IBinder v1 = v0.readStrongBinder();
        if(v1 == null) {
            IGoogleMapDelegate v1_1 = null;
        }
        else {
            IInterface v2 = v1.queryLocalInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if((v2 instanceof IGoogleMapDelegate)) {
                v1_2 = v2;
            }
            else {
                zzg v1_3 = new zzg(v1);
            }
        }

        v0.recycle();
        return ((IGoogleMapDelegate)v1_2);
    }

    public final void getMapAsync(zzap arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(9, v0);
    }

    public final IObjectWrapper getView() {
        Parcel v0 = ((zza)this).transactAndReadException(8, ((zza)this).obtainAndWriteInterfaceToken());
        IObjectWrapper v1 = Stub.asInterface(v0.readStrongBinder());
        v0.recycle();
        return v1;
    }

    public final void onCreate(Bundle arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(2, v0);
    }

    public final void onDestroy() {
        ((zza)this).transactAndReadExceptionReturnVoid(5, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void onEnterAmbient(Bundle arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(10, v0);
    }

    public final void onExitAmbient() {
        ((zza)this).transactAndReadExceptionReturnVoid(11, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void onLowMemory() {
        ((zza)this).transactAndReadExceptionReturnVoid(6, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void onPause() {
        ((zza)this).transactAndReadExceptionReturnVoid(4, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void onResume() {
        ((zza)this).transactAndReadExceptionReturnVoid(3, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void onSaveInstanceState(Bundle arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg3));
        v0 = ((zza)this).transactAndReadException(7, v0);
        if(v0.readInt() != 0) {
            arg3.readFromParcel(v0);
        }

        v0.recycle();
    }

    public final void onStart() {
        ((zza)this).transactAndReadExceptionReturnVoid(12, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void onStop() {
        ((zza)this).transactAndReadExceptionReturnVoid(13, ((zza)this).obtainAndWriteInterfaceToken());
    }
}

