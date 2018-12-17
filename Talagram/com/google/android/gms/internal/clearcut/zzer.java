package com.google.android.gms.internal.clearcut;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map$Entry;

class zzer extends AbstractSet {
    zzer(zzei arg1, zzej arg2) {
        this(arg1);
    }

    private zzer(zzei arg1) {
        this.zzos = arg1;
        super();
    }

    public boolean add(Object arg3) {
        if(!this.contains(arg3)) {
            this.zzos.zza(((Map$Entry)arg3).getKey(), ((Map$Entry)arg3).getValue());
            return 1;
        }

        return 0;
    }

    public void clear() {
        this.zzos.clear();
    }

    public boolean contains(Object arg3) {
        Object v0 = this.zzos.get(((Map$Entry)arg3).getKey());
        arg3 = ((Map$Entry)arg3).getValue();
        if(v0 != arg3 && (v0 == null || !v0.equals(arg3))) {
            return 0;
        }

        return 1;
    }

    public Iterator iterator() {
        return new zzeq(this.zzos, null);
    }

    public boolean remove(Object arg2) {
        if(this.contains(arg2)) {
            this.zzos.remove(((Map$Entry)arg2).getKey());
            return 1;
        }

        return 0;
    }

    public int size() {
        return this.zzos.size();
    }
}

