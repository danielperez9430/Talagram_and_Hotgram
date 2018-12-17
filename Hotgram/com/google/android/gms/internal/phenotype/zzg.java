package com.google.android.gms.internal.phenotype;

import android.database.ContentObserver;
import android.os.Handler;

final class zzg extends ContentObserver {
    zzg(Handler arg1) {
        super(null);
    }

    public final void onChange(boolean arg2) {
        zzf.zzi().set(true);
    }
}

