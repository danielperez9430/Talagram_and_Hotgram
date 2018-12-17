package com.google.android.gms.internal.vision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class zzdf extends zzbi implements zzdg, RandomAccess {
    private static final zzdf zzml;
    private static final zzdg zzmm;
    private final List zzmn;

    static {
        zzdf v0 = new zzdf();
        zzdf.zzml = v0;
        ((zzbi)v0).zzao();
        zzdf.zzmm = zzdf.zzml;
    }

    public zzdf(int arg2) {
        this(new ArrayList(arg2));
    }

    public zzdf() {
        this(10);
    }

    private zzdf(ArrayList arg1) {
        super();
        this.zzmn = ((List)arg1);
    }

    public final void add(int arg2, Object arg3) {
        ((zzbi)this).zzap();
        this.zzmn.add(arg2, arg3);
        ++this.modCount;
    }

    public final boolean addAll(int arg2, Collection arg3) {
        List v3;
        ((zzbi)this).zzap();
        if((arg3 instanceof zzdg)) {
            v3 = ((zzdg)arg3).zzck();
        }

        boolean v2 = this.zzmn.addAll(arg2, ((Collection)v3));
        ++this.modCount;
        return v2;
    }

    public final boolean addAll(Collection arg2) {
        return ((zzbi)this).addAll(this.size(), arg2);
    }

    public final void clear() {
        ((zzbi)this).zzap();
        this.zzmn.clear();
        ++this.modCount;
    }

    public final boolean equals(Object arg1) {
        return super.equals(arg1);
    }

    public final Object get(int arg3) {
        String v1;
        Object v0 = this.zzmn.get(arg3);
        if((v0 instanceof String)) {
            return v0;
        }

        if((v0 instanceof zzbo)) {
            v1 = ((zzbo)v0).zzas();
            if(((zzbo)v0).zzat()) {
                this.zzmn.set(arg3, v1);
            }

            return v1;
        }

        v1 = zzct.zzg(((byte[])v0));
        if(zzct.zzf(((byte[])v0))) {
            this.zzmn.set(arg3, v1);
        }

        return v1;
    }

    public final Object getRaw(int arg2) {
        return this.zzmn.get(arg2);
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public final Object remove(int arg2) {
        ((zzbi)this).zzap();
        Object v2 = this.zzmn.remove(arg2);
        ++this.modCount;
        return zzdf.zzf(v2);
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
        ((zzbi)this).zzap();
        return zzdf.zzf(this.zzmn.set(arg2, arg3));
    }

    public final int size() {
        return this.zzmn.size();
    }

    public final boolean zzan() {
        return super.zzan();
    }

    public final void zzc(zzbo arg2) {
        ((zzbi)this).zzap();
        this.zzmn.add(arg2);
        ++this.modCount;
    }

    public final List zzck() {
        return Collections.unmodifiableList(this.zzmn);
    }

    public final zzdg zzcl() {
        if(((zzbi)this).zzan()) {
            return new zzfi(((zzdg)this));
        }

        return this;
    }

    private static String zzf(Object arg1) {
        if((arg1 instanceof String)) {
            return arg1;
        }

        if((arg1 instanceof zzbo)) {
            return ((zzbo)arg1).zzas();
        }

        return zzct.zzg(((byte[])arg1));
    }

    public final zzcw zzk(int arg2) {
        if(arg2 >= this.size()) {
            ArrayList v0 = new ArrayList(arg2);
            v0.addAll(this.zzmn);
            return new zzdf(v0);
        }

        throw new IllegalArgumentException();
    }
}

