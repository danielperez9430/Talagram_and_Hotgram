package com.persianswitch.sdk.payment.managers;

import android.content.Context;
import com.persianswitch.sdk.base.BaseSetting;
import com.persianswitch.sdk.base.log.SDKLog;
import com.persianswitch.sdk.payment.model.ClientConfig;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class ScheduledTaskManager {
    final class com.persianswitch.sdk.payment.managers.ScheduledTaskManager$1 extends Task {
        com.persianswitch.sdk.payment.managers.ScheduledTaskManager$1() {
            super();
        }

        public void a(Context arg2) {
            new CardManager(arg2).b();
        }

        public boolean a(Context arg8, long arg9) {
            long v0 = BaseSetting.j(arg8);
            long v2 = TimeUnit.MILLISECONDS.convert(ClientConfig.a(arg8).f(), TimeUnit.SECONDS);
            arg9 -= v0;
            boolean v8 = Long.compare(arg9, v2) > 0 ? true : false;
            if(v8) {
                SDKLog.a("SyncCardTask", "passedFromLastSync(millis) : %d , threshold(millis) : %d", new Object[]{Long.valueOf(arg9), Long.valueOf(v2)});
            }

            return v8;
        }
    }

    public abstract class Task {
        public Task() {
            super();
        }

        public abstract boolean a(Context arg1, long arg2);

        public abstract void a(Context arg1);
    }

    public static final Task a;
    private final Context b;
    private final List c;
    private static ScheduledTaskManager d;

    static {
        ScheduledTaskManager.a = new com.persianswitch.sdk.payment.managers.ScheduledTaskManager$1();
    }

    private ScheduledTaskManager(Context arg1) {
        super();
        this.b = arg1;
        this.c = Collections.emptyList();
    }

    public static ScheduledTaskManager a(Context arg1) {
        if(ScheduledTaskManager.d == null) {
            ScheduledTaskManager.d = new ScheduledTaskManager(arg1);
        }

        return ScheduledTaskManager.d;
    }

    public void a() {
        long v0 = System.currentTimeMillis();
        Iterator v2 = this.c.iterator();
        while(v2.hasNext()) {
            Object v3 = v2.next();
            if(!((Task)v3).a(this.b, v0)) {
                continue;
            }

            ((Task)v3).a(this.b);
        }
    }
}

