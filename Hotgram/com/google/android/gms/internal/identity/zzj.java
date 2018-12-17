package com.google.android.gms.internal.identity;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.identity.intents.UserAddressRequest;

public final class zzj extends zza implements zzi {
    zzj(IBinder arg2) {
        super(arg2, "com.google.android.gms.identity.intents.internal.IAddressService");
    }

    public final void zza(zzg arg2, UserAddressRequest arg3, Bundle arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        zzc.zza(v0, ((Parcelable)arg3));
        zzc.zza(v0, ((Parcelable)arg4));
        ((zza)this).transactAndReadExceptionReturnVoid(2, v0);
    }
}

