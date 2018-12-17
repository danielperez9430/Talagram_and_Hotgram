package com.crashlytics.android.c;

import android.os.Looper;
import c.a.a.a.c;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

class g {
    private final ExecutorService a;

    public g(ExecutorService arg1) {
        super();
        this.a = arg1;
    }

    Object a(Callable arg5) {
        Object v0 = null;
        try {
            if(Looper.getMainLooper() == Looper.myLooper()) {
                return this.a.submit(arg5).get(4, TimeUnit.SECONDS);
            }

            return this.a.submit(arg5).get();
        }
        catch(Exception v5) {
            c.h().e("CrashlyticsCore", "Failed to execute task.", ((Throwable)v5));
            return v0;
        }
        catch(RejectedExecutionException ) {
            c.h().a("CrashlyticsCore", "Executor is shut down because we\'re handling a fatal crash.");
            return v0;
        }
    }

    Future a(Runnable arg3) {
        try {
            return this.a.submit(new Runnable(arg3) {
                public void run() {
                    try {
                        this.a.run();
                    }
                    catch(Exception v0) {
                        c.h().e("CrashlyticsCore", "Failed to execute task.", ((Throwable)v0));
                    }
                }
            });
        }
        catch(RejectedExecutionException ) {
            c.h().a("CrashlyticsCore", "Executor is shut down because we\'re handling a fatal crash.");
            return null;
        }
    }

    Future b(Callable arg3) {
        try {
            return this.a.submit(new Callable(arg3) {
                public Object call() {
                    try {
                        return this.a.call();
                    }
                    catch(Exception v0) {
                        c.h().e("CrashlyticsCore", "Failed to execute task.", ((Throwable)v0));
                        return null;
                    }
                }
            });
        }
        catch(RejectedExecutionException ) {
            c.h().a("CrashlyticsCore", "Executor is shut down because we\'re handling a fatal crash.");
            return null;
        }
    }
}

