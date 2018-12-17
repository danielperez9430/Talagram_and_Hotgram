package com.google.android.gms.common.util;

import android.os.Looper;

public class ThreadUtils {
    private ThreadUtils() {
        super();
    }

    public static boolean isMainThread() {
        if(Looper.getMainLooper() == Looper.myLooper()) {
            return 1;
        }

        return 0;
    }
}

