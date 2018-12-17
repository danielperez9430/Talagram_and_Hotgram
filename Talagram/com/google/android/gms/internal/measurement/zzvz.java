package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map$Entry;

final class zzvz implements Iterator {
    private Iterator zzcac;

    public zzvz(Iterator arg1) {
        super();
        this.zzcac = arg1;
    }

    public final boolean hasNext() {
        return this.zzcac.hasNext();
    }

    public final Object next() {
        Object v0 = this.zzcac.next();
        if((((Map$Entry)v0).getValue() instanceof zzvw)) {
            return new zzvy(((Map$Entry)v0), null);
        }

        return v0;
    }

    public final void remove() {
        this.zzcac.remove();
    }
}

