package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import com.google.android.gms.internal.wearable.zzb;

public abstract class zzej extends zzb implements zzei {
    public zzej() {
        super("com.google.android.gms.wearable.internal.IChannelStreamCallbacks");
    }

    protected final boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
        if(arg1 == 2) {
            this.zza(arg2.readInt(), arg2.readInt());
            arg3.writeNoException();
            return 1;
        }

        return 0;
    }
}

