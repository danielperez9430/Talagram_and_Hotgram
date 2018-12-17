package com.google.android.gms.internal.clearcut;

import java.util.Map$Entry;

final class zzct implements Map$Entry {
    private Map$Entry zzll;

    private zzct(Map$Entry arg1) {
        super();
        this.zzll = arg1;
    }

    zzct(Map$Entry arg1, zzcs arg2) {
        this(arg1);
    }

    public final Object getKey() {
        return this.zzll.getKey();
    }

    public final Object getValue() {
        if(this.zzll.getValue() == null) {
            return null;
        }

        return zzcr.zzbr();
    }

    public final Object setValue(Object arg2) {
        if((arg2 instanceof zzdo)) {
            return this.zzll.getValue().zzi(((zzdo)arg2));
        }

        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }

    public final zzcr zzbs() {
        return this.zzll.getValue();
    }
}

