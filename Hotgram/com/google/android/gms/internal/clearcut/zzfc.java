package com.google.android.gms.internal.clearcut;

import java.util.Iterator;

final class zzfc implements Iterator {
    private Iterator zzpf;

    zzfc(zzfa arg1) {
        this.zzpe = arg1;
        super();
        this.zzpf = zzfa.zza(this.zzpe).iterator();
    }

    public final boolean hasNext() {
        return this.zzpf.hasNext();
    }

    public final Object next() {
        return this.zzpf.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

