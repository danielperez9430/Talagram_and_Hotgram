package com.google.android.gms.maps.internal;

import android.os.Parcel;
import com.google.android.gms.internal.maps.zzb;

public abstract class zzd extends zzb implements zzc {
    public zzd() {
        super("com.google.android.gms.maps.internal.ICancelableCallback");
    }

    protected final boolean dispatchTransaction(int arg1, Parcel arg2, Parcel arg3, int arg4) {
        switch(arg1) {
            case 1: {
                goto label_5;
            }
            case 2: {
                goto label_3;
            }
        }

        return 0;
    label_3:
        this.onCancel();
        goto label_6;
    label_5:
        this.onFinish();
    label_6:
        arg3.writeNoException();
        return 1;
    }
}

