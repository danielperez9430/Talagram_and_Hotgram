package com.google.android.gms.common.util;

import android.os.StrictMode$ThreadPolicy;
import android.os.StrictMode;

public class StrictModeUtils {
    public StrictModeUtils() {
        super();
    }

    public static StrictMode$ThreadPolicy setDynamiteThreadPolicy() {
        StrictMode.noteSlowCall("gcore.dynamite");
        StrictMode$ThreadPolicy v0 = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(StrictMode$ThreadPolicy.LAX);
        return v0;
    }
}

