package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzad extends zzb implements zzac {
    public static zzac zzj(IBinder arg2) {
        if(arg2 == null) {
            return null;
        }

        IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
        if((v0 instanceof zzac)) {
            return ((zzac)v0);
        }

        return new zzae(arg2);
    }
}

