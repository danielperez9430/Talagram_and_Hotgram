package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzo extends zzb implements zzn {
    public static zzn zze(IBinder arg2) {
        if(arg2 == null) {
            return null;
        }

        IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
        if((v0 instanceof zzn)) {
            return ((zzn)v0);
        }

        return new zzp(arg2);
    }
}

