package com.google.android.gms.wearable;

import com.google.android.gms.wearable.internal.zzfo;

final class zzn implements Runnable {
    zzn(zzd arg1, zzfo arg2) {
        this.zzao = arg1;
        this.zzaq = arg2;
        super();
    }

    public final void run() {
        this.zzao.zzak.onPeerConnected(this.zzaq);
    }
}

