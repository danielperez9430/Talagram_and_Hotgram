package com.google.firebase.analytics.connector;

import java.util.concurrent.Executor;

final class c implements Executor {
    static final Executor a;

    static {
        c.a = new c();
    }

    private c() {
        super();
    }

    public final void execute(Runnable arg1) {
        arg1.run();
    }
}

