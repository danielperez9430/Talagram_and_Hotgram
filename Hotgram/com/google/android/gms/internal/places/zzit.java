package com.google.android.gms.internal.places;

import java.util.ArrayList;
import java.util.List;

final class zzit extends zzfk {
    private final List zzuk;
    private static final zzit zzvx;

    static {
        zzit v0 = new zzit();
        zzit.zzvx = v0;
        ((zzfk)v0).zzbb();
    }

    zzit() {
        this(new ArrayList(10));
    }

    private zzit(List arg1) {
        super();
        this.zzuk = arg1;
    }

    public final void add(int arg2, Object arg3) {
        ((zzfk)this).zzbc();
        this.zzuk.add(arg2, arg3);
        ++this.modCount;
    }

    public final Object get(int arg2) {
        return this.zzuk.get(arg2);
    }

    public final Object remove(int arg2) {
        ((zzfk)this).zzbc();
        Object v2 = this.zzuk.remove(arg2);
        ++this.modCount;
        return v2;
    }

    public final Object set(int arg2, Object arg3) {
        ((zzfk)this).zzbc();
        Object v2 = this.zzuk.set(arg2, arg3);
        ++this.modCount;
        return v2;
    }

    public final int size() {
        return this.zzuk.size();
    }

    public final zzhg zzae(int arg2) {
        if(arg2 >= this.size()) {
            ArrayList v0 = new ArrayList(arg2);
            ((List)v0).addAll(this.zzuk);
            return new zzit(((List)v0));
        }

        throw new IllegalArgumentException();
    }

    public static zzit zzfd() {
        return zzit.zzvx;
    }
}

