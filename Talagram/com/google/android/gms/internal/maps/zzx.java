package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzx extends zzb implements zzw {
    public static zzw zzh(IBinder arg2) {
        if(arg2 == null) {
            return null;
        }

        IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
        if((v0 instanceof zzw)) {
            return ((zzw)v0);
        }

        return new zzy(arg2);
    }
}

