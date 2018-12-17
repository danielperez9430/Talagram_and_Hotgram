package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzl extends zzb implements zzk {
    public static zzk zzd(IBinder arg2) {
        if(arg2 == null) {
            return null;
        }

        IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
        if((v0 instanceof zzk)) {
            return ((zzk)v0);
        }

        return new zzm(arg2);
    }
}

