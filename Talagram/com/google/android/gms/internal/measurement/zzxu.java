package com.google.android.gms.internal.measurement;

import java.util.Iterator;

final class zzxu implements Iterator {
    private int pos;
    private Iterator zzccg;
    private boolean zzccl;

    private zzxu(zzxm arg1) {
        this.zzcch = arg1;
        super();
        this.pos = -1;
    }

    zzxu(zzxm arg1, zzxn arg2) {
        this(arg1);
    }

    public final boolean hasNext() {
        if(this.pos + 1 >= zzxm.zzb(this.zzcch).size() && ((zzxm.zzc(this.zzcch).isEmpty()) || !this.zzyb().hasNext())) {
            return 0;
        }

        return 1;
    }

    public final Object next() {
        this.zzccl = true;
        int v1 = this.pos + 1;
        this.pos = v1;
        Object v0 = v1 < zzxm.zzb(this.zzcch).size() ? zzxm.zzb(this.zzcch).get(this.pos) : this.zzyb().next();
        return v0;
    }

    public final void remove() {
        if(this.zzccl) {
            this.zzccl = false;
            zzxm.zza(this.zzcch);
            if(this.pos < zzxm.zzb(this.zzcch).size()) {
                zzxm v0 = this.zzcch;
                int v1 = this.pos;
                this.pos = v1 - 1;
                zzxm.zza(v0, v1);
                return;
            }

            this.zzyb().remove();
            return;
        }

        throw new IllegalStateException("remove() was called before next()");
    }

    private final Iterator zzyb() {
        if(this.zzccg == null) {
            this.zzccg = zzxm.zzc(this.zzcch).entrySet().iterator();
        }

        return this.zzccg;
    }
}

