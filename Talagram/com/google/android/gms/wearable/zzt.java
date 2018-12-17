package com.google.android.gms.wearable;

import com.google.android.gms.wearable.internal.zzaw;

final class zzt implements Runnable {
    zzt(zzd arg1, zzaw arg2) {
        this.zzao = arg1;
        this.zzav = arg2;
        super();
    }

    public final void run() {
        this.zzav.zza(this.zzao.zzak);
        this.zzav.zza(WearableListenerService.zzc(this.zzao.zzak));
    }
}

