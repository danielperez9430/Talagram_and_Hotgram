package com.google.android.gms.stats;

import java.lang.ref.WeakReference;

final class zzc implements Runnable {
    zzc(WeakReference arg1) {
        this.zzaej = arg1;
        super();
    }

    public final void run() {
        Object v0 = this.zzaej.get();
        if(v0 != null) {
            ((HeldLock)v0).release(0);
        }
    }
}

