package com.google.android.gms.internal.clearcut;

public abstract class zzat implements zzdp {
    public zzat() {
        super();
    }

    public Object clone() {
        return this.zzt();
    }

    protected abstract zzat zza(zzas arg1);

    public final zzdp zza(zzdo arg2) {
        if(this.zzbe().getClass().isInstance(arg2)) {
            return this.zza(((zzas)arg2));
        }

        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }

    public abstract zzat zzt();
}

