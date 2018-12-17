package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzu extends zzb implements zzt {
    public static zzt zzg(IBinder arg2) {
        if(arg2 == null) {
            return null;
        }

        IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
        if((v0 instanceof zzt)) {
            return ((zzt)v0);
        }

        return new zzv(arg2);
    }
}

