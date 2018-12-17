package com.google.android.gms.internal.location;

import android.os.Parcel;
import com.google.android.gms.location.LocationSettingsResult;

public abstract class zzar extends zzb implements zzaq {
    public zzar() {
        super("com.google.android.gms.location.internal.ISettingsCallbacks");
    }

    protected final boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
        if(arg1 == 1) {
            this.zza(zzc.zza(arg2, LocationSettingsResult.CREATOR));
            return 1;
        }

        return 0;
    }
}

