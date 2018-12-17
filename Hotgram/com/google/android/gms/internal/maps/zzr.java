package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzr extends zzb implements zzq {
    public static zzq zzf(IBinder arg2) {
        if(arg2 == null) {
            return null;
        }

        IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
        if((v0 instanceof zzq)) {
            return ((zzq)v0);
        }

        return new zzs(arg2);
    }
}

