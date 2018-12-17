package com.google.android.gms.maps.internal;

import android.os.Parcel;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzo;

public abstract class zzaa extends zzb implements zzz {
    public zzaa() {
        super("com.google.android.gms.maps.internal.IOnIndoorStateChangeListener");
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
        this.zza(zzo.zze(arg2.readStrongBinder()));
        goto label_8;
    label_7:
        this.onIndoorBuildingFocused();
    label_8:
        arg3.writeNoException();
        return 1;
    }
}

