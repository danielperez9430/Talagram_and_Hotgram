package com.google.android.gms.internal.measurement;

import java.util.ListIterator;

final class zzyf implements ListIterator {
    private ListIterator zzccr;

    zzyf(zzye arg1, int arg2) {
        this.zzcct = arg1;
        this.zzccs = arg2;
        super();
        this.zzccr = zzye.zza(this.zzcct).listIterator(this.zzccs);
    }

    public final void add(Object arg1) {
        throw new UnsupportedOperationException();
    }

    public final boolean hasNext() {
        return this.zzccr.hasNext();
    }

    public final boolean hasPrevious() {
        return this.zzccr.hasPrevious();
    }

    public final Object next() {
        return this.zzccr.next();
    }

    public final int nextIndex() {
        return this.zzccr.nextIndex();
    }

    public final Object previous() {
        return this.zzccr.previous();
    }

    public final int previousIndex() {
        return this.zzccr.previousIndex();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final void set(Object arg1) {
        throw new UnsupportedOperationException();
    }
}

