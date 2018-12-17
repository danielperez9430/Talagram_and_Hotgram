package com.google.android.exoplayer2.util;

import android.os.Handler$Callback;
import android.os.Handler;
import android.os.Looper;

final class SystemClock implements Clock {
    SystemClock() {
        super();
    }

    public HandlerWrapper createHandler(Looper arg3, Handler$Callback arg4) {
        return new SystemHandlerWrapper(new Handler(arg3, arg4));
    }

    public long elapsedRealtime() {
        return android.os.SystemClock.elapsedRealtime();
    }

    public void sleep(long arg1) {
        android.os.SystemClock.sleep(arg1);
    }

    public long uptimeMillis() {
        return android.os.SystemClock.uptimeMillis();
    }
}

