package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
import java.util.Map$Entry;

final class zzcu implements Iterator {
    private Iterator zzlm;

    public zzcu(Iterator arg1) {
        super();
        this.zzlm = arg1;
    }

    public final boolean hasNext() {
        return this.zzlm.hasNext();
    }

    public final Object next() {
        Object v0 = this.zzlm.next();
        if((((Map$Entry)v0).getValue() instanceof zzcr)) {
            return new zzct(((Map$Entry)v0), null);
        }

        return v0;
    }

    public final void remove() {
        this.zzlm.remove();
    }
}

