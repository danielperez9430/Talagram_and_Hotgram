package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zzt implements Executor {
    zzt() {
        super();
    }

    public final void execute(Runnable arg1) {
        arg1.run();
    }
}

