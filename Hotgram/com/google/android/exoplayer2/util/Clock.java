package com.google.android.exoplayer2.util;

import android.os.Handler$Callback;
import android.os.Looper;

public interface Clock {
    public static final Clock DEFAULT;

    static {
        Clock.DEFAULT = new SystemClock();
    }

    HandlerWrapper createHandler(Looper arg1, Handler$Callback arg2);

    long elapsedRealtime();

    void sleep(long arg1);

    long uptimeMillis();
}

