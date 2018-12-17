package com.google.android.gms.internal.vision;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map$Entry;

class zzez extends AbstractSet {
    zzez(zzeq arg1, zzer arg2) {
        this(arg1);
    }

    private zzez(zzeq arg1) {
        this.zzom = arg1;
        super();
    }

    public boolean add(Object arg3) {
        if(!this.contains(arg3)) {
            this.zzom.zza(((Map$Entry)arg3).getKey(), ((Map$Entry)arg3).getValue());
            return 1;
        }

        return 0;
    }

    public void clear() {
        this.zzom.clear();
    }

    public boolean contains(Object arg3) {
        Object v0 = this.zzom.get(((Map$Entry)arg3).getKey());
        arg3 = ((Map$Entry)arg3).getValue();
        if(v0 != arg3 && (v0 == null || !v0.equals(arg3))) {
            return 0;
        }

        return 1;
    }

    public Iterator iterator() {
        return new zzey(this.zzom, null);
    }

    public boolean remove(Object arg2) {
        if(this.contains(arg2)) {
            this.zzom.remove(((Map$Entry)arg2).getKey());
            return 1;
        }

        return 0;
    }

    public int size() {
        return this.zzom.size();
    }
}

