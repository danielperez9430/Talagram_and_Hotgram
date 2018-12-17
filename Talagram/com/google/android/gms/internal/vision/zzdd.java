package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map$Entry;

final class zzdd implements Iterator {
    private Iterator zzmh;

    public zzdd(Iterator arg1) {
        super();
        this.zzmh = arg1;
    }

    public final boolean hasNext() {
        return this.zzmh.hasNext();
    }

    public final Object next() {
        Object v0 = this.zzmh.next();
        if((((Map$Entry)v0).getValue() instanceof zzda)) {
            return new zzdc(((Map$Entry)v0), null);
        }

        return v0;
    }

    public final void remove() {
        this.zzmh.remove();
    }
}

