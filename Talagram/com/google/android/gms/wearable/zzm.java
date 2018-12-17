package com.google.android.gms.wearable;

import com.google.android.gms.wearable.internal.zzfe;

final class zzm implements Runnable {
    zzm(zzd arg1, zzfe arg2) {
        this.zzao = arg1;
        this.zzap = arg2;
        super();
    }

    public final void run() {
        this.zzao.zzak.onMessageReceived(this.zzap);
    }
}

