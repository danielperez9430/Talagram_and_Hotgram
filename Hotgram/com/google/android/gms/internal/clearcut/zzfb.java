package com.google.android.gms.internal.clearcut;

import java.util.ListIterator;

final class zzfb implements ListIterator {
    private ListIterator zzpc;

    zzfb(zzfa arg1, int arg2) {
        this.zzpe = arg1;
        this.zzpd = arg2;
        super();
        this.zzpc = zzfa.zza(this.zzpe).listIterator(this.zzpd);
    }

    public final void add(Object arg1) {
        throw new UnsupportedOperationException();
    }

    public final boolean hasNext() {
        return this.zzpc.hasNext();
    }

    public final boolean hasPrevious() {
        return this.zzpc.hasPrevious();
    }

    public final Object next() {
        return this.zzpc.next();
    }

    public final int nextIndex() {
        return this.zzpc.nextIndex();
    }

    public final Object previous() {
        return this.zzpc.previous();
    }

    public final int previousIndex() {
        return this.zzpc.previousIndex();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final void set(Object arg1) {
        throw new UnsupportedOperationException();
    }
}

