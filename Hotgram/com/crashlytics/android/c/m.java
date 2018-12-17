package com.crashlytics.android.c;

import c.a.a.a.c;
import java.util.concurrent.atomic.AtomicBoolean;

class m implements Thread$UncaughtExceptionHandler {
    interface a {
        void a(Thread arg1, Throwable arg2);
    }

    private final a a;
    private final Thread$UncaughtExceptionHandler b;
    private final AtomicBoolean c;

    public m(a arg1, Thread$UncaughtExceptionHandler arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = new AtomicBoolean(false);
    }

    boolean a() {
        return this.c.get();
    }

    public void uncaughtException(Thread arg6, Throwable arg7) {
        this.c.set(true);
        try {
            this.a.a(arg6, arg7);
        }
        catch(Throwable v1) {
        }
        catch(Exception v1_1) {
            try {
                c.h().e("CrashlyticsCore", "An error occurred in the uncaught exception handler", ((Throwable)v1_1));
            }
            catch(Throwable v1) {
                c.h().a("CrashlyticsCore", "Crashlytics completed exception processing. Invoking default exception handler.");
                this.b.uncaughtException(arg6, arg7);
                this.c.set(false);
                throw v1;
            }
        }

        c.h().a("CrashlyticsCore", "Crashlytics completed exception processing. Invoking default exception handler.");
        this.b.uncaughtException(arg6, arg7);
        this.c.set(false);
    }
}

