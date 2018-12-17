package com.google.android.gms.internal.vision;

public abstract class zzbg implements zzdy {
    public zzbg() {
        super();
    }

    public Object clone() {
        return this.zzam();
    }

    protected abstract zzbg zza(zzbf arg1);

    public final zzdy zza(zzdx arg2) {
        if(this.zzbw().getClass().isInstance(arg2)) {
            return this.zza(((zzbf)arg2));
        }

        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }

    public abstract zzbg zzam();
}

