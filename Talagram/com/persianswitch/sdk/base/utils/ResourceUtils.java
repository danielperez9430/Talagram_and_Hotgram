package com.persianswitch.sdk.base.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import java.util.Locale;

public class ResourceUtils {
    public ResourceUtils() {
        super();
    }

    public static String a(Context arg5, String arg6, int arg7) {
        String v7;
        Class v0 = ResourceUtils.class;
        __monitor_enter(v0);
        try {
            Configuration v1 = arg5.getApplicationContext().getResources().getConfiguration();
            Locale v2 = v1.locale;
            v1.locale = new Locale(arg6);
            DisplayMetrics v6 = new DisplayMetrics();
            v7 = new Resources(arg5.getApplicationContext().getAssets(), v6, v1).getString(arg7);
            v1.locale = v2;
            new Resources(arg5.getApplicationContext().getAssets(), v6, v1);
        }
        catch(Throwable v5) {
            __monitor_exit(v0);
            throw v5;
        }

        __monitor_exit(v0);
        return v7;
    }
}

