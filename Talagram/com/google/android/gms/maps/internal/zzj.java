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
import com.google.android.gms.maps.GoogleMapOptions;

public final class zzj extends zza implements IMapFragmentDelegate {
    zzj(IBinder arg2) {
        super(arg2, "com.google.android.gms.maps.internal.IMapFragmentDelegate");
    }

    public final IGoogleMapDelegate getMap() {
        IGoogleMapDelegate v1_1;
        Parcel v0 = ((zza)this).transactAndReadException(1, ((zza)this).obtainAndWriteInterfaceToken());
        IBinder v1 = v0.readStrongBinder();
        if(v1 == null) {
            v1_1 = null;
        }
        else {
            IInterface v2 = v1.queryLocalInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if((v2 instanceof IGoogleMapDelegate)) {
                IInterface v1_2 = v2;
            }
            else {
                zzg v1_3 = new zzg(v1);
            }
        }

        v0.recycle();
        return v1_1;
    }

    public final void getMapAsync(zzap arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(12, v0);
    }

    public final boolean isReady() {
        Parcel v0 = ((zza)this).transactAndReadException(11, ((zza)this).obtainAndWriteInterfaceToken());
        boolean v1 = zzc.zza(v0);
        v0.recycle();
        return v1;
    }

    public final void onCreate(Bundle arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(3, v0);
    }

    public final IObjectWrapper onCreateView(IObjectWrapper arg2, IObjectWrapper arg3, Bundle arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        zzc.zza(v0, ((IInterface)arg3));
        zzc.zza(v0, ((Parcelable)arg4));
        Parcel v2 = ((zza)this).transactAndReadException(4, v0);
        arg3 = Stub.asInterface(v2.readStrongBinder());
        v2.recycle();
        return arg3;
    }

    public final void onDestroy() {
        ((zza)this).transactAndReadExceptionReturnVoid(8, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void onDestroyView() {
        ((zza)this).transactAndReadExceptionReturnVoid(7, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void onEnterAmbient(Bundle arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(13, v0);
    }

    public final void onExitAmbient() {
        ((zza)this).transactAndReadExceptionReturnVoid(14, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void onInflate(IObjectWrapper arg2, GoogleMapOptions arg3, Bundle arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        zzc.zza(v0, ((Parcelable)arg3));
        zzc.zza(v0, ((Parcelable)arg4));
        ((zza)this).transactAndReadExceptionReturnVoid(2, v0);
    }

    public final void onLowMemory() {
        ((zza)this).transactAndReadExceptionReturnVoid(9, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void onPause() {
        ((zza)this).transactAndReadExceptionReturnVoid(6, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void onResume() {
        ((zza)this).transactAndReadExceptionReturnVoid(5, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void onSaveInstanceState(Bundle arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg3));
        v0 = ((zza)this).transactAndReadException(10, v0);
        if(v0.readInt() != 0) {
            arg3.readFromParcel(v0);
        }

        v0.recycle();
    }

    public final void onStart() {
        ((zza)this).transactAndReadExceptionReturnVoid(15, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void onStop() {
        ((zza)this).transactAndReadExceptionReturnVoid(16, ((zza)this).obtainAndWriteInterfaceToken());
    }
}

