package com.google.android.gms.internal.identity;

import android.os.Bundle;
import android.os.Parcel;

public abstract class zzh extends zzb implements zzg {
    public zzh() {
        super("com.google.android.gms.identity.intents.internal.IAddressCallbacks");
    }

    protected final boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
        if(arg1 == 2) {
            this.zza(arg2.readInt(), zzc.zza(arg2, Bundle.CREATOR));
            arg3.writeNoException();
            return 1;
        }

        return 0;
    }
}

