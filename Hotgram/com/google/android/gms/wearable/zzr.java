package com.google.android.gms.wearable;

import com.google.android.gms.wearable.internal.zzl;

final class zzr implements Runnable {
    zzr(zzd arg1, zzl arg2) {
        this.zzao = arg1;
        this.zzat = arg2;
        super();
    }

    public final void run() {
        this.zzao.zzak.onNotificationReceived(this.zzat);
    }
}

