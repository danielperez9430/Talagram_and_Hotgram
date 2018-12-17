package com.google.android.gms.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.internal.location.zzb;

public class zzs extends zzb implements zzr {
    protected final boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
        throw new NoSuchMethodError();
    }

    public static zzr zza(IBinder arg2) {
        if(arg2 == null) {
            return null;
        }

        IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.location.IDeviceOrientationListener");
        if((v0 instanceof zzr)) {
            return ((zzr)v0);
        }

        return new zzt(arg2);
    }
}

