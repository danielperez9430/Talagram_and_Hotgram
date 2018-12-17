package com.google.android.gms.internal.vision;

import java.util.Iterator;

final class zzfk implements Iterator {
    private Iterator zzoz;

    zzfk(zzfi arg1) {
        this.zzoy = arg1;
        super();
        this.zzoz = zzfi.zza(this.zzoy).iterator();
    }

    public final boolean hasNext() {
        return this.zzoz.hasNext();
    }

    public final Object next() {
        return this.zzoz.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

