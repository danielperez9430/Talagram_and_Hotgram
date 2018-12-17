package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class zzye extends AbstractList implements zzwc, RandomAccess {
    private final zzwc zzccq;

    public zzye(zzwc arg1) {
        super();
        this.zzccq = arg1;
    }

    public final Object get(int arg2) {
        return this.zzccq.get(arg2);
    }

    public final Object getRaw(int arg2) {
        return this.zzccq.getRaw(arg2);
    }

    public final Iterator iterator() {
        return new zzyg(this);
    }

    public final ListIterator listIterator(int arg2) {
        return new zzyf(this, arg2);
    }

    public final int size() {
        return this.zzccq.size();
    }

    static zzwc zza(zzye arg0) {
        return arg0.zzccq;
    }

    public final void zzc(zzud arg1) {
        throw new UnsupportedOperationException();
    }

    public final List zzwv() {
        return this.zzccq.zzwv();
    }

    public final zzwc zzww() {
        return this;
    }
}

