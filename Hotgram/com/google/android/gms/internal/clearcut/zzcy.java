package com.google.android.gms.internal.clearcut;

abstract class zzcy {
    private static final zzcy zzlt;
    private static final zzcy zzlu;

    static {
        zzcy.zzlt = new zzda(null);
        zzcy.zzlu = new zzdb(null);
    }

    private zzcy() {
        super();
    }

    zzcy(zzcz arg1) {
        this();
    }

    abstract void zza(Object arg1, long arg2);

    abstract void zza(Object arg1, Object arg2, long arg3);

    static zzcy zzbv() {
        return zzcy.zzlt;
    }

    static zzcy zzbw() {
        return zzcy.zzlu;
    }
}

