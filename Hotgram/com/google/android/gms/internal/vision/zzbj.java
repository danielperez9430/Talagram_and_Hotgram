package com.google.android.gms.internal.vision;

final class zzbj {
    private static final Class zzgm;
    private static final boolean zzgn;

    static {
        zzbj.zzgm = zzbj.zzf("libcore.io.Memory");
        boolean v0 = zzbj.zzf("org.robolectric.Robolectric") != null ? true : false;
        zzbj.zzgn = v0;
    }

    static boolean zzaq() {
        if(zzbj.zzgm != null && !zzbj.zzgn) {
            return 1;
        }

        return 0;
    }

    static Class zzar() {
        return zzbj.zzgm;
    }

    private static Class zzf(String arg0) {
        try {
            return Class.forName(arg0);
        }
        catch(Throwable ) {
            return null;
        }
    }
}

