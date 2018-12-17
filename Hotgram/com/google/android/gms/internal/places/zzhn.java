package com.google.android.gms.internal.places;

import java.util.Iterator;
import java.util.Map$Entry;

final class zzhn implements Iterator {
    private Iterator zzue;

    public zzhn(Iterator arg1) {
        super();
        this.zzue = arg1;
    }

    public final boolean hasNext() {
        return this.zzue.hasNext();
    }

    public final Object next() {
        Object v0 = this.zzue.next();
        if((((Map$Entry)v0).getValue() instanceof zzhk)) {
            return new zzhm(((Map$Entry)v0), null);
        }

        return v0;
    }

    public final void remove() {
        this.zzue.remove();
    }
}

