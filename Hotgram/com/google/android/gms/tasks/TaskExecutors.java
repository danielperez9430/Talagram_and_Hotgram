package com.google.android.gms.tasks;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

public final class TaskExecutors {
    final class zza implements Executor {
        private final Handler mHandler;

        public zza() {
            super();
            this.mHandler = new Handler(Looper.getMainLooper());
        }

        public final void execute(Runnable arg2) {
            this.mHandler.post(arg2);
        }
    }

    public static final Executor MAIN_THREAD;
    static final Executor zzagd;

    static {
        TaskExecutors.MAIN_THREAD = new zza();
        TaskExecutors.zzagd = new zzt();
    }

    private TaskExecutors() {
        super();
    }
}

