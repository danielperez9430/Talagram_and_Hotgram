package com.google.android.gms.internal.firebase_messaging;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class zzd extends WeakReference {
    private final int zzf;

    public zzd(Throwable arg1, ReferenceQueue arg2) {
        super(arg1, arg2);
        if(arg1 != null) {
            this.zzf = System.identityHashCode(arg1);
            return;
        }

        throw new NullPointerException("The referent cannot be null");
    }

    public final boolean equals(Object arg5) {
        if(arg5 != null) {
            if(arg5.getClass() != this.getClass()) {
            }
            else if(this == (((zzd)arg5))) {
                return 1;
            }
            else if(this.zzf == ((zzd)arg5).zzf && this.get() == ((zzd)arg5).get()) {
                return 1;
            }
        }

        return 0;
    }

    public final int hashCode() {
        return this.zzf;
    }
}

