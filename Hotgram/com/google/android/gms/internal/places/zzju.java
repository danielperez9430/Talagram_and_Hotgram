package com.google.android.gms.internal.places;

import java.util.ListIterator;

final class zzju implements ListIterator {
    private ListIterator zzxu;

    zzju(zzjt arg1, int arg2) {
        this.zzxv = arg1;
        this.zzfx = arg2;
        super();
        this.zzxu = zzjt.zzb(this.zzxv).listIterator(this.zzfx);
    }

    public final void add(Object arg1) {
        throw new UnsupportedOperationException();
    }

    public final boolean hasNext() {
        return this.zzxu.hasNext();
    }

    public final boolean hasPrevious() {
        return this.zzxu.hasPrevious();
    }

    public final Object next() {
        return this.zzxu.next();
    }

    public final int nextIndex() {
        return this.zzxu.nextIndex();
    }

    public final Object previous() {
        return this.zzxu.previous();
    }

    public final int previousIndex() {
        return this.zzxu.previousIndex();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final void set(Object arg1) {
        throw new UnsupportedOperationException();
    }
}

