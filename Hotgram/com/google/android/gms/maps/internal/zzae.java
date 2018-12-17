package com.google.android.gms.maps.internal;

import android.os.Parcel;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzu;

public abstract class zzae extends zzb implements zzad {
    public zzae() {
        super("com.google.android.gms.maps.internal.IOnInfoWindowCloseListener");
    }

    protected final boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
        if(arg1 == 1) {
            this.zzg(zzu.zzg(arg2.readStrongBinder()));
            arg3.writeNoException();
            return 1;
        }

        return 0;
    }
}

