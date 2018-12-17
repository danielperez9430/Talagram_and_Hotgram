package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.location.ActivityTransitionRequest;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.zzal;

public final class zzap extends zza implements zzao {
    zzap(IBinder arg2) {
        super(arg2, "com.google.android.gms.location.internal.IGoogleLocationManagerService");
    }

    public final Location zza(String arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeString(arg2);
        Parcel v2 = ((zza)this).transactAndReadException(21, v0);
        Parcelable v0_1 = zzc.zza(v2, Location.CREATOR);
        v2.recycle();
        return ((Location)v0_1);
    }

    public final void zza(long arg1, boolean arg3, PendingIntent arg4) {
        Parcel v3 = ((zza)this).obtainAndWriteInterfaceToken();
        v3.writeLong(arg1);
        zzc.zza(v3, true);
        zzc.zza(v3, ((Parcelable)arg4));
        ((zza)this).transactAndReadExceptionReturnVoid(5, v3);
    }

    public final void zza(PendingIntent arg2, IStatusCallback arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        zzc.zza(v0, ((IInterface)arg3));
        ((zza)this).transactAndReadExceptionReturnVoid(73, v0);
    }

    public final void zza(Location arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(13, v0);
    }

    public final void zza(zzaj arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(67, v0);
    }

    public final void zza(zzbf arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(59, v0);
    }

    public final void zza(zzo arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(75, v0);
    }

    public final void zza(ActivityTransitionRequest arg2, PendingIntent arg3, IStatusCallback arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        zzc.zza(v0, ((Parcelable)arg3));
        zzc.zza(v0, ((IInterface)arg4));
        ((zza)this).transactAndReadExceptionReturnVoid(72, v0);
    }

    public final void zza(GeofencingRequest arg2, PendingIntent arg3, zzam arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        zzc.zza(v0, ((Parcelable)arg3));
        zzc.zza(v0, ((IInterface)arg4));
        ((zza)this).transactAndReadExceptionReturnVoid(57, v0);
    }

    public final void zza(LocationSettingsRequest arg2, zzaq arg3, String arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        zzc.zza(v0, ((IInterface)arg3));
        v0.writeString(arg4);
        ((zza)this).transactAndReadExceptionReturnVoid(63, v0);
    }

    public final void zza(zzal arg2, zzam arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        zzc.zza(v0, ((IInterface)arg3));
        ((zza)this).transactAndReadExceptionReturnVoid(74, v0);
    }

    public final void zza(boolean arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(12, v0);
    }

    public final LocationAvailability zzb(String arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeString(arg2);
        Parcel v2 = ((zza)this).transactAndReadException(34, v0);
        Parcelable v0_1 = zzc.zza(v2, LocationAvailability.CREATOR);
        v2.recycle();
        return ((LocationAvailability)v0_1);
    }

    public final void zzb(PendingIntent arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(6, v0);
    }
}

