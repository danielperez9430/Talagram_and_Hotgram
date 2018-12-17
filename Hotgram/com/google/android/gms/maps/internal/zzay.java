package com.google.android.gms.maps.internal;

import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import com.google.android.gms.internal.maps.zzb;

public abstract class zzay extends zzb implements zzax {
    public zzay() {
        super("com.google.android.gms.maps.internal.IOnMyLocationChangeListener");
    }

    protected final boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
        if(arg1 == 1) {
            this.zza(Stub.asInterface(arg2.readStrongBinder()));
            arg3.writeNoException();
            return 1;
        }

        return 0;
    }
}

