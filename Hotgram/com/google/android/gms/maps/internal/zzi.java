package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.internal.maps.zzu;

public abstract class zzi extends zzb implements zzh {
    public zzi() {
        super("com.google.android.gms.maps.internal.IInfoWindowAdapter");
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
        IObjectWrapper v1 = this.zzi(zzu.zzg(arg2.readStrongBinder()));
        goto label_10;
    label_7:
        v1 = this.zzh(zzu.zzg(arg2.readStrongBinder()));
    label_10:
        arg3.writeNoException();
        zzc.zza(arg3, ((IInterface)v1));
        return 1;
    }
}

