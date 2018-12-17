package com.google.android.gms.internal.places;

final class zzfl {
    private static final Class zznl;
    private static final boolean zznm;

    static {
        zzfl.zznl = zzfl.zzi("libcore.io.Memory");
        boolean v0 = zzfl.zzi("org.robolectric.Robolectric") != null ? true : false;
        zzfl.zznm = v0;
    }

    static boolean zzbd() {
        if(zzfl.zznl != null && !zzfl.zznm) {
            return 1;
        }

        return 0;
    }

    static Class zzbe() {
        return zzfl.zznl;
    }

    private static Class zzi(String arg0) {
        try {
            return Class.forName(arg0);
        }
        catch(Throwable ) {
            return null;
        }
    }
}

