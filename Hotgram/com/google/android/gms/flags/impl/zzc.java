package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

final class zzc implements Callable {
    zzc(SharedPreferences arg1, String arg2, Long arg3) {
        this.zzacl = arg1;
        this.zzacm = arg2;
        this.zzacr = arg3;
        super();
    }

    public final Object call() {
        return Long.valueOf(this.zzacl.getLong(this.zzacm, this.zzacr.longValue()));
    }
}

