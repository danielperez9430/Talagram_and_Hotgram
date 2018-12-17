package com.google.android.gms.flags.impl.util;

import android.os.StrictMode$ThreadPolicy;
import android.os.StrictMode;
import java.util.concurrent.Callable;

public class StrictModeUtil {
    public StrictModeUtil() {
        super();
    }

    public static Object runWithLaxStrictMode(Callable arg2) {
        Object v2_1;
        StrictMode$ThreadPolicy v0 = StrictMode.getThreadPolicy();
        try {
            StrictMode.setThreadPolicy(StrictMode$ThreadPolicy.LAX);
            v2_1 = arg2.call();
        }
        catch(Throwable v2) {
            StrictMode.setThreadPolicy(v0);
            throw v2;
        }

        StrictMode.setThreadPolicy(v0);
        return v2_1;
    }
}

