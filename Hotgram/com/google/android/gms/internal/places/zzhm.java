package com.google.android.gms.internal.places;

import java.util.Map$Entry;

final class zzhm implements Map$Entry {
    private Map$Entry zzud;

    private zzhm(Map$Entry arg1) {
        super();
        this.zzud = arg1;
    }

    zzhm(Map$Entry arg1, zzhl arg2) {
        this(arg1);
    }

    public final Object getKey() {
        return this.zzud.getKey();
    }

    public final Object getValue() {
        if(this.zzud.getValue() == null) {
            return null;
        }

        return zzhk.zzei();
    }

    public final Object setValue(Object arg2) {
        if((arg2 instanceof zzih)) {
            return this.zzud.getValue().zzj(((zzih)arg2));
        }

        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }

    public final zzhk zzej() {
        return this.zzud.getValue();
    }
}

