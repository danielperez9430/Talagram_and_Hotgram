package com.google.android.gms.maps.internal;

import android.os.Parcel;
import com.google.android.gms.internal.maps.zzb;

public abstract class zzu extends zzb implements zzt {
    public zzu() {
        super("com.google.android.gms.maps.internal.IOnCameraMoveStartedListener");
    }

    protected final boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
        if(arg1 == 1) {
            this.onCameraMoveStarted(arg2.readInt());
            arg3.writeNoException();
            return 1;
        }

        return 0;
    }
}

