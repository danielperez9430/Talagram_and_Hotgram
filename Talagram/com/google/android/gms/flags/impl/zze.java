package com.google.android.gms.flags.impl;

import android.content.Context;
import java.util.concurrent.Callable;

final class zze implements Callable {
    zze(Context arg1) {
        this.val$context = arg1;
        super();
    }

    public final Object call() {
        return this.val$context.getSharedPreferences("google_sdk_flags", 0);
    }
}

