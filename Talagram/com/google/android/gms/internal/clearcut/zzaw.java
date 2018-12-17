package com.google.android.gms.internal.clearcut;

final class zzaw {
    private static final Class zzfb;
    private static final boolean zzfc;

    static {
        zzaw.zzfb = zzaw.zze("libcore.io.Memory");
        boolean v0 = zzaw.zze("org.robolectric.Robolectric") != null ? true : false;
        zzaw.zzfc = v0;
    }

    private static Class zze(String arg0) {
        try {
            return Class.forName(arg0);
        }
        catch(Throwable ) {
            return null;
        }
    }

    static boolean zzx() {
        if(zzaw.zzfb != null && !zzaw.zzfc) {
            return 1;
        }

        return 0;
    }

    static Class zzy() {
        return zzaw.zzfb;
    }
}

