package com.google.android.gms.internal.stable;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class zzn extends WeakReference {
    private final int zzahl;

    public zzn(Throwable arg1, ReferenceQueue arg2) {
        super(arg1, arg2);
        if(arg1 != null) {
            this.zzahl = System.identityHashCode(arg1);
            return;
        }

        throw new NullPointerException("The referent cannot be null");
    }

    public final boolean equals(Object arg5) {
        if(arg5 != null) {
            if(arg5.getClass() != this.getClass()) {
            }
            else if(this == (((zzn)arg5))) {
                return 1;
            }
            else if(this.zzahl == ((zzn)arg5).zzahl && this.get() == ((zzn)arg5).get()) {
                return 1;
            }
        }

        return 0;
    }

    public final int hashCode() {
        return this.zzahl;
    }
}

