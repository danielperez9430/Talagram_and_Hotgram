package com.google.android.gms.internal.clearcut;

import java.util.ArrayList;
import java.util.List;

final class zzeb extends zzav {
    private final List zzls;
    private static final zzeb zznf;

    static {
        zzeb v0 = new zzeb();
        zzeb.zznf = v0;
        ((zzav)v0).zzv();
    }

    zzeb() {
        this(new ArrayList(10));
    }

    private zzeb(List arg1) {
        super();
        this.zzls = arg1;
    }

    public final void add(int arg2, Object arg3) {
        ((zzav)this).zzw();
        this.zzls.add(arg2, arg3);
        ++this.modCount;
    }

    public final Object get(int arg2) {
        return this.zzls.get(arg2);
    }

    public final Object remove(int arg2) {
        ((zzav)this).zzw();
        Object v2 = this.zzls.remove(arg2);
        ++this.modCount;
        return v2;
    }

    public final Object set(int arg2, Object arg3) {
        ((zzav)this).zzw();
        Object v2 = this.zzls.set(arg2, arg3);
        ++this.modCount;
        return v2;
    }

    public final int size() {
        return this.zzls.size();
    }

    public static zzeb zzcn() {
        return zzeb.zznf;
    }

    public final zzcn zzi(int arg2) {
        if(arg2 >= this.size()) {
            ArrayList v0 = new ArrayList(arg2);
            ((List)v0).addAll(this.zzls);
            return new zzeb(((List)v0));
        }

        throw new IllegalArgumentException();
    }
}

