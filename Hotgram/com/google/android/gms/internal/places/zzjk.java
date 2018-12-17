package com.google.android.gms.internal.places;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map$Entry;

class zzjk extends AbstractSet {
    zzjk(zzjb arg1, zzjc arg2) {
        this(arg1);
    }

    private zzjk(zzjb arg1) {
        this.zzxk = arg1;
        super();
    }

    public boolean add(Object arg3) {
        if(!this.contains(arg3)) {
            this.zzxk.zzb(((Map$Entry)arg3).getKey(), ((Map$Entry)arg3).getValue());
            return 1;
        }

        return 0;
    }

    public void clear() {
        this.zzxk.clear();
    }

    public boolean contains(Object arg3) {
        Object v0 = this.zzxk.get(((Map$Entry)arg3).getKey());
        arg3 = ((Map$Entry)arg3).getValue();
        if(v0 != arg3 && (v0 == null || !v0.equals(arg3))) {
            return 0;
        }

        return 1;
    }

    public Iterator iterator() {
        return new zzjj(this.zzxk, null);
    }

    public boolean remove(Object arg2) {
        if(this.contains(arg2)) {
            this.zzxk.remove(((Map$Entry)arg2).getKey());
            return 1;
        }

        return 0;
    }

    public int size() {
        return this.zzxk.size();
    }
}

