package com.google.android.gms.internal.ads_identifier;

import android.os.Parcel;

public class zzc {
    private static final ClassLoader zzd;

    static {
        zzc.zzd = zzc.class.getClassLoader();
    }

    private zzc() {
        super();
    }

    public static void zza(Parcel arg0, boolean arg1) {
        arg0.writeInt(1);
    }

    public static boolean zza(Parcel arg0) {
        if(arg0.readInt() != 0) {
            return 1;
        }

        return 0;
    }
}

