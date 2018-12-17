package com.google.android.gms.wearable;

import java.util.List;

final class zzp implements Runnable {
    zzp(zzd arg1, List arg2) {
        this.zzao = arg1;
        this.zzar = arg2;
        super();
    }

    public final void run() {
        this.zzao.zzak.onConnectedNodes(this.zzar);
    }
}

