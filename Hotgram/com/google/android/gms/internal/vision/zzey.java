package com.google.android.gms.internal.vision;

import java.util.Iterator;

final class zzey implements Iterator {
    private int pos;
    private Iterator zzol;
    private boolean zzoq;

    private zzey(zzeq arg1) {
        this.zzom = arg1;
        super();
        this.pos = -1;
    }

    zzey(zzeq arg1, zzer arg2) {
        this(arg1);
    }

    public final boolean hasNext() {
        if(this.pos + 1 >= zzeq.zzb(this.zzom).size() && ((zzeq.zzc(this.zzom).isEmpty()) || !this.zzdq().hasNext())) {
            return 0;
        }

        return 1;
    }

    public final Object next() {
        this.zzoq = true;
        int v1 = this.pos + 1;
        this.pos = v1;
        Object v0 = v1 < zzeq.zzb(this.zzom).size() ? zzeq.zzb(this.zzom).get(this.pos) : this.zzdq().next();
        return v0;
    }

    public final void remove() {
        if(this.zzoq) {
            this.zzoq = false;
            zzeq.zza(this.zzom);
            if(this.pos < zzeq.zzb(this.zzom).size()) {
                zzeq v0 = this.zzom;
                int v1 = this.pos;
                this.pos = v1 - 1;
                zzeq.zza(v0, v1);
                return;
            }

            this.zzdq().remove();
            return;
        }

        throw new IllegalStateException("remove() was called before next()");
    }

    private final Iterator zzdq() {
        if(this.zzol == null) {
            this.zzol = zzeq.zzc(this.zzom).entrySet().iterator();
        }

        return this.zzol;
    }
}

