package com.google.android.gms.internal.clearcut;

import java.util.Iterator;

final class zzeq implements Iterator {
    private int pos;
    private Iterator zzor;
    private boolean zzow;

    private zzeq(zzei arg1) {
        this.zzos = arg1;
        super();
        this.pos = -1;
    }

    zzeq(zzei arg1, zzej arg2) {
        this(arg1);
    }

    public final boolean hasNext() {
        if(this.pos + 1 >= zzei.zzb(this.zzos).size() && ((zzei.zzc(this.zzos).isEmpty()) || !this.zzdw().hasNext())) {
            return 0;
        }

        return 1;
    }

    public final Object next() {
        this.zzow = true;
        int v1 = this.pos + 1;
        this.pos = v1;
        Object v0 = v1 < zzei.zzb(this.zzos).size() ? zzei.zzb(this.zzos).get(this.pos) : this.zzdw().next();
        return v0;
    }

    public final void remove() {
        if(this.zzow) {
            this.zzow = false;
            zzei.zza(this.zzos);
            if(this.pos < zzei.zzb(this.zzos).size()) {
                zzei v0 = this.zzos;
                int v1 = this.pos;
                this.pos = v1 - 1;
                zzei.zza(v0, v1);
                return;
            }

            this.zzdw().remove();
            return;
        }

        throw new IllegalStateException("remove() was called before next()");
    }

    private final Iterator zzdw() {
        if(this.zzor == null) {
            this.zzor = zzei.zzc(this.zzos).entrySet().iterator();
        }

        return this.zzor;
    }
}

