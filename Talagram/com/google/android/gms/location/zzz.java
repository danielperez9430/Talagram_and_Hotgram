package com.google.android.gms.location;

import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.location.zza;
import com.google.android.gms.internal.location.zzc;

public final class zzz extends zza implements zzx {
    zzz(IBinder arg2) {
        super(arg2, "com.google.android.gms.location.ILocationListener");
    }

    public final void onLocationChanged(Location arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactOneway(1, v0);
    }
}

