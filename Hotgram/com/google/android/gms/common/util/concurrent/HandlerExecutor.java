package com.google.android.gms.common.util.concurrent;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

public class HandlerExecutor implements Executor {
    private final Handler handler;

    public HandlerExecutor(Handler arg1) {
        this(arg1.getLooper());
    }

    public HandlerExecutor(Looper arg2) {
        super();
        this.handler = new Handler(arg2);
    }

    public void execute(Runnable arg2) {
        this.handler.post(arg2);
    }
}

