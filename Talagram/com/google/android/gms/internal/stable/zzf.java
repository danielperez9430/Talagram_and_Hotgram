package com.google.android.gms.internal.stable;

import android.database.ContentObserver;
import android.os.Handler;

final class zzf extends ContentObserver {
    zzf(Handler arg1, zzh arg2) {
        this.zzagr = arg2;
        super(null);
    }

    public final void onChange(boolean arg2) {
        this.zzagr.zzagu.set(true);
    }
}

