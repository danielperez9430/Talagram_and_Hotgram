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
import com.google.android.gms.maps.model.VisibleRegion;

public final class zzbr extends zza implements IProjectionDelegate {
    zzbr(IBinder arg2) {
        super(arg2, "com.google.android.gms.maps.internal.IProjectionDelegate");
    }

    public final LatLng fromScreenLocation(IObjectWrapper arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        Parcel v2 = ((zza)this).transactAndReadException(1, v0);
        Parcelable v0_1 = zzc.zza(v2, LatLng.CREATOR);
        v2.recycle();
        return ((LatLng)v0_1);
    }

    public final VisibleRegion getVisibleRegion() {
        Parcel v0 = ((zza)this).transactAndReadException(3, ((zza)this).obtainAndWriteInterfaceToken());
        Parcelable v1 = zzc.zza(v0, VisibleRegion.CREATOR);
        v0.recycle();
        return ((VisibleRegion)v1);
    }

    public final IObjectWrapper toScreenLocation(LatLng arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        Parcel v2 = ((zza)this).transactAndReadException(2, v0);
        IObjectWrapper v0_1 = Stub.asInterface(v2.readStrongBinder());
        v2.recycle();
        return v0_1;
    }
}

