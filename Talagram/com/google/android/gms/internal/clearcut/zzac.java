package com.google.android.gms.internal.clearcut;

import android.database.ContentObserver;
import android.os.Handler;

final class zzac extends ContentObserver {
    zzac(zzab arg1, Handler arg2) {
        this.zzdm = arg1;
        super(null);
    }

    public final void onChange(boolean arg1) {
        this.zzdm.zzh();
        zzab.zza(this.zzdm);
    }
}

