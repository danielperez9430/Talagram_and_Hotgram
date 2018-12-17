package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

final class zzsh extends ContentObserver {
    zzsh(Handler arg1) {
        super(null);
    }

    public final void onChange(boolean arg2) {
        zzsg.zzsy().set(true);
    }
}

