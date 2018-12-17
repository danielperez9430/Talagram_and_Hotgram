package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;

final class zzbb extends Callback {
    private WeakReference zziy;

    zzbb(zzav arg2) {
        super();
        this.zziy = new WeakReference(arg2);
    }

    public final void zzv() {
        Object v0 = this.zziy.get();
        if(v0 == null) {
            return;
        }

        zzav.zza(((zzav)v0));
    }
}

