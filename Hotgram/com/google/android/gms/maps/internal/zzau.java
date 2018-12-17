package com.google.android.gms.maps.internal;

import android.os.Parcel;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzu;

public abstract class zzau extends zzb implements zzat {
    public zzau() {
        super("com.google.android.gms.maps.internal.IOnMarkerDragListener");
    }

    protected final boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
        switch(arg1) {
            case 1: {
                goto label_11;
            }
            case 2: {
                goto label_7;
            }
            case 3: {
                goto label_3;
            }
        }

        return 0;
    label_3:
        this.zzc(zzu.zzg(arg2.readStrongBinder()));
        goto label_14;
    label_7:
        this.zzd(zzu.zzg(arg2.readStrongBinder()));
        goto label_14;
    label_11:
        this.zzb(zzu.zzg(arg2.readStrongBinder()));
    label_14:
        arg3.writeNoException();
        return 1;
    }
}

