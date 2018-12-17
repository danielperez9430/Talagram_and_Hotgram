package com.google.ads.interactivemedia.v3.internal;

import android.os.HandlerThread;
import android.os.Process;

public final class fr extends HandlerThread {
    private final int a;

    public fr(String arg1, int arg2) {
        super(arg1);
        this.a = arg2;
    }

    public void run() {
        Process.setThreadPriority(this.a);
        super.run();
    }
}

