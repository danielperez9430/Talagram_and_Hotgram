package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzi extends zzb implements zzh {
    public static zzh zzc(IBinder arg2) {
        if(arg2 == null) {
            return null;
        }

        IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
        if((v0 instanceof zzh)) {
            return ((zzh)v0);
        }

        return new zzj(arg2);
    }
}

