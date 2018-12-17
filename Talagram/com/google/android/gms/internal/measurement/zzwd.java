package com.google.android.gms.internal.measurement;

import java.util.List;

abstract class zzwd {
    private static final zzwd zzcaj;
    private static final zzwd zzcak;

    static {
        zzwd.zzcaj = new zzwf(null);
        zzwd.zzcak = new zzwg(null);
    }

    private zzwd() {
        super();
    }

    zzwd(zzwe arg1) {
        this();
    }

    abstract List zza(Object arg1, long arg2);

    abstract void zza(Object arg1, Object arg2, long arg3);

    abstract void zzb(Object arg1, long arg2);

    static zzwd zzwx() {
        return zzwd.zzcaj;
    }

    static zzwd zzwy() {
        return zzwd.zzcak;
    }
}

