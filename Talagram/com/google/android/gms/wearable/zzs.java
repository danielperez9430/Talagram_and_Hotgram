package com.google.android.gms.wearable;

import com.google.android.gms.wearable.internal.zzi;

final class zzs implements Runnable {
    zzs(zzd arg1, zzi arg2) {
        this.zzao = arg1;
        this.zzau = arg2;
        super();
    }

    public final void run() {
        this.zzao.zzak.onEntityUpdate(this.zzau);
    }
}

