package com.google.android.gms.internal.firebase_abt;

import java.nio.charset.Charset;

public final class zzh {
    private static final Charset ISO_8859_1;
    protected static final Charset UTF_8;
    public static final Object zzaa;

    static {
        zzh.UTF_8 = Charset.forName("UTF-8");
        zzh.ISO_8859_1 = Charset.forName("ISO-8859-1");
        zzh.zzaa = new Object();
    }

    public static void zza(zzd arg1, zzd arg2) {
        if(arg1.zzs != null) {
            arg2.zzs = arg1.zzs.clone();
        }
    }
}

