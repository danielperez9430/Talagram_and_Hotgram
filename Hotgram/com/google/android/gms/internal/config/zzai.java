package com.google.android.gms.internal.config;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

public final class zzai extends zza implements zzah {
    zzai(IBinder arg2) {
        super(arg2, "com.google.android.gms.config.internal.IConfigService");
    }

    public final void zza(zzaf arg2, zzab arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        zzc.zza(v0, ((Parcelable)arg3));
        ((zza)this).transactAndReadExceptionReturnVoid(8, v0);
    }
}

