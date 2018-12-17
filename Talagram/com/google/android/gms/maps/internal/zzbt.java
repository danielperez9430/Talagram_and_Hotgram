package com.google.android.gms.maps.internal;

import android.graphics.Bitmap;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzc;

public abstract class zzbt extends zzb implements zzbs {
    public zzbt() {
        super("com.google.android.gms.maps.internal.ISnapshotReadyCallback");
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
        this.zzb(Stub.asInterface(arg2.readStrongBinder()));
        goto label_10;
    label_7:
        this.onSnapshotReady(zzc.zza(arg2, Bitmap.CREATOR));
    label_10:
        arg3.writeNoException();
        return 1;
    }
}

