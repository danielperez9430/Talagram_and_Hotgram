package com.google.firebase.iid;

import android.content.BroadcastReceiver$PendingResult;
import android.content.Intent;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

final class af {
    final Intent a;
    private final BroadcastReceiver$PendingResult b;
    private boolean c;
    private final ScheduledFuture d;

    af(Intent arg3, BroadcastReceiver$PendingResult arg4, ScheduledExecutorService arg5) {
        super();
        this.c = false;
        this.a = arg3;
        this.b = arg4;
        this.d = arg5.schedule(new ag(this, arg3), 9500, TimeUnit.MILLISECONDS);
    }

    final void a() {
        __monitor_enter(this);
        try {
            if(!this.c) {
                this.b.finish();
                this.d.cancel(false);
                this.c = true;
            }
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }
}

