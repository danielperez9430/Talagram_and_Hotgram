package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

final class zza implements Callable {
    zza(SharedPreferences arg1, String arg2, Boolean arg3) {
        this.zzacl = arg1;
        this.zzacm = arg2;
        this.zzacn = arg3;
        super();
    }

    public final Object call() {
        return Boolean.valueOf(this.zzacl.getBoolean(this.zzacm, this.zzacn.booleanValue()));
    }
}

