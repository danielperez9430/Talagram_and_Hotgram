package com.google.android.gms.internal.places;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class zzjt extends AbstractList implements zzhq, RandomAccess {
    private final zzhq zzxt;

    public zzjt(zzhq arg1) {
        super();
        this.zzxt = arg1;
    }

    public final Object get(int arg2) {
        return this.zzxt.get(arg2);
    }

    public final Object getRaw(int arg2) {
        return this.zzxt.getRaw(arg2);
    }

    public final Iterator iterator() {
        return new zzjv(this);
    }

    public final ListIterator listIterator(int arg2) {
        return new zzju(this, arg2);
    }

    public final int size() {
        return this.zzxt.size();
    }

    static zzhq zzb(zzjt arg0) {
        return arg0.zzxt;
    }

    public final void zzd(zzfr arg1) {
        throw new UnsupportedOperationException();
    }

    public final List zzek() {
        return this.zzxt.zzek();
    }

    public final zzhq zzel() {
        return this;
    }
}

