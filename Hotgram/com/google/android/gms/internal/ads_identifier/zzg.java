package com.google.android.gms.internal.ads_identifier;

import android.os.IBinder;
import android.os.Parcel;

public final class zzg extends zza implements zze {
    zzg(IBinder arg2) {
        super(arg2, "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
    }

    public final String getId() {
        Parcel v0 = ((zza)this).transactAndReadException(1, ((zza)this).obtainAndWriteInterfaceToken());
        String v1 = v0.readString();
        v0.recycle();
        return v1;
    }

    public final boolean zzb(boolean arg2) {
        Parcel v2 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v2, true);
        v2 = ((zza)this).transactAndReadException(2, v2);
        boolean v0 = zzc.zza(v2);
        v2.recycle();
        return v0;
    }

    public final boolean zzc() {
        Parcel v0 = ((zza)this).transactAndReadException(6, ((zza)this).obtainAndWriteInterfaceToken());
        boolean v1 = zzc.zza(v0);
        v0.recycle();
        return v1;
    }
}

