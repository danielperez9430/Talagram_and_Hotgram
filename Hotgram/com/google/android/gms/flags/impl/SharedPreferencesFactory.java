package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.flags.impl.util.StrictModeUtil;

public class SharedPreferencesFactory {
    private static SharedPreferences zzacv;

    static {
    }

    public SharedPreferencesFactory() {
        super();
    }

    public static SharedPreferences getSharedPreferences(Context arg2) {
        Class v0 = SharedPreferences.class;
        __monitor_enter(v0);
        try {
            if(SharedPreferencesFactory.zzacv == null) {
                SharedPreferencesFactory.zzacv = StrictModeUtil.runWithLaxStrictMode(new zze(arg2));
            }

            __monitor_exit(v0);
            return SharedPreferencesFactory.zzacv;
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_12;
        }

        throw v2;
    }
}

