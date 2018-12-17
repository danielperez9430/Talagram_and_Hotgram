package com.google.android.gms.internal.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class zzc {
    private static final ClassLoader zzd;

    static {
        zzc.zzd = zzc.class.getClassLoader();
    }

    private zzc() {
        super();
    }

    public static Parcelable zza(Parcel arg1, Parcelable$Creator arg2) {
        if(arg1.readInt() == 0) {
            return null;
        }

        return arg2.createFromParcel(arg1);
    }

    public static void zza(Parcel arg0, IInterface arg1) {
        IBinder v1 = arg1 == null ? null : arg1.asBinder();
        arg0.writeStrongBinder(v1);
    }

    public static void zza(Parcel arg2, Parcelable arg3) {
        if(arg3 == null) {
            arg2.writeInt(0);
            return;
        }

        arg2.writeInt(1);
        arg3.writeToParcel(arg2, 0);
    }

    public static void zza(Parcel arg0, boolean arg1) {
        arg0.writeInt(((int)arg1));
    }
}

