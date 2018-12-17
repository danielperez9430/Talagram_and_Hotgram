package com.crashlytics.android.b;

import android.annotation.TargetApi;
import android.app.Activity;
import java.util.concurrent.ExecutorService;

@TargetApi(value=14) class b extends a {
    class com.crashlytics.android.b.b$1 extends c.a.a.a.a$b {
        com.crashlytics.android.b.b$1(b arg1) {
            this.a = arg1;
            super();
        }

        public void a(Activity arg2) {
            if(this.a.a()) {
                b.a(this.a).submit(new Runnable() {
                    public void run() {
                        this.a.a.c();
                    }
                });
            }
        }
    }

    private final c.a.a.a.a$b a;
    private final ExecutorService b;

    public b(c.a.a.a.a arg2, ExecutorService arg3) {
        super();
        this.a = new com.crashlytics.android.b.b$1(this);
        this.b = arg3;
        arg2.a(this.a);
    }

    static ExecutorService a(b arg0) {
        return arg0.b;
    }
}

