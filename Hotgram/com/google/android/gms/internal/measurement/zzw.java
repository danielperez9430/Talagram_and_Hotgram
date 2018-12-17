package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

public final class zzw extends zzq implements zzu {
    zzw(IBinder arg2) {
        super(arg2, "com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
    }

    public final Bundle zza(Bundle arg2) {
        Parcel v0 = ((zzq)this).obtainAndWriteInterfaceToken();
        zzs.zza(v0, ((Parcelable)arg2));
        Parcel v2 = ((zzq)this).transactAndReadException(1, v0);
        Parcelable v0_1 = zzs.zza(v2, Bundle.CREATOR);
        v2.recycle();
        return ((Bundle)v0_1);
    }
}

