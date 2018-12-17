package com.google.android.gms.measurement.internal;

import java.util.Iterator;

final class zzab implements Iterator {
    private Iterator zzain;

    zzab(zzaa arg1) {
        this.zzaio = arg1;
        super();
        this.zzain = zzaa.zza(this.zzaio).keySet().iterator();
    }

    public final boolean hasNext() {
        return this.zzain.hasNext();
    }

    public final Object next() {
        return this.zzain.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }
}

