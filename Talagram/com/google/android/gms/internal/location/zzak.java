package com.google.android.gms.internal.location;

import android.os.Parcel;

public abstract class zzak extends zzb implements zzaj {
    public zzak() {
        super("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
    }

    protected final boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
        if(arg1 == 1) {
            this.zza(zzc.zza(arg2, zzad.CREATOR));
            return 1;
        }

        return 0;
    }
}

