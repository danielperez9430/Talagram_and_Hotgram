package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.List;

final class zzes implements Iterator {
    private int pos;
    private Iterator zzol;

    private zzes(zzeq arg1) {
        this.zzom = arg1;
        super();
        this.pos = zzeq.zzb(this.zzom).size();
    }

    zzes(zzeq arg1, zzer arg2) {
        this(arg1);
    }

    public final boolean hasNext() {
        if(this.pos > 0 && this.pos <= zzeq.zzb(this.zzom).size() || (this.zzdq().hasNext())) {
            return 1;
        }

        return 0;
    }

    public final Object next() {
        Object v0;
        if(this.zzdq().hasNext()) {
            v0 = this.zzdq().next();
        }
        else {
            List v0_1 = zzeq.zzb(this.zzom);
            int v1 = this.pos - 1;
            this.pos = v1;
            v0 = v0_1.get(v1);
        }

        return v0;
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final Iterator zzdq() {
        if(this.zzol == null) {
            this.zzol = zzeq.zzd(this.zzom).entrySet().iterator();
        }

        return this.zzol;
    }
}

