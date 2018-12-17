package com.google.android.gms.internal.measurement;

import java.util.Map$Entry;

final class zzvy implements Map$Entry {
    private Map$Entry zzcab;

    private zzvy(Map$Entry arg1) {
        super();
        this.zzcab = arg1;
    }

    zzvy(Map$Entry arg1, zzvx arg2) {
        this(arg1);
    }

    public final Object getKey() {
        return this.zzcab.getKey();
    }

    public final Object getValue() {
        if(this.zzcab.getValue() == null) {
            return null;
        }

        return zzvw.zzwt();
    }

    public final Object setValue(Object arg2) {
        if((arg2 instanceof zzwt)) {
            return this.zzcab.getValue().zzi(((zzwt)arg2));
        }

        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }

    public final zzvw zzwu() {
        return this.zzcab.getValue();
    }
}

