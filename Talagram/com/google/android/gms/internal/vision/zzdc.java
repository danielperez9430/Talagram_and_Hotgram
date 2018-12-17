package com.google.android.gms.internal.vision;

import java.util.Map$Entry;

final class zzdc implements Map$Entry {
    private Map$Entry zzmg;

    private zzdc(Map$Entry arg1) {
        super();
        this.zzmg = arg1;
    }

    zzdc(Map$Entry arg1, zzdb arg2) {
        this(arg1);
    }

    public final Object getKey() {
        return this.zzmg.getKey();
    }

    public final Object getValue() {
        if(this.zzmg.getValue() == null) {
            return null;
        }

        return zzda.zzci();
    }

    public final Object setValue(Object arg2) {
        if((arg2 instanceof zzdx)) {
            return this.zzmg.getValue().zzi(((zzdx)arg2));
        }

        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }

    public final zzda zzcj() {
        return this.zzmg.getValue();
    }
}

