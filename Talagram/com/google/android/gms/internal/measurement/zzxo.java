package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;

final class zzxo implements Iterator {
    private int pos;
    private Iterator zzccg;

    private zzxo(zzxm arg1) {
        this.zzcch = arg1;
        super();
        this.pos = zzxm.zzb(this.zzcch).size();
    }

    zzxo(zzxm arg1, zzxn arg2) {
        this(arg1);
    }

    public final boolean hasNext() {
        if(this.pos > 0 && this.pos <= zzxm.zzb(this.zzcch).size() || (this.zzyb().hasNext())) {
            return 1;
        }

        return 0;
    }

    public final Object next() {
        Object v0;
        if(this.zzyb().hasNext()) {
            v0 = this.zzyb().next();
        }
        else {
            List v0_1 = zzxm.zzb(this.zzcch);
            int v1 = this.pos - 1;
            this.pos = v1;
            v0 = v0_1.get(v1);
        }

        return v0;
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final Iterator zzyb() {
        if(this.zzccg == null) {
            this.zzccg = zzxm.zzd(this.zzcch).entrySet().iterator();
        }

        return this.zzccg;
    }
}

