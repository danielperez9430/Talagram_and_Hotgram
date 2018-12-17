package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Parcel;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzc;

public abstract class zzba extends zzb implements zzaz {
    public zzba() {
        super("com.google.android.gms.maps.internal.IOnMyLocationClickListener");
    }

    protected final boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
        if(arg1 == 1) {
            this.onMyLocationClick(zzc.zza(arg2, Location.CREATOR));
            arg3.writeNoException();
            return 1;
        }

        return 0;
    }
}

