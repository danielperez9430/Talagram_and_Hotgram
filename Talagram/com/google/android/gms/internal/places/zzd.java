package com.google.android.gms.internal.places;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class zzd {
    private static final ClassLoader zze;

    static {
        zzd.zze = zzd.class.getClassLoader();
    }

    private zzd() {
        super();
    }

    public static Parcelable zzb(Parcel arg1, Parcelable$Creator arg2) {
        if(arg1.readInt() == 0) {
            return null;
        }

        return arg2.createFromParcel(arg1);
    }

    public static void zzb(Parcel arg0, IInterface arg1) {
        IBinder v1 = arg1 == null ? null : arg1.asBinder();
        arg0.writeStrongBinder(v1);
    }

    public static void zzb(Parcel arg2, Parcelable arg3) {
        if(arg3 == null) {
            arg2.writeInt(0);
            return;
        }

        arg2.writeInt(1);
        arg3.writeToParcel(arg2, 0);
    }
}

