package com.google.android.gms.internal.measurement;

public abstract class zztx implements zzwu {
    public zztx() {
        super();
    }

    public Object clone() {
        return this.zztv();
    }

    protected abstract zztx zza(zztw arg1);

    public final zzwu zza(zzwt arg2) {
        if(this.zzwf().getClass().isInstance(arg2)) {
            return this.zza(((zztw)arg2));
        }

        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }

    public abstract zztx zztv();
}

