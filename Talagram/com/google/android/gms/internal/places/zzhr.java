package com.google.android.gms.internal.places;

import java.util.List;

abstract class zzhr {
    private static final zzhr zzul;
    private static final zzhr zzum;

    static {
        zzhr.zzul = new zzht(null);
        zzhr.zzum = new zzhu(null);
    }

    private zzhr() {
        super();
    }

    zzhr(zzhs arg1) {
        this();
    }

    abstract List zzb(Object arg1, long arg2);

    abstract void zzb(Object arg1, Object arg2, long arg3);

    abstract void zzc(Object arg1, long arg2);

    static zzhr zzem() {
        return zzhr.zzul;
    }

    static zzhr zzen() {
        return zzhr.zzum;
    }
}

