package com.google.android.gms.internal.location;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

public final class zzal extends zza implements zzaj {
    zzal(IBinder arg2) {
        super(arg2, "com.google.android.gms.location.internal.IFusedLocationProviderCallback");
    }

    public final void zza(zzad arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactOneway(1, v0);
    }
}

