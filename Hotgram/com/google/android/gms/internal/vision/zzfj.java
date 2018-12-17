package com.google.android.gms.internal.vision;

import java.util.ListIterator;

final class zzfj implements ListIterator {
    private ListIterator zzow;

    zzfj(zzfi arg1, int arg2) {
        this.zzoy = arg1;
        this.zzox = arg2;
        super();
        this.zzow = zzfi.zza(this.zzoy).listIterator(this.zzox);
    }

    public final void add(Object arg1) {
        throw new UnsupportedOperationException();
    }

    public final boolean hasNext() {
        return this.zzow.hasNext();
    }

    public final boolean hasPrevious() {
        return this.zzow.hasPrevious();
    }

    public final Object next() {
        return this.zzow.next();
    }

    public final int nextIndex() {
        return this.zzow.nextIndex();
    }

    public final Object previous() {
        return this.zzow.previous();
    }

    public final int previousIndex() {
        return this.zzow.previousIndex();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final void set(Object arg1) {
        throw new UnsupportedOperationException();
    }
}

