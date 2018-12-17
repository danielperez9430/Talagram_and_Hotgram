package com.google.android.gms.wearable;

import com.google.android.gms.wearable.internal.zzfo;

final class zzo implements Runnable {
    zzo(zzd arg1, zzfo arg2) {
        this.zzao = arg1;
        this.zzaq = arg2;
        super();
    }

    public final void run() {
        this.zzao.zzak.onPeerDisconnected(this.zzaq);
    }
}

