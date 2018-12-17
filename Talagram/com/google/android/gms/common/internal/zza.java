package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class zza implements Parcelable$Creator {
    zza() {
        super();
    }

    public final Object createFromParcel(Parcel arg3) {
        return new BinderWrapper(arg3, null);
    }

    public final Object[] newArray(int arg1) {
        return new BinderWrapper[arg1];
    }
}

