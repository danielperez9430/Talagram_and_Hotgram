package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
import java.util.List;

final class zzek implements Iterator {
    private int pos;
    private Iterator zzor;

    private zzek(zzei arg1) {
        this.zzos = arg1;
        super();
        this.pos = zzei.zzb(this.zzos).size();
    }

    zzek(zzei arg1, zzej arg2) {
        this(arg1);
    }

    public final boolean hasNext() {
        if(this.pos > 0 && this.pos <= zzei.zzb(this.zzos).size() || (this.zzdw().hasNext())) {
            return 1;
        }

        return 0;
    }

    public final Object next() {
        Object v0;
        if(this.zzdw().hasNext()) {
            v0 = this.zzdw().next();
        }
        else {
            List v0_1 = zzei.zzb(this.zzos);
            int v1 = this.pos - 1;
            this.pos = v1;
            v0 = v0_1.get(v1);
        }

        return v0;
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final Iterator zzdw() {
        if(this.zzor == null) {
            this.zzor = zzei.zzd(this.zzos).entrySet().iterator();
        }

        return this.zzor;
    }
}

