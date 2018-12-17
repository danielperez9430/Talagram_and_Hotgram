package com.google.android.gms.internal.places;

public abstract class zzfi implements zzii {
    public zzfi() {
        super();
    }

    public Object clone() {
        return this.zzaz();
    }

    public abstract zzfi zzaz();

    protected abstract zzfi zzb(zzfh arg1);

    public final zzii zzb(zzih arg2) {
        if(this.zzds().getClass().isInstance(arg2)) {
            return this.zzb(((zzfh)arg2));
        }

        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}

