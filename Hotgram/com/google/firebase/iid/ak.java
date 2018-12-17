package com.google.firebase.iid;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final class ak {
    private static final Executor a;

    static {
        ak.a = al.a;
    }

    static Executor a() {
        return ak.a;
    }

    static final void a(Runnable arg0) {
        arg0.run();
    }

    static Executor b() {
        return new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue());
    }
}

