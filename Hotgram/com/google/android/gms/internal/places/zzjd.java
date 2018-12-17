package com.google.android.gms.internal.places;

import java.util.Iterator;
import java.util.List;

final class zzjd implements Iterator {
    private int pos;
    private Iterator zzxj;

    private zzjd(zzjb arg1) {
        this.zzxk = arg1;
        super();
        this.pos = zzjb.zzc(this.zzxk).size();
    }

    zzjd(zzjb arg1, zzjc arg2) {
        this(arg1);
    }

    public final boolean hasNext() {
        if(this.pos > 0 && this.pos <= zzjb.zzc(this.zzxk).size() || (this.zzgl().hasNext())) {
            return 1;
        }

        return 0;
    }

    public final Object next() {
        Object v0;
        if(this.zzgl().hasNext()) {
            v0 = this.zzgl().next();
        }
        else {
            List v0_1 = zzjb.zzc(this.zzxk);
            int v1 = this.pos - 1;
            this.pos = v1;
            v0 = v0_1.get(v1);
        }

        return v0;
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final Iterator zzgl() {
        if(this.zzxj == null) {
            this.zzxj = zzjb.zze(this.zzxk).entrySet().iterator();
        }

        return this.zzxj;
    }
}

