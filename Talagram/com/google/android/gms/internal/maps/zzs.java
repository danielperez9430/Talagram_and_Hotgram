package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public final class zzs extends zza implements zzq {
    zzs(IBinder arg2) {
        super(arg2, "com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
    }

    public final void activate() {
        ((zza)this).transactAndReadExceptionReturnVoid(3, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final String getName() {
        Parcel v0 = ((zza)this).transactAndReadException(1, ((zza)this).obtainAndWriteInterfaceToken());
        String v1 = v0.readString();
        v0.recycle();
        return v1;
    }

    public final String getShortName() {
        Parcel v0 = ((zza)this).transactAndReadException(2, ((zza)this).obtainAndWriteInterfaceToken());
        String v1 = v0.readString();
        v0.recycle();
        return v1;
    }

    public final boolean zzb(zzq arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        Parcel v2 = ((zza)this).transactAndReadException(4, v0);
        boolean v0_1 = zzc.zza(v2);
        v2.recycle();
        return v0_1;
    }

    public final int zzi() {
        Parcel v0 = ((zza)this).transactAndReadException(5, ((zza)this).obtainAndWriteInterfaceToken());
        int v1 = v0.readInt();
        v0.recycle();
        return v1;
    }
}

