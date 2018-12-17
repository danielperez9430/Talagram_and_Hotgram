package com.google.android.gms.internal.ads_identifier;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzf extends zzb implements zze {
    public static zze zza(IBinder arg2) {
        if(arg2 == null) {
            return null;
        }

        IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        if((v0 instanceof zze)) {
            return ((zze)v0);
        }

        return new zzg(arg2);
    }
}

