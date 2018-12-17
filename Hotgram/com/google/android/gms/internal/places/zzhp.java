package com.google.android.gms.internal.places;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class zzhp extends zzfk implements zzhq, RandomAccess {
    private static final zzhp zzui;
    private static final zzhq zzuj;
    private final List zzuk;

    static {
        zzhp v0 = new zzhp();
        zzhp.zzui = v0;
        ((zzfk)v0).zzbb();
        zzhp.zzuj = zzhp.zzui;
    }

    public zzhp(int arg2) {
        this(new ArrayList(arg2));
    }

    public zzhp() {
        this(10);
    }

    private zzhp(ArrayList arg1) {
        super();
        this.zzuk = ((List)arg1);
    }

    public final void add(int arg2, Object arg3) {
        ((zzfk)this).zzbc();
        this.zzuk.add(arg2, arg3);
        ++this.modCount;
    }

    public final boolean addAll(int arg2, Collection arg3) {
        List v3;
        ((zzfk)this).zzbc();
        if((arg3 instanceof zzhq)) {
            v3 = ((zzhq)arg3).zzek();
        }

        boolean v2 = this.zzuk.addAll(arg2, ((Collection)v3));
        ++this.modCount;
        return v2;
    }

    public final boolean addAll(Collection arg2) {
        return ((zzfk)this).addAll(this.size(), arg2);
    }

    public final void clear() {
        ((zzfk)this).zzbc();
        this.zzuk.clear();
        ++this.modCount;
    }

    public final boolean equals(Object arg1) {
        return super.equals(arg1);
    }

    public final Object get(int arg3) {
        String v1;
        Object v0 = this.zzuk.get(arg3);
        if((v0 instanceof String)) {
            return v0;
        }

        if((v0 instanceof zzfr)) {
            v1 = ((zzfr)v0).zzcd();
            if(((zzfr)v0).zzce()) {
                this.zzuk.set(arg3, v1);
            }

            return v1;
        }

        v1 = zzhb.zzg(((byte[])v0));
        if(zzhb.zzf(((byte[])v0))) {
            this.zzuk.set(arg3, v1);
        }

        return v1;
    }

    public final Object getRaw(int arg2) {
        return this.zzuk.get(arg2);
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public final Object remove(int arg2) {
        ((zzfk)this).zzbc();
        Object v2 = this.zzuk.remove(arg2);
        ++this.modCount;
        return zzhp.zzf(v2);
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
        ((zzfk)this).zzbc();
        return zzhp.zzf(this.zzuk.set(arg2, arg3));
    }

    public final int size() {
        return this.zzuk.size();
    }

    public final zzhg zzae(int arg2) {
        if(arg2 >= this.size()) {
            ArrayList v0 = new ArrayList(arg2);
            v0.addAll(this.zzuk);
            return new zzhp(v0);
        }

        throw new IllegalArgumentException();
    }

    public final boolean zzba() {
        return super.zzba();
    }

    public final void zzd(zzfr arg2) {
        ((zzfk)this).zzbc();
        this.zzuk.add(arg2);
        ++this.modCount;
    }

    public final List zzek() {
        return Collections.unmodifiableList(this.zzuk);
    }

    public final zzhq zzel() {
        if(((zzfk)this).zzba()) {
            return new zzjt(((zzhq)this));
        }

        return this;
    }

    private static String zzf(Object arg1) {
        if((arg1 instanceof String)) {
            return arg1;
        }

        if((arg1 instanceof zzfr)) {
            return ((zzfr)arg1).zzcd();
        }

        return zzhb.zzg(((byte[])arg1));
    }
}

