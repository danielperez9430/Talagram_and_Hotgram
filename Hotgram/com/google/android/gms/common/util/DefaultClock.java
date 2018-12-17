package com.google.android.gms.common.util;

import android.os.SystemClock;

public class DefaultClock implements Clock {
    private static final DefaultClock zzzk;

    static {
        DefaultClock.zzzk = new DefaultClock();
    }

    private DefaultClock() {
        super();
    }

    public long currentThreadTimeMillis() {
        return SystemClock.currentThreadTimeMillis();
    }

    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public long elapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }

    public static Clock getInstance() {
        return DefaultClock.zzzk;
    }

    public long nanoTime() {
        return System.nanoTime();
    }
}

