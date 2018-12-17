package com.crashlytics.android.c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.concurrent.atomic.AtomicBoolean;

class q {
    private static final IntentFilter a;
    private static final IntentFilter b;
    private static final IntentFilter c;
    private final AtomicBoolean d;
    private final Context e;
    private final BroadcastReceiver f;
    private final BroadcastReceiver g;
    private boolean h;

    static {
        q.a = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        q.b = new IntentFilter("android.intent.action.ACTION_POWER_CONNECTED");
        q.c = new IntentFilter("android.intent.action.ACTION_POWER_DISCONNECTED");
    }

    public q(Context arg4) {
        super();
        this.e = arg4;
        Intent v0 = arg4.registerReceiver(null, q.a);
        int v1 = -1;
        if(v0 != null) {
            v1 = v0.getIntExtra("status", v1);
        }

        boolean v0_1 = v1 == 2 || v1 == 5 ? true : false;
        this.h = v0_1;
        this.g = new BroadcastReceiver() {
            public void onReceive(Context arg1, Intent arg2) {
                q.a(this.a, true);
            }
        };
        this.f = new BroadcastReceiver() {
            public void onReceive(Context arg1, Intent arg2) {
                q.a(this.a, false);
            }
        };
        arg4.registerReceiver(this.g, q.b);
        arg4.registerReceiver(this.f, q.c);
        this.d = new AtomicBoolean(true);
    }

    public boolean a() {
        return this.h;
    }

    static boolean a(q arg0, boolean arg1) {
        arg0.h = arg1;
        return arg1;
    }

    public void b() {
        if(!this.d.getAndSet(false)) {
            return;
        }

        this.e.unregisterReceiver(this.g);
        this.e.unregisterReceiver(this.f);
    }
}

