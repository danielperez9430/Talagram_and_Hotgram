package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;

final class zzxg extends zztz {
    private final List zzcai;
    private static final zzxg zzcbv;

    static {
        zzxg v0 = new zzxg();
        zzxg.zzcbv = v0;
        ((zztz)v0).zzsm();
    }

    zzxg() {
        this(new ArrayList(10));
    }

    private zzxg(List arg1) {
        super();
        this.zzcai = arg1;
    }

    public final void add(int arg2, Object arg3) {
        ((zztz)this).zztx();
        this.zzcai.add(arg2, arg3);
        ++this.modCount;
    }

    public final Object get(int arg2) {
        return this.zzcai.get(arg2);
    }

    public final Object remove(int arg2) {
        ((zztz)this).zztx();
        Object v2 = this.zzcai.remove(arg2);
        ++this.modCount;
        return v2;
    }

    public final Object set(int arg2, Object arg3) {
        ((zztz)this).zztx();
        Object v2 = this.zzcai.set(arg2, arg3);
        ++this.modCount;
        return v2;
    }

    public final int size() {
        return this.zzcai.size();
    }

    public final zzvs zzak(int arg2) {
        if(arg2 >= this.size()) {
            ArrayList v0 = new ArrayList(arg2);
            ((List)v0).addAll(this.zzcai);
            return new zzxg(((List)v0));
        }

        throw new IllegalArgumentException();
    }

    public static zzxg zzxo() {
        return zzxg.zzcbv;
    }
}

