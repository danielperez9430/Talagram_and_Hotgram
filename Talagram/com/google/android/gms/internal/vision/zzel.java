package com.google.android.gms.internal.vision;

import java.util.ArrayList;
import java.util.List;

final class zzel extends zzbi {
    private final List zzmn;
    private static final zzel zzoa;

    static {
        zzel v0 = new zzel();
        zzel.zzoa = v0;
        ((zzbi)v0).zzao();
    }

    zzel() {
        this(new ArrayList(10));
    }

    private zzel(List arg1) {
        super();
        this.zzmn = arg1;
    }

    public final void add(int arg2, Object arg3) {
        ((zzbi)this).zzap();
        this.zzmn.add(arg2, arg3);
        ++this.modCount;
    }

    public final Object get(int arg2) {
        return this.zzmn.get(arg2);
    }

    public final Object remove(int arg2) {
        ((zzbi)this).zzap();
        Object v2 = this.zzmn.remove(arg2);
        ++this.modCount;
        return v2;
    }

    public final Object set(int arg2, Object arg3) {
        ((zzbi)this).zzap();
        Object v2 = this.zzmn.set(arg2, arg3);
        ++this.modCount;
        return v2;
    }

    public final int size() {
        return this.zzmn.size();
    }

    public static zzel zzdd() {
        return zzel.zzoa;
    }

    public final zzcw zzk(int arg2) {
        if(arg2 >= this.size()) {
            ArrayList v0 = new ArrayList(arg2);
            ((List)v0).addAll(this.zzmn);
            return new zzel(((List)v0));
        }

        throw new IllegalArgumentException();
    }
}

