package com.google.android.gms.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.internal.location.zzb;
import com.google.android.gms.internal.location.zzc;

public abstract class zzv extends zzb implements zzu {
    public zzv() {
        super("com.google.android.gms.location.ILocationCallback");
    }

    protected final boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
        switch(arg1) {
            case 1: {
                goto label_7;
            }
            case 2: {
                goto label_3;
            }
        }

        return 0;
    label_3:
        this.onLocationAvailability(zzc.zza(arg2, LocationAvailability.CREATOR));
        return 1;
    label_7:
        this.onLocationResult(zzc.zza(arg2, LocationResult.CREATOR));
        return 1;
    }

    public static zzu zzb(IBinder arg2) {
        if(arg2 == null) {
            return null;
        }

        IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.location.ILocationCallback");
        if((v0 instanceof zzu)) {
            return ((zzu)v0);
        }

        return new zzw(arg2);
    }
}

