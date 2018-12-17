package com.persianswitch.sdk.base.log;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class LogReporter {
    class com.persianswitch.sdk.base.log.LogReporter$1 implements Runnable {
        public void run() {
        }
    }

    private static LogReporter a;
    private final ScheduledExecutorService b;
    private boolean c;

    private LogReporter() {
        super();
        this.b = Executors.newScheduledThreadPool(1);
        this.c = false;
        if(LogReporter.a == null) {
            return;
        }

        throw new InstantiationError();
    }
}

