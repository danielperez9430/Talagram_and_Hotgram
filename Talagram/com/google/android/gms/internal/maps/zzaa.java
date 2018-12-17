package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzaa extends zzb implements zzz {
    public static zzz zzi(IBinder arg2) {
        if(arg2 == null) {
            return null;
        }

        IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
        if((v0 instanceof zzz)) {
            return ((zzz)v0);
        }

        return new zzab(arg2);
    }
}

