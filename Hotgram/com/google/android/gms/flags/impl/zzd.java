package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

final class zzd implements Callable {
    zzd(SharedPreferences arg1, String arg2, String arg3) {
        this.zzacl = arg1;
        this.zzacm = arg2;
        this.zzact = arg3;
        super();
    }

    public final Object call() {
        return this.zzacl.getString(this.zzacm, this.zzact);
    }
}

