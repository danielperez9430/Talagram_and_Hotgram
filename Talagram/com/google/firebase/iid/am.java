package com.google.firebase.iid;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable$Creator;

final class am implements Parcelable$Creator {
    am() {
        super();
    }

    public final Object createFromParcel(Parcel arg2) {
        IBinder v2 = arg2.readStrongBinder();
        if(v2 != null) {
            return new zzk(v2);
        }

        return null;
    }

    public final Object[] newArray(int arg1) {
        return new zzk[arg1];
    }
}

