package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Looper;

public final class zzk {
    private final boolean zzaha;

    zzk(Context arg1) {
        super();
        this.zzaha = false;
    }

    public static boolean isMainThread() {
        if(Looper.myLooper() == Looper.getMainLooper()) {
            return 1;
        }

        return 0;
    }
}

