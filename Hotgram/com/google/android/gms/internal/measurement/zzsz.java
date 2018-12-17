package com.google.android.gms.internal.measurement;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class zzsz extends WeakReference {
    private final int zzbry;

    public zzsz(Throwable arg1, ReferenceQueue arg2) {
        super(arg1, null);
        if(arg1 != null) {
            this.zzbry = System.identityHashCode(arg1);
            return;
        }

        throw new NullPointerException("The referent cannot be null");
    }

    public final boolean equals(Object arg5) {
        if(arg5 != null) {
            if(arg5.getClass() != this.getClass()) {
            }
            else if(this == (((zzsz)arg5))) {
                return 1;
            }
            else if(this.zzbry == ((zzsz)arg5).zzbry && this.get() == ((zzsz)arg5).get()) {
                return 1;
            }
        }

        return 0;
    }

    public final int hashCode() {
        return this.zzbry;
    }
}

