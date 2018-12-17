package com.google.android.gms.location;

import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.internal.location.zzb;
import com.google.android.gms.internal.location.zzc;

public abstract class zzy extends zzb implements zzx {
    public zzy() {
        super("com.google.android.gms.location.ILocationListener");
    }

    protected final boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
        if(arg1 == 1) {
            this.onLocationChanged(zzc.zza(arg2, Location.CREATOR));
            return 1;
        }

        return 0;
    }

    public static zzx zzc(IBinder arg2) {
        if(arg2 == null) {
            return null;
        }

        IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.location.ILocationListener");
        if((v0 instanceof zzx)) {
            return ((zzx)v0);
        }

        return new zzz(arg2);
    }
}

