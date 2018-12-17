package com.google.android.gms.internal.clearcut;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class zzcw extends zzav implements zzcx, RandomAccess {
    private static final zzcw zzlq;
    private static final zzcx zzlr;
    private final List zzls;

    static {
        zzcw v0 = new zzcw();
        zzcw.zzlq = v0;
        ((zzav)v0).zzv();
        zzcw.zzlr = zzcw.zzlq;
    }

    public zzcw(int arg2) {
        this(new ArrayList(arg2));
    }

    public zzcw() {
        this(10);
    }

    private zzcw(ArrayList arg1) {
        super();
        this.zzls = ((List)arg1);
    }

    public final void add(int arg2, Object arg3) {
        ((zzav)this).zzw();
        this.zzls.add(arg2, arg3);
        ++this.modCount;
    }

    public final boolean addAll(int arg2, Collection arg3) {
        List v3;
        ((zzav)this).zzw();
        if((arg3 instanceof zzcx)) {
            v3 = ((zzcx)arg3).zzbt();
        }

        boolean v2 = this.zzls.addAll(arg2, ((Collection)v3));
        ++this.modCount;
        return v2;
    }

    public final boolean addAll(Collection arg2) {
        return ((zzav)this).addAll(this.size(), arg2);
    }

    public final void clear() {
        ((zzav)this).zzw();
        this.zzls.clear();
        ++this.modCount;
    }

    public final boolean equals(Object arg1) {
        return super.equals(arg1);
    }

    public final Object get(int arg3) {
        String v1;
        Object v0 = this.zzls.get(arg3);
        if((v0 instanceof String)) {
            return v0;
        }

        if((v0 instanceof zzbb)) {
            v1 = ((zzbb)v0).zzz();
            if(((zzbb)v0).zzaa()) {
                this.zzls.set(arg3, v1);
            }

            return v1;
        }

        v1 = zzci.zzf(((byte[])v0));
        if(zzci.zze(((byte[])v0))) {
            this.zzls.set(arg3, v1);
        }

        return v1;
    }

    public final Object getRaw(int arg2) {
        return this.zzls.get(arg2);
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public final Object remove(int arg2) {
        ((zzav)this).zzw();
        Object v2 = this.zzls.remove(arg2);
        ++this.modCount;
        return zzcw.zze(v2);
    }

    public final boolean remove(Object arg1) {
        return super.remove(arg1);
    }

    public final boolean removeAll(Collection arg1) {
        return super.removeAll(arg1);
    }

    public final boolean retainAll(Collection arg1) {
        return super.retainAll(arg1);
    }

    public final Object set(int arg2, Object arg3) {
        ((zzav)this).zzw();
        return zzcw.zze(this.zzls.set(arg2, arg3));
    }

    public final int size() {
        return this.zzls.size();
    }

    public final List zzbt() {
        return Collections.unmodifiableList(this.zzls);
    }

    public final zzcx zzbu() {
        if(((zzav)this).zzu()) {
            return new zzfa(((zzcx)this));
        }

        return this;
    }

    private static String zze(Object arg1) {
        if((arg1 instanceof String)) {
            return arg1;
        }

        if((arg1 instanceof zzbb)) {
            return ((zzbb)arg1).zzz();
        }

        return zzci.zzf(((byte[])arg1));
    }

    public final zzcn zzi(int arg2) {
        if(arg2 >= this.size()) {
            ArrayList v0 = new ArrayList(arg2);
            v0.addAll(this.zzls);
            return new zzcw(v0);
        }

        throw new IllegalArgumentException();
    }

    public final boolean zzu() {
        return super.zzu();
    }
}

