package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class zzwb extends zztz implements zzwc, RandomAccess {
    private static final zzwb zzcag;
    private static final zzwc zzcah;
    private final List zzcai;

    static {
        zzwb v0 = new zzwb();
        zzwb.zzcag = v0;
        ((zztz)v0).zzsm();
        zzwb.zzcah = zzwb.zzcag;
    }

    public zzwb(int arg2) {
        this(new ArrayList(arg2));
    }

    public zzwb() {
        this(10);
    }

    private zzwb(ArrayList arg1) {
        super();
        this.zzcai = ((List)arg1);
    }

    public final void add(int arg2, Object arg3) {
        ((zztz)this).zztx();
        this.zzcai.add(arg2, arg3);
        ++this.modCount;
    }

    public final boolean addAll(int arg2, Collection arg3) {
        List v3;
        ((zztz)this).zztx();
        if((arg3 instanceof zzwc)) {
            v3 = ((zzwc)arg3).zzwv();
        }

        boolean v2 = this.zzcai.addAll(arg2, ((Collection)v3));
        ++this.modCount;
        return v2;
    }

    public final boolean addAll(Collection arg2) {
        return ((zztz)this).addAll(this.size(), arg2);
    }

    public final void clear() {
        ((zztz)this).zztx();
        this.zzcai.clear();
        ++this.modCount;
    }

    public final boolean equals(Object arg1) {
        return super.equals(arg1);
    }

    public final Object get(int arg3) {
        String v1;
        Object v0 = this.zzcai.get(arg3);
        if((v0 instanceof String)) {
            return v0;
        }

        if((v0 instanceof zzud)) {
            v1 = ((zzud)v0).zzua();
            if(((zzud)v0).zzub()) {
                this.zzcai.set(arg3, v1);
            }

            return v1;
        }

        v1 = zzvo.zzm(((byte[])v0));
        if(zzvo.zzl(((byte[])v0))) {
            this.zzcai.set(arg3, v1);
        }

        return v1;
    }

    public final Object getRaw(int arg2) {
        return this.zzcai.get(arg2);
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public final Object remove(int arg2) {
        ((zztz)this).zztx();
        Object v2 = this.zzcai.remove(arg2);
        ++this.modCount;
        return zzwb.zzw(v2);
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
        ((zztz)this).zztx();
        return zzwb.zzw(this.zzcai.set(arg2, arg3));
    }

    public final int size() {
        return this.zzcai.size();
    }

    public final zzvs zzak(int arg2) {
        if(arg2 >= this.size()) {
            ArrayList v0 = new ArrayList(arg2);
            v0.addAll(this.zzcai);
            return new zzwb(v0);
        }

        throw new IllegalArgumentException();
    }

    public final void zzc(zzud arg2) {
        ((zztz)this).zztx();
        this.zzcai.add(arg2);
        ++this.modCount;
    }

    public final boolean zztw() {
        return super.zztw();
    }

    private static String zzw(Object arg1) {
        if((arg1 instanceof String)) {
            return arg1;
        }

        if((arg1 instanceof zzud)) {
            return ((zzud)arg1).zzua();
        }

        return zzvo.zzm(((byte[])arg1));
    }

    public final List zzwv() {
        return Collections.unmodifiableList(this.zzcai);
    }

    public final zzwc zzww() {
        if(((zztz)this).zztw()) {
            return new zzye(((zzwc)this));
        }

        return this;
    }
}

