package com.google.android.gms.internal.places;

import java.util.Iterator;

final class zzjv implements Iterator {
    private Iterator zzxw;

    zzjv(zzjt arg1) {
        this.zzxv = arg1;
        super();
        this.zzxw = zzjt.zzb(this.zzxv).iterator();
    }

    public final boolean hasNext() {
        return this.zzxw.hasNext();
    }

    public final Object next() {
        return this.zzxw.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

