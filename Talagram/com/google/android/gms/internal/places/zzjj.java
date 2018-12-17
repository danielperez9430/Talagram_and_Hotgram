package com.google.android.gms.internal.places;

import java.util.Iterator;

final class zzjj implements Iterator {
    private int pos;
    private Iterator zzxj;
    private boolean zzxo;

    private zzjj(zzjb arg1) {
        this.zzxk = arg1;
        super();
        this.pos = -1;
    }

    zzjj(zzjb arg1, zzjc arg2) {
        this(arg1);
    }

    public final boolean hasNext() {
        if(this.pos + 1 >= zzjb.zzc(this.zzxk).size() && ((zzjb.zzd(this.zzxk).isEmpty()) || !this.zzgl().hasNext())) {
            return 0;
        }

        return 1;
    }

    public final Object next() {
        this.zzxo = true;
        int v1 = this.pos + 1;
        this.pos = v1;
        Object v0 = v1 < zzjb.zzc(this.zzxk).size() ? zzjb.zzc(this.zzxk).get(this.pos) : this.zzgl().next();
        return v0;
    }

    public final void remove() {
        if(this.zzxo) {
            this.zzxo = false;
            zzjb.zzb(this.zzxk);
            if(this.pos < zzjb.zzc(this.zzxk).size()) {
                zzjb v0 = this.zzxk;
                int v1 = this.pos;
                this.pos = v1 - 1;
                zzjb.zzb(v0, v1);
                return;
            }

            this.zzgl().remove();
            return;
        }

        throw new IllegalStateException("remove() was called before next()");
    }

    private final Iterator zzgl() {
        if(this.zzxj == null) {
            this.zzxj = zzjb.zzd(this.zzxk).entrySet().iterator();
        }

        return this.zzxj;
    }
}

