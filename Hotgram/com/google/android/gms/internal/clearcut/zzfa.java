package com.google.android.gms.internal.clearcut;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class zzfa extends AbstractList implements zzcx, RandomAccess {
    private final zzcx zzpb;

    public zzfa(zzcx arg1) {
        super();
        this.zzpb = arg1;
    }

    public final Object get(int arg2) {
        return this.zzpb.get(arg2);
    }

    public final Object getRaw(int arg2) {
        return this.zzpb.getRaw(arg2);
    }

    public final Iterator iterator() {
        return new zzfc(this);
    }

    public final ListIterator listIterator(int arg2) {
        return new zzfb(this, arg2);
    }

    public final int size() {
        return this.zzpb.size();
    }

    static zzcx zza(zzfa arg0) {
        return arg0.zzpb;
    }

    public final List zzbt() {
        return this.zzpb.zzbt();
    }

    public final zzcx zzbu() {
        return this;
    }
}

