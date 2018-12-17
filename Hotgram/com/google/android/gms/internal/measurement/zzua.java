package com.google.android.gms.internal.measurement;

final class zzua {
    private static final Class zzbtv;
    private static final boolean zzbtw;

    static {
        zzua.zzbtv = zzua.zzfu("libcore.io.Memory");
        boolean v0 = zzua.zzfu("org.robolectric.Robolectric") != null ? true : false;
        zzua.zzbtw = v0;
    }

    private static Class zzfu(String arg0) {
        try {
            return Class.forName(arg0);
        }
        catch(Throwable ) {
            return null;
        }
    }

    static boolean zzty() {
        if(zzua.zzbtv != null && !zzua.zzbtw) {
            return 1;
        }

        return 0;
    }

    static Class zztz() {
        return zzua.zzbtv;
    }
}

