package com.google.android.gms.wearable;

import com.google.android.gms.wearable.internal.zzah;

final class zzq implements Runnable {
    zzq(zzd arg1, zzah arg2) {
        this.zzao = arg1;
        this.zzas = arg2;
        super();
    }

    public final void run() {
        this.zzao.zzak.onCapabilityChanged(this.zzas);
    }
}

