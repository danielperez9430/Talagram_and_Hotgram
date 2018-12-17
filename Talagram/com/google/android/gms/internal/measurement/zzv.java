package com.google.android.gms.internal.measurement;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzv extends zzr implements zzu {
    public static zzu zza(IBinder arg2) {
        if(arg2 == null) {
            return null;
        }

        IInterface v0 = arg2.queryLocalInterface("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
        if((v0 instanceof zzu)) {
            return ((zzu)v0);
        }

        return new zzw(arg2);
    }
}

