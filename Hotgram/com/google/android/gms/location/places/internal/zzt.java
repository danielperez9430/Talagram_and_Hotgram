package com.google.android.gms.location.places.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.places.zzb;
import com.google.android.gms.internal.places.zzd;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceReport;

public final class zzt extends zzb implements zzs {
    zzt(IBinder arg2) {
        super(arg2, "com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
    }

    public final void zzb(PlaceFilter arg2, zzat arg3, zzy arg4) {
        Parcel v0 = ((zzb)this).obtainAndWriteInterfaceToken();
        zzd.zzb(v0, ((Parcelable)arg2));
        zzd.zzb(v0, ((Parcelable)arg3));
        zzd.zzb(v0, ((IInterface)arg4));
        ((zzb)this).transactAndReadExceptionReturnVoid(6, v0);
    }

    public final void zzb(PlaceReport arg2, zzat arg3, zzy arg4) {
        Parcel v0 = ((zzb)this).obtainAndWriteInterfaceToken();
        zzd.zzb(v0, ((Parcelable)arg2));
        zzd.zzb(v0, ((Parcelable)arg3));
        zzd.zzb(v0, ((IInterface)arg4));
        ((zzb)this).transactAndReadExceptionReturnVoid(7, v0);
    }
}

