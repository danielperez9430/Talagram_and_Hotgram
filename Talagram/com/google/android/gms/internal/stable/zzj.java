package com.google.android.gms.internal.stable;

import android.database.ContentObserver;
import android.os.Handler;

final class zzj extends ContentObserver {
    zzj(Handler arg1) {
        super(null);
    }

    public final void onChange(boolean arg2) {
        zzi.zzdv().set(true);
    }
}

