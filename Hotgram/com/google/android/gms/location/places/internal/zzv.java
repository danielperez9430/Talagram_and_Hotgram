package com.google.android.gms.location.places.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.places.zzb;
import com.google.android.gms.internal.places.zzd;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;

public final class zzv extends zzb implements zzu {
    zzv(IBinder arg2) {
        super(arg2, "com.google.android.gms.location.places.internal.IGooglePlacesService");
    }

    public final void zzb(AddPlaceRequest arg2, zzat arg3, zzy arg4) {
        Parcel v0 = ((zzb)this).obtainAndWriteInterfaceToken();
        zzd.zzb(v0, ((Parcelable)arg2));
        zzd.zzb(v0, ((Parcelable)arg3));
        zzd.zzb(v0, ((IInterface)arg4));
        ((zzb)this).transactAndReadExceptionReturnVoid(14, v0);
    }

    public final void zzb(String arg2, int arg3, int arg4, int arg5, zzat arg6, zzw arg7) {
        Parcel v0 = ((zzb)this).obtainAndWriteInterfaceToken();
        v0.writeString(arg2);
        v0.writeInt(arg3);
        v0.writeInt(arg4);
        v0.writeInt(arg5);
        zzd.zzb(v0, ((Parcelable)arg6));
        zzd.zzb(v0, ((IInterface)arg7));
        ((zzb)this).transactAndReadExceptionReturnVoid(20, v0);
    }

    public final void zzb(String arg2, zzat arg3, zzw arg4) {
        Parcel v0 = ((zzb)this).obtainAndWriteInterfaceToken();
        v0.writeString(arg2);
        zzd.zzb(v0, ((Parcelable)arg3));
        zzd.zzb(v0, ((IInterface)arg4));
        ((zzb)this).transactAndReadExceptionReturnVoid(19, v0);
    }

    public final void zzb(String arg2, LatLngBounds arg3, int arg4, AutocompleteFilter arg5, zzat arg6, zzy arg7) {
        Parcel v0 = ((zzb)this).obtainAndWriteInterfaceToken();
        v0.writeString(arg2);
        zzd.zzb(v0, ((Parcelable)arg3));
        v0.writeInt(arg4);
        zzd.zzb(v0, ((Parcelable)arg5));
        zzd.zzb(v0, ((Parcelable)arg6));
        zzd.zzb(v0, ((IInterface)arg7));
        ((zzb)this).transactAndReadExceptionReturnVoid(28, v0);
    }

    public final void zzb(List arg2, zzat arg3, zzy arg4) {
        Parcel v0 = ((zzb)this).obtainAndWriteInterfaceToken();
        v0.writeStringList(arg2);
        zzd.zzb(v0, ((Parcelable)arg3));
        zzd.zzb(v0, ((IInterface)arg4));
        ((zzb)this).transactAndReadExceptionReturnVoid(17, v0);
    }
}

