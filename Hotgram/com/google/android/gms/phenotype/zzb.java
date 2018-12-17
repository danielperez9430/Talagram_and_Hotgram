package com.google.android.gms.phenotype;

import android.database.ContentObserver;
import android.os.Handler;

final class zzb extends ContentObserver {
    zzb(zza arg1, Handler arg2) {
        this.zzm = arg1;
        super(null);
    }

    public final void onChange(boolean arg1) {
        this.zzm.zzb();
    }
}

