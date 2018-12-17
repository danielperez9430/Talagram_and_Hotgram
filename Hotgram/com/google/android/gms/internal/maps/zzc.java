package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import java.util.ArrayList;

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

    public static boolean zza(Parcel arg0) {
        if(arg0.readInt() != 0) {
            return 1;
        }

        return 0;
    }

    public static ArrayList zzb(Parcel arg1) {
        return arg1.readArrayList(zzc.zzd);
    }

    public static void zzb(Parcel arg1, Parcelable arg2) {
        if(arg2 == null) {
            arg1.writeInt(0);
            return;
        }

        arg1.writeInt(1);
        arg2.writeToParcel(arg1, 1);
    }
}

