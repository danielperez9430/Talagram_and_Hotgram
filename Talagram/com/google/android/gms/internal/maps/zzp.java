package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.ArrayList;
import java.util.List;

public final class zzp extends zza implements zzn {
    zzp(IBinder arg2) {
        super(arg2, "com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
    }

    public final int getActiveLevelIndex() {
        Parcel v0 = ((zza)this).transactAndReadException(1, ((zza)this).obtainAndWriteInterfaceToken());
        int v1 = v0.readInt();
        v0.recycle();
        return v1;
    }

    public final int getDefaultLevelIndex() {
        Parcel v0 = ((zza)this).transactAndReadException(2, ((zza)this).obtainAndWriteInterfaceToken());
        int v1 = v0.readInt();
        v0.recycle();
        return v1;
    }

    public final List getLevels() {
        Parcel v0 = ((zza)this).transactAndReadException(3, ((zza)this).obtainAndWriteInterfaceToken());
        ArrayList v1 = v0.createBinderArrayList();
        v0.recycle();
        return ((List)v1);
    }

    public final boolean isUnderground() {
        Parcel v0 = ((zza)this).transactAndReadException(4, ((zza)this).obtainAndWriteInterfaceToken());
        boolean v1 = zzc.zza(v0);
        v0.recycle();
        return v1;
    }

    public final boolean zzb(zzn arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        Parcel v2 = ((zza)this).transactAndReadException(5, v0);
        boolean v0_1 = zzc.zza(v2);
        v2.recycle();
        return v0_1;
    }

    public final int zzi() {
        Parcel v0 = ((zza)this).transactAndReadException(6, ((zza)this).obtainAndWriteInterfaceToken());
        int v1 = v0.readInt();
        v0.recycle();
        return v1;
    }
}

