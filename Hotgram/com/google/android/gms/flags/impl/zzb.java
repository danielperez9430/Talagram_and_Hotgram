package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

final class zzb implements Callable {
    zzb(SharedPreferences arg1, String arg2, Integer arg3) {
        this.zzacl = arg1;
        this.zzacm = arg2;
        this.zzacp = arg3;
        super();
    }

    public final Object call() {
        return Integer.valueOf(this.zzacl.getInt(this.zzacm, this.zzacp.intValue()));
    }
}

