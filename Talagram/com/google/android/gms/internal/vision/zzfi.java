package com.google.android.gms.internal.vision;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class zzfi extends AbstractList implements zzdg, RandomAccess {
    private final zzdg zzov;

    public zzfi(zzdg arg1) {
        super();
        this.zzov = arg1;
    }

    public final Object get(int arg2) {
        return this.zzov.get(arg2);
    }

    public final Object getRaw(int arg2) {
        return this.zzov.getRaw(arg2);
    }

    public final Iterator iterator() {
        return new zzfk(this);
    }

    public final ListIterator listIterator(int arg2) {
        return new zzfj(this, arg2);
    }

    public final int size() {
        return this.zzov.size();
    }

    static zzdg zza(zzfi arg0) {
        return arg0.zzov;
    }

    public final void zzc(zzbo arg1) {
        throw new UnsupportedOperationException();
    }

    public final List zzck() {
        return this.zzov.zzck();
    }

    public final zzdg zzcl() {
        return this;
    }
}

